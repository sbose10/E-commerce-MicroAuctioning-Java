package dao;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Order implements Initializable{
	
	int orderId;
	/**
	 * @return the productNm
	 */
	public String getProductNm() {
		return productNm;
	}

	/**
	 * @param productNm the productNm to set
	 */
	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}

	/**
	 * @return the askPrice
	 */
	public Double getAskPrice() {
		return askPrice;
	}

	/**
	 * @param askPrice the askPrice to set
	 */
	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}

	String productNm;
	Double askPrice;
	
	

	int orderProductId;
	
	public int getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getCycleId() {
		return cycleId;
	}

	public void setCycleId(int cycleId) {
		this.cycleId = cycleId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getAddToCartDate() {
		return addToCartDate;
	}

	public void setAddToCartDate(Date addToCartDate) {
		this.addToCartDate = addToCartDate;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	int buyerId;
	int cycleId;
	int totalPrice;
	Date addToCartDate;
	Buyer buyer;
	
	
	public void getAllOrders()
	{
		
	}
	
	public void getOrderProductDetails()
	{
		//get order product details based on OrderId passed 
	}

	public void getOfferDetails()
	{
		//get order offer details based on OrderId passed 
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
