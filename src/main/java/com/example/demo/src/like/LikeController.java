package com.example.demo.src.like;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.post.PostService;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.response.BaseResponseStatus.*;
import static com.example.demo.common.response.BaseResponseStatus.UNEXPECTED_ERROR;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/posts")
public class LikeController {

    private final LikeService likeService;
    private final JwtService jwtService;

    @ResponseBody
    @PostMapping("/like/{postId}")
    public BaseResponse<String> clickLike(@PathVariable Long postId)
    {
        try{
            Long userId = jwtService.getUserId();
            String result = likeService.clickLike(postId, userId);
            return new BaseResponse<>(result);
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
