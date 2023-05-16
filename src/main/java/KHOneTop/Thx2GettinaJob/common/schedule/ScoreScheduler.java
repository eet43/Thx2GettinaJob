package KHOneTop.Thx2GettinaJob.common.schedule;

import KHOneTop.Thx2GettinaJob.score.entity.Score;
import KHOneTop.Thx2GettinaJob.score.event.ScoreExpiredEvent;
import KHOneTop.Thx2GettinaJob.score.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ScoreScheduler {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ScoreRepository scoreRepository;
    @Scheduled(cron = "0 0 0 * * *")
    public void checkCertificateExpiration() {
        LocalDate today = LocalDate.now();
        Optional<List<Score>> scoreList = scoreRepository.findByIsEffectiveTrueAndExpirationDateBefore(today);
        scoreList.ifPresent(list -> {
            List<Long> scoreIds = list.stream()
                    .map(Score::getId).toList();
            applicationEventPublisher.publishEvent(new ScoreExpiredEvent(scoreIds));
            });
    }
}
