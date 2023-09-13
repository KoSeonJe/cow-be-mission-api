package com.example.demo.post.domain;

import com.example.demo.comment.domain.Comment;
import com.example.demo.member.domain.Member;
import com.example.demo.post.controller.request.CreatePostRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long postId;

  private String title;

  private String content;

  @CreationTimestamp
  private LocalDateTime createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  public static Post create(CreatePostRequest createPostRequest, Member postAuthor) {
    return new Post(null, createPostRequest.getTitle(), createPostRequest.getContent(), null, postAuthor, null);
  }

  public void addComment(Comment comment) {
    comments.add(comment);
  }

  public String getCommentsContent() {
    StringBuilder comments = new StringBuilder();
    this.comments.stream().forEach(comment -> comments.append(comment.getContent()));
    return String.valueOf(comments);
  }
}
