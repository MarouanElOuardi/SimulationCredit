package Marouan.CreditSimulator.Dao;

public interface IDao<T,ID> {
    T  trouverParId(ID id);
}
