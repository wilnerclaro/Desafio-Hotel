package Desafio_Hotel.builder;

import Desafio_Hotel.domain.hospede.Hospede;


public class HospedeBuilder {
    private Hospede elemento;

    private HospedeBuilder() {
    }

    public static HospedeBuilder umHospede() {
        HospedeBuilder builder = new HospedeBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(HospedeBuilder builder) {
        builder.elemento = new Hospede();
        Hospede elemento = builder.elemento;


        elemento.setIdHospede(1L);
        elemento.setNomeHospede("teste");
        elemento.setDocumentoHospede("teste");
        elemento.setTelefoneHospede("teste");
    }

    public HospedeBuilder comIdHospede(Long param) {
        elemento.setIdHospede(param);
        return this;
    }

    public HospedeBuilder comNomeHospede(String param) {
        elemento.setNomeHospede(param);
        return this;
    }

    public HospedeBuilder comDocumentoHospede(String param) {
        elemento.setDocumentoHospede(param);
        return this;
    }

    public HospedeBuilder comTelefoneHospede(String param) {
        elemento.setTelefoneHospede(param);
        return this;
    }

    public Hospede agora() {
        return elemento;
    }
}
