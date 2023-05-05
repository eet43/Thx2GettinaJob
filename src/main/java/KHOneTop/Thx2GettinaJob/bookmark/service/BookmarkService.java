package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.AddBookmarkPrivateExamRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.AddBookmarkPubExamRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkInfo;
import KHOneTop.Thx2GettinaJob.bookmark.dto.GetBookmarkListRequest;

import java.util.List;

public interface BookmarkService {
    void addBookmarkPriExam(AddBookmarkPrivateExamRequest request);
    void addBookmarkPubExam(AddBookmarkPubExamRequest request);
    BookmarkInfo getBookmarkInfo(GetBookmarkListRequest request);
}
