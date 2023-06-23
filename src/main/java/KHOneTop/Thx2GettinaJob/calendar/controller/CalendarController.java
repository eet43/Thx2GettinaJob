package KHOneTop.Thx2GettinaJob.calendar.controller;

import KHOneTop.Thx2GettinaJob.calendar.dto.CalendarBookmarkDetail;
import KHOneTop.Thx2GettinaJob.calendar.dto.CalendarBookmarkSearch;
import KHOneTop.Thx2GettinaJob.calendar.dto.GetCalendarBookmarkRequest;
import KHOneTop.Thx2GettinaJob.calendar.dto.GetCalendarSearchRequest;
import KHOneTop.Thx2GettinaJob.calendar.service.CalendarService;
import KHOneTop.Thx2GettinaJob.common.EndPoint;
import KHOneTop.Thx2GettinaJob.common.response.CustomResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoint.CALENDAR)
public class CalendarController {

    private final CalendarService calendarService;

    @ApiResponse(responseCode = "200", description = "캘린더 검색 즐겨찾기 리스트 조회", content = @Content(schema = @Schema(implementation = CalendarBookmarkSearch.class)))
    @GetMapping("/info")
    public CustomResponse getCalendarBookmarkInfo(@RequestBody GetCalendarSearchRequest request){
        List<CalendarBookmarkSearch> data = calendarService.getCalendarBookmarkInfo(request);
        return CustomResponse.success(data);
    }

    @ApiResponse(responseCode = "200", description = "캘린더 즐겨찾기 시험 스케줄 조회", content = @Content(schema = @Schema(implementation = CalendarBookmarkDetail.class)))
    @GetMapping("/detail") //수정필요
    public CustomResponse getCalendarBookmarkDetail(@RequestBody GetCalendarBookmarkRequest request){
        List<CalendarBookmarkDetail> data = calendarService.getCalendarBookmarkDetail(request);
        return CustomResponse.success(data);
    }
}
