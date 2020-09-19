package net.vrabie.takereactive002.services;

import lombok.RequiredArgsConstructor;
import net.vrabie.takereactive002.data.Reservation;
import net.vrabie.takereactive002.repos.ReservationRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final TransactionalOperator transactionalOperator;

    public Flux<Reservation> saveSomeReservationsService() {
        Flux<String> names = Flux.just("Adrian2", "Eugene Ciorba", "Vitya");
        Flux<Reservation> reservationFlux = names
                .map(name -> new Reservation(name))
                .flatMap(reservationRepo::save);
        return transactionalOperator.transactional(reservationFlux);
    }

}
