package KHOneTop.Thx2GettinaJob.exam.entity;

import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exam_type")
@Getter @Setter
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
