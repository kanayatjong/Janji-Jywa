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
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
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

public class BuyBeverageForm extends JInternalFrame implements MouseListener, ActionListener{
	JPanel titlePanel, contentPanel, fieldPanel, fieldKiriPanel, fieldKananPanel, tablePanel,
	beverageIDPanel, beverageNamePanel, beverageTypePanel, beveragePricePanel, beverageStockPanel, beverageQuantityPanel,
	beverageIDFieldPanel, beverageNameFieldPanel, beverageTypeFieldPanel, beveragePriceFieldPanel, beverageStockFieldPanel, addButtonPanel,
	beverageQuantitySpinnerPanel, tableCartPanel, removeSelectedCartPanel, clearCartPanel, checkoutPanel, buttonBawahPanel;
	
	JLabel title, beverageID, beverageName, beverageType, beveragePrice, beverageStock, beverageQuantity;
	
	JTextField beverageIDField, beverageNameField, beverageTypeField, beveragePriceField, beverageStockField;
	
	JTable beverageTable, cartTable;
	
	DefaultTableModel dtm, dtmCart;
	
	JScrollPane spBeverage, spCart;
	
	JButton addToCart, removeSelectedCart, clearCart, checkout;
	
	SpinnerNumberModel beverageQuantitySpinnerModel;
	
	JSpinner beverageQuantitySpinner;
	
	String currentID;
	
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	
	public BuyBeverageForm(String currentID) {
		//initialize
		internalFrameinitialize();
		
		//set
		internalFrameSet();
		
		//get data dari beverages
		getData();
		
		//Current ID
		this.currentID = currentID;
		
		//get data dari cart
		getDataCart();
	}
	
	public void internalFrameinitialize() {
		//panel
		titlePanel = new JPanel();
		contentPanel = new JPanel(new GridLayout(5,1));
		tablePanel = new JPanel(new BorderLayout());
		fieldPanel = new JPanel(new GridLayout(1,2));
		fieldKiriPanel = new JPanel(new GridLayout(0,2));
		fieldKananPanel = new JPanel(new GridLayout(0,2));
		beverageIDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beveragePricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageStockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageQuantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageIDFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageNameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageTypeFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beveragePriceFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		beverageStockFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addButtonPanel = new JPanel();
		beverageQuantitySpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tableCartPanel = new JPanel(new BorderLayout()); 
		removeSelectedCartPanel = new JPanel();
		clearCartPanel = new JPanel();
		checkoutPanel = new JPanel();
		buttonBawahPanel = new JPanel();
				
		//Tabel
		String couloumnBeverageName[] = {"Beverage Id", "Beverage Name", "Beverage Type", "Beverage Price", "Beverage Stock"};
		dtm = new DefaultTableModel(couloumnBeverageName, 0);
		beverageTable = new JTable(dtm);
		spBeverage = new JScrollPane(beverageTable);
				
		String couloumnCartName[] = {"Beverage Id", "Beverage Name", "Beverage Type", "Beverage Price", "Beverage Stock", "Beverage Quantity", "Sub Total"};
		dtmCart = new DefaultTableModel(couloumnCartName, 0);
		cartTable = new JTable(dtmCart);
		spCart = new JScrollPane(cartTable);
				
		//label
		title = new JLabel("Buy Beverages");
		beverageID = new JLabel("Beverage ID");	
		beverageName = new JLabel("Beverage Name");	
		beverageType = new JLabel("Beverage Type");	
		beveragePrice = new JLabel("Beverage Price");	
		beverageStock = new JLabel("Beverage Stock");	
		beverageQuantity = new JLabel("Beverage Quantity");	
				
		//field
		beverageIDField = new JTextField();
		beverageIDField.setPreferredSize(new Dimension(160,20));
		beverageIDField.setEditable(false);
				
		beverageNameField = new JTextField();
		beverageNameField.setPreferredSize(new Dimension(160,20));
		beverageNameField.setEditable(false);
				
		beverageTypeField = new JTextField();
		beverageTypeField.setPreferredSize(new Dimension(160,20));
		beverageTypeField.setEditable(false);
				
		beveragePriceField = new JTextField();
		beveragePriceField.setPreferredSize(new Dimension(160,20));
		beveragePriceField.setEditable(false);
				
		beverageStockField = new JTextField();
		beverageStockField.setPreferredSize(new Dimension(160,20));
		beverageStockField.setEditable(false);
				
		//Spinner
		beverageQuantitySpinnerModel = new SpinnerNumberModel(0, 0, 1000, 1);
		beverageQuantitySpinner = new JSpinner(beverageQuantitySpinnerModel);
				
		//button
		addToCart = new JButton("Add To Cart");
		removeSelectedCart = new JButton("Remove Selected Cart");
		clearCart = new JButton("Clear Cart");
		checkout = new JButton("Checkout");
		
	}
	
	
	public void internalFrameSet() {
		//Field dan label
		beverageIDPanel.add(beverageID);
		beverageNamePanel.add(beverageName);
		beverageTypePanel.add(beverageType);
		beveragePricePanel.add(beveragePrice);
		beverageStockPanel.add(beverageStock);
		beverageQuantityPanel.add(beverageQuantity);
		beverageIDFieldPanel.add(beverageIDField);
		beverageNameFieldPanel.add(beverageNameField);
		beverageTypeFieldPanel.add(beverageTypeField);
		beveragePriceFieldPanel.add(beveragePriceField);
		beverageStockFieldPanel.add(beverageStockField);
		beverageQuantitySpinnerPanel.add(beverageQuantitySpinner);
		
		//field kiri
		fieldKiriPanel.add(beverageIDPanel);
		fieldKiriPanel.add(beverageIDFieldPanel);
		fieldKiriPanel.add(beverageNamePanel);
		fieldKiriPanel.add(beverageNameFieldPanel);
		fieldKiriPanel.add(beverageTypePanel);
		fieldKiriPanel.add(beverageTypeFieldPanel);
		
		//Field Kanan
		fieldKananPanel.add(beveragePricePanel);
		fieldKananPanel.add(beveragePriceFieldPanel);
		fieldKananPanel.add(beverageStockPanel);
		fieldKananPanel.add(beverageStockFieldPanel);
		fieldKananPanel.add(beverageQuantityPanel);
		fieldKananPanel.add(beverageQuantitySpinnerPanel);
		
		//gabungan field
		fieldPanel.add(fieldKiriPanel);
		fieldPanel.add(fieldKananPanel);
		
		//button add
		addButtonPanel.add(addToCart);
		
		//button bawah
		removeSelectedCartPanel.add(removeSelectedCart);
		clearCartPanel.add(clearCart);
		checkoutPanel.add(checkout);
		buttonBawahPanel.add(removeSelectedCartPanel);
		buttonBawahPanel.add(clearCartPanel);
		buttonBawahPanel.add(checkoutPanel);
		
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
		this.setSize(680, 400);
		this.setVisible(true);
		
		titlePanel.add(title);
		
		tablePanel.add(spBeverage);
		contentPanel.add(tablePanel);
		contentPanel.add(fieldPanel);
		contentPanel.add(addButtonPanel);
		tableCartPanel.add(spCart);
		contentPanel.add(tableCartPanel);
		contentPanel.add(buttonBawahPanel);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		
		//Warna
		titlePanel.setBackground(new Color(0, 191, 255));
		contentPanel.setBackground(new Color(0, 191, 255));
		tablePanel.setBackground(new Color(0, 191, 255));
		fieldPanel.setBackground(new Color(0, 191, 255));
		fieldKiriPanel.setBackground(new Color(0, 191, 255));
		fieldKananPanel.setBackground(new Color(0, 191, 255));
		beverageIDPanel.setBackground(new Color(0, 191, 255));
		beverageNamePanel.setBackground(new Color(0, 191, 255));
		beverageTypePanel.setBackground(new Color(0, 191, 255));
		beveragePricePanel.setBackground(new Color(0, 191, 255));
		beverageStockPanel.setBackground(new Color(0, 191, 255));
		beverageQuantityPanel.setBackground(new Color(0, 191, 255));
		beverageIDFieldPanel.setBackground(new Color(0, 191, 255));
		beverageNameFieldPanel.setBackground(new Color(0, 191, 255));
		beverageTypeFieldPanel.setBackground(new Color(0, 191, 255));
		beveragePriceFieldPanel.setBackground(new Color(0, 191, 255));
		beverageStockFieldPanel.setBackground(new Color(0, 191, 255));
		addButtonPanel.setBackground(new Color(0, 191, 255));
		beverageQuantitySpinnerPanel.setBackground(new Color(0, 191, 255));
		tableCartPanel.setBackground(new Color(0, 191, 255));
		removeSelectedCartPanel.setBackground(new Color(0, 191, 255));
		clearCartPanel.setBackground(new Color(0, 191, 255));
		checkoutPanel.setBackground(new Color(0, 191, 255));
		buttonBawahPanel.setBackground(new Color(0, 191, 255));
		
		beverageTable.addMouseListener(this);
		addToCart.addActionListener(this);
		removeSelectedCart.addActionListener(this);
		clearCart.addActionListener(this);
		checkout.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Integer quantity = (Integer) beverageQuantitySpinner.getValue();
		int rowDiKlik = beverageTable.getSelectedRow();
		int rowCartDiKlik = cartTable.getSelectedRow();
		
		String stock = "";
		
		try {
			stock = dtm.getValueAt(rowDiKlik, 4).toString();
		}catch(Exception e) {
			stock = "";
		}
		
		if (arg0.getSource() == addToCart) {
			if(beverageIDField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please Select the Beverage!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(quantity < 1) {
				JOptionPane.showMessageDialog(null, "Quantity Can’t be Less Than 1!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(quantity > Integer.parseInt(stock)) {
				JOptionPane.showMessageDialog(null, "Quantity Can’t be More Than Its Beverage Stock!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				String idCart, namaCart, tipeCart;
				Integer priceCart, stockCart, subTotal;
				idCart = beverageIDField.getText();
				namaCart = beverageNameField.getText();
				tipeCart = beverageTypeField.getText();
				priceCart = Integer.parseInt(beveragePriceField.getText());
				stockCart = Integer.parseInt(beverageStockField.getText());
				subTotal = priceCart * quantity;
				
				String queryIdSama = String.format("SELECT * FROM carts WHERE BeverageID = '%s' AND UserID = '%s'", idCart, currentID);
				ResultSet resultSetIdSama =  connector.executeQuery(queryIdSama);
				try {
					if(resultSetIdSama.next()) {
						//Update data di tabel cart
						//id yang sama ada di row
						int rowIdSama = 0;
						
						for(int i = 0; i < dtmCart.getRowCount(); i++) {
							if(idCart.equals(dtmCart.getValueAt(i, 0))) {
								rowIdSama = i;
								break;
							} 
						}
	
						String quantityCart = dtmCart.getValueAt(rowIdSama, 5).toString();
						int quantitySekarang = Integer.parseInt(quantityCart) + quantity;
						
						if( Integer.parseInt(stock) < quantity) {
							JOptionPane.showMessageDialog(null, "Quantity Can’t be More Than Its Beverage Stock!", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							//kurangi stock - quantity yang mau dimasukin ke cart
							int stockBeverageCart = stockCart - quantity;
							
							//kurangi stock di database beverage
							String query = String.format("UPDATE beverages SET BeverageStock = %d WHERE BeverageID =  '%s'", stockBeverageCart,  idCart);
							connector.execute(query);
							
							//update quantity di database cart
							String queryCart = String.format("UPDATE carts SET Quantity	= %d WHERE BeverageID = '%s' AND UserID = '%s'", quantitySekarang,  
									idCart, currentID);
							connector.execute(queryCart);
							
							//Refresh
							getData();
							getDataCart();
							refreshField();
							
							JOptionPane.showMessageDialog(null, "Successfully Update Quantity!");
						}
					}else {
						//insert data ke cart
						String queryInsertCart = String.format("INSERT INTO carts VALUES ('%s','%s', %d)", currentID, beverageIDField.getText(), quantity);
						connector.execute(queryInsertCart);
						
						//kurangi stock - quantity yang mau dimasukin ke cart
						int stockBeverageCart = stockCart - quantity;
		
						String query = String.format("UPDATE beverages SET BeverageStock = %d WHERE BeverageID =  '%s'", stockBeverageCart,  idCart);
						connector.execute(query);
						
						//Refresh
						getData();
						getDataCart();
						refreshField();
						
						JOptionPane.showMessageDialog(null, "Successfully Insert Cart!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					
				}
			}
			
		}else if (arg0.getSource() == removeSelectedCart) {
			if (rowCartDiKlik < 0) {
				JOptionPane.showMessageDialog(null, "Please Select the Beverage to Remove!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				//tambahin kembali ke beverage
				String beverageIDKembalikanStock = dtmCart.getValueAt(rowCartDiKlik, 0).toString(),
						beverageQtyKembalikanStock = dtmCart.getValueAt(rowCartDiKlik, 5).toString(),
						stockSebelum = dtmCart.getValueAt(rowCartDiKlik, 4).toString();
				
				Integer stockTambahKembali = Integer.parseInt(stockSebelum) + Integer.parseInt(beverageQtyKembalikanStock);
				
				String query = String.format("UPDATE beverages SET BeverageStock = %d WHERE BeverageID =  '%s'", 
						stockTambahKembali,  beverageIDKembalikanStock);
				connector.execute(query);
				
				//delete dari table cart
				String idBeverageRemove = dtmCart.getValueAt(rowCartDiKlik, 0).toString();
				dtmCart.removeRow(rowCartDiKlik);
				
				//delete dari database cart
				String queryRemove = String.format("DELETE FROM carts WHERE BeverageID = '%s' AND UserID = '%s'", idBeverageRemove, currentID);
				connector.execute(queryRemove);
				
				//refresh
				getData();
				getDataCart();
				
				JOptionPane.showMessageDialog(null, "Successfully Delete Item From Cart!");
			}
		}else if (arg0.getSource() == clearCart) {
			String message = "Are You Sure Want to Clear Cart?";
			String option[] = {"Yes", "No"};
			int mauClear = JOptionPane.showOptionDialog(
					null, 
					message, 
					"Confirmation Message", 
					0, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					option, 
					option[0]);
			
			switch(mauClear) {
			case 0:
				//add kembali stock ke beverage stock
				for(int i = 0; i < dtmCart.getRowCount(); i++) {
					String beverageIDKembalikanStock = dtmCart.getValueAt(i, 0).toString(),
							beverageQtyKembalikanStock = dtmCart.getValueAt(i, 5).toString(),
							stockSebelum = dtmCart.getValueAt(i, 4).toString();
					
					Integer stockTambahKembali = Integer.parseInt(stockSebelum) + Integer.parseInt(beverageQtyKembalikanStock);
					
					String query = String.format("UPDATE beverages SET BeverageStock = %d WHERE BeverageID =  '%s'", 
							stockTambahKembali,  beverageIDKembalikanStock);
					connector.execute(query);
				}
				
				//clear tabel cart
				dtmCart.setRowCount(0);
				
				//clear database cart
				String queryRemove = String.format("DELETE FROM carts WHERE UserID = '%s'", currentID);
				connector.execute(queryRemove);
				
				//refresh
				getData();
				getDataCart();
				
				JOptionPane.showMessageDialog(null, "Successfully Clear Cart!");
				break;
			case 1:
				break;
			}
			
		}else if(arg0.getSource() == checkout) {
			String message = "Are You Sure Want to Checkout Cart?";
			String option[] = {"Yes", "No"};
			int mauCheckout = JOptionPane.showOptionDialog(
					null, 
					message, 
					"Confirmation Message", 
					0, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					option, 
					option[0]);
			
			switch(mauCheckout) {
			case 0:
				String transactionID = generateID();
				
				Date date = new Date(System.currentTimeMillis());
				
				date.toString();
				
				String queryInsertTransactionHeader = String.format("INSERT INTO headertransactions VALUES ('%s', '%s', '%s')", transactionID, currentID, date);
				connector.execute(queryInsertTransactionHeader);
				
				for(int i = 0; i < dtmCart.getRowCount(); i++) {
					String idBeverageCart = dtmCart.getValueAt(i, 0).toString();
					String quantityCart = dtmCart.getValueAt(i, 5).toString();
					Integer quantityCartInt = Integer.parseInt(quantityCart);
					
					String queryInsertTransactionDetail = String.format("INSERT INTO detailtransactions VALUES ('%s', '%s', %d)", transactionID, idBeverageCart, quantityCartInt);
					connector.execute(queryInsertTransactionDetail);
					
				}
				//clear cart
				dtmCart.setRowCount(0);
				
				//clear database cart
				String queryRemove = String.format("DELETE FROM carts WHERE UserID = '%s'", currentID);
				connector.execute(queryRemove);
				
				//refresh
				getData();
				getDataCart();
				
				JOptionPane.showMessageDialog(null, "Successfully Checkout Cart!");
				break;
			case 1:
				break;
			}
		}
		
	}
	
	
	public void getData() {
		//refresh data
		dtm.setRowCount(0); 
		String query = "SELECT * FROM beverages";
		ResultSet getData = connector.executeQuery(query);
		
		try {
			while(getData.next()) {
				String id, nama, tipe;
				Integer price, stock;
				
				id = getData.getString("BeverageID");
				nama = getData.getString("BeverageName");
				tipe = getData.getString("BeverageType");
				price = getData.getInt("BeveragePrice");
				stock = getData.getInt("BeverageStock");
				
				dtm.addRow(new String[] {id, nama, tipe, price +"", stock +"" });
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void getDataCart() {
		//refresh data
		dtmCart.setRowCount(0);
		
		String beverageIdCart;
		Integer beverageQuantityCart;
		
		//select data dari cart
		String query = String.format("SELECT * FROM carts WHERE UserID = '%s'", currentID);
		ResultSet getDataCart = connector.executeQuery(query);
		
		try {
			while(getDataCart.next()) {
				//database cart ada userid, beverageid, quantity
				beverageIdCart = getDataCart.getString("BeverageID");
				beverageQuantityCart = getDataCart.getInt("Quantity");
				
				//add row dulu
				dtmCart.addRow(new String[] {beverageIdCart, "", "", "", "", beverageQuantityCart+"", ""});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//untuk tahu beverage id apa di row apa
		for(int i = 0; i < dtmCart.getRowCount(); i++) {
			int nomorRow = i;
			String beverageIdYangMauDiambil = dtmCart.getValueAt(i, 0).toString();
			
			String queryDataFromBeverageToCart = String.format("SELECT * FROM beverages WHERE BeverageID = '%s'", beverageIdYangMauDiambil);
			ResultSet getDataFromBeverageToCart = connector.executeQuery(queryDataFromBeverageToCart);
			
			try {
				if(getDataFromBeverageToCart.next()) {
					String beverageNameCart, beverageTypeCart, beverageQuantity;
					Integer beveragePriceCart, beverageStockCart, beverageSubTotalCart;
					
					beverageNameCart = getDataFromBeverageToCart.getString("BeverageName");
					beverageTypeCart = getDataFromBeverageToCart.getString("BeverageType");
					beveragePriceCart = getDataFromBeverageToCart.getInt("BeveragePrice");
					beverageStockCart = getDataFromBeverageToCart.getInt("BeverageStock");
					beverageQuantity = dtmCart.getValueAt(i, 5).toString();
					beverageSubTotalCart = Integer.parseInt(beverageQuantity) * beveragePriceCart;

					dtmCart.setValueAt(beverageNameCart, nomorRow, 1);
					dtmCart.setValueAt(beverageTypeCart, nomorRow, 2);
					dtmCart.setValueAt(beveragePriceCart, nomorRow, 3);
					dtmCart.setValueAt(beverageStockCart, nomorRow, 4);
					dtmCart.setValueAt(beverageSubTotalCart, nomorRow, 6);	
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	
	
	public String generateID() {
		String query = "SELECT COUNT(*) AS rowcount FROM headertransactions";
		String id = "TR";
		ResultSet jumlahRow = connector.executeQuery(query);
		try {
			while(jumlahRow.next()) {
				int count = jumlahRow.getInt("rowcount");
				id = id + String.format("%03d", count + 1);
				}
			}catch (SQLException e) {
				e.printStackTrace();
				}
		
		return id;
	}
	
	
	public void refreshField() {
		beverageIDField.setText("");
		beverageNameField.setText("");
		beverageTypeField.setText("");
		beverageStockField.setText("");
		beveragePriceField.setText("");
		beverageQuantitySpinnerModel.setValue(0);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == beverageTable) {
			int rowDiKlik = beverageTable.getSelectedRow();
			
			String id = dtm.getValueAt(rowDiKlik, 0).toString(),
					nama = dtm.getValueAt(rowDiKlik, 1).toString(),
					tipe = dtm.getValueAt(rowDiKlik, 2).toString(),
					price = dtm.getValueAt(rowDiKlik, 3).toString(),
					stock = dtm.getValueAt(rowDiKlik, 4).toString();
			
			if(Integer.parseInt(stock) == 0) {
				JOptionPane.showMessageDialog(null, "There is no more stock for this beverage!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				beverageIDField.setText(id);
				beverageNameField.setText(nama);
				beverageTypeField.setText(tipe); 
				beveragePriceField.setText(price); 
				beverageStockField.setText(stock); 
			}
		}	
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
