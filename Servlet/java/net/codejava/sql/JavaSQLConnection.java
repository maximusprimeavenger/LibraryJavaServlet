package net.codejava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaSQLConnection {

	public static void main(String[] args) {
		ContactForm form = new ContactForm();
      	form.setVisible(true);
      	
      	
//         String url = "jdbc:sqlserver://MAX;databaseName=Library;encrypt=true;trustServerCertificate=true";
//         String username = "newlogin";
//         String password = "Maks2005";
//         try {
//         Connection connection = DriverManager.getConnection(url,username, password);
//         String query = "insert into register(login_user, password_user)" 
//         + "values('name_field', '{name1_field.Text}')";
//         Statement statement = connection.createStatement();
//         int rows = statement.executeUpdate(query);
//         if(rows> 0) {
//        	 System.out.println("Ячейки были заполнены значениями");
//         }
//         System.out.println("Успех!");
//         }
//         catch(SQLException e){
//        	 System.out.println("Error");
//        	 e.printStackTrace();
//         }
	}

}
