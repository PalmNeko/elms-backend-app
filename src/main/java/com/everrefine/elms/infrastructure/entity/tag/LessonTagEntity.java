package com.everrefine.elms.infrastructure.entity.tag;

import com.everrefine.elms.domain.model.tag.Tag;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** レッスンタグのエンティティ */
@Table("lesson_tags")
public record LessonTagEntity(
    @Id UUID id, // サロゲートキー
    UUID lessonId,
    UUID tagId) {

  /**
   * レッスンIDとタグからレッスンタグのエンティティを作る
   *
   * @param lessonId レッスンID
   * @param tag タグ
   * @return レッスンタグのエンティティ
   */
  public static LessonTagEntity from(UUID lessonId, Tag tag) {
    return new LessonTagEntity(null, lessonId, tag.id());
  }
}
