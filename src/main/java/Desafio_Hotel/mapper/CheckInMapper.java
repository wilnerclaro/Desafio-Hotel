package Desafio_Hotel.mapper;

import Desafio_Hotel.domain.checkIn.CheckIn;
import Desafio_Hotel.domain.checkIn.CheckInDTO;
import Desafio_Hotel.domain.hospede.Hospede;
import Desafio_Hotel.domain.hospede.HospedeRequestDTO;
import Desafio_Hotel.domain.hospede.HospedeResponseDTO;
import Desafio_Hotel.repository.HospedeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CheckInMapper {
    private final HospedeMapper hospedeMapper;
    private final HospedeRepository hospedeRepository;

    public CheckInMapper(HospedeMapper hospedeMapper, HospedeRepository hospedeRepository) {
        this.hospedeMapper = hospedeMapper;
        this.hospedeRepository = hospedeRepository;
    }

    private Hospede buscarHospede(HospedeResponseDTO hospedeRequestDTO) {
        return hospedeRepository.buscarHospedes(hospedeRequestDTO.nome(), null, hospedeRequestDTO.telefone());
    }

    public CheckInDTO convertToDTO(CheckIn checkInEntity, Double valorTotal) {
        HospedeRequestDTO hospedeRequestDTO = hospedeMapper.converteToRequestDTO(checkInEntity.getHospede());
        return CheckInDTO.builder()
                .hospede(hospedeRequestDTO)
                .dataEntrada(checkInEntity.getDataEntrada())
                .dataSaida(checkInEntity.getDataSaida())
                .adicionalVeiculo(checkInEntity.getAdcionarVeiculo())
                .valorTotal(valorTotal.equals(null) ? checkInEntity.getValorTotal() : valorTotal)
                .build();
    }

    public CheckIn convertToEntity(CheckInDTO checkInDTO, HospedeResponseDTO hospede) {
        return CheckIn.builder()
                .hospede(buscarHospede(hospede))
                .clienteNoHotel(true)
                .adcionarVeiculo(checkInDTO.adicionalVeiculo())
                .dataEntrada(LocalDateTime.now())
                .dataSaida(checkInDTO.dataSaida())
                .valorTotal(checkInDTO.valorTotal())
                .build();
    }

    public CheckIn converteToEntityUpdate(CheckInDTO checkInDTO, CheckIn checkInEntity, Long id) {
        HospedeResponseDTO hospedeRequestDTO = hospedeMapper.converteToDTO(checkInEntity.getHospede());
        return CheckIn.builder()
                .hospede(buscarHospede(hospedeRequestDTO))
                .id(id)
                .dataEntrada(checkInDTO.dataEntrada() != null ? checkInDTO.dataEntrada() : checkInEntity.getDataEntrada())
                .adcionarVeiculo(checkInDTO.adicionalVeiculo() != null ? checkInDTO.adicionalVeiculo() : checkInEntity.getAdcionarVeiculo())
                .clienteNoHotel(false)
                .dataSaida(LocalDateTime.now())
                .build();
    }
}

