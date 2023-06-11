package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.bookmark.dto.GetCalenderDetailRequest;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExamRepositoryCustom {
    Optional<Exam> findExamByExamTimeStampFields(LocalDate startDate, LocalDate endDate, GetCalenderDetailRequest request);
}
