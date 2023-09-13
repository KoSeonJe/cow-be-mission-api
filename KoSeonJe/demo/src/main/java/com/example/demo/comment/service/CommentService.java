package com.example.demo.comment.service;

import com.example.demo.comment.controller.request.CreateCommentRequest;
import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.service.MemberService;
import com.example.demo.post.domain.Post;
import com.example.demo.post.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

  private final CommentRepository commentRepository;
  private final MemberService memberService;
  private final PostService postService;


  public void createComment(CreateCommentRequest createCommentRequest, Long memberId, Long postId) {
    Member commentWriter = memberService.findOneUser(memberId);
    Post writenPost = postService.findOnePost(postId);
    Comment comment = Comment.create(createCommentRequest, commentWriter, writenPost);
    writenPost.addComment(comment);
    commentRepository.save(comment);
  }
}
