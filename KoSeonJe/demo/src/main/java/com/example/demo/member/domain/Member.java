package com.example.demo.member.domain;

import com.example.demo.member.controller.request.CreateMemberRequest;
import com.example.demo.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long memberId;

  @Column(unique = true, nullable = false)
  private String authId;

  private String name;

  @OneToMany(mappedBy = "member")
  private List<Post> posts = new ArrayList<>();

  public static Member create(CreateMemberRequest createMemberRequest) {
    return new Member(null, createMemberRequest.getAuthId(), createMemberRequest.getName(), null);
  }

  public void update(String authId, String name) {
    this.authId = authId;
    this.name = name;
  }

  public void addPost(Post post) {
    posts.add(post);
  }
}
