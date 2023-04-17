package KHOneTop.Thx2GettinaJob.exam.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    TOEIC("토익", "한국 TOEIC 위원회", "https://exam.toeic.co.kr/"),
    AFPK("AFPK", "사단법인한국에프피에스비", "https://www.fpsbkorea.org/?mnu_usn=29"),
    KOREAN("한국사능력검정시험", "국사편찬위원회", "https://www.historyexam.go.kr/"),
    TOEIC_SPEAKING("토익 스피킹", "ETS", "https://www.toeicswt.co.kr/"),
    OPIC_ENGLISH("OPIC 영어", "ACTFL", "https://m.opic.or.kr/opics/jsp/senior/indexMobile.jsp"),
    COMPUTER_ONE("컴퓨터활용능력 1급", "대한상공회의소", "https://license.korcham.net/"),
    COMPUTER_TWO("컴퓨터활용능력 2급", "대한상공회의소", "https://license.korcham.net/"),
    WORD_PROCESSOR("워드프로세서", "대한상공회의소", "https://license.korcham.net/"),
    COMPUTER_ACCOUNTING("전산회계운용사", "대한상공회의소", "https://license.korcham.net/"),
    SECRETARY("비서", "대한상공회의소", "https://license.korcham.net/"),
    COMMERCE_KANJI("상공회의소 한자", "대한상공회의소", "https://license.korcham.net/"),
    TRADE_ENGLISH("무역영어", "대한상공회의소", "https://license.korcham.net/");



    private final String value;
    private final String issuer;
    private final String url;
}
