/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviePack;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class UpdateMovieBE extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>UpdateMovieBE</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" "
                    + "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
            out.println("<style>");
            out.println("body {");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    min-height: 100vh;");
            out.println("}");
            out.println(".container {");
            out.println("    width: 400px;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            int mid = Integer.parseInt(request.getParameter("mid"));
            String title = request.getParameter("title");
            String genre = request.getParameter("genre");
            String director = request.getParameter("director");
            int year = Integer.parseInt(request.getParameter("year"));
            String description = request.getParameter("description");

            Part filePart = request.getPart("image");
            InputStream inputStream = filePart.getInputStream();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies", "root", "root123");

            String sql = "UPDATE movies SET title=?, genre=?, director=?, year=?, description=?, image=? WHERE mid=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, genre);
            pst.setString(3, director);
            pst.setInt(4, year);
            pst.setString(5, description);
            pst.setBinaryStream(6, inputStream, (int) filePart.getSize());
            pst.setInt(7, mid);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                out.println("<script>");
                out.println("    alert('Record Updated Successfully!');");
                out.println("    window.location.href = 'ViewMovie';");  // Redirect to ViewMovie
                out.println("</script>");
            } else {
                out.println("<script>alert('Failed to update record.');</script>");
            }

            con.close();

            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateMovieBE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported. Please use POST method.");
    }

    public String getServletInfo() {
        return "Short description";
    }
}