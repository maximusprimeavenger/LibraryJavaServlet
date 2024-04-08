package garvit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerUsers")
public class RegisterUsers extends HttpServlet {
    private static final String CHECK_USER_QUERY = "SELECT COUNT(*) FROM register WHERE email=? OR login_user=?";
    private static final String INSERT_QUERY = "INSERT INTO register(login_user, password_user, is_admin, email) VALUES (?, ?, 0, ?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String userPassword = req.getParameter("password");
        String confirmPassword = req.getParameter("passwordConfirm");
        String userEmail = req.getParameter("email");

        String url = "jdbc:sqlserver://MAX;databaseName=Library;encrypt=true;trustServerCertificate=true";
        String dbUser = "newlogin";
        String dbPassword = "Maks2005";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection(url, dbUser, dbPassword)) {
                PreparedStatement checkUserStatement = con.prepareStatement(CHECK_USER_QUERY);
                checkUserStatement.setString(1, userEmail);
                checkUserStatement.setString(2, name);
                ResultSet userResult = checkUserStatement.executeQuery();
                if (userResult.next() && userResult.getInt(1) > 0) {
                    out.println("<h1>Error: Email or Username already exists</h1>");
                    return;
                }
                if (!confirmPassword.equals(userPassword)) {
                    out.println("<h1>Error: Passwords do not match</h1>");
                    return;
                }
                PreparedStatement insertStatement = con.prepareStatement(INSERT_QUERY);
                insertStatement.setString(1, name);
                insertStatement.setString(2, userPassword);
                insertStatement.setString(3, userEmail);
                int count = insertStatement.executeUpdate();
                if (count == 1) {
                    resp.sendRedirect("Login.html");
                    return;
                } else {
                    out.println("<h1>Error: Record insertion failed</h1>");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace(out);
        } catch (Exception e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
        out.println("<a href='Account.html'>Home</a>");
    }
}



