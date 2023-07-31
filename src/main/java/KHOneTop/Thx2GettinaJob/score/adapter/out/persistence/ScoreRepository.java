package KHOneTop.Thx2GettinaJob.score.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
    List<ScoreEntity> findAllByUserId(Long userId);

    List<ScoreEntity> findAllByUserIdAndIsEffectiveTrue(Long userId);

    List<ScoreEntity> findByIsEffectiveTrueAndExpirationDateBefore(LocalDate date);

    @Modifying
    @Query("DELETE FROM ScoreEntity s WHERE s.userId = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
