package KHOneTop.Thx2GettinaJob.score.adapter.in.web;

import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto.*;
import KHOneTop.Thx2GettinaJob.score.application.dto.ScoreDetail;
import KHOneTop.Thx2GettinaJob.score.application.service.ScoreService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.SCORE)
public class ScoreController {
    private final ScoreService scoreService;

    @PostMapping("")
    public CustomResponse createScore(@RequestBody CreateScoreRequest request) {
        scoreService.create(request);
        return CustomResponse.success();
    }

    @PutMapping("")
    public CustomResponse modifyScore(@RequestBody ModifyScoreRequest request) {
        scoreService.modify(request);
        return CustomResponse.success();
    }

    @ApiResponse(responseCode = "200", description = "자격증 조회", content = @Content(schema = @Schema(implementation = ScoreDetail.class)))
    @GetMapping("")
    public CustomResponse getScoreDetails(@RequestBody GetScoreRequest request) {
        List<ScoreDetail> data = scoreService.getScores(request);
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "유효한 자격증 조회", content = @Content(schema = @Schema(implementation = ScoreDetail.class)))
    @GetMapping("/valid")
    public CustomResponse getValidScoreDetails(@RequestBody GetValidScoreRequest request) {
        List<ScoreDetail> data = scoreService.getValidScores(request);
        return CustomResponse.success(data);
    }

    @DeleteMapping("")
    public CustomResponse deleteScore(@RequestBody DeleteScoreRequest request) {
        scoreService.delete(request);
        return CustomResponse.success();
    }

}