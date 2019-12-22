package controller;

import application.Main;

//import controllers.Boolean;
//import controllers.String;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.AdminModel;
import model.LoginModel;


public class AdminController {
	
	@FXML
	private TextField txtDelUser;

	private AdminModel model;

	public AdminController() {
		model = new AdminModel();
	}

	//Deleting User
	public void deleteUser()
	{
		
		model.deleteUser(this.txtDelUser.getText());
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText("Hello Admin");
		alert.setContentText("User has been deleted");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
		
		
	}
	//Method to logout 
	public void logout() {
		// System.exit(0);
		try {
			System.out.println("Buyer Controller");
		//	AnchorPaneBuilder root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
}