package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamTimeStamp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String turn;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
