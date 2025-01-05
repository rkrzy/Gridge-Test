package com.example.demo.src.post;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.post.model.PostDTO;
import com.example.demo.src.post.model.PostDetailDTO;
import com.example.demo.src.reply.model.ReplyDetailDTO;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    @DeleteMapping("/delete/{postId}")
    public BaseResponse<String> deletePost(@PathVariable("postId")Long postId){

        try{
            Long userId = jwtService.getUserId();
            postService.deletePost(postId);
            String result = "삭제 완료!!";
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
    @ResponseBody
    @PostMapping("/register")
    public BaseResponse<String> register(
            @RequestParam String content
    )
    {
        try{
            Long userId = jwtService.getUserId();
            postService.createPost(userId, content);
            return new BaseResponse<>("게시물이 정상적으로 등록!!");
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
    @GetMapping("{postId}")
    public BaseResponse<PostDetailDTO> getPostDetail(@PathVariable Long postId)
    {
        try{
            Long userId = jwtService.getUserId();
            PostDetailDTO postDetailDTO = postService.getPostDetail(postId);
            return new BaseResponse<>(postDetailDTO);
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
    @PostMapping("revise/{postId}")
    public BaseResponse<String> revisePost(
            @PathVariable Long postId,
            @RequestParam String content
    ){
        try{
            Long userId = jwtService.getUserId();
            postService.revisePost(postId,content);
            return new BaseResponse<>("게시물이 정상적으로 수정되었습니다.");
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
