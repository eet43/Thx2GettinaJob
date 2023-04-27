package KHOneTop.Thx2GettinaJob.exam.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public record AddPrivateExamRequest(
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
        public PrivateExam toEntity() {
                ExamTimeStamp timeStamp = ExamTimeStamp.builder()
                        .examDate(this.examDate)
                        .regStartDate(this.regStartDate)
                        .regEndDate(this.regEndDate)
                        .addRegStartDate(this.addRegStartDate)
                        .addRegEndDate(this.addRegEndDate)
                        .resultDate(this.resultDate)
                        .build();

                return PrivateExam.builder()
                        .name(this.name)
                        .issuer(this.issuer)
                        .url(this.url)
                        .examTimeStamp(timeStamp)
                        .build();
        }
}
