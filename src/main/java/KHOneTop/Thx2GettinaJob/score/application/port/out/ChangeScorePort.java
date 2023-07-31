package KHOneTop.Thx2GettinaJob.score.application.port.out;

import KHOneTop.Thx2GettinaJob.score.domain.Score;

import java.util.List;

public interface ChangeScorePort {
    void save(Score score);
    void saveAll(List<Score> scores); //수정필요
    void delete(Score score);
    void deleteAllByUserId(Long userId);
}
