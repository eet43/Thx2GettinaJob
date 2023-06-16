package KHOneTop.Thx2GettinaJob.exam.controller;

import KHOneTop.Thx2GettinaJob.bookmark.dto.*;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.exam.dto.*;
import KHOneTop.Thx2GettinaJob.exam.service.ExamService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.EXAM)
public class ExamController {
    private final ExamService examService;

    @ApiResponse(responseCode = "200", description = "즐겨찾기 리스트 조회(검색용)", content = @Content(schema = @Schema(implementation = ExamInfo.class)))
    @GetMapping("/summary")
    public CustomResponse getExamList(@RequestBody GetExamListRequest request) {
        List<ExamInfo> data = examService.getExamList(request);
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "시험(단건), 직접입력, 조회", content = @Content(schema = @Schema(implementation = ExamDetail.class)))
    @GetMapping("/detail/single/{examId}")
    public CustomResponse getSingleExamDetail(@PathVariable Long examId) {
        ExamDetail data = examService.getSingleExamDetail(examId);
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "회차 있는 시험 일정 조회", content = @Content(schema = @Schema(implementation = BookmarkDetailOfTurn.class)))
    @GetMapping("/detail/turn/{examId}")
    public CustomResponse getBookmarkDetailOfTurn(@PathVariable Long examId) {
        List<BookmarkDetailOfTurn> data = examService.getBookmarkDetailOfTurn(examId);
        return CustomResponse.success(data);
    }

    @PutMapping("/private")
    public CustomResponse modifyPriExam(@RequestBody ModifyPriExamRequest request) {
        examService.modifyPriExam(request);
        return CustomResponse.success();
    }
}
