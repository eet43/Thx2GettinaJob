package KHOneTop.Thx2GettinaJob.exam.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NearExamInfo {
    private final Long id;
    private final String name;
    private final String issuer;
    private final LocalDateTime regEndDate;
    private final LocalDateTime addRegEndDate;
}
