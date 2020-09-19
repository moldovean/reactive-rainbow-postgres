package net.vrabie.takereactive002.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.vrabie.takereactive002.data.Reservation;
import net.vrabie.takereactive002.repos.RegistrationRepo;
import net.vrabie.takereactive002.repos.ReservationRepo;
import net.vrabie.takereactive002.services.DbClientServiceExample;
import net.vrabie.takereactive002.services.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class HelloWorldReactive {
    private final ReservationRepo reservationRepo;
    private final DbClientServiceExample dbClientServiceExample;
    private final ReservationService reservationService;

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
                .map(reservation -> reservation.getName() + " ")
                .delayElements(Duration.ofMillis(500));
    }

    @GetMapping("/test3")
    public Flux<Reservation> getReservationsFromService() {
        return dbClientServiceExample.getAllReservations()
                .doOnComplete(()-> log.info("/test3 was called and finished! -------"));
    }

    @GetMapping("/helloReactive")
    public Flux<String> helloReactive() {

        List<String> data = List.of("{A}", "{B}", "{C}", "{D}", "{E}");
        return Flux.fromStream(data.stream())
                .delayElements(Duration.ofMillis(1200));
    }

    @GetMapping("/reservations")
    public Flux<Reservation> makeSomeReservations() {

        return reservationService.saveSomeReservationsService()
                .delayElements(Duration.ofMillis(600));
    }


}
