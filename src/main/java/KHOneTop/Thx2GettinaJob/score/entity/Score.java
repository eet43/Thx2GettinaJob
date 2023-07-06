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


    public void modifyScore(String name, String score, String studentCode, String issuer,
                            LocalDate acquisitionDate, LocalDate expirationDate) {
        boolean isEffect = true;
        if(expirationDate != null) {
            isEffect = expirationDate.isAfter(LocalDate.now());
        }

        this.name = name;
        this.score = score;
        this.studentCode = studentCode;
        this.issuer = issuer;
        this.isEffective = isEffect;
        this.acquisitionDate = acquisitionDate;
        this.expirationDate = expirationDate;
    }

    public void expire() {
        this.isEffective = false;
    }
}
