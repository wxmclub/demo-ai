package com.demo.ai.mcp.sample.client;

import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-03-17
 */
public class ClientSse {

    public static void main(String[] args) {
        var transport = new WebFluxSseClientTransport(
                WebClient.builder()
                        .baseUrl("http://localhost:8001"));
        new SampleClient(transport).run();
    }

}
