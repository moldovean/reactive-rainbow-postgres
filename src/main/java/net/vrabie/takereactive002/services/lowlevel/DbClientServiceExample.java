package net.vrabie.takereactive002.services.lowlevel;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.vrabie.takereactive002.data.Reservation;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Log4j2
public class DbClientServiceExample {
    private final DatabaseClient databaseClient;

    //using low level DatabaseClient which also looks good
    public Flux<Reservation> getAllReservations() {
        return databaseClient.select()
                .from("reservation")
                .as(Reservation.class)
                .fetch()
                .all();
    }

}
