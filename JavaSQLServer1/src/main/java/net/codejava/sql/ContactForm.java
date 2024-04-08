package net.codejava.sql;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ContactForm extends JFrame{
	JTextField name_field, password_field, phone_number;
	JRadioButton male, female;
	JCheckBox check;
	
public ContactForm() {
	
	super("Библиотечная форма");
	super.setBounds(400, 200, 300, 300);
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	Container container = super.getContentPane();
	container.setLayout(new GridLayout(7, 2, 10, 10));
	JLabel name = new JLabel("Введите имя:");
	 name_field = new JTextField("", 1);
	 
	 JLabel number = new JLabel("Введите ваш телефон:");
	 phone_number = new JTextField("+77", 1);
	 
	JLabel name1 = new JLabel("Введите пароль:");
	password_field = new JTextField("", 1);
	
	container.add(name);
	container.add(name_field);
	container.add(number);
	container.add(phone_number);
	container.add(name1);
	container.add(password_field);
	
	 male = new JRadioButton("Мужской");
	 female = new JRadioButton("Женский");
	 check = new JCheckBox("Согласен", false);
	JButton send = new JButton("Отправить");
	
	male.setSelected(true);
	container.add(male);
	container.add(female);
	
	ButtonGroup group = new ButtonGroup();
	group.add(male);
	group.add(female);
	container.add(check);
	container.add(send);
	
	send.addActionListener(new ButtonEventManager());
	
}

class ButtonEventManager implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = name_field.getText();
		String password_user = password_field.getText();
		String phone = phone_number.getText();
		String ismale = "Мужской";
		if(!male.isSelected()) 
			ismale = "Женский";
		
		//boolean checkbox = check.isSelected();
		
		JOptionPane.showMessageDialog(null, "Ваш пароль: "+ password_user + "\n"+ "Ваш пол: "+ ismale +"\n"+ "Ваш телефон: " + phone + "\nВы согласны?", "Привет, " + name, JOptionPane.PLAIN_MESSAGE);
		String url = "jdbc:sqlserver://MAX;databaseName=Library;encrypt=true;trustServerCertificate=true";
	    String username = "newlogin";
	    String password = "Maks2005";
		String sql = "INSERT INTO register(login_user, password_user, is_admin, phone_number) VALUES (?, ?, 0, ?)";

	     try (Connection connection = DriverManager.getConnection(url, username, password);
	          PreparedStatement statement = connection.prepareStatement(sql)) {


	         statement.setString(1, name);
	         statement.setString(2, password_user);
	         statement.setString(3, phone);


	         int rowsInserted = statement.executeUpdate();

	         if (rowsInserted > 0) {
	             System.out.println("Данные успешно вставлены в таблицу.");
	             Login form1 = new Login();
	           	 form1.setVisible(true);
	             
	         } else {
	             System.out.println("Не удалось вставить данные в таблицу.");
	         }
	     } catch (SQLException c) {
	         c.printStackTrace();
	     }
	}
	
}



	

}
