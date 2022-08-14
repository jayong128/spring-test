package com.example.projectboard.repository;

import com.example.projectboard.config.JpaConfig;
import com.example.projectboard.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(JpaConfig.class)
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    @DisplayName("select 테스트")
    void crudTest() {
        //given

        //when
        List<Article> articles = articleRepository.findAll();
        //then
        Assertions.assertThat(articles).isNotNull().hasSize(123);
    }

    @DisplayName("insert 테스트")
    @Test
    void insertTest() {
        //given
        long count = articleRepository.count();
        Article article = Article.of("new article", "new content", "#spring");
        //when
        articleRepository.save(article);

        //then
        Assertions.assertThat(articleRepository.count()).isEqualTo(count + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void updateTest() {
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#Springboot";
        article.setHashtag(updateHashtag);
        Article saved = articleRepository.saveAndFlush(article);
        Assertions.assertThat(saved).hasFieldOrPropertyWithValue("hashtag", updateHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void deleteTest() {
        Article article = articleRepository.findById(1L).orElseThrow();
        long count = articleRepository.count();
        long commentCount = articleCommentRepository.count();
        int deletedSize = article.getArticleComments().size();

        articleRepository.delete(article);
        Assertions.assertThat(articleRepository.count()).isEqualTo(count - 1);
        Assertions.assertThat(articleCommentRepository.count()).isEqualTo(commentCount - deletedSize);
    }
}