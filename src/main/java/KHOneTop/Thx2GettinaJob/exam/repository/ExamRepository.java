package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.exam.dto.NearExamInfo;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long>, ExamRepositoryCustom {
        boolean existsById(Long Id);

        boolean existsByName(String Name);

        @Query(value = "SELECT CASE WHEN (COUNT(e) > 0) THEN true ELSE false END FROM exam e WHERE (e.name = :examName AND e.exam_type = 'public') OR (e.name = :examName AND e.exam_type = 'private' AND e.user_id = :userId)", nativeQuery = true)
        boolean existsByNameAndUserId(@Param("examName") String examName, @Param("userId") Long userId);


        Optional<Exam> findByName(String name);

        @Query("SELECT e.isPublic FROM Exam e WHERE e.id = :id")
        Boolean isPublicExam(Long id);

        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id IN :examIds")
        List<Exam> findByIdInFetchJoin(@Param("examIds") List<Long> examIds);

        @Query("SELECT e FROM Exam e JOIN e.examTimeStamp WHERE e.id IN :examIds")
        List<Exam> findAllByIdIn(@Param("examIds") List<Long> examIds);

        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id = :id")
        Optional<Exam> findByIdFetchJoin(@Param("id") Long id);

        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id = :id")
        Optional<PrivateExam> findPriExamByIdFetchJoin(@Param("id") Long id);

        @Query("SELECT e FROM Exam e WHERE e.name IN :examNames")
        List<Exam> findAllByExamNames(@Param("examNames") List<String> examNames);

        @Query(value = "SELECT * FROM exam e WHERE e.is_public = true OR (e.exam_type = 'private' AND e.user_id = :userId)", nativeQuery = true)
        List<Exam> findPublicOrOwnedExams(@Param("userId") Long userId);

        @Query("SELECT new KHOneTop.Thx2GettinaJob.exam.dto.NearExamInfo(e.id, e.name, e.issuer, MIN(ets.regEndDate), MIN(ets.addRegEndDate)) FROM Exam e JOIN e.examTimeStamp ets WHERE (ets.regEndDate > CURRENT_TIMESTAMP OR ets.addRegEndDate > CURRENT_TIMESTAMP) AND e.id IN :examIds GROUP BY e.id ORDER BY MIN(LEAST(ets.regEndDate, ets.addRegEndDate)) ASC")
        List<NearExamInfo> findTop3ByOrderByRegEndDateAsc(@Param("examIds") List<Long> examIds, Pageable pageable);


        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id IN :examIds")
        List<Exam> findExamsWithAnySchedule(@Param("examIds") List<Long> examIds);

}
