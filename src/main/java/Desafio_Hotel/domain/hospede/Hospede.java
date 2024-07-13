package Desafio_Hotel.domain.hospede;

import Desafio_Hotel.domain.checkIn.CheckIn;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "HOSPEDES")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHospede;
    @Column(name = "nome")
    private String nomeHospede;
    @Column(name = "documento")
    private String documentoHospede;
    @Column(name = "telefone")
    private String telefoneHospede;
    @OneToMany(mappedBy = "hospede")
    @ToString.Exclude
    private List<CheckIn> checkInList;
}
