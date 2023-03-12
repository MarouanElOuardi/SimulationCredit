package Marouan.CreditSimulator.Metier;

import Marouan.CreditSimulator.Dao.IDao;
import Marouan.CreditSimulator.Modele.Credit;
import lombok.Data;

@Data
public class CreditMetier implements ICreditMetier {

    IDao<Credit,Long> creditDao;
    public Credit calculerMenseualite(Long idcredit) {

        var credit= creditDao.trouverParId(idcredit);
        if(credit==null)
            throw new RuntimeException("Credit introuvable");
        else {
            double mensualité=0.0;
            double taux=credit.getTaux()/1200;
            double capital=credit.getCapitale_emprunté();
            double nbr_mois=credit.getNbr_mois();
            mensualité=Math.round((capital*taux/(1-Math.pow(1+taux,-nbr_mois))*100/100));
            credit.setMensualité(mensualité);
        }

        return credit;
    }

    @Override
    public void calculerMensualité(Long idCredit) {

    }

}
