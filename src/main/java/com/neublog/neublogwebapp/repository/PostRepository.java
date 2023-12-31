package com.neublog.neublogwebapp.repository;

import com.neublog.neublogwebapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Integer> // Post-Entity Type and Long coz of Primary key id
{
    Optional<Post> findByUrl(String url); //query method or finder method

    @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);

    @Query(value = "select * from posts p where p.created_by =:userId",nativeQuery = true)
    List<Post> findPostByUser(Integer userId);
}

