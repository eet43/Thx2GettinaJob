package KHOneTop.Thx2GettinaJob.bookmark.controller;

import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.*;
import KHOneTop.Thx2GettinaJob.bookmark.service.BookmarkService;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.BOOKMARK)
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping("/private")
    public CustomResponse addBookmarkPriExam(@RequestBody AddBookmarkPrivateExamRequest request) {
        bookmarkService.addBookmarkPriExam(request);
        return CustomResponse.success();
    }

    @PostMapping("/public")
    public CustomResponse addBookmarkPubExam(@RequestBody AddBookmarkPubExamRequest request) {
        bookmarkService.addBookmarkPubExam(request);
        return CustomResponse.success();
    }

    @GetMapping("/info")
    public CustomResponse getBookmarkInfo(@RequestBody GetBookmarkListRequest request) {
        List<BookmarkInfo> data = bookmarkService.getBookmarkInfo(request);
        return CustomResponse.success(data);
    }

    @GetMapping("/details/turns/{examId}")
    public CustomResponse getBookmarkDetailOfTurn(@PathVariable Long examId) {
        List<BookmarkDetailOfTurn> data = bookmarkService.getBookmarkDetailOfTurn(examId);
        return CustomResponse.success(data);
    }
}
