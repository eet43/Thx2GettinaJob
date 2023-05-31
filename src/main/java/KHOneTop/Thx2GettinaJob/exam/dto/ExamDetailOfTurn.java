package KHOneTop.Thx2GettinaJob.exam.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "회차 자격증 일정")
public record ExamDetailOfTurn(
        @Schema(description = "일정 Id", nullable = false, example = "2L")
        Long id,
        @Schema(description = "회차", nullable = false, example = "300회차")
        String turn,
        @Schema(description = "시험일자", nullable = false)
        LocalDateTime examDate,
        @Schema(description = "등록시작일자", nullable = false)
        LocalDateTime regStartDate,
        @Schema(description = "등록종료일자", nullable = false)
        LocalDateTime regEndDate,
        @Schema(description = "추가등록시작일자")
        LocalDateTime addRegStartDate,
        @Schema(description = "추가등록종료일자")
        LocalDateTime addRegEndDate,
        @Schema(description = "시험결과발표일자")
        LocalDateTime resultDate,
        @Schema(description = "시험 접수 상태", nullable = false, example = "추가접수중")
        String status,
        @Schema(description = "D-day", nullable = false, example = "3")
        Long day
) {
    public static ExamDetailOfTurn fromEntity(ExamTimeStamp timeStamp, String status, Long day) {
        return new ExamDetailOfTurn(timeStamp.getId(), timeStamp.getTurn(), timeStamp.getExamDate(), timeStamp.getRegStartDate(), timeStamp.getRegEndDate(),
                timeStamp.getAddRegStartDate(), timeStamp.getAddRegEndDate(), timeStamp.getResultDate(), status, day);
    }
}
