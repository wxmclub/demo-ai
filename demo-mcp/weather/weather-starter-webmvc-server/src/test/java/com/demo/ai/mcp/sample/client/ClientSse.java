package com.demo.ai.mcp.sample.client;

import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-03-17
 */
public class ClientSse {

    public static void main(String[] args) {
        var transport = new HttpClientSseClientTransport("http://localhost:8002");
        new SampleClient(transport).run();
    }

}
