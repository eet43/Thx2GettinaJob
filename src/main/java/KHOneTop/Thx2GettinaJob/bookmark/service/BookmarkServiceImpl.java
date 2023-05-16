package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;
import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ExamRepository examRepository;

    @Override
    @Transactional
    public void addBookmarkPriExam(AddBookmarkPrivateExamRequest request) {
        checkValidUserId(request.userId());

        PrivateExam newExam = request.toEntity();
        Long examId = saveAndReturnId(newExam);

        Bookmark newBookmark = Bookmark.create(request.userId(), examId);
        bookmarkRepository.save(newBookmark);
    }

    @Override
    @Transactional
    public void addBookmarkPubExam(AddBookmarkPubExamRequest request) {
        checkValidUserId(request.userId());
        checkValidExam(request.examId());

        Bookmark newBookmark = Bookmark.create(request.userId(), request.examId());
        bookmarkRepository.save(newBookmark);
    }

    @Override
    public List<BookmarkInfo> getBookmarkInfo(GetBookmarkListRequest request) {
        checkValidUserId(request.userId());

        List<Long> findExamIds = bookmarkRepository.findExamIdByUserId(request.userId());
        List<Exam> findExams = examRepository.findByIdInFetchJoin(findExamIds);
        List<BookmarkInfo> bookmarkDtos = new ArrayList<>();

        for (Exam exam : findExams) {
            if (Boolean.TRUE.equals(exam.getIsPublic())) {
                boolean flag = false;
                for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
                    LocalDateTime regEndDate = timeStamp.getRegEndDate();
                    LocalDateTime addRegEndDate = timeStamp.getAddRegEndDate();
                    if (regEndDate == null) {
                        bookmarkDtos.add(BookmarkInfo.fromEntity(exam, "상시접수", null));
                        flag = true;
                        break;
                    } else if (regEndDate.isAfter(LocalDateTime.now())) {
                        Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), regEndDate.toLocalDate());
                        bookmarkDtos.add(BookmarkInfo.fromEntity(exam, "정기접수중", day));
                        flag = true;
                        break;
                    } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
                        Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), addRegEndDate.toLocalDate());
                        bookmarkDtos.add(BookmarkInfo.fromEntity(exam, "추가접수중", day));
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, "접수마감", null));
                }
            } else {
                ExamTimeStamp timeStamp = exam.getExamTimeStamp().get(0);
                LocalDateTime regEndDate = timeStamp.getRegEndDate();
                LocalDateTime addRegEndDate = timeStamp.getAddRegEndDate();
                if (regEndDate == null && addRegEndDate == null) {
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, "기간미입력", null));
                } else if (regEndDate != null && regEndDate.isAfter(LocalDateTime.now())) {
                    Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), regEndDate.toLocalDate());
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, "정기접수중", day));
                } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
                    Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), addRegEndDate.toLocalDate());
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, "추가접수중", day));
                } else {
                    bookmarkDtos.add(BookmarkInfo.fromEntity(exam, "접수마감", null));
                }
            }
        }

        return bookmarkDtos;

    }

    @Override
    public List<BookmarkDetailOfTurn> getBookmarkDetailOfTurn(Long examId) {
        Exam exam = examRepository.findByIdFetchJoin(examId).
                orElseThrow(() -> new CustomException(Codeset.INVALID_EXAM, "해당하는 시험이 존재하지 않습니다."));
        List<BookmarkDetailOfTurn> bookmarkDtos = new ArrayList<>();

        for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
            LocalDateTime regStartDate = timeStamp.getRegStartDate();
            LocalDateTime regEndDate = timeStamp.getRegEndDate();
            LocalDateTime addRegEndDate = timeStamp.getAddRegEndDate();
            if (!regStartDate.isAfter(LocalDateTime.now())) {
                bookmarkDtos.add(BookmarkDetailOfTurn.fromEntity(timeStamp, "접수예정", null));
                break;
            } else if (regEndDate.isAfter(LocalDateTime.now())) {
                Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), regEndDate.toLocalDate());
                bookmarkDtos.add(BookmarkDetailOfTurn.fromEntity(timeStamp, "정기접수중", day));
                break;
            } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
                Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), addRegEndDate.toLocalDate());
                bookmarkDtos.add(BookmarkDetailOfTurn.fromEntity(timeStamp, "추가접수중", day));
                break;
            } else {
                bookmarkDtos.add(BookmarkDetailOfTurn.fromEntity(timeStamp, "접수마감", null));
            }
        }

        return bookmarkDtos;
    }

    @Override
    public List<Top5PopBookmark> getTop5PopBookmarks() { //시간이 정해지면, 시간 단위로 카운트 해야함. Top5 북마크 가져오는 쿼리를 수정하면됨.
        List<Top5PopBookmark> result = new ArrayList<>();
        List<BookmarkCount> findTop5PopBookmarks = bookmarkRepository.findTop5PopBookmarkCount(PageRequest.of(0,5));

        for (BookmarkCount findTop5PopBookmark : findTop5PopBookmarks) {
            Exam findExam = examRepository.findByIdFetchJoin(findTop5PopBookmark.getExamId())
                    .orElseThrow(() -> new CustomException(Codeset.INVALID_EXAM, "해당하는 시험이 존재하지 않습니다."));
            boolean flag = false;
            for (ExamTimeStamp timeStamp : findExam.getExamTimeStamp()) {
                LocalDateTime regEndDate = timeStamp.getRegEndDate();
                LocalDateTime addRegEndDate = timeStamp.getAddRegEndDate();
                if (regEndDate == null) {
                    result.add(Top5PopBookmark.fromEntity(findExam, "상시접수", null, findTop5PopBookmark.getCount()));
                    flag = true;
                    break;
                } else if (regEndDate.isAfter(LocalDateTime.now())) {
                    Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), regEndDate.toLocalDate());
                    result.add(Top5PopBookmark.fromEntity(findExam, "정기접수중", day, findTop5PopBookmark.getCount()));
                    flag = true;
                    break;
                } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
                    Long day = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), addRegEndDate.toLocalDate());
                    result.add(Top5PopBookmark.fromEntity(findExam, "추가접수중", day, findTop5PopBookmark.getCount()));
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                result.add(Top5PopBookmark.fromEntity(findExam, "접수마감", null, findTop5PopBookmark.getCount()));
            }
        }
        return result;
    }


    private void checkValidUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(Codeset.INVALID_USER, "해당 유저를 찾을 수 없습니다.");
        }
    }

    private void checkValidExam(Long examId) {
        if (!examRepository.existsById(examId)) {
            throw new CustomException(Codeset.INVALID_EXAM, "해당 시험을 찾을 수 없습니다.");
        }
    }

    private Long saveAndReturnId(Exam exam) {
        Exam saveExam = examRepository.save(exam);
        return saveExam.getId();
    }
}
