package com.freecode.redditclone.repository;


import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.freecode.redditclone.model.Post;
import com.freecode.redditclone.model.User;
import com.freecode.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
    
}