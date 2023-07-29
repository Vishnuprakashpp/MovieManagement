/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviePack;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateMovieFE extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateMovieFE</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" "
                    + "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
            out.println("<style>");
            out.println("body {");
            out.println("    background-color: #f7f7f7;");
            out.println("}");

            out.println(".container {");
            out.println("    max-width: 400px;");
            out.println("    margin: 0 auto;");
            out.println("    padding: 20px;");
            out.println("    border: 1px solid #ccc;");
            out.println("    border-radius: 5px;");
            out.println("    background-color: #fff;");
            out.println("}");

            out.println("form {");
            out.println("    margin-bottom: 20px;");
            out.println("}");

            out.println(".form-group {");
            out.println("    margin-bottom: 15px;");
            out.println("}");

            out.println(".form-group label {");
            out.println("    font-weight: bold;");
            out.println("}");

            out.println(".form-group input[type='text'],");
            out.println(".form-group input[type='file'] {");
            out.println("    width: 100%;");
            out.println("    padding: 8px;");
            out.println("    border: 1px solid #ccc;");
            out.println("    border-radius: 4px;");
            out.println("    box-sizing: border-box;");
            out.println("}");

            out.println(".form-group input[type='submit'] {");
            out.println("    background-color: #007bff;");
            out.println("    color: #fff;");
            out.println("    border: none;");
            out.println("    padding: 10px 15px;");
            out.println("    border-radius: 4px;");
            out.println("    cursor: pointer;");
            out.println("}");

            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>" + request.getContextPath() + "</h1>");

            out.println("<div class=\"container\">");
            out.println("<h1 class=\"text-center\">Update Movie</h1>");

            out.println("<form method='post' action='UpdateMovieBE' enctype='multipart/form-data'>");
            int mid = Integer.parseInt(request.getParameter("mid"));
            String title = request.getParameter("title");
            String genre = request.getParameter("genre");
            String director = request.getParameter("director");
            int year = Integer.parseInt(request.getParameter("year"));
            String description = request.getParameter("description");

            out.println("<div class='form-group'><label>ID:</label><input type='text' name='mid' value='" + mid + "' readonly/></div>");
            out.println("<div class='form-group'><label>Movie Title</label><input type='text' name='title' value='" + title + "'/></div>");
            out.println("<div class='form-group'><label>Genre</label><input type='text' name='genre' value='" + genre + "'></div>");
            out.println("<div class='form-group'><label>Director</label><input type='text' name='director' value='" + director + "'></div>");
            out.println("<div class='form-group'><label>Year</label><input type='text' name='year' value='" + year + "'></div>");
            out.println("<div class='form-group'><label>Description</label><input type='text' name='description' value='" + description + "'></div>");
            out.println("<div class='form-group'><label>Image</label><input type='file' name='image'></div>");
            out.println("<div class='form-group'><input type='submit' value='Update' class='btn btn-primary'></div>");
            out.println("</form>");

            // Display the current image
            //out.println("<h3>Current Image:</h3>");
            //String currentImage = getImageFromDatabase(); // Replace this with your code to retrieve the image from the database
            //out.println("<img src=\"data:image/png;base64," + currentImage + "\" alt=\"Current Image\" width=\"200\">");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

