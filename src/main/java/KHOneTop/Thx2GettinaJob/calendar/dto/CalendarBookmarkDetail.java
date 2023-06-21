package KHOneTop.Thx2GettinaJob.calendar.dto;

import KHOneTop.Thx2GettinaJob.calendar.dto.CalendarBookmarkTimeDetail;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarBookmarkDetail {
    private Long examId;
    private String name;
    private String issuer;
    private String url;
    private Boolean isPublic;
    private List<CalendarBookmarkTimeDetail> times;

    public static CalendarBookmarkDetail toDto(Exam exam) {
        CalendarBookmarkDetail dto = new CalendarBookmarkDetail();
        dto.setExamId(exam.getId());
        dto.setName(exam.getName());
        dto.setIssuer(exam.getIssuer());
        dto.setUrl(exam.getUrl());
        dto.setIsPublic(exam.getIsPublic());

        List<CalendarBookmarkTimeDetail> timeDto = new ArrayList<>();
        for (ExamTimeStamp time : exam.getExamTimeStamp()) {
            timeDto.add(CalendarBookmarkTimeDetail.toDto(time));
        }
        dto.setTimes(timeDto);
        return dto;
    }
}
