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

@WebServlet("/bookList")
public class BookList extends HttpServlet {
	 private static final String query = "select * from Publications";

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
	                out.println("<table border ='1' align= 'center'>");
	                out.println("<tr>");
	                out.println("<th>Book ID</th>");
	                out.println("<th>Title</th>");
	                out.println("<th>Publication Year</th>");
	                out.println("<th>Shelf Location</th>");
	                out.println("<th>Availability</th>");
	                out.println("<th>Type</th>");
	                out.println("<th>Amount</th>");
	                out.println("<th>Edit</th>");
	                out.println("<th>Delete</th>");
	                out.println("</tr>");
	                while(rs.next()){
		                out.println("<tr>");
		                out.println("<td>"+rs.getInt(1)+"</td>");
		                out.println("<td>"+rs.getString(2)+"</td>");
		                out.println("<td>"+rs.getInt(3)+"</td>");
		                out.println("<td>"+rs.getString(4)+"</td>");
		                out.println("<td>"+rs.getBoolean(5)+"</td>");
		                out.println("<td>"+rs.getString(6)+"</td>");
		                out.println("<td>"+rs.getInt(7)+"</td>");
		                out.println("<td><a href = 'editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
		                out.println("<td><a href = 'deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
		                out.println("</tr>");
	                }
	                out.println("</table>");
	                
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            out.println("<h1>" + e.getMessage() + "</h1>");
	        }
	        out.println("<a href ='index.html'>Home</a>");
	    }
}