package com.example.demo.src.reply;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.reply.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void deleteReply(Long id)
    {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FIND_REPLY));

        reply.deleteReply();
    }

}
