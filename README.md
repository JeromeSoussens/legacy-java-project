# Spring Boot Legacy 1.8 Sample App


# API

Method | Path           | Description                    |
-------|----------------|--------------------------------|
GET    | /articles      | retrieve all the articles      |
GET    | /articles/{id} | retrieve one article by its ID |
POST   | /articles      | store a new article            |
PUT    | /articles      | update an existing article     |
DELETE | /articles/{id} | remove an article byt its ID   |

```
// GET /articles
$ curl -X GET http://localhost:8080/api/articles -i -H "Content-Type: application/json"

// GET /articles/{id}
$ curl -X GET http://localhost:8080/api/articles/1 -i -H "Content-Type: application/json"

// POST /articles
$ curl -X POST http://{host}:{port}/api/articles -i
    -H "Accept: application/json"
    -H "Content-Type: application/json"
    -d '{"title": "Hello world!", "author": "J. Doe", "publicationDate": "20/05/2015", "excerpt": "This is a simple hello world.", "content": "Lorem ipsum dolor sit amet etc."}'

// PUT /articles
$ curl -X PUT http://{host}:{port}/api/articles -i -H "Accept: application/json" -H "Content-Type: application/json"

// DELETE /articles/{id}
$ curl -X DELETE http://{host}:{port}/api/articles/{id} -i
```

# MIGRATION STEPS

## Step 0
sdk use java 8.0.452-zulu
mvn verify

## Step 1
mvn -U org.openrewrite.maven:rewrite-maven-plugin:run \
-Drewrite.recipeArtifactCoordinates=com.jsoussens.rewerite:rewrite-base:1.0-SNAPSHOT \
-Drewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_4

## Step 2
sdk use java 17.0.15-tem

## Step 3
mvn -U org.openrewrite.maven:rewrite-maven-plugin:run \
-Drewrite.recipeArtifactCoordinates=com.jsoussens.rewerite:rewrite-base:1.0-SNAPSHOT \
-Drewrite.activeRecipes="org.openrewrite.staticanalysis.CodeCleanup,\
org.openrewrite.staticanalysis.CommonStaticAnalysis,\
org.openrewrite.java.RemoveUnusedImports,\
org.openrewrite.staticanalysis.RemoveUnusedPrivateMethods"

## Step 4 : test & fix
mvn verify

## upgrade non-working dependencies (jaxb, module java.base does not "opens java.lang", etc.)