package KHOneTop.Thx2GettinaJob.common.response;
import lombok.Getter;
/**
 * [ 1000단위 ] - 오류의 범위
 *  0000 : 요청 성공
 *  1 : Request 오류
 *  2 : Reponse 오류
 *  3 : DB, Server 오류
 *
 * [ 100단위 ] - 오류 도메인
 *  100 : 인증 오류
 *
 * [10단위] - 오류 HTTP Method
 *  0~19 : Common
 *  20~39 : GET
 *  40~59 : POST
 *  60~79 : PATCH
 *  80~99 : else
 *
 *  [1 단위] - 그외 오류의 고유 식별자
 *          - 순서대로 설정해주면 됨
 */

@Getter
public enum Codeset {
    OK("0000", "OK"),
    BAD_REQUEST("1000", "BAD_REQUEST"),

    VERIFY_EMAIL_FAIL("1100", "인증에 실패했습니다. 인증번호를 확인 후 재입력해주세요."),
    ALREADY_NICKNAME("1101", "이미 존재하는 닉네임입니다."),
    INVALID_USER("1102", "해당 이메일에 해당하는 ID가 존재하지 않습니다.");

    private final String code;
    private final String message;

    Codeset(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
