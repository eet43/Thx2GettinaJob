package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;

import java.util.List;

public sealed interface BookmarkService
        permits BookmarkServiceImpl {

    void addBookmarkPriExam(AddBookmarkPrivateExamRequest request);

    void addBookmarkPubExam(AddBookmarkPubExamRequest request);

    void deleteBookmarkPubExam(DeleteBookmarkPubExamRequest request);

    List<BookmarkSummary> getBookmarkSummary(GetBookmarkListRequest request);

    List<BookmarkInfo> getBookmarkInfo(GetBookmarkListRequest request);

    List<Top5PopBookmark> getTop5PopBookmarksNoAuth();

    List<Top5PopBookmark> getTop5PopBookmarks(GetBookmarkListRequest request);


    List<Top3NearBookmark> getTop3NearBookmarks(GetTop3NearBookmarkRequest request);

}
