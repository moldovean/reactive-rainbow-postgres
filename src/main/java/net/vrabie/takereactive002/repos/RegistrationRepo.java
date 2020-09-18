package net.vrabie.takereactive002.repos;

import net.vrabie.takereactive002.data.Registration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepo extends ReactiveCrudRepository<Registration, Integer> {
}
