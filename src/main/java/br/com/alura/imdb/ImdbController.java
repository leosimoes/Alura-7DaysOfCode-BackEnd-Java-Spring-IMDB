package br.com.alura.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/imdb/json/{titulo}")
    public ResponseEntity<List<Filme>> jsonTopFilmesTitulo(@PathVariable("titulo") String titulo) {
        List<Filme> filmes = this.imdbService.getJsonTopFilmesTitulo(titulo);
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/imdb/html")
    public ResponseEntity<String> htmlTopFilmes() {
        String html = this.imdbService.getHtmlTopFilmes();
        return ResponseEntity.ok(html);
    }

    @GetMapping("/imdb/html/{titulo}")
    public ResponseEntity<String> htmlTopFilmesTitulo(@PathVariable("titulo") String titulo) {
        String html = this.imdbService.getHtmlTopFilmesTitulo(titulo);
        return ResponseEntity.ok(html);
    }

    @GetMapping("/imdb/html-download")
    public ResponseEntity<byte[]> downloadHtmlTopFilmes() {
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

    @GetMapping("/imdb/html-download/{titulo}")
    public ResponseEntity<byte[]> downloadHtmlTopFilmesTitulo(@PathVariable("titulo") String titulo) {
        try {
            byte[] arquivo = this.imdbService.getHtmlTopFilmesDownloadTitulo(titulo);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            headers.setContentDispositionFormData("attachment", "index.html");
            return new ResponseEntity<>(arquivo, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/imdb/favoritos")
    public ResponseEntity<Filme> adicionarFilmeFavorito(@RequestBody Filme filme){
        Filme favorito = this.imdbService.adicionarFilmeFavorito(filme);
        return ResponseEntity.accepted().body(favorito);
    }

    @DeleteMapping("/imdb/favoritos/{id}")
    public ResponseEntity<Boolean> deletarFilmeFavorito(@PathVariable("id") String id){
        Boolean is_removed  = this.imdbService.delFilmeFavoritoId(id);
        return ResponseEntity.accepted().body(is_removed);
    }

    @GetMapping("/imdb/favoritos/json")
    public ResponseEntity<List<Filme>> jsonFilmesFavoritos(){
        List<Filme> favoritos = this.imdbService.getJsonFilmesFavoritos();
        return ResponseEntity.accepted().body(favoritos);
    }

    @GetMapping("/imdb/favoritos/html")
    public ResponseEntity<String> htmlFilmesFavoritos() {
        String html = this.imdbService.getHtmlFilmesFavoritos();
        return ResponseEntity.ok(html);
    }

    @GetMapping("/imdb/favoritos/html-download")
    public ResponseEntity<byte[]> downloadHtmlFilmesFavoritos() {
        try {
            byte[] arquivo = this.imdbService.getHtmlilmesFavoritosDownload();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            headers.setContentDispositionFormData("attachment", "index.html");
            return new ResponseEntity<>(arquivo, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}