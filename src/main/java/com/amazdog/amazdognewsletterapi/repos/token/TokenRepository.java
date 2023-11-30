package com.amazdog.amazdognewsletterapi.repos.token;

import com.amazdog.amazdognewsletterapi.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
}
