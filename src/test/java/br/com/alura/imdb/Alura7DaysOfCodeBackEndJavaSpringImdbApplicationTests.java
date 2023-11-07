package br.com.alura.imdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Alura7DaysOfCodeBackEndJavaSpringImdbApplicationTests {

    private final String URL_1 =  "http://localhost:%s/imdb/json";
    private final String URL_2 =  "http://localhost:%s/imdb/html";
    private final String URL_3 =  "http://localhost:%s/html-download";

    @LocalServerPort
    private int porta;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testImdbEndpoint_JSON() {

        ResponseEntity<List> response =
                this.restTemplate.getForEntity(String.format(this.URL_1, Integer.toString(porta)), List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testImdbEndpoint_HTML() {

        ResponseEntity<String> response =
                this.restTemplate.getForEntity(String.format(this.URL_2, Integer.toString(porta)), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
