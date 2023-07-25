package KHOneTop.Thx2GettinaJob.common.checkDday.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "회차 자격증 일정")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExamDdayTimeInfo implements Serializable {
    @Schema(description = "일정 Id", nullable = false, example = "2L")
    private Long id;
    @Schema(description = "회차", nullable = false, example = "300회차")
    private String turn;
    @Schema(description = "시험일자", nullable = false)
    private LocalDateTime examDate;
    @Schema(description = "등록시작일자", nullable = false)
    private LocalDateTime regStartDate;
    @Schema(description = "등록종료일자", nullable = false)
    private LocalDateTime regEndDate;
    @Schema(description = "추가등록시작일자")
    private LocalDateTime addRegStartDate;
    @Schema(description = "추가등록종료일자")
    private LocalDateTime addRegEndDate;
    @Schema(description = "시험결과발표일자")
    private LocalDateTime resultDate;
    @Schema(description = "시험 접수 상태", nullable = false, example = "추가접수중")
    private String status;
    @Schema(description = "D-day", nullable = false, example = "3")
    private Long day;


    public static ExamDdayTimeInfo create(ExamTimeStamp examTime) {
        ExamDdayTimeInfo dto = new ExamDdayTimeInfo();
        dto.id = examTime.getId();
        dto.turn = examTime.getTurn();
        dto.examDate = examTime.getExamDate();
        dto.regStartDate = examTime.getRegStartDate();
        dto.regEndDate = examTime.getRegEndDate();
        dto.addRegStartDate = examTime.getAddRegStartDate();
        dto.addRegEndDate = examTime.getAddRegEndDate();
        dto.resultDate = examTime.getResultDate();//false 로 초기화
        return dto;
    }
    public void setDday(String status, Long day) {
        this.status = status;
        this.day = day;
    }
}