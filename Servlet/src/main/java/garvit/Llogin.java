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

@WebServlet("/login")
public class Llogin extends HttpServlet {
    private static final String CHECK_USER_QUERY = "SELECT COUNT(*) FROM register WHERE email=? AND login_user=? AND password_user=?";
    private static final String GET_USER_QUERY = "SELECT id_user FROM register WHERE email=? AND login_user=? AND password_user=?";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String userPassword = req.getParameter("password");
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
                checkUserStatement.setString(3, userPassword);
                ResultSet userResult = checkUserStatement.executeQuery();
                if (userResult.next() && userResult.getInt(1) > 0) {
                    PreparedStatement getUserStatement = con.prepareStatement(GET_USER_QUERY);
                    getUserStatement.setString(1, userEmail);
                    getUserStatement.setString(2, name);
                    getUserStatement.setString(3, userPassword);
                    ResultSet userData = getUserStatement.executeQuery();
                    if (userData.next()) {
                        int userId = userData.getInt("id_user");
                        resp.sendRedirect("Account.html?id=" + userId);
                        return;
                    }
                } else {
                    out.println("<h1>Error: Invalid email, username, or password</h1>");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace(out);
        } catch (Exception e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
        out.println("<a href='Login.html'>Back to Login</a>");
    }
}




