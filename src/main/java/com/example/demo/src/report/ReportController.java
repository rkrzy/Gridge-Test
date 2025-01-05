package com.example.demo.src.report;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.post.model.PostDTO;
import com.example.demo.src.report.model.ReportReq;
import com.example.demo.utils.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.response.BaseResponseStatus.*;
import static com.example.demo.common.response.BaseResponseStatus.UNEXPECTED_ERROR;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/report")
public class ReportController {

    private final ReportService reportService;
    private final JwtService jwtService;
    @Operation(summary = "댓글 신고 등록")
    @ResponseBody
    @PostMapping("/reply/{replyId}")
    public BaseResponse<String> registerReplyReport(@PathVariable Long replyId,
                                      @RequestBody ReportReq reportReq){
        try{
            Long userId = jwtService.getUserId();
            reportService.registerReplyReport(userId, replyId, reportReq.getContent());
            return new BaseResponse<>("댓글 신고가 완료되었습니다.");
        }catch(BaseException e)
        {
            if(e.getStatus() == EMPTY_JWT)
                return new BaseResponse<>(USERS_NOT_LOGIN);
            else
                return new BaseResponse<>(INVALID_JWT);
        }
        catch(Exception e) {
            // 기타 예상치 못한 예외도 잡아서 응답 (선택적)
            return new BaseResponse<>(UNEXPECTED_ERROR);
        }
    }
    @Operation(summary = "게시물 신고 등록")
    @ResponseBody
    @PostMapping("/post/{postId}")
    public BaseResponse<String> registerPostReport(@PathVariable Long postId,
                                      @RequestBody ReportReq reportReq){
        try{
            Long userId = jwtService.getUserId();
            reportService.registerPostReport(userId, postId, reportReq.getContent());
            return new BaseResponse<>("게시물 신고가 완료되었습니다");

        }catch(BaseException e)
        {
            if(e.getStatus() == EMPTY_JWT)
                return new BaseResponse<>(USERS_NOT_LOGIN);
            else
                return new BaseResponse<>(INVALID_JWT);
        }
        catch(Exception e) {
            // 기타 예상치 못한 예외도 잡아서 응답 (선택적)
            return new BaseResponse<>(UNEXPECTED_ERROR);
        }
    }
}
