package KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;

public record GetScoreRequest(
        @NotNull
        Long userId
) {
}