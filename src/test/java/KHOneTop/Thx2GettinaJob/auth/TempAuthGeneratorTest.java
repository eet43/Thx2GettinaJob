package KHOneTop.Thx2GettinaJob.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TempAuthGeneratorTest {

    @Test
    @DisplayName("인증코드 사이즈 테스트")
    void checkNumberSize() throws Exception {
        //given
        final TempAuthGenerator tempAuthGenerator = new TempAuthGenerator();

        //when
        String tempNum = tempAuthGenerator.generateRandomNum();

        //then
        assertThat(tempNum.length()).isEqualTo(6);
    }

    @Test
    @DisplayName("임시 비밀번호 사이즈 테스트")
    void checkTempPwSize() throws Exception {
        //given
        final TempAuthGenerator tempAuthGenerator = new TempAuthGenerator();

        //when
        String tempNum = tempAuthGenerator.generateTempPw();

        //then
        assertThat(tempNum.length()).isEqualTo(10);
    }
}