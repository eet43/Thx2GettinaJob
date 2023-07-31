package KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteScoreRequest(
        @NotNull
        Long userId,
        @NotNull
        Long scoreId
) {
}
