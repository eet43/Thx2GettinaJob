package KHOneTop.Thx2GettinaJob.exam.service;

import KHOneTop.Thx2GettinaJob.exam.dto.AddPrivateExamRequest;
import KHOneTop.Thx2GettinaJob.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrivateExamServiceImpl implements PrivateExamService{

    private final ExamRepository examRepository;
    @Override
    public void addExam(AddPrivateExamRequest request) {
        examRepository.save(request.toEntity());
    }
}
