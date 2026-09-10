package com.everrefine.elms.infrastructure.dao;

import com.everrefine.elms.infrastructure.entity.tag.TagEntity;
import com.everrefine.elms.infrastructure.row.TagWithLessonIdRow;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/** TagのDAOインターフェース。 */
public interface TagDao extends CrudRepository<TagEntity, UUID> {

  @Modifying
  @Query(
      """
      INSERT INTO tags (name)
      SELECT unnest(CAST(:names AS VARCHAR[]))
      ON CONFLICT (name) DO NOTHING
      """)
  void insertAllIfNotExists(@Param("names") String[] names);

  List<TagEntity> findByNameIn(List<String> names);

  @Query(
      """
      SELECT t.id, t.name, t.created_at, t.updated_at
      FROM tags t
      JOIN lesson_tags lt ON lt.tag_id = t.id
      WHERE lt.lesson_id = :lessonId
      """)
  List<TagEntity> findByLessonId(@Param("lessonId") UUID lessonId);

  @Query(
      """
      SELECT lt.lesson_id, t.id AS tag_id, t.name, t.created_at, t.updated_at
      FROM tags t
      JOIN lesson_tags lt ON lt.tag_id = t.id
      WHERE lt.lesson_id IN (:lessonIds)
      """)
  List<TagWithLessonIdRow> findByLessonIdIn(@Param("lessonIds") Collection<UUID> lessonIds);
}
