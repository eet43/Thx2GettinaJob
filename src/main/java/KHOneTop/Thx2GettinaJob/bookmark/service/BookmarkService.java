package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;

import java.util.List;

public interface BookmarkService {
    void addBookmarkPriExam(AddBookmarkPrivateExamRequest request);

    void addBookmarkPubExam(AddBookmarkPubExamRequest request);

    BookmarkInfo getBookmarkInfo(GetBookmarkListRequest request);

    List<BookmarkDetail> getBookmarkDetail(GetBookmarkListRequest request);
    List<Top5PopBookmark> getTop5PopBookmarks();
}
