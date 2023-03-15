package Marouan.CreditSimulator.TestJDBC;

import java.sql.*;

public class TestJDBC {

    Long id;
    public static void main(String[] args) {

        var url="jdbc:mysql://localhost:3306/Bankati";
        var login="root";
        var password="";
        var driver="com.mysql.cj.jdbc.Driver";

        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try{
            Class.forName(driver);
            System.out.println("Driver loaded");
            connection=DriverManager.getConnection(url,login,password);
            System.out.println("Connection Succeded");
            Statement statement=connection.createStatement();
            var resultSet=statement.executeQuery("select * from credit where id_credit=2");
            while (resultSet.next()){
                System.out.println("=========================================================");
                System.out.println("                        CREDITs                          ");
                System.out.println("=========================================================");
                System.out.println("#ID        :   "+ resultSet.getInt("id_credit"));
                System.out.println("+OWNER     :   "+ resultSet.getString("demandeur"));
                System.out.println("-AMOUNT    :   "+ resultSet.getDouble("capital")+ " DHs");
                System.out.println("-DURATION  :   "+ resultSet.getInt("nbr_mois")+" Months");
                System.out.println("-RATE      :   "+ resultSet.getDouble("taux_mensuel")+" %");
                System.out.println("-DHs/Month :   "+ resultSet.getDouble("mensualite")+" DHs");
                System.out.println("=========================================================");
            }



        }catch (ClassNotFoundException e){
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Connection Lost");
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                    System.out.println("Connection Closed");
                } catch (SQLException e) {
                    System.out.println("Connection still Open");
                }
            }
        }



    }

}
