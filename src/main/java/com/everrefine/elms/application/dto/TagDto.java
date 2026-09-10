package com.everrefine.elms.application.dto;

import com.everrefine.elms.domain.model.tag.Tag;
import com.everrefine.elms.domain.model.tag.TagCollection;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.UUID;

/** タグDTO */
public record TagDto(
    @Schema(description = "タグID", example = "ad2cb47f-2ae3-4013-b1b1-e915067ca52f") UUID id,
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

  /**
   * タグのコレクションからTagDtoのリストを生成する
   *
   * @param tags タグのコレクション
   * @return TagDtoのリスト
   */
  public static List<TagDto> listFrom(TagCollection tags) {
    return tags.values().stream().map(TagDto::from).toList();
  }
}
