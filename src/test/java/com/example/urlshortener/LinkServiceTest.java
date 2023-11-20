package com.example.urlshortener;

import org.junit.Test;
import reactor.test.StepVerifier;

class LinkServiceTest {

    private LinkService linkService = new LinkService();


    @Test
    public void shortenLink() {
        StepVerifier.create(linkService.shortenLink("https://spring.io"))
                .expectNextMatches(result -> result != null && !result.isEmpty())
                .expectComplete()
                .verify();
    }

}