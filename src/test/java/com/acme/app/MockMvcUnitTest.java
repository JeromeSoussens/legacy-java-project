package com.acme.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MockMvcUnitTest {

    private AutoCloseable mocks;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleResource articleResource;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        mocks = MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(articleResource).build();

        List<Article> articles = new ArrayList<>();
        articles.add(ArticleFactory.newArticle("Article 1"));
        articles.add(ArticleFactory.newArticle("Article 2"));
        articles.add(ArticleFactory.newArticle("Article 3"));
        Mockito.when(articleRepository.findAll()).thenReturn(articles);
    }

    @Test
    public void testGetArticles() throws Exception {
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse().getContentAsString();
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

}
