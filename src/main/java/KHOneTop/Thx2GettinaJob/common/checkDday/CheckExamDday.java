package KHOneTop.Thx2GettinaJob.common.checkDday;

import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.common.checkDday.dto.ExamDdayInfo;
import KHOneTop.Thx2GettinaJob.exam.dto.HomeSearch;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class CheckExamDday {

    private final BookmarkRepository bookmarkRepository;

    public ExamDdayInfo checkPubExam(Exam exam, Long userId) {
        ExamDdayInfo examInfo = ExamDdayInfo.create(exam);
        checkIsBookmark(examInfo, userId);

        for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
            if(checkExamTime(examInfo, timeStamp)) {
                return examInfo;
            }
        }

        examInfo.setDday("접수마감", null);
        return examInfo;
    }

    public ExamDdayInfo checkWithNoAuth(Exam exam) {
        ExamDdayInfo examInfo = ExamDdayInfo.create(exam);

        for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
            if(checkExamTime(examInfo, timeStamp)) {
                return examInfo;
            }
        }

        examInfo.setDday("접수마감", null);
        return examInfo;
    }

    private void checkIsBookmark(ExamDdayInfo examInfo, Long userId) {
        boolean isBookmark = bookmarkRepository.existsByUserIdAndExamId(userId, examInfo.getId());
        examInfo.setIsBookmark(isBookmark);
    }


    private boolean checkExamTime(ExamDdayInfo examInfo, ExamTimeStamp examTimeStamp) {
        LocalDateTime reStartDate = examTimeStamp.getRegStartDate();
        LocalDateTime regEndDate = examTimeStamp.getRegEndDate();
        LocalDateTime addRegStartDate = examTimeStamp.getAddRegStartDate();
        LocalDateTime addRegEndDate = examTimeStamp.getAddRegEndDate();

        if (regEndDate == null) {
            examInfo.setDday("상시접수", null);
            return true;
        } else if (reStartDate.isAfter(LocalDateTime.now())) {
            examInfo.setDday("접수예정", calculateDday(reStartDate.toLocalDate()));
            return true;
        } else if (regEndDate.isAfter(LocalDateTime.now())) {
            examInfo.setDday("정기접수", calculateDday(regEndDate.toLocalDate()));
            return true;
        } else if (addRegStartDate != null && addRegStartDate.isAfter(LocalDateTime.now())) {
            examInfo.setDday("추가접수예정", calculateDday(addRegStartDate.toLocalDate()));
            return true;
        }
        else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
            examInfo.setDday("추가접수중", calculateDday(addRegEndDate.toLocalDate()));
            return true;
        }
        return false;
    }

    private Long calculateDday(LocalDate targetTime) {
        return ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), targetTime);
    }

}
