package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.bookmark.dto.PrivateExamInfo;
import KHOneTop.Thx2GettinaJob.bookmark.dto.PublicExamInfo;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {
        boolean existsById(Long Id);
        Optional<Exam> findByName(String name);

        List<Exam> findByIdIn(List<Long> examIds);

        @Query("SELECT new KHOneTop.Thx2GettinaJob.bookmark.dto.PublicExamInfo(e.name, e.turn) FROM PublicExam e WHERE e.name IN :examNames")
        List<PublicExamInfo> findPublicExamsByExamNames(@Param("examNames") List<String> examNames);

        @Query("SELECT new KHOneTop.Thx2GettinaJob.bookmark.dto.PrivateExamInfo(e.name, e.examTimeStamp) FROM PrivateExam e WHERE e.name IN :examNames")
        List<PrivateExamInfo> findPrivateExamsByExamNames(@Param("examNames") List<String> examNames);

        @Query("SELECT e FROM Exam e WHERE e.name IN :examNames")
        Optional<List<Exam>> findAllByExamNames(@Param("examNames") List<String> examNames);
}
