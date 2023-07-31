package KHOneTop.Thx2GettinaJob.score.application.service;

import KHOneTop.Thx2GettinaJob.common.util.CheckUserUtil;
import KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto.*;
import KHOneTop.Thx2GettinaJob.score.application.port.in.ChangeScoreUseCase;
import KHOneTop.Thx2GettinaJob.score.application.port.in.ViewScoreUseCase;
import KHOneTop.Thx2GettinaJob.score.application.port.out.ChangeScorePort;
import KHOneTop.Thx2GettinaJob.score.application.port.out.LoadScorePort;
import KHOneTop.Thx2GettinaJob.score.application.dto.ScoreDetail;
import KHOneTop.Thx2GettinaJob.score.domain.Score;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreService implements ViewScoreUseCase, ChangeScoreUseCase {
    private final LoadScorePort loadScorePort;
    private final ChangeScorePort changeScorePort;
    private final CheckUserUtil checkUserUtil;
    private final ModelMapper modelMapper;

    @CacheEvict(
            value = "ScoreDetail",
            key = "#request.userId()"
    )
    @Override
    @Transactional
    public void create(CreateScoreRequest request) {
        checkUserUtil.checkValidUserId(request.userId());

        Score score = request.toDomain();
        changeScorePort.save(score);
    }

    @CacheEvict(
            value = "ScoreDetail",
            key = "#request.userId()"
    )
    @Override
    @Transactional
    public void modify(ModifyScoreRequest request) {
        checkUserUtil.checkValidUserId(request.userId());
        Score findScore = loadScorePort.load(request.scoreId());

        findScore.modifyScore(request.name(), request.score(), request.studentCode(),
                request.issuer(), request.acquisitionDate(), request.expirationDate());

        changeScorePort.save(findScore);
    }

    @CacheEvict(
            value = "ScoreDetail",
            key = "#request.userId()"
    )
    @Override
    public void delete(DeleteScoreRequest request) {
        Score findScore = loadScorePort.load(request.scoreId());
        changeScorePort.delete(findScore);
    }

    @Cacheable(
            value = "ScoreDetail",
            key = "#request.userId()"
    )
    @Override
    public List<ScoreDetail> getScores(GetScoreRequest request) {
        checkUserUtil.checkValidUserId(request.userId());
        List<Score> findScores = loadScorePort.loadAll(request.userId());

        return getScoresFormat(findScores);
    }

    @Override
    public List<ScoreDetail> getValidScores(GetValidScoreRequest request) {
        if(request.userId() == null) {
            return Collections.emptyList(); //메인화면 비로그인 대비
        }
        checkUserUtil.checkValidUserId(request.userId());

        List<Score> findScores = loadScorePort.loadValidAll(request.userId());
        return getScoresFormat(findScores);
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
