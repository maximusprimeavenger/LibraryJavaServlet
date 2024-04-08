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
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String query = "SELECT * FROM Publications WHERE publication_id = ?";

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

                    if (rs.next()) {
                        out.println("<form action='editurl?id=" + id + "' method='post'>");
                        out.println("<table align='center'>");
                        out.println("<tr><td>Book Title</td><td><input type='text' name='bookTitle' value='" + rs.getString(2) + "'></td></tr>");
                        out.println("<tr><td>Publication Year</td><td><input type='text' name='publicationYear' value='" + rs.getInt(3) + "'></td></tr>");
                        out.println("<tr><td>Shelf Location</td><td><input type='text' name='shelfLocation' value='" + rs.getString(4) + "'></td></tr>");
                        out.println("<tr><td>Availability</td><td><input type='text' name='availability' value='" + rs.getBoolean(5) + "'></td></tr>");
                        out.println("<tr><td>Type</td><td><input type='text' name='type' value='" + rs.getString(6) + "'></td></tr>");
                        out.println("<tr><td>Amount</td><td><input type='text' name='amount' value='" + rs.getInt(7) + "'></td></tr>");
                        out.println("<tr><td colspan='2'><input type='submit' value='Edit'></td></tr>");
                        out.println("</table>");
                        out.println("</form>");
                    } else {
                        out.println("<h1>Error: Record not found</h1>");
                    }
                }
            } catch (NumberFormatException e) {
                out.println("<h1>Error: Invalid ID parameter</h1>");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                out.println("<h1>" + e.getMessage() + "</h1>");
            }
        } else {
            out.println("<h1>Error: ID parameter is missing</h1>");
        }
        out.println("<a href='index.html'>Home</a>");
    }
}


