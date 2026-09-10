package com.everrefine.elms.infrastructure.row;

import com.everrefine.elms.domain.model.tag.Tag;
import com.everrefine.elms.domain.model.tag.TagName;
import java.time.LocalDateTime;
import java.util.UUID;

/** タグとレッスンIDをJOINしたセレクト結果の1行。 */
public record TagWithLessonIdRow(
    UUID lessonId, UUID tagId, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {

  /**
   * この行のタグ部分をドメインモデルへ変換する。
   *
   * @return タグ
   */
  public Tag toTag() {
    return new Tag(tagId, new TagName(name), createdAt, updatedAt);
  }
}
