package KHOneTop.Thx2GettinaJob.exam.dto;

import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkInfo;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public record HomeSearch(
        @Schema(description = "시험 Id", nullable = false, example = "2L")
        Long id,
        @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
        String name,
        @Schema(description = "자격증 발급처", nullable = false, example = "한국진흥")
        String issuer,
        @Schema(description = "회차 여부", nullable = false)
        Boolean isTurn,
        @Schema(description = "즐겨찾기 여부", nullable = false)
        Boolean isBookmark,
        @Schema(description = "시험 접수 상태", nullable = false, example = "추가접수중")
        String status,
        @Schema(description = "D-day", example = "3")
        Long day
) {
    public static HomeSearch fromEntity(Exam exam, Boolean isTurn, Boolean isBookmark, String status, Long day) {
        return new HomeSearch(exam.getId(), exam.getName(), exam.getIssuer(), isTurn, isBookmark, status, day);
    }
}
