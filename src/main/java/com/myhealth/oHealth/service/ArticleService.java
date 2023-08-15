package com.myhealth.oHealth.service;

import com.myhealth.oHealth.model.domain.Articles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {

    void createArticle(String content);

    ResponseEntity<?> updateArticle(Long id, String content);

    List<Articles> getAllArticles();
}
