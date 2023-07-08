package KHOneTop.Thx2GettinaJob.user.service;

import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.bookmark.service.BookmarkService;
import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import KHOneTop.Thx2GettinaJob.exam.service.ExamService;
import KHOneTop.Thx2GettinaJob.score.repository.ScoreRepository;
import KHOneTop.Thx2GettinaJob.user.dto.DeleteUserRequest;
import KHOneTop.Thx2GettinaJob.user.entity.Secession;
import KHOneTop.Thx2GettinaJob.user.repository.SecessionRepository;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecessionServiceImpl implements SecessionService{

    private final UserRepository userRepository;
    private final ExamRepository examRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ScoreRepository scoreRepository;
    private final SecessionRepository secessionRepository;
    private final CheckUserUtil checkUserUtil;

    @Override
    @Transactional
    public void deleteUser(DeleteUserRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        Secession secession = Secession.builder()
                .reason(request.reason())
                .build();
        secessionRepository.save(secession);

        deleteInfo(request.userId());
    }

    private void deleteInfo(Long userId) {
        deleteUser(userId);
        deleteExam(userId);
        deleteBookmark(userId);
        deleteScore(userId);
    }

    private void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private void deleteExam(Long userId) {
        examRepository.deleteByUserId(userId);
    }

    private void deleteBookmark(Long userId) {
        bookmarkRepository.deleteByUserId(userId);
    }

    private void deleteScore(Long userId) {
        scoreRepository.deleteByUserId(userId);
    }
}
