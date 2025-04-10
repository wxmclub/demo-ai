package com.demo.ai.spring.app.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-04-10
 */
@Configuration
public class ModelConfig {

    @Bean
    public ChatClient toolChatClient(ChatClient.Builder chatClientBuilder,
                                     ToolCallbackProvider tools) {
        return chatClientBuilder
                .defaultOptions(ChatOptions.builder().model("qwen-max-latest").build())
                .defaultTools(tools)
                .build();
    }

}
