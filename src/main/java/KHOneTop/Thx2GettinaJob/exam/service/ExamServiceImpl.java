package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.exam.dto.ExamDetail;
import KHOneTop.Thx2GettinaJob.exam.dto.ExamInfo;
import KHOneTop.Thx2GettinaJob.exam.dto.GetExamDetailRequest;
import KHOneTop.Thx2GettinaJob.exam.dto.GetExamListRequest;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService{

    private final ExamRepository examRepository;
    private final BookmarkRepository bookmarkRepository;
    private final CheckUserUtil checkUserUtil;

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
    public ExamDetail getExamDetail(GetExamDetailRequest request) {
        Exam findExam = examRepository.findByIdFetchJoin(request.examId())
                .orElseThrow(() -> new CustomException(Codeset.INVALID_EXAM, "해당 시험이 존재하지 않습니다."));

        return ExamDetail.toDto(findExam, findExam.getExamTimeStamp().get(0));
    }

    private void checkExam(Long examId) {
        if(!examRepository.existsById(examId)) {
            throw new CustomException(Codeset.INVALID_EXAM, "해당 시험이 존재하지 않습니다.");
        }
    }
}
