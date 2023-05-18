package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "즐겨찾기 자격증 정보")
public record BookmarkInfo(
        @Schema(description = "시험 Id", nullable = false, example = "2L")
        Long id,
        @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
        String name,
        @Schema(description = "시험 발급처", nullable = false, example = "한국진흥원")
        String issuer,
        @Schema(description = "홈페이지", nullable = false, example = "http~")
        String url,
        @Schema(description = "직접 등록인지 공개적 시험인지", nullable = false, example = "false")
        Boolean isPublic,
        @Schema(description = "회차가 있는 시험인지", nullable = false, example = "false")
        Boolean isTurn,
        @Schema(description = "시험 접수 상태", nullable = false, example = "추가접수중")
        String status,
        @Schema(description = "D-day", nullable = false, example = "3")
        Long day
) {
    public static BookmarkInfo fromEntity(Exam exam, Boolean isTurn, String status, Long day) {
        return new BookmarkInfo(exam.getId(), exam.getName(), exam.getIssuer(), exam.getUrl(), exam.getIsPublic(), isTurn, status, day);
    }

}
