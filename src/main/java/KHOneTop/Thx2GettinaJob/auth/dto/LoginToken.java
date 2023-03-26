package KHOneTop.Thx2GettinaJob.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class LoginToken {
    private String accessToken;
    private String refreshToken;
}
