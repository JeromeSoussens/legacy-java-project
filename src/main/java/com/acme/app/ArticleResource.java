package com.acme.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@Api(value = "Articles", description = "Opérations liées aux articles")
public class ArticleResource {

    @Autowired
    private ArticleRepository articleRepository;

    @ApiOperation(value = "Voir la liste des articles", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Liste des articles récupérée avec succès"),
        @ApiResponse(code = 401, message = "Vous n'êtes pas autorisé à voir la liste des articles"),
        @ApiResponse(code = 403, message = "L'accès à la liste des articles est interdit"),
        @ApiResponse(code = 404, message = "La liste des articles n'est pas disponible")
    })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}
