package KHOneTop.Thx2GettinaJob.score.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Score {
    private Long id;
    private Long userId;
    private String name;
    private String score;
    private String studentCode;
    private String issuer;
    private Boolean isEffective;
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
