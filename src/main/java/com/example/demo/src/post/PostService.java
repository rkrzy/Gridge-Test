package com.example.demo.src.post;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.response.BaseResponseStatus.NOT_FIND_POST;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public void deletePost(Long id)
    {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException(NOT_FIND_POST));

        post.deletePost();
    }
}
