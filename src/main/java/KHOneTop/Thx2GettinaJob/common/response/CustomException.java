package KHOneTop.Thx2GettinaJob.common.response;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private Codeset code;
    private String message;

    public CustomException(Codeset code, String internalMessage) {
        this.code = code;
        this.message = internalMessage;
    }
}
