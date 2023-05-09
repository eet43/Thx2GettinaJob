package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

public record BookmarkDetail(
        Long id,
        String name,
        String issuer,
        String url,
        String turn,
        Boolean isPublic,
        ExamTimeStamp examTimeStamp
) {
}
