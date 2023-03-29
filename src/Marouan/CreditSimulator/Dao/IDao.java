package Marouan.CreditSimulator.Dao;

import java.util.List;

public interface IDao<T,ID> {
    T  trouverParId(ID id);
    List<T> trouverTous();
    T Enregistrer(T t);
    T Modifier(T t);
    boolean Supprimer(T t);
    boolean SupprimerParId(ID id);
}
