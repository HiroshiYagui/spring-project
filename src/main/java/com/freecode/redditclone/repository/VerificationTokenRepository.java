package com.freecode.redditclone.repository;


import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.freecode.redditclone.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    Optional<VerificationToken> findByToken(String token);
}
