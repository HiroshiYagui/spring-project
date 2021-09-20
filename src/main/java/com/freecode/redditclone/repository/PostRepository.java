package com.freecode.redditclone.repository;


import org.springframework.stereotype.Repository;
import com.freecode.redditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    
}
