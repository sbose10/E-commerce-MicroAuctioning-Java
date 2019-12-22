package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnect;
import dao.Offer;
import dao.Order;

public class VendorModel extends DBConnect{
	
	public List getAllOrders() {
		
		System.out.println("VendorModel.getAllOrders");
	
		List orders = new ArrayList();
		String query = "select * from 510fp.sb_order o, 510fp.sb_product p where p.product_id= o.order_product_id;";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
           	
           ResultSet rs = stmt.executeQuery();
            while(rs.next()) { 
               	Order order = new Order();
            	order.setOrderId(rs.getInt("order_Id"));
                order.setOrderProductId(rs.getInt("order_product_Id"));
                order.setProductNm(rs.getString("product_Name"));
                order.setAskPrice(Double.parseDouble(rs.getString("price_limit")));
            	//System.out.println(rs.getInt("order_product_Id"));
              //  bm.add(b);
                orders.add(order);
                
           	}
         }catch (SQLException e) {
        	e.printStackTrace();   
         }
		return orders;
		
	}
	
	public List showOffers() {
		List offers = new ArrayList();
		int order_buyer_id = 0;
	    System.out.println("User is "+LoginModel.userid );
	    
				
		String query = " select * " + 
				"                    from  sb_product p, sb_offer o " + 
				"                    where  o.offer_product_Id =  p.product_id" + 
			//	"                    and    a.order_product_Id = c.offer_product_Id" + 
			//	"                    and    c.offer_procurer_Id = d.procurer_Id" + 
			//	"                    and    (c.offer_qty - c.committed_qty) >= a.qty\r\n" + 
			//    "					 and    c.price<= a.price_limit" +
			//	"                    and    a.on_cart is null" + 
			//	"                    and    a.cycle_id = ?\r\n" + 
			//	"                    and    c.cycle_id = ?\r\n" + 
			//	"                    and    order_buyer_id = ?" + 
				"                    order by o.offer_id"; 
		
		try(PreparedStatement stmt = connection.prepareStatement(query)) {
			 //  stmt.setInt(1, order_buyer_id);        	
	           ResultSet rs = stmt.executeQuery();
	            while(rs.next()) { 
	    	            	Offer offer = new Offer();
	                        offer.setProductName(rs.getString("product_name"));
	                       // offer.setVendorName(rs.getString("procurer_Name"));
	                       // offer.setMinOrderQty((rs.getString("Min_Order_Qty")));
	                        //offer.setQuantity(Double.parseDouble(rs.getString("qty")));
	                        //offer.setPriceLimit(Float.parseFloat(rs.getString("price_limit")));
	                        offer.setPrice(Double.parseDouble(rs.getString("price")));
	                       // offer.setOrderId(rs.getString("order_id"));
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
	
	
	public List checkOffer() {
		
		List offers = new ArrayList();
		int vendor_id = 0;
	    System.out.println("User is "+LoginModel.userid );
	    
		    String queryBuyer = "select procurer_id from sb_users where user_id = ?";	   
	   
	    try(PreparedStatement stmt = connection.prepareStatement(queryBuyer)) {
	    	 stmt.setString(1, LoginModel.userid);
	    	 
	    	 System.out.println("VendorController checkoffer "+queryBuyer);
	    	ResultSet rs = stmt.executeQuery();
	    	
	    	  while(rs.next()) { 
	    		  vendor_id = rs.getInt("procurer_id");
	    	  }
	    	  System.out.println("Vendor id is "+vendor_id);
	    }catch (SQLException e) {
        	e.printStackTrace();   
         }
		
		String query = " select p.product_name, o.price, o.offer_qty" + 
				"                    from  sb_product p, sb_offer o " + 
				"                    where  o.offer_product_Id =  p.product_id" + 
				"                    and    o.offer_procurer_id = ?" + 
				"                    order by p.product_id"; 
		
		try(PreparedStatement stmt = connection.prepareStatement(query)) {
			   stmt.setInt(1, vendor_id);        	
	           ResultSet rs = stmt.executeQuery();
	           System.out.println("VendorController stmt "+stmt);
	            while(rs.next()) { 
	    	            	Offer offer = new Offer();
	                        offer.setOfferProductName(rs.getString("product_name"));
	                     /*   offer.setVendorName(rs.getString("procurer_Name"));
	                        offer.setMinOrderQty((rs.getString("Min_Order_Qty")));
	                        offer.setQuantity(Double.parseDouble(rs.getString("qty")));
	                        offer.setPriceLimit(Float.parseFloat(rs.getString("price_limit"))); */
	                        offer.setOfferPrice(Double.parseDouble(rs.getString("price")));
	    	                offer.setOfferMyQty(Integer.parseInt((rs.getString("offer_qty"))));
	                      //  offer.setCommittedQty(Double.parseDouble(rs.getString("committed_qty")));
	                      //  offer.setBalQty(Double.parseDouble(rs.getString("bal_qty")));
	                        
	                offers.add(offer);
	                System.out.println("VendorController checkoffer "+offers.size());
	           	}
	         }catch (SQLException e) {
	        	e.printStackTrace();   
	         }
		
		return offers;
		
		
		
		
	}
	
	
	
	public void addOffer(String minQuantity, String bidPrice, String product_name)
	{
		
		
		List offers = new ArrayList();
		int vendor_id = 0;
	    System.out.println("User is "+LoginModel.userid );
	    
		    String queryBuyer = "select procurer_id from sb_users where user_id = ?";	   
	   
	    try(PreparedStatement stmt = connection.prepareStatement(queryBuyer)) {
	    	 stmt.setString(1, LoginModel.userid);
	    	ResultSet rs = stmt.executeQuery();
	    	  while(rs.next()) { 
	    		  vendor_id = rs.getInt("procurer_id");
	    	  }
	    }catch (SQLException e) {
        	e.printStackTrace();   
         }
	    
		try {
		int maxid = 0, productId=0;	
		String sqlMaxOrderId= "select max(offer_id) as maxid from 510fp.sb_offer";
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

		System.out.println("VendorModel"+stmt2);
		//cycleId = 201519

		String sqlInsertOrder = "INSERT INTO 510fp.sb_offer (offer_Id,offer_product_Id,offer_procurer_Id,cycle_Id,price,offer_qty) VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt3 = connection.prepareStatement(sqlInsertOrder);
		
				
		stmt3.setInt(1, maxid+1);
		stmt3.setInt(2, productId);
		stmt3.setInt(3, vendor_id);
		stmt3.setInt(4, 201519);
		stmt3.setInt(5, Integer.parseInt(bidPrice));
		stmt3.setDouble(6, Double.parseDouble(minQuantity));
		
		System.out.println("VendorModel:"+stmt3);
		
		stmt3.execute();
	//	connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
