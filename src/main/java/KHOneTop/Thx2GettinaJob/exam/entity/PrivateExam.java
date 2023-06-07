package KHOneTop.Thx2GettinaJob.exam.entity;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.exam.dto.ModifyPriExamRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("private")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor
public class PrivateExam extends Exam{
    private Long userId;

    public void modify(ModifyPriExamRequest request) {
        this.name = request.name();
        this.issuer = request.issuer();
        this.url = request.url();

        if(this.examTimeStamp.size() > 1) {
            throw new CustomException(Codeset.INVALID_PRIEXAM_TIMESTAMP, "직접 추가 시험에 일정 개수 문제가 있습니다.");
        } else {
            this.examTimeStamp.get(0).modify(request.examDate(), request.regStartDate(), request.regEndDate(),
                    request.addRegStartDate(), request.addRegEndDate(), request.resultDate());
        }
    }

}
