package Surya;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Do POST method to handle login
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String n = request.getParameter("txtName");  // Get username
        String p = request.getParameter("txtpwd");   // Get password

        // Validate Name: starts with a capital letter and at least 3 characters
        if (n == null || !n.matches("[A-Z][a-zA-Z]{2,}")) {
            response.getWriter().println("<font color='red' size='5'>Invalid Name!<br>");
            response.getWriter().println("Name must start with a capital letter and be at least 3 characters long.</font><br>");
            response.getWriter().println("<a href='login.jsp'>Try Again</a>");
            return;
        }

        // Validate Password: min 8 chars, 1 uppercase, 1 digit, exactly 1 special character
        if (p == null || !p.matches("^(?=.*[A-Z])(?=.*\\d)(?=\\S{8,}$)(?=.*[^\\w\\d\\s]).*$")) {
            response.getWriter().println("<font color='red' size='5'>Invalid Password!<br>");
            response.getWriter().println("Password must be at least 8 characters long,<br>");
            response.getWriter().println("have 1 uppercase letter, 1 digit, and exactly 1 special character.</font><br>");
            response.getWriter().println("<a href='login.jsp'>Try Again</a>");
            return;
        }

        // Debugging: Print out user inputs for debugging purposes
        System.out.println("Received username: " + n);
        System.out.println("Received password: " + p);

        // Connect to the database and check credentials
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/suryadb", "root", "Surya@2003");
            System.out.println("Database connection successful!");

            // Prepare SQL query to check credentials
            PreparedStatement ps = con.prepareStatement("SELECT uname FROM login WHERE uname=? AND password=?");
            ps.setString(1, n);
            ps.setString(2, p);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // Check if the user exists
            if (rs.next()) {
                // Debugging: Print the result of the query
                System.out.println("Login successful for username: " + n);

                // Forward the request to the welcome page
                request.setAttribute("username", n);  // Pass username to welcome page
                RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
                rd.forward(request, response);
            } else {
                // Debugging: If login fails
                System.out.println("Login failed for username: " + n);

                // If login fails, show an error message
                response.getWriter().println("<font color='red' size='5'>Login Failed!!</font><br>");
                response.getWriter().println("<a href='login.jsp'>Try AGAIN!!</a>");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC driver: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
