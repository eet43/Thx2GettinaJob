package KHOneTop.Thx2GettinaJob.exam.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import io.swagger.v3.oas.annotations.media.Schema;

public record ExamInfo(
        @Schema(description = "시험 Id", nullable = false, example = "2L")
        Long id,
        @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
        String name,
        @Schema(description = "자격증 발급처", nullable = false, example = "한국진흥")
        String issuer,
        @Schema(description = "직접추가 여부", nullable = false)
        Boolean isPublic,
        @Schema(description = "즐겨찾기 여부", nullable = false)
        Boolean isBookmark
) {
    public static ExamInfo toDto(Exam exam, boolean isBookmark) {
        return new ExamInfo(exam.getId(), exam.getName(), exam.getIssuer(),
                exam.getIsPublic(), isBookmark);
    }
}
