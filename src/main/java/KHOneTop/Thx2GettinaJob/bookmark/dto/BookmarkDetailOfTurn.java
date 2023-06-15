package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "회차 자격증 일정")
@Data
public class BookmarkDetailOfTurn implements Serializable {
    @Schema(description = "일정 Id", nullable = false, example = "2L")
    private final Long id;
    @Schema(description = "회차", nullable = false, example = "300회차")
    private final String turn;
    @Schema(description = "시험일자", nullable = false)
    private final LocalDateTime examDate;
    @Schema(description = "등록시작일자", nullable = false)
    private final LocalDateTime regStartDate;
    @Schema(description = "등록종료일자", nullable = false)
    private final LocalDateTime regEndDate;
    @Schema(description = "추가등록시작일자")
    private final LocalDateTime addRegStartDate;
    @Schema(description = "추가등록종료일자")
    private final LocalDateTime addRegEndDate;
    @Schema(description = "시험결과발표일자")
    private final LocalDateTime resultDate;
    @Schema(description = "시험 접수 상태", nullable = false, example = "추가접수중")
    private final String status;
    @Schema(description = "D-day", nullable = false, example = "3")
    private final Long day;


    public static BookmarkDetailOfTurn fromEntity(ExamTimeStamp timeStamp, String status, Long day) {
        return new BookmarkDetailOfTurn(timeStamp.getId(), timeStamp.getTurn(), timeStamp.getExamDate(), timeStamp.getRegStartDate(), timeStamp.getRegEndDate(),
                timeStamp.getAddRegStartDate(), timeStamp.getAddRegEndDate(), timeStamp.getResultDate(), status, day);
    }
}
