package KHOneTop.Thx2GettinaJob.auth;

import java.security.SecureRandom;
import java.util.Random;

public class TempAuthGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int NUMBER_LENGTH = 6;
    private static final int PASSWORD_LENGTH = 10;
    private Random random = new SecureRandom();

    public String generateRandomNum() {
        StringBuilder sb = new StringBuilder(NUMBER_LENGTH);
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public String generateTempPw() {
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}