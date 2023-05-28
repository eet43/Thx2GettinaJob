package KHOneTop.Thx2GettinaJob.bookmark.dto;

public record GetCalenderDetailRequest(
        Long examId,
        Boolean HavRegDate,
        Boolean HavAddRegDate,
        Boolean HavResultDate,
        Boolean HavExamDate
) {
}
