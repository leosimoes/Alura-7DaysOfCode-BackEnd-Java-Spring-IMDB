package br.com.alura.imdb;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ImdbClientFetch {

    private final String URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    private final RestTemplate restTemplate;

    @Autowired
    public ImdbClientFetch(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Filme> fetchTopFilmes(){
        String json = restTemplate.getForObject(this.URL, String.class);

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray itemsArray = jsonObject.getAsJsonArray("items");

        return gson.fromJson(itemsArray, new TypeToken<List<Filme>>() {}.getType());
    }
}
