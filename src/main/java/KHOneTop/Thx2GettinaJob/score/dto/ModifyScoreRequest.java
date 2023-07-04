package KHOneTop.Thx2GettinaJob.score.dto;

import KHOneTop.Thx2GettinaJob.score.entity.Score;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ModifyScoreRequest(
        @NotNull
        Long scoreId,
        @NotNull
        Long userId,
        @NotNull
        String name,
        @NotNull
        String score,
        String studentCode,
        String issuer,
        @NotNull
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate acquisitionDate,
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate expirationDate
) {
}
