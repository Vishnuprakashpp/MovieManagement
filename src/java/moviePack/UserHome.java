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

public class UserHome extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Connection con = MConnection.connectDB();
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM movies";
            ResultSet rs = stmt.executeQuery(sql);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" href=\"userhomestyle.css\">ss");
            out.println("<title>User Home</title>"
                    + "");
            out.println("<style>");
            out.println("body { background-color: #f0f0f0; }");
            out.println(".container { display: flex; flex-wrap: wrap; justify-content: center; margin: 30px auto; max-width: 1200px; }");
            out.println(".movie-card { background-color: rgba(255, 255, 255, 0.8); box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px; margin: 20px; border-radius: 10px; width: 300px; }");
            out.println(".movie-image { display: block; margin: 0 auto; width: 200px; height: 250px; object-fit: cover; }");
            out.println(".movie-title { font-size: 20px; font-weight: bold; text-align: center; margin: 10px 0; }");
            out.println(".movie-year { font-size: 16px; font-weight: bold; }");
            out.println(".movie-director { font-size: 16px; font-weight: bold; text-align: right; }");
            out.println(".readmore { text-align: center; margin-top: 10px; }");
            out.println(".watchlist-btn { background-color: #00bfff; color: #fff; border: none; padding: 6px 12px; font-weight: bold; cursor: pointer; border-radius: 4px; text-shadow: 0 0 4px rgba(0, 0, 255, 0.7); }");
            out.println(".watchlist-btn:hover { background-color: #007bff; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println(" <nav class=\"navbar\">\n"
                    + "    <div class=\"navbar-left\">\n"
                    + "      <div class=\"logo\"><img src=\"cinemax-1-logo-png-transparent.png\" alt=\"CineMax Logo\"></div>\n"
                    + "      <ul class=\"nav-links\">\n"
                    + "        <li><a href=\"UserHome\">Home</a></li>\n"
                    + "        <li><a href=\"usersearch.html\">Search</a></li>\n"
                    + "        <li><a href=\"UserSearchmoviez\">Watchlist</a></li>\n"
                    + "      </ul>\n"
                    + "    </div>\n"
                    + "    <div class=\"navbar-right\">\n"
                    + "      <div class=\"username\">Your Username</div>\n"
                    + "      <button class=\"logout-btn\" onclick=\"redirectToUserHome();\">Logout</button>\n"
                    + "    </div>\n"
                    + "  </nav>"
                    + "<script>\n"
                    + "        function redirectToUserHome() {\n"
                    + "            // Perform the redirection here\n"
                    + "            window.location.href = \"LoginUser\";\n"
                    + "        }\n"
                    + "    </script>");
            out.println("<div class='container'>");

            while (rs.next()) {
                int mid = rs.getInt("mid");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                int year = rs.getInt("year");
                String description = rs.getString("description");

                byte[] image = rs.getBytes("image");
                String base64Image = Base64.getEncoder().encodeToString(image);

                out.println("<div class='movie-card'>");
                out.println("<img src='data:image/png;base64," + base64Image + "' class='movie-image'>");
                out.println("<div class='movie-title'>" + title + "</div>");
                out.println("<div class='movie-year'>" + year + "</div>");
                out.println("<div class='movie-director'>" + director + "</div>");
                out.println("<div class='readmore'><a href='javascript:void(0);' onclick='+toggleDescription(" + mid + ")'>Read More</a></div>");
                out.println("<div class='description' id='description-" + mid + "' style='display: none;'>" + description + "</div>");
                out.println("<div><a href='UserSearchmoviez?base64Image="+image+"&title="+title+"&year="+year+"&director="+director+"&description="+description+"'><button class='watchlist-btn'>Add to Watchlist</button></a></div>");
                
                out.println("</div>");
            }

            //out.println("</div>");s
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
