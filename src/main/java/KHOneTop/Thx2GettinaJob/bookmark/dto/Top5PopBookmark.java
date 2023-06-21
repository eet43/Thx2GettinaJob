package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인기있는 자격증")
public record Top5PopBookmark(
        @Schema(description = "시험 Id", nullable = false, example = "2L")
        Long examId,
        @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
        String name,
        @Schema(description = "시험 발급처", nullable = false, example = "한국진흥원")
        String issuer,
        @Schema(description = "시험 접수 상태", nullable = false, example = "추가접수중")
        String status,
        @Schema(description = "D-day", nullable = false, example = "3")
        Long day,
        @Schema(description = "즐겨찾기 수", nullable = false, example = "10")
        Long count
) {
    public static Top5PopBookmark fromEntity(Exam exam, String status, Long day, Long count) {
        return new Top5PopBookmark(exam.getId(), exam.getName(), exam.getIssuer(), status, day, count);
    }
}
