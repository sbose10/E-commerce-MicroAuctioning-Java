package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnect;

public class AdminModel extends DBConnect {

	public void deleteUser(String userId)
	{
		String query = "delete from sb_users where user_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
           	
           stmt.setString(1,userId);
           
			 stmt.execute();
		    
              
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
