package inventoryapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import inventoryapp.Model.InHousePart;
import inventoryapp.Model.Inventory;
import inventoryapp.Model.Part;
import inventoryapp.Model.Product;
import inventoryapp.View_Controller.Add_Modify_PartController;
import inventoryapp.View_Controller.Add_Modify_ProductController;
import inventoryapp.View_Controller.MainScreenController;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author desire
 */
public class Main extends Application {
    
     
    public static Inventory  mainInventory = new Inventory();
    
    private Stage mainStage;

    //getter of Inventory Object to be injected elsewhere
    public Inventory getMainInventory() {
        return mainInventory;
    }

    //get a stage

    public Stage getMainStage() {
        return mainStage;
    }
    
    
    
    //start of App 
    @Override
    public void start(Stage stage) throws Exception {

          this.mainStage = stage;
          this.mainStage.setTitle("Inventory App");
          
        //load main screen  
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventoryapp/View_Controller/MainScreen.fxml"));
        Parent root = loader.load();
        
        //set stage and scene then show
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        
        // Give mainscreen controller access to the main class
        //so it can use inventory object
        MainScreenController controller = loader.getController();
            controller.setMyMain(this);
            
    }

    /**
     * Initialization of tableviews at start of program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Part part1 = new InHousePart(1,"screw",10.00,56, 100,5,1);
        Part part2 = new InHousePart(2,"screwdriver",15.00,10,25,2,3);
        Part part3 = new InHousePart(3,"nail",6.99,13, 40,5,1);
        Part part4 = new InHousePart(4,"paper",4.99,4,10,1,3);
        mainInventory.addPart(part1);
        mainInventory.addPart(part2);
        mainInventory.addPart(part3);
        mainInventory.addPart(part4);
        
        ObservableList<Part> associatedPart1 = FXCollections.observableArrayList();
        ObservableList<Part> associatedPart2 = FXCollections.observableArrayList();

        
        associatedPart1.add(part1);
        associatedPart1.add(part2);
        
        associatedPart2.add(part3);
        associatedPart2.add(part4);
        
        
        Product product1 = new Product( 1, "sample prod", 2.99, 40, 5, 100);
        Product product2 = new Product( 2, "another prod", 10.99, 25, 1, 90);
        
        mainInventory.addProduct(product1);
        mainInventory.addProduct(product2);
        

        
        launch(args);
        
        
    }
/**
 * Show the Add/Modify Parts screen
 * @param part
 * @return
 * @throws IOException 
 */
    public boolean showAddModifyPartScreen(Part part) throws IOException {
        //load fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventoryapp/View_Controller/Add_Modify_Part.fxml"));
        Parent root = loader.load();
        
        //set stage and scene
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        //set part into Add/Modify-part controller
        Add_Modify_PartController controller = loader.getController();
        controller.setStage(stage);
        controller.setInHousePart((InHousePart) part);
        
        stage.showAndWait();
        
        return controller.isSaveClicked();
    }
    
    /**
     * Show Add/Modify Product screen
     * @param product
     * @return
     * @throws IOException 
     */
    public boolean showAddModifyProductScreen(Product product) throws IOException{
    
        //load fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventoryapp/View_Controller/Add_Modify_Product.fxml"));
        Parent root = loader.load();
        
        //set stage and scene
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        Add_Modify_ProductController controller = loader.getController();
        controller.setStage(stage);
        controller.setProduct(product);
        
        stage.showAndWait();
        
        return controller.isSavedClicked();
    }
    
}
