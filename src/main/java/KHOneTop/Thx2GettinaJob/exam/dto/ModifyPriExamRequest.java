package KHOneTop.Thx2GettinaJob.exam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ModifyPriExamRequest(
        Long examId,
        String name,
        String issuer,
        String url,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime examDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime regStartDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime regEndDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime addRegStartDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime addRegEndDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime resultDate
) {

}
