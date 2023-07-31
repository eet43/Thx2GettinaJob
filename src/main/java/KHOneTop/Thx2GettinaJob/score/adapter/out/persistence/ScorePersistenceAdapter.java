package KHOneTop.Thx2GettinaJob.score.adapter.out.persistence;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.score.application.port.out.ChangeScorePort;
import KHOneTop.Thx2GettinaJob.score.application.port.out.LoadScorePort;
import KHOneTop.Thx2GettinaJob.score.domain.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScorePersistenceAdapter implements LoadScorePort, ChangeScorePort {
    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreMapper;

    @Override
    public Score load(Long id) {
        ScoreEntity entity = scoreRepository.findById(id)
                .orElseThrow(() -> new CustomException(Codeset.INVALID_SCORE, "해당하는 자격증 데이터를 찾을 수 없습니다."));

        return scoreMapper.toDomain(entity);
    }

    @Override
    public List<Score> loadAll(Long userId) {
        List<ScoreEntity> entity = scoreRepository.findAllByUserId(userId);
        return entity.stream()
                .map(scoreMapper::toDomain)
                .toList();
    }

    @Override
    public List<Score> loadValidAll(Long userId) {
        List<ScoreEntity> entity = scoreRepository.findAllByUserIdAndIsEffectiveTrue(userId);
        return entity.stream()
                .map(scoreMapper::toDomain)
                .toList();
    }

    @Override
    public List<Score> loadAllByIds(List<Long> scoreIds) {
        List<ScoreEntity> entity = scoreRepository.findAllById(scoreIds);
        return entity.stream()
                .map(scoreMapper::toDomain)
                .toList();
    }

    @Override
    public void save(Score score) {
        scoreRepository.save(scoreMapper.toEntity(score));
    }

    @Override
    public void saveAll(List<Score> scores) {
        List<ScoreEntity> entity = scores.stream()
                .map(scoreMapper::toEntity)
                .toList();
        scoreRepository.saveAll(entity);
    }

    @Override
    public void delete(Score score) {
        scoreRepository.delete(scoreMapper.toEntity(score));
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        scoreRepository.deleteByUserId(userId);
    }

}
