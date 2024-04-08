package net.codejava.sql;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Desktop;


	public class Login extends JFrame {
		JTextField phone_field, password1_field;
		JLabel phone, password1;
		JCheckBox password_check;
public Login() {
super("Логин");
super.setBounds(400, 200, 350, 200);
super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
Container container = super.getContentPane();
container.setLayout(new GridLayout(4, 2, 10, 10));
phone = new JLabel("Введите телефон:");
phone_field = new JTextField("+77", 1);
password1 = new JLabel("Введите пароль:");
password1_field = new JTextField("", 1);
JButton send = new JButton("Отправить");

container.add(phone);
container.add(phone_field);
container.add(password1);
container.add(password1_field);
container.add(send);
send.addActionListener(new ButtonClicked());

}
class ButtonClicked implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String number = phone_field.getText();
        String password_u = password1_field.getText();

        String url = "jdbc:sqlserver://MAX;databaseName=Library;encrypt=true;trustServerCertificate=true";
        String username = "newlogin";
        String password = "Maks2005";
        String sql = "Select * from register where phone_number = ? and password_user = ?";
        String sql_hello = "Select login_user from register where phone_number = ? and password_user = ?";
        
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             PreparedStatement helloStatement = connection.prepareStatement(sql_hello)) {

            statement.setString(1, number);
            statement.setString(2, password_u);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String login_user = resultSet.getString("login_user");
                System.out.println("Данные успешно обработаны.");
                JOptionPane.showMessageDialog(null, "", "Добро пожаловать, " + login_user, JOptionPane.PLAIN_MESSAGE);
                try{
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        Desktop.getDesktop().browse(new URI("http://localhost:8083/Servlet/"));
                    }
                } catch (IOException a) {
                    throw new RuntimeException(a);
                } catch (URISyntaxException a) {
                    throw new RuntimeException(a);
                }
            } else {
                System.out.println("Не удалось найти пользователя.");
            }
        } catch (SQLException c) {
            c.printStackTrace();
        }
    }
}

}



