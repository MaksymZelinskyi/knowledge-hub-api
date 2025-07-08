package com.javadevmz.knowledgehub.repository;

import com.javadevmz.knowledgehub.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByValue(String value);
}
