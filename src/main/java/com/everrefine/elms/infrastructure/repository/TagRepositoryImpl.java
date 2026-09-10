package com.everrefine.elms.infrastructure.repository;

import com.everrefine.elms.domain.model.tag.TagCollection;
import com.everrefine.elms.domain.repository.TagRepository;
import com.everrefine.elms.infrastructure.dao.LessonTagDao;
import com.everrefine.elms.infrastructure.dao.TagDao;
import com.everrefine.elms.infrastructure.entity.tag.LessonTagEntity;
import com.everrefine.elms.infrastructure.entity.tag.TagEntity;
import com.everrefine.elms.infrastructure.row.TagWithLessonIdRow;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class TagRepositoryImpl implements TagRepository {

  private final TagDao tagDao;
  private final LessonTagDao lessonTagDao;
  private final JdbcAggregateTemplate jdbcAggregateTemplate;

  @Override
  @Transactional
  public TagCollection replaceLessonTags(UUID lessonId, TagCollection tags) {
    TagCollection saved = saveAll(tags);
    lessonTagDao.deleteByLessonId(lessonId);
    jdbcAggregateTemplate.insertAll(
        saved.values().stream().map(tag -> LessonTagEntity.from(lessonId, tag)).toList());
    return saved;
  }

  private TagCollection saveAll(TagCollection tags) {
    if (tags.values().isEmpty()) {
      return TagCollection.empty();
    }
    List<String> tagNames = tags.nameValues();
    tagDao.insertAllIfNotExists(tagNames.toArray(String[]::new));
    return TagCollection.create(
        tagDao.findByNameIn(tagNames).stream().map(TagEntity::toDomain).toList());
  }

  @Override
  public TagCollection findByLessonId(UUID lessonId) {
    return TagCollection.create(
        tagDao.findByLessonId(lessonId).stream().map(TagEntity::toDomain).toList());
  }

  @Override
  public Map<UUID, TagCollection> findByLessonIdIn(Collection<UUID> lessonIds) {
    if (lessonIds.isEmpty()) {
      return Map.of();
    }
    return tagDao.findByLessonIdIn(lessonIds).stream()
        .collect(
            Collectors.groupingBy(
                TagWithLessonIdRow::lessonId,
                Collectors.collectingAndThen(
                    Collectors.mapping(TagWithLessonIdRow::toTag, Collectors.toList()),
                    TagCollection::create)));
  }
}
