package com.example.demo.src.user.entity.specification;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.user.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class UserSpecification {
    public static Specification<User> equalsName(String name){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), name));
    }
    public static Specification<User> equalsId(Long id){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id));
    }
    public static Specification<User> equalsCreateDateTime(LocalDateTime createDateTime){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("createdAt"), createDateTime));
    }
    public static Specification<User> equalsState(BaseEntity.State state){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("state"), state));
    }
}
