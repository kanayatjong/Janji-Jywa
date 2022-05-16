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

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class TransactionForm extends JInternalFrame implements MouseListener{
	JPanel titlePanel, contentPanel, selectedIDLabelPanel, grandTotalLabelPanel
	, selectedIDFieldPanel, grandTotalFieldPanel, tableHeaderPanel, tableDetailPanel, 
	selectedIDPanel, grandTotalPanel;
	
	JLabel  titleLabel, selectedIDLabel, grandTotalLabel;
	
	JTable tableHeader, tableDetail;
	
	DefaultTableModel dtm, dtm2;
	
	JScrollPane scrollpaneHeader, scrollpaneDetail;
	
	JTextField selectedIDField, grandTotalField;
	
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	String currentID;
	
	
	public TransactionForm(String currentID) {
		//initialize
		initialize();
		
		//set component
		setComponent();
		
		//Current ID
		this.currentID = currentID;	
		
		//get data
		getData();
	}
	
	
	public void initialize() {
		//Panel
		titlePanel = new JPanel();
		contentPanel = new JPanel(new GridLayout(4, 1));
		tableHeaderPanel = new JPanel(new BorderLayout());
		selectedIDLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		selectedIDFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tableDetailPanel = new JPanel(new BorderLayout());
		grandTotalLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		grandTotalFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		selectedIDPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		grandTotalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		
		//Label
		titleLabel = new JLabel("Transaction History");
		selectedIDLabel = new JLabel("Selected ID");
		grandTotalLabel = new JLabel("Grand Total");
		
		//text field
		selectedIDField = new JTextField();
		selectedIDField.setPreferredSize(new Dimension(200,20));
		selectedIDField.setEditable(false);
		
		grandTotalField = new JTextField();
		grandTotalField.setPreferredSize(new Dimension(200,20));
		grandTotalField.setEditable(false);
		
		//model, table, scrollpane
		String columnHeaderNames[] = {"Transaction ID", "User ID", "Transaction Date"};
		dtm = new DefaultTableModel(columnHeaderNames, 0);
		tableHeader = new JTable(dtm);
		scrollpaneHeader = new JScrollPane(tableHeader);
		
		String columnDetailNames[] = {"Transaction ID", "Beverage ID", "Beverage Name", "Beverage Type", "Beverage Price", "Beverage Quantity", "SubTotal"};
		dtm2 = new DefaultTableModel(columnDetailNames, 0);
		tableDetail = new JTable(dtm2);
		scrollpaneDetail = new JScrollPane(tableDetail);
		
	}
	
	
	public void setComponent() {
		//title
		titlePanel.add(titleLabel);
		
		tableHeaderPanel.add(scrollpaneHeader);
		selectedIDLabelPanel.add(selectedIDLabel);
		selectedIDFieldPanel.add(selectedIDField);
		tableDetailPanel.add(scrollpaneDetail);
		grandTotalLabelPanel.add(grandTotalLabel);
		grandTotalFieldPanel.add(grandTotalField);
	
		//gabungan
		selectedIDPanel.add(selectedIDLabelPanel);
		selectedIDPanel.add(selectedIDFieldPanel);
		grandTotalPanel.add(grandTotalLabelPanel);
		grandTotalPanel.add(grandTotalFieldPanel);
		
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
		
		contentPanel.add(tableHeaderPanel);
		contentPanel.add(selectedIDPanel);
		contentPanel.add(tableDetailPanel);
		contentPanel.add(grandTotalPanel);

		this.add(titlePanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		
		titlePanel.setBackground(new Color(0, 191, 255));
		contentPanel.setBackground(new Color(0, 191, 255));
		selectedIDPanel.setBackground(new Color(0, 191, 255));
		grandTotalPanel.setBackground(new Color(0, 191, 255));
		selectedIDLabelPanel.setBackground(new Color(0, 191, 255));
		selectedIDFieldPanel.setBackground(new Color(0, 191, 255));
		grandTotalLabelPanel.setBackground(new Color(0, 191, 255));
		grandTotalFieldPanel.setBackground(new Color(0, 191, 255));
		
		tableHeader.addMouseListener(this);
	}
	
	
	public void getData() {
		//refresh data
		dtm.setRowCount(0); 
		String query = String.format("SELECT * FROM headertransactions WHERE UserID = '%s'", currentID);
		ResultSet getData = connector.executeQuery(query);
				
		try {
			while(getData.next()) {
				String transactionId, userId;
				Date transactionDate;
						
				transactionId = getData.getString("TransactionID");
				userId = getData.getString("UserID");
				transactionDate = getData.getDate("TransactionDate");
						
				dtm.addRow(new String[] {transactionId, userId, transactionDate +""});
						
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
	}
	
	
	//get detail
	public void getDataDetail(String transactionId) {
		//refresh data di detail
		dtm2.setRowCount(0);
		
		//id trans yang mau diambil detailnya
		String idTrans = transactionId;
		
		//di detail ada transID, BeverageID, quantity
		String queryDataTransDetail = String.format("SELECT * FROM detailtransactions WHERE TransactionID='%s'", idTrans);
		ResultSet getDataTransDetail = connector.executeQuery(queryDataTransDetail);
		
		try {
			while(getDataTransDetail.next()) {
				String transBeverageID = getDataTransDetail.getString("BeverageID");
				Integer transQuantity = getDataTransDetail.getInt("Quantity");
				
				//add row dulu
				dtm2.addRow(new String[] {idTrans, transBeverageID, "", "", "", transQuantity+"", ""});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//untuk tahu beverage id di row itu apa
		for(int i = 0; i < dtm2.getRowCount(); i++) {
			int nomorRow = i;
			String qty = dtm2.getValueAt(i, 5).toString();
			
			//beverage yang mau diambil datanya
			String beverageId = dtm2.getValueAt(nomorRow, 1).toString();
			
			String getDataBeverage = String.format("SELECT * FROM beverages WHERE BeverageID = '%s'", beverageId);
			ResultSet getDataBeverageTrans = connector.executeQuery(getDataBeverage);
			
			try {
			if(getDataBeverageTrans.next()) {
				String beverageName, beverageType, beveragePrice;
				
				beverageName = getDataBeverageTrans.getString("BeverageName");
				beverageType = getDataBeverageTrans.getString("BeverageType");
				beveragePrice = getDataBeverageTrans.getString("BeveragePrice");
				int subTotal = Integer.parseInt(beveragePrice) * Integer.parseInt(qty);
				
				dtm2.setValueAt(beverageName, nomorRow, 2);
				dtm2.setValueAt(beverageType, nomorRow, 3);
				dtm2.setValueAt(beveragePrice, nomorRow, 4);
				dtm2.setValueAt(subTotal, nomorRow, 6);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tableHeader) {
			int rowDiKlik = tableHeader.getSelectedRow();
			
			String transactionId = dtm.getValueAt(rowDiKlik, 0).toString();
				
			selectedIDField.setText(transactionId);
			
			//Ambil data detail
			getDataDetail(transactionId);
			
			
			//hitung total
			int grandTotal = 0;
			
			for(int i = 0; i < dtm2.getRowCount(); i++) {
				grandTotal = grandTotal + (int) dtm2.getValueAt(i, 6);
			}
			
			grandTotalField.setText(String.valueOf(grandTotal));
			
		}	
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
