package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.dto.LoginRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    public void SignUp(SignUpRequest request);

    public LoginToken login(LoginRequest request);

}
