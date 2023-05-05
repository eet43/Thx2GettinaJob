package KHOneTop.Thx2GettinaJob.bookmark.dto;

import KHOneTop.Thx2GettinaJob.exam.entity.ExamTimeStamp;

import java.util.List;

public record BookmarkInfo(
        List<PrivateExamInfo> privateExams,
        List<PublicExamInfo> publicExams
) {
}
