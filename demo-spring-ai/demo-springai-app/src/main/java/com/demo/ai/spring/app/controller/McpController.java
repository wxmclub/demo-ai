package com.demo.ai.spring.app.controller;

import com.demo.ai.spring.app.service.McpService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-04-10
 */
@RestController
@RequestMapping("/mcp")
public class McpController {

    private final McpService mcpService;

    public McpController(McpService mcpService) {
        this.mcpService = mcpService;
    }

    @GetMapping("/call")
    public Mono<String> call(@RequestParam String message) {
        return mcpService.callMcp(message);
    }

    @GetMapping(value = "/call/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> callStream(@RequestParam String message) {
        return mcpService.callMcpStream(message);
    }

}
