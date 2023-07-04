package KHOneTop.Thx2GettinaJob.score.dto;

import jakarta.validation.constraints.NotNull;

public record GetScoreRequest(
        @NotNull
        Long userId
) {
}