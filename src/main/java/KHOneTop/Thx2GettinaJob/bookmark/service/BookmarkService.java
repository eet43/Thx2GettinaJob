package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.AddBookmarkPrivateExamRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.AddBookmarkPubExamRequest;

public interface BookmarkService {
    void addBookmarkPriExam(AddBookmarkPrivateExamRequest request);
    void addBookmarkPubExam(AddBookmarkPubExamRequest request);
}
