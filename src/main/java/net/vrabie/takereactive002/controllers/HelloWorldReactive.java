package net.vrabie.takereactive002.controllers;

import lombok.RequiredArgsConstructor;
import net.vrabie.takereactive002.data.Reservation;
import net.vrabie.takereactive002.repos.RegistrationRepo;
import net.vrabie.takereactive002.repos.ReservationRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.MILLIS;

@RestController
@RequiredArgsConstructor
public class HelloWorldReactive {
    private final RegistrationRepo registrationRepo;
    private final ReservationRepo reservationRepo;
    @GetMapping("/hello")
    public Mono<String> helloWorld() {
        return Mono.just("Hello World Take 2");
    }

    @GetMapping("/test")
    public Flux<Reservation> helloReactivePostgres() {
        return reservationRepo.findAll();
    }

    @GetMapping("/search/{name}")
    public Flux<Reservation> helloReactivePostgres(@PathVariable String name) {
        return reservationRepo.findByName(name)
                .delayElements(Duration.ofMillis(500));
    }

    @GetMapping("/test2")
    public Flux<String> helloReactivePostgresTest() {
        return reservationRepo.findAll()
                .map(reservation -> reservation.getName()+" ")
                .delayElements(Duration.ofMillis(500));
    }

    @GetMapping("/helloReactive")
    public Flux<String> helloReactive() {

        List<String> data = List.of("{A}", "{B}", "{C}", "{D}", "{E}");
        return Flux.fromStream(data.stream())
                .delayElements(Duration.ofMillis(1200));
    }



}
