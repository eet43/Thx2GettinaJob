package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.calendar.dto.GetCalenderDetailRequest;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExamRepositoryCustom {
    List<Exam> findExamWithoutBoolean(LocalDate startDate, LocalDate endDate, List<Long> examIds);

    Optional<Exam> findExamByExamTimeStampFields(LocalDate startDate, LocalDate endDate, GetCalenderDetailRequest request);
}
