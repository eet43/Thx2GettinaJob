package KHOneTop.Thx2GettinaJob.calendar.service;

import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.calendar.dto.*;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService{
    private final ExamRepository examRepository;
    private final BookmarkRepository bookmarkRepository;
    private final CheckUserUtil checkUserUtil;
    @Override
    public List<CalendarBookmarkSearch> getCalendarBookmarkInfo(GetCalendarSearchRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        List<Long> findExamIds = bookmarkRepository.findExamIdByUserId(request.userId());
        List<Exam> findExams = examRepository.findExamsWithAnySchedule(findExamIds);
        List<CalendarBookmarkSearch> result = new ArrayList<>();

        for (Exam exam : findExams) {
            result.add(CalendarBookmarkSearch.toDto(exam));
        }
        return result;
    }

    @Override
    public List<CalendarBookmarkDetail> getCalendarBookmarkDetail(GetCalendarBookmarkRequest request) {
        List<CalendarBookmarkDetail> result = new ArrayList<>();

        for (GetCalenderDetailRequest detailRequest : request.exams()) {
            checkValidExam(detailRequest.examId());
            Optional<Exam> findExam = examRepository.findExamByExamTimeStampFields(request.startDate(), request.endDate(), detailRequest);

            if (findExam.isPresent()) {
                Exam data = findExam.get();
                result.add(CalendarBookmarkDetail.toDto(data));
            }
        }

        return result;
    }

    private void checkValidExam(Long examId) {
        if (!examRepository.existsById(examId)) {
            throw new CustomException(Codeset.INVALID_EXAM, "해당 시험을 찾을 수 없습니다.");
        }
    }
}
