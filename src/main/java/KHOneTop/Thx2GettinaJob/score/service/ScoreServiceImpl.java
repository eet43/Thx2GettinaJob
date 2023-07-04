package KHOneTop.Thx2GettinaJob.score.service;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.score.dto.CreateScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.GetScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ModifyScoreRequest;
import KHOneTop.Thx2GettinaJob.score.dto.ScoreDetail;
import KHOneTop.Thx2GettinaJob.score.entity.Score;
import KHOneTop.Thx2GettinaJob.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{
    private final ScoreRepository scoreRepository;

    private final CheckUserUtil checkUserUtil;
    private final ModelMapper modelMapper;

    @CacheEvict(
            value = "ScoreDetail",
            key = "#request.userId()"
    )
    @Override
    @Transactional
    public void createScore(CreateScoreRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        Score score = request.toEntity();
        scoreRepository.save(score);
    }

    @CacheEvict(
            value = "ScoreDetail",
            key = "#request.userId()"
    )
    @Override
    @Transactional
    public void modifyScore(ModifyScoreRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        Score findScore = scoreRepository.findById(request.scoreId())
                .orElseThrow(() -> new CustomException(Codeset.INVALID_SCORE, "해당하는 자격증 데이터를 찾을 수 없습니다."));

        findScore.modifyScore(request.name(), request.score(), request.studentCode(),
                request.issuer(), request.acquisitionDate(), request.expirationDate());
    }

    @Cacheable(
            value = "ScoreDetail",
            key = "#userId"
    )
    @Override
    public List<ScoreDetail> getScoreDetails(GetScoreRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        Optional<List<Score>> findScores = scoreRepository.findAllByUserId(request.userId());
        return getScoresFormat(findScores);
    }

    @Override
    public List<ScoreDetail> getValidScoreDetails(GetScoreRequest request) {
        if(request.userId() == null) {
            return Collections.emptyList();
        }

        checkUserUtil.checkValidUserId(request.userId());

        Optional<List<Score>> findScores = scoreRepository.findAllByUserIdAndIsEffective(request.userId());
        return getScoresFormat(findScores);
    }

    private List<ScoreDetail> getScoresFormat(Optional<List<Score>> findScores) {
        List<ScoreDetail> result = new ArrayList<>();

        if(findScores.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<Score> getScores = findScores.get();
            for(Score score : getScores) {
                ScoreDetail dto = modelMapper.map(score, ScoreDetail.class);
                if(score.getExpirationDate() != null && score.getIsEffective()) {
                    dto.setDay(ChronoUnit.DAYS.between(LocalDate.now(), score.getExpirationDate()));
                }
                result.add(dto);
            }
            return result;
        }
    }
}
