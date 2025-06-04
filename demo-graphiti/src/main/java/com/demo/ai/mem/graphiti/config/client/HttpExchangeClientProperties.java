package com.demo.ai.mem.graphiti.config.client;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-06-04
 */
@Data
@ConfigurationProperties(prefix = "http-exchange-client")
public class HttpExchangeClientProperties implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Config> config;


    @Data
    public static class Config implements Serializable {

        private static final long serialVersionUID = 1L;

        private String url;

        private Duration connectTimeout;

        private Duration readTimeout;

    }
}
