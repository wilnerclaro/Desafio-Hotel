package Desafio_Hotel.controller;

import Desafio_Hotel.domain.hospede.HospedeRequestDTO;
import Desafio_Hotel.domain.hospede.HospedeResponseDTO;
import Desafio_Hotel.service.HospedesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospedes")

public class HospedeController {
    private final HospedesService hospedesService;

    public HospedeController(HospedesService hospedesService) {
        this.hospedesService = hospedesService;
    }

    @Operation(summary = "Buscar Hospede: Deve buscar um hospede por Nome ou Documento ou Telefone", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao  realizar busca dos dados")
    })
    @GetMapping("/hospede")
    public ResponseEntity<HospedeResponseDTO> buscarHospesPorNome(@RequestParam(value = "nome", required = false) String nome,
                                                                  @RequestParam(value = "documento", required = false) String documento,
                                                                  @RequestParam(value = "telefone", required = false) String telefone) {
        return ResponseEntity.ok(hospedesService.buscarHospedes(nome, documento, telefone));
    }

    @Operation(summary = "Cadastra um novo Hospede", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastrado  com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao  realizar busca dos dados")
    })
    @PostMapping("/novo")
    public ResponseEntity<HospedeResponseDTO> criarHospede(@RequestBody HospedeRequestDTO hospedeRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospedesService.criarHospede(hospedeRequestDTO));
    }

    @Operation(summary = "Buscar Todos os Hospedes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao  realizar busca dos dados")
    })
    @GetMapping("/")
    public ResponseEntity<List<HospedeResponseDTO>> listarHospedes() {
        return ResponseEntity.ok(hospedesService.listarHospedes());
    }

    @Operation(summary = "Atualizar um  Hospede", method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atualizado  com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao  realizar busca dos dados")
    })
    @PatchMapping("/atualizar")
    public ResponseEntity<HospedeResponseDTO> atualizarHospede(@RequestParam Long id, @RequestBody HospedeRequestDTO hospedeRequestDTO) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(hospedesService.atualizarHospede(id, hospedeRequestDTO));
    }

    @Operation(summary = "Fazer um delete de hospede", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hospede deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao  realizar busca dos dados")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletarHospede(@RequestParam String documento) {
        hospedesService.removerHospede(documento);
        return ResponseEntity.accepted().build();
    }


}
