package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.VerifyCodeRequest;

public interface EmailService {
    void authEmail(SendToEmailRequest request);
    void authTempPw(SendToEmailRequest request);
    boolean verifyCode(VerifyCodeRequest request);
}
