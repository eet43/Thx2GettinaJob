package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.bookmark.dto.PrivateExamInfo;
import KHOneTop.Thx2GettinaJob.exam.dto.PublicExamInfo;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
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

        @Query("SELECT e FROM Exam e JOIN FETCH e.examTimeStamp WHERE e.id = :id")
        Optional<Exam> findByIdFetchJoin(@Param("id") Long id);

        @Query("SELECT e FROM Exam e WHERE e.name IN :examNames")
        Optional<List<Exam>> findAllByExamNames(@Param("examNames") List<String> examNames);

        @Query("SELECT new KHOneTop.Thx2GettinaJob.exam.dto.PublicExamInfo(e.id, e.name, e.issuer) FROM Exam e WHERE e.isPublic = true")
        List<PublicExamInfo> findPublicExam();
}
