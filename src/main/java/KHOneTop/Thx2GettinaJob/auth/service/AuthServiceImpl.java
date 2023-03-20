package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.dto.LoginRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.response.FilterException;
import KHOneTop.Thx2GettinaJob.user.entity.User;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByEmail(String email) {
        User findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new FilterException(Codeset.INVALID_USER));
        return org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password(findUser.getPassword())
                .authorities("BASIC")
                .build();
    }

    @Override
    @Transactional
    public void SignUp(SignUpRequest request) {
        if(userRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new CustomException(Codeset.ALREADY_NICKNAME, "이미 존재하는 닉네임");
        } else {
            User user = userRepository.save(request.toEntity());
            user.encodePassword(passwordEncoder);
        }
    }

    @Override
    public LoginToken login(LoginRequest request) {
        return null;
    }
}
