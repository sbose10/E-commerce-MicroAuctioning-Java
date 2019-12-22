package dao;

import java.net.URL;
import java.util.ResourceBundle;

import dao.Product;
import dao.Vendor;
import javafx.fxml.Initializable;

public class Offer implements Initializable{
	
	/**
	 * @return the offerProductName
	 */
	public String getOfferProductName() {
		return offerProductName;
	}

	/**
	 * @param offerProductName the offerProductName to set
	 */
	public void setOfferProductName(String offerProductName) {
		this.offerProductName = offerProductName;
	}

	/**
	 * @return the offerMyQty
	 */
	public int getOfferMyQty() {
		return offerMyQty;
	}

	/**
	 * @param offerMyQty the offerMyQty to set
	 */
	public void setOfferMyQty(int offerMyQty) {
		this.offerMyQty = offerMyQty;
	}

	/**
	 * @return the offerPrice
	 */
	public double getOfferPrice() {
		return offerPrice;
	}

	/**
	 * @param offerPrice the offerPrice to set
	 */
	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	String offerProductName;
	int offerMyQty;
	double offerPrice;
	

	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public float getPriceLimit() {
		return priceLimit;
	}

	public void setPriceLimit(float priceLimit) {
		this.priceLimit = priceLimit;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getOfferQty() {
		return offerQty;
	}

	public void setOfferQty(double offerQty) {
		this.offerQty = offerQty;
	}

	public double getCommittedQty() {
		return committedQty;
	}

	public void setCommittedQty(double committedQty) {
		this.committedQty = committedQty;
	}

	public double getBalQty() {
		return balQty;
	}

	public void setBalQty(double balQty) {
		this.balQty = balQty;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getMinOrderQty() {
		return minOrderQty;
	}

	public void setMinOrderQty(String minOrderQty) {
		this.minOrderQty = minOrderQty;
	}

	int offerOrderId;
	

	public int getOfferOrderId() {
		return offerOrderId;
	}

	public void setOfferOrderId(int offerOrderId) {
		this.offerOrderId = offerOrderId;
	}

	int productId;
	double quantity;
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	int vendorId;
	double price;
    float priceLimit;
    Vendor vendor;
    Product product;
    
    String orderId;
    double offerQty;
    double committedQty;
    double balQty;
    String productName;
    String vendorName;
    String minOrderQty;

	public void getOffers()
	{
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	


}
