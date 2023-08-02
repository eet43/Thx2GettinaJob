package KHOneTop.Thx2GettinaJob.checkDday;

import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.checkDday.dto.ExamDdayInfo;
import KHOneTop.Thx2GettinaJob.checkDday.dto.ExamDdayTimeInfo;
import KHOneTop.Thx2GettinaJob.checkDday.dto.PopExamDdayInfo;
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
            if(checkPubDday(examInfo, timeStamp)) {
                return examInfo;
            }
        }

        examInfo.setDday("접수마감", null);
        return examInfo;
    }

    public ExamDdayInfo checkPubExamNoAuth(Exam exam) {
        ExamDdayInfo examInfo = ExamDdayInfo.create(exam);

        for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
            if(checkPubDday(examInfo, timeStamp)) {
                return examInfo;
            }
        }

        examInfo.setDday("접수마감", null);
        return examInfo;
    }

    public ExamDdayInfo checkBookmarkExam(Exam exam) {
        ExamDdayInfo examInfo = ExamDdayInfo.create(exam);
        examInfo.setIsBookmark(true);

        for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
            if(checkPubDday(examInfo, timeStamp)) {
                return examInfo;
            }
        }

        examInfo.setDday("접수마감", null);
        return examInfo;
    }

    public ExamDdayInfo checkPriExam(Exam exam) {
        ExamDdayInfo examInfo = ExamDdayInfo.create(exam);
        examInfo.setIsBookmark(true);
        for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
            if(checkPriDday(examInfo, timeStamp)) {
                return examInfo;
            }
        }

        examInfo.setDday("접수마감", null);
        return examInfo;
    }

    public PopExamDdayInfo checkPopExamAuth(Exam exam, Long count, Long userId) {
        PopExamDdayInfo examInfo = PopExamDdayInfo.create(exam, count);
        checkIsBookmark(examInfo, userId);

        for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
            if(checkPubDday(examInfo, timeStamp)) {
                return examInfo;
            }
        }

        examInfo.setDday("접수마감", null);
        return examInfo;
    }

    public PopExamDdayInfo checkPopExamAuthLess(Exam exam, Long count) {
        PopExamDdayInfo examInfo = PopExamDdayInfo.create(exam, count);

        for (ExamTimeStamp timeStamp : exam.getExamTimeStamp()) {
            if(checkPubDday(examInfo, timeStamp)) {
                return examInfo;
            }
        }

        examInfo.setDday("접수마감", null);
        return examInfo;
    }

    public ExamDdayTimeInfo checkExamTime(ExamTimeStamp examTimeStamp) {
        ExamDdayTimeInfo examTimeInfo = ExamDdayTimeInfo.create(examTimeStamp);

        LocalDateTime regStartDate = examTimeStamp.getRegStartDate();
        LocalDateTime regEndDate = examTimeStamp.getRegEndDate();
        LocalDateTime addRegStartDate = examTimeStamp.getAddRegStartDate();
        LocalDateTime addRegEndDate = examTimeStamp.getAddRegEndDate();

        if (regStartDate.isAfter(LocalDateTime.now())) {
            examTimeInfo.setDday("접수예정", null);
        } else if (regEndDate.isAfter(LocalDateTime.now())) {
            examTimeInfo.setDday("정기접수", calculateDday(regEndDate.toLocalDate()));

        } else if (addRegStartDate != null && addRegStartDate.isAfter(LocalDateTime.now())) {
            examTimeInfo.setDday("접수예정", null);
        } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
            examTimeInfo.setDday("추가접수", calculateDday(addRegEndDate.toLocalDate()));
        } else if(regEndDate.isBefore(LocalDateTime.now())) {
            examTimeInfo.setDday("접수마감", null);
        }

        return examTimeInfo;
    }

    private void checkIsBookmark(ExamDdayInfo examInfo, Long userId) {
        boolean isBookmark = bookmarkRepository.existsByUserIdAndExamId(userId, examInfo.getExamId());
        examInfo.setIsBookmark(isBookmark);
    }


    private boolean checkPubDday(ExamDdayInfo examInfo, ExamTimeStamp examTimeStamp) {
        LocalDateTime regStartDate = examTimeStamp.getRegStartDate();
        LocalDateTime regEndDate = examTimeStamp.getRegEndDate();
        LocalDateTime addRegStartDate = examTimeStamp.getAddRegStartDate();
        LocalDateTime addRegEndDate = examTimeStamp.getAddRegEndDate();

        if (regEndDate == null) {
            examInfo.setDday("상시접수", null);
            return true;
        }
        return checkCommonDday(examInfo, regStartDate, regEndDate, addRegStartDate, addRegEndDate);
    }

    private boolean checkPriDday(ExamDdayInfo examInfo, ExamTimeStamp examTimeStamp) {
        LocalDateTime regStartDate = examTimeStamp.getRegStartDate();
        LocalDateTime regEndDate = examTimeStamp.getRegEndDate();
        LocalDateTime addRegStartDate = examTimeStamp.getAddRegStartDate();
        LocalDateTime addRegEndDate = examTimeStamp.getAddRegEndDate();

        if (regStartDate == null || regEndDate == null) {
            examInfo.setDday("기간미입력", null);
            return true;
        }
        return checkCommonDday(examInfo, regStartDate, regEndDate, addRegStartDate, addRegEndDate);
    }

    private boolean checkCommonDday(ExamDdayInfo examInfo, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime addRegStartDate, LocalDateTime addRegEndDate) {
        if (startDate.isAfter(LocalDateTime.now())) {
            examInfo.setDday("접수예정", null);
            return true;
        } else if (endDate.isAfter(LocalDateTime.now())) {
            examInfo.setDday("정기접수", calculateDday(endDate.toLocalDate()));
            return true;
        } else if (addRegStartDate != null && addRegStartDate.isAfter(LocalDateTime.now())) {
            examInfo.setDday("접수예정", null);
            return true;
        } else if (addRegEndDate != null && addRegEndDate.isAfter(LocalDateTime.now())) {
            examInfo.setDday("추가접수", calculateDday(addRegEndDate.toLocalDate()));
            return true;
        }
        return false;
    }


    private Long calculateDday(LocalDate targetTime) {
        return ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), targetTime);
    }

}
