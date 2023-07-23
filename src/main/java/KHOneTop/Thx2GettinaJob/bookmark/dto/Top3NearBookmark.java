package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.dto.NearExamInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "마감 얼마 안 남은 자격증")
public class Top3NearBookmark {

    @Schema(description = "시험 Id", nullable = false, example = "2L")
    public final Long examId;
    @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
    public final String name;
    @Schema(description = "시험 발급처", nullable = false, example = "한국진흥원")
    public final String issuer;
    @Schema(description = "시험 url", nullable = false, example = "https://example.com")
    public final String url;
    @Schema(description = "시험 접수 상태", nullable = false, example = "추가접수중")
    public final String status;
    @Schema(description = "D-day", nullable = false, example = "3")
    public final Long day;

    public static Top3NearBookmark toDto(NearExamInfo exam, String status, Long day) {
        return new Top3NearBookmark(exam.getId(), exam.getName(), exam.getIssuer(), exam.getUrl(), status, day);
    }
}
