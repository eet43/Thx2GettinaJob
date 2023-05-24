package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarBookmarkSearch {
    private Long examId;
    private boolean isPublic;
    private boolean isTurn;
    private boolean HavRegDate;
    private boolean HavAddRegDate;
    private boolean HavResultDate;
    private boolean HavExamDate;

    public static CalendarBookmarkSearch toDto(Exam exam) {
        CalendarBookmarkSearch dto = new CalendarBookmarkSearch();
        dto.setExamId(exam.getId());
        dto.setPublic(exam.getIsPublic());
        dto.setTurn(exam.getExamTimeStamp().size() > 1);

        ExamTimeStamp sample = exam.getExamTimeStamp().get(0);
        dto.setHavRegDate(sample.getRegStartDate() != null);
        dto.setHavAddRegDate(sample.getAddRegStartDate() != null);
        dto.setHavResultDate(sample.getResultDate() != null);
        dto.setHavExamDate(sample.getExamDate() != null);

        return dto;
    }
}
