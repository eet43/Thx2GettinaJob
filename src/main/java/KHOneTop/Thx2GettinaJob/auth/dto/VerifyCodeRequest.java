package KHOneTop.Thx2GettinaJob.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerifyCodeRequest {
    @Email
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank
    private String code;
}