package KHOneTop.Thx2GettinaJob.calendar.dto;

public record GetCalenderDetailRequest(
        Long examId,
        Boolean HavRegDate,
        Boolean HavAddRegDate,
        Boolean HavResultDate,
        Boolean HavExamDate
) {
}
