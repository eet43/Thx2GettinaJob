package KHOneTop.Thx2GettinaJob.user.controller;

import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.user.dto.ChangeNicknameRequest;
import KHOneTop.Thx2GettinaJob.user.dto.ChangePasswordRequest;
import KHOneTop.Thx2GettinaJob.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.USER)
public class UserController {

    private final UserService userService;

    @PostMapping("/change/password")
    public CustomResponse changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return CustomResponse.success();
    }
}
