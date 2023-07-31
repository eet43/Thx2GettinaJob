package KHOneTop.Thx2GettinaJob.score.application.port.in;

import KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto.CreateScoreRequest;
import KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto.DeleteScoreRequest;
import KHOneTop.Thx2GettinaJob.score.adapter.in.web.dto.ModifyScoreRequest;

public interface ChangeScoreUseCase {
    void create(CreateScoreRequest request);
    void modify(ModifyScoreRequest request);
    void delete(DeleteScoreRequest request);
}
