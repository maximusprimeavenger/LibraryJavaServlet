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

@WebServlet("/bookListUser")
public class BookListUser extends HttpServlet {
    private static final String query = "Select publication_id, title, publication_year, shelf_location, type, amount_of_book from Publications";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "jdbc:sqlserver://MAX;databaseName=Library;encrypt=true;trustServerCertificate=true";
        String user = "newlogin";
        String password = "Maks2005";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                out.println("<html><head><title>Book List</title>");
                out.println("<style>");
                out.println("h1{ text-align: center;}");
                out.println("table {border-collapse: collapse; width: 80%; margin: 20px auto;}");
                out.println("th, td {padding: 10px; text-align: center;}");
                out.println("th {background-color: #db2727; color: white;}");
                out.println("tr:nth-child(even) {background-color: #f2f2f2;}");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<h1>Book List</h1>");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Book ID</th>");
                out.println("<th>Title</th>");
                out.println("<th>Publication Year</th>");
                out.println("<th>Shelf Location</th>");
                out.println("<th>Type</th>");
                out.println("<th>Amount</th>");
                out.println("</tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt(1) + "</td>");
                    out.println("<td>" + rs.getString(2) + "</td>");
                    out.println("<td>" + rs.getInt(3) + "</td>");
                    out.println("<td>" + rs.getString(4) + "</td>");
                    out.println("<td>" + rs.getString(5) + "</td>");
                    out.println("<td>" + rs.getInt(6) + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<a href ='Account.html'>Home</a>");
                out.println("</body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}
