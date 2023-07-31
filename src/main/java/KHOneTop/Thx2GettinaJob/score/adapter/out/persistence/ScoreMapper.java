package KHOneTop.Thx2GettinaJob.score.adapter.out.persistence;

import KHOneTop.Thx2GettinaJob.score.domain.Score;
import org.springframework.stereotype.Component;

@Component
public class ScoreMapper {
    public Score toDomain(ScoreEntity entity) {
        return Score.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .score(entity.getScore())
                .studentCode(entity.getStudentCode())
                .issuer(entity.getIssuer())
                .isEffective(entity.getIsEffective())
                .acquisitionDate(entity.getAcquisitionDate())
                .expirationDate(entity.getExpirationDate())
                .build();
    }

    public ScoreEntity toEntity(Score domain) {
        return ScoreEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .name(domain.getName())
                .score(domain.getScore())
                .studentCode(domain.getStudentCode())
                .issuer(domain.getIssuer())
                .isEffective(domain.getIsEffective())
                .acquisitionDate(domain.getAcquisitionDate())
                .expirationDate(domain.getExpirationDate())
                .build();
    }
}
