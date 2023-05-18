package KHOneTop.Thx2GettinaJob.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
@Schema(description = "유저 토큰값")
public class LoginToken {
    @Schema(description = "accessToken", nullable = false)
    private String accessToken;
    @Schema(description = "refreshToken", nullable = false)
    private String refreshToken;
}
