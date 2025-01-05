package com.example.demo.src.like;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.like.entity.Like;
import com.example.demo.src.post.PostRepository;
import com.example.demo.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.response.BaseResponseStatus.NOT_FIND_POST;
import static com.example.demo.common.response.BaseResponseStatus.NOT_FIND_USER;

@Transactional
@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public String clickLike(Long postId, Long userId)
    {
        Like exist = likeRepository.findByPostIdAndUserId(postId, userId);
        if(exist == null)
        {
            Like like = new Like(
                    userRepository.findById(userId).orElseThrow(()->new BaseException(NOT_FIND_USER)),
                    postRepository.findById(postId).orElseThrow(()->new BaseException(NOT_FIND_POST)));
            likeRepository.save(like);
            return "좋아요가 생성되었습니다!";
        }
        else{
            likeRepository.delete(exist);
            return "좋아요가 지워졌습니다!";
        }
    }
}
