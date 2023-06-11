package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;

import java.util.List;

public interface BookmarkService {
    void addBookmarkPriExam(AddBookmarkPrivateExamRequest request);

    void addBookmarkPubExam(AddBookmarkPubExamRequest request);

    void deleteBookmarkPubExam(DeleteBookmarkPubExamRequest request);

    List<BookmarkSummary> getBookmarkSummary(GetBookmarkListRequest request);

    List<BookmarkInfo> getBookmarkInfo(GetBookmarkListRequest request);

    List<Top5PopBookmark> getTop5PopBookmarks();

    List<Top3NearBookmark> getTop3NearBookmarks();

    List<CalendarBookmarkSearch> getCalendarBookmarkInfo(GetBookmarkListRequest request);

    List<CalendarBookmarkDetail> getCalendarBookmarkDetail(GetCalendarBookmarkRequest request);

}
