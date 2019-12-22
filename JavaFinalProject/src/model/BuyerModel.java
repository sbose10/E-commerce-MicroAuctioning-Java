package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnect;
import dao.Offer;
import dao.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BuyerModel extends DBConnect{
	
	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	//String productOrderId;
	//String orderId;

	String uname;
	String passwd;
	

	public List showOffers() {
		List offers = new ArrayList();
		int order_buyer_id = 0;
	    System.out.println("User is "+LoginModel.userid );
	    
		    String queryBuyer = "select buyer_id from sb_users where user_id = ?";	   
	   
	    try(PreparedStatement stmt = connection.prepareStatement(queryBuyer)) {
	    	 stmt.setString(1, LoginModel.userid);
	    	ResultSet rs = stmt.executeQuery();
	    	  while(rs.next()) { 
	    	order_buyer_id = rs.getInt("buyer_id");
	    	  }
	    }catch (SQLException e) {
        	e.printStackTrace();   
         }
		
		String query = " select b.product_name, d.procurer_Name,b.Min_Order_Qty, a.qty, a.price_limit, c.price, a.order_id, c.offer_id, c.offer_qty " + 
				"                    from sb_order a , sb_product b, sb_offer c, sb_procurer d " + 
				"                    where  a.order_product_Id =  b.product_id" + 
				"                    and    a.order_product_Id = c.offer_product_Id" + 
				"                    and    c.offer_procurer_Id = d.procurer_Id" + 
			//	"                    and    (c.offer_qty - c.committed_qty) >= a.qty\r\n" + 
			    "					 and    c.price<= a.price_limit" +
				"                    and    a.on_cart is null" + 
			//	"                    and    a.cycle_id = ?\r\n" + 
			//	"                    and    c.cycle_id = ?\r\n" + 
				"                    and    order_buyer_id = ?" + 
				"                    order by a.order_id"; 
		
		try(PreparedStatement stmt = connection.prepareStatement(query)) {
			   stmt.setInt(1, order_buyer_id);        	
	           ResultSet rs = stmt.executeQuery();
	            while(rs.next()) { 
	    	            	Offer offer = new Offer();
	                        offer.setProductName(rs.getString("product_name"));
	                        offer.setVendorName(rs.getString("procurer_Name"));
	                        offer.setMinOrderQty((rs.getString("Min_Order_Qty")));
	                        offer.setQuantity(Double.parseDouble(rs.getString("qty")));
	                        offer.setPriceLimit(Float.parseFloat(rs.getString("price_limit")));
	                        offer.setPrice(Double.parseDouble(rs.getString("price")));
	                        offer.setOrderId(rs.getString("order_id"));
	                        offer.setOfferOrderId(Integer.parseInt(rs.getString("offer_id")));
	                        offer.setOfferQty(Double.parseDouble((rs.getString("offer_qty"))));
	                      //  offer.setCommittedQty(Double.parseDouble(rs.getString("committed_qty")));
	                      //  offer.setBalQty(Double.parseDouble(rs.getString("bal_qty")));
	                        
	                offers.add(offer);
	                
	           	}
	         }catch (SQLException e) {
	        	e.printStackTrace();   
	         }
		
		return offers;
		
	}
	
	public double getBill(String selectedOffer)
	{    System.out.println("selectedOffer"+selectedOffer);
		double total = 0;
		 int offerIndex = selectedOffer.indexOf("#");
		 int offerID = Integer.parseInt(selectedOffer.substring(0, offerIndex));
		 System.out.println("sub"+offerID);
		 String query = "select (offer_qty*price) as amt "
		 		+      "from sb_offer o "
					+ "where o.offer_id= ? ";
	        try(PreparedStatement stmt = connection.prepareStatement(query)) {
	        	stmt.setInt(1, offerID);
	           ResultSet rs;
			rs = stmt.executeQuery();
			
	           while(rs.next()) { 
	        	//   System.out.println(rs.getString("db_prod_name"));
	        	   total = Double.parseDouble(rs.getString("amt"));
	        	 
	           }
	    
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 
		 
		return total;
	}
	
	
	
	
	
	public ObservableList getOffers()
	{
		ObservableList offers = FXCollections.observableArrayList();
		
			int order_buyer_id = 0;
	    System.out.println("User is "+LoginModel.userid );
	    
		    String queryBuyer = "select buyer_id from sb_users where user_id = ?";	   
	   
	    try(PreparedStatement stmt = connection.prepareStatement(queryBuyer)) {
	    	 stmt.setString(1, LoginModel.userid);
	    	ResultSet rs = stmt.executeQuery();
	    	  while(rs.next()) { 
	    	order_buyer_id = rs.getInt("buyer_id");
	    	  }
	    }catch (SQLException e) {
        	e.printStackTrace();   
         }
		
		String query = " select concat(c.offer_id,'#',c.offer_qty, ' - ', b.product_name ,'@',c.price ) as db_offer_name " + 
				"                    from sb_order a , sb_product b, sb_offer c, sb_procurer d " + 
				"                    where  a.order_product_Id =  b.product_id" + 
				"                    and    a.order_product_Id = c.offer_product_Id" + 
				"                    and    c.offer_procurer_Id = d.procurer_Id" + 
			//	"                    and    (c.offer_qty - c.committed_qty) >= a.qty\r\n" + 
			    "					 and    c.price<= a.price_limit" +
				"                    and    a.on_cart is null" + 
			//	"                    and    a.cycle_id = ?\r\n" + 
			//	"                    and    c.cycle_id = ?\r\n" + 
				"                    and    order_buyer_id = ?" + 
				"                    order by a.order_id"; 
		
	/*	String query = "select concat(offer_id,'#',offer_qty, ' - ', product_name ,'@',price ) as db_offer_name from offer o, product p "
				+ "where  o.offer_product_Id = p.product_Id; "; */
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
        	stmt.setInt(1, order_buyer_id);
           ResultSet rs;
	
			rs = stmt.executeQuery();
		
           while(rs.next()) { 
        	//   System.out.println(rs.getString("db_prod_name"));
        	   offers.add(rs.getString("db_offer_name"));
        	 
           }
    
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println("Offers"+offers.size());
		return offers;
	}
	
	public ObservableList getProducts()
	{
		System.out.println("Model.getProducts");
		ObservableList products = FXCollections.observableArrayList();
				// FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday",  "Thrusday", "Friday");
		
		String query = "select concat(product_name, ' - ', Min_Order_Qty) as db_prod_name , product_name from sb_PRODUCT";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
           	
           ResultSet rs;
	
			rs = stmt.executeQuery();
		
           while(rs.next()) { 
        	//   System.out.println(rs.getString("db_prod_name"));
        	   products.add(rs.getString("db_prod_name"));
        	 
           }
    
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println("Products"+products.size());
		return products;
	}
	
	public void addOrder(String askQuantity, String minPrice, String product_name)
	{
		
		int order_buyer_id = 0;
	    System.out.println("User is "+LoginModel.userid );
	    String queryBuyer = "select buyer_id from sb_users where user_id = ?";	   
	   
	    try(PreparedStatement stmt = connection.prepareStatement(queryBuyer)) {
	    	 stmt.setString(1, LoginModel.userid);
	    	ResultSet rs = stmt.executeQuery();
	    	  while(rs.next()) { 
	    	order_buyer_id = rs.getInt("buyer_id");
	    	  }
	    }catch (SQLException e) {
        	e.printStackTrace();   
         }
	    
	    
		try {
		int maxid = 0, productId=0;	
		String sqlMaxOrderId= "select max(order_id) as maxid from 510fp.sb_order";
		PreparedStatement stmt1;
		
			stmt1 = connection.prepareStatement(sqlMaxOrderId);
		
		ResultSet rs1;
		rs1 = stmt1.executeQuery();
		
		
		while(rs1.next())
		{
			maxid = rs1.getInt("maxid");
		}
		
		
		
		String sqlgetProduct = "select product_Id from sb_product where product_name = ?";
		PreparedStatement stmt2 = connection.prepareStatement(sqlgetProduct);
		System.out.println("product_name"+product_name);
		stmt2.setString(1, product_name);
		System.out.println("stmt2"+stmt2);
		ResultSet rs2;
		rs2 = stmt2.executeQuery();
		while(rs2.next())
		{
			productId = rs2.getInt("product_Id");
		}

		System.out.println("BuyerModel"+stmt2);
		//cycleId = 201519

		String sqlInsertOrder = "INSERT INTO 510fp.sb_order (order_Id,order_buyer_Id,order_product_Id,cycle_Id,qty,price_limit) VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt3 = connection.prepareStatement(sqlInsertOrder);
		
				
		stmt3.setInt(1, maxid+1);
		stmt3.setInt(2, order_buyer_id);
		stmt3.setInt(3, productId);
		stmt3.setInt(4, 201519);
		stmt3.setInt(5, Integer.parseInt(askQuantity));
		stmt3.setDouble(6, Double.parseDouble(minPrice));
		
		System.out.println("BuyerModel:"+stmt3);
		
		stmt3.execute();
	//	connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List myorders() {
		
		
		int order_buyer_id = 0;
	    System.out.println("User is "+LoginModel.userid );
	    String queryBuyer = "select buyer_id from sb_users where user_id = ?";	   
	   
	    try(PreparedStatement stmt = connection.prepareStatement(queryBuyer)) {
	    	 stmt.setString(1, LoginModel.userid);
	    	ResultSet rs = stmt.executeQuery();
	    	  while(rs.next()) { 
	    	order_buyer_id = rs.getInt("buyer_id");
	    	  }
	    }catch (SQLException e) {
        	e.printStackTrace();   
         }
		
		
		
		System.out.println("Model.myorders");
		List bm = new ArrayList();
		List orders = new ArrayList();
		String query = "select * from 510fp.sb_users u, 510fp.sb_order o, 510fp.sb_product p " + 
				"where u.buyer_id= o.order_buyer_id and o.order_product_id= p.product_id and o.order_buyer_id = ? " + 
				";";
		
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
   
           stmt.setInt(1, order_buyer_id);  
           System.out.println("My orders "+query );
           ResultSet rs = stmt.executeQuery();
            while(rs.next()) { 
          //  	BuyerModel b = new BuyerModel();
                    	
        //    	b.setPasswd(rs.getString("passwd"));
        //    	b.setUname(rs.getString("uname"));
            //	setId(rs.getInt("id"));
            //	setAdmin(rs.getBoolean("admin"));
            //	return true;
            	
            	Order order = new Order();
            	order.setOrderId(rs.getInt("order_Id"));
                order.setOrderProductId(rs.getInt("order_product_Id"));
                order.setProductNm(rs.getString("product_Name"));
                order.setAskPrice(Double.parseDouble(rs.getString("price_limit")));
            	System.out.println(rs.getInt("order_product_Id"));
              //  bm.add(b);
                orders.add(order);
                
           	}
         }catch (SQLException e) {
        	e.printStackTrace();   
         }
		return orders;
		
	}

}
