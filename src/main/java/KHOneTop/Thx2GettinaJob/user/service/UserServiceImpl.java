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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void changeNickname(ChangeNicknameRequest request) {
        if(checkNickname(request.nickname())) {
            User findUser = userRepository.findById(request.userId())
                    .orElseThrow(() -> new CustomException(Codeset.INVALID_USER, "해당 아이디의 사용자를 찾을 수 없습니다."));
            findUser.changeInfo(request.nickname());
        } else {
            throw new CustomException(Codeset.ALREADY_NICKNAME, "이미 존재하는 닉네임입니다.");
        }
    }

    private boolean checkNickname(String nickname) {
        return userRepository.findByNickname(nickname).isEmpty();
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        User findUser = checkEmail(request.email());
        if(passwordEncoder.matches(request.oldPassword(), findUser.getPassword())) {
            if(UserPasswordUtil.pwIsValid(request.newPassword())) {
                findUser.changePassword(passwordEncoder, request.newPassword());
            } else {
                throw new CustomException(Codeset.INVALID_PASSWORD_TYPE, "비밀번호 규격이 맞지 않습니다.");
            }
        } else {
            throw new CustomException(Codeset.INVALID_PASSWORD, "비밀번호가 일치하지 않습니다");
        }
    }

    private User checkEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(Codeset.INVALID_USER, "해당 이메일의 사용자를 찾을 수 없습니다."));
    }

}
