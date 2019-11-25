// Name: Mya Hussain 
// Date: 2019-11-09
// Assignment: Inventory management system
// Description: An inventory management system that stores objects in a file by 
// serializing and deserializing an array list of objects. Objects can be added 
// edited, sold, searched through and removed from the inventory using the programs 
// graphical user interface.  
// CLASS PRODUCTS 

package Store;


import java.io.Serializable;

import javax.swing.JOptionPane;

// implements Serializable to allow class objects to be serialized to store in file
public class Product implements Serializable {

	private String productCode;
	private String productName;
	private String productDescription;
	private int quantity;
	private int quantitySold = 0;
	private double priceRetail;
	private double priceCost;
	
	// constructor 
	public Product(String productCode, String productName, String productDescription,
			int quantity, double priceRetail, double priceCost ) {
		
		// use getters and setters to apply values to private attributes
		setProductCode(productCode);
		setProductName(productName);
		setProductDescription(productDescription);
		setQuantity(quantity);
		setPriceRetail(priceRetail);
		setPriceCost(priceCost);
		
		
	}
	
	// GETTERS AND SETTERS FOR ALL PRIVATE VARIABLES 
	
	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public int getQuantity () {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantitySold() {
		return quantitySold;
	}
	
	// to be used when something is sold, originally 0
	public void addQuantitySold(int quantitySold) {
		// add values to initial instead of setting 
		this.quantitySold += quantitySold;
	}
	
	public double getPriceRetail() {
		return priceRetail;
	}
	
	public void setPriceRetail(double priceRetail) {
		this.priceRetail = priceRetail;
	}
	
	
	public double getPriceCost() {
		return priceCost;
	}
	
	public void setPriceCost (double priceCost) {
		this.priceCost = priceCost;
	}
	


}
