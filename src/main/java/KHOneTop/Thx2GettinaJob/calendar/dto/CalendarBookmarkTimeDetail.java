package KHOneTop.Thx2GettinaJob.calendar.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CalendarBookmarkTimeDetail {
    private final String turn;
    private final LocalDateTime examDate;
    private final LocalDateTime regStartDate;
    private final LocalDateTime regEndDate;
    private final LocalDateTime addRegStartDate;
    private final LocalDateTime addRegEndDate;
    private final LocalDateTime resultDate;

    public static CalendarBookmarkTimeDetail toDto(ExamTimeStamp time) {
        return new CalendarBookmarkTimeDetail(time.getTurn(), time.getExamDate(), time.getRegStartDate()
        , time.getRegEndDate(), time.getAddRegStartDate(), time.getRegEndDate(), time.getResultDate());
    }
}
