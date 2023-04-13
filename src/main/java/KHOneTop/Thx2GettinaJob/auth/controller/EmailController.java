package KHOneTop.Thx2GettinaJob.auth.controller;

import KHOneTop.Thx2GettinaJob.auth.dto.VerifyCodeRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.auth.service.EmailService;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
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
@RequestMapping(EndPoint.EMAIL)
public class EmailController {

    private final EmailService emailService;
    @PostMapping("")
    public CustomResponse authEmail(@RequestBody @Valid SendToEmailRequest request) {
        emailService.authEmail(request);
        return CustomResponse.success();
    }

    @PostMapping("/password")
    public CustomResponse authTempPw(@RequestBody @Valid SendToEmailRequest request) {
        emailService.authTempPw(request);
        return CustomResponse.success();
    }

    @PostMapping("/certification")
    public CustomResponse verifyCode(@RequestBody @Valid VerifyCodeRequest request) {
        if(emailService.verifyCode(request)) {
            return CustomResponse.success();
        } else {
            return CustomResponse.fail(Codeset.VERIFY_EMAIL_FAIL);
        }
    }
}
