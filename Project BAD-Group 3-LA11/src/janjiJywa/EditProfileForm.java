package janjiJywa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditProfileForm extends JInternalFrame implements ActionListener {
	
	JPanel palingLuar, kiri, kanan, judulUpdatePanel, contentKiriPanel, contentKananPanel, userNamePanel, 
	usernameFieldPanel, userEmailPanel, userEmailFieldPanel, userPhonePanel,
	userPhoneFieldPanel, addressPanel, addresTextAreaPanel, userGenderPanel,
	userGenderr, judulChangePanel, oldPasswordPanel, oldPasswordFieldPanel, 
	newPasswordPanel, newPasswordFieldPanel, 
	confirmPasswordPanel, confirmPasswordFieldPanel, 
	buttonKiriPanel, buttonKananPanel, genderPanel, genderLabelPanel;
	  
	JLabel  update, change, userName, userEmail, userPhone, userAddress, oldPassword,
	newPassword, confirmPassword, gender;
	  
	JTextField usernameField, userEmailField, userPhoneField;
	  
	JTextArea addressTextArea;
	  
	JPasswordField oldPasswordField, newPasswordField, confirmPasswordField;
	
	JRadioButton female,male;
	  
	JButton changeButton, updateButton;
	  
	ButtonGroup genderGroup;
	  
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	String currentID;
	
	String passwordLama;
	
	
	public EditProfileForm(String currentID) {
		//initialize
		intialize();
		
		//set
		set();
				
		//Current ID
		this.currentID = currentID;
			
		//get date
		getData();
	}
	
	
	public void intialize() {
		 //Panel
		 palingLuar = new JPanel(new GridLayout(1,2));
		 kiri = new JPanel(new GridLayout(3,1));
		 kanan = new JPanel(new GridLayout(3,1));
		 judulUpdatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		 contentKiriPanel = new JPanel(new GridLayout(0,2));
		 contentKananPanel = new JPanel(new GridLayout(0,2));
		 userNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 usernameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 userEmailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 userEmailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 userPhonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 userPhoneFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 addressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		 addresTextAreaPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
		 genderLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		 genderPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
		 userGenderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 userGenderr = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		 judulChangePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		 oldPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 oldPasswordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 newPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 newPasswordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 confirmPasswordFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		 buttonKiriPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		 buttonKananPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
		 
		//Label
		 update = new JLabel("Update Profile");
		 change = new JLabel("Change Password");
		 userName = new JLabel("Username");
		 userEmail = new JLabel("User Email");
		 userPhone = new JLabel("User Phone");
		 userAddress = new JLabel("User Address");
		 gender = new JLabel ("Gender");
		 oldPassword = new JLabel("Old Password");
		 newPassword = new JLabel("New Password");
		 confirmPassword = new JLabel("Confirm Password");	   
		 
		 //Field
		 usernameField = new JTextField();
		 usernameField.setPreferredSize(new Dimension(200,20));
		 
		 userEmailField = new JTextField();
		 userEmailField.setPreferredSize(new Dimension(200,20));
		  
		 userPhoneField = new JTextField();
		 userPhoneField.setPreferredSize(new Dimension(200,20));
		 
		 //Address
		 addressTextArea = new JTextArea();
		 addressTextArea.setPreferredSize(new Dimension(200,100));
		 
		 //Button
		 updateButton = new JButton("Update Profile");
		 changeButton = new JButton("Change Password");
		 
		 //radio button gender
		 female = new JRadioButton("Female");
		 male = new JRadioButton("Male");
		 
		 genderGroup = new ButtonGroup();
		 genderGroup.add(female);
		 genderGroup.add(male);
		 
		 //Password field
		 oldPasswordField = new JPasswordField();
		 oldPasswordField.setPreferredSize(new Dimension(200,20));
		 
		 newPasswordField = new JPasswordField();
		 newPasswordField.setPreferredSize(new Dimension(200,20));
		 
		 confirmPasswordField = new JPasswordField();
		 confirmPasswordField.setPreferredSize(new Dimension(200,20));
	 }

	 
	 public void set() { 
		  judulUpdatePanel.add(update);
		  kiri.add(judulUpdatePanel);
		  
		  userNamePanel.add(userName);
		  usernameFieldPanel.add(usernameField);
		  
		  userEmailPanel.add(userEmail);
		  userEmailFieldPanel.add(userEmailField);
		  
		  userPhonePanel.add(userPhone);
		  userPhoneFieldPanel.add(userPhoneField);
		  
		  addressPanel.add(userAddress);
		  addresTextAreaPanel.add(addressTextArea);
		  
		  addressPanel.add(userAddress);
		  addresTextAreaPanel.add(addressTextArea);
		  
		  genderLabelPanel.add(gender);
		  genderPanel.add(female);
		  genderPanel.add(male);
		  
		  contentKiriPanel.add(userNamePanel);
		  contentKiriPanel.add(usernameFieldPanel);
		  
		  contentKiriPanel.add(userEmailPanel);
		  contentKiriPanel.add(userEmailFieldPanel);
		  
		  contentKiriPanel.add(userPhonePanel);
		  contentKiriPanel.add(userPhoneFieldPanel);
		  
		  contentKiriPanel.add(addressPanel);
		  contentKiriPanel.add(addresTextAreaPanel);
		  
		  contentKiriPanel.add(genderLabelPanel);
		  contentKiriPanel.add(genderPanel);
		  
		  kiri.add(contentKiriPanel);
		  
		  buttonKiriPanel.add(updateButton);
		  kiri.add(buttonKiriPanel);
		  
		  judulChangePanel.add(change);
		  kanan.add(judulChangePanel);
		  
		  oldPasswordPanel.add(oldPassword);
		  oldPasswordFieldPanel.add(oldPasswordField);
		  
		  newPasswordPanel.add(newPassword);
		  newPasswordFieldPanel.add(newPasswordField);
		  
		  confirmPasswordPanel.add(confirmPassword);
		  confirmPasswordFieldPanel.add(confirmPasswordField);
		  
		  contentKananPanel.add(oldPasswordPanel);
		  contentKananPanel.add(oldPasswordFieldPanel);
		  
		  contentKananPanel.add(newPasswordPanel);
		  contentKananPanel.add(newPasswordFieldPanel);
		  
		  contentKananPanel.add(confirmPasswordPanel);
		  contentKananPanel.add(confirmPasswordFieldPanel);
		  
		  kanan.add(contentKananPanel);
		  
		  buttonKananPanel.add(changeButton);
		  kanan.add(buttonKananPanel);
		  
		  palingLuar.add(kiri);
		  palingLuar.add(kanan);
		  
		  this.add(palingLuar);
		  
//		  Agar dapat langsung terbuka maksimum
//		  try {
//			  this.setMaximum(true);
//			} catch (PropertyVetoException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		  this.setResizable(false);
		  this.setMaximizable(true);
		  this.setClosable(true);
		  this.setSize(680, 400);
		  this.setVisible(true);	
		  
		  palingLuar.setBackground(new Color(0, 191, 255));
		  kiri.setBackground(new Color(0, 191, 255));
		  kanan.setBackground(new Color(0, 191, 255));
		  judulUpdatePanel.setBackground(new Color(0, 191, 255));
		  contentKiriPanel.setBackground(new Color(0, 191, 255));
		  contentKananPanel.setBackground(new Color(0, 191, 255));
		  userNamePanel.setBackground(new Color(0, 191, 255));
		  usernameFieldPanel.setBackground(new Color(0, 191, 255));
		  userEmailPanel.setBackground(new Color(0, 191, 255));
		  userEmailFieldPanel.setBackground(new Color(0, 191, 255));
		  userPhonePanel.setBackground(new Color(0, 191, 255));
		  userPhoneFieldPanel.setBackground(new Color(0, 191, 255));
		  addressPanel.setBackground(new Color(0, 191, 255));
		  addresTextAreaPanel.setBackground(new Color(0, 191, 255));
		  genderLabelPanel.setBackground(new Color(0, 191, 255));
		  genderPanel.setBackground(new Color(0, 191, 255));
		  userGenderPanel.setBackground(new Color(0, 191, 255));
		  userGenderr.setBackground(new Color(0, 191, 255));
		  judulChangePanel.setBackground(new Color(0, 191, 255));
		  oldPasswordPanel.setBackground(new Color(0, 191, 255));
		  oldPasswordFieldPanel.setBackground(new Color(0, 191, 255));
		  newPasswordPanel.setBackground(new Color(0, 191, 255));
		  newPasswordFieldPanel.setBackground(new Color(0, 191, 255));
		  confirmPasswordPanel.setBackground(new Color(0, 191, 255));
		  confirmPasswordFieldPanel.setBackground(new Color(0, 191, 255));
		  buttonKiriPanel.setBackground(new Color(0, 191, 255));
		  buttonKananPanel.setBackground(new Color(0, 191, 255));
		  
		  
		  
		  
		  updateButton.addActionListener(this);
		  changeButton.addActionListener(this);
	 }
	 
	 
	 public void getData() {
		 //Refresh
		 usernameField.setText("");
		 userEmailField.setText("");
		 userPhoneField.setText("");
		 addressTextArea.setText("");
		 female.setSelected(false);
		 male.setSelected(false);
		 oldPasswordField.setText("");
		 newPasswordField.setText("");
		 confirmPasswordField.setText("");
		 
		 String queryDataUser = String.format("SELECT * FROM users WHERE UserID = '%s'", currentID);
		 ResultSet getDataUser = connector.executeQuery(queryDataUser);
		 
		 try {
			 if(getDataUser.next()) {
				 String username, email, phone, adress, oldPass, gender;
				 
				 username = getDataUser.getString("UserName");
				 email = getDataUser.getString("UserEmail");
				 phone = getDataUser.getString("UserPhone");
				 adress = getDataUser.getString("UserAddress");
				 oldPass = getDataUser.getString("UserPassword");
				 gender = getDataUser.getString("UserGender");
				 
				 usernameField.setText(username);
				 userEmailField.setText(email);
				 userPhoneField.setText(phone);
				 addressTextArea.setText(adress);
				 oldPasswordField.setText(oldPass);
				 
				 passwordLama = oldPass;
				 
				 if(gender.equalsIgnoreCase("female")) {
					 female.setSelected(true);
				 }else {
					 male.setSelected(true);
				 }
			 }
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	 }
	 
	 
	 @Override
	 public void actionPerformed(ActionEvent arg0) {
		 if(arg0.getSource() == updateButton) {
			 String userName, email, address, validasi = "true", phone, gender="";
			 userName = usernameField.getText();
			 email = userEmailField.getText();
			 address = addressTextArea.getText();
			 phone =  userPhoneField.getText();
			 
			 if(female.isSelected()) {
				 gender = "female";
			 }else {
				 gender = "male";
			 }
			 
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
			 
			 if(validasi.equals("true")) {
				 
				 String message = "Are You Sure Want to Update Profile?";
					String option[] = {"Yes", "No"};
					int mauUpdate = JOptionPane.showOptionDialog(
							null, 
							message, 
							"Warning", 
							0, 
							JOptionPane.QUESTION_MESSAGE, 
							null, 
							option, 
							option[0]);
					
					switch(mauUpdate) {
					case 0:
						 String queryUpdate = String.format("UPDATE users SET UserName = '%s', UserEmail = '%s', UserPhone = '%s', "
							 		+ "UserAddress = '%s',  UserGender = '%s' WHERE UserID = '%s'" , userName, email, phone, address, gender, currentID);
						 connector.execute(queryUpdate);
							 
						 JOptionPane.showMessageDialog(null, "Successfully Update Profile!");
							 
						break;
					case 1:
						break;
					}
			 }
			 
		 }else if(arg0.getSource() == changeButton) {
			 String oldPassword, newPassword, confirmPassword, validasi = "true";
			 oldPassword = oldPasswordField.getText();
			 newPassword = newPasswordField.getText();
			 confirmPassword = confirmPasswordField.getText();
			 
			 if(!(oldPassword.equals(passwordLama))) {
				 JOptionPane.showMessageDialog(null, "Old Password must match with user current password!", "Error", JOptionPane.ERROR_MESSAGE);
				 validasi = "false";
			 }
			 
			 boolean validasiPasswordKecil = newPassword.matches("^(?=.*[a-z])(?=.*[0-9])[a-z0-9]+$");
			 boolean validasiPasswordBesar = newPassword.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$");
			 
			 if(!(newPassword.length() >= 5 && newPassword.length() <= 30) || !(validasiPasswordKecil == true || validasiPasswordBesar ==  true)) {
				 JOptionPane.showMessageDialog(null, "Please fill with valid password!",  "Error", JOptionPane.ERROR_MESSAGE);
				 validasi = "false";
			 }
			 
			 if(!(confirmPassword.equals(newPassword))) {
				 JOptionPane.showMessageDialog(null, "Confirmation Password must match with New Password!",  "Error", JOptionPane.ERROR_MESSAGE);
				 validasi = "false";
			 }
			
			 if(validasi.equals("true")) {
				 
				 String message = "Are You Sure Want to Change Password?";
					String option[] = {"Yes", "No"};
					int mauChange = JOptionPane.showOptionDialog(
							null, 
							message, 
							"Warning", 
							0, 
							JOptionPane.QUESTION_MESSAGE, 
							null, 
							option, 
							option[0]);
					
					switch(mauChange) {
					case 0:
						 String queryChange = String.format("UPDATE users SET UserPassword = '%s' WHERE UserID = '%s'", newPassword, currentID);
						 connector.execute(queryChange);
							 
						 JOptionPane.showMessageDialog(null, "Successfully Change Password!");
							 
						break;
					case 1:
						break;
					}
					
			 }
			 
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
	 
	 
}

