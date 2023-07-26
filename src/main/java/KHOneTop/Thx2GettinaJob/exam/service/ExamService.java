package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.checkDday.dto.ExamDdayInfo;
import KHOneTop.Thx2GettinaJob.checkDday.dto.ExamDdayTimeInfo;
import KHOneTop.Thx2GettinaJob.exam.dto.*;

import java.util.List;

public interface ExamService {
    List<ExamDdayInfo> getHomeSearchList(GetExamListRequest request);
    List<ExamInfo> getExamList(GetExamListRequest request);
    ExamDetail getSingleExamDetail(Long examId);
    List<ExamDdayTimeInfo> getBookmarkDetailOfTurn(Long examId);
    void modifyPriExam(ModifyPriExamRequest request);
}
