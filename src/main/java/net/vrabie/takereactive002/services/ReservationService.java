package net.vrabie.takereactive002.services;

import io.r2dbc.postgresql.util.Assert;
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

    public Flux<Reservation> saveSomeReservationsService(String ... names) {
        Flux<Reservation> reservationFlux = Flux.fromArray(names)
                .map(name -> new Reservation(name))
                .flatMap(reservationRepo::save)
                .doOnNext(reservation -> validate(reservation));
        return transactionalOperator.transactional(reservationFlux);
    }

    private void validate(Reservation reservation) {
        Assert.isTrue(Character.isUpperCase(reservation.getName().charAt(0)), "Must start with upper case!");
    }

}
