package Marouan.CreditSimulator.Presentation;

import Marouan.CreditSimulator.Metier.ICreditMetier;
import lombok.Data;
@Data
public class CreditControleur implements ICreditControleur {
    ICreditMetier creditMetier;
    @Override
    public void afficherMensualité(Long idCredit){
        var credit = creditMetier.calculerMenseualite(idCredit);
        System.out.println(credit);

    }
}
