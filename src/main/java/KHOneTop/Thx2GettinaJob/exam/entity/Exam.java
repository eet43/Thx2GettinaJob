package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "exam")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "agency", nullable = false)
    private String agency;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Embedded
    private ExamTimeStamp examTimeStamp;

    @Enumerated(value = EnumType.STRING)
    private ExamStatus examStatus;

}
