package garvit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String query = "insert into Publications(title, publication_year, shelf_location, availability, type, amount_of_book) values(?,?,?,?,?,?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String bookTitle = req.getParameter("bookTitle");
        int publicationYear = Integer.parseInt(req.getParameter("publicationYear"));
        String shelfLocation = req.getParameter("shelfLocation");
        boolean availability = Boolean.parseBoolean(req.getParameter("availability"));
        String type = req.getParameter("type");
        int amountOfBooks = Integer.parseInt(req.getParameter("amount"));

        String url = "jdbc:sqlserver://MAX;databaseName=Library;encrypt=true;trustServerCertificate=true";
        String user = "newlogin";
        String password = "Maks2005";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, bookTitle);
                ps.setInt(2, publicationYear);
                ps.setString(3, shelfLocation);
                ps.setBoolean(4, availability);
                ps.setString(5, type);
                ps.setInt(6, amountOfBooks);

                int count = ps.executeUpdate();
                if (count == 1) {
                    out.println("<h3>Record is inserted successfully!!</h3>");
                } else {
                    out.println("<h1>Error: Record insertion failed</h1>");
                }
            }
        } catch (NumberFormatException e) {
            out.println("<h1>Error: Invalid input format</h1>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h1>" + e.getMessage() + "</h1>");
        }
        out.println("<a href ='index.html'>Home</a>");
    }
}

