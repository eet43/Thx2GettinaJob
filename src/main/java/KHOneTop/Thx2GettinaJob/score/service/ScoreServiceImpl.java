package KHOneTop.Thx2GettinaJob.score.service;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.score.dto.CreateScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ModifyScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ScoreDetail;
import KHOneTop.Thx2GettinaJob.score.entity.Score;
import KHOneTop.Thx2GettinaJob.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{
    private final ScoreRepository scoreRepository;

    @Override
    public void createScore(CreateScoreRequest request) {
        Score score = request.toEntity();
        scoreRepository.save(score);
    }

    @Override
    public void modifyScore(ModifyScoreRequest request) {
        Score findScore = scoreRepository.findById(request.scoreId())
                .orElseThrow(() -> new CustomException(Codeset.INVALID_SCORE, "해당하는 자격증 데이터를 찾을 수 없습니다."));

        findScore.modifyScore(request.name(), request.score(), request.studentCode(),
                request.issuer(), request.acquisitionDate(), request.expirationDate());
    }

    @Override
    public List<ScoreDetail> getScoreDetails(Long userId) {
        return null;
    }
}
