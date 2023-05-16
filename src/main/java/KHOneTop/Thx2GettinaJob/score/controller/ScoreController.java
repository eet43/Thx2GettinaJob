package KHOneTop.Thx2GettinaJob.score.controller;

import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import KHOneTop.Thx2GettinaJob.score.dto.CreateScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.GetScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ModifyScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ScoreDetail;
import KHOneTop.Thx2GettinaJob.score.service.ScoreService;
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
        scoreService.createScore(request);
        return CustomResponse.success();
    }

    @PutMapping("")
    public CustomResponse modifyScore(@RequestBody ModifyScoreRequest request) {
        scoreService.modifyScore(request);
        return CustomResponse.success();
    }

    @GetMapping("")
    public CustomResponse getScoreDetails(@RequestBody GetScoreRequest request) {
        List<ScoreDetail> data = scoreService.getScoreDetails(request.userId());
        return CustomResponse.success(data);
    }

}
