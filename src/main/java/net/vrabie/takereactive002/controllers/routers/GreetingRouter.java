package net.vrabie.takereactive002.controllers.routers;

import lombok.RequiredArgsConstructor;
import net.vrabie.takereactive002.daos.GreetingsRequest;
import net.vrabie.takereactive002.daos.GreetingsResponse;
import net.vrabie.takereactive002.services.GreetingsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class GreetingRouter {

    private final GreetingsService greetingsService;

    @Bean
    RouterFunction<ServerResponse> greetingsRouter() {
        return RouterFunctions.route()
                .GET("/greeting/{name}", helloWorldHandlerFunction(greetingsService))
                .build();
    }


    // this can be moved in the services
    private HandlerFunction<ServerResponse> helloWorldHandlerFunction(GreetingsService greetingsService) {
        HandlerFunction<ServerResponse> helloWorldHandler = request ->
        {
            String name = request.pathVariable("name");
            GreetingsRequest greetingsRequest = new GreetingsRequest(name);
            return ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_STREAM_JSON)
                    .body(greetingsService.greet(greetingsRequest), GreetingsResponse.class);
        };
        return helloWorldHandler;
    }
}
