package KHOneTop.Thx2GettinaJob.score.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "score")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String score;
    private String studentCode;
    private String issuer;
    private Boolean isEffective;

    @Column(nullable = false)
    private LocalDate acquisitionDate;
    private LocalDate expirationDate;
}
