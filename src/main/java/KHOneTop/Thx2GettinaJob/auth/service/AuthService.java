package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.dto.LoginRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.RefeshAccessTokenRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void SignUp(SignUpRequest request);
    LoginToken login(LoginRequest request);
    LoginToken refreshAccessToken(RefeshAccessTokenRequest request);


}
