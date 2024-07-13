package Desafio_Hotel.domain.checkIn;

import Desafio_Hotel.domain.hospede.Hospede;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CHECKIN_CHECKOUT")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "hospede_id", nullable = false)
    @ToString.Exclude
    private Hospede hospede;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private Boolean adcionarVeiculo;
    private Boolean clienteNoHotel;
    private Double valorTotal;
}
