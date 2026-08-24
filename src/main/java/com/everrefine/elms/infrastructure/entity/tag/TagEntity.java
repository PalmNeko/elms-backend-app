package com.everrefine.elms.infrastructure.entity.tag;

import com.everrefine.elms.domain.model.tag.Tag;
import com.everrefine.elms.domain.model.tag.TagName;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** タグのエンティティ */
@Table("tags")
public record TagEntity(@Id UUID id, String name) {

  /**
   * タグのエンティティをドメインモデルに変換する
   *
   * @return タグ
   */
  public Tag toDomain() {
    return new Tag(id, new TagName(name));
  }
}
