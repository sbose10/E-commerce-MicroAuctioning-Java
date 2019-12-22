package controller;

import application.Main;

//import controllers.Boolean;
//import controllers.String;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.LoginModel;


public class LoginController {

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Label lblError;

	private LoginModel model;

	public LoginController() {
		model = new LoginModel();
	}

	//Method to Login 
	public void login() {

		lblError.setText("");
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();

		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setText("Password Cannot be empty or spaces");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setText("User name / Password Cannot be empty or spaces");
			return;
		}

		// authentication check
		checkCredentials(username, password);

	}
	
	public void logout() {
		// System.exit(0);
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

// Checking type of user to show the corresponding screens

	public void checkCredentials(String username, String password) {
		
		Boolean isValid = model.getCredentials(username, password);
		System.out.println("In checkcreds");
		if (!isValid) {
			lblError.setText("Incorrect credentials!");
			System.out.println("Incorrect creds");
			return;
		}
		try {
		
			AnchorPane root;
			System.out.println("checking model");
		//	if (model.isAdmin() && isValid) {
			if (isValid) {
				// If user is admin, inflate admin view
				String role = model.getRole(username, password);
				
				if(role.equalsIgnoreCase("B"))
				{
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Buyer.fxml"));
				Main.stage.setTitle("Buyer View");
				
					System.out.println("Valid and Buyer");
				}
				else if(role.equalsIgnoreCase("V"))
				{
					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Vendor.fxml"));
					Main.stage.setTitle("Vendor View");
					
						System.out.println("Valid and Vendor");
					}
				else if(role.equalsIgnoreCase("A"))
				{
					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Admin.fxml"));
					Main.stage.setTitle("Admin View");
					
						System.out.println("Valid and Admin");
					}
				else {
					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Buyer.fxml"));
				}
				
			} else {
				// If user is customer, inflate customer view
			
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ClientView.fxml"));
				// ***Set user ID acquired from db****
			//	int user_id = model.getId();  
			//	ClientController.setUserid(user_id);
				Main.stage.setTitle("Vendor View");
				System.out.println("Valid and not Admin");
			} 

		/* if(isValid) {
		
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			Main.stage.setTitle("View");
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
		 } */
			
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		
	


	}
	
	public void register()
	{
		try {
			System.out.println("in LoginController Register");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Register.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.setTitle("Register");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
}