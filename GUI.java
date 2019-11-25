// Name: Mya Hussain 
// Date: 2019-11-09
// Assignment: Inventory management system
// Description: An inventory management system that stores objects in a file by 
// serializing and deserializing an array list of objects. Objects can be added 
// edited, sold, searched through and removed from the inventory using the programs 
// graphical user interface.  

package Store;

// imports mainly for graphics and reading and writing to file
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

// start of class
public class GUI {
	
	// attributes, private, only accessible to this class
	public JFrame frmInventoryManagementSystem;
	public JLayeredPane layeredPane;
	
	// list of all product objects in inventory 
	public ArrayList<Product> products = deserializeFromFile();
	
	Product ggg = new Product("iiiiiiii", "Grape", "purple, sweet", 30, 3.99, 2.56);
	Product nnn = new Product("oooooooo", "Banana", "yellow, long", 10, 1.2, 0.5);
	private JTextField TFnameofproduct;
	private JTextField TFproductcode;
	private JTextField TFquantity;
	private JTextField TFretailvalue;
	private JTextField TFcosttopurchase;
	private JTable table;
	private JTextField TFsearchname;
	private JTextField TFsearchcode;
	private JLabel lblProductcode1; 
	private JLabel lblpd1;
	private JLabel lblProductName1;
	private JLabel lblproductquantity1;
	private JLabel lblcostprice1;
	private JLabel lblretailprice1;
	private JLabel lblquantitysold1;
	private JTextField TFremovename;
	private JTextField TFremovecode;
	private JTextField TFpppproductname;
	private JTextField TFppppcode;
	private JTextField TFppppquantity2;
	private JTextField TFppppquantity1;
	private JTable table_1;
	private JTextField TFSN;
	private JTextField TFSC;
	private JTextField TFSQ;
	private JTextField TFPC;
	private JTextField TFSD;
	private JTextField TFPR;
	private JTextField TFQS;
	private JTextField TFQQQQ;
	private JTextField TFQQQQSSSS;
	private JTextField TFRRRRPPPP;
	private JTextField TFCCCCPPPP;
	private JTable table_2;
		
	// Create the application.
	// constructor 
	public GUI() {
		
		// set up GUI
		initialize();
		populateTable();

	}
	
	// function to switch panels and create new screen effect 
	public void switchPanels(JPanel panel) {

		// panels are switched by removing and adding them to layered pane
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
		
	}
	
	
	// function to write object to the file 
	// takes object to serialize, in this case our product list
	public void serializeToFile(ArrayList<Product> listt)   {
		
		// try to store it if it does not store catch with error message 
		try {
		// where to store data
		File f = new File("database");
		
		// test statements to ensure file directory is working and valid
		//System.out.println(f.exists());
		//System.out.println(f.isDirectory());
		//System.out.println(f.canRead());
		//System.out.println(f.canWrite());       
		
		FileOutputStream fileout = new FileOutputStream(f);
		ObjectOutputStream out = new ObjectOutputStream(fileout);
		out.writeObject(listt);
		out.close();
		System.out.println("object was serialized");
		
		
		}
		catch (IOException e) {
			System.out.println("object was not serialized");
		}
		
	}
	
	// deserialize object from file to read data 
	public ArrayList<Product> deserializeFromFile () {
		// return the array list
		ArrayList<Product> p = null;
		
		try {
			// where to get data
			File f = new File("database");
			
			
			// if the file has nothing return empty list (running app for first time)
			if(f.length() == 0) {
				
				p = new ArrayList<Product> ();
				System.out.println("New list of objects returned");
				
			}
			
			// else return what's in the list
			else {			
			FileInputStream filein = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(filein);
			
			// read object of type Product
			p = (ArrayList<Product>) in.readObject();
			in.close();
			filein.close();
			
			System.out.println("object deserialized");
			
			//filein = null;
			//in = null;
			//f = null;
			}
			
		}
		// deal with unexpected error
		catch(ClassNotFoundException | IOException e){
			
			System.out.println("object not deserialized");
			
		}
		
		return p;
		
	}
	
	// takes array list of integers with all indexes to display
	public void displayToSearchContains (ArrayList<Integer> integers) {
		
		DefaultTableModel  model = (DefaultTableModel) table_1.getModel();
		
		// empty the table 
		model.setRowCount(0);
		
		// list of objects to be used to populate table 
		Object rowData[] = new Object[7];
		
		for (int x = 0; x < integers.size(); x++) {
				
				
				// fill the table with variables that correspond to the column
				rowData[0] = products.get(integers.get(x)).getProductName();
				rowData[1] = products.get(integers.get(x)).getProductCode();
				rowData[2] = products.get(integers.get(x)).getProductDescription();
				rowData[3] = products.get(integers.get(x)).getQuantity();
				rowData[4] = products.get(integers.get(x)).getPriceCost();
				rowData[5] = products.get(integers.get(x)).getPriceRetail();
				rowData[6] = products.get(integers.get(x)).getQuantitySold();
				// fill in the row of data then move to next product
				model.addRow(rowData);
				
		}
		
	}
	
	public ArrayList<Integer> containsSearchName (String name) {
		
		name.toLowerCase();
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see if all the names in products contains the character 
		
		for (int x = 0; x < products.size(); x++ ) {
			
			if ((products.get(x).getProductName().strip().toLowerCase()).contains(name)) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Product name character not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		return integers;
		
	}
	
	public ArrayList<Integer> containsSearchCode (String code) {
		
		code.toLowerCase();

		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see if all the codes in products contains the character 
		
		for (int x = 0; x < products.size(); x++ ) {
			
			if ((products.get(x).getProductCode().strip().toLowerCase()).contains(code)) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Product code character not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		return integers;
		
	}
	
	public ArrayList<Integer> containsSearchDescription (String desc) {
		
		desc.toLowerCase();
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see if all the descriptions in products contains the character 
		
		for (int x = 0; x < products.size(); x++ ) {
			
			if ((products.get(x).getProductDescription().strip().toLowerCase()).contains(desc)) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Description character not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		return integers;
		
	}
	
	public ArrayList<Integer> containsSearchQuantity (String quan) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see if all the quantities in products contains the character 
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if ((String.valueOf(products.get(x).getQuantity())).contains(quan)) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"quantity character not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	public ArrayList<Integer> containsSearchRetailPrice (String retail) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see if all the retail prices in products contains the character 
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the retail price has character inputed 
			// type cast to string to compare characters with .contains()
			if ((String.valueOf(products.get(x).getPriceRetail())).contains(retail)) {
				integers.add(x);
				
				
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"retail price character not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
			
		return integers;
		
	}
	
	public ArrayList<Integer> containsSearchCostPrice (String cost) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see if all the cost prices  in products contains the character 
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the price cost have character inputed 
			// type cast to string to compare characters with .contains()
			if ((String.valueOf(products.get(x).getPriceCost())).contains(cost)) {
				integers.add(x);
				
				
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Cost price character not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
			
		return integers;
		
	}
	
	public ArrayList<Integer> containsSearchQuantitySold (String Qsold) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see if all the quantity sold in products contains the character 
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if ((String.valueOf(products.get(x).getQuantitySold())).contains(Qsold)) {
				integers.add(x);
				
				
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Quantity sold character not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
			
		return integers;
		
	}
	
	// function to display "less than more than" product list to table 2
	// takes array list of integers with all indexes to display
	public void displayToLessThanMoreThan (ArrayList<Integer> integers) {
		
		DefaultTableModel  model = (DefaultTableModel) table_2.getModel();
		
		// empty the table 
		model.setRowCount(0);
		
		// list of objects to be used to populate table 
		Object rowData[] = new Object[7];
		
		for (int x = 0; x < integers.size(); x++) {
				
				
				// fill the table with variables that correspond to the column
				rowData[0] = products.get(integers.get(x)).getProductName();
				rowData[1] = products.get(integers.get(x)).getProductCode();
				rowData[2] = products.get(integers.get(x)).getProductDescription();
				rowData[3] = products.get(integers.get(x)).getQuantity();
				rowData[4] = products.get(integers.get(x)).getPriceCost();
				rowData[5] = products.get(integers.get(x)).getPriceRetail();
				rowData[6] = products.get(integers.get(x)).getQuantitySold();
				// fill in the row of data then move to next product
				model.addRow(rowData);
				
		}
		
	}
	
	
	
	public ArrayList<Integer> lessThanQuantity (int quan) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see what products have a lower quantity
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if (products.get(x).getQuantity() < quan) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"No such products found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	public ArrayList<Integer> moreThanQuantity (int quan) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see what products have a greater quantity
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if (products.get(x).getQuantity() > quan) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"No such products found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	public ArrayList<Integer> lessThanQuantitySold (int quanS) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see what products have a lower quantity
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if (products.get(x).getQuantitySold() < quanS) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"No such products found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	public ArrayList<Integer> moreThanQuantitySold (int quanS) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see what products have a lower quantity
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if (products.get(x).getQuantitySold() > quanS) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"No such products found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	public ArrayList<Integer> lessThanRetailPrice (int ret) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see what products have a lower quantity
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if (products.get(x).getPriceRetail() < ret) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"No such numbers found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	public ArrayList<Integer> moreThanPriceRetail (int ret) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see what products have a lower quantity
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if (products.get(x).getPriceRetail() > ret) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"No such numbers found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	public ArrayList<Integer> lessThanCostPrice (int CP) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see what products have a lower quantity
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if (products.get(x).getPriceCost() < CP) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"No such numbers found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	public ArrayList<Integer> moreThanCostPrice (int CP) {
		
		// list to print to table 
		ArrayList<Integer> integers = new ArrayList<Integer>();
		
		// see what products have a lower quantity
		
		for (int x = 0; x < products.size(); x++ ) {
			
			// see if the quantities have character inputed 
			// type cast to string to compare characters with .contains()
			if (products.get(x).getPriceCost() > CP) {
				integers.add(x);
			}
		}
		// show error if not found 
		if (integers.size() == 0) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"No such numbers found",
					"Inane error",JOptionPane.ERROR_MESSAGE);	
			
		}
		
		return integers;
		
	}
	
	// this function searches for a product by name and displays it on the GUI by editing labels
	public void searchName (String name) {
		
		boolean found = false;
		
		// iterate through all products looking for a matching name
		for (int x=0; x< products.size(); x++) {
			
			// if the product names are the same you found the product 
			if (name.toLowerCase().strip().equals(products.get(x).getProductName().toLowerCase().strip())) {
				// display all info for that product 
				lblProductName1.setText(products.get(x).getProductName());
				lblProductcode1.setText(products.get(x).getProductCode());
				lblpd1.setText(products.get(x).getProductDescription());
				
				// casting needed to make string in order to display on label
				lblproductquantity1.setText((String.valueOf(products.get(x).getQuantity())));
				lblretailprice1.setText(String.valueOf(products.get(x).getPriceRetail()));
				lblcostprice1.setText(String.valueOf(products.get(x).getPriceCost()));
				lblquantitysold1.setText(String.valueOf(products.get(x).getQuantitySold()));
				
				found = true;
			}
		}
		
		if (!found) {
			// if program did not find it alert user 
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"name not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// this function searches for a product by code and displays it's info on the GUI by editing labels
	public void searchCode(String code) {
		
		boolean found = false;
		
		// iterate through all products looking for a matching code
		for (int x=0; x< products.size(); x++) {

			// if the code matches display the info
			if (code.toLowerCase().strip().equals(products.get(x).getProductCode().toLowerCase().strip())) {
				// display all info for that product 
				lblProductName1.setText(products.get(x).getProductName());
				lblProductcode1.setText(products.get(x).getProductCode());
				lblpd1.setText(products.get(x).getProductDescription());
				
				// casting needed to make string
				lblproductquantity1.setText((String.valueOf(products.get(x).getQuantity())));
				lblretailprice1.setText(String.valueOf(products.get(x).getPriceRetail()));
				lblcostprice1.setText(String.valueOf(products.get(x).getPriceCost()));
				lblquantitysold1.setText(String.valueOf(products.get(x).getQuantitySold()));
				
				found = true;
				
			}
		}
		
		if (!found) {
			// show error message 
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Code not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	// this function removes a product from the inventory by its code 
	public void removeByCode (String code) {
		
		boolean removed = false;
		
		// iterate through all products looking for a matching code
		for (int x=0; x< products.size(); x++) {
			
			if (code.toLowerCase().strip().equals(products.get(x).getProductCode().toLowerCase().strip())) {
				
				// if the code is found remove it from the inventory -> "arraylist of products"
				products.remove(x);
				removed = true;
				JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Product sucessfully removed",
						"Success",JOptionPane.INFORMATION_MESSAGE);	
				serializeToFile(products);
			}
		}
		
		if (!removed) {
			// show error message 
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Code not found, "
					+ "product not removed","Inane error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	// this function removes a product from inventory by name
	public void removeByName(String name) {
		
		boolean removed = false;
		
		// iterate through all products looking for a matching name
		for (int x=0; x< products.size(); x++) {
			if (name.toLowerCase().equals(products.get(x).getProductName().toLowerCase())) {
				
				// remove product
				products.remove(x);
				removed = true;
				JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Product sucessfully removed",
						"Success",JOptionPane.INFORMATION_MESSAGE);		
				System.out.println(products);
				serializeToFile(products);
			}
		}
		
		if (!removed) {
			// show error message
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"name not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void sellByName (String name, int quantity) {
		boolean found = false;
		boolean validQ = false;
		
		// iterate through all products looking for a matching name
		for (int x=0; x< products.size(); x++) {
			if (name.toLowerCase().equals(products.get(x).getProductName().toLowerCase())) {
						
				found = true;
				
				// find out how many being sold make sure its not more than you have 
				int quantityleft = products.get(x).getQuantity() - quantity;
				
				// if you didn't sell more than you have
				if (quantityleft<0) {
					validQ = false;
				}
				else {
					validQ = true;
					
					// subtract sold units from quantity and update sold unit variable
					products.get(x).setQuantity(quantityleft);
					products.get(x).addQuantitySold(quantity);
					
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Product sold",
							"Success",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		// show error messages depending on what went wrong
		if (!found) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Name not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);
		}
		else if (!validQ) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Quantity too high",
					"Inane error",JOptionPane.ERROR_MESSAGE);
		}

		// update database
		serializeToFile(products);
		
	}
	
	// this function sells a product by its code depending on its quantity
	public void sellByCode (String code, int quantity) {
		boolean found = false;
		boolean validQ = false;
		
		// iterate through all products looking for a matching name
		for (int x=0; x< products.size(); x++) {
			if (code.toLowerCase().equals(products.get(x).getProductCode().toLowerCase())) {
						
				found = true;
				
				// find out how many being sold make sure its not more than you have 
				int quantityleft = products.get(x).getQuantity() - quantity;
				
				// if you didn't sell more than you have
				if (quantityleft<0) {
					validQ = false;
				}
				else {
					validQ = true;
					
					// subtract sold units from quantity and update sold unit variable
					products.get(x).setQuantity(quantityleft);
					products.get(x).addQuantitySold(quantity);
					
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Product sold",
							"Success",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		// show error depending on what went wrong
		if (!found) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Code not found",
					"Inane error",JOptionPane.ERROR_MESSAGE);
		}
		else if (!validQ) {
			JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Quantity too high",
					"Inane error",JOptionPane.ERROR_MESSAGE);
		}

		// update database
		serializeToFile(products);
	}
	
	// this function populates the Jtable, to be used whenever data changes 
	public void populateTable() {
		
		// get latest data
		products = deserializeFromFile();
				
		DefaultTableModel  model= (DefaultTableModel) table.getModel();
		
		// empty the table 
		model.setRowCount(0);
		
		// list of objects to be used to populate table 
		Object rowData[] = new Object[7];
		
		// for every product fill in it's column value
		for (int i= 0; i<products.size(); i++ ) {
			
			// fill the table with variables that correspond to the column
			rowData[0] = products.get(i).getProductName();
			rowData[1] = products.get(i).getProductCode();
			rowData[2] = products.get(i).getProductDescription();
			rowData[3] = products.get(i).getQuantity();
			rowData[4] = products.get(i).getPriceCost();
			rowData[5] = products.get(i).getPriceRetail();
			rowData[6] = products.get(i).getQuantitySold();
			// fill in the row of data then move to next product
			model.addRow(rowData);
		}
	}
	
	// allows you to edit values of products directly from inventory 
	// this function error checks and stores 
	public void updateFromTable () {
		
		// the number of products will be the same at this stage 
		// however the value of some products will be different 
		// this function will error check them and only apply if no error 
		
		// temporary variables  
		String name;
		String code;
		String desc;
		String quan;
		String retail;
		String cost;
		
		// iterate through each product
		for (int x= 0; x< products.size(); x++) {
			
			// iterate through each variable except quantity sold 
			// quantity sold is changed when a product is sold through the app, not manually
		
			// ERROR CHECK NAME
			name = (String) table.getModel().getValueAt(x, 0);
			
			if (name.isBlank()) {
				JOptionPane.showMessageDialog(frmInventoryManagementSystem,"name not stored",
						"Inane error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				boolean uniquename = true;
				// the product name has to be unique
				for(int r =0; r < products.size(); r++) {
					
					if (name.toLowerCase().equals(products.get(r).getProductName().toLowerCase())) {
						// if its the same index obviously there's no real "duplicate"
						if (r == x) {
							
						}
						else {
							uniquename = false;
						}
					}
				}
				
				if (uniquename){
					
					// it passed store it 
					products.get(x).setProductName(name);
					
				}
				else {
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,"name not stored",
							"Inane error",JOptionPane.ERROR_MESSAGE);
					
				}
			}
			
			// ERROR CHECK CODE
			code = (String) table.getModel().getValueAt(x, 1);	
			
			if (code.isBlank()) {
				JOptionPane.showMessageDialog(frmInventoryManagementSystem,"code not stored",
						"Inane error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				// product code must be 8 characters and only 8 characters 
				if (code.length() != 8) {
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,"code not stored",
							"Inane error",JOptionPane.ERROR_MESSAGE);
				}
				// check if it's unique
				else {
					boolean uniquecode = true;
					// the product code  has to be unique
					for(int r =0; r < products.size(); r++) {
						
						if (code.toLowerCase().equals(products.get(r).getProductCode().toLowerCase())) {
							// if its the same index obviously there's no real "duplicate"
							if (r == x) {
								
							}
							else {
								uniquecode = false;
							}
						}
					}
					
					if (uniquecode){
						// success store it
						products.get(x).setProductCode(code);
					}
					else {
						JOptionPane.showMessageDialog(frmInventoryManagementSystem,"code not stored",
								"Inane error",JOptionPane.ERROR_MESSAGE);
					}	
				}
			}

			
			// CHECK DESCRIPTION
			desc = (String) table.getModel().getValueAt(x, 2);
			if (desc.isBlank()) {
				JOptionPane.showMessageDialog(frmInventoryManagementSystem,"description not stored",
						"Inane error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				products.get(x).setProductDescription(desc);	
			}	
			
			
			// CHECK QUANTITY
			
			quan = (table.getModel().getValueAt(x, 3).toString());
			
			// if blank
			if (quan.isBlank()) {
				JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Quantity not stored",
						"Inane error",JOptionPane.ERROR_MESSAGE);
				
			}
			
			// If its not blank, check if its an integer and check to make sure its not 0
			else {
				// if try block fails its not an integer
				try {
					int testquantity= Integer.parseInt(quan);
					
					if (testquantity != 0) {
						products.get(x).setQuantity(testquantity);
						
					}
					else {
						JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Quantity not stored",
								"Inane error",JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				catch (ClassCastException c){
					System.out.println("the quantity is not an integer, catch");
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,"The quantity must be an integer ",
							"Inane error",JOptionPane.ERROR_MESSAGE);

				}
			}	
			
			// Check retail price
			
			retail = (table.getModel().getValueAt(x, 5).toString());
			
			if (retail.isBlank()) {
				JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Retail price not stored ",
						"Inane error",JOptionPane.ERROR_MESSAGE);
			}
			
			// if its not blank make sure it's a double and its not 0
			else {
				try {
					double testquantity= Double.parseDouble(retail);
					
					if (testquantity != 0.0) {
						products.get(x).setPriceRetail(testquantity);
					}
					else {
						JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Retail price cant be 0 ",
								"Inane error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch(ClassCastException b) {
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Retail price not stored ",
							"Inane error",JOptionPane.ERROR_MESSAGE);
				}	
			}		
			
			// check cost price 
			cost = (table.getModel().getValueAt(x, 4).toString());
			
			if (cost.isBlank()) {
				JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Cost price not stored ",
						"Inane error",JOptionPane.ERROR_MESSAGE);
			}
			// if its not blank make sure its an integer and not 0
			else {
				try {
					double testcost= Double.parseDouble(cost);
					
					if (testcost != 0.0) {
						products.get(x).setPriceCost(testcost);
						System.out.println("the cost price is " + products.get(x).getPriceCost());
					}
					else {
						JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Cost price not stored ",
								"Inane error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch(ClassCastException a) {
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Cost price not stored ",
							"Inane error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
			
		// end of loop	
		}
		
		System.out.println("the quantity is "+ products.get(0).getQuantity());
		
	}
	
	
	
	// Initialize the contents of the frame.
	// Any non commented long lines of non repeating code were auto generated by swing window builder
	// I did not have the will power to go insane and write/manually position them all myself
	public void initialize() {
		frmInventoryManagementSystem = new JFrame();
		frmInventoryManagementSystem.setTitle("Inventory Management System");
	
		frmInventoryManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInventoryManagementSystem.getContentPane().setLayout(new BorderLayout(0, 0));
		frmInventoryManagementSystem.setSize(1150, 700);
		JSplitPane splitPane = new JSplitPane();
		frmInventoryManagementSystem.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		layeredPane = new JLayeredPane();
		splitPane.setRightComponent(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(75, 0, 130));
		layeredPane.add(panel_1, "name_24321827492300");
		
		JLabel lblWelcomeToYour = new JLabel("Welcome to your ");
		lblWelcomeToYour.setFont(new Font("Stylus BT", Font.ITALIC, 50));
		lblWelcomeToYour.setForeground(new Color(255, 255, 255));
		
		JLabel lblInventoryManagementSystem = new JLabel("Inventory Management \r\n");
		lblInventoryManagementSystem.setForeground(Color.WHITE);
		lblInventoryManagementSystem.setFont(new Font("Stylus BT", Font.PLAIN, 50));
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setForeground(Color.WHITE);
		lblSystem.setFont(new Font("Stylus BT", Font.PLAIN, 50));
		
		JLabel lblChooseAFunction = new JLabel("Choose a function to get started");
		lblChooseAFunction.setFont(new Font("Yu Gothic UI", Font.PLAIN, 33));
		lblChooseAFunction.setForeground(new Color(255, 255, 255));
		
		JLabel lblByMya = new JLabel("By Mya");
		lblByMya.setFont(new Font("Viner Hand ITC", Font.PLAIN, 20));
		lblByMya.setForeground(new Color(255, 255, 255));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChooseAFunction)
						.addComponent(lblWelcomeToYour, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblInventoryManagementSystem)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSystem, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(105, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(709, Short.MAX_VALUE)
					.addComponent(lblByMya, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(108)
					.addComponent(lblWelcomeToYour, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInventoryManagementSystem, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSystem, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblChooseAFunction)
					.addGap(160)
					.addComponent(lblByMya)
					.addGap(51))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel inventoryLibrary = new JPanel();
		inventoryLibrary.setBackground(new Color(176, 224, 230));
		layeredPane.add(inventoryLibrary, "name_1606944493415100");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblInventoryLibrary = new JLabel("Inventory Library");
		lblInventoryLibrary.setFont(new Font("Times New Roman", Font.PLAIN, 56));
		
		JButton btnSubmitEdits = new JButton("Submit Edits");
		btnSubmitEdits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFromTable();
				serializeToFile(products);
			}
		});
		btnSubmitEdits.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblMakeSureTo = new JLabel("Make sure to click off the cell before submitting");
		lblMakeSureTo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblRemember = new JLabel("Remember: ");
		lblRemember.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblYouCanNot = new JLabel("You can not edit the quantity sold");
		lblYouCanNot.setFont(new Font("Tahoma", Font.ITALIC, 16));
		
		JLabel lblProductNamesAnd = new JLabel("Product names and product codes must be unique");
		lblProductNamesAnd.setFont(new Font("Tahoma", Font.ITALIC, 16));
		GroupLayout gl_inventoryLibrary = new GroupLayout(inventoryLibrary);
		gl_inventoryLibrary.setHorizontalGroup(
			gl_inventoryLibrary.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_inventoryLibrary.createSequentialGroup()
					.addGroup(gl_inventoryLibrary.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSubmitEdits, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_inventoryLibrary.createSequentialGroup()
							.addGap(25)
							.addComponent(lblInventoryLibrary, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_inventoryLibrary.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_inventoryLibrary.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
								.addGroup(gl_inventoryLibrary.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_inventoryLibrary.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRemember, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMakeSureTo)
										.addComponent(lblYouCanNot, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblProductNamesAnd, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap())
		);
		gl_inventoryLibrary.setVerticalGroup(
			gl_inventoryLibrary.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inventoryLibrary.createSequentialGroup()
					.addGap(22)
					.addComponent(lblInventoryLibrary)
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_inventoryLibrary.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inventoryLibrary.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addComponent(btnSubmitEdits)
							.addGap(48))
						.addGroup(gl_inventoryLibrary.createSequentialGroup()
							.addGap(14)
							.addComponent(lblMakeSureTo)
							.addGap(10)
							.addComponent(lblRemember, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblYouCanNot, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblProductNamesAnd, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Name", "Product Code", "Product Description", "Quantity", "Cost Price", "Retail Price", "Quantity Sold"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(265);
		table.getColumnModel().getColumn(3).setPreferredWidth(74);
		scrollPane.setViewportView(table);
		inventoryLibrary.setLayout(gl_inventoryLibrary);
		
		JPanel editInventory = new JPanel();
		editInventory.setBackground(new Color(222, 184, 135));
		layeredPane.add(editInventory, "name_1607050156035400");
		
		JLabel lblAddProductsTo = new JLabel("Add Products to Shop ");
		lblAddProductsTo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 60));
		
		JLabel lblproductcode = new JLabel("Product Code:");
		lblproductcode.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JLabel lblnameofproduct = new JLabel("Name of product:");
		lblnameofproduct.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JLabel lbldescription = new JLabel("Description of product:");
		lbldescription.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JLabel lblCostToPurchase = new JLabel("Cost To Purchase:");
		lblCostToPurchase.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JLabel lblRetailValue = new JLabel("Retail Value:");
		lblRetailValue.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		TFnameofproduct = new JTextField();
		TFnameofproduct.setColumns(10);
		
		TFproductcode = new JTextField();
		TFproductcode.setText("(must be 8 char)");
		TFproductcode.setToolTipText("");
		TFproductcode.setColumns(10);
		
		TFquantity = new JTextField();
		TFquantity.setColumns(10);
		
		TFretailvalue = new JTextField();
		TFretailvalue.setColumns(10);
		
		TFcosttopurchase = new JTextField();
		TFcosttopurchase.setColumns(10);
		
		JTextPane TPdescription = new JTextPane();
		
		JButton btnSubmit = new JButton("Add Item");
		btnSubmit.addActionListener(new ActionListener() {
			
	
			public void actionPerformed(ActionEvent e) {
				// what happens when you push the submit button in  add to inventory panel
				// some variables to error check each input 
				Boolean okname = null;
				Boolean okcode= null;
				Boolean okdescription = null;
				Boolean okquantity = null;
				Boolean okretailprice= null;
				Boolean okcostprice = null;
				String errormessageString = " ";
				
				// here we will make the object based on the data in the text fields
				
				// error check the product name
				if (TFnameofproduct.getText().isBlank()) {
					okname = false;
					errormessageString += "The product name field is empty! ";
				}
				else {
					boolean uniquename = true;
					// the product name has to be unique
					for(int r =0; r < products.size(); r++) {
						
						if (TFnameofproduct.getText().toLowerCase().strip().equals(products.get(r).getProductName().toLowerCase().strip())) {
						uniquename = false;
						
						}
					}
					
					if (uniquename){
						okname = true;
					}
					else {
						okname = false;
						errormessageString += "The product name has been used before! ";
					}
				}
				
				// error check the description 
				if (TPdescription.getText().isBlank()) {
					okdescription = false;
					errormessageString += "The product description field is empty! ";
				}
				else {
					okdescription = true;	
				}				
				
				// error check the product code 
				if (TFproductcode.getText().isBlank()) {
					okcode = false;
					errormessageString += "The product code field is empty! ";
				}
				else {
					// product code must be 8 characters and only 8 characters 
					if (TFproductcode.getText().length() != 8) {
						okcode = false;
						errormessageString += "Product code must be exactly 8 characters! ";	
					}
					// check if it's unique
					else {
						boolean uniquecode = true;
						// the product code  has to be unique
						for(int r =0; r < products.size(); r++) {
							
							if (TFproductcode.getText().toLowerCase().strip().equals(products.get(r).getProductCode().toLowerCase().strip())) {
							uniquecode = false;
							
							}
						}
						
						if (uniquecode){
							okcode = true;
						}
						else {
							okcode = false;
							errormessageString += "The product code has been used before! ";
						}
						
					}
				}
	
				// check quantity
				if (TFquantity.getText().isBlank()) {
					okname = false;
					errormessageString += "The product quantity field is empty! ";
				}
				// If its not blank, check if its an integer and check to make sure its not 0
				else {
					try {
						int testquantity= Integer.parseInt(TFquantity.getText());
						
						if (testquantity != 0) {
							okquantity = true;
						}
						else {
							okquantity = false;
							errormessageString += "The quantity can't be 0! ";
						}
						
					}
					catch (ClassCastException c){
					
						JOptionPane.showMessageDialog(frmInventoryManagementSystem,"The quantity must be an integer ",
								"Inane error",JOptionPane.ERROR_MESSAGE);
						okquantity = false;
						errormessageString += "The quantity must be an integer ";	
					}
				}
				
				// check retail cost
				if (TFretailvalue.getText().isBlank()) {
					okretailprice = false;
					errormessageString += "The retail value field is empty! ";
				}
				// if its not blank make sure it's a double and its not 0
				else {
					try {
						double testquantity= Double.parseDouble(TFretailvalue.getText());
						
						if (testquantity != 0.0) {
							okretailprice = true;
						}
						else {
							okretailprice = false;
							errormessageString += "The retail cost can't be 0! ";
						}
						
					}
					catch(ClassCastException b) {
						okretailprice = false;
						errormessageString += "The retail cost must be a number ";	
						JOptionPane.showMessageDialog(frmInventoryManagementSystem,"The retail cost must be a number ",
								"Inane error",JOptionPane.ERROR_MESSAGE);
					}	
				}		
				
				// check product cost 
				if (TFcosttopurchase.getText().isBlank()) {
					okcostprice = false;
					errormessageString += "The cost to purchase field is empty! ";
				}
				// if its not blank make sure its an integer and not 0
				else {
					try {
						double testquantity= Double.parseDouble(TFcosttopurchase.getText());
						
						if (testquantity != 0.0) {
							okcostprice = true;
						}
						else {
							okcostprice = false;
							errormessageString += "The price to purchase can't be 0! ";
						}
						
					}
					catch(ClassCastException a) {
						okcostprice = false;
						errormessageString += "The price to purchase must be a number! ";	
						JOptionPane.showMessageDialog(frmInventoryManagementSystem,"The price to purchase must be a number! ",
								"Inane error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
				// if all the variables were 'ok' make the object otherwise alert user of the errors
				if(okname && okcode && okdescription && okquantity && okcostprice && okretailprice) {
					
					products.add(new Product(
							TFproductcode.getText().strip(),
							TFnameofproduct.getText().strip(), 
							TPdescription.getText(), 
							Integer.parseInt(TFquantity.getText()), 
							Double.parseDouble(TFretailvalue.getText()), 
							Double.parseDouble(TFcosttopurchase.getText())));
					
					// object added to list list must be serialized to update database 
					serializeToFile(products);
					// tell user object was added
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,"Product added to shop :)","Success",JOptionPane.INFORMATION_MESSAGE);
					errormessageString = " ";
				}
				else {
					// show error box to help user
					JOptionPane.showMessageDialog(frmInventoryManagementSystem,errormessageString,"Inane error",JOptionPane.ERROR_MESSAGE);
					errormessageString = " ";
				}
					
			}
			
		});
		btnSubmit.setBackground(new Color(144, 238, 144));
		btnSubmit.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 30));
		GroupLayout gl_editInventory = new GroupLayout(editInventory);
		gl_editInventory.setHorizontalGroup(
			gl_editInventory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_editInventory.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_editInventory.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_editInventory.createSequentialGroup()
							.addComponent(lblAddProductsTo)
							.addContainerGap(427, Short.MAX_VALUE))
						.addGroup(gl_editInventory.createSequentialGroup()
							.addGroup(gl_editInventory.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_editInventory.createSequentialGroup()
									.addGroup(gl_editInventory.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(TPdescription, Alignment.LEADING)
										.addComponent(lbldescription, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.LEADING, gl_editInventory.createSequentialGroup()
											.addGroup(gl_editInventory.createParallelGroup(Alignment.LEADING)
												.addComponent(lblnameofproduct, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblproductcode))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_editInventory.createParallelGroup(Alignment.LEADING)
												.addComponent(TFproductcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(TFnameofproduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
									.addGroup(gl_editInventory.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRetailValue)
										.addComponent(lblCostToPurchase, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblQuantity))
									.addGap(47)
									.addGroup(gl_editInventory.createParallelGroup(Alignment.TRAILING)
										.addComponent(TFretailvalue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(TFcosttopurchase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(TFquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_editInventory.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
							.addGap(119))))
		);
		gl_editInventory.setVerticalGroup(
			gl_editInventory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_editInventory.createSequentialGroup()
					.addGap(41)
					.addComponent(lblAddProductsTo)
					.addGap(88)
					.addGroup(gl_editInventory.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblnameofproduct, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFnameofproduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFcosttopurchase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCostToPurchase, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_editInventory.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_editInventory.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_editInventory.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblproductcode)
								.addComponent(TFproductcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lbldescription, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(TPdescription, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_editInventory.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_editInventory.createParallelGroup(Alignment.BASELINE)
								.addComponent(TFretailvalue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRetailValue, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_editInventory.createParallelGroup(Alignment.BASELINE)
								.addComponent(TFquantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(55))
		);
		editInventory.setLayout(gl_editInventory);
		
		JPanel sellProduct = new JPanel();
		sellProduct.setBackground(new Color(143, 188, 143));
		layeredPane.add(sellProduct, "name_1607062616016500");
		
		JLabel lblSellProduct = new JLabel("Sell Product");
		lblSellProduct.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		JLabel lblSellByName = new JLabel("Sell By Product Name");
		lblSellByName.setFont(new Font("Tahoma", Font.ITALIC, 25));
		
		JLabel lblSellByProduct = new JLabel("Sell By Product Code");
		lblSellByProduct.setFont(new Font("Tahoma", Font.ITALIC, 25));
		
		JLabel lblProductName_1 = new JLabel("Product Name:");
		lblProductName_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblProductCode_1 = new JLabel("Product Code:");
		lblProductCode_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		TFpppproductname = new JTextField();
		TFpppproductname.setColumns(10);
		
		TFppppcode = new JTextField();
		TFppppcode.setColumns(10);
		
		JLabel lblProductQuantity_1 = new JLabel("Product Quantity:");
		lblProductQuantity_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		TFppppquantity2 = new JTextField();
		TFppppquantity2.setColumns(10);
		
		JLabel label = new JLabel("Product Quantity:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		TFppppquantity1 = new JTextField();
		TFppppquantity1.setColumns(10);
		
		JButton btnSellByProduct = new JButton("Sell By Product Name");
		btnSellByProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellByName(TFpppproductname.getText(),Integer.parseInt((TFppppquantity1.getText())) );
			}
		});
		btnSellByProduct.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JButton btnSellCode = new JButton("Sell By Product Code");
		btnSellCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellByCode(TFppppcode.getText(), Integer.parseInt(TFppppquantity2.getText()));
			}
		});
		btnSellCode.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GroupLayout gl_sellProduct = new GroupLayout(sellProduct);
		gl_sellProduct.setHorizontalGroup(
			gl_sellProduct.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_sellProduct.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_sellProduct.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_sellProduct.createSequentialGroup()
							.addComponent(lblSellByProduct, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_sellProduct.createSequentialGroup()
							.addGroup(gl_sellProduct.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSellProduct, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSellByName, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(470, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_sellProduct.createSequentialGroup()
							.addGroup(gl_sellProduct.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_sellProduct.createSequentialGroup()
									.addGroup(gl_sellProduct.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_sellProduct.createSequentialGroup()
											.addComponent(lblProductName_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(TFpppproductname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_sellProduct.createSequentialGroup()
											.addComponent(label, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(TFppppquantity1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
									.addComponent(btnSellByProduct))
								.addGroup(gl_sellProduct.createSequentialGroup()
									.addGroup(gl_sellProduct.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_sellProduct.createSequentialGroup()
											.addComponent(lblProductQuantity_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(TFppppquantity2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_sellProduct.createSequentialGroup()
											.addComponent(lblProductCode_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(TFppppcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
									.addComponent(btnSellCode, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)))
							.addGap(84))))
		);
		gl_sellProduct.setVerticalGroup(
			gl_sellProduct.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sellProduct.createSequentialGroup()
					.addGap(43)
					.addComponent(lblSellProduct)
					.addGap(59)
					.addComponent(lblSellByName)
					.addGap(52)
					.addGroup(gl_sellProduct.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductName_1)
						.addComponent(TFpppproductname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_sellProduct.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFppppquantity1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(90)
					.addComponent(lblSellByProduct, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(gl_sellProduct.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductCode_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFppppcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_sellProduct.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductQuantity_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFppppquantity2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(100, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_sellProduct.createSequentialGroup()
					.addContainerGap(247, Short.MAX_VALUE)
					.addComponent(btnSellByProduct, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(129)
					.addComponent(btnSellCode, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(95))
		);
		sellProduct.setLayout(gl_sellProduct);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 128, 128));
		layeredPane.add(panel_3, "name_1607064972398600");
		
		JLabel lblRemoveAProduct = new JLabel("Remove a Product: ");
		lblRemoveAProduct.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		JLabel lblRemoveByName = new JLabel("Remove by Name: ");
		lblRemoveByName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblRemoveByCode = new JLabel("Remove by Code:");
		lblRemoveByCode.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		TFremovename = new JTextField();
		TFremovename.setColumns(10);
		
		TFremovecode = new JTextField();
		TFremovecode.setColumns(10);
		
		JButton btnRemoveByName = new JButton("Remove By Name");
		btnRemoveByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeByName(TFremovename.getText());
			}
		});
		btnRemoveByName.setBackground(new Color(173, 255, 47));
		btnRemoveByName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnRemoveByCode = new JButton("Remove By Code");
		btnRemoveByCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeByCode(TFremovecode.getText());
			}
		});
		btnRemoveByCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemoveByCode.setBackground(new Color(173, 255, 47));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblRemoveByCode, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(TFremovecode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblRemoveByName, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(TFremovename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRemoveAProduct))
					.addContainerGap(442, Short.MAX_VALUE))
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(460, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRemoveByCode, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemoveByName, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
					.addGap(151))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(37)
					.addComponent(lblRemoveAProduct)
					.addGap(66)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRemoveByName)
						.addComponent(TFremovename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(78)
					.addComponent(btnRemoveByName)
					.addGap(62)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRemoveByCode, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFremovecode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addComponent(btnRemoveByCode, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(197, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel searchpanel = new JPanel();
		searchpanel.setBackground(new Color(169, 169, 169));
		layeredPane.add(searchpanel, "name_32759685287500");
		
		JLabel lblSearchInventory = new JLabel("Search Inventory");
		lblSearchInventory.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		JLabel lblSearchByProduct = new JLabel("Search by Product Name :");
		lblSearchByProduct.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel lblSearchByProduct_1 = new JLabel("Search by Product Code:");
		lblSearchByProduct_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		TFsearchname = new JTextField();
		TFsearchname.setColumns(10);
		
		TFsearchcode = new JTextField();
		TFsearchcode.setColumns(10);
		
		JButton btnSearchName = new JButton("Search Name");
		btnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchName(TFsearchname.getText());
			}
		});
		btnSearchName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnsearchcode = new JButton("Search Code");
		btnsearchcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCode(TFsearchcode.getText());
			}
		});
		btnsearchcode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblProductCode = new JLabel("Product Code:");
		lblProductCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblProductDescription = new JLabel("Product Description:");
		lblProductDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblProductName1 = new JLabel("NA");
		lblProductName1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblProductcode1 = new JLabel("NA");
		lblProductcode1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblpd1 = new JLabel("NA");
		lblpd1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblProductQuantity = new JLabel("Product Quantity:");
		lblProductQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblCostPrice = new JLabel("Cost Price:");
		lblCostPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblRetailPrice = new JLabel("Retail Price:");
		lblRetailPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblQuantitySold = new JLabel("Quantity Sold:");
		lblQuantitySold.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblproductquantity1 = new JLabel("NA");
		lblproductquantity1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblcostprice1 = new JLabel("NA");
		lblcostprice1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblretailprice1 = new JLabel("NA");
		lblretailprice1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblquantitysold1 = new JLabel("NA");
		lblquantitysold1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_1 = new JLabel("---------------------------------------------------"+
		"-------------------------------------------------------------");
		label_1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		
		JLabel lblSearchInventoryBy = new JLabel("Search inventory by exact product or code");
		lblSearchInventoryBy.setFont(new Font("Tahoma", Font.ITALIC, 15));
		GroupLayout gl_searchpanel = new GroupLayout(searchpanel);
		gl_searchpanel.setHorizontalGroup(
			gl_searchpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchpanel.createSequentialGroup()
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_searchpanel.createSequentialGroup()
									.addComponent(lblSearchInventory, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblSearchInventoryBy, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_searchpanel.createSequentialGroup()
									.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_searchpanel.createSequentialGroup()
											.addComponent(lblSearchByProduct, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(TFsearchname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_searchpanel.createSequentialGroup()
											.addComponent(lblSearchByProduct_1, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(TFsearchcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(65)
									.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnsearchcode, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSearchName, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_searchpanel.createSequentialGroup()
										.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_searchpanel.createSequentialGroup()
												.addComponent(lblProductCode, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblProductcode1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
											.addComponent(lblProductDescription, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_searchpanel.createSequentialGroup()
												.addComponent(lblCostPrice, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
												.addComponent(lblcostprice1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_searchpanel.createSequentialGroup()
												.addComponent(lblRetailPrice, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
												.addComponent(lblretailprice1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_searchpanel.createSequentialGroup()
												.addComponent(lblQuantitySold, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
												.addComponent(lblquantitysold1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))))
									.addGroup(gl_searchpanel.createSequentialGroup()
										.addComponent(lblProductName, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblProductName1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblProductQuantity)
										.addGap(18)
										.addComponent(lblproductquantity1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblpd1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 830, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_searchpanel.setVerticalGroup(
			gl_searchpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchpanel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchInventory)
						.addComponent(lblSearchInventoryBy))
					.addGap(42)
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchByProduct, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFsearchname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchName))
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_searchpanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSearchByProduct_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(TFsearchcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(21)
							.addComponent(btnsearchcode, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(label_1)
					.addGap(49)
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProductName)
						.addComponent(lblProductName1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProductQuantity, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblproductquantity1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProductcode1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProductCode, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_searchpanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCostPrice, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblcostprice1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(40)
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProductDescription, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRetailPrice, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblretailprice1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblquantitysold1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantitySold, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(18)
							.addComponent(lblpd1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		searchpanel.setLayout(gl_searchpanel);
		
		JPanel containsSearch = new JPanel();
		containsSearch.setBackground(new Color(255, 228, 181));
		layeredPane.add(containsSearch, "name_149691588878700");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblContainsSearchInventory = new JLabel("Contains Search Inventory");
		lblContainsSearchInventory.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblSearchName = new JLabel("Search Names:");
		lblSearchName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblSearchCodes = new JLabel("Search Codes:");
		lblSearchCodes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblSearchQuantitys = new JLabel("Search Quantities:");
		lblSearchQuantitys.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblSearchDescriptions = new JLabel("Search Descriptions:");
		lblSearchDescriptions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblPriceRetail = new JLabel("Search Retail Price:");
		lblPriceRetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblPriceCost = new JLabel("Search Cost Price:");
		lblPriceCost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblQuantitySold_1 = new JLabel("Search Quantity Sold:");
		lblQuantitySold_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		TFSN = new JTextField();
		TFSN.setColumns(10);
		
		TFSC = new JTextField();
		TFSC.setColumns(10);
		
		TFSQ = new JTextField();
		TFSQ.setColumns(10);
		
		TFPC = new JTextField();
		TFPC.setColumns(10);
		
		TFSD = new JTextField();
		TFSD.setColumns(10);
		
		TFPR = new JTextField();
		TFPR.setColumns(10);
		
		TFQS = new JTextField();
		TFQS.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Integer> ints = containsSearchName(TFSN.getText());
				displayToSearchContains(ints);
			}
		});
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Integer> ints= containsSearchCode(TFSC.getText());
				displayToSearchContains(ints);
			}
		});
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> ints= containsSearchQuantity(TFSQ.getText());
				displayToSearchContains(ints);
			}
		});
		
		JButton button_2 = new JButton("Search");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// see if contains character and display results
				ArrayList<Integer> ints= containsSearchCostPrice(TFPC.getText());
	
				displayToSearchContains(ints);
			}
		});
		
		JButton button_3 = new JButton("Search");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> ints= containsSearchDescription(TFSD.getText());
				displayToSearchContains(ints);
			}
		});
		
		JButton button_4 = new JButton("Search");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> ints= containsSearchRetailPrice(TFPR.getText());
				displayToSearchContains(ints);
			}
		});
		
		JButton button_5 = new JButton("Search");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Integer> ints= containsSearchQuantitySold(TFQS.getText());
				displayToSearchContains(ints);
			}
		});
		
		JLabel lblSearchACharacter = new JLabel("Search a character to see if it exists within a product's specific field");
		lblSearchACharacter.setFont(new Font("Tahoma", Font.ITALIC, 15));
		GroupLayout gl_containsSearch = new GroupLayout(containsSearch);
		gl_containsSearch.setHorizontalGroup(
			gl_containsSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_containsSearch.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_containsSearch.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContainsSearchInventory, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 776, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSearchACharacter, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
						.addGroup(gl_containsSearch.createSequentialGroup()
							.addGroup(gl_containsSearch.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_containsSearch.createSequentialGroup()
									.addComponent(lblPriceCost)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(TFPC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_containsSearch.createSequentialGroup()
									.addGroup(gl_containsSearch.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_containsSearch.createSequentialGroup()
											.addComponent(lblSearchQuantitys)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(TFSQ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_containsSearch.createSequentialGroup()
											.addComponent(lblSearchCodes, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(TFSC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_containsSearch.createSequentialGroup()
											.addComponent(lblSearchName, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(TFSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_containsSearch.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))))
							.addGap(38)
							.addGroup(gl_containsSearch.createParallelGroup(Alignment.LEADING)
								.addComponent(TFSD, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSearchDescriptions, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_containsSearch.createSequentialGroup()
									.addComponent(lblQuantitySold_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TFQS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_containsSearch.createSequentialGroup()
									.addComponent(lblPriceRetail, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(TFPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
							.addGap(5)))
					.addContainerGap())
		);
		gl_containsSearch.setVerticalGroup(
			gl_containsSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_containsSearch.createSequentialGroup()
					.addGap(20)
					.addComponent(lblContainsSearchInventory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSearchACharacter)
					.addGap(22)
					.addGroup(gl_containsSearch.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchName)
						.addComponent(lblSearchDescriptions, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch)
						.addComponent(TFSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TFSD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(gl_containsSearch.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchCodes, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3)
						.addComponent(button)
						.addComponent(TFSC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_containsSearch.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchQuantitys, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFSQ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1)
						.addComponent(lblPriceRetail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_4))
					.addGap(55)
					.addGroup(gl_containsSearch.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPriceCost, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFPC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2)
						.addComponent(lblQuantitySold_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(TFQS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_5))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Name", "Product Code", "Product Description", "Quantity", "Cost Price ", "Retail Price ", "Quantity Sold"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(93);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(204);
		scrollPane_1.setViewportView(table_1);
		containsSearch.setLayout(gl_containsSearch);
		
		JPanel Less_greater_panel = new JPanel();
		Less_greater_panel.setBackground(new Color(176, 224, 230));
		layeredPane.add(Less_greater_panel, "name_207998004800700");
		
		JLabel lblSearchForProducts = new JLabel("Search for products with properties that are less than ");
		lblSearchForProducts.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblOrGreaterThan = new JLabel("or greater than...");
		lblOrGreaterThan.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblProductQuantity_2 = new JLabel("Quantity:");
		lblProductQuantity_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblQuantitySold_2 = new JLabel("Quantity Sold:");
		lblQuantitySold_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblRetailPrice_1 = new JLabel("Retail Price:");
		lblRetailPrice_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblCostPrice_1 = new JLabel("Cost Price:");
		lblCostPrice_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		TFQQQQ = new JTextField();
		TFQQQQ.setColumns(10);
		
		TFQQQQSSSS = new JTextField();
		TFQQQQSSSS.setColumns(10);
		
		TFRRRRPPPP = new JTextField();
		TFRRRRPPPP.setColumns(10);
		
		TFCCCCPPPP = new JTextField();
		TFCCCCPPPP.setColumns(10);
		
		JButton btnSearchLessThan = new JButton("Search Less Than");
		btnSearchLessThan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if less than quantity display 
				ArrayList<Integer> ints = lessThanQuantity(Integer.valueOf(TFQQQQ.getText()));
				displayToLessThanMoreThan(ints);
			}
		});
		btnSearchLessThan.setBackground(new Color(240, 128, 128));
		
		JButton button_6 = new JButton("Search Less Than");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if less than quantity sold display 
				ArrayList<Integer> ints = lessThanQuantitySold((Integer.valueOf(TFQQQQSSSS.getText())));
				displayToLessThanMoreThan(ints);
			}
		});
		button_6.setBackground(new Color(240, 128, 128));
		
		JButton button_7 = new JButton("Search Less Than");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if less than retail price display 
				ArrayList<Integer> ints = lessThanRetailPrice((Integer.valueOf(TFRRRRPPPP.getText())));
				displayToLessThanMoreThan(ints);
			}
		});
		button_7.setBackground(new Color(240, 128, 128));
		
		JButton button_8 = new JButton("Search Less Than");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if less than cost price display 
				ArrayList<Integer> ints = lessThanCostPrice((Integer.valueOf(TFCCCCPPPP.getText())));
				displayToLessThanMoreThan(ints);
			}
		});
		button_8.setBackground(new Color(240, 128, 128));
		
		JButton btnSearchGreaterThan = new JButton("Search Greater Than");
		btnSearchGreaterThan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if more than quantity display 
				ArrayList<Integer> ints = moreThanQuantity(Integer.valueOf(TFQQQQ.getText()));
				displayToLessThanMoreThan(ints);
			}
		});
		btnSearchGreaterThan.setBackground(new Color(0, 255, 127));
		
		JButton button_9 = new JButton("Search Greater Than");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if greater than quantity sold display
				ArrayList<Integer> ints = moreThanQuantitySold((Integer.valueOf(TFQQQQSSSS.getText())));
				displayToLessThanMoreThan(ints);
			}
		});
		button_9.setBackground(new Color(0, 255, 127));
		
		JButton button_10 = new JButton("Search Greater Than");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if more than retail price display 
				ArrayList<Integer> ints = moreThanPriceRetail((Integer.valueOf(TFRRRRPPPP.getText())));
				displayToLessThanMoreThan(ints);
			}
		});
		button_10.setBackground(new Color(0, 255, 127));
		
		JButton button_11 = new JButton("Search Greater Than");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if more than cost price display 
				ArrayList<Integer> ints = moreThanCostPrice((Integer.valueOf(TFCCCCPPPP.getText())));
				displayToLessThanMoreThan(ints);
			}
		});
		button_11.setBackground(new Color(0, 255, 127));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_Less_greater_panel = new GroupLayout(Less_greater_panel);
		gl_Less_greater_panel.setHorizontalGroup(
			gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Less_greater_panel.createSequentialGroup()
					.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Less_greater_panel.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOrGreaterThan, GroupLayout.PREFERRED_SIZE, 759, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSearchForProducts, GroupLayout.PREFERRED_SIZE, 759, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)))
						.addGroup(gl_Less_greater_panel.createSequentialGroup()
							.addGap(54)
							.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Less_greater_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblProductQuantity_2, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TFQQQQ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_Less_greater_panel.createSequentialGroup()
									.addComponent(lblQuantitySold_2, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TFQQQQSSSS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_Less_greater_panel.createSequentialGroup()
									.addComponent(lblRetailPrice_1, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TFRRRRPPPP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_Less_greater_panel.createSequentialGroup()
									.addComponent(lblCostPrice_1, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TFCCCCPPPP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
							.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_Less_greater_panel.createSequentialGroup()
									.addComponent(button_7, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(button_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSearchLessThan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(65)
							.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(button_11, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addComponent(btnSearchGreaterThan, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addComponent(button_9, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addComponent(button_10, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
					.addGap(52))
		);
		gl_Less_greater_panel.setVerticalGroup(
			gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Less_greater_panel.createSequentialGroup()
					.addGap(38)
					.addComponent(lblSearchForProducts)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOrGreaterThan, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnSearchGreaterThan)
							.addComponent(btnSearchLessThan)
							.addComponent(TFQQQQ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblProductQuantity_2))
					.addGap(21)
					.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuantitySold_2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(TFQQQQSSSS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_9)
							.addComponent(button_6)))
					.addGap(32)
					.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRetailPrice_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(TFRRRRPPPP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_10)
							.addComponent(button_7)))
					.addGap(28)
					.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Less_greater_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(TFCCCCPPPP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_8)
							.addComponent(button_11))
						.addComponent(lblCostPrice_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Name", "Product Code", "Product Description", "Quantity", "Cost Price", "Retail Price", "Quantity Sold"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(92);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(172);
		scrollPane_2.setViewportView(table_2);
		Less_greater_panel.setLayout(gl_Less_greater_panel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(147, 112, 219));
		splitPane.setLeftComponent(panel);
		
		// set x location for divider
		splitPane.setDividerLocation(270);

	
		
		JButton btnInventoryLibrary = new JButton("Inventory Library");
		btnInventoryLibrary.setIcon(new ImageIcon(GUI.class.getResource("/Store/book.png")));
		btnInventoryLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update the table and switch
				populateTable();
				switchPanels(inventoryLibrary);
			}
		});
		
		JButton btnAddToInventory = new JButton("Add To Inventory");
		btnAddToInventory.setIcon(new ImageIcon(GUI.class.getResource("/Store/pencil.png")));
		
		// give button functionality
		btnAddToInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(editInventory);
				
			}
		});
		
		JButton btnSellProduct = new JButton("Sell Product");
		btnSellProduct.setIcon(new ImageIcon(GUI.class.getResource("/Store/bag.png")));
		btnSellProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(sellProduct);
			}
		});
		
		JButton btnRemoveFromInventory = new JButton("Remove From Inventory");
		btnRemoveFromInventory.setIcon(new ImageIcon(GUI.class.getResource("/Store/remove.png")));
		btnRemoveFromInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_3);
			}
		});
		
		JButton tbnSearch = new JButton("Search Inventory");
		tbnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(searchpanel);
			}
		});
		tbnSearch.setIcon(new ImageIcon(GUI.class.getResource("/Store/search.png")));
		
		JButton btnContainsSearch = new JButton("Search Inventory For Char");
		btnContainsSearch.setIcon(new ImageIcon(GUI.class.getResource("/Store/ssearch.png")));
		btnContainsSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(containsSearch);
				
			}
		});
		
		JButton btnSearchFor = new JButton("Search Inventory For > or <");
		btnSearchFor.setIcon(new ImageIcon(GUI.class.getResource("/Store/abc.png")));
		btnSearchFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Less_greater_panel);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInventoryLibrary, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddToInventory, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSellProduct, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchFor, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemoveFromInventory, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(tbnSearch, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnContainsSearch, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(38)
					.addComponent(btnInventoryLibrary)
					.addGap(31)
					.addComponent(btnAddToInventory)
					.addGap(34)
					.addComponent(btnSellProduct)
					.addGap(32)
					.addComponent(btnRemoveFromInventory)
					.addGap(37)
					.addComponent(tbnSearch, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnContainsSearch, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnSearchFor, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	
	}
	
	// -------------------------------------------------------------------------------------------------------
	// All functions above main method 
	// Constructor located at top of class after private attributes
	// Launch the application.
		public static void main(String[] args) {
			
			EventQueue.invokeLater(new Runnable() {
				
				public void run() {

					// try catch for error 
					try {
						// make the 'window' and set to visible by invoking this class
						// constructor runs, program launched
						GUI window = new GUI();
						window.frmInventoryManagementSystem.setVisible(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	
}
