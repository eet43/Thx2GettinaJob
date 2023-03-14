package KHOneTop.Thx2GettinaJob.common.response;

public class CustomException extends RuntimeException{
    private Codeset code;
    private String message;

    public CustomException(Codeset code, String internalMessage) {
        this.code = code;
        this.message = internalMessage;
    }
}
