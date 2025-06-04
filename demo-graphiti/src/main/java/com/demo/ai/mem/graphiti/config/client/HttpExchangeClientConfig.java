package com.demo.ai.mem.graphiti.config.client;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import com.demo.ai.mem.graphiti.client.graphiti.api.IGraphitiServerApi;
import io.netty.channel.ChannelOption;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

/**
 * @author wxmclub
 * @version 1.0
 * @date 2025-06-03
 */
@EnableConfigurationProperties(HttpExchangeClientProperties.class)
@Configuration
public class HttpExchangeClientConfig {

    HttpExchangeClientProperties.Config pickDefaultConfig(HttpExchangeClientProperties properties) {
        return this.pickConfig(properties, "default");
    }

    HttpExchangeClientProperties.Config pickConfig(HttpExchangeClientProperties properties,
                                                   String key) {
        return Optional.ofNullable(properties.getConfig())
                .map(v -> v.get(key))
                .orElse(null);
    }

    <T> T pickConfigValue(HttpExchangeClientProperties.Config config,
                          HttpExchangeClientProperties.Config defaultConfig,
                          Function<HttpExchangeClientProperties.Config, T> readValue) {
        return Optional.ofNullable(config)
                .map(readValue)
                .orElse(Optional.ofNullable(defaultConfig).map(readValue).orElse(null));
    }

    @Bean
    public IGraphitiServerApi graphitiServerApi(HttpExchangeClientProperties properties) {
        HttpExchangeClientProperties.Config config = pickConfig(properties, "graphiti-server");
        HttpExchangeClientProperties.Config defaultConfig = pickDefaultConfig(properties);
        String url = config.getUrl();
        Duration connectTimeout = pickConfigValue(config, defaultConfig, HttpExchangeClientProperties.Config::getConnectTimeout);
        Duration readTimeout = pickConfigValue(config, defaultConfig, HttpExchangeClientProperties.Config::getReadTimeout);

        return this.createApi(IGraphitiServerApi.class,
                url,
                connectTimeout,
                readTimeout,
                null
        );
    }

    <T> T createApi(Class<T> clazz,
                    String url,
                    Duration connectTimeout,
                    Duration socketTimeout,
                    Consumer<WebClient.Builder> webClientBuilderConsumer) {
        HttpClient client = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Optional.ofNullable(connectTimeout).map(Duration::toMillis).map(Long::intValue).orElse(null))
                .responseTimeout(socketTimeout);
        WebClient.Builder webClientBuilder = WebClient.builder()
                .baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(client));
        if (webClientBuilderConsumer != null) {
            webClientBuilderConsumer.accept(webClientBuilder);
        }

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClientBuilder.build()))
                .build();
        return factory.createClient(clazz);
    }

}
