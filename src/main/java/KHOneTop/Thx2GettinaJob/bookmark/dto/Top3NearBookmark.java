package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;

public record Top3NearBookmark(
        Long examId,
        String name,
        String issuer,
        String status,
        Long day
) {
    public static Top3NearBookmark fromEntity(Exam exam, String status, Long day) {
        return new Top3NearBookmark(exam.getId(), exam.getName(), exam.getIssuer(), status, day);
    }
}
