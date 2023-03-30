package KHOneTop.Thx2GettinaJob.common.advice;

import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse exHandler(CustomException e){
        log.error("EXCEPTION = {}, INTERNAL_MESSAGE = {}", e.getCode(), e.getMessage());
        return CustomResponse.fail(e.getCode());
    }
}
