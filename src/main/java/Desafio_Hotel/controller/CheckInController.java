package Desafio_Hotel.controller;

import Desafio_Hotel.domain.checkIn.CheckInDTO;
import Desafio_Hotel.service.CheckInService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkIn")
public class CheckInController {

    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @Operation(summary = "Fazer CheckIn", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastrado  com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao  realizar busca dos dados")
    })
    @PostMapping("")
    public ResponseEntity<CheckInDTO> fazerCheckIn(@RequestBody CheckInDTO checkInDTO) {
        CheckInDTO createdCheckIn = checkInService.fazerCheckIn(checkInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCheckIn);
    }

    @Operation(summary = "Fazer CheckOut", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizado  com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao  realizar busca dos dados")
    })
    @PostMapping("/checkout/{checkInId}")
    public ResponseEntity<CheckInDTO> fazerCheckOut(@RequestBody CheckInDTO checkInDTO, @RequestParam Long id) {
        CheckInDTO checkOut = checkInService.fazerCheckOut(checkInDTO, id);
        return ResponseEntity.ok(checkOut);
    }
}
