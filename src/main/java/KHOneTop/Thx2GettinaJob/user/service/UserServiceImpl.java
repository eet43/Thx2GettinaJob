package KHOneTop.Thx2GettinaJob.user.service;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.UserPasswordUtil;
import KHOneTop.Thx2GettinaJob.user.dto.ChangeNicknameRequest;
import KHOneTop.Thx2GettinaJob.user.dto.ChangePasswordRequest;
import KHOneTop.Thx2GettinaJob.user.entity.User;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void changeNickname(ChangeNicknameRequest request) {
        if(checkNickname(request.getNickname())) {
            User findUser = checkEmail(request.getEmail());
            findUser.changeNickname(request.getNickname());
        } else {
            throw new CustomException(Codeset.ALREADY_NICKNAME, "이미 존재하는 닉네임입니다.");
        }
    }

    private boolean checkNickname(String nickname) {
        return userRepository.findByNickname(nickname).isEmpty();
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        User findUser = checkEmail(request.getEmail());
        if(UserPasswordUtil.pwIsValid(request.getPassword())) {
            findUser.changePassword(passwordEncoder, request.getPassword());
        } else {
            throw new CustomException(Codeset.INVALID_PASSWORD_TYPE, "비밀번호 타입이 맞지 않습니다.");
        }
    }

    private User checkEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(Codeset.INVALID_USER, "해당 이메일의 사용자를 찾을 수 없습니다."));
    }
}
