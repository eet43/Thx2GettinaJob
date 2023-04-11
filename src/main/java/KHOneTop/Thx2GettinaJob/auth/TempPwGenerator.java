package KHOneTop.Thx2GettinaJob.auth;

import java.security.SecureRandom;
import java.util.Random;

public class TempPwGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@";
    private static final int PASSWORD_LENGTH = 10;
    private Random random = new SecureRandom();

    public String generateTempPassword() {
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}