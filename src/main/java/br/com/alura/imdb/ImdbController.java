package br.com.alura.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class ImdbController {

    private final ImdbService imdbService;
    @Autowired
    public ImdbController(ImdbService imdbService){
        this.imdbService = imdbService;
    }

    @GetMapping("/imdb/json")
    public ResponseEntity<List<Filme>> jsonTopFilmes() {
        List<Filme> filmes = this.imdbService.getJsonTopFilmes();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/imdb/html")
    public ResponseEntity<String> htmlTopFilmes() {
        String html = this.imdbService.getHtmlTopFilmes();
        return ResponseEntity.ok(html);
    }

    @GetMapping("/imdb/html-download")
    public ResponseEntity<byte[]> downloadHtml() {
        try {
            byte[] arquivo = this.imdbService.getHtmlTopFilmesDownload();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            headers.setContentDispositionFormData("attachment", "index.html");
            return new ResponseEntity<>(arquivo, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
