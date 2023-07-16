package KHOneTop.Thx2GettinaJob.score.service;

import KHOneTop.Thx2GettinaJob.common.response.Codeset;
import KHOneTop.Thx2GettinaJob.common.response.CustomException;
import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.score.dto.*;
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
            key = "#request.userId()"
    )
    @Override
    public List<ScoreDetail> getScoreDetails(GetScoreRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        List<Score> findScores = scoreRepository.findAllByUserId(request.userId());
        return getScoresFormat(findScores);
    }

    @Override
    public List<ScoreDetail> getValidScoreDetails(GetValidScoreRequest request) {
        if(request.userId() == null) {
            return Collections.emptyList();
        }

        checkUserUtil.checkValidUserId(request.userId());

        List<Score> findScores = scoreRepository.findAllByUserIdAndIsEffectiveTrue(request.userId());
        return getScoresFormat(findScores);
    }

    @Override
    public void deleteScore(DeleteScoreRequest request) {
        Score findScore = scoreRepository.findById(request.scoreId())
                .orElseThrow(() -> new CustomException(Codeset.INVALID_SCORE, "해당하는 자격증 데이터를 찾을 수 없습니다."));
        scoreRepository.delete(findScore);
    }

    private List<ScoreDetail> getScoresFormat(List<Score> findScores) {
        List<ScoreDetail> result = new ArrayList<>();

        if(findScores.isEmpty()) {
            return Collections.emptyList();
        } else {
            for(Score score : findScores) {
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
