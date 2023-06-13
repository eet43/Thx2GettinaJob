package KHOneTop.Thx2GettinaJob.common.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@RestControllerAdvice
//public class GlobalControllerAdvice {
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport()
//        {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//            }
//        });
//    }
//}
