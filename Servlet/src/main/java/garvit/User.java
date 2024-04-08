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

@WebServlet("/user")
public class User extends HttpServlet {
    private static final String query = "SELECT login_user, password_user, email FROM register WHERE id_user = ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idParam = req.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                String url = "jdbc:sqlserver://MAX;databaseName=Library;encrypt=true;trustServerCertificate=true";
                String user = "newlogin";
                String password = "Maks2005";

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                try (Connection con = DriverManager.getConnection(url, user, password);
                    PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    out.println("<html><head>");
                    out.println("<title>User Profile</title>");
                    out.println("<style>");
                    out.println("h1 {text-align: center;}");
                    out.println("table {border-collapse: collapse; width: 80%; margin: 20px auto;}");
                    out.println("th, td {padding: 10px; text-align: center;}");
                    out.println("th {background-color: #db2727; color: white;}");
                    out.println("tr:nth-child(even) {background-color: #f2f2f2;}");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>User Profile</h1>");
                    out.println("<table border='1'>");
                    out.println("<tr>");
                    out.println("<th>Name</th>");
                    out.println("<th>Password</th>");
                    out.println("<th>Email</th>");
                    out.println("</tr>");
                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rs.getString(1) + "</td>");
                        out.println("<td>" + rs.getString(2) + "</td>");
                        out.println("<td>" + rs.getString(3) + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</table>");
                    out.println("<a href ='Account.html'>Home</a>");
                    out.println("</body></html>");

                } catch (SQLException e) {
                    e.printStackTrace();
                    out.println("<h1>Error: " + e.getMessage() + "</h1>");
                }
            } catch (NumberFormatException | ClassNotFoundException e) {
                out.println("<h1>Error: Invalid input format</h1>");
            }
        } else {
            out.println("<h1>Error: Missing ID parameter</h1>");
        }
    }
}
