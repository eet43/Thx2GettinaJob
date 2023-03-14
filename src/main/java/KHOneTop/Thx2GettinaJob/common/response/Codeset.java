package KHOneTop.Thx2GettinaJob.common.response;
import lombok.Getter;
/**
 * [ 1000단위 ] - 오류의 범위
 *  0000 : 요청 성공
 *  2 : Request 오류
 *  3 : Reponse 오류
 *  4 : DB, Server 오류
 *
 * [ 100단위 ] - 오류 도메인
 *  0 : 공통 오류
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
    BAD_REQUEST("1000", "BAD_REQUEST");

    private final String code;
    private final String message;

    Codeset(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
