package com.example.demo.src.admin;


import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.admin.model.AdminPostRes;
import com.example.demo.src.admin.model.PostDetailRes;
import com.example.demo.src.admin.model.UserDetailRes;
import com.example.demo.src.post.PostService;
import com.example.demo.src.reply.ReplyService;
import com.example.demo.src.report.model.ReplyReportRes;
import com.example.demo.src.report.model.PostReportRes;
import com.example.demo.src.user.model.GetUserRes;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.common.entity.BaseEntity.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/admin")
public class AdminController {

    private final AdminService adminService;
    private final PostService postService;
    private final ReplyService replyService;
    /**
     * 4가지의 조건으로 유저를 검색
     * 생성일자를 기준으로 정렬해서 반환
     * @param name
     * @param id
     * @param createDate
     * @param state
     * @return
     */
    @Operation(summary = "유저 검색", description = "등록된 유저를 검색한다")
    @ResponseBody
    @GetMapping("users/search") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Long id,
                                                   @RequestParam(required = false) LocalDateTime createDate,
                                                   @RequestParam(required = false) State state) {

        if(name == null && id == null && createDate == null && state == null){
            List<GetUserRes> getUsersRes = adminService.getUsers();
            return new BaseResponse<>(getUsersRes);
        }
        // Get Users
        List<GetUserRes> getUsersRes = adminService.getUserByCondition(name,id,createDate,state);
        return new BaseResponse<>(getUsersRes);
    }

    /**
     * 유저의 모든 정보를 볼 수 있는 상세 조회
     * @param userId
     * @return
     */
    @Operation(summary = "유저 상세 조회", description = "유저 아이디를 받아서 유저 상세 조회")
    @ResponseBody
    @GetMapping("users/detail/{userId}")
    public BaseResponse<UserDetailRes> getUsersDetail(@PathVariable Long userId)
    {
        UserDetailRes userDetailRes = adminService.getUserDetail(userId);
        return new BaseResponse<>(userDetailRes);
    }
    @Operation(summary = "계정 차단", description = "유저의 계정을 차단")
    @ResponseBody
    @PostMapping("users/block/{userId}")
    public BaseResponse<String> blockUser(@PathVariable Long userId){

        adminService.blockUser(userId);

        String str = "유저의 계정이 차단되었습니다.";

        return new BaseResponse<>(str);
    }

    /**여기서 부터 피드관리 admin
     *
     * @param id
     * @param createdTime
     * @param state
     * @return
     */
    @Operation(summary = "게시물 검색", description = "다양한 조건을 입력받아서 게시물 검색.")
    @ResponseBody
    @GetMapping("post/search")
    public BaseResponse<List<AdminPostRes>> getPosts(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) LocalDateTime createdTime,
                                               @RequestParam(required = false) State state)
    {
        if(id == null && createdTime == null && state == null){
            List<AdminPostRes> adminPostRes = adminService.getPosts();
            return new BaseResponse<>(adminPostRes);
        }
        List<AdminPostRes> adminPostRes = adminService.getPostsByCondition(id, createdTime, state);
        return new BaseResponse<>(adminPostRes);
    }
    @Operation(summary = "게시물 확인", description = "게시물의 디테일한 부분을 확인")
    @ResponseBody
    @GetMapping("/post/detail/{postId}")
    public BaseResponse<PostDetailRes> getPostDetail(@PathVariable Long postId)
    {
        PostDetailRes postDetailRes = adminService.getPostDetail(postId);
        return new BaseResponse<>(postDetailRes);
    }
    @Operation(summary = "게시물 삭제", description = "해당하는 게시물을 삭제")
    @ResponseBody
    @DeleteMapping("/post/delete/{postId}")
    public BaseResponse<String> deletePost(@PathVariable("postId") Long postId){

        postService.deletePost(postId);

        String result = "삭제 완료!!";
        return new BaseResponse<>(result);
    }

    /**여기서부터 신고관리 admin
     *
     * @return
     */
    @Operation(summary = "신고된 댓글 확인", description = "신고받은 댓글 전체확인.")
    @ResponseBody
    @GetMapping("/report/reply")
    public BaseResponse<List<ReplyReportRes>> getReplyReport()
    {
        List<ReplyReportRes> commentReportRes = adminService.getReplyReport();

        return new BaseResponse<>(commentReportRes);
    }
    @Operation(summary = "신고된 게시물 확인", description = "신고받은 게시물 전체확인.")
    @ResponseBody
    @GetMapping("/report/post")
    public BaseResponse<List<PostReportRes>> getPostReport()
    {
        List<PostReportRes> postReportRes = adminService.getPostReport();

        return new BaseResponse<>(postReportRes);
    }
    @Operation(summary = "댓글신고 삭제", description = "댓글 신고 삭제")
    @ResponseBody
    @DeleteMapping("/report/reply/{replyReportId}")
    public BaseResponse<String> deleteReplyReport(@PathVariable Long replyReportId)
    {
        adminService.deleteReplyReport(replyReportId);

        String result = "댓글 신고가 삭제되었습니다 !";

        return new BaseResponse<>(result);
    }
    @Operation(summary = "게시물신고 삭제", description = "게시물 신고 삭제.")
    @ResponseBody
    @DeleteMapping("/report/post/{postReportId}")
    public BaseResponse<String> deletePostReport(@PathVariable Long postReportId)
    {
        adminService.deletePostReport(postReportId);

        String result = "게시글 신고가 삭제되었습니다 !";

        return new BaseResponse<>(result);
    }

    @Operation(summary = "신고된 게시물 삭제", description = "게시물 아이디를 받아와서 삭제한다.")
    @ResponseBody
    @DeleteMapping("/report/reported-post/{postId}")
    public BaseResponse<String> deleteReportedPost(@PathVariable Long postId)
    {
        postService.deletePost(postId);

        String result = "삭제 완료!!";
        return new BaseResponse<>(result);
    }
    @Operation(summary = "신고된 댓글 삭제", description = "댓글 아이디를 받아와서 삭제한다.")
    @ResponseBody
    @DeleteMapping("/report/reported-reply/{replyId}")
    public BaseResponse<String> deleteReportedReply(@PathVariable Long replyId)
    {
        replyService.deleteReply(replyId);

        String result = "삭제 완료!!";
        return new BaseResponse<>(result);
    }

}
