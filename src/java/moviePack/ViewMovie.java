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

public class ViewMovie extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewMovie</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
            out.println("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
            out.println("</head>");
            out.println("<body>");
            
            // Navbar
            out.println("<nav class=\"navbar navbar-expand-lg bg-body-tertiary\">");
            out.println("  <div class=\"container-fluid\">");
            out.println("    <image width=\"50\" height=\"50\" src=\"cinemax-1-logo-png-transparent.png\"/>");
            out.println("    <a class=\"navbar-brand\" href=\"home.html\">Home</a>");
            out.println("    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNavAltMarkup\" aria-controls=\"navbarNavAltMarkup\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
            out.println("      <span class=\"navbar-toggler-icon\"></span>");
            out.println("    </button>");
            out.println("    <div class=\"collapse navbar-collapse\" id=\"navbarNavAltMarkup\">");
            out.println("      <div class=\"navbar-nav\">");
            out.println("        <a class=\"nav-link active\" aria-current=\"page\" href=\"Registration.html\">AddMovie</a>");
            out.println("        <a class=\"nav-link\" href=\"search.html\">SearchMovie</a>");
            out.println("        <a class=\"nav-link\" href=\"deleteMovie.html\">DeleteMovie</a>");
            out.println("        <button><a class=\"nav-link Enabled\" href=\"ViewMovie\">ViewMovies</a></button>");
            out.println("      </div>");
            out.println("    </div>");
            out.println("  </div>");
            out.println("</nav>");
            
            // Movie Table
            out.println("<div class=\"container\">");
            out.println("<h1 class=\"text-primary\">Welcome To CineMax</h1>");
            out.println("<table class=\"table table-bordered\">");
            out.println("<thead><tr><th>ID</th><th>Title</th><th>Genre</th><th>Director</th><th>Year</th>"
                    + "<th>Description</th><th>Image</th><th>Update</th></tr></thead>");
            out.println("<tbody>");
            
            Connection con = MConnection.connectDB();
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM movies";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int mid = rs.getInt("mid");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                int year = rs.getInt("year");
                String description = rs.getString("description");
                
                byte[] imageData = rs.getBytes("image");
                
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                String imageHtml = "<img src='data:image/png;base64," + base64Image + "' class='img-thumbnail' width='100' height='100'>";
                
                out.println("<tr>");
                out.println("<td>" + mid + "</td>");
                out.println("<td>" + title + "</td>");
                out.println("<td>" + genre + "</td>");
                out.println("<td>" + director + "</td>");
                out.println("<td>" + year + "</td>");
                out.println("<td>" + description + "</td>");  
                out.println("<td>" + imageHtml + "</td>");
                out.println("<td><a href='UpdateMovieFE?id=" + mid + "&title=" + title + "&genre=" + genre +
                        "&director=" + director + "&year=" + year + "&description=" + description +
                        "' class='btn btn-primary'>Update</a></td>");
                out.println("</tr>");
            }
            
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            
            con.close();
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}





