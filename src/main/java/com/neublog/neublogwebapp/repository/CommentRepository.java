package com.neublog.neublogwebapp.repository;

import com.neublog.neublogwebapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query(value = "select c.* from comments c inner join posts p\n" +
            "where c.post_id = p.id and p.created_by=:userId", nativeQuery = true)
    List<Comment> findCommentsByPost(Integer userId);
}

