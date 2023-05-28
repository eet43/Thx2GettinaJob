package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CalendarBookmarkTimeDetail {
    private String turn;
    private LocalDateTime examDate;
    private LocalDateTime regStartDate;
    private LocalDateTime regEndDate;
    private LocalDateTime addRegStartDate;
    private LocalDateTime addRegEndDate;
    private LocalDateTime resultDate;

    public static CalendarBookmarkTimeDetail toDto(ExamTimeStamp time) {
        return new CalendarBookmarkTimeDetail(time.getTurn(), time.getExamDate(), time.getRegStartDate()
        , time.getRegEndDate(), time.getAddRegStartDate(), time.getRegEndDate(), time.getResultDate());
    }
}
