package KHOneTop.Thx2GettinaJob.bookmark.controller;

import KHOneTop.Thx2GettinaJob.auth.dto.LoginToken;
import KHOneTop.Thx2GettinaJob.auth.dto.SendToEmailRequest;
import KHOneTop.Thx2GettinaJob.bookmark.dto.*;
import KHOneTop.Thx2GettinaJob.bookmark.service.BookmarkService;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 1. 사적 시험 즐겨찾기 등록
 * 2. 공적 시험 즐겨찾기 등록
 * 3. 즐겨찾기 등록한 시험 조회
 * 4. 회차있는 시험에 대해 일정 리스트 조회
 *
 */
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

    @DeleteMapping("")
    public CustomResponse deleteBookmarkPubExam(@RequestBody DeleteBookmarkPubExamRequest request) {
        bookmarkService.deleteBookmarkPubExam(request);
        return CustomResponse.success();
    }

    @ApiResponse(responseCode = "200", description = "즐겨찾기 리스트 조회(검색용)", content = @Content(schema = @Schema(implementation = BookmarkSummary.class)))
    @GetMapping("/summary")
    public CustomResponse getBookmarkSummary(@RequestBody GetBookmarkListRequest request) {
        List<BookmarkSummary> data = bookmarkService.getBookmarkSummary(request);
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "메인화면 즐겨찾기 시험 조회 성공", content = @Content(schema = @Schema(implementation = BookmarkInfo.class)))
    @GetMapping("/info")
    public CustomResponse getBookmarkInfo(@RequestBody GetBookmarkListRequest request) {
        List<BookmarkInfo> data = bookmarkService.getBookmarkInfo(request);
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "인기있는 자격증 조회 성공", content = @Content(schema = @Schema(implementation = Top5PopBookmark.class)))
    @GetMapping("/pop")
    public CustomResponse getTop5PopBookmarks() {
        List<Top5PopBookmark> data = bookmarkService.getTop5PopBookmarks();
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "마감 얼마 안남은 즐겨찾기 시험 조회 성공", content = @Content(schema = @Schema(implementation = Top3NearBookmark.class)))
    @GetMapping("/near") //수정필요
    public CustomResponse getTop3NearBookmarks(){
        List<Top3NearBookmark> data = bookmarkService.getTop3NearBookmarks();
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "캘린더 검색 즐겨찾기 리스트 조회", content = @Content(schema = @Schema(implementation = CalendarBookmarkSearch.class)))
    @GetMapping("/calendar/info") //수정필요
    public CustomResponse getCalendarBookmarkInfo(@RequestBody GetBookmarkListRequest request){
        List<CalendarBookmarkSearch> data = bookmarkService.getCalendarBookmarkInfo(request);
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "캘린더 즐겨찾기 시험 스케줄 조회", content = @Content(schema = @Schema(implementation = CalendarBookmarkSearch.class)))
    @GetMapping("/calendar/detail") //수정필요
    public CustomResponse getCalendarBookmarkDetail(@RequestBody GetCalendarBookmarkRequest request){
        List<CalendarBookmarkDetail> data = bookmarkService.getCalendarBookmarkDetail(request);
        return CustomResponse.success(data);
    }



}
