package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exam_type")
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "url")
    private String url;

    private Boolean isPublic;

    @Embedded
    private ExamTimeStamp examTimeStamp;
}
