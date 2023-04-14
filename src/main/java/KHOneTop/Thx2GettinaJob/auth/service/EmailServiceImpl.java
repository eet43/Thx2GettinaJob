package KHOneTop.Thx2GettinaJob.auth.service;

import KHOneTop.Thx2GettinaJob.auth.TempAuthGenerator;
import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.auth.dto.VerifyCodeRequest;
import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.RedisUtil;
import KHOneTop.Thx2GettinaJob.user.entity.User;
import KHOneTop.Thx2GettinaJob.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;
    private final TempAuthGenerator tempPwGenerator;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void authEmail(SendToEmailRequest request) {
        String subject = "회원가입 이메일 인증 코드입니다.";
        sendToEmail(subject, request.getEmail(), tempPwGenerator.generateRandomNum());
    }

    @Override
    @Transactional
    public void authTempPw(SendToEmailRequest request) {
        User findUser = checkEmail(request.getEmail());
        String tempPw = tempPwGenerator.generateTempPw();

        String subject = "회원 임시 비밀번호입니다.";
        sendToEmail(subject, request.getEmail(), tempPw);
        findUser.setPassword(tempPw);
    }

    private User checkEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(Codeset.INVALID_USER, "해당 이메일의 사용자를 찾을 수 없습니다."));
    }

    private void sendToEmail(String subject, String email, String authKey) {
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
