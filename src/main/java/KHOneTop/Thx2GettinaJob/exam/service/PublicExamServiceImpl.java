package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.exam.dto.PublicExamInfo;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicExamServiceImpl implements PublicExamService{

    private final ExamRepository examRepository;
    @Override
    public List<PublicExamInfo> getPublicExamList() {
        return examRepository.findPublicExam();
    }
}
