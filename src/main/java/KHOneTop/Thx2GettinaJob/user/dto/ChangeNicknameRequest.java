package KHOneTop.Thx2GettinaJob.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeNicknameRequest {
    private String email;
    private String nickname;
}
