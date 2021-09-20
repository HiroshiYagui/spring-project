package com.freecode.redditclone.repository;


import org.springframework.stereotype.Repository;
import com.freecode.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    
}