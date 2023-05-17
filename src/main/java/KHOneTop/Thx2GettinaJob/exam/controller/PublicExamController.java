package KHOneTop.Thx2GettinaJob.exam.controller;

import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.exam.dto.PublicExamInfo;
import KHOneTop.Thx2GettinaJob.exam.service.PublicExamService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 1. 공적 시험 리스트 조회 (검색할 때 사용)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.PUB_EXAM)
public class PublicExamController {
    private final PublicExamService publicExamService;

    @GetMapping("")
    public CustomResponse getPublicExamList() {
        List<PublicExamInfo> data = publicExamService.getPublicExamList();
        return CustomResponse.success(data);
    }
}
