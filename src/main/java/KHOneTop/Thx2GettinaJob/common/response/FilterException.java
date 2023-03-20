package KHOneTop.Thx2GettinaJob.common.response;

import org.springframework.security.core.AuthenticationException;

public class FilterException extends AuthenticationException {
    public FilterException(Codeset code) {
        super(code.getMessage());
        this.code = code;
    }
    private Codeset code;

    public Codeset getCode() {
        return this.code;
    }
}