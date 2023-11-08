package br.com.alura.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String getHtmlTopFilmes(){
        List<Filme> filmes = this.imdbClientFetch.fetchTopFilmes();
        return this.geradorHTML.gerarStringHTML(filmes);
    }

    public byte[] getHtmlTopFilmesDownload(){
        List<Filme> filmes = this.imdbClientFetch.fetchTopFilmes();
        String html = this.geradorHTML.gerarStringHTML(filmes);
        return html.getBytes();
    }

}
