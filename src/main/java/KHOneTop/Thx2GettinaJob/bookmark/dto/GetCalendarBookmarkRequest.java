package KHOneTop.Thx2GettinaJob.bookmark.dto;

import java.time.LocalDate;
import java.util.List;

public record GetCalendarBookmarkRequest(
        LocalDate startDate,
        LocalDate endDate,
        List<GetCalenderDetailRequest> exams
) {
}
