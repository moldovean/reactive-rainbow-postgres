package net.vrabie.takereactive002.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GreetingRouter {

    @Bean
    RouterFunction<ServerResponse> greetingsRouter() {
        return RouterFunctions.route()
                .GET("/greeting/{name}", helloWorldHandlerFunction())
        .build();
    }

    private HandlerFunction<ServerResponse> helloWorldHandlerFunction() {
        HandlerFunction<ServerResponse> helloWorldHandler = request ->
        {
            String name = request.pathVariable("name");
            return ServerResponse
                    .ok()
                    .body(Mono.just("Hello from Reactive " + name), String.class);
        };
        return helloWorldHandler;
    }
}
