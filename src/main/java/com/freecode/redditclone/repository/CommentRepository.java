package com.freecode.redditclone.repository;

import org.springframework.stereotype.Repository;
import com.freecode.redditclone.model.Comment;
import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);

    
}
