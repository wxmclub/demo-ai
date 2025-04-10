package com.demo.ai.spring.app.service;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-04-10
 */
@Slf4j
@Service
public class DashScopeService {

    private final DashScopeChatModel dashScopeChatModel;

    public DashScopeService(DashScopeChatModel dashScopeChatModel) {
        this.dashScopeChatModel = dashScopeChatModel;
    }

    Prompt createPrompt(String message) {
        return new Prompt(message);
    }

    public Mono<String> simpleChat(String message) {
        log.info("DashScopeService.simpleChat: {}", message);
        return Mono.just(this.dashScopeChatModel.call(message));
    }

    public Flux<String> simpleChatStream(String message) {
        log.info("DashScopeService.simpleChatStream: {}", message);
        return this.dashScopeChatModel.stream(message);
    }

}
