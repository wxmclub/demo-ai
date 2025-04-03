package com.demo.ai.spring.app.controller;

import java.util.Optional;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
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
 * @date 2025-04-03
 */
@RestController
@RequestMapping("/bailian/chat")
public class BailianController {

    private DashScopeChatModel dashScopeChatModel;

    public BailianController(DashScopeChatModel dashScopeChatModel) {
        this.dashScopeChatModel = dashScopeChatModel;
    }

    Prompt createPrompt(String message) {
        return new Prompt(message);
    }

    @GetMapping(value = "call")
    public Mono<String> chat(@RequestParam(value = "message") String message) {
        System.out.println("message: " + message);
        return dashScopeChatModel.stream(message)
                .collectList()
                .map(v -> String.join("", v));
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam(value = "message") String message) {
        System.out.println("message: " + message);
        return dashScopeChatModel.stream(createPrompt(message))
                .map(v -> {
                    AssistantMessage result = Optional.ofNullable(v.getResult()).map(Generation::getOutput).orElse(null);
                    if (result == null) {
                        return "";
                    } else {
                        // 获取思考
                        String reasoningContent = (String) result.getMetadata().get("reasoningContent");
                        if (reasoningContent != null && !reasoningContent.isEmpty()) {
                            return reasoningContent;
                        }
                        return result.getText();
                    }
                });
    }

}
