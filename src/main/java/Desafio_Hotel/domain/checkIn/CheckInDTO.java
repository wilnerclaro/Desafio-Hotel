package Desafio_Hotel.domain.checkIn;

import Desafio_Hotel.domain.hospede.HospedeRequestDTO;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CheckInDTO(HospedeRequestDTO hospede, LocalDateTime dataEntrada, LocalDateTime dataSaida,
                         Boolean adicionalVeiculo, Double valorTotal) {

}
