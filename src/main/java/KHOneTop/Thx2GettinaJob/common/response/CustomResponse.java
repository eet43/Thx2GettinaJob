package KHOneTop.Thx2GettinaJob.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"code", "message", "result", "invalidInput"})  /** Json 으로 나갈 순서를 설정하는 어노테이션*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {

    @JsonProperty("code")
    private final String code;

    private final String message;

    private T result;

    private T invalidInput;

    private CustomResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private CustomResponse(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @JsonPropertyOrder({"isSuccess", "code", "message", "invalidInput"})
    private CustomResponse(Codeset code, T invalidInput){
        this.code = code.getCode();
        this.message = code.getMessage();
        this.invalidInput = invalidInput;
    }

    /** 1. [API 성공시 나가는 응답] */
    public static CustomResponse success(){
        return new CustomResponse(Codeset.OK.getCode(), Codeset.OK.getMessage());
    }

    public static <T> CustomResponse<T> success(T result){
        return new CustomResponse<>(Codeset.OK.getCode(), Codeset.OK.getMessage(), result);
    }

    /** 2. [API 실패시 나가는 응답] */

    public static CustomResponse fail(Codeset code){
        return new CustomResponse<>(code.getCode(), code.getMessage());
    }

    /** 3. [API 실패시 나가는 응답 - 어떤 유효하지 않은 입력값이 들어와서 실패했는지 - 그 유효하지 않은 입력값을 함꼐 보내줌] */
    public static <T> CustomResponse<T> failWithInvalidInput(Codeset code, T invalidInput){
        return new CustomResponse<>(code, invalidInput);
    }
}
