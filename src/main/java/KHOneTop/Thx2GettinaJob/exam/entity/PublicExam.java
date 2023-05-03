package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("public")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
public class PublicExam extends Exam{
    @Column(name = "turn", nullable = false)
    private String turn;
}
