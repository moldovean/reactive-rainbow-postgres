package net.vrabie.takereactive002.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.vrabie.takereactive002.daos.GreetingsRequest;
import net.vrabie.takereactive002.daos.GreetingsResponse;
import net.vrabie.takereactive002.data.Reservation;
import net.vrabie.takereactive002.repos.ReservationRepo;
import net.vrabie.takereactive002.services.GreetingsService;
import net.vrabie.takereactive002.services.ReservationService;
import net.vrabie.takereactive002.services.lowlevel.DbClientServiceExample;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@EnableWebFlux
@RestController
@RequiredArgsConstructor
@Log4j2
public class HelloWorldReactive {
    private final ReservationRepo reservationRepo;
    private final DbClientServiceExample dbClientServiceExample;
    private final ReservationService reservationService;
    private final GreetingsService greetingsService;

    @GetMapping("/hello")
    public Mono<String> helloWorld() {
        return Mono.just("Hello World Take 2");
    }


    @GetMapping("/search/{name}")
    public Flux<Reservation> helloReactivePostgres(@PathVariable String name) {
        return reservationRepo.findByName(name)
                .delayElements(Duration.ofMillis(500));
    }


    @GetMapping("/findAll")
    public Flux<Reservation> getReservationsFromService() {
        return dbClientServiceExample.getAllReservations()
                .doOnComplete(() -> log.info("/test3 was called and finished! -------"));
    }


    @GetMapping("/reservations")
    public Flux<Reservation> makeSomeReservations() {

        return reservationService.saveSomeReservationsService("Adrian2", "Eugene Ciorba", "Vitya");
    }


    @GetMapping(path = "/greetings/{name}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<GreetingsResponse> fulxOfGreetings(@PathVariable String name) {
        return greetingsService.greet(new GreetingsRequest(name));
    }

}
