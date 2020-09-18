package net.vrabie.takereactive002.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.vrabie.takereactive002.data.Reservation;
import net.vrabie.takereactive002.repos.ReservationRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Log4j2
public class ReservationDataInit {
    private final ReservationRepo reservationRepo;

    @EventListener(ApplicationReadyEvent.class)
    public void initSomeDbData() {
        Flux<String> names = Flux.just("Adrian", "Ina", "Michael", "Josh", "Violetta", "Tarilyn", "Eugene");
        Flux<Reservation> reservationFlux = names
                .map(name -> new Reservation(name))
                .flatMap(reservationRepo::save);

        reservationRepo
                .deleteAll()
                .thenMany(reservationFlux)
                .thenMany(this.reservationRepo.findAll())
                .subscribe(log::info);
    }
}
