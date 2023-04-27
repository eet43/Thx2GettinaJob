package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "publicExam")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicExam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "turn", nullable = false)
    private String turn;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Embedded
    private ExamTimeStamp examTimeStamp;


}
