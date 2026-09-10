package com.everrefine.elms.domain.repository;

import com.everrefine.elms.domain.model.tag.TagCollection;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface TagRepository {

  /**
   * レッスンIDとタグのコレクションから置き換える
   *
   * @param lessonId レッスンID
   * @param tags タグのコレクション
   * @return タグのコレクション
   */
  TagCollection replaceLessonTags(UUID lessonId, TagCollection tags);

  /**
   * レッスンIDからレッスンのタグを取得する
   *
   * @param lessonId レッスンID
   * @return タグ一覧
   */
  TagCollection findByLessonId(UUID lessonId);

  /**
   * レッスンIDの一覧からレッスンごとのタグを取得する
   *
   * <p>タグが1件も紐づかないレッスンのIDは、返却されるMapのキーに含まれない。
   *
   * @param lessonIds レッスンIDの一覧
   * @return レッスンIDをキーとしたタグのコレクションのMap
   */
  Map<UUID, TagCollection> findByLessonIdIn(Collection<UUID> lessonIds);
}
