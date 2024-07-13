package Desafio_Hotel.mapper;

import Desafio_Hotel.domain.hospede.Hospede;
import Desafio_Hotel.domain.hospede.HospedeRequestDTO;
import Desafio_Hotel.domain.hospede.HospedeResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class HospedeMapper {

    public HospedeResponseDTO converteToDTO(Hospede hospedeEntity) {
        return HospedeResponseDTO.builder()
                .nome(hospedeEntity.getNomeHospede())
                .telefone(hospedeEntity.getTelefoneHospede())
                .build();
    }

    public Hospede converteToEntity(HospedeRequestDTO hospedeRequestDTO) {
        return Hospede.builder()
                .nomeHospede(hospedeRequestDTO.nome())
                .documentoHospede(hospedeRequestDTO.documento())
                .telefoneHospede(hospedeRequestDTO.telefone())
                .build();
    }

    public Hospede converteToEntityUpdate(HospedeRequestDTO hospedeRequestDTO, Hospede hospedeEntity, Long id) {
        return Hospede.builder()
                .idHospede(id)
                .nomeHospede(hospedeRequestDTO.nome() != null ? hospedeRequestDTO.nome() : hospedeEntity.getNomeHospede())
                .documentoHospede(hospedeRequestDTO.documento() != null ? hospedeRequestDTO.documento() : hospedeEntity.getDocumentoHospede())
                .telefoneHospede(hospedeRequestDTO.telefone() != null ? hospedeRequestDTO.telefone() : hospedeEntity.getTelefoneHospede())
                .build();
    }

    public HospedeRequestDTO converteToRequestDTO(Hospede entity) {
        return new HospedeRequestDTO(entity.getNomeHospede(), entity.getDocumentoHospede(), entity.getTelefoneHospede());
    }

   
}
