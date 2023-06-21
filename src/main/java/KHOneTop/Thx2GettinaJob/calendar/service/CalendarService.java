package KHOneTop.Thx2GettinaJob.calendar.service;

import KHOneTop.Thx2GettinaJob.calendar.dto.CalendarBookmarkDetail;
import KHOneTop.Thx2GettinaJob.calendar.dto.CalendarBookmarkSearch;
import KHOneTop.Thx2GettinaJob.calendar.dto.GetCalendarBookmarkRequest;
import KHOneTop.Thx2GettinaJob.calendar.dto.GetCalendarSearchRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.GetBookmarkListRequest;

import java.util.List;

public interface CalendarService {
    List<CalendarBookmarkSearch> getCalendarBookmarkInfo(GetCalendarSearchRequest request);

    List<CalendarBookmarkDetail> getCalendarBookmarkDetail(GetCalendarBookmarkRequest request);
}
