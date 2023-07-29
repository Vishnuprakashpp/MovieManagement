/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviePack;

import connection.MConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vishnuprakash.p
 */
public class UserSearchmoviez extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Movie Details</title>"
                    + "<link rel=\"stylesheet\" href=\"userhomestyle.css\">");

            String title = request.getParameter("title");

            String base64Image = request.getParameter("image");

            String genre = request.getParameter("genre");
            String director = request.getParameter("director");
            int year = Integer.parseInt(request.getParameter("year"));
            String description = request.getParameter("description");
            String email = request.getParameter("email");
            int mid = Integer.parseInt("mid");

            out.println("<style>");
            out.println("body { background-color: #f2f2f2; font-family: Arial, sans-serif; }");
            out.println(".container { display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".frame { border: 2px solid rgba(0, 0, 0, 0.2); background-color: rgba(255, 255, 255, 0.8);");
            out.println("         padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }");
            out.println(".image { max-width: 300px; max-height: 400px; margin-right: 20px; }");
            out.println(".details { display: flex; flex-direction: column; }");
            out.println(".details p { margin: 5px 0; }");
            out.println("</style>");
            out.println("</head>"
                    + "<body>\n"
                    + "  <nav class=\"navbar\">\n"
                    + "    <div class=\"navbar-left\">\n"
                    + "      <div class=\"logo\"><img src=\"cinemax-1-logo-png-transparent.png\" alt=\"CineMax Logo\"></div>\n"
                    + "      <ul class=\"nav-links\">\n"
                    + "        <li><a href=\"UserHome\">Home</a></li>\n"
                    + "        <li><a href=\"usersearch.html\">Search</a></li>\n"
                    + "        <li><a href=\"/UserSearchmoviesz\">Watchlist</a></li>\n"
                    + "      </ul>\n"
                    + "    </div>\n"
                    + "    <div class=\"navbar-right\">\n"
                    + "      <div class=\"username\">Your Username</div>\n"
                    + "      <button class=\"logout-btn\" onclick=\"redirectToUserHome();\">Logout</button>\n"
                    + "    </div>\n"
                    + "  </nav>\n"
                    + "     <script>\n"
                    + "        function redirectToUserHome() {\n"
                    + "            // Perform the redirection here\n"
                    + "            window.location.href = \"LoginUsers\";\n"
                    + "        }\n"
                    + "    </script>");
            out.println("<div class='container'>");
            out.println("<div class='frame'>");
            out.println("<img class='image' src='data:image/png;base64," + base64Image + "' alt='Movie Image'>");
            out.println("<div class='details'>");
            out.println("<p><strong>Title:</strong> " + title + "</p>");
            out.println("<p><strong>Year:</strong> " + year + "</p>");
            out.println("<p><strong>Director:</strong> " + director + "</p>");
            out.println("<p><strong>Description:</strong> " + description + "</p>");
            out.println("</div></div></div>");
            Connection con = MConnection.connectDB();
            String sql = "insert into watchlist(email,title,director,year,mid) values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, title);
            pst.setString(3, director);
            pst.setInt(4, year);
            pst.setInt(5, mid);
            out.println("</body></html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method. s
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserSearchmoviez.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSearchmoviez.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserSearchmoviez.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSearchmoviez.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
