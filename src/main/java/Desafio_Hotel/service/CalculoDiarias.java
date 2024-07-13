package Desafio_Hotel.service;

import Desafio_Hotel.domain.checkIn.CheckIn;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalculoDiarias {
    private static final double DIARIA_SEMANA = 120.0;
    private static final double DIARIA_FIM_SEMANA = 150.0;
    private static final double GARAGEM_SEMANA = 15.0;
    private static final double GARAGEM_FIM_SEMANA = 20.0;
    private static final LocalTime HORARIO_LIMITE_DIARIA = LocalTime.of(16, 30);

    public static double calcularTotal(CheckIn checkOut) {
        double total = 0.0;
        LocalDateTime dataAtual = checkOut.getDataEntrada();
        LocalDateTime dataSaida = checkOut.getDataSaida();
        Boolean adicionalVeiculo = checkOut.getAdcionarVeiculo();

        while (dataAtual.toLocalDate().isBefore(dataSaida.toLocalDate()) || dataAtual.toLocalDate().isEqual(dataSaida.toLocalDate())) {
            total += calcularValorDiaria(dataAtual, adicionalVeiculo);
            dataAtual = dataAtual.plusDays(1);
        }

        if (dataSaida.toLocalTime().isAfter(HORARIO_LIMITE_DIARIA)) {
            total += calcularValorDiaria(dataSaida, adicionalVeiculo);
        }

        return total;
    }

    private static double calcularValorDiaria(LocalDateTime data, boolean adicionalVeiculo) {
        boolean ehFimDeSemana = (data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY);
        double valorDiaria = ehFimDeSemana ? DIARIA_FIM_SEMANA : DIARIA_SEMANA;
        double valorGaragem = ehFimDeSemana ? GARAGEM_FIM_SEMANA : GARAGEM_SEMANA;
        return valorDiaria + (adicionalVeiculo ? valorGaragem : 0);
    }
}
