package com.freecode.redditclone.repository;


import org.springframework.stereotype.Repository;
import com.freecode.redditclone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
    
}
