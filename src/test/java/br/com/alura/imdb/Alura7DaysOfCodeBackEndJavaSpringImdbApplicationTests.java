package br.com.alura.imdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Alura7DaysOfCodeBackEndJavaSpringImdbApplicationTests {

    private final String URL =  "http://localhost:%s/imdb";

    @LocalServerPort
    private int porta;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void contextLoads() {
    }

    @Test
    void testImdbEndpoint() {

        ResponseEntity<String> response =
                this.restTemplate.getForEntity(String.format(this.URL, Integer.toString(porta)), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
