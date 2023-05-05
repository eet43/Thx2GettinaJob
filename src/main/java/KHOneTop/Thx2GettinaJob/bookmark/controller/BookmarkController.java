package KHOneTop.Thx2GettinaJob.bookmark.controller;

import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.AddBookmarkPrivateExamRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.AddBookmarkPubExamRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkInfo;
import KHOneTop.Thx2GettinaJob.bookmark.dto.GetBookmarkListRequest;
import KHOneTop.Thx2GettinaJob.bookmark.service.BookmarkService;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        BookmarkInfo data = bookmarkService.getBookmarkInfo(request);
        return CustomResponse.success(data);
    }
}
