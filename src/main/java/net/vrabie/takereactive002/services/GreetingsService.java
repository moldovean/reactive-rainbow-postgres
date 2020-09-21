package net.vrabie.takereactive002.services;

import net.vrabie.takereactive002.daos.GreetingsRequest;
import net.vrabie.takereactive002.daos.GreetingsResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Service
public class GreetingsService {
    public Flux<GreetingsResponse> greet(GreetingsRequest greetingsRequest) {
        return Flux.fromStream(Stream.generate(() ->
                new GreetingsResponse("Hello " + greetingsRequest.getName() + " @ " + Instant.now())))
                .delayElements(Duration.ofMillis(600))
                .limitRequest(10);

    }
}

