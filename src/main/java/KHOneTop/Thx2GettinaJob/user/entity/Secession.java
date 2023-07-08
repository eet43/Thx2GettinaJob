package KHOneTop.Thx2GettinaJob.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "secession")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Secession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "secession_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Reason reason;

    private LocalDateTime deleteTime;
}
