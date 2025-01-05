package com.example.demo.src.report.entity;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.post.entity.Post;
import com.example.demo.src.user.entity.User;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "POSTREPORT",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "reply_id"})) // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class PostReport extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //신고 내용
    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @JoinColumn(name = "post_id", nullable = false, unique = true)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;
    public PostReport(User user, Post post, String content){
        this.user = user;
        this.post = post;
        this.content = content;
    }


}
