package Marouan.CreditSimulator.Dao.daoVolatile;

import Marouan.CreditSimulator.Dao.IDao;
import Marouan.CreditSimulator.Modele.Credit;
import lombok.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CreditDao implements IDao <Credit, Long>{

        public Credit trouverParId(Long id) {
           return BD_Credits().stream().filter(credit -> credit.getId()==id).findFirst().get();
        }

    @Override
    public List<Credit> trouverTous() {
            return BD_Credits().stream().toList();
    }

    @Override
    public Credit Enregistrer(Credit credit) {
            return null;
    }

    @Override
    public Credit Modifier(Credit credit) {
        return null;
    }

    @Override
    public boolean Supprimer(Credit credit) {
        return false;
    }

    @Override
    public boolean SupprimerParId(Long aLong) {
        return false;
    }

    static Set<Credit> BD_Credits(){
            var credits= new HashSet<Credit>(
                    Arrays.asList(
                            new Credit(1L, 3000000.0, 120, 2.5, "Cj", 0.0),
                            new Credit(2L, 8500000.0, 240, 2.5, "Marouan", 0.0),
                            new Credit(3L, 20000.0, 30, 1.5, "Pinky", 0.0)
                    ));
            return credits;

        }
}

