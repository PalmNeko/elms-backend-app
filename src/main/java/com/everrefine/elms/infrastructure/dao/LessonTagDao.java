package com.everrefine.elms.infrastructure.dao;

import com.everrefine.elms.infrastructure.entity.tag.LessonTagEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/** レッスンタグのDAOインターフェース */
public interface LessonTagDao extends CrudRepository<LessonTagEntity, UUID> {

  void deleteByLessonId(UUID lessonId);
}
