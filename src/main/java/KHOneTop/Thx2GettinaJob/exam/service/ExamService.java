package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkDetailOfTurn;
import KHOneTop.Thx2GettinaJob.exam.dto.ExamDetail;
import KHOneTop.Thx2GettinaJob.exam.dto.ExamInfo;
import KHOneTop.Thx2GettinaJob.exam.dto.GetExamDetailRequest;
import KHOneTop.Thx2GettinaJob.exam.dto.GetExamListRequest;

import java.util.List;

public interface ExamService {
    List<ExamInfo> getExamList(GetExamListRequest request);
    ExamDetail getExamDetail(GetExamDetailRequest request);

    List<BookmarkDetailOfTurn> getBookmarkDetailOfTurn(Long examId);
}
