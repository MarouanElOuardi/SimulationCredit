package Marouan.CreditSimulator.Modele;
import lombok.*;
@Data @AllArgsConstructor @NoArgsConstructor
public class Credit {

    private Long id;
    private Double capitale_emprunté;
    private Integer nbr_mois;
    private Double taux;
    private String demandeur;
    private Double mensualité;


}

