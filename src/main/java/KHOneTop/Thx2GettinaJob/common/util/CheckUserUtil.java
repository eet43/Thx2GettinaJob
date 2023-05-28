package KHOneTop.Thx2GettinaJob.common.util;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckUserUtil {
    private final UserRepository userRepository;

    public void checkValidUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(Codeset.INVALID_USER, "해당 유저를 찾을 수 없습니다.");
        }
    }
}
