package janjiJywa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import janjiJywa.DatabaseConnector;
import janjiJywa.MainFormAdmin;
import janjiJywa.MainFormCustomer;


public class LoginForm extends JFrame implements ActionListener, MouseListener{
	JPanel titlePanel, contentPanel, buttonPanel, loginButtonPanel, 
	signUpPanel, emailLabelPanel, emailFieldPanel,
	passwordLabelPanel, passwordFieldPanel;
	
	JLabel  title, email, password, signUp ;
	
	JTextField emailField;
	
	JPasswordField  passwordField;
	
	JButton  login;
	
	String userName;
	
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	
	public void intialize() {
		//Panel
		titlePanel = new JPanel();
		contentPanel = new JPanel(new GridLayout(0, 2, 2, 5));
		buttonPanel =  new JPanel(new GridLayout(0,1));
		loginButtonPanel = new JPanel();
		signUpPanel = new JPanel();
		emailLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//Label
		title = new JLabel("Login Form");
		email = new JLabel("Email");
		password = new JLabel("Password");
		signUp = new JLabel("Sign Up Here");
		
		//Field
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(200,20));
		
		//Password Field
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(200,20));
		
		//Button
		login = new JButton("Login");
		
	}
	
	
	public void setComponent() {
		titlePanel.add(title);
		
		loginButtonPanel.add(login);
		signUpPanel.add(signUp);
		emailLabelPanel.add(email);
		emailFieldPanel.add(emailField);
		passwordLabelPanel.add(password);
		passwordFieldPanel.add(passwordField);
		
		contentPanel.add(emailLabelPanel);
		contentPanel.add(emailFieldPanel);
		contentPanel.add(passwordLabelPanel);
		contentPanel.add(passwordFieldPanel);
		
		buttonPanel.add(loginButtonPanel);
		buttonPanel.add(signUpPanel);
		
		add(titlePanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		login.addActionListener(this);
		signUp.addMouseListener(this);
		
		titlePanel.setBackground(new Color(0, 191, 255));
		contentPanel.setBackground(new Color(0, 191, 255));
		buttonPanel.setBackground(new Color(0, 191, 255));
		loginButtonPanel.setBackground(new Color(0, 191, 255));
		signUpPanel.setBackground(new Color(0, 191, 255));
		emailLabelPanel.setBackground(new Color(0, 191, 255));
		emailFieldPanel.setBackground(new Color(0, 191, 255));
		passwordLabelPanel.setBackground(new Color(0, 191, 255));
		passwordFieldPanel.setBackground(new Color(0, 191, 255));
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String email, password;
		email = emailField.getText();
		password = passwordField.getText();
		
		if (arg0.getSource() == login) {
			if(email.isEmpty() && !(password.isEmpty())) {
				JOptionPane.showMessageDialog(null, "Email must be filled!",  "Error", JOptionPane.ERROR_MESSAGE);
				} else if(!(email.isEmpty()) && password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Password must be filled!",  "Error", JOptionPane.ERROR_MESSAGE);
					} else if(emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Email and Password must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							String query = String.format("SELECT * FROM users WHERE UserEmail = '%s' AND UserPassword = '%s'", email, password);
							ResultSet resultSet =  connector.executeQuery(query);
							try {
								if(resultSet.next()) {
									String role = resultSet.getString("UserRole");
									String currentID = resultSet.getString(1);
									userName = resultSet.getString("UserName");
									if(role.equals("Customer")) {
										this.dispose(); 
										new MainFormCustomer(userName, currentID);
										}else {
											this.dispose(); 
											new MainFormAdmin(userName, currentID);
											}
									} else {
										JOptionPane.showMessageDialog(null, "Invalid Email or Password", "Error", JOptionPane.ERROR_MESSAGE);
										}
								} catch (SQLException e1) {
									e1.printStackTrace();
									}
							}
			}
		}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.dispose();
		new RegisterForm();
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public LoginForm() {
		this.setTitle("Login Form");
		this.setSize(500, 230);
		this.setLocationRelativeTo(null);
		intialize();
		setComponent();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
}