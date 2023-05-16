package KHOneTop.Thx2GettinaJob.score.entity;

import KHOneTop.Thx2GettinaJob.score.dto.ModifyScoreRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    @Id
    @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long userId;
    private String name;
    private Long score;
    private String studentCode;
    private String issuer;
    private Boolean isEffective;

    private LocalDate acquisitionDate;
    private LocalDate expirationDate;


    public void modifyScore(String name, Long score, String studentCode, String issuer,
                            LocalDate acquisitionDate, LocalDate expirationDate) {
        this.name = name;
        this.score = score;
        this.studentCode = studentCode;
        this.issuer = issuer;
        this.acquisitionDate = acquisitionDate;
        this.expirationDate = expirationDate;
    }

    public void expire() {
        this.isEffective = false;
    }
}
