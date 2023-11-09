package br.com.alura.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImdbService {

    private final ImdbClientFetch imdbClientFetch;

    private final GeradorHTML geradorHTML;

    @Autowired
    public ImdbService(ImdbClientFetch imdbClientFetch, GeradorHTML geradorHTML){
        this.imdbClientFetch = imdbClientFetch;
        this.geradorHTML = geradorHTML;
    }

    public List<Filme> getJsonTopFilmes(){
        return this.imdbClientFetch.fetchTopFilmes();
    }

    public List<Filme> getJsonTopFilmesTitulo(String titulo){
        List<Filme> filmes = this.imdbClientFetch.fetchTopFilmes();
        return this.filtrarFilmesTitulo(filmes, titulo);
    }

    public String getHtmlTopFilmes(){
        List<Filme> filmes = this.imdbClientFetch.fetchTopFilmes();
        return this.geradorHTML.gerarStringHTML(filmes);
    }

    public String getHtmlTopFilmesTitulo(String titulo){
        List<Filme> filmes = this.imdbClientFetch.fetchTopFilmes();
        List<Filme> filmesTitulo = this.filtrarFilmesTitulo(filmes, titulo);
        return this.geradorHTML.gerarStringHTML(filmesTitulo);
    }

    public byte[] getHtmlTopFilmesDownload(){
        List<Filme> filmes = this.imdbClientFetch.fetchTopFilmes();
        String html = this.geradorHTML.gerarStringHTML(filmes);
        return html.getBytes();
    }

    public byte[] getHtmlTopFilmesDownloadTitulo(String titulo){
        List<Filme> filmes = this.imdbClientFetch.fetchTopFilmes();
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

}
