package inventoryapp.View_Controller;

import inventoryapp.Main;
import inventoryapp.Model.Part;
import inventoryapp.Model.InHousePart;
import inventoryapp.Model.Inventory;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {

    Stage stage = new Stage();
    Scene scene;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> part_id;

    @FXML
    private TableColumn<Part, String> part_name;

    @FXML
    private TableColumn<Part, Integer> inventory_level;

    @FXML
    private TableColumn<Part, Double> part_price;

    @FXML
    private TableView<Product> productTableView;
    
    @FXML
    private TableColumn<Product, Integer> product_id;

    @FXML
    private TableColumn<Product, String> product_name;
    
    @FXML
    private TableColumn<Product, String> product_inventory_level;

    @FXML
    private TableColumn<Product, Double> product_price;

    @FXML
    private Button searchPartBtn;
    
    @FXML
    private TextField searchPartTxt;

    @FXML
    private Button searchProductBtn;
    
    @FXML
    private TextField searchProductTxt;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button modifyPartBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private Button addProductPartBtn;

    @FXML
    private Button modifyProductBtn;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private Button exitBtn;
    
    //get a reference for the main class
    private Main myMain = new Main();
    
    Inventory myInventory = new Inventory();
    
    //create default constructor
    public MainScreenController(){
    }

    /**
     * Is called by the main application to give a reference back to itself
     * @param myMain 
     */
    public void setMyMain(Main myMain) {
        this.myMain = myMain;
        partTableView.setItems(myMain.getMainInventory().getAllParts());
    }
    
    /**
     * ADD PART handler
     * @param event
     * @throws Exception 
     */
    @FXML
    void handleAddPartBtn(ActionEvent event) throws Exception {

         InHousePart inHousePart = new InHousePart();
         boolean addButtonClicked = myMain.showAddModifyPartScreen(inHousePart);
         if (addButtonClicked){
            myMain.getMainInventory().getAllParts().add(inHousePart);
         }
    }
    
    /**
     * ADD PRODUCT handler
     * @param event
     * @throws IOException 
     */

    @FXML
    void handleAddProductBtn(ActionEvent event) throws IOException {
          
        Product product = new Product();
        Boolean addProductButtonClicked = myMain.showAddModifyProductScreen(product);
        if (addProductButtonClicked){
           myMain.getMainInventory().getProducts().add(product);
        }

    }
    
    /**
     * DELETE PART handler
     * @param event 
     */

    @FXML
    void handleDeletePartBtn(ActionEvent event) {

        InHousePart selectedInHousePart = (InHousePart) partTableView.getSelectionModel().getSelectedItem();
        
        if(selectedInHousePart != null){
            myMain.getMainInventory().deletePart(selectedInHousePart);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete");
            alert.setContentText("Part Successfuly Deleted!");

            alert.showAndWait();
        }else{
          // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(myMain.getMainStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * DELETE PRODUCT handler
     * @param event 
     */
    @FXML
    void handleDeleteProductBtn(ActionEvent event) {

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null){
           myMain.getMainInventory().removeProduct(selectedProduct);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete product");
            alert.setContentText("Product Successfuly Deleted!");

            alert.showAndWait();
        }else{
          // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a Product in the table.");
            
            alert.showAndWait();
        }
        
    }

    /**
     * EXIT button handler
     * @param event 
     */
    @FXML
    void handleExitBtn(ActionEvent event) {

        System.exit(0);
    }

    /**
     * MODIFY PART handler
     * @param event
     * @throws IOException 
     */
    
    @FXML
    void handleModifyPartBtn(ActionEvent event) throws IOException {
        //notify the tableview that an element was selected
        InHousePart selectedInHousePart = (InHousePart) partTableView.getSelectionModel().getSelectedItem();
        if (selectedInHousePart != null) {
            boolean saveClicked = myMain.showAddModifyPartScreen(selectedInHousePart);
            if (saveClicked) {
               myMain.getMainInventory().updatePart(selectedInHousePart);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(myMain.getMainStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
        
    /**
     * MODIFY PRODUCT handler
     * @param event 
     */

    @FXML
    void handleModifyProductBtn(ActionEvent event) throws IOException {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null){
           boolean saveClicked = myMain.showAddModifyProductScreen(selectedProduct);
             if(saveClicked){
                 myMain.getMainInventory().updateProduct(selectedProduct);
             }
        }else{
          // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(myMain.getMainStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * SEARCH PART handler
     * @param event 
     */

    @FXML
    void handleSearchPartBtn(ActionEvent event) {
       try{
        Part searchPart = myMain.getMainInventory().lookupPart(Integer.parseInt(searchPartTxt.getText()));
           if(!myMain.getMainInventory().isFoundRecord()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(myMain.getMainStage());
            alert.setTitle("Not Found");
            alert.setHeaderText("Not Found");
            alert.setContentText("Not Found! please try again");
            
            alert.showAndWait();
           }else{
              partTableView.requestFocus();
              partTableView.getSelectionModel().select(searchPart);
           }
       }catch(NumberFormatException e){
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(myMain.getMainStage());
            alert.setTitle("Error");
            alert.setHeaderText("Wrong data");
            alert.setContentText("Please enter an ID number in search field");
            
            alert.showAndWait();
       }
       
    }
    
    /**
     * SEARCH PRODUCT handler
     * @param event 
     */

    @FXML
    void handleSearchProductBtn(ActionEvent event) {
       
     try{   
       Product searchProduct = myMain.getMainInventory().lookupProduct(Integer.parseInt(searchProductTxt.getText()));
       if(!myMain.getMainInventory().isFoundRecord()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(myMain.getMainStage());
            alert.setTitle("Not Found");
            alert.setHeaderText("Not Found");
            alert.setContentText("Not Found! please try again");
            
            alert.showAndWait();
           }else{
            productTableView.requestFocus();
            productTableView.getSelectionModel().select(searchProduct);
           }
    }catch(NumberFormatException e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(myMain.getMainStage());
            alert.setTitle("Error");
            alert.setHeaderText("Wrong data");
            alert.setContentText("Please enter an ID number in search field");
            
            alert.showAndWait();
    }
     
  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
           stage.initModality(Modality.APPLICATION_MODAL); 
           
           partTableView.setItems(myMain.getMainInventory().getAllParts());
           productTableView.setItems(myMain.getMainInventory().getProducts());
           
           part_id.setCellValueFactory(new PropertyValueFactory<>("partID"));
           part_name.setCellValueFactory(new PropertyValueFactory<>("name"));
           inventory_level.setCellValueFactory((new PropertyValueFactory<>("inStock")));
           part_price.setCellValueFactory(new PropertyValueFactory<>("price"));
           
           product_id.setCellValueFactory(new PropertyValueFactory<>("productID"));
           product_name.setCellValueFactory(new PropertyValueFactory<>("name"));
           product_inventory_level.setCellValueFactory((new PropertyValueFactory<>("inStock")));
           product_price.setCellValueFactory(new PropertyValueFactory<>("price"));
           
           
    }

}
