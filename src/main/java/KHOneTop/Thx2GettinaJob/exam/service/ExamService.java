package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkDetailOfTurn;
import KHOneTop.Thx2GettinaJob.exam.dto.*;

import java.util.List;

public interface ExamService {
    List<HomeSearch> getHomeSearchList(GetExamListRequest request);
    List<ExamInfo> getExamList(GetExamListRequest request);
    ExamDetail getSingleExamDetail(Long examId);
    List<BookmarkDetailOfTurn> getBookmarkDetailOfTurn(Long examId);
    void modifyPriExam(ModifyPriExamRequest request);
}
