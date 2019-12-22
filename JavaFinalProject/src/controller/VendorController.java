package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import application.Main;
import dao.Offer;
import dao.Order;
import dao.showMyOffers;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.BuyerModel;
import model.VendorModel;

public class VendorController implements Initializable, showMyOffers{
	
	private VendorModel vm;
	private BuyerModel bm;

	public VendorController() {
		vm = new VendorModel();
		bm = new BuyerModel();
	}
    
	@FXML
	private TableView<Order> tblOfOrders;
	@FXML
	private TableView<Offer> tblShowOffers;
	@FXML
	private TableView<Offer> tblOfMyOffers;
	@FXML
	private TableColumn<Order, String> orderId;
	@FXML
	private TableColumn<Order, String> orderProductId;
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
	@FXML
	private TableColumn<Offer, String> committedQty; 
	@FXML
	private TableColumn<Offer, String> balQty; 
	@FXML
	private ComboBox comboBoxProducts;
	@FXML
	private TextField minQuantity;
	@FXML
	private TextField bidPrice;
	@FXML
	private TextField askQuantity;
	@FXML
	private TextField minPrice;
	@FXML
	private TableColumn<Order, String> askPrice; 
	@FXML
	private TableColumn<Order, String> productNm; 
	//MyOffers
	
	@FXML
	private TableColumn<Offer, String> offerProductName;
	@FXML
	private TableColumn<Offer, String> offerMyQty;
	@FXML
	private TableColumn<Offer, String> offerPrice;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		orderId.setCellValueFactory(new PropertyValueFactory<Order, String>("orderId"));
		orderProductId.setCellValueFactory(new PropertyValueFactory<Order, String>("orderProductId"));
		askPrice.setCellValueFactory(new PropertyValueFactory<Order, String>("askPrice"));
		productNm.setCellValueFactory(new PropertyValueFactory<Order, String>("productNm"));

		
		offerOrderId.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerOrderId"));
		productName.setCellValueFactory(new PropertyValueFactory<Offer, String>("productName"));
	//	minOrderQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("minOrderQty"));
	//	quantity.setCellValueFactory(new PropertyValueFactory<Offer, String>("quantity"));
		//priceLimit.setCellValueFactory(new PropertyValueFactory<Offer, String>("priceLimit"));
		price.setCellValueFactory(new PropertyValueFactory<Offer, String>("price"));
		offerQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerQty"));
	//	committedQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("committedQty"));
	//	balQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("balQty"));
				
		
		//myoffers
		offerProductName.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerProductName"));
		offerMyQty.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerMyQty"));
		offerPrice.setCellValueFactory(new PropertyValueFactory<Offer, String>("offerPrice"));
				
		
	  // auto adjust width of columns depending on their content
	     tblShowOffers.setColumnResizePolicy((param) -> true);
		 Platform.runLater(() -> customResize(tblShowOffers)); 
			
		tblOfOrders.setVisible(false); 
		tblShowOffers.setVisible(false);
		comboBoxProducts.setVisible(false);
		bidPrice.setVisible(false);
		minQuantity.setVisible(false);
		tblOfMyOffers.setVisible(false);
		
	}

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
    // Method to check all offers from different vendors
	public void checkAllOffers() throws IOException {
		
		tblOfOrders.setVisible(false); 
		tblShowOffers.setVisible(false);
		comboBoxProducts.setVisible(false);
		bidPrice.setVisible(false);
		minQuantity.setVisible(false);
		tblOfMyOffers.setVisible(false);

		System.out.println("VendorController checkAllOffers");

		tblShowOffers.getItems().setAll(vm.showOffers()); // load table data from ClientModel List
		tblShowOffers.setVisible(true); // set tableview to visible if not
		

	}
	 // Method to check all offers from different vendors
	public void checkAllOrders() throws IOException {
		
		tblOfMyOffers.setVisible(false);
		tblShowOffers.setVisible(false);
		comboBoxProducts.setVisible(false);
		bidPrice.setVisible(false);
		minQuantity.setVisible(false);
		
		System.out.println("VendorController checkAllOrders");
		
		tblOfOrders.getItems().setAll(vm.getAllOrders()); // load table data from ClientModel List
		tblOfOrders.setVisible(true); // set tableview to visible if not
		
	}
	
	 // Implementing the abstract method for the interface showMyOffers.java interface
	@Override
	public void showoffers() {
		
		tblOfOrders.setVisible(false); 
		tblShowOffers.setVisible(false);
		comboBoxProducts.setVisible(false);
		bidPrice.setVisible(false);
		minQuantity.setVisible(false);
		
		tblOfMyOffers.getItems().setAll(vm.checkOffer());
		tblOfMyOffers.setVisible(true);
	}
	
	// Method to Add offers by Vendor
	public void addOffers() throws IOException {
		
		tblOfMyOffers.setVisible(false);
		tblOfOrders.setVisible(false); 
		tblShowOffers.setVisible(false);
		comboBoxProducts.setVisible(false);
		bidPrice.setVisible(false);
		minQuantity.setVisible(false);
		
		System.out.println("addOffers");
		
		ObservableList<String> products = bm.getProducts();
     
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
        minQuantity.setVisible(true);
        bidPrice.setVisible(true);
        
       
    		
	}
	
	public void submitOrder() throws IOException {
		
		System.out.println("submitOrder");
         
		String askQuantity = this.minQuantity.getText();
		String minPrice = this.bidPrice.getText();
		String product = ((String) this.comboBoxProducts.getValue());
		int prodIndex = product.lastIndexOf("-");
		String product_name = product.substring(0, prodIndex-1);
		
		
		System.out.println(askQuantity +" "+ minPrice +" "+ product_name);
		
		//bm.addOrder(askQuantity,minPrice,product_name);
		vm.addOffer(askQuantity,minPrice,product_name);
		
		 Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText("Dear Vendor");
			alert.setContentText("Offer Submitted");
			alert.showAndWait().ifPresent(rs -> {
			    if (rs == ButtonType.OK) {
			        System.out.println("Pressed OK.");
			    }
			});
		
		
	} 
	// Logout for Vendor to redirect to Login Page
	public void logout() {
		// System.exit(0);
		try {
			System.out.println("Vendor Controller");
		//	AnchorPaneBuilder root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
			System.out.println("Vendor Controller1");
			Scene scene = new Scene(root);
			System.out.println("Vendor Controller2");
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			System.out.println("Vendor Controller3");
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}	
	
	
}
