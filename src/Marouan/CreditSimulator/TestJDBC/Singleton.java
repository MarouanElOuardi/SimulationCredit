package Marouan.CreditSimulator.TestJDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class Singleton {
    private static Connection connection;

    public Singleton() {
        String url = "jdbc:mysql://localhost:3306Bankati";
        String login = "root";
        String password = "";
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            System.out.println("Driver loaded");
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Succeded");
        } catch (Exception e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        if(connection==null){
            new Singleton();
        }
        return connection;
    }
}
