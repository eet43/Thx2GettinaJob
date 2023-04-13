package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class ExamTimeStamp {

    @Column(name = "examDate", nullable = false)
    private LocalDateTime examDate;

    @Column(name = "regStartDate", nullable = false)
    private LocalDateTime regStartDate;

    @Column(name = "regEndDate", nullable = false)
    private LocalDateTime regEndDate;

    @Column(name = "addRegStartDate", nullable = false)
    private LocalDateTime addRegStartDate;

    @Column(name = "addRegEndDate", nullable = false)
    private LocalDateTime addRegEndDate;

    @Column(name = "resultDate", nullable = false)
    private LocalDateTime resultDate;
}
