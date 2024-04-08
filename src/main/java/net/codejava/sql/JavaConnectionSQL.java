package net.codejava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaConnectionSQL {

	public static void main(String[] args) {
		ContactForm form = new ContactForm();
		form.setVisible(true);
         String url = "jdbc:sqlserver://MAX;databaseName=Library;encrypt=true;trustServerCertificate=true";
         String username = "newlogin";
         String password = "Maks2005";
         try {
         Connection connection = DriverManager.getConnection(url,username, password);
         String query = "insert into Books(publication_id, issue_number, publication_date, publication_title)" 
         + "values(1, 1, '2024-03-13', 'Макс')";
         Statement statement = connection.createStatement();
         int rows = statement.executeUpdate(query);
         if(rows> 0) {
        	 System.out.println("Ячейки были заполнены значениями");
         }
         System.out.println("Успех!");
         }
         catch(SQLException e){
        	 System.out.println("Error");
        	 e.printStackTrace();
         }
	}

}


