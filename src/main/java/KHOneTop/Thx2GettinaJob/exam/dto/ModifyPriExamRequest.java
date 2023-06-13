package KHOneTop.Thx2GettinaJob.exam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ModifyPriExamRequest(
        Long examId,
        String name,
        String issuer,
        String url,
        LocalDateTime examDate,
        LocalDateTime regStartDate,
        LocalDateTime regEndDate,
        LocalDateTime addRegStartDate,
        LocalDateTime addRegEndDate,
        LocalDateTime resultDate
) {

}
