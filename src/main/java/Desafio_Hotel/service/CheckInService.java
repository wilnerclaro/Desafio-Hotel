package Desafio_Hotel.service;

import Desafio_Hotel.domain.checkIn.CheckIn;
import Desafio_Hotel.domain.checkIn.CheckInDTO;
import Desafio_Hotel.domain.hospede.HospedeResponseDTO;
import Desafio_Hotel.exception.ValidationException;
import Desafio_Hotel.mapper.CheckInMapper;
import Desafio_Hotel.repository.CheckInRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;
    private final CheckInMapper checkInMapper;
    private final HospedesService hospedesService;
    private Double valorTotal = 0.0;

    public CheckInService(CheckInRepository checkInRepository, CheckInMapper checkInMapper, HospedesService hospedesService) {
        this.checkInRepository = checkInRepository;
        this.checkInMapper = checkInMapper;
        this.hospedesService = hospedesService;
    }

    public CheckInDTO fazerCheckIn(CheckInDTO checkInDTO) {
        CheckInDTO checkIn = validaCheckIn(checkInDTO);
        return checkIn;
    }

    private CheckInDTO validaCheckIn(CheckInDTO checkInDTO) {
        HospedeResponseDTO validaSeDeveCriarHospede = hospedesService.criarHospede(checkInDTO.hospede());
        CheckIn checkInEntity = checkInMapper.convertToEntity(checkInDTO, validaSeDeveCriarHospede);
        checkInRepository.save(checkInEntity);
        CheckInDTO checkIn = checkInMapper.convertToDTO(checkInEntity, valorTotal);
        return checkIn;
    }

    public CheckInDTO fazerCheckOut(CheckInDTO checkInDTO, Long id) {
        return checkOut(checkInDTO, id);

    }

    private CheckInDTO checkOut(CheckInDTO checkInDTO, Long id) {
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Check-in não encontrado"));

        CheckIn checkOutUpdate = checkInMapper.converteToEntityUpdate(checkInDTO, checkIn, id);

        valorTotal = CalculoDiarias.calcularTotal(checkOutUpdate);
        checkInRepository.save(checkOutUpdate);
        checkInDTO = checkInMapper.convertToDTO(checkOutUpdate, valorTotal);
        return checkInDTO;
    }


}
