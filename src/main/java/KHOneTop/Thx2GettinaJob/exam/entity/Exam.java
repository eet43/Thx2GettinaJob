package KHOneTop.Thx2GettinaJob.exam.entity;

import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@DiscriminatorColumn(name = "exam_type")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
public sealed class Exam
        permits PrivateExam, PublicExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "issuer")
    protected String issuer;

    @Column(name = "url")
    protected String url;

    protected Boolean isPublic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exam", cascade = CascadeType.ALL)
    protected List<ExamTimeStamp> examTimeStamp;

    public void addExamTime(ExamTimeStamp examTime) {
        this.examTimeStamp.add(examTime);
        examTime.setExam(this);
    }
}
