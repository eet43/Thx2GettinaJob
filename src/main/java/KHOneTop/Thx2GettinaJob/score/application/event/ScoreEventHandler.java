package KHOneTop.Thx2GettinaJob.score.application.event;

import KHOneTop.Thx2GettinaJob.score.application.port.out.ChangeScorePort;
import KHOneTop.Thx2GettinaJob.score.application.port.out.LoadScorePort;
import KHOneTop.Thx2GettinaJob.score.domain.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScoreEventHandler {
    private final LoadScorePort loadScorePort;
    private final ChangeScorePort changeScorePort;

    @EventListener
    @Transactional
    public void handleScoreExpiredEvent(ScoreExpiredEvent event) {
        List<Score> scores = loadScorePort.loadAllByIds(event.getScoreIds());
        for (Score score : scores) {
            score.expire();
        }
        changeScorePort.saveAll(scores);
    }
}
