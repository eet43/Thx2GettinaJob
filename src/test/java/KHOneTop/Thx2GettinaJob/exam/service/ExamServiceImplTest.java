package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import KHOneTop.Thx2GettinaJob.bookmark.service.BookmarkServiceImpl;
import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.exam.dto.ExamInfo;
import KHOneTop.Thx2GettinaJob.exam.dto.GetExamListRequest;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import KHOneTop.Thx2GettinaJob.exam.entity.PublicExam;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({CheckUserUtil.class, ExamRepository.class, BookmarkRepository.class, ExamServiceImpl.class})
class ExamServiceImplTest {
    @MockBean
    CheckUserUtil checkUserUtil;

    @MockBean
    ExamRepository examRepository;

    @MockBean
    BookmarkRepository bookmarkRepository;

    @Autowired
    ExamServiceImpl examService;

    @Test
    @DisplayName("즐겨찾기 화면 Exam 리스트 검색 Test")
    void getExamList() throws Exception {
        //given
        GetExamListRequest request = new GetExamListRequest(1L);
        List<Exam> exams = new ArrayList<>();
        exams.add(PublicExam.builder().id(1L).name("Exam1").build());
        exams.add(PrivateExam.builder().id(2L).name("Exam2").userId(1L).build());

        //when
        when(examRepository.findPublicOrOwnedExams(request.userId()))
                .thenReturn(exams);
        when(bookmarkRepository.existsByUserIdAndExamId(request.userId(), 1L)).thenReturn(true);
        when(bookmarkRepository.existsByUserIdAndExamId(request.userId(), 2L)).thenReturn(false);

        List<ExamInfo> result = examService.getExamList(request);

        //then
        assertEquals(2, result.size());
        assertTrue(result.get(0).isBookmark());
        assertFalse(result.get(1).isBookmark());
    }

}