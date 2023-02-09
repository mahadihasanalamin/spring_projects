package com.example.springsecurityclient.repository;

import com.example.springsecurityclient.entity.UserVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationTokenRepository extends JpaRepository<UserVerificationToken, Integer> {
    UserVerificationToken findByToken(String token);
}
