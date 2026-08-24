package com.everrefine.elms.application.dto;

import com.everrefine.elms.domain.model.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

/** タグDTO */
public record TagDto(
    @Schema(description = "タグID", example = "1") UUID id,
    @Schema(description = "タグ名", example = "Java") String name) {

  /**
   * TagDtoを生成する
   *
   * @param tag タグ
   * @return TagDto
   */
  public static TagDto from(Tag tag) {
    return new TagDto(tag.id(), tag.name().value());
  }
}
