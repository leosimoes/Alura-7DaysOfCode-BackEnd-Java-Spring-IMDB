package br.com.alura.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@RestController
public class ImdbController {

    private RestTemplate restTemplate;

    @Autowired
    public ImdbController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/imdb/json")
    public ResponseEntity<List<Filme>> jsonTopFilmes() {
        List<Filme> filmes = this.fetchTopMoviesData();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/imdb/html")
    public ResponseEntity<String> htmlTopFilmes() {

        List<Filme> filmes = this.fetchTopMoviesData();
        GeradorHTML geradorHTML = new GeradorHTML();
        String html = geradorHTML.gerarStringHTML(filmes);

        return ResponseEntity.ok(html);
    }

    @GetMapping("/imdb/html-download")
    public ResponseEntity<byte[]> downloadHtml() {
        try {
            List<Filme> filmes = this.fetchTopMoviesData();
            GeradorHTML geradorHTML = new GeradorHTML();
            String htmlContent = geradorHTML.gerarStringHTML(filmes);
            byte[] content = htmlContent.getBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            headers.setContentDispositionFormData("attachment", "index.html");

            return new ResponseEntity<>(content, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Filme> fetchTopMoviesData(){
        final String URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String json = restTemplate.getForObject(URL, String.class);

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray itemsArray = jsonObject.getAsJsonArray("items");

        return gson.fromJson(itemsArray, new TypeToken<List<Filme>>() {}.getType());
    }

}
