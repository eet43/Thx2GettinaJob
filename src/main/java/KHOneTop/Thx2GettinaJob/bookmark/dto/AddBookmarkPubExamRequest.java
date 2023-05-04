package KHOneTop.Thx2GettinaJob.bookmark.dto;

import lombok.Data;

public record AddBookmarkPubExamRequest (
        Long userId,
        String examName
) {

}
