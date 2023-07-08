package KHOneTop.Thx2GettinaJob.auth.controller;

import KHOneTop.Thx2GettinaJob.auth.dto.LoginRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.RefreshAccessTokenRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.SignUpRequest;
import KHOneTop.Thx2GettinaJob.auth.service.AuthService;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.user.dto.ChangeNicknameRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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

    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = LoginToken.class)))
    @PostMapping("/login")
    public CustomResponse login(@Valid @RequestBody LoginRequest request) {
        LoginToken successToken = authService.login(request);
        return CustomResponse.success(successToken);
    }

    @ApiResponse(responseCode = "200", description = "AccessToken 갱신 성공", content = @Content(schema = @Schema(implementation = LoginToken.class)))
    @PostMapping("/refesh")
    public CustomResponse refreshAccessToken(@RequestBody RefreshAccessTokenRequest request) {
        LoginToken authToken = authService.refreshAccessToken(request);
        return CustomResponse.success(authToken);
    }

    @ApiResponse(responseCode = "200", description = "유저 닉네임 변경 성공", content = @Content(schema = @Schema(implementation = LoginToken.class)))
    @PostMapping("/change/info")
    public CustomResponse changeNickname(@RequestBody ChangeNicknameRequest request) {
        LoginToken authToken = authService.changeNickname(request);
        return CustomResponse.success(authToken);
    }
}
