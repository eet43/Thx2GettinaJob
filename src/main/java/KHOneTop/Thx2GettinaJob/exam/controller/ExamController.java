package KHOneTop.Thx2GettinaJob.exam.controller;

import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkSummary;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.exam.dto.ExamInfo;
import KHOneTop.Thx2GettinaJob.exam.dto.GetExamListRequest;
import KHOneTop.Thx2GettinaJob.exam.service.ExamService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.EXAM)
public class ExamController {
    private final ExamService examService;

    @ApiResponse(responseCode = "200", description = "즐겨찾기 리스트 조회(검색용)", content = @Content(schema = @Schema(implementation = BookmarkSummary.class)))
    @GetMapping("/summary")
    public CustomResponse getExamList(@RequestBody GetExamListRequest request) {
        List<ExamInfo> data = examService.getExamList(request);
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "즐겨찾기 리스트 조회(검색용)", content = @Content(schema = @Schema(implementation = BookmarkSummary.class)))
    @GetMapping("/summary")
    public CustomResponse getExamList(@RequestBody GetExamListRequest request) {
        List<ExamInfo> data = examService.getExamList(request);
        return CustomResponse.success(data);
    }
}
