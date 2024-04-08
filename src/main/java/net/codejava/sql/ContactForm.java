package net.codejava.sql;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ContactForm extends JFrame{
	JTextField name_field, name1_field;
	JRadioButton male, female;
	JCheckBox check;
public ContactForm() {
	super("Библиотечная форма");
	super.setBounds(200, 100, 300, 300);
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	Container container = super.getContentPane();
	container.setLayout(new GridLayout(5,2, 2, 10));
	
	JLabel name = new JLabel("Введите имя:");
	 name_field = new JTextField("", 1);
	JLabel name1 = new JLabel("Введите email:");
	 name1_field = new JTextField("@", 1);
	
	container.add(name);
	container.add(name_field);
	container.add(name1);
	container.add(name1_field);
	
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
		String name1 = name1_field.getText();
		String ismale = "Мужской";
		if(!male.isSelected()) 
			ismale = "Женский";
		
		boolean checkbox = check.isSelected();
		
		JOptionPane.showMessageDialog(null, "Ваша почта: "+ name1 + "\n"+ "Ваш пол: "+ ismale + "\nВы согласны?", "Привет" + name, JOptionPane.PLAIN_MESSAGE);
	}
}
}
