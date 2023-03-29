package Marouan.CreditSimulator.Dao;

import Marouan.CreditSimulator.Dao.daoExceptions.DAOConfigException;
import Marouan.CreditSimulator.Modele.Credit;
import Marouan.CreditSimulator.TestJDBC.Singleton;

import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import Marouan.CreditSimulator.Dao.daoExceptions.DAOException;
import Marouan.CreditSimulator.Dao.daoExceptions.DAOException;




public class FactoryMySQL extends Factory{
    private static final String PROPERTIES_FILE = "Dao/dao.properties",
                                URL = "SDB_URL",
                                DRIVER = "SDB_DRIVER",
                                LOGIN = "SDB_LOGIN",
                                PASSWORD = "SDB_PASSWORD",
                                DB = "DB_NAME";
    public static  FactoryMySQL Instance = getInstance();
    private static Connection connection;
    private String url, login, password;

    private FactoryMySQL(String url, String login, String password) throws SQLException {
         this.url = url;
         this.login = login;
         this.password = password;
         connection = DriverManager.getConnection(url,login,password);
    }
    static FactoryMySQL getInstance() throws DAOConfigException {
        FactoryMySQL instance = null;
        String property_URL, property_DBNAME, property_LOGIN, property_PASSWORD, property_DRIVER;
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");
        if(propertiesFile==null){
            System.out.println("Properties file not found");
        }
        else{
            try{
                properties.load(propertiesFile);
                property_URL = properties.getProperty("URL");
                property_DBNAME = properties.getProperty("DBNAME");
                property_LOGIN = properties.getProperty("LOGIN");
                property_PASSWORD = properties.getProperty("PASSWORD");
                property_DRIVER = properties.getProperty("DRIVER");
                propertiesFile.close();
                Class.forName(property_DRIVER);
                property_URL = property_URL + "/" + property_DBNAME;
                instance =new FactoryMySQL(property_URL, property_LOGIN, property_PASSWORD);
                System.out.println("MySQL Factory Instance for DataBase : [" + property_DBNAME + "] is Ready");
            }catch (IOException e){
                throw new DAOConfigException("Properties File Not Found : "+e.getMessage());
            }
            catch (ClassNotFoundException e){
                throw new DAOConfigException("JDBC Not Found : "+e.getMessage());
            }
            catch (Exception e){
                throw new DAOConfigException("Connection Failed : "+e.getMessage());
            }
            finally {
                properties.clear();
            }
        }
        return instance;
    }
    public static Connection getConnection() throws DAOConfigException {
        if(connection==null)
            Instance=getInstance();
        return connection;
    }

    public static void CloseConnection(){
        if(connection!=null){
            try {
                if(!connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Connection Close Failed : "+e.getMessage());
            }
        }
    }
    @Override
    public IDao<Credit, Long> getCreditDao() {
        return null;
    }
}
