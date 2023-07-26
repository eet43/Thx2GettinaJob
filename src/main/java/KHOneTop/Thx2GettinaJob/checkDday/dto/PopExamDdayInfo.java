package KHOneTop.Thx2GettinaJob.checkDday.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "인기 자격증 D-day 일정")
@Getter
public class PopExamDdayInfo extends ExamDdayInfo{
    private Long count;

    public PopExamDdayInfo() {
        super();
    }

    public static PopExamDdayInfo create(Exam exam, Long count) {
        PopExamDdayInfo dto = new PopExamDdayInfo();
        dto.examId = exam.getId();
        dto.name = exam.getName();
        dto.issuer = exam.getIssuer();
        dto.url = exam.getUrl();
        dto.isPublic = exam.getIsPublic();
        dto.isTurn = exam.checkIsTurn();
        dto.isBookmark = false; //false 로 초기화
        dto.count = count;

        return dto;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
