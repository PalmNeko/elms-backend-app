package com.everrefine.elms.domain.repository;

import com.everrefine.elms.domain.model.tag.TagCollection;
import java.util.UUID;

public interface TagRepository {

  /**
   * レッスン
   *
   * @param lessonId レッスンID
   * @param tags タグID
   * @return タグのコレクション
   */
  TagCollection replaceLessonTags(UUID lessonId, TagCollection tags);
}
