package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {
        boolean existsById(Long Id);
        Optional<Exam> findByName(String name);

        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id IN :examIds")
        List<Exam> findByIdInFetchJoin(@Param("examIds") List<Long> examIds);

        @Query("SELECT e FROM Exam e JOIN e.examTimeStamp WHERE e.id IN :examIds")
        List<Exam> findAllByIdIn(@Param("examIds") List<Long> examIds);

        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id = :id")
        Optional<Exam> findByIdFetchJoin(@Param("id") Long id);

        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id = :id")
        Optional<PrivateExam> findPriExamByIdFetchJoin(@Param("id") Long id);

        @Query("SELECT e FROM Exam e WHERE e.name IN :examNames")
        Optional<List<Exam>> findAllByExamNames(@Param("examNames") List<String> examNames);

        @Query(value = "SELECT * FROM Exam e WHERE e.is_public = true OR (e.DTYPE = 'private' AND e.user_id = :userId)", nativeQuery = true)
        List<Exam> findPublicOrOwnedExams(@Param("userId") Long userId);

        @Query("SELECT e FROM Exam e JOIN e.examTimeStamp ets WHERE ets.regEndDate > CURRENT_TIMESTAMP OR ets.addRegEndDate > CURRENT_TIMESTAMP ORDER BY ets.addRegEndDate ASC, ets.addRegEndDate ASC")
        List<Exam> findTop3ByOrderByDateAsc(Pageable pageable);

        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id IN :examIds")
        List<Exam> findExamsWithAnySchedule(@Param("examIds") List<Long> examIds);

}
