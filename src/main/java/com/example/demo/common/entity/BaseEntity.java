package com.example.demo.common.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 10)
    protected State state = State.ACTIVE;

    /**
     * ACTIVE : 유저 활성화 상태
     * INACTIVE : 유저 비활성화 상태
     * WITHDRAW : 유저 탈퇴 상태
     * BLOCK : 유저 차단 상태
     * ADMIN : 유저 관리자 상태
     */
    public enum State {
        ACTIVE, INACTIVE, WITHDRAW, BLOCK, ADMIN;
    }

    public void setState(State state) {
        this.state = state;
    }
}
