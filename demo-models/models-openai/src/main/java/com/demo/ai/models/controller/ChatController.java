package com.demo.ai.models.controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wangxiaoming
 * @version 1.0
 * @date 2025-03-23
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private OpenAiChatModel chatModel;

    @GetMapping(value = "/generate")
    Mono<String> generate(@RequestParam String message) {
        String result = chatModel.call(message);
        return Mono.just(result);
    }

    @GetMapping(value = "/generatestream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ChatResponse> generateStream(@RequestParam String message) {
        return chatModel.stream(new Prompt(message));
    }

}
