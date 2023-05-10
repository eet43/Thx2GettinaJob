package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;

public record BookmarkInfo(
        Long id,
        String name,
        String issuer,
        String url,
        Boolean isPublic,
        String status,
        Long day
) {
    public static BookmarkInfo fromEntity(Exam exam, String status, Long day) {
        return new BookmarkInfo(exam.getId(), exam.getName(), exam.getIssuer(), exam.getUrl(), exam.getIsPublic(), status, day);
    }

}
