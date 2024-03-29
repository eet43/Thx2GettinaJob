package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.checkDday.CheckExamDday;
import KHOneTop.Thx2GettinaJob.checkDday.dto.ExamDdayInfo;
import KHOneTop.Thx2GettinaJob.checkDday.dto.ExamDdayTimeInfo;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.exam.dto.*;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final BookmarkRepository bookmarkRepository;
    private final CheckUserUtil checkUserUtil;

    private final CheckExamDday checkExamDday;


    @Override
    public List<ExamDdayInfo> getHomeSearchList(GetExamListRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        List<Exam> findExams = examRepository.findAllByIsPublicFetchJoin();
        List<ExamDdayInfo> result = new ArrayList<>();

        for (Exam exam : findExams) {
            result.add(checkExamDday.checkPubExam(exam, request.userId()));

        }

        return result;
    }

    @Override
    public List<ExamDdayInfo> getHomeSearchListNoAuth() {

        List<Exam> findExams = examRepository.findAllByIsPublicFetchJoin();
        List<ExamDdayInfo> result = new ArrayList<>();

        for (Exam exam : findExams) {
            result.add(checkExamDday.checkPubExamNoAuth(exam));
        }

        return result;
    }

    @Override
    public List<ExamInfo> getExamList(GetExamListRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        List<Exam> findExams = examRepository.findPublicOrOwnedExams(request.userId());
        List<ExamInfo> result = new ArrayList<>();

        for (Exam exam : findExams) {
            boolean isBookmark = bookmarkRepository.existsByUserIdAndExamId(request.userId(), exam.getId());
            result.add(ExamInfo.toDto(exam, isBookmark));
        }
        return result;
    }

    @Override
    public ExamDetail getSingleExamDetail(Long examId) {
        Exam findExam = examRepository.findByIdFetchJoin(examId)
                .orElseThrow(() -> new CustomException(Codeset.INVALID_EXAM, "해당 시험이 존재하지 않습니다."));

        return ExamDetail.toDto(findExam, findExam.getExamTimeStamp().get(0));
    }

    //    @Override
//    @Cacheable(
//            value = "ExamDdayTimeInfo",
//            key = "#examId"
//    )
    public List<ExamDdayTimeInfo> getBookmarkDetailOfTurn(Long examId) {
        Exam findExam = examRepository.findByIdFetchJoin(examId).
                orElseThrow(() -> new CustomException(Codeset.INVALID_EXAM, "해당하는 시험이 존재하지 않습니다."));
        List<ExamDdayTimeInfo> result = new ArrayList<>();

        for (ExamTimeStamp timeStamp : findExam.getExamTimeStamp()) { //캐시 필
            result.add(checkExamDday.checkExamTime(timeStamp));
        }

        return result;
    }

    @Override
    @Transactional
    public void modifyPriExam(ModifyPriExamRequest request) {
        PrivateExam findExam = examRepository.findPriExamByIdFetchJoin(request.examId()).
                orElseThrow(() -> new CustomException(Codeset.INVALID_EXAM, "해당하는 시험이 존재하지 않습니다."));

        if (Boolean.TRUE.equals(findExam.getIsPublic())) {
            throw new CustomException(Codeset.INVALID_PRIEXAM, "해당 시험은 직접 추가한 객체가 아닙니다. 개발팀에게 문의해주세요");
        } else {
            findExam.modify(request);
        }
    }


    private void checkExam(Long examId) {
        if (!examRepository.existsById(examId)) {
            throw new CustomException(Codeset.INVALID_EXAM, "해당 시험이 존재하지 않습니다.");
        }
    }
}
