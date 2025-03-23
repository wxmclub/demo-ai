package com.demo.ai.mcp.client.controller;

import java.util.List;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxiaoming
 * @version 1.0
 * @date 2025-03-23
 */
@RestController
@RequestMapping("/mcp")
public class McpController {

    @Autowired
    private List<McpSyncClient> mcpSyncClients;

    @GetMapping("/hello")
    public String helloClients() {
        for (McpSyncClient mcpSyncClient : mcpSyncClients) {
            System.out.println("clientInfo: " + mcpSyncClient.getClientInfo());
            System.out.println("serverInfo: " + mcpSyncClient.getServerInfo());
            System.out.println("tools: " + mcpSyncClient.listTools());
            //mcpSyncClient.listTools();
            //mcpSyncClient.callTool()
            System.out.println("----------------");
        }

        return "hello";
    }

}
