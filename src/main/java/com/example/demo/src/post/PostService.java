package com.example.demo.src.post;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.image.ProfileImageRepository;
import com.example.demo.src.image.entity.Image;
import com.example.demo.src.image.ImageRepository;
import com.example.demo.src.image.model.ImageInfoDTO;
import com.example.demo.src.like.LikeRepository;
import com.example.demo.src.like.entity.Like;
import com.example.demo.src.post.entity.Post;
import com.example.demo.src.post.model.PostDTO;
import com.example.demo.src.post.model.PostDetailDTO;
import com.example.demo.src.reply.ReplyRepository;
import com.example.demo.src.reply.entity.Reply;
import com.example.demo.src.reply.model.ReplyDetailDTO;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.model.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.common.response.BaseResponseStatus.NOT_FIND_POST;
import static com.example.demo.common.response.BaseResponseStatus.NOT_FIND_USER;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final LikeRepository likeRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;

    public void deletePost(Long id)
    {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException(NOT_FIND_POST));

        post.deletePost();
    }
    @Transactional(readOnly = true)
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
                    List<Image> images = imageRepository.findAllByPostId(post.getId());
                    List<ImageInfoDTO> imageDTOs = images.stream()
                            .map(ImageInfoDTO::new)
                            .collect(Collectors.toList());

                    long likeCount = likeRepository.findAllByPostId(post.getId()).stream().count();
                    long replyCount = replyRepository.findAllByPostId(post.getId()).stream().count();

                    return new PostDTO(
                            post.getId(),
                            new UserInfoDTO(post.getUser(),profileImageRepository.findByUserId(post.getUser().getId())),
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
    @Transactional(readOnly = true)
    public PostDetailDTO getPostDetail(Long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BaseException(NOT_FIND_POST));

        List<Image> images = imageRepository.findAllByPostId(post.getId());
        List<ImageInfoDTO> imageDTOs = images.stream()
                .map(ImageInfoDTO::new)
                .collect(Collectors.toList());

        List<Reply> replies = replyRepository.findAllByPostId(postId);
        List<ReplyDetailDTO> replyDetailDTOS = replies.stream()
                .map(reply -> new ReplyDetailDTO(
                        reply, profileImageRepository.findByUserId(reply.getUser().getId())
                ))
                .collect(Collectors.toList());

        long likeCount = likeRepository.findAllByPostId(post.getId()).stream().count();
        return new PostDetailDTO(
                postId,
                new UserInfoDTO(post.getUser(),profileImageRepository.findByUserId(post.getUser().getId())),
                post.getContent(),
                imageDTOs,
                likeCount,
                replyDetailDTOS,
                post.getCreatedAt()
        );
    }
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
