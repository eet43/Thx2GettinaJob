package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;

import java.time.LocalDateTime;
import java.util.ArrayList;

public record AddBookmarkPrivateExamRequest(
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
                        .examTimeStamp(new ArrayList<>())
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
