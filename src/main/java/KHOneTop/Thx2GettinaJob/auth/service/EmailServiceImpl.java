package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.VerifyCodeRequest;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;

    @Override
    @Transactional
    public void authEmail(SendToEmailRequest request) {
        sendToEmailNumber(request.getEmail(), createRandomNumber());
    }

    private String createRandomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(888888) + 111111);
    }

    private void sendToEmailNumber(String email, String authKey) {
        String subject = "회원가입 이메일 인증 코드입니다.";
        String text = "";
        text+="<div>";
        text+="CODE: <strong>";
        text+=authKey;
        text+="</strong></div>";

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text, true); //포함된 텍스트가 HTML이라는 의미로 true.
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new CustomException(Codeset.BAD_REQUEST, e.getMessage());
        }

        redisUtil.setDataExpire(email, authKey, 60*5L);
    }

    @Override
    public boolean verifyCode(VerifyCodeRequest request) {
        String findCodeByEmail = redisUtil.getData(request.getEmail());
        return request.getCode().equals(findCodeByEmail);
    }
}
