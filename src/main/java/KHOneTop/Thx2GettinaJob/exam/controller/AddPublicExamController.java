package KHOneTop.Thx2GettinaJob.exam.controller;

import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.exam.dto.AddAlwaysPublicExamRequest;
import KHOneTop.Thx2GettinaJob.exam.service.NewExamAddService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.EXAM_ADD)
public class AddPublicExamController {
    private final NewExamAddService addService;

    @PostMapping("/all-times")
    public CustomResponse addAllTimesExam(@RequestBody AddAlwaysPublicExamRequest request) {
        addService.addAllTimesExam(request);
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

    @PostMapping("/korean")
    public CustomResponse addKoreanExam() {
        addService.addKoreanExam();
        return CustomResponse.success();
    }

    @PostMapping("/toeic-speaking")
    public CustomResponse addToeicSpeackingExam() {
        addService.addToeicSpeackingExam();
        return CustomResponse.success();
    }

    @PostMapping("/hsk")
    public CustomResponse addHskExam() {
        addService.addHskExam();
        return CustomResponse.success();
    }

}
