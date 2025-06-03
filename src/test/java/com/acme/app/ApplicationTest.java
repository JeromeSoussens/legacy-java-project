package com.acme.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    private static final Logger LOGGER = getLogger(ApplicationTest.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleResource articleResource;

    @Test
    public void testDependencyInjection() {
        Assertions.assertThat(articleRepository).isNotNull();
        Assertions.assertThat(articleResource).isNotNull();
    }

    @Test
    public void testInitialize() {
        List<Article> articles = articleResource.getArticles();
        Assertions.assertThat(articles).isNotNull().hasSize(3);
    }

}
