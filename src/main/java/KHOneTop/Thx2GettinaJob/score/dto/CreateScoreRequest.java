package KHOneTop.Thx2GettinaJob.score.dto;

import KHOneTop.Thx2GettinaJob.score.entity.Score;

import java.time.LocalDate;

public record CreateScoreRequest(
        Long userId,
        String name,
        Long score,
        String studentCode,
        String issuer,
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
