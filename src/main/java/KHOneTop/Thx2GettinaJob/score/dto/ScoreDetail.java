package KHOneTop.Thx2GettinaJob.score.dto;

import java.time.LocalDate;

public record ScoreDetail(
        Long id,
        String name,
        Long score,
        String studentCode,
        String issuer,
        LocalDate acquisitionDate,
        LocalDate expirationDate
) {
}
