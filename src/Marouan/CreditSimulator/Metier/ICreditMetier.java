package Marouan.CreditSimulator.Metier;

import Marouan.CreditSimulator.Modele.Credit;

public interface ICreditMetier {


    void calculerMensualité(Long idCredit);

    Credit calculerMenseualite(Long idCredit);
}
