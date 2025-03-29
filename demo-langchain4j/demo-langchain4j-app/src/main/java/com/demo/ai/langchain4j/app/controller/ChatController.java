package com.demo.ai.langchain4j.app.controller;

import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-03-29
 */
@Slf4j
@RestController
@RequestMapping("/simple")
public class ChatController {

    @Autowired
    private StreamingChatLanguageModel streamingChatLanguageModel;

    @GetMapping("/chat")
    public Mono<String> chat(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        return Mono.create(sink -> {
            streamingChatLanguageModel.chat(message, new StreamingChatResponseHandler() {

                @Override
                public void onPartialResponse(String partialResponse) {
                    // 忽略
                }

                @Override
                public void onCompleteResponse(ChatResponse completeResponse) {
                    sink.success(completeResponse.aiMessage().text());
                }

                @Override
                public void onError(Throwable error) {
                    sink.error(error);
                }
            });
        });
    }

    @GetMapping("/streamchat")
    public Flux<String> streamChat(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        return Flux.create(sink -> {
            streamingChatLanguageModel.chat(message, new StreamingChatResponseHandler() {
                @Override
                public void onPartialResponse(String partialResponse) {
                    log.debug("partial response: {}", partialResponse);
                    sink.next(partialResponse);
                }

                @Override
                public void onCompleteResponse(ChatResponse completeResponse) {
                    log.info("response: {}", completeResponse);
                    sink.complete();
                }

                @Override
                public void onError(Throwable error) {
                    sink.error(error);
                }
            });
        });
    }

}
