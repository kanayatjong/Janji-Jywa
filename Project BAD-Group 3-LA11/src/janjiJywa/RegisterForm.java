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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterForm extends JFrame implements ActionListener, MouseListener{
	JPanel titlePanel, contentPanel, buttonPanel, idLabelPanel, usernameLabelPanel,
	emailLabelPanel, phoneLabelPanel, addressLabelPanel, passwordLabelPanel,
	genderLabelPanel, roleLabelPanel, signInLabelPanel, registerButtonPanel,
	idFieldPanel , usernameFieldPanel, passwordFieldPanel, emailFieldPanel, phoneFieldPanel,
	addressTextAreaPanel, genderPanel, roleComboBoxPanel;
	
	JLabel  title, id, username, email, phone, address, password, 
	gender, role,  signIn;
	
	JTextField idField , usernameField, emailField, phoneField;
	
	JTextArea addressTextArea;
	
	JPasswordField passwordField;
	
	JRadioButton female,male;
	
	JButton register;
	
	ButtonGroup genderGroup;
	
	JComboBox <String> pilihanRole;
	
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	
	public void intialize() {
		//Panel
		titlePanel = new JPanel();
		contentPanel = new JPanel(new GridLayout(0, 2));
		buttonPanel = new JPanel(new GridLayout(0,1));
		idLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		usernameLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		phoneLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		addressLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		roleLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		signInLabelPanel = new JPanel();
		registerButtonPanel = new JPanel();
		idFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		usernameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		phoneFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addressTextAreaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		roleComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//Label
		title = new JLabel("Register Form");
		id = new JLabel("ID");
		username = new JLabel("User Name"); 
		email = new JLabel("Email"); 
		phone = new JLabel("Phone"); 
		address = new JLabel("Address"); 
		password  = new JLabel("Password"); 
		gender = new JLabel("Gender"); 
		role  = new JLabel("Role"); 
		signIn  = new JLabel("Sign In"); 
		
		//Text Field
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(200,20));
		idField.setEditable(false);
		
		usernameField = new JTextField(); 
		usernameField.setPreferredSize(new Dimension(200,20));
		
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(200,20));
		
		phoneField = new JTextField();
		phoneField.setPreferredSize(new Dimension(200,20)); emailField.setPreferredSize(new Dimension(200,20));
		
		//Password Field
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(200,20));
		
		//Text Area
		addressTextArea = new JTextArea();
		addressTextArea.setPreferredSize(new Dimension(200,80));
		
		//Radio Button
		female = new JRadioButton("Female");
		male = new JRadioButton("Male");
		
		//Button Group
		genderGroup = new ButtonGroup();
		genderGroup.add(female);
		genderGroup.add(male);
		
		//Button
		register = new JButton("Register");
		
		//Combo Box
		String[] jenisRole = new String[] {"Admin", "Customer"};
		pilihanRole = new JComboBox <>(jenisRole);
		roleComboBoxPanel.add(pilihanRole);
		
	}
	
	
	public void setComponent() {
		titlePanel.add(title);
		
		registerButtonPanel.add(register);
		idLabelPanel.add(id);
		usernameLabelPanel.add(username);
		emailLabelPanel.add(email);
		phoneLabelPanel.add(phone);
		passwordLabelPanel.add(password);
		genderLabelPanel.add(gender);
		addressLabelPanel.add(address);
		roleLabelPanel.add(role);
		signInLabelPanel.add(signIn);
		registerButtonPanel.add(register);
		idFieldPanel.add(idField);
		usernameFieldPanel.add(usernameField);
		passwordFieldPanel.add(passwordField);
		emailFieldPanel.add(emailField);
		phoneFieldPanel.add(phoneField);
		addressTextAreaPanel.add(addressTextArea);
		genderPanel.add(female);
		genderPanel.add(male);
		
		contentPanel.add(idLabelPanel);
		contentPanel.add(idFieldPanel);
		contentPanel.add(usernameLabelPanel);
		contentPanel.add(usernameFieldPanel);
		contentPanel.add(emailLabelPanel);
		contentPanel.add(emailFieldPanel);
		contentPanel.add(phoneLabelPanel);
		contentPanel.add(phoneFieldPanel);
		contentPanel.add(addressLabelPanel);
		contentPanel.add(addressTextAreaPanel);
		contentPanel.add(passwordLabelPanel);
		contentPanel.add(passwordFieldPanel);
		contentPanel.add(genderLabelPanel);
		contentPanel.add(genderPanel);
		contentPanel.add(roleLabelPanel);
		contentPanel.add(roleComboBoxPanel);
		
		buttonPanel.add(registerButtonPanel);
		buttonPanel.add(signInLabelPanel);
		
		add(titlePanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		register.addActionListener(this);
		signIn.addMouseListener(this);
		
		titlePanel.setBackground(new Color(0, 191, 255));
		contentPanel.setBackground(new Color(0, 191, 255));
		buttonPanel.setBackground(new Color(0, 191, 255));
		registerButtonPanel.setBackground(new Color(0, 191, 255));
		idLabelPanel.setBackground(new Color(0, 191, 255));
		usernameLabelPanel.setBackground(new Color(0, 191, 255));
		emailLabelPanel.setBackground(new Color(0, 191, 255));
		phoneLabelPanel.setBackground(new Color(0, 191, 255));
		passwordLabelPanel.setBackground(new Color(0, 191, 255));
		genderLabelPanel.setBackground(new Color(0, 191, 255));
		addressLabelPanel.setBackground(new Color(0, 191, 255));
		roleLabelPanel.setBackground(new Color(0, 191, 255));
		signInLabelPanel.setBackground(new Color(0, 191, 255));
		registerButtonPanel.setBackground(new Color(0, 191, 255));
		idFieldPanel.setBackground(new Color(0, 191, 255));
		usernameFieldPanel.setBackground(new Color(0, 191, 255));
		passwordFieldPanel.setBackground(new Color(0, 191, 255));
		emailFieldPanel.setBackground(new Color(0, 191, 255));
		phoneFieldPanel.setBackground(new Color(0, 191, 255));
		addressTextAreaPanel.setBackground(new Color(0, 191, 255));
		genderPanel.setBackground(new Color(0, 191, 255));
		roleComboBoxPanel.setBackground(new Color(0, 191, 255));
		
	}
	
	public void generateID() {
		String query = "SELECT COUNT(*) AS rowcount FROM users";
		String id = "US";
		ResultSet jumlahRow = connector.executeQuery(query);
		try {
			while(jumlahRow.next()) {
				int count = jumlahRow.getInt("rowcount");
				id = id + String.format("%03d", count + 1);
				}
			}catch (SQLException e) {
				e.printStackTrace();
				}
		idField.setText(id);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String userName, email, address, password, validasi = "true", role, id, gender = "";
		String phone = phoneField.getText();
		userName = usernameField.getText();
		email = emailField.getText();
		address = addressTextArea.getText();
		password = passwordField.getText();
		role = (String) pilihanRole.getSelectedItem();
		id = idField.getText();
		
		if(!(userName.length()>= 5 && userName.length()<=30)) {
			JOptionPane.showMessageDialog(null, "Username must be between 5 - 30 characters!", "Error", JOptionPane.ERROR_MESSAGE);
			validasi = "false";
			}
		
		if(validasiEmail(email)== false) {
			JOptionPane.showMessageDialog(null, "Please fill with valid email!", "Error", JOptionPane.ERROR_MESSAGE);
			validasi = "false";
			}
		
		if (!(phone.length()>= 12 && isNumeric(phone) == true)) {
			JOptionPane.showMessageDialog(null, "Phone must be numeric and more than equals 12 digits", "Error", JOptionPane.ERROR_MESSAGE);
			validasi = "false";  	
			}
		
		if(!(address.length() >= 10 && address.endsWith("Street"))) {
			JOptionPane.showMessageDialog(null, "Please fill with valid adress!" , "Error", JOptionPane.ERROR_MESSAGE);
			validasi = "false";
			}
		
		boolean validasiPasswordKecil = password.matches("^(?=.*[a-z])(?=.*[0-9])[a-z0-9]+$") ;
		boolean validasiPasswordBesar = password.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$") ;
		
		if(!(password.length() >= 5 && password.length() <= 30) || !(validasiPasswordKecil == true || validasiPasswordBesar ==  true)) {
			JOptionPane.showMessageDialog(null, "Please fill with valid password!",  "Error", JOptionPane.ERROR_MESSAGE);
			validasi = "false";
			}
		
		if(!female.isSelected() && !male.isSelected()) {
			JOptionPane.showMessageDialog(null, "Please select gender!",  "Error", JOptionPane.ERROR_MESSAGE);
			validasi = "false"; 
			}
		
		if(female.isSelected()) {
			gender = "female";
			}
		
		if(male.isSelected()) {
			gender = "male";
			}
		
		if(!(role.equalsIgnoreCase("customer") || (role.equalsIgnoreCase("admin")))) {
			JOptionPane.showMessageDialog(null, "Please select role!",  "Error", JOptionPane.ERROR_MESSAGE);
			validasi = "false";
			}
		
		if(validasi.equals("true")) {
			String query = String.format("INSERT INTO users VALUES ('%s', '%s', '%s', '%s', null, '%s', '%s', '%s', '%s')", 
					id, userName, email, password, gender, address, phone, role);
			connector.execute(query);
			
			JOptionPane.showMessageDialog(null, "Successfully register!");
			generateID();
			this.dispose();
			new LoginForm();
			}
		
	}
	
	
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
			} catch(NumberFormatException e){  
				return false;  
				}  
		}
	
	
	public static boolean validasiEmail (String input) {
		String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPat.matcher(input);
		return matcher.find();
	}
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.dispose();
		new LoginForm();
		
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


	public RegisterForm() {
		this.setTitle("Register Form");
		this.setSize(500,430);
		this.setLocationRelativeTo(null);
		intialize();
		setComponent();
		generateID();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}