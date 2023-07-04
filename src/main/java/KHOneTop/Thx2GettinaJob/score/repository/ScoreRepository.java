package KHOneTop.Thx2GettinaJob.score.repository;

import KHOneTop.Thx2GettinaJob.score.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByUserId(Long userId);

    List<Score> findAllByUserIdAndIsEffectiveTrue(Long userId);

    List<Score> findByIsEffectiveTrueAndExpirationDateBefore(LocalDate date);
}
