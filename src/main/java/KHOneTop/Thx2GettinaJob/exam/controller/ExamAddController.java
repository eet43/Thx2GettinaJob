package KHOneTop.Thx2GettinaJob.exam.controller;

import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.exam.entity.Category;
import KHOneTop.Thx2GettinaJob.exam.service.NewExamAddService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.EXAM_ADD)
public class ExamAddController {
    private final NewExamAddService addService;

    @PostMapping("/all-times")
    public CustomResponse addAllTimesExam(@PathVariable Category category) {
        addService.addAllTimesExam(category);
        return CustomResponse.success();
    }

    @PostMapping("/toeic")
    public CustomResponse addToeicExam() {
        addService.addToeicExam();
        return CustomResponse.success();
    }

    @PostMapping("/afpk")
    public CustomResponse addAfpkExam() {
        addService.addAfpkExam();
        return CustomResponse.success();
    }
}
