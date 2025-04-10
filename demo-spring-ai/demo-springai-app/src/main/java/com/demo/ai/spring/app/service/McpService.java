package com.demo.ai.spring.app.service;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClient;
import org.springframework.ai.model.function.FunctionCallback;
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
public class McpService {

    private static final String MODEL_NAME = "qwen-max-latest";

    private final ChatClient toolChatClient;

    public McpService(ChatClient toolChatClient) {
        this.toolChatClient = toolChatClient;
    }

    public Mono<String> callMcp(String message) {
        ChatClient.ChatClientRequestSpec requestSpec = toolChatClient.prompt(message)
                .options(DashScopeChatOptions.builder().withModel(MODEL_NAME).build());
        this.logLlmRequest(requestSpec);
        return requestSpec.stream()
                .content()
                .collect(Collectors.joining());
    }

    public Flux<String> callMcpStream(String message) {
        ChatClient.ChatClientRequestSpec requestSpec = toolChatClient.prompt(message)
                .options(DashScopeChatOptions.builder().withModel(MODEL_NAME).build());
        this.logLlmRequest(requestSpec);
        return requestSpec.stream()
                .content();
    }

    void logLlmRequest(ChatClient.ChatClientRequestSpec requestSpec) {
        if (requestSpec instanceof DefaultChatClient.DefaultChatClientRequestSpec item) {
            log.info("McpService.callMcp: functionCallbacks: {}", formatFunctionCallbacks(item.getFunctionCallbacks()));
        }
    }

    String formatFunctionCallbacks(List<FunctionCallback> functionCallbacks) {
        if (functionCallbacks == null || functionCallbacks.isEmpty()) {
            return "[]";
        }
        return "[" + functionCallbacks.stream().map(this::formatFunctionCallback).collect(Collectors.joining(",")) + "]";
    }

    String formatFunctionCallback(FunctionCallback functionCallback) {
        return String.format("{\"name\":\"%s\",\"description\":\"%s\"}", functionCallback.getName(), functionCallback.getDescription());
    }

}
