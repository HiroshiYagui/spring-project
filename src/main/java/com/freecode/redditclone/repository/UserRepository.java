package com.freecode.redditclone.repository;


import org.springframework.stereotype.Repository;
import com.freecode.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
}
