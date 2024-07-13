package Desafio_Hotel.service;

import Desafio_Hotel.domain.hospede.Hospede;
import Desafio_Hotel.domain.hospede.HospedeRequestDTO;
import Desafio_Hotel.domain.hospede.HospedeResponseDTO;
import Desafio_Hotel.exception.ValidationException;
import Desafio_Hotel.mapper.HospedeMapper;
import Desafio_Hotel.repository.HospedeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static Desafio_Hotel.builder.HospedeBuilder.umHospede;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HospedesServiceTest {
    @InjectMocks
    HospedesService hospedesService;
    @Mock
    HospedeRepository hospedeRepository;
    @Mock
    HospedeMapper hospedeMapper;

    HospedeResponseDTO hospedeResponseDTO;
    HospedeRequestDTO hospedeRequestDTO;
    Hospede mockHospede;

    @BeforeEach
    void setUp() {
        hospedeResponseDTO = new HospedeResponseDTO("teste", "teste");
        hospedeRequestDTO = new HospedeRequestDTO("teste", "teste", "teste");
        mockHospede = umHospede().agora();
    }

    @Test
    void deveConsultarHospedeComNomeComSucesso() {
        when(hospedeRepository.buscarHospedes(mockHospede.getNomeHospede(), mockHospede.getDocumentoHospede(), mockHospede.getTelefoneHospede())).thenReturn(mockHospede);
        when(hospedeMapper.converteToDTO(mockHospede)).thenReturn(hospedeResponseDTO);
        HospedeResponseDTO buscaHospede = hospedesService.buscarHospedes(mockHospede.getNomeHospede(), mockHospede.getDocumentoHospede(), mockHospede.getTelefoneHospede());

        assertNotNull(buscaHospede);
        assertEquals(hospedeResponseDTO.nome(), buscaHospede.nome());
        verifyNoMoreInteractions(hospedeRepository);
    }

    @Test
    void deveFalharCasoNaoConsigaEncontrarHospede() {
        when(hospedeRepository.buscarHospedes("Inexistente", "000", "111")).thenReturn(null);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> hospedesService.buscarHospedes("Inexistente", "000", "111"));

        assertEquals("Falha ao encontrar hospede  : Nenhum hóspede encontrado com os critérios fornecidos.", exception.getMessage());
        assertTrue(exception.getMessage().contains("Falha ao encontrar hospede  : "));

    }

    @Test
    void deveCriarUmHospedeComSucesso() {
        when(hospedeRepository.save(mockHospede)).thenReturn(mockHospede);
        when(hospedeMapper.converteToDTO(mockHospede)).thenReturn(hospedeResponseDTO);
        when(hospedeMapper.converteToEntity(hospedeRequestDTO)).thenReturn(mockHospede);
        HospedeResponseDTO criarHospede = hospedesService.criarHospede(hospedeRequestDTO);

        assertNotNull(hospedeResponseDTO);
        assertEquals(hospedeResponseDTO.nome(), criarHospede.nome());
        verifyNoMoreInteractions(hospedeRepository);

    }

    @Test
    void deveFalharCasoNaoCriarUmHospede() {
        when(hospedeRepository.save(any(Hospede.class))).thenThrow(new ValidationException("Falha ao criar hospede : "));
        ValidationException exception = assertThrows(ValidationException.class,
                () -> hospedesService.criarHospede(hospedeRequestDTO));

        assertTrue(exception.getMessage().contains("Falha ao criar hospede : "));
    }
}