package br.com.alura.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class ImdbController {

    private RestTemplate restTemplate;

    @Value("${imdb.api.key}")
    private String imdbApiKey;

    @Autowired
    public ImdbController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/imdb")
    public String fetchTopMoviesData() {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        return restTemplate.getForObject(url, String.class);
    }

}
