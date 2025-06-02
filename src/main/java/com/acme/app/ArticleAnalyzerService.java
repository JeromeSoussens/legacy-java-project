package com.acme.app;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ArticleAnalyzerService {

    public Article format(Article a) {
        return formatWithMyPreferedLibrary(a);
    }

    private Article formatWithMyPreferedLibrary(Article a) {
       a.setContent(a.getContent() + "\n");
       return a;
    }

    private Article formatDeadMethod(Article a) {
        //a.setContent(StringUtils.capitalize(a.getContent()) + "\n");
        a.setContent(a.getContent() + "\n");
        return a;
    }
}
