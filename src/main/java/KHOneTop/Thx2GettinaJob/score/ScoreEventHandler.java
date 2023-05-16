package KHOneTop.Thx2GettinaJob.score;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.score.entity.Score;
import KHOneTop.Thx2GettinaJob.score.event.ScoreExpiredEvent;
import KHOneTop.Thx2GettinaJob.score.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ScoreEventHandler {

    @Autowired
    private ScoreRepository scoreRepository;

    @EventListener
    @Transactional
    public void handleScoreExpiredEvent(ScoreExpiredEvent event) {
        List<Score> scores = scoreRepository.findAllById(event.getScoreIds());
        for (Score score : scores) {
            score.expire();
        }
        scoreRepository.saveAll(scores);
    }
}
