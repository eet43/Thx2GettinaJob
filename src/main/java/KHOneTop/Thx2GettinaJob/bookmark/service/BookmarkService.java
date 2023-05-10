package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;

import java.util.List;

public interface BookmarkService {
    void addBookmarkPriExam(AddBookmarkPrivateExamRequest request);

    void addBookmarkPubExam(AddBookmarkPubExamRequest request);

    List<BookmarkInfo> getBookmarkInfo(GetBookmarkListRequest request);

    List<BookmarkDetailOfTurn> getBookmarkDetailOfTurn(Long examId);

    List<Top5PopBookmark> getTop5PopBookmarks();
}
