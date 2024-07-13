package Desafio_Hotel.service;

import Desafio_Hotel.domain.hospede.Hospede;
import Desafio_Hotel.domain.hospede.HospedeRequestDTO;
import Desafio_Hotel.domain.hospede.HospedeResponseDTO;
import Desafio_Hotel.exception.ValidationException;
import Desafio_Hotel.mapper.HospedeMapper;
import Desafio_Hotel.repository.HospedeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class HospedesService {

    private final HospedeRepository hospedeRepository;
    private final HospedeMapper hospedeMapper;

    public HospedesService(HospedeRepository hospedeRepository, HospedeMapper hospedeMapper) {
        this.hospedeRepository = hospedeRepository;
        this.hospedeMapper = hospedeMapper;
    }

    public HospedeResponseDTO buscarHospedes(String nome, String documento, String telefone) {
        try {
            Hospede hospede = hospedeRepository.buscarHospedes(nome, documento, telefone);
            if (hospede == null) {
                throw new ValidationException("Nenhum hóspede encontrado com os critérios fornecidos.");
            }
            return hospedeMapper.converteToDTO(hospede);
        } catch (Exception e) {
            throw new ValidationException("Falha ao encontrar hospede  : " + e.getMessage());
        }
    }

    public HospedeResponseDTO criarHospede(HospedeRequestDTO hospedeRequestDTO) {
        try {
            Hospede hospedeToEntity = hospedeMapper.converteToEntity(hospedeRequestDTO);
            Hospede novoHospede = ehNovoHospede(hospedeRequestDTO, hospedeToEntity);
            return hospedeMapper.converteToDTO(novoHospede);
        } catch (Exception e) {
            throw new ValidationException("Falha ao criar hospede : " + e.getMessage());
        }
    }

    private Hospede ehNovoHospede(HospedeRequestDTO hospedeRequestDTO, Hospede hospedeToEntity) {
        Hospede novoHospede;
        if (hospedeRepository.buscarHospedes(hospedeRequestDTO.nome(), hospedeRequestDTO.documento(), hospedeRequestDTO.telefone()) == null) {
            novoHospede = hospedeRepository.save(hospedeToEntity);
            return novoHospede;
        } else {
            return verificaExistenciaHospede(hospedeToEntity.getDocumentoHospede());
        }
    }

    public List<HospedeResponseDTO> listarHospedes() {
        List<Hospede> hospedes;
        try {
            hospedes = hospedeRepository.findAll();
            return hospedes.stream().map(hospedeMapper::converteToDTO).toList();
        } catch (Exception e) {
            throw new ValidationException("Falha ao listar hospedes : " + e.getMessage());
        }

    }

    public HospedeResponseDTO atualizarHospede(Long id, HospedeRequestDTO hospedeRequestDTO) {
        try {
            Hospede exiteHospede = verificaExistenciaHospede(hospedeRequestDTO.documento());
            Hospede hospedeUpdate = hospedeMapper.converteToEntityUpdate(hospedeRequestDTO, exiteHospede, id);
            return hospedeMapper.converteToDTO(hospedeRepository.save(hospedeUpdate));
        } catch (Exception e) {
            throw new ValidationException("Falha ao atualizar hospede");
        }
    }

    private Hospede verificaExistenciaHospede(String documento) {
        Hospede findHospedeByDocumento = hospedeRepository.findByDocumentoHospede(documento).orElseThrow(() -> new ValidationException("Nenhum hospede encontrado!"));
        return findHospedeByDocumento;
    }

    public void removerHospede(String documento) {
        if (verificaExistenciaHospede(documento) == null) {
            throw new ValidationException("Nenhum hospede encontrado");
        } else {
            hospedeRepository.deleteHospedeByDocumentoHospede(documento);
        }
    }


}
