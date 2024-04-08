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

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query = "DELETE FROM Publications WHERE publication_id = ?";
	
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

	                    int count = ps.executeUpdate();
	                    if (count == 1) {
	                        out.println("<h3>Record is edited successfully!!</h3>");
	                    } else {
	                        out.println("<h1>Error: Record not found</h1>");
	                    }
	                }
	            } catch (NumberFormatException e) {
	                out.println("<h1>Error: Invalid ID or publication year</h1>");
	            } catch (ClassNotFoundException | SQLException e) {
	                e.printStackTrace();
	                out.println("<h1>" + e.getMessage() + "</h1>");
	            }
	        } else {
	            out.println("<h1>Error: Missing parameters</h1>");
	        }
	        out.println("<a href='index.html'>Home</a>");
    
	 }
}