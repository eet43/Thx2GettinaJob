package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;
import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.common.checkDday.CheckExamDday;
import KHOneTop.Thx2GettinaJob.common.checkDday.dto.ExamDdayInfo;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.exam.dto.NearExamInfo;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public non-sealed class BookmarkServiceImpl implements BookmarkService {
    private final CheckUserUtil checkUserUtil;
    private final BookmarkRepository bookmarkRepository;
    private final ExamRepository examRepository;

    private final CheckExamDday checkExamDday;

    @Override
    @Transactional
    public void addBookmarkPriExam(AddBookmarkPrivateExamRequest request) {
        checkUserUtil.checkValidUserId(request.userId());
        if(examRepository.existsByNameAndUserId(request.name(), request.userId()) == 1) {
            throw new CustomException(Codeset.ALREADY_EXAM_NAME, "이미 존재하는 시험 이름입니다.");
        }

        PrivateExam newExam = request.toEntity();
        Long examId = saveAndReturnId(newExam);

        Bookmark newBookmark = Bookmark.create(request.userId(), examId, false);
        bookmarkRepository.save(newBookmark);
    }

    @Override
    @Transactional
    public void addBookmarkPubExam(AddBookmarkPubExamRequest request) {
        checkUserUtil.checkValidUserId(request.userId());
        checkValidBookmark(request.userId(), request.examId());

        Bookmark newBookmark = Bookmark.create(request.userId(), request.examId(), true);
        bookmarkRepository.save(newBookmark);
    }

    @Override
    @Transactional
    public void deleteBookmarkPubExam(DeleteBookmarkPubExamRequest request) {
        checkUserUtil.checkValidUserId(request.userId());
        if (!isPubExam(request.examId())) {
            examRepository.deleteById(request.examId());
        }
        Bookmark findBookmark = bookmarkRepository.findByUserIdAndExamId(request.userId(), request.examId());
        bookmarkRepository.delete(findBookmark);
    }

    @Override
    public List<BookmarkSummary> getBookmarkSummary(GetBookmarkListRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        List<Long> findExamIds = bookmarkRepository.findExamIdByUserId(request.userId());
        List<Exam> findExams = examRepository.findAllByIdIn(findExamIds);
        List<BookmarkSummary> result = new ArrayList<>();

        for (Exam exam : findExams) {
            result.add(BookmarkSummary.fromEntity(exam));
        }
        return result;
    }

    @Override
    public List<BookmarkInfo> getBookmarkInfo(GetBookmarkListRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        List<Long> findExamIds = bookmarkRepository.findExamIdByUserId(request.userId()); //즐겨찾기 exam ID 추출
        List<Exam> findExams = examRepository.findByIdInFetchJoin(findExamIds); //즐겨찾기된 exam 추출
        List<BookmarkInfo> bookmarkDtos = new ArrayList<>();

        for (Exam exam : findExams) {
            if (Boolean.TRUE.equals(exam.getIsPublic())) {
                boolean flag = false;
                boolean isTurn = exam.getExamTimeStamp().size() != 1;
                for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
                    LocalDateTime regEndDate = timeStamp.getRegEndDate();
                    LocalDateTime addRegEndDate = timeStamp.getAddRegEndDate();
                    if (regEndDate == null) {
                        bookmarkDtos.add(BookmarkInfo.fromEntity(exam, isTurn, "상시접수", null));
                        flag = true;
                        break;
                    } else if (regEndDate.isAfter(LocalDateTime.now())) {
                        Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), regEndDate.toLocalDate());
                        bookmarkDtos.add(BookmarkInfo.fromEntity(exam, isTurn, "정기접수중", day));
                        flag = true;
                        break;
                    } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
                        Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), addRegEndDate.toLocalDate());
                        bookmarkDtos.add(BookmarkInfo.fromEntity(exam, isTurn, "추가접수중", day));
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, isTurn, "접수마감", null));
                }
            } else {
                ExamTimeStamp timeStamp = exam.getExamTimeStamp().get(0);
                LocalDateTime regEndDate = timeStamp.getRegEndDate();
                LocalDateTime addRegEndDate = timeStamp.getAddRegEndDate();
                if (regEndDate == null && addRegEndDate == null) {
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, false, "기간미입력", null));
                } else if (regEndDate != null && regEndDate.isAfter(LocalDateTime.now())) {
                    Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), regEndDate.toLocalDate());
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, false, "정기접수중", day));
                } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
                    Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), addRegEndDate.toLocalDate());
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, false, "추가접수중", day));
                } else {
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, false, "접수마감", null));
                }
            }
        }

        return bookmarkDtos;

    }

    @Override
    public List<ExamDdayInfo> getTop5PopBookmarksNoAuth() { //시간이 정해지면, 시간 단위로 카운트 해야함. Top5 북마크 가져오는 쿼리를 수정하면됨.
        List<BookmarkCount> findTop5PopBookmarks = bookmarkRepository.findTop5PopBookmarkCount(PageRequest.of(0, 5));
        List<ExamDdayInfo> result = new ArrayList<>();

        for (BookmarkCount findTop5PopBookmark : findTop5PopBookmarks) {
            Exam findExam = examRepository.findByIdFetchJoin(findTop5PopBookmark.getExamId())
                    .orElseThrow(() -> new CustomException(Codeset.INVALID_EXAM, "해당하는 시험이 존재하지 않습니다."));
            ExamDdayInfo examInfo = checkExamDday.checkWithNoAuth(findExam);
            result.add(examInfo);
        }
        return result;
    }

    @Override
    public List<ExamDdayInfo> getTop5PopBookmarks(GetBookmarkListRequest request) { //시간이 정해지면, 시간 단위로 카운트 해야함. Top5 북마크 가져오는 쿼리를 수정하면됨.
        List<BookmarkCount> findTop5PopBookmarks = bookmarkRepository.findTop5PopBookmarkCount(PageRequest.of(0, 5));
        List<ExamDdayInfo> result = new ArrayList<>();


        for (BookmarkCount findTop5PopBookmark : findTop5PopBookmarks) {
            Exam findExam = examRepository.findByIdFetchJoin(findTop5PopBookmark.getExamId())
                    .orElseThrow(() -> new CustomException(Codeset.INVALID_EXAM, "해당하는 시험이 존재하지 않습니다."));

            ExamDdayInfo examInfo = checkExamDday.checkPubExam(findExam, request.userId());
            result.add(examInfo);
        }
        return result;
    }

    @Override
    public List<Top3NearBookmark> getTop3NearBookmarks(GetTop3NearBookmarkRequest request) {
        if(request.userId() == null) {
            return Collections.emptyList();
        }

        List<Top3NearBookmark> result = new ArrayList<>();
        List<Long> findExamIds = bookmarkRepository.findExamIdByUserId(request.userId());
        List<NearExamInfo> findExams = examRepository.findTop3ByOrderByRegEndDateAsc(findExamIds, PageRequest.of(0, 3)); //fetch 조인으로 바꿔야함

        for (NearExamInfo exam : findExams) {
            LocalDateTime regEndDate = exam.getRegEndDate();
            LocalDateTime addRegEndDate = exam.getAddRegEndDate();
            if (regEndDate != null && regEndDate.isAfter(LocalDateTime.now())) {
                Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), regEndDate.toLocalDate());
                result.add(Top3NearBookmark.toDto(exam, "정기접수중", day));
            } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
                Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), addRegEndDate.toLocalDate());
                result.add(Top3NearBookmark.toDto(exam, "추가접수중", day));
            } else {
                throw new CustomException(Codeset.INVALID_NEAR_EXAM, "얼마남지 않은 자격증 조회에 문제가 발생했습니다.");
            }
        }

        result.sort(Comparator.comparingLong(Top3NearBookmark::getDay));
        return result;
    }

    private void checkValidBookmark(Long userId, Long examId) {
        if (bookmarkRepository.existsByUserIdAndExamId(userId, examId)) {
            throw new CustomException(Codeset.ALREADY_BOOKMARK, "이미 즐겨찾기에 등록된 시험입니다.");
        }
    }

    private boolean isPubExam(Long examId) {
        Boolean isPub = examRepository.isPublicExam(examId);
        if (isPub == null) {
            throw new CustomException(Codeset.INVALID_EXAM, "해당 시험을 찾을 수 없습니다.");
        } else {
            return isPub;
        }
    }

    private Long saveAndReturnId(Exam exam) {
        Exam saveExam = examRepository.save(exam);
        return saveExam.getId();
    }
}
