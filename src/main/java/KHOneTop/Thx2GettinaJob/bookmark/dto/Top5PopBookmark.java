package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.common.Dday;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;

public record Top5PopBookmark(
        Long examId,
        String name,
        String issuer,
        String status,
        Long day,
        Long count
) {
    public static Top5PopBookmark fromEntity(Exam exam, String status, Long day, Long count) {
        return new Top5PopBookmark(exam.getId(), exam.getName(), exam.getIssuer(), status, day, count);
    }
}
