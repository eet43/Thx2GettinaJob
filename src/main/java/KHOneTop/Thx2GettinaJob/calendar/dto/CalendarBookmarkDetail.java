package KHOneTop.Thx2GettinaJob.calendar.dto;

import KHOneTop.Thx2GettinaJob.calendar.dto.CalendarBookmarkTimeDetail;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    public static CalendarBookmarkDetail toDtoFilter(Exam exam, GetCalenderDetailRequest request, LocalDate startDate, LocalDate endDate) {
        CalendarBookmarkDetail dto = createDto(exam);

        List<CalendarBookmarkTimeDetail> timeDto = new ArrayList<>();
        for (ExamTimeStamp time : exam.getExamTimeStamp()) {
            timeDto.add(CalendarBookmarkTimeDetail.toDtoFilter(time, request, startDate, endDate));
        }
        dto.setTimes(timeDto);
        return dto;
    }

    public static CalendarBookmarkDetail toDtoNonFilter(Exam exam, LocalDate startDate, LocalDate endDate) {
        CalendarBookmarkDetail dto = createDto(exam);

        List<CalendarBookmarkTimeDetail> timeDto = new ArrayList<>();
        for (ExamTimeStamp time : exam.getExamTimeStamp()) {
            timeDto.add(CalendarBookmarkTimeDetail.toDtoNonFilter(time, startDate, endDate));
        }
        dto.setTimes(timeDto);
        return dto;
    }

    private static CalendarBookmarkDetail createDto(Exam exam) {
        CalendarBookmarkDetail dto = new CalendarBookmarkDetail();
        dto.setExamId(exam.getId());
        dto.setName(exam.getName());
        dto.setIssuer(exam.getIssuer());
        dto.setUrl(exam.getUrl());
        dto.setIsPublic(exam.getIsPublic());

        return dto;
    }
}
