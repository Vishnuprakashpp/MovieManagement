/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vishnuprakash.p
 */
public class MConnection {
    public static Connection connectDB() throws ClassNotFoundException, SQLException
    {
       Class.forName("com.mysql.jdbc.Driver");
       Connection con=null;
       String dbname="movies";
       String URL="jdbc:mysql://localhost:3306/";
       String userName="root";
       String passWord="root123";
       con=DriverManager.getConnection(URL+dbname,userName, passWord);
       return con;
    }
}
