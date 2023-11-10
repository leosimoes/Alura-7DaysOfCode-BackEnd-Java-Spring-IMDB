package br.com.alura.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImdbService {

    private final ImdbClientFetch imdbClientFetch;

    private final GeradorHTML geradorHTML;

    private Map<String, Filme> favoritos;

    @Autowired
    public ImdbService(ImdbClientFetch imdbClientFetch, GeradorHTML geradorHTML){
        this.imdbClientFetch = imdbClientFetch;
        this.geradorHTML = geradorHTML;
        this.favoritos = new HashMap<>();
    }

    public List<Filme> getJsonTopFilmes(){
        return this.imdbClientFetch.loadTopFilmes();
    }

    public List<Filme> getJsonTopFilmesTitulo(String titulo){
        List<Filme> filmes = this.imdbClientFetch.loadTopFilmes();
        return this.filtrarFilmesTitulo(filmes, titulo);
    }

    public String getHtmlTopFilmes(){
        List<Filme> filmes = this.imdbClientFetch.loadTopFilmes();
        return this.geradorHTML.gerarStringHTML(filmes);
    }

    public String getHtmlTopFilmesTitulo(String titulo){
        List<Filme> filmes = this.imdbClientFetch.loadTopFilmes();
        List<Filme> filmesTitulo = this.filtrarFilmesTitulo(filmes, titulo);
        return this.geradorHTML.gerarStringHTML(filmesTitulo);
    }

    public byte[] getHtmlTopFilmesDownload(){
        List<Filme> filmes = this.imdbClientFetch.loadTopFilmes();
        String html = this.geradorHTML.gerarStringHTML(filmes);
        return html.getBytes();
    }

    public byte[] getHtmlTopFilmesDownloadTitulo(String titulo){
        List<Filme> filmes = this.imdbClientFetch.loadTopFilmes();
        List<Filme> filmesTitulo = this.filtrarFilmesTitulo(filmes, titulo);
        String html = this.geradorHTML.gerarStringHTML(filmesTitulo);
        return html.getBytes();
    }

    private List<Filme> filtrarFilmesTitulo(List<Filme> filmes, String titulo){
        return filmes
                .stream()
                .filter(filme -> filme.getTitle().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Filme adicionarFilmeFavorito(Filme filme) {
        return favoritos.put(filme.getId(), filme);
    }

    public List<Filme> getJsonFilmesFavoritos() {
        return this.favoritos.values().stream().toList();
    }

    public Boolean delFilmeFavoritoId(String id) {
        if(this.favoritos.containsKey(id)){
            this.favoritos.remove(id);
            return true;
        }
        return false;
    }

    public String getHtmlFilmesFavoritos(){
        List<Filme> filmes = this.favoritos.values().stream().toList();
        return this.geradorHTML.gerarStringHTML(filmes);
    }

    public byte[] getHtmlilmesFavoritosDownload(){
        List<Filme> filmes = this.favoritos.values().stream().toList();
        String html = this.geradorHTML.gerarStringHTML(filmes);
        return html.getBytes();
    }

}
