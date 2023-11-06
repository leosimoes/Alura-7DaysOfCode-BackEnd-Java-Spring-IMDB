package br.com.alura.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@RestController
public class ImdbController {

    private RestTemplate restTemplate;

    @Autowired
    public ImdbController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/imdb")
    public ResponseEntity<List<Filme>> fetchTopMoviesData() {
        final String URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String json = restTemplate.getForObject(URL, String.class);

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray itemsArray = jsonObject.getAsJsonArray("items");

        List<Filme> filmes = gson.fromJson(itemsArray, new TypeToken<List<Filme>>() {}.getType());

        return ResponseEntity.ok(filmes);
    }

}
