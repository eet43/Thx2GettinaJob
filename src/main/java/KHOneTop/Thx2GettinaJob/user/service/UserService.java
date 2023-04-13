package KHOneTop.Thx2GettinaJob.user.service;

import KHOneTop.Thx2GettinaJob.user.dto.ChangeNicknameRequest;
import KHOneTop.Thx2GettinaJob.user.dto.ChangePasswordRequest;

public interface UserService {
    void changeNickname(ChangeNicknameRequest request);
    void changePassword(ChangePasswordRequest request);
}
