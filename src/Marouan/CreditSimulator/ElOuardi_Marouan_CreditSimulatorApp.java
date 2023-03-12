package Marouan.CreditSimulator;


import Marouan.CreditSimulator.Dao.IDao;
import Marouan.CreditSimulator.Dao.daoVolatile.CreditDao;
import Marouan.CreditSimulator.Metier.ICreditMetier;
import Marouan.CreditSimulator.Modele.Credit;
import Marouan.CreditSimulator.Presentation.ICreditControleur;
import Marouan.CreditSimulator.Presentation.CreditControleur;
import Marouan.CreditSimulator.Metier.CreditMetier;
import java.lang.reflect.Method;
import java.util.Properties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ElOuardi_Marouan_CreditSimulatorApp {
    static ICreditControleur CreditControleur;

    public static void main(String[] args) throws Exception {
        Test3();
    }
    //    ===================================================================================================================================================
    //                                                                    TEST 1
    //    ===================================================================================================================================================
    public static void Test1(){
        IDao<Credit, Long> dao = new CreditDao();
        CreditControleur = new CreditControleur();
        ICreditMetier metier = new CreditMetier();

        ((CreditMetier) metier).setCreditDao(dao);
        ((CreditControleur) CreditControleur).setCreditMetier(metier);
        try{
            CreditControleur.afficherMensualité(1L);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Test 1 Completed");
    }
    //    =================================================================================================================================================
//                                                                    TEST 2
//    =================================================================================================================================================
    public static void Test2(){
        IDao<Credit, Long> dao = null;
        ICreditControleur controleur= null;
        ICreditMetier metier= null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        classLoader.getResourceAsStream("Marouan/CreditControl/config.properties");
        var config = classLoader.getResourceAsStream("Marouan/CreditSimulator/config.properties");
        Properties props = new Properties();
        try
        {
            props.load(config);
            var daoClass = props.getProperty("DAO");
            var serviceClass = props.getProperty("SERVICE");
            var controllerClass = props.getProperty("CONTROLLER");

            Class cDao = Class.forName(daoClass);
            dao = (IDao<Credit, Long>) cDao.getDeclaredConstructor().newInstance();

            Class cMetier = Class.forName(serviceClass);
            metier = (ICreditMetier) cDao.getDeclaredConstructor().newInstance();

            Class cControleur = Class.forName(controllerClass);
            controleur = (ICreditControleur) cDao.getDeclaredConstructor().newInstance();

            //Dependecys Injection

            Method setMetier = cControleur.getMethod("setCreditMetier", ICreditMetier.class);
            setMetier.invoke(controleur,metier);

            Method setDao = cMetier.getMethod("setCreditDao", IDao.class);
            setDao.invoke(metier,dao);


            controleur.afficherMensualité(1L);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            props.clear();
        }
    }
    //    ============================================================================================================================================
    //                                                                    TEST 3
    //    ============================================================================================================================================
    public static void Test3(){
        ApplicationContext application = new ClassPathXmlApplicationContext("sprinc-IOC.xml");
        ICreditControleur controleur = application.getBean(ICreditControleur.class);
        controleur.afficherMensualité(1L);
    }
}