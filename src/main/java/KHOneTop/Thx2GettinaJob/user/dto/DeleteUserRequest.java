package KHOneTop.Thx2GettinaJob.user.dto;

import KHOneTop.Thx2GettinaJob.user.entity.Reason;

public record DeleteUserRequest(
        Long userId,
        Reason reason
) {
}
