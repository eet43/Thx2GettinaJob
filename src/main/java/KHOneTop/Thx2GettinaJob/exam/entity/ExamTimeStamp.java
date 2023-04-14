package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamTimeStamp {

    @Column(name = "examDate", nullable = false)
    private LocalDateTime examDate;

    @Column(name = "regStartDate", nullable = false)
    private LocalDateTime regStartDate;

    @Column(name = "regEndDate", nullable = false)
    private LocalDateTime regEndDate;

    @Column(name = "addRegStartDate")
    private LocalDateTime addRegStartDate;

    @Column(name = "addRegEndDate")
    private LocalDateTime addRegEndDate;

    @Column(name = "resultDate", nullable = false)
    private LocalDateTime resultDate;
}
