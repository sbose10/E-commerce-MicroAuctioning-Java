package dao;

public class User {
	

	String login;
	String password;
	String userType;
	Buyer buyer;
	Vendor vendor;

	
	public void validateUser(String login, String password, String userType)
	{
		
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

}
