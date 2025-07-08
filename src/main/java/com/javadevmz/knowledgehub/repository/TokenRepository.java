package com.javadevmz.knowledgehub.repository;

import com.javadevmz.knowledgehub.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByValue(String jwt);

    void deleteAllByRevokedTrueOrExpiredTrue();
}
