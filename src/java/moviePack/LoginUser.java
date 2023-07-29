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

public class LoginUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection con = MConnection.connectDB();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, password);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + " <link rel=\"stylesheet\" type=\"text/css\" href=\"/cssforLogin.css\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n"
                    + "        ");
            out.println("<title>Servlet LoginUser</title>"
                    + " <style>\n"
                    + "            .container {\n"
                    + "        display: flex;\n"
                    + "        justify-content: center;\n"
                    + "        align-items: center;\n"
                    + "        height: 100vh;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .login-container, .signup-container {\n"
                    + "        background-color: #fff;\n"
                    + "        padding: 40px;\n"
                    + "        border-radius: 10px;\n"
                    + "        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\n"
                    + "    }\n"
                    + "    \n"
                    + "    .login-container h1, .signup-container h1 {\n"
                    + "        text-align: center;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .form-group {\n"
                    + "        margin-bottom: 20px;\n"
                    + "    }\n"
                    + "    \n"
                    + "    label {\n"
                    + "        display: block;\n"
                    + "        font-weight: bold;\n"
                    + "        margin-bottom: 5px;\n"
                    + "    }\n"
                    + "    \n"
                    + "    input[type=\"text\"],\n"
                    + "    input[type=\"email\"],\n"
                    + "    input[type=\"password\"] {\n"
                    + "        width: 100%;\n"
                    + "        padding: 10px;\n"
                    + "        border-radius: 5px;\n"
                    + "        border: 1px solid #ccc;\n"
                    + "    }\n"
                    + "    \n"
                    + "    button[type=\"submit\"] {\n"
                    + "        display: block;\n"
                    + "        width: 100%;\n"
                    + "        padding: 10px;\n"
                    + "        background-color: #007bff;\n"
                    + "        color: #fff;\n"
                    + "        border: none;\n"
                    + "        border-radius: 5px;\n"
                    + "        cursor: pointer;\n"
                    + "    }\n"
                    + "    \n"
                    + "    button[type=\"submit\"]:hover {\n"
                    + "        background-color: #0056b3;\n"
                    + "    }\n"
                    + "    \n"
                    + "    p {\n"
                    + "        text-align: center;\n"
                    + "    }\n"
                    + "    \n"
                    + "    a {\n"
                    + "        color: #007bff;\n"
                    + "    }\n"
                    + "        </style>");
            out.println("</head>");
            out.println("<body>"
                    + "<div class=\"container\">\n"
                    + "        <div class=\"login-container\">\n"
                    + "            <h1>Login</h1>\n"
                    + "            <form action=\"LoginUser\" method=\"post\">\n"
                    + "                <div class=\"form-group\">\n"
                    + "                    <label for=\"login-email\">Email:</label>\n"
                    + "                    <input type=\"email\" id=\"login-email\" name=\"email\" required>\n"
                    + "                </div>\n"
                    + "                <div class=\"form-group\">\n"
                    + "                    <label for=\"login-password\">Password:</label>\n"
                    + "                    <input type=\"password\" id=\"login-password\" name=\"password\" required>\n"
                    + "                </div>\n"
                    + "                <button type=\"submit\">Login</button>\n"
                    + "                <p>Not a user? <a href=\"UserSignUp\">Sign Up</a></p>\n"
                    + "            </form>\n"
                    + "        </div>\n"
                    + "    </div>");

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                // User authentication successful, redirect to UserHome servlet
                response.sendRedirect("UserHome");
            } else {
                // Invalid credentials, display an error message and stay on the LoginUser page
                out.println("<script>alert('Invalid credentials entered');</script>");
                // You can also consider using request.getRequestDispatcher() instead of redirecting
                // request.getRequestDispatcher("LoginUser").include(request, response);
            }
            con.close();
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException ex) {
            // Handle exceptions appropriately (e.g., logging or displaying an error page)
            Logger.getLogger(LoginUser.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
