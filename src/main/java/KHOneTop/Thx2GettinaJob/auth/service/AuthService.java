package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.dto.LoginRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.RefreshAccessTokenRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
import KHOneTop.Thx2GettinaJob.user.dto.ChangeNicknameRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void SignUp(SignUpRequest request);
    LoginToken login(LoginRequest request);
    LoginToken refreshAccessToken(RefreshAccessTokenRequest request);
    LoginToken changeNickname(ChangeNicknameRequest request);
}
