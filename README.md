# Alura - 7 Days Of Code - Back-End - Java - Spring - IMDB

Projeto do 7 Days of Code de Spring da Alura: Consumindo a API de filmes do IMDB e criando uma página HTML de exibição.


## Atividades

Dia 1:
- Criar projeto com Spring Boot.

![IntelliJ-Spring-Initializr-Directories](imgs/IntelliJ-Spring-Initializr-1.jpg)

![IntelliJ-Spring-Initializr-Dependences](imgs/IntelliJ-Spring-Initializr-2.jpg)

- ~~Cadastrar no site IMDB para obter chave da API.~~
- ~~Colocar a Chave da API em `application.properties` mas não compartilhá-la.~~
- Criar a classe RestTemplateConfig que tem um método com `@Bean` que retorna uma instância de RestTemplate.
- Criar a classe ImdbController e implementar método de getmapping para `/imdb` usando a url para o dados. 
- Testar o endpoint `http://localhost:8080/imdb`

Dia 2:
- Remover o campo `imdbApiKey` de ImdbController e seu valor de `application.properties`, já que os dados serão obtidas via url do github.
- Alterar classe de teste `Alura7DaysOfCodeBackEndJavaSpringImdbApplicationTests`:
  * Anotar a classe com `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`.
  * Criar atributo `porta` e anotá-lo com `@LocalServerPort`.
  * Criar atributo `restTemplate` do tipo `TestRestTemplate` e anotá-lo com `@Autowired`.
  * Criar método `testImdbEndpoint`para testar o endpoint `http://localhost:8080/imdb`.

![Test-1-Endpoint](imgs/test1-endpoint.jpg)

Dia 3:
- Criar classe Filme e usar Lombok para gerar getters, setters e construtores.

![UML-Classe-Filme](imgs/UML-Classe-Filme.jpg)

- Adicionar dependência do GSON `implementation group: 'com.google.code.gson', name: 'gson', version: '2.10.1'` em `build.gradle`.
- Alterar método da controller para que o retorno tenta uma List de Filmes ao invés de uma String.
- Alterar o método da classe de teste para verificar o retorno do tipo List e não String.


## Referências

Alura - 7 Days Of Code - Back-End - Java - Spring:
https://7daysofcode.io/matricula/spring

Spring Framework - Docs - Rest Template: 
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html

Baeldung Rest Template:
https://www.baeldung.com/rest-template

Imdb Top 250 Filmes:
https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json