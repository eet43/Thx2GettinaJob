package KHOneTop.Thx2GettinaJob.score.dto;

import KHOneTop.Thx2GettinaJob.score.entity.Score;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
public class ScoreDetail {
    private Long id;
    private String name;
    private Long score;
    private String studentCode;
    private String issuer;
    private Long day;
    private LocalDate acquisitionDate;
    private LocalDate expirationDate;
    public void setDay(Long day) {
        this.day = day;
    }
}
