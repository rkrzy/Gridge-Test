package com.example.demo.src.post;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.post.model.PostDTO;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.example.demo.common.response.BaseResponseStatus.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/posts")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<PostDTO>> getPosts(
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer size
    )
    {
        try{
            Long userId = jwtService.getUserId();
            List<PostDTO> posts = postService.getPostList(pageIndex, size);
            return new BaseResponse<>(posts);
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
    @ResponseBody
    @PostMapping("/like/{postId}")
    public BaseResponse<String> clickLike(@PathVariable Long postId)
    {
        try{
            Long userId = jwtService.getUserId();
            String result = postService.clickLike(postId, userId);
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
