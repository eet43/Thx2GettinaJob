package KHOneTop.Thx2GettinaJob.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public record ChangePasswordRequest(
        String email,
        String oldPassword,
        String newPassword
) {
}
