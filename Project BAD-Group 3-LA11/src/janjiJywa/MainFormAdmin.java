package janjiJywa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MainFormAdmin extends JFrame implements ActionListener {
	JMenuBar menu;
	JMenu  menuProfile, menuManage;
	JLabel welcome;
	JPanel welcomePanel;
	JMenuItem  editProfile, logOff, exit, manageBeverage;
	String currentUserName;
	String currentID;
	JDesktopPane mainAdminPane;
	private boolean paneSudahDiBuat = false;
	
	
	public void intialize() {
		menu = new JMenuBar ();
		
		menuProfile = new JMenu("Profile");
		menuManage = new JMenu("Manage");
		
		welcome = new JLabel("Welcome to Janji Jywa ");
		
		welcomePanel = new JPanel();
		
		editProfile = new JMenuItem ("Edit Profile");
		logOff= new JMenuItem ("Log Off");
		exit = new JMenuItem ("Exit");
		manageBeverage = new JMenuItem ("Manage Beverage");
		
		mainAdminPane = new JDesktopPane();
		
	}
	
	
	public void setComponent() {
		this.setJMenuBar(menu);
		menuProfile.add(editProfile);
		menuProfile.add(logOff);
		menuProfile.add(exit);
		
		menuManage.add(manageBeverage);
		
		menu.add(menuProfile);
		menu.add(menuManage);
		
		editProfile.addActionListener(this);
		logOff.addActionListener(this);
		exit.addActionListener(this);
		
		manageBeverage.addActionListener(this);
		
		welcomePanel.add(welcome);
		add(welcomePanel, BorderLayout.CENTER);
		
		welcomePanel.setBackground(new Color(0, 191, 255));
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(paneSudahDiBuat == false) {
			add(mainAdminPane);
			paneSudahDiBuat = true;
		}
		
		if(arg0.getSource() == editProfile) {
			welcome.setVisible(false);
			mainAdminPane.add(new EditProfileForm(this.currentID));
			mainAdminPane.setBackground(new Color(0, 191, 255));
		}else if(arg0.getSource() == logOff) {
			this.dispose(); 
			new LoginForm();
		}else if(arg0.getSource() == exit) {
			this.dispose();
		}else if(arg0.getSource() == manageBeverage) {
			welcome.setVisible(false);
			mainAdminPane.add(new ManageBeverageForm());
			mainAdminPane.setBackground(new Color(0, 191, 255));
		}
		
	}
	
	
	public MainFormAdmin(String userName, String currentID) {
		this.setTitle("Main Form");
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		intialize();
		setComponent();
		currentUserName = userName;
		this.currentID = currentID;
		sayWelcome();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void sayWelcome() {
		welcome = new JLabel("Welcome to Janji Jywa, " + currentUserName, SwingConstants.CENTER);
		add(welcome, BorderLayout.CENTER);
		welcome.setBackground(new Color(0, 191, 255));
		welcome.setOpaque(true);
	}
	
}