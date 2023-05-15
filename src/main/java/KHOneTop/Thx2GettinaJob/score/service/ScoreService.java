package KHOneTop.Thx2GettinaJob.score.service;

import KHOneTop.Thx2GettinaJob.score.dto.CreateScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ModifyScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ScoreDetail;

import java.util.List;

public interface ScoreService {
    public void createScore(CreateScoreRequest request);
    public void modifyScore(ModifyScoreRequest request);
    public List<ScoreDetail> getScoreDetails(Long userId);
}
