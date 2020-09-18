package net.vrabie.takereactive002.repos;

import net.vrabie.takereactive002.data.Reservation;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReservationRepo extends ReactiveCrudRepository<Reservation, Integer> {

    @Query("SELECT * FROM reservation WHERE (name) = ($1)")
    Flux<Reservation> findByName(String name);
}
