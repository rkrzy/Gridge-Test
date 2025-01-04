package com.example.demo.src.admin;


import com.example.demo.common.entity.BaseEntity;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.user.model.GetUserRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @GetMapping("/search") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Integer id,
                                                   @RequestParam(required = false) LocalDateTime createDate,
                                                   @RequestParam(required = false) BaseEntity.State state) {

        if(name == null && id == null && createDate == null && state == null){
            List<GetUserRes> getUsersRes = adminService.getUsers();
            return new BaseResponse<>(getUsersRes);
        }
        // Get Users
        List<GetUserRes> getUsersRes = adminService.getUserByCondition(name,id,createDate,state);
        return new BaseResponse<>(getUsersRes);
    }
}
