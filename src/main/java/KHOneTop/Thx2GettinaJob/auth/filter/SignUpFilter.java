package KHOneTop.Thx2GettinaJob.auth.filter;

import KHOneTop.Thx2GettinaJob.auth.service.AuthService;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SignUpFilter extends UsernamePasswordAuthenticationFilter {
}
