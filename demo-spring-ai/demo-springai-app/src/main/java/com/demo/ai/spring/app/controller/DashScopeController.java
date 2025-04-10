package com.demo.ai.spring.app.controller;

import com.demo.ai.spring.app.service.DashScopeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 阿里百炼
 *
 * @author wxmclub
 * @version 1.0
 * @date 2025-04-03
 */
@RestController
@RequestMapping("/dashscope")
public class DashScopeController {

    private final DashScopeService dashScopeService;

    public DashScopeController(DashScopeService dashScopeService) {
        this.dashScopeService = dashScopeService;
    }

    @GetMapping(value = "/chat/simple")
    public Mono<String> simpleChat(@RequestParam(value = "message") String message) {
        return dashScopeService.simpleChat(message);
    }

    @GetMapping(value = "/chat/simple/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> simpleChatStream(@RequestParam(value = "message") String message) {
        return dashScopeService.simpleChatStream(message);
    }

}
