package com.javadevmz.knowledgehub.repository;

import com.javadevmz.knowledgehub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
