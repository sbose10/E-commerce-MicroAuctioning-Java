package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import dao.DBConnect;
import dao.Order;

public class UserModel extends DBConnect{
	
	public void register(String username, String password, String userType)
	{
		String query= null;
		int buyer_id = 0;
		
		if(userType.equals("B"))
		{
			
		    try {			
			String sqlbuyerId= "select max(buyer_id) as maxid from 510fp.sb_users";
			PreparedStatement stmt1;
			stmt1 = connection.prepareStatement(sqlbuyerId);
			ResultSet rs1;
			rs1 = stmt1.executeQuery();
						
			while(rs1.next())
			{
				buyer_id = Integer.parseInt(rs1.getString("maxid"));
				buyer_id = buyer_id+1;
			}
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		 
			query = " insert into  510fp.sb_users(user_id, password, user_type, buyer_id) values('" + username + "', '" + password +  "', '" + userType+ "', '" + buyer_id+ "')";
		}
		else if(userType.equals("V"))
		{
			int vendor_id = 0;  
			 try {		
				
					
					String sqlvendorId= "select max(procurer_id) as maxid from 510fp.sb_users";
					PreparedStatement stmt2;
					stmt2 = connection.prepareStatement(sqlvendorId);
					ResultSet rs2;
					rs2 = stmt2.executeQuery();
								
					while(rs2.next())
					{
						vendor_id = Integer.parseInt(rs2.getString("maxid"));
						vendor_id = vendor_id+1;
					}
				    } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  query = " insert into 510fp.sb_users(user_id, password, user_type, procurer_id ) values('" + username + "', '" + password +  "', '" + userType+ "', '" + vendor_id + "')";
		}
		else if(userType.equals("A"))
		{
			 System.out.println("In A");
			  query = " insert into  510fp.sb_users(user_id, user_type, password) values('" + username + "', '" + userType +  "', '" + password+ "')";
		}
		else
		{
			 //query = " insert into  microDB.users(user_id, user_type, password) values('" + username + "', '" + password +  "', '" + userType+ "')";
			 System.out.println("Please enter user_type");
		}
			  
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
           	
        //   ResultSet rs = stmt.execute();
     		stmt.executeUpdate(query);
	    
           
         }		
	catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
