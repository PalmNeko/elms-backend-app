package com.everrefine.elms.application.dto;

import com.everrefine.elms.domain.model.lesson.LessonGroupWithLessons;
import com.everrefine.elms.domain.model.tag.TagCollection;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/** ユーザーレッスングループのDTO。 */
public record UserLessonGroupDto(
    @Schema(description = "レッスングループID", example = "1") UUID id,
    @Schema(description = "コースID", example = "2") UUID courseId,
    @Schema(description = "レッスングループの表示順", example = "1.0") BigDecimal lessonGroupOrder,
    @Schema(description = "レッスングループ名", example = "第1章: 基礎編") String name,
    @Schema(description = "登録日時", example = "2024-01-01T09:00:00") LocalDateTime createdAt,
    @Schema(description = "更新日時", example = "2024-06-01T10:30:00") LocalDateTime updatedAt,
    @Schema(description = "レッスン一覧") List<UserLessonDto> userLessons) {

  /**
   * レッスングループと配下レッスンの読み取りモデルと、完了済みレッスンID、レッスンごとのタグから UserLessonGroupDtoを生成する。
   *
   * @param group レッスングループと配下レッスンの読み取りモデル
   * @param completedLessonIds 受講完了済みのレッスンID集合
   * @param tagsByLessonId レッスンIDをキーとしたタグのコレクションのMap
   * @return ユーザーレッスングループDTO
   */
  public static UserLessonGroupDto from(
      LessonGroupWithLessons group,
      Set<UUID> completedLessonIds,
      Map<UUID, TagCollection> tagsByLessonId) {
    List<UserLessonDto> userLessons =
        group.lessons().stream()
            .map(
                lesson -> {
                  TagCollection tags =
                      tagsByLessonId.getOrDefault(lesson.id(), TagCollection.empty());
                  return new UserLessonDto(LessonDto.from(group, lesson, tags),
                      completedLessonIds.contains(lesson.id()));
                })
            .toList();
    return new UserLessonGroupDto(
        group.id(),
        group.courseId(),
        group.lessonGroupOrder(),
        group.title(),
        group.createdAt(),
        group.updatedAt(),
        userLessons);
  }
}
