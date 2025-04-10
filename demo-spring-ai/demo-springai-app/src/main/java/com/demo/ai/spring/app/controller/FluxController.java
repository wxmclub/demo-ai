package com.demo.ai.spring.app.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-04-10
 */
@RestController
@RequestMapping("/flux")
public class FluxController {

    @GetMapping(value = "/helloflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> helloFlux(String message) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofMillis(100L))
                .map(v -> String.format("idx-%02d: %s", v, message));
    }

    @GetMapping(value = "/hellomono")
    public Mono<String> helloMono(String message) {
        return Mono.just(String.format("hello: %s", message));
    }

}
