package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;
import KHOneTop.Thx2GettinaJob.common.checkDday.dto.ExamDdayInfo;

import java.util.List;

public sealed interface BookmarkService
        permits BookmarkServiceImpl {

    void addBookmarkPriExam(AddBookmarkPrivateExamRequest request);

    void addBookmarkPubExam(AddBookmarkPubExamRequest request);

    void deleteBookmarkPubExam(DeleteBookmarkPubExamRequest request);

    List<BookmarkSummary> getBookmarkSummary(GetBookmarkListRequest request);

    List<BookmarkInfo> getBookmarkInfo(GetBookmarkListRequest request);

    List<ExamDdayInfo> getTop5PopBookmarksNoAuth();

    List<ExamDdayInfo> getTop5PopBookmarks(GetBookmarkListRequest request);


    List<Top3NearBookmark> getTop3NearBookmarks(GetTop3NearBookmarkRequest request);

}
