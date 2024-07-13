package Desafio_Hotel.repository;

import Desafio_Hotel.domain.hospede.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    Optional<Hospede> findByDocumentoHospede(String documento);

    @Query(value = "SELECT * FROM HOSPEDES  WHERE " +
            "(:nome IS NULL OR NOME = :nome) " +
            "AND (:documento IS NULL OR DOCUMENTO = :documento) " +
            "AND (:telefone IS NULL OR telefone = :telefone)", nativeQuery = true)
    Hospede buscarHospedes(@Param("nome") String nome, @Param("documento") String documento, @Param("telefone") String telefone);

    void deleteHospedeByDocumentoHospede(String documento);
}



