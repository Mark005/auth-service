package com.bmo.common.auth_service.client.config;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.client.reactive.ClientHttpRequestDecorator;
import reactor.core.publisher.Mono;

@Slf4j
public class LoggingClientHttpRequestDecorator extends ClientHttpRequestDecorator {

  public LoggingClientHttpRequestDecorator(ClientHttpRequest delegate) {
    super(delegate);
  }

  @Override
  public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
    body = DataBufferUtils.join(body).doOnNext(content -> log.debug(content.toString(StandardCharsets.UTF_8)));
    return super.writeWith(body);
  }
}
