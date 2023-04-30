package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("private")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
public class PrivateExam extends Exam{
    private Long userId;
}
