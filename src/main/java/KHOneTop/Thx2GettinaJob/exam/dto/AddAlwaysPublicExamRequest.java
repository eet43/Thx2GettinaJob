package KHOneTop.Thx2GettinaJob.exam.dto;

import lombok.Data;

@Data
public record AddAlwaysPublicExamRequest(
        String name,
        String issuer,
        String url
) {
}
