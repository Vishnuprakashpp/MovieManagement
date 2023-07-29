/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviePack;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class AddMovie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    int mid = Integer.parseInt(request.getParameter("mid"));
    String title = request.getParameter("title");
    String genre = request.getParameter("genre");
    String director = request.getParameter("director");
    int year = Integer.parseInt(request.getParameter("year"));
    String description = request.getParameter("description");
    
    Part filePart = request.getPart("image");
    InputStream inputStream = filePart.getInputStream();

    try {
        // Establish database connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies", "root", "root123");

        // Prepare the SQL statement to insert the data
        String sql = "INSERT INTO movies (mid, title, genre, director, year, description, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, mid);
        pst.setString(2, title);
        pst.setString(3, genre);
        pst.setString(4, director);
        pst.setInt(5, year);
        pst.setString(6, description);
        pst.setBinaryStream(7, inputStream, (int) filePart.getSize());

        // Execute the SQL statement to insert the data
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            response.getWriter().println("<script>alert('Record inserted successfully');</script>");
            request.getRequestDispatcher("home.html").include(request, response);
        } else {
            response.getWriter().println("<script>alert('Failed to insert record');</script>");
            request.getRequestDispatcher("AddMovie").include(request, response);
        }

        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
}
}