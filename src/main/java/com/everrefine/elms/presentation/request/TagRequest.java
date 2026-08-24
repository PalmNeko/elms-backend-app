package com.everrefine.elms.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** タグリクエスト。 */
public record TagRequest(
    @Schema(description = "タグ名（必須・32文字以内）（前後の空白はトリムされる）", example = "Java")
        @NotBlank(message = "タグ名は必須です")
        @Size(max = 32, message = "タグ名は32文字以内で入力してください")
        String name) {}
