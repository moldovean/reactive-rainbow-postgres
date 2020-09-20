package net.vrabie.takereactive002.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Service
public class GreetingsService {
    Flux<GreetingsResponse> greet(GreetingsRequest greetingsRequest) {
        return Flux.fromStream(Stream.generate(() ->
                new GreetingsResponse("Hello " + greetingsRequest.getName() + " @ " + Instant.now())))
                .delayElements(Duration.ofMillis(600));

    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class GreetingsRequest {
    private String name;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class GreetingsResponse {
    private String name;
}
