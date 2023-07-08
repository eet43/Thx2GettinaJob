package KHOneTop.Thx2GettinaJob.user.controller;

import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.user.dto.DeleteUserRequest;
import KHOneTop.Thx2GettinaJob.user.service.SecessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.USER)
public class SecessionController {
    private final SecessionService secessionService;

    @DeleteMapping("")
    public CustomResponse deleteUser(@RequestBody DeleteUserRequest request) {
        secessionService.deleteUser(request);
        return CustomResponse.success();
    }
}
