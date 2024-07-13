package Desafio_Hotel.domain.hospede;

import lombok.Builder;

@Builder
public record HospedeRequestDTO(String nome, String documento, String telefone) {
}
