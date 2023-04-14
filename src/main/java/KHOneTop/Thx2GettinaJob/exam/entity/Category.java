package KHOneTop.Thx2GettinaJob.exam.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    TOEIC("토익", "한국 TOEIC 위원회", "https://exam.toeic.co.kr/"),
    AFPK("AFPK", "사단법인한국에프피에스비", "https://www.fpsbkorea.org/?mnu_usn=29");

    private final String value;
    private final String issuer;
    private final String url;
}
