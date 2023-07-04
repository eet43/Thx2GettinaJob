package KHOneTop.Thx2GettinaJob.exam.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("public")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
public final class PublicExam extends Exam{
}
