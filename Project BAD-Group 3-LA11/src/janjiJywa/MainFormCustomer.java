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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MainFormCustomer extends JFrame implements ActionListener {
	JMenuBar menu;
	JMenu  menuProfile, menuTransaction ;
	JLabel welcome;
	JPanel welcomePanel;
	JMenuItem  editProfile, logOff, exit, buyBeverage, viewTransactionHistory ;
	JDesktopPane mainCustomerPane;
	JInternalFrame sayWelcome;
	String currentUserName, currentID;
	private boolean paneSudahDiBuat = false;
	
	public void initialize() {
		
		menu = new JMenuBar ();
		
		menuProfile = new JMenu("Profile");
		menuTransaction = new JMenu("Transaction");
		
		editProfile = new JMenuItem ("Edit Profile");
		logOff= new JMenuItem ("Log Off");
		exit = new JMenuItem ("Exit");
		buyBeverage = new JMenuItem ("Buy Beverage");
		viewTransactionHistory = new JMenuItem ("View Transaction History");
		
		mainCustomerPane = new JDesktopPane();
		
		sayWelcome = new JInternalFrame();
	}
	
	
	public void setComponent() {
		//menu
		this.setJMenuBar(menu);
		menuProfile.add(editProfile);
		menuProfile.add(logOff);
		menuProfile.add(exit);
		
		menuTransaction.add(buyBeverage);
		menuTransaction.add(viewTransactionHistory);
		
		menu.add(menuProfile);
		menu.add(menuTransaction);
		
		editProfile.addActionListener(this);
		logOff.addActionListener(this);
		exit.addActionListener(this);
		
		buyBeverage.addActionListener(this);
		viewTransactionHistory.addActionListener(this);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(paneSudahDiBuat == false) {
			add(mainCustomerPane);
			paneSudahDiBuat = true;
		}
		
		if(arg0.getSource() == buyBeverage) {
			welcome.setVisible(false);
			mainCustomerPane.add(new BuyBeverageForm(this.currentID));
			mainCustomerPane.setBackground(new Color(0, 191, 255));
		}else if(arg0.getSource() == viewTransactionHistory) {
			welcome.setVisible(false);
			mainCustomerPane.add(new TransactionForm(this.currentID));
			mainCustomerPane.setBackground(new Color(0, 191, 255));
		}else if(arg0.getSource() == editProfile) {
			welcome.setVisible(false);
			mainCustomerPane.add(new EditProfileForm(this.currentID));
			mainCustomerPane.setBackground(new Color(0, 191, 255));
		}else if(arg0.getSource() == logOff) {
			this.dispose(); 
			new LoginForm();
		}else if(arg0.getSource() == exit) {
			this.dispose();
		}
		
	}
	
	
	public MainFormCustomer(String UserName, String currentID) {
		this.setTitle("Main Form");
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		initialize();
		setComponent();
		currentUserName = UserName;
		this.currentID = currentID;
		sayWelcome();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(0, 191, 255));
	}
	
	
	public void sayWelcome() {
		welcome = new JLabel("Welcome to Janji Jywa, " + currentUserName, SwingConstants.CENTER);
		add(welcome, BorderLayout.CENTER);
		welcome.setBackground(new Color(0, 191, 255));
		welcome.setOpaque(true);
	}

}