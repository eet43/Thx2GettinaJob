package KHOneTop.Thx2GettinaJob.score.service;

import KHOneTop.Thx2GettinaJob.score.dto.*;

import java.util.List;

public interface ScoreService {
    void createScore(CreateScoreRequest request);

    void modifyScore(ModifyScoreRequest request);

    List<ScoreDetail> getScoreDetails(GetScoreRequest request);

    List<ScoreDetail> getValidScoreDetails(GetValidScoreRequest request);

}
