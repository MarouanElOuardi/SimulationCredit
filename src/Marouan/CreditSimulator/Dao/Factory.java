package Marouan.CreditSimulator.Dao;

import Marouan.CreditSimulator.Modele.Credit;
import com.mysql.cj.xdevapi.Client;

public abstract class Factory {

    public static final int DAO_VOLATILE = 1;
    public static final int DAO_FILES = 2;
    public static final int DAO_MYSQL = 3;

    public abstract  IDao<Credit, Long> getCreditDao();

    public static Factory getFactory(int type){
        switch (type){
            case DAO_VOLATILE:
               // return FactoryVolatile.getInstance();
            case DAO_FILES:
               // return FactoryFiles.getInstance();
            case DAO_MYSQL:
                return FactoryMySQL.Instance;
            default:
                return null;
        }
    }
}
