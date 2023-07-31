package KHOneTop.Thx2GettinaJob.score.application.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class ScoreExpiredEvent extends ApplicationEvent {
    private List<Long> scoreIds;

    public ScoreExpiredEvent(List<Long> scoreIds) {
        super(scoreIds);
        this.scoreIds = scoreIds;
    }
}
