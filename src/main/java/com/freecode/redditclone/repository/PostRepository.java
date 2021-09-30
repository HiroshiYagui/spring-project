package com.freecode.redditclone.repository;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.Subreddit;
import com.freecode.redditclone.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long id);

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
