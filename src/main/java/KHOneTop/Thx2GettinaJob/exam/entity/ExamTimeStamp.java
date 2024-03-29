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

    @Column(name = "examDate")
    private LocalDateTime examDate;

    @Column(name = "regStartDate")
    private LocalDateTime regStartDate;

    @Column(name = "regEndDate")
    private LocalDateTime regEndDate;

    @Column(name = "addRegStartDate")
    private LocalDateTime addRegStartDate;

    @Column(name = "addRegEndDate")
    private LocalDateTime addRegEndDate;

    @Column(name = "resultDate")
    private LocalDateTime resultDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void modify(LocalDateTime examDate, LocalDateTime regStartDate, LocalDateTime regEndDate,
                       LocalDateTime addRegStartDate, LocalDateTime addRegEndDate, LocalDateTime resultDate) {
        this.examDate = examDate;
        this.regStartDate = regStartDate;
        this.regEndDate = regEndDate;
        this.addRegStartDate = addRegStartDate;
        this.addRegEndDate = addRegEndDate;
        this.resultDate = resultDate;
    }
}
