package KHOneTop.Thx2GettinaJob.score.application.port.in;

import KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto.GetScoreRequest;
import KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto.GetValidScoreRequest;
import KHOneTop.Thx2GettinaJob.score.application.dto.ScoreDetail;

import java.util.List;

public interface ViewScoreUseCase {
    List<ScoreDetail> getScores(GetScoreRequest request);
    List<ScoreDetail> getValidScores(GetValidScoreRequest request);
}
