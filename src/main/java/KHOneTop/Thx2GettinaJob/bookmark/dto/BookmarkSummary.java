package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "검색에 쓰이는 즐겨찾기 요약 리스트")
public record BookmarkSummary(
        @Schema(description = "시험 Id", nullable = false, example = "2L")
        Long id,
        @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
        String name,
        @Schema(description = "직접 등록인지 공개적 시험인지", nullable = false, example = "false")
        Boolean isPublic
) {
        public static BookmarkSummary fromEntity(Exam exam) {
                return new BookmarkSummary(exam.getId(), exam.getName(), exam.getIsPublic());
        }
}
