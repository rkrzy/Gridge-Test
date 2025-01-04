package com.example.demo.src.post.entity.specification;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.post.entity.Post;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static com.example.demo.common.entity.BaseEntity.*;

public class PostSpecification {

    public static Specification<Post> equalsId(Long id){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"),id));
    }
    public static Specification<Post> equalsCreatedDate(LocalDateTime createdDate){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("createdAt"), createdDate));
    }
    public static Specification<Post> equalsState(State state){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("state"), state));
    }

}
