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
    ALREADY_EMAIL("1105", "이미 존재하는 이메일입니다."),
    INVALID_USER("1102", "해당 이메일에 해당하는 ID가 존재하지 않습니다."),
    INVALID_PASSWORD("1103", "비밀번호가 일치하지 않습니다."),
    INVALID_PASSWORD_TYPE("1104", "비밀번호가 8자 이하이거나, 특수문자를 포함하지 않습니다."),
    INVALID_REFRESH_TOKEN("1110", "Refresh Token 이 만료되었습니다. 다시 로그인해주세요."),
    INVALID_EXAM("1202", "해당하는 시험 데이터가 존재하지 않습니다."),
    INVALID_PRIEXAM_TIMESTAMP("1203", "직접추가한 자격증 일정 객체 개수에 문제가 있습니다. 개발팀에게 문의해주세요"),
    INVALID_PRIEXAM("1204", "해당 시험은 직접 추가한 객체가 아닙니다. 개발팀에게 문의해주세요"),
    INVALID_NEAR_EXAM("1205", "얼마남지 않은 자격증 조회에 문제가 생겼습니다."),
    ALREADY_EXAM_NAME("1206", "이미 존재하는 시험입니다."),
    ALREADY_BOOKMARK("1207", "이미 즐겨찾기에 추가된 시험입니다."),
    INVALID_SCORE("1302", "해당하는 자격증 점수 데이터가 존재하지 않습니다."),
    INVALID_CALENDAR_FILTER_REQUEST("1401", "올바른 캘린더 필터링이 아닙니다.");


    private final String code;
    private final String message;

    Codeset(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
