package KHOneTop.Thx2GettinaJob.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record ChangeNicknameRequest(
        String email,
        String name,
        String nickname
) {
}
