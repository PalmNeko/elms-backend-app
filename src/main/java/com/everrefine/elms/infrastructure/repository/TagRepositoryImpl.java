package com.everrefine.elms.infrastructure.repository;

import com.everrefine.elms.domain.model.tag.TagCollection;
import com.everrefine.elms.domain.repository.TagRepository;
import com.everrefine.elms.infrastructure.dao.LessonTagDao;
import com.everrefine.elms.infrastructure.dao.TagDao;
import com.everrefine.elms.infrastructure.entity.tag.LessonTagEntity;
import com.everrefine.elms.infrastructure.entity.tag.TagEntity;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class TagRepositoryImpl implements TagRepository {

  private final TagDao tagDao;
  private final LessonTagDao lessonTagDao;

  @Override
  @Transactional
  public TagCollection replaceLessonTags(UUID lessonId, TagCollection tags) {
    TagCollection saved = saveAll(tags);
    lessonTagDao.deleteByLessonId(lessonId);
    lessonTagDao.saveAll(
        saved.values().stream().map(tag -> LessonTagEntity.from(lessonId, tag)).toList());
    return saved;
  }

  private TagCollection saveAll(TagCollection tags) {
    if (tags.values().isEmpty()) {
      return TagCollection.create(List.of());
    }
    List<String> tagNames = tags.nameValues();
    tagDao.insertAllIfNotExists(tagNames.toArray(String[]::new));
    return TagCollection.create(
        tagDao.findByNameIn(tagNames).stream().map(TagEntity::toDomain).toList());
  }
}
