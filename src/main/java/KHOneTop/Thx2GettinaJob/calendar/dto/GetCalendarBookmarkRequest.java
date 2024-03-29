package KHOneTop.Thx2GettinaJob.calendar.dto;

import java.time.LocalDate;
import java.util.List;

public record GetCalendarBookmarkRequest(
        Long userId,
        LocalDate startDate,
        LocalDate endDate,
        List<GetCalenderDetailRequest> exams
) {
}
