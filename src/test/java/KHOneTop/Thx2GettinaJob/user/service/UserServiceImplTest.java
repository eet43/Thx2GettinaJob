package KHOneTop.Thx2GettinaJob.user.service;

import KHOneTop.Thx2GettinaJob.auth.JwtProvider;
import KHOneTop.Thx2GettinaJob.auth.service.AuthServiceImpl;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.user.dto.ChangeNicknameRequest;
import KHOneTop.Thx2GettinaJob.user.dto.ChangePasswordRequest;
import KHOneTop.Thx2GettinaJob.user.entity.User;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;


    final String email = "test@gmail.com";
    final String validPassword = "1q2w3e4r!";
    final String invalidPassword1 = "1q2e3@";
    final String invalidPassword2 = "1q2e31251";
    final String encodePassword = "encodePassword";
    final String name = "김달순";
    final String nickname = "김덕자";
    final String changeNickname = "김달자";
    @Test
    void ChangeNicknameByAlreadyNickname() throws Exception {
        //given
        ChangeNicknameRequest request = new ChangeNicknameRequest(email, name, nickname);
        User user = User.builder().build();
        given(userRepository.findByNickname(nickname)).willReturn(Optional.of(user));

        //when & then
        assertThatThrownBy(() -> userService.changeNickname(request))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("code", Codeset.ALREADY_NICKNAME);
    }

    @Test
    void validChangeNickname() throws Exception {
        //given
        ChangeNicknameRequest request = new ChangeNicknameRequest(email, name, changeNickname);
        User user = User.builder()
                .email(email)
                .password(encodePassword)
                .name(name)
                .nickname(nickname)
                .build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
        given(userRepository.findByNickname(changeNickname)).willReturn(Optional.empty());

        //when
        userService.changeNickname(request);

        // then
        assertThat(user.getNickname()).isEqualTo(changeNickname);
    }

    @Test
    void changePwByValidPw() throws Exception {
        //given
        ChangePasswordRequest request = new ChangePasswordRequest(email, name, validPassword);
        User user = User.builder()
                .email(email)
                .password(invalidPassword1)
                .name(name)
                .nickname(nickname)
                .build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
        given(passwordEncoder.encode(validPassword)).willReturn(encodePassword);

        //when
        userService.changePassword(request);

        //then
        assertThat(user.getPassword()).isEqualTo(encodePassword);
    }
}