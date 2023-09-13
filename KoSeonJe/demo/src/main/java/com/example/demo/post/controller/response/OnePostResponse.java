package com.example.demo.post.controller.response;

import com.example.demo.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OnePostResponse {

  private String title;
  private String content;
  private String author;
  private String createdDate;
  private String comments;

  @Builder
  private OnePostResponse(String title, String content, String author, String createdDate, String comments) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.createdDate = createdDate;
    this.comments = comments;
  }

  public static OnePostResponse from(Post post) {
    return OnePostResponse.builder()
        .title(post.getTitle())
        .content(post.getContent())
        .author(post.getMember().getName())
        .createdDate(post.getCreatedDate().toString())
        .comments(post.getCommentsContent())
        .build();
  }

}
