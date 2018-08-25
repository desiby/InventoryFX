package inventoryapp.View_Controller;

import inventoryapp.Main;
import inventoryapp.Model.Part;
import inventoryapp.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Add_Modify_ProductController implements Initializable {
    

    @FXML
    private Label addProductLbl;

    @FXML
    private Button searchTopPartBtn;

    @FXML
    private TextField searchTopPartTxt;

    @FXML
    private TableView<Part> topPartTbView;
    
    @FXML
    private TableColumn<Part, Integer> topPartId;

    @FXML
    private TableColumn<Part, String> topPartName;

    @FXML
    private TableColumn<Part, Integer> topInventoryLevel;

    @FXML
    private TableColumn<Part, Integer> topPrice;
    
     @FXML
    private TableView<Part> bottomPartTbView;
    
    @FXML
    private TableColumn<Part, Integer> bottomPartId;

    @FXML
    private TableColumn<Part, String> bottomPartName;

    @FXML
    private TableColumn<Part, Integer> bottomInventoryLevel;

    @FXML
    private TableColumn<Part, Integer> bottomPrice;

    @FXML
    private Button addTopPartBtn;
    
    @FXML
    private Button deleteAssociatedPartBtn;
    
    @FXML
    private Button addProductBtn;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private Button saveProductBtn;

    @FXML
    private Button cancelProductBtn;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField productIdTxt;
    
    Scene scene;
    
    private final Main productMain = new Main();
    
    private int productId = 0;
    
    private Stage stage;
    
    private boolean saveClicked = false;
    
    //variable to notify us whether an associated part's price is greater than the product price
    private boolean foundProductPriceLessThanPart = false;
    
    private Product product;
    
    
     //set the stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set product to be added
     * @param product 
     */
    public void setProduct(Product product) {
        this.product = product;
        
        productNameTxt.setText(product.getName());
        invTxt.setText(Integer.toString(product.getInStock()));
        productPriceTxt.setText(Double.toString(product.getPrice()));
        productMaxTxt.setText(Integer.toString(product.getMax()));
        productMinTxt.setText(Integer.toString(product.getMin()));
        bottomPartTbView.setItems(product.getAssociatedParts());
        
    }
    
    //default constructor
    public Add_Modify_ProductController(){
    
    }


    @FXML
    void handleAddTopPartBtn(ActionEvent event) {
       //notify the tableview that an element was selected 
       Part selectedPart = topPartTbView.getSelectionModel().getSelectedItem();
       //add selected part into associated part collection
       product.addAssociatedPart(selectedPart);
       //display it into bottom part tableview
       bottomPartTbView.setItems(product.getAssociatedParts());
    }

    @FXML
    void handleCancelProductBtn(ActionEvent event) throws IOException {

        stage.close();
        
    }

    @FXML
    void handleDeleteAssociatedPartBtn(ActionEvent event) {
        
        //if there is at least 1 associated part for product
        if (product.getAssociatedParts().size() == 1){
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Delete");
          alert.setHeaderText("Attempt to delete");
          alert.setContentText("The product must have at least ONE associated part!");
          
          alert.showAndWait();
        }else if(bottomPartTbView.getSelectionModel().getSelectedItem() != null){
          product.removeAssociatedPart(bottomPartTbView.getSelectionModel().getSelectedItem());
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Delete");
          alert.setHeaderText("Delete");
          alert.setContentText("Part Successfuly Deleted!");
          
          alert.showAndWait();
        //if there is no selection
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Associated part Selected");
            alert.setContentText("Please select a part in the table.");
            
            alert.showAndWait();
        }
    }

    @FXML
    void handleSaveProductBtn(ActionEvent event) {
        product.setProductID(productId);
        product.setName(productNameTxt.getText());
        product.setInStock(Integer.parseInt(invTxt.getText()));
        product.setPrice(Double.parseDouble(productPriceTxt.getText()));
        product.setMax(Integer.parseInt(productMaxTxt.getText()));
        product.setMin(Integer.parseInt(productMinTxt.getText()));
        
        //loop through the associated part tableview and notify if 
        //we find an associated part greater than the product price 
        //by keeping track of foundProductPriceLessThanPart
        for (Part assocPart: product.getAssociatedParts()){
            if (product.getPrice() < assocPart.getPrice()){
               foundProductPriceLessThanPart = true;
            }else{
               foundProductPriceLessThanPart = false;
            }
        }  
       
       //if inventory greater than max inventory
        if (Integer.parseInt(invTxt.getText()) > Integer.parseInt(productMaxTxt.getText())) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.initOwner(productMain.getMainStage());
              alert.setTitle("Error");
              alert.setHeaderText("Validation Error");
              alert.setContentText("Inventory must be less than Max Inv.");
            
              alert.showAndWait();
          //if max inventory < min inventory
          }else if(Integer.parseInt(productMaxTxt.getText()) < Integer.parseInt(productMinTxt.getText())){
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.initOwner(productMain.getMainStage());
              alert.setTitle("Error");
              alert.setHeaderText("Validation Error");
              alert.setContentText("Maximun inventory must be greater than Minimun inventory");
            
              alert.showAndWait();
          //if part has no name
          }else if ((productNameTxt.getText()) == null) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.initOwner(productMain.getMainStage());
              alert.setTitle("Error");
              alert.setHeaderText("Validation Error");
              alert.setContentText("Product must have a name.");
            
              alert.showAndWait();
        //if we found an assoc. part greater than product price    
          }else if(foundProductPriceLessThanPart){    
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.initOwner(productMain.getMainStage());
              alert.setTitle("Error");
              alert.setHeaderText("Validation Error");
              alert.setContentText("Product price must be greater than any associated part price.\n"
                      + "Please try again");
            
              alert.showAndWait();
          }else {
              saveClicked = true;
              stage.close();
        }
        
    }
    

   @FXML
    void handleSearchAssociatedPartBtn(ActionEvent event) {
     try{
        Part searchPart = productMain.getMainInventory().lookupPart(Integer.parseInt(searchTopPartTxt.getText()));
        //if the id is not found
        if(!productMain.getMainInventory().isFoundRecord()){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(productMain.getMainStage());
            alert.setTitle("Not Found");
            alert.setHeaderText("Not Found");
            alert.setContentText("Not Found! please try again");
            
            alert.showAndWait();
        }else{
        topPartTbView.requestFocus();
        topPartTbView.getSelectionModel().select(searchPart);
        }
     }catch(NumberFormatException e){
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(productMain.getMainStage());
            alert.setTitle("Error");
            alert.setHeaderText("Wrong data");
            alert.setContentText("Please enter an ID number in search field");
            
            alert.showAndWait();
     }
    
  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
           //stage.initModality(Modality.APPLICATION_MODAL); 
          
           //grabbing data from inventory collection
           topPartTbView.setItems(productMain.getMainInventory().getAllParts());
           
           //Initializing top tableview with data from mainscreen's part tbview
           topPartId.setCellValueFactory(new PropertyValueFactory<>("partID"));
           topPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
           topInventoryLevel.setCellValueFactory((new PropertyValueFactory<>("inStock")));
           topPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
           
           //Auto-Incrementing the id
           for(int i = 0; i < productMain.getMainInventory().getProducts().size(); i++ ){
               
                 if(productId < productMain.getMainInventory().getProducts().get(i).getProductID()){
                    productId  = productMain.getMainInventory().getProducts().get(i).getProductID();
                 }
                  
           }
           
           productId += 1;
           productIdTxt.setDisable(true);
           
          product = new Product(productId, "sample",  0.0,  0, 0, 0);
          
          //Initializing bottom tableview
           bottomPartId.setCellValueFactory(new PropertyValueFactory<>("partID"));
           bottomPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
           bottomInventoryLevel.setCellValueFactory((new PropertyValueFactory<>("inStock")));
           bottomPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    //"save" button is clicked?
    public boolean isSavedClicked() {
        return saveClicked;
    }

    

}
