package KHOneTop.Thx2GettinaJob.user.controller;

import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.user.dto.ChangeNicknameRequest;
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

    private UserService userService;

    @PostMapping("/change/nickname")
    public CustomResponse changeNickname(@RequestBody ChangeNicknameRequest request) {
        userService.changeNickname(request);
        return CustomResponse.success();
    }
}
