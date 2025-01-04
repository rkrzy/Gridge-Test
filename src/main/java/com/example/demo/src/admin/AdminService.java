package com.example.demo.src.admin;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.admin.model.AdminPostRes;
import com.example.demo.src.admin.model.UserDetailRes;
import com.example.demo.src.post.PostRepository;
import com.example.demo.src.post.entity.Post;
import com.example.demo.src.post.entity.specification.PostSpecification;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import com.example.demo.src.user.entity.specification.UserSpecification;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.common.entity.BaseEntity.*;
import static com.example.demo.common.entity.BaseEntity.State.ACTIVE;
import static com.example.demo.common.entity.BaseEntity.State.BLOCK;
import static com.example.demo.common.response.BaseResponseStatus.*;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<GetUserRes> getUsers() {
        List<GetUserRes> getUserResList = userRepository.findAllByState(ACTIVE).stream()
                .map(GetUserRes::new)
                .collect(Collectors.toList());
        return getUserResList;
    }

    /**
     * 각 조건에 중복 검색으로 돌려주는 값
     * @param name 이름
     * @param id 유저 아이디
     * @param createDateTime 유저 생성일자
     * @param state 유저 상태
     * @return User -> GetUserRes로 반환한 값
     */
    @Transactional(readOnly = true)
    public List<GetUserRes> getUserByCondition(String name, Long id, LocalDateTime createDateTime, State state){
        Specification<User> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(UserSpecification.equalsName(name));
        }
        if (id != null) {
            spec = spec.and(UserSpecification.equalsId(id));
        }
        if (createDateTime != null) {
            spec = spec.and(UserSpecification.equalsCreateDateTime(createDateTime));
        }
        if (state != null) {
            spec = spec.and(UserSpecification.equalsState(state));
        }
        List<User> users = userRepository.findAll(spec);

        users.sort(Comparator.comparing(User::getCreatedAt));
        return users.stream()
                .map(GetUserRes::new)
                .collect(Collectors.toList());
    }

    /**
     * 유저의 모든 정보를 볼 수 있는 상세조회 페이지
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public UserDetailRes getUserDetail(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new BaseException(NOT_FIND_USER));
        return new UserDetailRes(user);
    }
    @Transactional(readOnly = false)
    public void blockUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new BaseException(NOT_FIND_USER));

        user.setState(BLOCK);
    }
    @Transactional(readOnly = true)
    public List<AdminPostRes> getPosts()
    {
        List<AdminPostRes> adminPostRes = postRepository.findAll().stream()
                .map(AdminPostRes::new)
                .collect(Collectors.toList());
        return adminPostRes;
    }
    @Transactional(readOnly = true)
    public List<AdminPostRes> getPostsByCondition(Long id, LocalDateTime createdDateTime, State state)
    {
        Specification<Post> spec = Specification.where(null);
        if(id != null)
        {
            spec = spec.and(PostSpecification.equalsId(id));
        }
        if(createdDateTime != null){
            spec = spec.and(PostSpecification.equalsCreatedDate(createdDateTime));
        }
        if(state != null){
            spec = spec.and(PostSpecification.equalsState(state));
        }
        List<Post> posts = postRepository.findAll(spec);

        posts.sort(Comparator.comparing(Post::getCreatedAt));
        return posts.stream()
                .map(AdminPostRes::new)
                .collect(Collectors.toList());

    }
}
