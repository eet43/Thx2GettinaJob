package KHOneTop.Thx2GettinaJob.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class UserPasswordUtilTest {

    final String invalidPassword1 = "1q2e3@";
    final String invalidPassword2 = "1q2e31251";

    @Test
    @DisplayName("비밀번호 검증 테스트")
    void checkPasswordValid() throws Exception {
        //when & then
        assertThat(UserPasswordUtil.pwIsValid(invalidPassword2)).isFalse();
    }

}