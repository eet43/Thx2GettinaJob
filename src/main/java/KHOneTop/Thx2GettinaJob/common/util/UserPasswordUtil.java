package KHOneTop.Thx2GettinaJob.common.util;

import org.springframework.stereotype.Component;

@Component
public class UserPasswordUtil {
    public static boolean pwIsValid(String password) {
        if(password == null || password.length() < 8) {
            return false;
        }
        return password.matches(".*[^a-zA-Z0-9].*");
    }
}
