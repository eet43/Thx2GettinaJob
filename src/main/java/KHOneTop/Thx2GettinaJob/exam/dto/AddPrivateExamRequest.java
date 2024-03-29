package KHOneTop.Thx2GettinaJob.exam.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public record AddPrivateExamRequest(
        Long userId,
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
        public PrivateExam toEntity() {

                PrivateExam newExam = PrivateExam.builder()
                        .userId(this.userId)
                        .name(this.name)
                        .issuer(this.issuer)
                        .url(this.url)
                        .isPublic(false)
                        .build();

                ExamTimeStamp timeStamp = ExamTimeStamp.builder()
                        .examDate(this.examDate)
                        .regStartDate(this.regStartDate)
                        .regEndDate(this.regEndDate)
                        .addRegStartDate(this.addRegStartDate)
                        .addRegEndDate(this.addRegEndDate)
                        .resultDate(this.resultDate)
                        .build();

                newExam.addExamTime(timeStamp);

                return newExam;
        }
}
