package KHOneTop.Thx2GettinaJob.calendar.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarBookmarkTimeDetail {
    private final String turn;
    private final LocalDateTime examDate;
    private final LocalDateTime regStartDate;
    private final LocalDateTime regEndDate;
    private final LocalDateTime addRegStartDate;
    private final LocalDateTime addRegEndDate;
    private final LocalDateTime resultDate;

    public static CalendarBookmarkTimeDetail toDtoFilter(ExamTimeStamp time, GetCalenderDetailRequest request, LocalDate startDate, LocalDate endDate) {
        return new CalendarBookmarkTimeDetail(
                time.getTurn(),
                isBetween(time.getExamDate(), startDate, endDate) && Boolean.TRUE.equals(request.HavExamDate()) ? time.getExamDate() : null,
                isBetween(time.getRegStartDate(), startDate, endDate)  && Boolean.TRUE.equals(request.HavRegDate()) ? time.getRegStartDate() : null,
                isBetween(time.getRegEndDate(), startDate, endDate) && Boolean.TRUE.equals(request.HavRegDate()) ? time.getRegEndDate() : null,
                isBetween(time.getAddRegStartDate(), startDate, endDate) && Boolean.TRUE.equals(request.HavAddRegDate())? time.getAddRegStartDate() : null,
                isBetween(time.getAddRegEndDate(), startDate, endDate) && Boolean.TRUE.equals(request.HavAddRegDate()) ? time.getAddRegEndDate() : null,
                isBetween(time.getResultDate(), startDate, endDate) && Boolean.TRUE.equals(request.HavResultDate())? time.getResultDate() : null
        );
    }

    public static CalendarBookmarkTimeDetail toDtoNonFilter(ExamTimeStamp time, LocalDate startDate, LocalDate endDate) {
        return new CalendarBookmarkTimeDetail(
                time.getTurn(),
                isBetween(time.getExamDate(), startDate, endDate)? time.getExamDate() : null,
                isBetween(time.getRegStartDate(), startDate, endDate) ? time.getRegStartDate() : null,
                isBetween(time.getRegEndDate(), startDate, endDate) ? time.getRegEndDate() : null,
                isBetween(time.getAddRegStartDate(), startDate, endDate) ? time.getAddRegStartDate() : null,
                isBetween(time.getAddRegEndDate(), startDate, endDate) ? time.getAddRegEndDate() : null,
                isBetween(time.getResultDate(), startDate, endDate) ? time.getResultDate() : null
        );
    }

    private static boolean isBetween(LocalDateTime date, LocalDate startDate, LocalDate endDate) {
        if(startDate == null && endDate == null) {
            return true;
        } else if (date != null && startDate != null && endDate != null) {
            return !date.isBefore(startDate.atStartOfDay()) && !date.isAfter(endDate.atTime(23, 59, 59));
        }
        return false;
    }

}
