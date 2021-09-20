package com.freecode.redditclone.repository;


import org.springframework.stereotype.Repository;
import com.freecode.redditclone.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    
}
