package com.acme.app;

import org.springframework.stereotype.Service;

@Service
public class ArticleAnalyzerService {

    public Article format(Article a) {
        return formatWithMyPreferedLibrary(a);
    }

    private Article formatWithMyPreferedLibrary(Article a) {
       a.setContent(a.getContent() + "\n");
       return a;
    }
}
