package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import KHOneTop.Thx2GettinaJob.exam.entity.PrivateExam;
import KHOneTop.Thx2GettinaJob.exam.entity.PublicExam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExamRepositoryTest {
    @Autowired
    ExamRepository examRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findPublicOrOwnedExamsTest() throws Exception {
        // given
        Long userId = 1L;
        PublicExam publicExam = PublicExam.builder().id(1L).name("Exam1").build();
        PrivateExam privateExam = PrivateExam.builder().id(2L).name("Exam2").userId(1L).build();
        privateExam.setUserId(1L);
        entityManager.persist(publicExam);
        entityManager.persist(privateExam);

        // when
        List<Exam> result = examRepository.findPublicOrOwnedExams(userId);

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(publicExam));
        assertTrue(result.contains(privateExam));
    }
}