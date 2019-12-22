package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;
import javafx.scene.Scene;
import application.Main;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import dao.DBConnect;
import dao.Offer;
import dao.Order;
import dao.showMyOffers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import model.BuyerModel;
import model.LoginModel;


public class BuyerController implements Initializable, showMyOffers{
	
	private BuyerModel bm;

	public BuyerController() {
		bm = new BuyerModel();
	}



	/***** TABLEVIEW intel *********************************************************************/

	@FXML
	private TableView<Order> tblAccounts;
	@FXML
	private TableView<Offer> tblShowOffers;
	@FXML
	private TableColumn<Order, String> orderId;
	@FXML
	private TableColumn<Order, String> orderProductId;
//	@FXML
//	private TableColumn<Offer, String> offerId;
	//AddToCart
	@FXML
	private ComboBox comboBoxOffers;
	//Offers
	@FXML
	private ComboBox comboBoxProducts;
	@FXML
	private TableColumn<Offer, String> productName;
	@FXML
	private TableColumn<Offer, String> minOrderQty;
	@FXML
	private TableColumn<Offer, String> quantity;
	@FXML
	private TableColumn<Offer, String> priceLimit;
	@FXML
	private TableColumn<Offer, String> price;
	@FXML
	private TableColumn<Offer, String> offerOrderId;  
	@FXML
	private TableColumn<Offer, String> offerQty; 
/*	@FXML
	private TableColumn<Offer, String> committedQty; 
	@FXML
	private TableColumn<Offer, String> balQty; */
	@FXML
	private TableColumn<Order, String> askPrice; 
	@FXML
	private TableColumn<Order, String> productNm; 
	//showTotal
	//Add Order
	@FXML
	private Button showTotal;
	
	//Add Order
	@FXML
	private Button submitOrder;
	@FXML
	private TextField askQuantity;
	@FXML
	private TextField minPrice;
	
	public void initialize(URL location, ResourceBundle resources) {
		orderId.setCellValueFactory(new PropertyValueFactory<Order, String>("orderId"));
		orderProductId.setCellValueFactory(new PropertyValueFactory<Order, String>("orderProductId"));
		askPrice.setCellValueFactory(new PropertyValueFactory<Order, String>("askPrice"));
		productNm.setCellValueFactory(new PropertyValueFactory<Order, String>("productNm"));

		// auto adjust width of columns depending on their content
		tblAccounts.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblAccounts));
		
		//offerId.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerId"));
		
		 
		submitOrder.setVisible(false);
		//showOffers
	   //  offerId.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerId"));
		offerOrderId.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerOrderId"));
		productName.setCellValueFactory(new PropertyValueFactory<Offer, String>("productName"));
		minOrderQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("minOrderQty"));
		quantity.setCellValueFactory(new PropertyValueFactory<Offer, String>("quantity"));
		priceLimit.setCellValueFactory(new PropertyValueFactory<Offer, String>("priceLimit"));
		price.setCellValueFactory(new PropertyValueFactory<Offer, String>("price"));
		offerQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerQty"));
		//	balQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("balQty"));
	//	committedQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("committedQty"));
	//	balQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("balQty"));
		
	  // auto adjust width of columns depending on their content
	     tblShowOffers.setColumnResizePolicy((param) -> true);
		 Platform.runLater(() -> customResize(tblShowOffers)); 
			
		 askQuantity.setVisible(false);
		 minPrice.setVisible(false);
		tblShowOffers.setVisible(false);
		tblAccounts.setVisible(false); // set invisible initially
		comboBoxProducts.setVisible(false);
		comboBoxOffers.setVisible(false);
		 showTotal.setVisible(false);
	}

	//Resize of the tables
    public void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }
    // Add to Cart functionality for the Buyer 
    public void addToCart() throws IOException
    {
    	comboBoxProducts.setVisible(false);
        askQuantity.setVisible(false);
        minPrice.setVisible(false);
        submitOrder.setVisible(false);
        showTotal.setVisible(false);
        tblShowOffers.setVisible(false);
        
    	ObservableList<String> offers = bm.getOffers();
    	
    	comboBoxOffers.setItems(offers);
       	comboBoxOffers.setVisible(true);
       	
          // Label to display the selected menuitem 
          Label selected = new Label("default item selected"); 
       // final  String choice;
          // Create action event 
          EventHandler<ActionEvent> event = 
                    new EventHandler<ActionEvent>() { 
              public void handle(ActionEvent e) 
              { 
             
				selected.setText(comboBoxOffers.getValue() + " selected"); 
                  System.out.println("testing"+comboBoxOffers.getValue());
                 // choice = (comboBoxOffers.getValue()).toString();
                  showTotal.setVisible(true);
              } 
          };
        
          comboBoxOffers.setOnAction(event); 

    }
    // Method to show the total of the bill
    public void showTotal() throws IOException {
    	
    	comboBoxProducts.setVisible(false);
        askQuantity.setVisible(false);
        minPrice.setVisible(false);
        submitOrder.setVisible(false);
       // showTotal.setVisible(false);
        tblShowOffers.setVisible(false);
        
    	
     Double total= 0.0;
         total =	bm.getBill( ((String) comboBoxOffers.getValue()));
          
            Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Message");
    		alert.setHeaderText("Dear Buyer , Offer Redeemed Successfully");
    		alert.setContentText("Your Bill Amount is $"+total);
    		alert.showAndWait().ifPresent(rs -> {
    		    if (rs == ButtonType.OK) {
    		        System.out.println("Pressed OK.");
    		    }
    		}); 
    	
    	
    }
    
    
    // Method for the Myorders tab
	public void myorders() throws IOException {
		
		comboBoxProducts.setVisible(false);
        askQuantity.setVisible(false);
        minPrice.setVisible(false);
        submitOrder.setVisible(false);
        comboBoxOffers.setVisible(false);
        showTotal.setVisible(false);
        
		System.out.println("Controller");
		tblAccounts.getItems().setAll(bm.myorders()); // load table data from ClientModel List
		tblAccounts.setVisible(true); // set tableview to visible if not

	}

	
	public void addOrders() throws IOException {
		tblShowOffers.setVisible(false);
		tblAccounts.setVisible(false);
		 comboBoxOffers.setValue(false);
		System.out.println("addOrders");
		
		 
		//ObservableList<String> week_days = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday",  "Thrusday", "Friday");
		ObservableList<String> products = bm.getProducts();
      //  List week_days =  ("Monday", "Tuesday", "Wednesday",  "Thrusday", "Friday" ); 
                  
                
        
        // Create a combo box 
      //   comboBoxProducts = new ComboBox(FXCollections.observableArrayList(week_days)); 
         comboBoxProducts.setItems(products);
         comboBoxProducts.setVisible(true);
        // Label to display the selected menuitem 
        Label selected = new Label("default item selected"); 
      
        // Create action event 
        EventHandler<ActionEvent> event = 
                  new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                selected.setText(comboBoxProducts.getValue() + " selected"); 
                System.out.println(comboBoxProducts.getValue());
            } 
        }; 
  
        // Set on action 
        comboBoxProducts.setOnAction(event); 
        askQuantity.setVisible(true);
        minPrice.setVisible(true);
        submitOrder.setVisible(true);
      
		
	}
	
	// Implementing the astract method for the interface named showMyOffers.java
	@Override
	public void showoffers()  {
		 comboBoxOffers.setValue(false);
		comboBoxProducts.setVisible(false);
        askQuantity.setVisible(false);
        minPrice.setVisible(false);
        submitOrder.setVisible(false);
   // 	tblShowOffers.setVisible(false);
		tblAccounts.setVisible(false);
		
		System.out.println("showOffers");
		tblShowOffers.getItems().setAll(bm.showOffers()); // load table data from ClientModel List
		tblShowOffers.setVisible(true); // set tableview to visible if not
		
		
	}
	// Method for submitting the order 
	public void submitOrder() throws IOException {
		
		tblShowOffers.setVisible(false);
		tblAccounts.setVisible(false);
		 comboBoxOffers.setValue(false);
		
		System.out.println("submitOrder");
         
		String askQuantity = this.askQuantity.getText();
		String minPrice = this.minPrice.getText();
		String product = ((String) this.comboBoxProducts.getValue());
		int prodIndex = product.lastIndexOf("-");
		String product_name = product.substring(0, prodIndex-1);
		
		
		System.out.println(askQuantity +" "+ minPrice +" "+ product_name);
		
		bm.addOrder(askQuantity,minPrice,product_name);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText("Dear Buyer");
		alert.setContentText("Order Submitted");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
		
		
	} 
	// Method to logout
	public void logout() {
		// System.exit(0);
		try {
			System.out.println("Buyer Controller");
		//	AnchorPaneBuilder root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			System.out.println("Buyer Controller1");
			Scene scene = new Scene(root);
			System.out.println("Buyer Controller2");
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			System.out.println("Buyer Controller3");
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	


}
