package Desafio_Hotel.domain.hospede;

import lombok.Builder;


@Builder
public record HospedeResponseDTO(String nome, String telefone) {
}
