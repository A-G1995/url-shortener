package com.example.urlshortener;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = LinkController.class)
public class LinkControllerTest {

    @Autowired
    private WebTestClient webTestClient;


    @MockBean
    private LinkService linkService;

    @Test
    public void shortensLink() {
        when(linkService.shortenLink("https://spring.io"))
                .thenReturn(Mono.just("http://localhost:8080"));
        webTestClient.post()
                .uri("/link")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"link\": \"https://spring.io\"}")
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody().jsonPath("$.shortenedLink")
                .value(val -> assertThat(val).isEqualTo("http://localhost:8080/aass2211"));

        ;
    }
}
