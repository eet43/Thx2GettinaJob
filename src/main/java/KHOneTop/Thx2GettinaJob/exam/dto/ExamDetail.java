package KHOneTop.Thx2GettinaJob.exam.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ExamDetail(
        @Schema(description = "시험 Id", nullable = false, example = "2L")
        Long id,
        @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
        String name,
        @Schema(description = "자격증 발급처", example = "한국진흥")
        String issuer,
        @Schema(description = "홈페이지", example = "http")
        String url,
        @Schema(description = "시험일자")
        LocalDateTime examDate,
        @Schema(description = "등록시작일자")
        LocalDateTime regStartDate,
        @Schema(description = "등록종료일자")
        LocalDateTime regEndDate,
        @Schema(description = "추가등록시작일자")
        LocalDateTime addRegStartDate,
        @Schema(description = "추가등록종료일자")
        LocalDateTime addRegEndDate,
        @Schema(description = "시험결과발표일자")
        LocalDateTime resultDate
) {
        public static ExamDetail toDto(Exam exam, ExamTimeStamp timeStamp) {
                return new ExamDetail(exam.getId(), exam.getName(), exam.getIssuer(), exam.getUrl(),
                        timeStamp.getExamDate(), timeStamp.getRegStartDate(), timeStamp.getRegEndDate(),
                        timeStamp.getAddRegStartDate(), timeStamp.getAddRegEndDate(), timeStamp.getResultDate());
        }
}
