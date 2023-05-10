package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ExamTimeStampRepository extends JpaRepository<ExamTimeStamp, Long> {
    Optional<ExamTimeStamp> findByExam(Exam exam);
    ExamTimeStamp findTopByExamIdAndAddRegEndDateAfterOrderByAddRegEndDateAsc(Long examId, LocalDateTime now);
    ExamTimeStamp findTopByExamIdAndRegEndDateAfterOrderByRegEndDateAsc(Long examId, LocalDateTime now);
}
