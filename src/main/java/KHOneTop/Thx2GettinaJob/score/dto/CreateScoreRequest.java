package KHOneTop.Thx2GettinaJob.score.dto;

import KHOneTop.Thx2GettinaJob.score.entity.Score;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateScoreRequest(
        @NotNull
        Long userId,
        @NotNull
        String name,
        @NotNull
        Long score,
        String studentCode,
        String issuer,
        @NotNull
        LocalDate acquisitionDate,
        LocalDate expirationDate
) {
    public Score toEntity() {
        return Score.builder()
                .userId(userId)
                .name(name)
                .score(score)
                .studentCode(studentCode)
                .issuer(issuer)
                .isEffective(true)
                .acquisitionDate(acquisitionDate)
                .expirationDate(expirationDate)
                .build();
    }
}
