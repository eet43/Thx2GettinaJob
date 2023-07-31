package KHOneTop.Thx2GettinaJob.common.schedule;

import KHOneTop.Thx2GettinaJob.score.adapter.out.persistence.ScoreEntity;
import KHOneTop.Thx2GettinaJob.score.adapter.out.persistence.ScoreRepository;
import KHOneTop.Thx2GettinaJob.score.application.event.ScoreExpiredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScoreScheduler {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ScoreRepository scoreRepository;
    @Scheduled(cron = "0 0 0 * * *")
    public void checkCertificateExpiration() {
        LocalDate today = LocalDate.now();
        List<ScoreEntity> scoreList = scoreRepository.findByIsEffectiveTrueAndExpirationDateBefore(today);
        if (!scoreList.isEmpty()) {
            List<Long> scoreIds = scoreList.stream()
                    .map(ScoreEntity::getId).toList();
            applicationEventPublisher.publishEvent(new ScoreExpiredEvent(scoreIds));
        }
    }
}
