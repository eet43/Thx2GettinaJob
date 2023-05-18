package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.JwtProvider;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.user.entity.User;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtProvider jwtProvider;


    final String email = "test@gmail.com";
    final String validPassword = "1q2w3e4r!";
    final String invalidPassword1 = "1q2e3@";
    final String invalidPassword2 = "1q2e31251";
    final String encodePassword = "encodePassword";
    final String name = "김달순";
    final String nickname = "김덕자";


    @Test
    @DisplayName("이미 있는 닉네임일 때")
    void checknickNameValid() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest(email, validPassword, name, nickname);
        given(userRepository.findByNickname(nickname)).willReturn(Optional.of(new User()));

        //when & then
        assertThatThrownBy(() -> authService.SignUp(signUpRequest))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("code", Codeset.ALREADY_NICKNAME);

    }

    @Test
    @DisplayName("유효하지 않은 로그인 검증 테스트")
    void InvalidLogin() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest(email, invalidPassword1);
        User user = User.builder()
                .email(email)
                .password(encodePassword)
                .build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
        given(passwordEncoder.matches(invalidPassword1, encodePassword)).willReturn(false);

        //when & then
        assertThatThrownBy(() -> authService.login(loginRequest))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("code", Codeset.INVALID_PASSWORD);
    }

    @Test
    @DisplayName("유효한 로그인 검증 테스트")
    void validLogin() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest(email, validPassword);
        User user = User.builder()
                .email(email)
                .password(encodePassword)
                .name(name)
                .nickname(nickname)
                .build();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", email);
        userInfo.put("name", name);
        userInfo.put("nickname", nickname);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
        given(passwordEncoder.matches(validPassword, encodePassword)).willReturn(true);
        given(jwtProvider.generateToken(userInfo)).willReturn("accessToken");
        given(jwtProvider.generateRefreshToken(userInfo)).willReturn("refreshToken");

        //when
        LoginToken result = authService.login(loginRequest);

        //then
        assertThat(result).isInstanceOf(LoginToken.class);
        assertThat(result.getAccessToken()).isEqualTo("accessToken");
    }

}