package KHOneTop.Thx2GettinaJob.common.checkDday.dto;

import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkInfo;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "자격증 D-day 일정")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExamDdayInfo {
    @Schema(description = "시험 Id", nullable = false, example = "2L")
    private Long id;
    @Schema(description = "시험 이름", nullable = false, example = "TOEIC")
    private String name;
    @Schema(description = "시험 발급처", nullable = false, example = "한국진흥원")
    private String issuer;
    @Schema(description = "홈페이지", nullable = false, example = "http~")
    private String url;
    @Schema(description = "직접 등록인지 공개적 시험인지", nullable = false, example = "false")
    private Boolean isPublic;
    @Schema(description = "회차가 있는 시험인지", nullable = false, example = "false")
    private Boolean isTurn;
    @Schema(description = "즐겨찾기 된 시험인지", nullable = false, example = "false")
    private Boolean isBookmark;
    @Schema(description = "시험 접수 상태", nullable = false, example = "추가접수중")
    private String status;
    @Schema(description = "D-day", example = "3")
    private Long day;

    public static ExamDdayInfo create(Exam exam) {
        ExamDdayInfo dto = new ExamDdayInfo();
        dto.id = exam.getId();
        dto.name = exam.getName();
        dto.issuer = exam.getIssuer();
        dto.url = exam.getUrl();
        dto.isPublic = exam.getIsPublic();
        dto.isTurn = exam.checkIsTurn();
        dto.isBookmark = false; //false 로 초기화
        return dto;
    }

    public void setIsBookmark(Boolean isBookmark) {
        this.isBookmark = isBookmark;
    }

    public void setDday(String status, Long day) {
        this.status = status;
        this.day = day;
    }
}
