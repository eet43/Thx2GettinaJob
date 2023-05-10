package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;
import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import KHOneTop.Thx2GettinaJob.exam.entity.PublicExam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import KHOneTop.Thx2GettinaJob.user.entity.User;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{
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
    public BookmarkInfo getBookmarkInfo(GetBookmarkListRequest request) {
        checkValidUserId(request.userId());

        List<String> findExamNames = bookmarkRepository.findExamNamesByUserId(request.userId());
        List<PrivateExamInfo> privateDto = examRepository.findPrivateExamsByExamNames(findExamNames);
        List<PublicExamInfo> publicDto = examRepository.findPublicExamsByExamNames(findExamNames);

        return new BookmarkInfo(privateDto, publicDto);
    }

    @Override
    public List<BookmarkDetail> getBookmarkDetail(GetBookmarkListRequest request) {
        checkValidUserId(request.userId());

        List<String> findExamNames = bookmarkRepository.findExamNamesByUserId(request.userId());
        Optional<List<Exam>> findExams = examRepository.findAllByExamNames(findExamNames);

        return findExams.map(exams -> exams.stream()
                        .map(exam -> {
                            if (exam instanceof PublicExam) {
                                PublicExam publicExam = (PublicExam) exam;
                                return new BookmarkDetail(publicExam.getId(), publicExam.getName(), publicExam.getIssuer(),
                                        publicExam.getUrl(), publicExam.getTurn(), publicExam.getIsPublic(), publicExam.getExamTimeStamp());
                            } else {
                                PrivateExam privateExam = (PrivateExam) exam;
                                return new BookmarkDetail(privateExam.getId(), privateExam.getName(), privateExam.getIssuer(),
                                        privateExam.getUrl(), null, privateExam.getIsPublic(), privateExam.getExamTimeStamp());
                            }
                        })
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<Top5PopBookmark> getTop5PopBookmarks() {
        List<BookmarkCount> findTop5PopBookmarkNames = bookmarkRepository.findTop5PopBookmarkCount();

    }

    private void checkValidUserId(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new CustomException(Codeset.INVALID_USER, "해당 유저를 찾을 수 없습니다.");
        }
    }

    private void checkValidExam(Long examId) {
        if(!examRepository.existsById(examId)) {
            throw new CustomException(Codeset.INVALID_EXAM, "해당 시험을 찾을 수 없습니다.");
        }
    }

    private Long saveAndReturnId(Exam exam) {
        Exam saveExam = examRepository.save(exam);
        return saveExam.getId();
    }
}
