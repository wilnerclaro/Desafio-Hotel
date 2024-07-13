package Desafio_Hotel.repository;

import Desafio_Hotel.domain.checkIn.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    //List<CheckIn> buscaTodosPelaSaida(LocalDateTime agora);
}
