package com.bmo.common.auth_service.client.config;

import com.bmo.common.auth_service.client.AuthServiceReactiveClientImpl;
import com.bmo.common.auth_service.client.config.properties.AuthServiceProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthServiceProperties.class)
@Slf4j
public class AuthServiceReactiveClientAutoconfiguration {

  private final AuthServiceProperties properties;

  @Bean
  public AuthServiceReactiveClientImpl authServiceReactiveClient(WebClient.Builder webClientBuilder) {
    return new AuthServiceReactiveClientImpl(createWebClient(webClientBuilder), properties.getPaths());
  }

  private WebClient createWebClient(WebClient.Builder webClientBuilder) {
    var objectMapper = createObjectMapper();

    return webClientBuilder
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .baseUrl(properties.getBaseUrl())
        .clientConnector(createHttpClient(properties.getMaxIdleTime()))
        .codecs(clientDefaultCodecsConfigurer -> {
          clientDefaultCodecsConfigurer.defaultCodecs()
              .jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
          clientDefaultCodecsConfigurer.defaultCodecs()
              .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
          clientDefaultCodecsConfigurer.defaultCodecs().maxInMemorySize(properties.getMaxInMemorySizeBytes());
        })
        .filters(exchangeFilterFunctions -> {
          exchangeFilterFunctions.add(logRequest());
        })
        .build();
  }

  private static ExchangeFilterFunction logRequest() {
    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
      StringBuilder sb = new StringBuilder("\nRequest: \n");
      sb.append(String.format("%s %s \n", clientRequest.method(), clientRequest.url()));
      clientRequest.headers()
          .forEach((name, values) -> values.forEach(value -> sb.append(String.format("%s=%s \n", name, value))));
      log.info(sb.toString());
      return Mono.just(clientRequest);
    });
  }

  private ObjectMapper createObjectMapper() {
    return new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  private ClientHttpConnector createHttpClient(Duration maxIdleTime) {
    var connectionProvider = ConnectionProvider.builder("auth-service-reactive-client")
        .maxIdleTime(maxIdleTime)
        .build();

    var httpClient = HttpClient.create(connectionProvider)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) properties.getConnectionTimeout().toMillis())
        .doOnConnected(connection ->
            connection.addHandlerLast(
                new ReadTimeoutHandler(properties.getReadTimeout().toMillis(), TimeUnit.MILLISECONDS))
        );

    if (properties.isHttpLoggingEnabled()) {
      httpClient.wiretap("auth-service-reactive-client", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
      return new LoggingClientHttpConnectorDecorator(new ReactorClientHttpConnector(httpClient));
    }

    return new ReactorClientHttpConnector(httpClient);
  }

}
