package KHOneTop.Thx2GettinaJob.exam.dto;

import lombok.Data;

public record AddAlwaysPublicExamRequest(
        String name,
        String issuer,
        String url
) {
}
