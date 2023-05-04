package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
        boolean existsByName(String name);
}
