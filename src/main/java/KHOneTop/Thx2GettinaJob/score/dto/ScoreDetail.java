package KHOneTop.Thx2GettinaJob.score.dto;

import KHOneTop.Thx2GettinaJob.score.entity.Score;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Schema(description = "자격증 조회")
public class ScoreDetail {
    @Schema(description = "자격증 점수 Id", nullable = false, example = "2L")
    private Long id;
    @Schema(description = "자격증 이름", nullable = false, example = "TOEIC")
    private String name;
    @Schema(description = "자격증 점수", nullable = false, example = "900")
    private Long score;
    @Schema(description = "수험번호", nullable = false, example = "15291BSA")
    private String studentCode;
    @Schema(description = "시험 발급처", nullable = false, example = "한국진흥원")
    private String issuer;
    @Schema(description = "유효한지 아닌지", nullable = false, example = "true")
    private Boolean isEffective;
    @Schema(description = "D-day", nullable = false, example = "3")
    private Long day;
    @Schema(description = "취득날짜", nullable = false)
    private LocalDate acquisitionDate;
    @Schema(description = "유효날짜")
    private LocalDate expirationDate;
    public void setDay(Long day) {
        this.day = day;
    }
}
