package com.example.demo.src.reply;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.reply.model.ReplyCreateRequestDTO;
import com.example.demo.src.reply.model.ReplyDetailDTO;
import com.example.demo.utils.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.response.BaseResponseStatus.*;
import static com.example.demo.common.response.BaseResponseStatus.UNEXPECTED_ERROR;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/posts")
public class ReplyController {

    ReplyService replyService;
    private final JwtService jwtService;
    @Operation(summary = "댓글 작성", description = "댓글을 작성한다.")
    @ResponseBody
    @GetMapping("/reply/{postId}")
    public BaseResponse<ReplyDetailDTO> leaveReply(@PathVariable Long postId,
                                                   @RequestBody ReplyCreateRequestDTO request)
    {
        try{
            Long userId = jwtService.getUserId();
            ReplyDetailDTO replyDetailDTO = replyService.leaveReply(userId, postId, request);
            return new BaseResponse<>(replyDetailDTO);
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
