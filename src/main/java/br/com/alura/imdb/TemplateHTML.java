package br.com.alura.imdb;

public class TemplateHTML {
    public static final String INICIO_HTML = """
            <!DOCTYPE html>
            <html>
                <head>
                    <meta charset="UTF-8"/>
                    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
                    <meta name="viewport" content="width=device-width, initial-scale=1"/>
                    <title>Imdb - Top 250 Filmes</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
                    rel="stylesheet" 
                    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
                    crossorigin="anonymous"/>
                </head>
                <body>
            """;

    public static final String FILME_HTML = """
                    <div class="d-inline-block">
                        <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
                            <h4 class="card-header">%s</h4>
                            <div class="card-body">
                                <img class="card-img" src="%s" alt="%s">
                                <p class="card-text mt-2">Nota: %s - Ano: %s</p>
                            </div>
                        </div>
                    </div>
            """;

    public static final String FIM_HTML = """
                </body>
            </html>
            """;
}
