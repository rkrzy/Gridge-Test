package com.example.demo.src.post;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.image.Image;
import com.example.demo.src.image.ImageRepository;
import com.example.demo.src.image.model.ImageInfoDTO;
import com.example.demo.src.like.LikeRepository;
import com.example.demo.src.post.entity.Post;
import com.example.demo.src.post.model.PostDTO;
import com.example.demo.src.reply.ReplyRepository;
import com.example.demo.src.user.model.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.common.response.BaseResponseStatus.NOT_FIND_POST;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final LikeRepository likeRepository;
    private final ReplyRepository replyRepository;

    public void deletePost(Long id)
    {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException(NOT_FIND_POST));

        post.deletePost();
    }
    public List<PostDTO> getPostList(Integer pageIndex, Integer size)
    {
        int page = (pageIndex == null || pageIndex < 1) ? 0 : pageIndex - 1;
        int pageSize = (size == null || size < 1) ? 10 : size;
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());

        Page<Post> pageOfPosts = postRepository.findAll(pageRequest);

        List<PostDTO> postDTOS = pageOfPosts.getContent().stream()
                .map(post -> {
                    // 각 post에 대해 DTO 생성
                    // 필요하다면 이미지, 좋아요 수, 댓글 수 등을 조회
                    // 예:
                    List<Image> images = imageRepository.findByPostId(post.getId());
                    List<ImageInfoDTO> imageDTOs = images.stream()
                            .map(ImageInfoDTO::new)
                            .collect(Collectors.toList());

                    long likeCount = likeRepository.findAllByPostId(post.getId()).stream().count();
                    long replyCount = replyRepository.findAllByPostId(post.getId()).stream().count();

                    return new PostDTO(
                            post.getId(),
                            new UserInfoDTO(post.getUser()),
                            post.getContent(),
                            imageDTOs,
                            likeCount,
                            replyCount,
                            post.getCreatedAt()
                    );
                })
                .collect(Collectors.toList());
        return postDTOS;
    }
}
