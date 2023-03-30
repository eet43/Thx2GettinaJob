package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.VerifyCodeRequest;

public interface EmailService {
    public void authEmail(SendToEmailRequest request);
    public boolean verifyCode(VerifyCodeRequest request);
}
