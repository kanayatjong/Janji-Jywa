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
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class ManageBeverageForm extends JInternalFrame implements MouseListener, ActionListener{
	JPanel titlePanel, tablePanel, contentPanel, contentPanel2, fieldKiriPanel,fieldKananPanel, contentPanel3, buttonKiriPanel, 
	buttonKananPanel, buttonUpdatePanel, buttonDeletePanel,contentPanel4, tombolKiriPanel, tombolKananPanel, buttonAddStockPanel,
	newBeverageIDPanel, newBeverageNamePanel, newBeverageTypePanel, newBeveragePricePanel, newBeverageStockPanel, newBeverageIDFieldPanel, 
	newBeverageNameFieldPanel, newBeveragePriceFieldPanel, newBeverageBoxPanel, newBeverageStockSpinnerPanel, beverageIDPanel,
	beverageNamePanel, beverageTypePanel, beveragePricePanel,beverageStockPanel, beverageIDFIeldPanel, beverageNameFieldPanel, 
	beveragePriceFieldPanel, beverageStockFieldPanel, beverageTypeBoxPanel, addStockLabelPanel, spinnerAddStockPanel;
	
	JLabel title, newBeverageID, newBeverageName, newBeverageType, newBeveragePrice, newBeverageStock, beverageID, beverageName, 
	beverageType, beveragePrice, beverageStock, addStockLabel;
	
	JTextField newBeverageIDField, newBeverageNameField, newBeveragePriceField, beverageIDField, beverageNameField, 
	beveragePriceField, beverageStockField;
	
	JButton updateBeverage, deleteBeverage, addStock, insertBeverage, reset;
	
	JComboBox<String> newBeverageTypeBox;
	JComboBox<String> beverageTypeBox;
	
	JSpinner newBeverageStockSpinner;
	SpinnerNumberModel nbss;
	
	JSpinner spinnerAddStock;
	SpinnerNumberModel sas;
	
	JTable beverageTable;
	DefaultTableModel dtm;
	
	JScrollPane sp;
	
	DatabaseConnector connector = DatabaseConnector.getConnector();

	
	public ManageBeverageForm() {
		//initialize
		InternalFrameInitialize();
		
		//set
		InternalFrameSet();
		
		//get data
		getDataBeverage();
		
		//generate new beverage id
		generateIDNewBeverage();
	}
	
	public void InternalFrameInitialize() {
		//panel
		titlePanel = new JPanel();
		tablePanel = new JPanel(new BorderLayout());
		contentPanel = new JPanel(new GridLayout(4,1));
		contentPanel2 = new JPanel(new GridLayout(1,2));
		fieldKiriPanel = new JPanel(new GridLayout(0,2));
		fieldKananPanel = new JPanel(new GridLayout(0,2));
		contentPanel3 = new JPanel(new GridLayout(1,2));
		buttonKiriPanel = new JPanel();
		buttonKananPanel = new JPanel(new GridLayout(1,2));
		buttonUpdatePanel = new JPanel();
		buttonDeletePanel = new JPanel();
		contentPanel4 = new JPanel(new GridLayout(1,3));
		tombolKiriPanel = new JPanel();
		tombolKananPanel = new JPanel();
		buttonAddStockPanel = new JPanel();
		
		newBeverageIDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeverageNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeverageTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeveragePricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeverageStockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeverageIDFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeverageNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeveragePriceFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeverageBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newBeverageStockSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		beverageIDPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageNamePanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageTypePanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beveragePricePanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageStockPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageIDFIeldPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageNameFieldPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beveragePriceFieldPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageStockFieldPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageTypeBoxPanel  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		addStockLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		spinnerAddStockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//table
		String coloumnManageBeverage[] = {"Beverage ID", "Beverage Name", "Beverage Type", "Beverage Price", "Beverage Stock"};
		dtm = new DefaultTableModel(coloumnManageBeverage, 0);
		beverageTable = new JTable(dtm);
		sp = new JScrollPane(beverageTable);
		
		//combo box
		String[] namaTipeBaru = new String[] {"Coffee", "Boba", "Tea", "Smoothies"};
		newBeverageTypeBox = new JComboBox<>(namaTipeBaru);	
		newBeverageBoxPanel.add(newBeverageTypeBox);
		
		String[] namaTipe = new String[] {"Coffee", "Boba", "Tea", "Smoothies"};
		beverageTypeBox = new JComboBox<>(namaTipe);	
		beverageTypeBoxPanel.add(beverageTypeBox);
		
		//button
		updateBeverage = new JButton("Update Beverage");
		deleteBeverage = new JButton("Delete Beverage");
		addStock = new JButton("Add Stock");
		insertBeverage = new JButton("Insert Beverage");
		reset = new JButton("Reset");
		
		//spinner
		nbss = new SpinnerNumberModel(0,0,1000,1);
		newBeverageStockSpinner = new JSpinner(nbss);
		newBeverageStockSpinnerPanel.add(newBeverageStockSpinner);
		
		sas = new SpinnerNumberModel(0,0,1000,1);
		spinnerAddStock = new JSpinner(sas);
		spinnerAddStockPanel.add(spinnerAddStock);
		
		//kumpulan label
		title = new JLabel("Manage Beverage");
		newBeverageID = new JLabel("New Beverage ID");
		newBeverageName = new JLabel("New Beverage Name");
		newBeverageType = new JLabel("New Beverage Type");
		newBeveragePrice = new JLabel("New Beverage Price");
		newBeverageStock = new JLabel("New Beverage Stock");
		
		beverageID = new JLabel("Beverage ID");
		beverageName = new JLabel("Beverage Name");
		beverageType = new JLabel("Beverage Type");
		beveragePrice = new JLabel("Beverage Price");
		beverageStock = new JLabel("Beverage Stock");
		
		addStockLabel = new JLabel("Add Stock");
		
		//kumpulan field
		newBeverageIDField = new JTextField();
		newBeverageIDField.setPreferredSize(new Dimension(200,20));
		newBeverageIDField.setEditable(false);
		
		newBeverageNameField = new JTextField();
		newBeverageNameField.setPreferredSize(new Dimension(200,20));
		
		newBeveragePriceField = new JTextField();
		newBeveragePriceField.setPreferredSize(new Dimension(200,20));
		
		beverageIDField = new JTextField();
		beverageIDField.setPreferredSize(new Dimension(200,20));
		beverageIDField.setEditable(false);
		
		beverageNameField = new JTextField();
		beverageNameField.setPreferredSize(new Dimension(200,20));
		
		beveragePriceField = new JTextField();
		beveragePriceField.setPreferredSize(new Dimension(200,20));
		
		beverageStockField = new JTextField();
		beverageStockField.setPreferredSize(new Dimension(200,20));
		beverageStockField.setEditable(false);
	}
	
	
	public void InternalFrameSet() {
//		Agar dapat langsung terbuka maksimum
//		try {
//			this.setMaximum(true);
//		} catch (PropertyVetoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		this.setResizable(false);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setSize(680, 550);
		this.setVisible(true);
		
		//Title Panel
		titlePanel.add(title);
		
		//isi contentPanel row 1
		tablePanel.add(sp);
		contentPanel.add(tablePanel);
		
		//isi fieldKiriPanel
		newBeverageIDPanel.add(newBeverageID);
		newBeverageIDFieldPanel.add(newBeverageIDField);
		newBeverageNamePanel.add(newBeverageName);
		newBeverageNameFieldPanel.add(newBeverageNameField);
		newBeverageTypePanel.add(newBeverageType);
		newBeveragePricePanel.add(newBeveragePrice);
		newBeveragePriceFieldPanel.add(newBeveragePriceField);
		newBeverageStockPanel.add(newBeverageStock);
		
		fieldKiriPanel.add(newBeverageIDPanel);
		fieldKiriPanel.add(newBeverageIDFieldPanel);
		fieldKiriPanel.add(newBeverageNamePanel);
		fieldKiriPanel.add(newBeverageNameFieldPanel);
		fieldKiriPanel.add(newBeverageTypePanel);
		fieldKiriPanel.add(newBeverageBoxPanel);
		fieldKiriPanel.add(newBeveragePricePanel);
		fieldKiriPanel.add(newBeveragePriceFieldPanel);
		fieldKiriPanel.add(newBeverageStockPanel);
		fieldKiriPanel.add(newBeverageStockSpinnerPanel);
		
		// isi field kanan panel
		beverageIDPanel.add(beverageID);
		beverageIDFIeldPanel.add(beverageIDField);
		beverageNamePanel.add(beverageName);
		beverageNameFieldPanel.add(beverageNameField);
		beverageTypePanel.add(beverageType);
		beveragePricePanel.add(beveragePrice);
		beveragePriceFieldPanel.add(beveragePriceField);
		beverageStockPanel.add(beverageStock);
		beverageStockFieldPanel.add(beverageStockField);
		
		fieldKananPanel.add(beverageIDPanel);
		fieldKananPanel.add(beverageIDFIeldPanel);
		fieldKananPanel.add(beverageNamePanel);
		fieldKananPanel.add(beverageNameFieldPanel);
		fieldKananPanel.add(beverageTypePanel);
		fieldKananPanel.add(beverageTypeBoxPanel);
		fieldKananPanel.add(beveragePricePanel);
		fieldKananPanel.add(beveragePriceFieldPanel);
		fieldKananPanel.add(beverageStockPanel);
		fieldKananPanel.add(beverageStockFieldPanel);
		
		//isi contentPanel row 2
		contentPanel2.add(fieldKiriPanel);
		contentPanel2.add(fieldKananPanel);
		contentPanel.add(contentPanel2);
		
		//isi content panel 3
		buttonKiriPanel.add(insertBeverage);
		
		buttonUpdatePanel.add(updateBeverage);
		buttonDeletePanel.add(deleteBeverage);
		buttonKananPanel.add(buttonUpdatePanel);
		buttonKananPanel.add(buttonDeletePanel);
		
		contentPanel3.add(buttonKiriPanel);
		contentPanel3.add(buttonKananPanel);
		contentPanel.add(contentPanel3);
		
		//isi content 4
		tombolKiriPanel.add(reset);
		addStockLabelPanel.add(addStockLabel);
		
		buttonAddStockPanel.add(addStock);
		tombolKananPanel.add(addStockLabelPanel);
		tombolKananPanel.add(spinnerAddStockPanel);
		tombolKananPanel.add(buttonAddStockPanel);
		
		contentPanel4.add(tombolKiriPanel);
		contentPanel4.add(tombolKananPanel);
		contentPanel.add(contentPanel4);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		
		//warna
		titlePanel.setBackground(new Color(0, 191, 255));
		tablePanel.setBackground(new Color(0, 191, 255));
		contentPanel.setBackground(new Color(0, 191, 255));
		contentPanel2.setBackground(new Color(0, 191, 255));
		fieldKiriPanel.setBackground(new Color(0, 191, 255));
		fieldKananPanel.setBackground(new Color(0, 191, 255));
		contentPanel3.setBackground(new Color(0, 191, 255));
		buttonKiriPanel.setBackground(new Color(0, 191, 255));
		buttonKananPanel.setBackground(new Color(0, 191, 255));
		buttonUpdatePanel.setBackground(new Color(0, 191, 255));
		buttonDeletePanel.setBackground(new Color(0, 191, 255));
		contentPanel4.setBackground(new Color(0, 191, 255));
		tombolKiriPanel.setBackground(new Color(0, 191, 255));
		tombolKananPanel.setBackground(new Color(0, 191, 255));
		buttonAddStockPanel.setBackground(new Color(0, 191, 255));
		
		newBeverageIDPanel.setBackground(new Color(0, 191, 255));
		newBeverageNamePanel.setBackground(new Color(0, 191, 255));
		newBeverageTypePanel.setBackground(new Color(0, 191, 255));
		newBeveragePricePanel.setBackground(new Color(0, 191, 255));
		newBeverageStockPanel.setBackground(new Color(0, 191, 255));
		newBeverageIDFieldPanel.setBackground(new Color(0, 191, 255));
		newBeverageNameFieldPanel.setBackground(new Color(0, 191, 255));
		newBeveragePriceFieldPanel.setBackground(new Color(0, 191, 255));
		newBeverageBoxPanel.setBackground(new Color(0, 191, 255));
		newBeverageStockSpinnerPanel.setBackground(new Color(0, 191, 255));
		
		beverageIDPanel.setBackground(new Color(0, 191, 255));
		beverageNamePanel.setBackground(new Color(0, 191, 255));
		beverageTypePanel.setBackground(new Color(0, 191, 255));
		beveragePricePanel.setBackground(new Color(0, 191, 255));  
		beverageStockPanel.setBackground(new Color(0, 191, 255));
		beverageIDFIeldPanel.setBackground(new Color(0, 191, 255));
		beverageNameFieldPanel.setBackground(new Color(0, 191, 255));
		beveragePriceFieldPanel.setBackground(new Color(0, 191, 255));
		beverageStockFieldPanel.setBackground(new Color(0, 191, 255));
		beverageTypeBoxPanel.setBackground(new Color(0, 191, 255));
		
		addStockLabelPanel.setBackground(new Color(0, 191, 255));
		spinnerAddStockPanel.setBackground(new Color(0, 191, 255));
		
		beverageTable.addMouseListener(this);
		updateBeverage.addActionListener(this);
		deleteBeverage.addActionListener(this);
		addStock.addActionListener(this);
		reset.addActionListener(this);
		insertBeverage.addActionListener(this);
	}
	
	
	public void getDataBeverage() {
		//Refresh Data
		dtm.setRowCount(0); 
		
		String query = "SELECT * FROM beverages";
		ResultSet getData = connector.executeQuery(query);
		
		try {
			while(getData.next()) {
				String id, name, type;
				int price, stock;
				
				id = getData.getString("BeverageID");
				name = getData.getString("BeverageName");
				type = getData.getString("BeverageType");
				price = getData.getInt("BeveragePrice");
				stock = getData.getInt("BeverageStock");
				
				dtm.addRow(new String[] {id, name, type, price +"", stock +"" });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == updateBeverage) {
			String id, nama, tipe, validasi = "benar", price , stock;
			price= beveragePriceField.getText();
			id = beverageIDField.getText();
			nama = beverageNameField.getText();
			tipe = (String) beverageTypeBox.getSelectedItem();
			
			
			if(beverageIDField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Fill The Beverage ID!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}
			
			if(!(nama.length()>= 5 && nama.length()<=30)) {
				JOptionPane.showMessageDialog(null, "Beverage Name Must Consist of 5 - 30 Characters!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}
				
			if(isNumeric(price) == false ) {
				JOptionPane.showMessageDialog(null, "Beverage Price Must Numeric!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}else if(Integer.parseInt(price) == 0) {
				JOptionPane.showMessageDialog(null, "Beverage Price Must More Than 0!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}
			
			if(validasi.equals("benar")) {
				String message = "Are You Sure Want to Update Beverage?";
				String option[] = {"Yes", "No"};
				int mauUpdate = JOptionPane.showOptionDialog(
						null, 
						message, 
						"Confirmation Message", 
						0, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						option, 
						option[0]);
				
				switch(mauUpdate) {
				case 0:
					String query = String.format("UPDATE beverages SET BeverageName = '%s', BeverageType = '%s', BeveragePrice = %s WHERE BeverageID = '%s'", 
							nama,  tipe, price, id);
					connector.execute(query);
					
					//refresh table
					getDataBeverage();
					
					//refresh field
					beverageIDField.setText("");
					beverageNameField.setText("");
					beverageTypeBox.setSelectedItem("Coffee");
					beveragePriceField.setText(""); 
					beverageStockField.setText("");
					
					JOptionPane.showMessageDialog(null, "Successfully Update Beverage!");
					
					break;
				case 1:
					break;
				}
			}
			
		}else if(arg0.getSource() == deleteBeverage) {
			String id;
			id = beverageIDField.getText();
			
			if(beverageIDField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Fill The Beverage ID!", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				String message = "Are You Sure Want to Delete Beverage?";
				String option[] = {"Yes", "No"};
				int mauHapus = JOptionPane.showOptionDialog(
						null, 
						message, 
						"Confirmation Message", 
						0, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						option, 
						option[0]);
				
				switch(mauHapus) {
				case 0:
					String queryRemove = String.format("DELETE FROM beverages WHERE BeverageID = '%s'", id);
					connector.execute(queryRemove);
					
					//refresh table
					getDataBeverage();
					
					//refresh field
					beverageIDField.setText("");
					beverageNameField.setText("");
					beverageTypeBox.setSelectedItem("Coffee");
					beveragePriceField.setText(""); 
					beverageStockField.setText("");
					
					JOptionPane.showMessageDialog(null, "Successfully Delete Beverage!");
					
					break;
				case 1:
					break;
				}
			}
		}else if(arg0.getSource() == addStock) {
			String id, validasi = "benar", stock;
			id = beverageIDField.getText();
			stock = beverageStockField.getText();
			Integer addStock = (Integer) spinnerAddStock.getValue();
			
			
			if(beverageIDField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Fill The Beverage ID!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}
			
			if(addStock == 0) {
				JOptionPane.showMessageDialog(null, "Add Stock Must be More Than 0!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}
			
			if(validasi.equals("benar")) {
				String message = "Are You Sure Want to Add Beverage Stock?";
				String option[] = {"Yes", "No"};
				int mauAddStock = JOptionPane.showOptionDialog(
						null, 
						message, 
						"Confirmation Message", 
						0, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						option, 
						option[0]);
				
				switch(mauAddStock) {
				case 0:
					int stockSetelahTambah = Integer.parseInt(stock) + addStock;
					
					String query = String.format("UPDATE beverages SET BeverageStock = %d WHERE BeverageID = '%s'", stockSetelahTambah, id);
					connector.execute(query);
					
					//refresh table
					getDataBeverage();
					
					//refresh field
					beverageIDField.setText("");
					beverageNameField.setText("");
					beverageTypeBox.setSelectedItem("Coffee");
					beveragePriceField.setText(""); 
					beverageStockField.setText("");
					
					JOptionPane.showMessageDialog(null, "Successfully Add Beverage Stock!");
					
					break;
				case 1:
					break;
				}
			}
		}else if(arg0.getSource() == reset) {
			beverageIDField.setText("");
			beverageNameField.setText("");
			beverageTypeBox.setSelectedItem("Coffee");
			beveragePriceField.setText(""); 
			beverageStockField.setText("");
			spinnerAddStock.setValue(0);
			newBeverageIDField.setText("");
			newBeverageNameField.setText("");
			newBeveragePriceField.setText("");
			newBeverageTypeBox.setSelectedItem("Coffee");
			newBeverageStockSpinner.setValue(0);
			
			//generate new beverage id
			generateIDNewBeverage();
			
		}else if(arg0.getSource() == insertBeverage) {
			String newId, newName, newPrice, newType, validasi = "benar";
			int newStock;
			
			newId = newBeverageIDField.getText();
			newName = newBeverageNameField.getText();
			newPrice = newBeveragePriceField.getText();
			newType = (String) newBeverageTypeBox.getSelectedItem();
			newStock = (int) newBeverageStockSpinner.getValue();
			
			if(!(newName.length()>= 5 && newName.length()<=30)) {
				JOptionPane.showMessageDialog(null, "New Beverage Name Must Consist of 5 - 30 Characters!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}
			
			if(isNumeric(newPrice) == false ) {
				JOptionPane.showMessageDialog(null, "New Beverage Price Must Numeric!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}else if(Integer.parseInt(newPrice) == 0) {
				JOptionPane.showMessageDialog(null, "New Beverage Price Must More Than 0!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}
			
			if(newStock == 0) {
				JOptionPane.showMessageDialog(null, "New Beverage Stock Must be More Than 0!", "Error", JOptionPane.ERROR_MESSAGE);
				validasi = "false";
			}
			
			if(validasi.equals("benar")) {
				String message = "Are You Sure Want to Insert New Beverage Stock?";
				String option[] = {"Yes", "No"};
				int mauInsert = JOptionPane.showOptionDialog(
						null, 
						message, 
						"Confirmation Message", 
						0, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						option, 
						option[0]);
				
				switch(mauInsert) {
				case 0:
					String queryInsertCart = String.format("INSERT INTO beverages VALUES ('%s','%s','%s', %s, %d)", newId, newName, newType, newPrice, newStock);
					connector.execute(queryInsertCart);
					
					//refresh table
					getDataBeverage();
					
					//refresh field
					newBeverageIDField.setText("");
					newBeverageNameField.setText("");
					newBeveragePriceField.setText("");
					newBeverageTypeBox.setSelectedItem("Coffee");
					newBeverageStockSpinner.setValue(0);
					
					JOptionPane.showMessageDialog(null, "Successfully Insert New Beverage!");
					
					//generate new beverage id
					generateIDNewBeverage();
					
					break;
				case 1:
					break;
				}
			}
		}
		
		
		
	}

	
	public void generateIDNewBeverage() {
		String queryIdLastRowPengiriman = "SELECT * FROM beverages WHERE BeverageID=(SELECT max(BeverageID) FROM beverages)";
		ResultSet IdPengirimanRowTerakhir= connector.executeQuery(queryIdLastRowPengiriman);
		String id = "BE";
		
		String idTerakhir = "";
		
		try {
			if(IdPengirimanRowTerakhir.next()) {
				idTerakhir = IdPengirimanRowTerakhir.getString("BeverageID");
				String angkaIdterakhir = idTerakhir.substring(2, 5);
				int angkaSekarang  = Integer.parseInt(angkaIdterakhir) + 1;
				
				id = id + String.format("%03d", angkaSekarang);
				newBeverageIDField.setText(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int rowDiKlik = beverageTable.getSelectedRow();
		
		String id = dtm.getValueAt(rowDiKlik, 0).toString(),
				nama = dtm.getValueAt(rowDiKlik, 1).toString(),
				tipe = dtm.getValueAt(rowDiKlik, 2).toString(),
				price = dtm.getValueAt(rowDiKlik, 3).toString(),
				stock = dtm.getValueAt(rowDiKlik, 4).toString();
		
			beverageIDField.setText(id);
			beverageNameField.setText(nama);

			if(tipe.equals("Coffee")) {
				beverageTypeBox.setSelectedItem("Coffee");
			}else if(tipe.equals("Boba")) {
				beverageTypeBox.setSelectedItem("Boba");
			}else if(tipe.equals("Tea")) {
				beverageTypeBox.setSelectedItem("Tea");
			}else {
				beverageTypeBox.setSelectedItem("Smoothies");
			}
			
			beveragePriceField.setText(price); 
			beverageStockField.setText(stock); 
			
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

}
