package com.example.demo.src.post.entity;


import com.example.demo.common.entity.BaseEntity;
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
@Table(name = "POST") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class Post extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    //유저와 게시물간 다대일 관계 설정
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public Post(User user, String content){
        this.user = user;
        this.content =content;
    }
    public void deletePost(){
        this.state = State.PRIVATE;
    }
    public void setContent(String content){
        this.content = content;
    }
}
