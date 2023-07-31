package KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto;

import KHOneTop.Thx2GettinaJob.score.domain.Score;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateScoreRequest(
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
    public Score toDomain() {
        boolean isEffect = true;
        if(expirationDate != null) {
            isEffect = expirationDate.isAfter(LocalDate.now());
        }

        return Score.builder()
                .userId(userId)
                .name(name)
                .score(score)
                .studentCode(studentCode)
                .issuer(issuer)
                .isEffective(isEffect)
                .acquisitionDate(acquisitionDate)
                .expirationDate(expirationDate)
                .build();
    }
}
