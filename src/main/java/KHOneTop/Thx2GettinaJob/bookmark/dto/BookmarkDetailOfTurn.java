package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;

import java.time.LocalDateTime;

public record BookmarkDetailOfTurn(
        String turn,
        LocalDateTime examDate,
        LocalDateTime regStartDate,
        LocalDateTime regEndDate,
        LocalDateTime addRegStartDate,
        LocalDateTime addRegEndDate,
        LocalDateTime resultDate,
        String status,
        Long day
) {
    public static BookmarkDetailOfTurn fromEntity(ExamTimeStamp timeStamp, String status, Long day) {
        return new BookmarkDetailOfTurn(timeStamp.getTurn(), timeStamp.getExamDate(), timeStamp.getRegStartDate(), timeStamp.getRegEndDate(),
                timeStamp.getAddRegStartDate(), timeStamp.getAddRegEndDate(), timeStamp.getResultDate(), status, day);
    }
}
