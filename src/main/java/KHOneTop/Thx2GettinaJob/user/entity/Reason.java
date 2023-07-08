package KHOneTop.Thx2GettinaJob.user.entity;

public enum Reason {
    INFO("자격증 정보가 부족해요."),
    QUALITY("콘텐츠 품질이 불만족스러워요."),
    RESIGN("다른 이메일로 재가입할게요."),
    ETC("기타.");

    private final String reason;

    Reason(String reason) {
        this.reason = reason;
    }
}
