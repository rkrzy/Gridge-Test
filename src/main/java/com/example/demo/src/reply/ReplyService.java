package com.example.demo.src.reply;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.image.ProfileImageRepository;
import com.example.demo.src.image.entity.ProfileImage;
import com.example.demo.src.post.PostRepository;
import com.example.demo.src.reply.entity.Reply;
import com.example.demo.src.reply.model.ReplyCreateRequestDTO;
import com.example.demo.src.reply.model.ReplyDetailDTO;
import com.example.demo.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.response.BaseResponseStatus.*;

@Transactional
@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ProfileImageRepository profileImageRepository;
    public void deleteReply(Long id)
    {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new BaseException(NOT_FIND_REPLY));

        reply.deleteReply();
    }
    @Transactional
    public ReplyDetailDTO leaveReply(Long userId, Long postId, ReplyCreateRequestDTO request)
    {
        Reply reply = new Reply(
                userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER)),
                postRepository.findById(postId).orElseThrow(() -> new BaseException(NOT_FIND_POST)),
                request.getContent());

        ProfileImage profileImage = profileImageRepository.findByUserId(userId);

        replyRepository.save(reply);

        return new ReplyDetailDTO(reply, profileImage);
    }

}
