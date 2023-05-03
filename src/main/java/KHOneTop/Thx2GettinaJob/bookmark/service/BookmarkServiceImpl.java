package KHOneTop.Thx2GettinaJob.bookmark.service;

import KHOneTop.Thx2GettinaJob.bookmark.dto.AddBookmarkPrivateExamRequest;
import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import KHOneTop.Thx2GettinaJob.user.entity.User;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        examRepository.save(newExam);

        Bookmark newBookmark = Bookmark.builder()
                .userId(request.userId())
                .examName(request.name())
                .build();
        bookmarkRepository.save(newBookmark);
    }

    private void checkValidUserId(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new CustomException(Codeset.INVALID_USER, "해당 유저를 찾을 수 없습니다.");
        }
    }
}
