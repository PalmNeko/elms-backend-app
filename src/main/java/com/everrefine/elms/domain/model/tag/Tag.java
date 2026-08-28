package com.everrefine.elms.domain.model.tag;

import java.time.LocalDateTime;
import java.util.UUID;

/** タグのドメインモデル */
public record Tag(UUID id, TagName name, LocalDateTime createdAt, LocalDateTime updatedAt) {

  /**
   * 新規作成用のタグを作成する。
   *
   * @param name タグ名
   * @return 新規作成用のタグ
   */
  public static Tag create(String name) {
    LocalDateTime now = LocalDateTime.now();
    return new Tag(null, new TagName(name), now, now);
  }
}
