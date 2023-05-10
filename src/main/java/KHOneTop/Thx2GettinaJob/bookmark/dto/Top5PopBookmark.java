package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.common.Dday;

public record Top5PopBookmark(
        String name,
        String issuer,
        Long count,
        Dday dday
) {
}
