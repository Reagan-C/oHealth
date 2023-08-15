package com.myhealth.oHealth.service.serviceImpl;

import com.myhealth.oHealth.model.domain.Articles;
import com.myhealth.oHealth.model.exceptions.ArticleIDExistsException;
import com.myhealth.oHealth.model.exceptions.ArticleIDNotFoundException;
import com.myhealth.oHealth.repository.ArticleRepository;
import com.myhealth.oHealth.service.ArticleService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional
    public void createArticle(String content) {

        Articles article = articleRepository.findByContent(content).orElseThrow(
                ArticleIDExistsException::new
        );
        article.setContent(content);
        articleRepository.save(article);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateArticle(Long id, String content) {
        Articles article = articleRepository.findById(id).orElseThrow(
                () -> new ArticleIDNotFoundException(id)
        );
        article.setContent(content);
        articleRepository.save(article);
        return ResponseEntity.ok("Updated successfully");
    }

    @Override
    public List<Articles> getAllArticles() {
        return articleRepository.findAll();
    }
}
