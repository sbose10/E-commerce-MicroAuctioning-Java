package controller;
import java.util.ResourceBundle;

import application.Main;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import dao.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.LoginModel;
import model.UserModel;

public class RegisterController implements Initializable{

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;
	
	@FXML
	private Labeled lblError;
	
	@FXML
	private TextField txtUserType;
		
	private UserModel um;

	public RegisterController() {
		um = new UserModel();
	}

	//Method for Registration for new user
	public void register()
	{
		System.out.println("In Register Controller");
		lblError.setText("");
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();
		String userType = this.txtUserType.getText();

		System.out.println("userType"+userType);
		System.out.println("userType"+username);
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
		um.register(username, password, userType);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText("Dear User");
		alert.setContentText("You are registered");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
	}

	
	public void back() {
		// System.exit(0);
		try {
		
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			Scene scene = new Scene(root);
			
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}