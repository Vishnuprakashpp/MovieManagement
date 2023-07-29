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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Search Movie</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
            out.println("<style>");
            out.println("body {");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    min-height: 100vh;");
            out.println("}");
            out.println(".container {");
            out.println("    width: 800px;");
            out.println("    border: 1px solid #ccc;");
            out.println("    padding: 20px;");
            out.println("    border-radius: 5px;");
            out.println("    margin-top: 20px;");
            out.println("}");
            out.println("table {");
            out.println("    width: 100%;");
            out.println("    border-collapse: collapse;");
            out.println("    margin-top: 20px;");
            out.println("}");
            out.println("th, td {");
            out.println("    padding: 8px;");
            out.println("    text-align: left;");
            out.println("    border-bottom: 1px solid #ddd;");
            out.println("}");
            out.println("th {");
            out.println("    background-color: #f2f2f2;");
            out.println("}");
            out.println("img {");
            out.println("    max-width: 200px;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Navbar
            out.println("<nav class=\"navbar navbar-expand-lg bg-body-tertiary fixed-top\">");
            out.println("  <div class=\"container-fluid\">");
            out.println("    <image width=\"50\" height=\"50\" src=\"cinemax-1-logo-png-transparent.png\" alt=\"Cinemax Logo\">");
            out.println("    <a class=\"navbar-brand\" href=\"home.html\">Home</a>");
            out.println("    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNavAltMarkup\" aria-controls=\"navbarNavAltMarkup\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
            out.println("      <span class=\"navbar-toggler-icon\"></span>");
            out.println("    </button>");
            out.println("    <div class=\"collapse navbar-collapse\" id=\"navbarNavAltMarkup\">");
            out.println("      <div class=\"navbar-nav\">");
            out.println("        <a class=\"nav-link active\" aria-current=\"page\" href=\"Registration.html\">Add Movie</a>");
            out.println("        <a class=\"nav-link\" href=\"search.html\">Search Movie</a>");
            out.println("        <a class=\"nav-link\" href=\"deleteMovie.html\">Delete Movie</a>");
            out.println("        <a class=\"nav-link Enabled\" href=\"ViewMovie\">View Movies</a>");
            out.println("      </div>");
            out.println("    </div>");
            out.println("  </div>");
            out.println("</nav>");

            // Movie details
            out.println("<div class=\"container\">");
            out.println("    <h1>Details of Movie</h1>");
            out.println("    <hr>");
            out.println("    <table>");
            out.println("        <tr>");
            out.println("            <th>Id</th>");
            out.println("            <th>Title</th>");
            out.println("            <th>Genre</th>");
            out.println("            <th>Director</th>");
            out.println("            <th>Year</th>");
            out.println("            <th>Description</th>");
           // out.println("            <th>Show Times</th>");
            //out.println("            <th>Date</th>");
            out.println("            <th>Image</th>");
            out.println("        </tr>");

            String title = request.getParameter("title");
            Connection con = MConnection.connectDB();
            String sql = "SELECT * FROM movies WHERE title = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                out.println("        <tr>");
                out.println("            <td>" + rs.getInt(1) + "</td>");
                out.println("            <td>" + rs.getString(2) + "</td>");
                out.println("            <td>" + rs.getString(3) + "</td>");
                out.println("            <td>" + rs.getString(4) + "</td>");
                out.println("            <td>" + rs.getString(5) + "</td>");
                out.println("            <td>" + rs.getString(6) + "</td>");
                //out.println("            <td>" + rs.getInt(7) + "</td>");
                //out.println("            <td>" + rs.getString(8) + "</td>");
                out.println("            <td><img src=\"" + rs.getString(9) + "\"></td>");
                out.println("        </tr>");
            }

            con.close();

            out.println("    </table>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
            Logger.getLogger(SearchMovie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchMovie.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchMovie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchMovie.class.getName()).log(Level.SEVERE, null, ex);
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

