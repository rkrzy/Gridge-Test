package com.example.demo.src.report;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.response.BaseResponseStatus;
import com.example.demo.src.post.PostRepository;
import com.example.demo.src.reply.ReplyRepository;
import com.example.demo.src.report.entity.PostReport;
import com.example.demo.src.report.entity.ReplyReport;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.response.BaseResponseStatus.*;

@Transactional
@RequiredArgsConstructor
@Service
public class ReportService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final ReplyRepository replyRepository;

  private final ReplyReportRepository replyReportRepository;
  private final PostReportRepository postReportRepository;

  @Transactional
    public void registerReplyReport(Long userId, Long replyId, String content){
    ReplyReport replyReport = new ReplyReport(
            userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER)),
            replyRepository.findById(replyId).orElseThrow(() -> new BaseException(NOT_FIND_REPLY)),
            content);
    replyReportRepository.save(replyReport);
  }
  @Transactional
    public void registerPostReport(Long userId, Long postId, String content){
    PostReport postReport = new PostReport(
            userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER)),
            postRepository.findById(postId).orElseThrow(()-> new BaseException(NOT_FIND_POST)),
            content);
    postReportRepository.save(postReport);
  }

}
