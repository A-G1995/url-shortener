package com.example.urlshortener;


import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LinkController {


    private final LinkService linkService;

    @PostMapping("/link")
    public Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request) {
        return linkService.shortenLink(request.getLink())
                .map(CreateLinkResponse::new);

//        return Mono.just(new CreateLinkResponse("http://localhost:8080/aass2211"));
    }


    @Value
    public static class CreateLinkRequest {
        private String link;
    }


    @Value
    public static class CreateLinkResponse {
        private String shortenedLink;

    }
}
