package com.demo.ai.mcp.client;

import org.springframework.ai.autoconfigure.mcp.client.SseHttpClientTransportAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {SseHttpClientTransportAutoConfiguration.class})
public class McpClientStarterWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpClientStarterWebfluxApplication.class, args);
    }

}
