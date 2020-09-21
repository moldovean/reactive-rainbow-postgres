package net.vrabie.takereactive002.controllers.ws;

import lombok.extern.log4j.Log4j2;
import net.vrabie.takereactive002.daos.GreetingsRequest;
import net.vrabie.takereactive002.daos.GreetingsResponse;
import net.vrabie.takereactive002.services.GreetingsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
@Log4j2
public class GreetingWebSocketsConfig {
    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of("/ws/greetings", webSocketHandler), 10);
    }

    @Bean
    WebSocketHandler webSocketHandler(GreetingsService greetingsService) {
        return new WebSocketHandler() {
            @Override
            public Mono<Void> handle(WebSocketSession session) {
                Flux<WebSocketMessage> receive = session.receive();
                Flux<String> names = receive.map(WebSocketMessage::getPayloadAsText);
                Flux<GreetingsRequest> requestFlux = names.map(GreetingsRequest::new);
                Flux<GreetingsResponse> greetingsResponseFlux = requestFlux.flatMap(greetingsService::greet);
                Flux<String> messageFlux = greetingsResponseFlux.map(GreetingsResponse::getMessage);
                Flux<WebSocketMessage> webSocketMessageFlux = messageFlux.map(session::textMessage);
                messageFlux.doOnEach(stringSignal -> log.info(stringSignal.getType()));
                messageFlux.doFinally(signalType -> log.info("finally: " + signalType.toString()));
                return session.send(webSocketMessageFlux);
            }
        };
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
