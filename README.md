# Alura - 7 Days Of Code - Back-End - Java - Spring - IMDB

Projeto do 7 Days of Code de Spring da Alura: Consumindo a API de filmes do IMDB e criando uma página HTML de exibição.


## Atividades

Dia 1:
- Criar projeto com Spring Boot.
- ~~Cadastrar no site IMDB para obter chave da API.~~
- ~~Colocar a Chave da API em `application.properties` mas não compartilhá-la.~~
- Criar a classe RestTemplateConfig que tem um método com `@Bean` que retorna uma instância de RestTemplate.
- Criar a classe ImdbController e implementar método de getmapping para `/imdb` usando a url para o dados. 
- Testar o endpoint `http://localhost:8080/imdb`


## Referências

Alura - 7 Days Of Code - Back-End - Java - Spring:
https://7daysofcode.io/matricula/spring

Spring Framework - Docs - Rest Template: 
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html

Baeldung Rest Template:
https://www.baeldung.com/rest-template

Imdb Top 250 Filmes:
https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json