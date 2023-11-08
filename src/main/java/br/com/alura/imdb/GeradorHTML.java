package br.com.alura.imdb;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.List;

@Component
public class GeradorHTML {
    public GeradorHTML(){
    }

    public String gerarStringHTML(List<Filme> filmes){
        StringBuffer html = new StringBuffer();
        html.append(TemplateHTML.INICIO_HTML);
        filmes.forEach(filme -> {
            html.append(
                    String.format(TemplateHTML.FILME_HTML,
                                    filme.getTitle(),
                                    filme.getImage(),
                                    filme.getTitle(),
                                    filme.getImDbRating(),
                                    filme.getYear()
                    )
            );
        });
        html.append(TemplateHTML.FIM_HTML);

        return html.toString();
    }

}
