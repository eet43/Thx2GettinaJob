package KHOneTop.Thx2GettinaJob.score.dto;

import KHOneTop.Thx2GettinaJob.score.entity.Score;

import java.time.LocalDate;

public record ModifyScoreRequest(
        Long scoreId,
        String name,
        Long score,
        String studentCode,
        String issuer,
        LocalDate acquisitionDate,
        LocalDate expirationDate
) {
}
