package KHOneTop.Thx2GettinaJob.score.dto;

import KHOneTop.Thx2GettinaJob.score.entity.Score;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ModifyScoreRequest(
        @NotNull
        Long scoreId,
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
}
