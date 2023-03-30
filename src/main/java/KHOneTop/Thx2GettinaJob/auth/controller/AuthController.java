package KHOneTop.Thx2GettinaJob.auth.controller;

import KHOneTop.Thx2GettinaJob.auth.dto.LoginRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
import KHOneTop.Thx2GettinaJob.auth.service.AuthService;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public CustomResponse signUp(@Valid @RequestBody SignUpRequest request) {
        authService.SignUp(request);
        return CustomResponse.success();
    }

    @PostMapping("login")
    public CustomResponse login(@Valid @RequestBody LoginRequest request) {
        LoginToken SuccessToken = authService.login(request);
        return CustomResponse.success(SuccessToken);
    }

}
