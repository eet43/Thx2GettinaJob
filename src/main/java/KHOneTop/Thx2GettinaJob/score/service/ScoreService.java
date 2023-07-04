package KHOneTop.Thx2GettinaJob.score.service;

import KHOneTop.Thx2GettinaJob.score.dto.CreateScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.GetScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ModifyScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ScoreDetail;

import java.util.List;

public interface ScoreService {
    void createScore(CreateScoreRequest request);

    void modifyScore(ModifyScoreRequest request);

    List<ScoreDetail> getScoreDetails(GetScoreRequest request);

    List<ScoreDetail> getValidScoreDetails(GetScoreRequest request);

}
