package com.myhealth.oHealth.repository;

import com.myhealth.oHealth.model.domain.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Articles, Long> {
    Optional<Articles> findByContent(String content);
}
