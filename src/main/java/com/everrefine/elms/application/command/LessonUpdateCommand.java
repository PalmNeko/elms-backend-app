package com.everrefine.elms.application.command;

import com.everrefine.elms.domain.model.lesson.Lesson;
import com.everrefine.elms.domain.model.tag.Tag;
import com.everrefine.elms.domain.model.tag.TagCollection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** レッスン更新用のコマンド。 */
public record LessonUpdateCommand(
    UUID id, String title, String content, String videoUrl, List<String> tags) {

  /**
   * Lessonエンティティに変換する。
   *
   * @param lesson 更新対象のレッスン
   * @return 更新後のレッスンエンティティ
   */
  public Lesson toLesson(Lesson lesson) {
    return lesson.update(title, content, videoUrl);
  }

  /**
   * タグコレクションに変換する。
   *
   * @return タグコレクション
   */
  public Optional<TagCollection> toTagCollection() {
    return Optional.ofNullable(tags)
        .map(tags -> TagCollection.create(tags.stream().map(Tag::create).toList()));
  }
}
