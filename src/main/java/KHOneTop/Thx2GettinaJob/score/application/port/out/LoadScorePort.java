package KHOneTop.Thx2GettinaJob.score.application.port.out;

import KHOneTop.Thx2GettinaJob.score.domain.Score;

import java.util.List;

public interface LoadScorePort {
    Score load(Long id);
    List<Score> loadAll(Long userId);
    List<Score> loadValidAll(Long userId);

    List<Score> loadAllByIds(List<Long> scoreIds);
}
