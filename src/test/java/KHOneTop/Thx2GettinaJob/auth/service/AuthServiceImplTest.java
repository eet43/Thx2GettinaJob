package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
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

    final String email = "test@gmail.com";
    final String validPassword = "1q2w3e4r!";
    final String invalidPassword1 = "1q2e3@";
    final String invalidPassword2 = "1q2e31251";
    final String name = "김달순";
    final String nickname = "김덕자";


    @Test
    @DisplayName("이미 있는 닉네임일 때")
    void checknickNameValid() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest(email, validPassword, name, nickname);
        given(userRepository.findByNickname(nickname)).willReturn(Optional.of(new User()));

        //when & then
        assertThatThrownBy(()-> authService.SignUp(signUpRequest)).isInstanceOf(CustomException.class);

    }

    @Test
    @DisplayName("비밀번호 검증 테스트")
    void checkPasswordValid() throws Exception {
        assertThat(authService.pwIsValid(invalidPassword2)).isFalse();
    }

}