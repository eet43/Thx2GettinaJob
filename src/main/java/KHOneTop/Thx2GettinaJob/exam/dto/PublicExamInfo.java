package KHOneTop.Thx2GettinaJob.exam.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "검색에 쓰이는 공공시험 요약 리스트")
public record PublicExamInfo(
        @Schema(description = "시험 Id", nullable = false, example = "2L")
        Long id,
        @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
        String name,
        @Schema(description = "자격증 발급처", nullable = false, example = "한국진흥")
        String issuer
) {
}
