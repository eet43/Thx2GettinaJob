package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.CustomUserDetails;
import KHOneTop.Thx2GettinaJob.auth.JwtProvider;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.user.entity.User;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void SignUp(SignUpRequest request) {
        if(userRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new CustomException(Codeset.ALREADY_NICKNAME, "이미 존재하는 닉네임");
        } else if (!pwIsValid(request.getPassword())) {
            throw new CustomException(Codeset.INVALID_PASSWORD_TYPE, "비밀번호 타입 에러");
        } else {
            User user = userRepository.save(request.toEntity());
            user.encodePassword(passwordEncoder);
        }
    }
    
    public boolean pwIsValid(String password) {
        if(password == null || password.length() < 8) {
            return false;
        }
        return password.matches(".*[^a-zA-Z0-9].*");
    }

    @Override
    public LoginToken login(LoginRequest request) {
        User findUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(Codeset.INVALID_USER, "아이디에 해당하는 유저가 없습니다."));
        if(!passwordEncoder.matches(request.getPassword(), findUser.getPassword())) {
            throw new CustomException(Codeset.INVALID_PASSWORD, "비밀번호가 일치하지 않습니다.");
        }

        return createTokens(findUser);
    }

    private LoginToken createTokens(User findUser) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("email", findUser.getEmail());
        userInfo.put("name", findUser.getName());
        userInfo.put("nickname", findUser.getNickname());

        String accessToken = jwtProvider.generateToken(userInfo);
        String refreshToken = jwtProvider.generateRefreshToken(userInfo);

        return new LoginToken(accessToken, refreshToken);
    }


}
