package com.example.demo.src.admin;


import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.admin.model.AdminPostRes;
import com.example.demo.src.admin.model.PostDetailRes;
import com.example.demo.src.admin.model.UserDetailRes;
import com.example.demo.src.report.model.ReplyReportRes;
import com.example.demo.src.report.model.PostReportRes;
import com.example.demo.src.user.model.GetUserRes;
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

    /**
     * 4가지의 조건으로 유저를 검색
     * 생성일자를 기준으로 정렬해서 반환
     * @param name
     * @param id
     * @param createDate
     * @param state
     * @return
     */
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
    @ResponseBody
    @GetMapping("users/detail/{userId}")
    public BaseResponse<UserDetailRes> getUsersDetail(@PathVariable Long userId)
    {
        UserDetailRes userDetailRes = adminService.getUserDetail(userId);
        return new BaseResponse<>(userDetailRes);
    }
    @ResponseBody
    @GetMapping("users/block/{userId}")
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
    @ResponseBody
    @GetMapping("/post/detail/{postId}")
    public BaseResponse<PostDetailRes> getPostDetail(@PathVariable Long postId)
    {
        PostDetailRes postDetailRes = adminService.getPostDetail(postId);
        return new BaseResponse<>(postDetailRes);
    }
    @ResponseBody
    @DeleteMapping("/post/delete/{postId}")
    public BaseResponse<String> deletePost(@PathVariable("userId") Long userId){
        adminService.deletePost(userId);

        String result = "삭제 완료!!";
        return new BaseResponse<>(result);
    }

    /**여기서부터 신고관리 admin
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/report/comment")
    public BaseResponse<List<ReplyReportRes>> getReplyReport()
    {
        List<ReplyReportRes> commentReportRes = adminService.getReplyReport();

        return new BaseResponse<>(commentReportRes);
    }
    @ResponseBody
    @GetMapping("/report/post")
    public BaseResponse<List<PostReportRes>> getPostReport()
    {
        List<PostReportRes> postReportRes = adminService.getPostReport();

        return new BaseResponse<>(postReportRes);
    }
    @ResponseBody
    @DeleteMapping("/report/reply/{replyReportId}")
    public BaseResponse<String> deleteReplyReport(@PathVariable Long replyReportId)
    {
        adminService.deleteReplyReport(replyReportId);

        String result = "댓글 신고가 삭제되었습니다 !";

        return new BaseResponse<>(result);
    }
    @ResponseBody
    @DeleteMapping("/report/post/{commentReportId}")
    public BaseResponse<String> deletePostReport(@PathVariable Long commentReportId)
    {
        adminService.deletePostReport(commentReportId);

        String result = "게시글 신고가 삭제되었습니다 !";

        return new BaseResponse<>(result);
    }

}
