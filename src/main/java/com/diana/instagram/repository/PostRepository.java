package com.diana.instagram.repository;

import com.diana.instagram.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByProfileIdAndIsArchived(long id, boolean isArchived);
}
