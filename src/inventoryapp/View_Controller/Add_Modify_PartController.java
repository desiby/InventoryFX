/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryapp.View_Controller;

import inventoryapp.Main;
import inventoryapp.Model.InHousePart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author desire
 */
public class Add_Modify_PartController implements Initializable {
    
    @FXML
    private Label add_modify_partLbl;

    @FXML
    private RadioButton inHouseRadBtn;

    @FXML
    private RadioButton outsourcedRadBtn;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceCostTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private Label variableLbl;

    @FXML
    private TextField variableTxt;

    @FXML
    private Button add_modifyPart_saveBtn;

    @FXML
    private Button add_modifyPart_CancelBtn;

    @FXML
    private TextField partIdTxt;
    
    public Add_Modify_PartController(){
    }
    
    //get a reference to the main class to use Inventory obj
    private Main myMain = new Main();
    
    private int partId = 0;
    
    //define a stage
    private Stage stage;
    
    //define a part
    private InHousePart inHousePart;
    
    //boolean value
    private boolean saveClicked = false;

    //
    public void setMyMain(Main myMain) {
        this.myMain = myMain;
    }
    
    //set the stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Set the part to be added
     * @param inHousePart 
     */
    public void setInHousePart(InHousePart inHousePart){
        
        this.inHousePart = inHousePart;
        
      partNameTxt.setText(inHousePart.getName());
      invTxt.setText(Integer.toString(inHousePart.getInStock()));
      priceCostTxt.setText(Double.toString(inHousePart.getPrice()));
      maxTxt.setText(Integer.toString(inHousePart.getMax()));
      minTxt.setText(Integer.toString(inHousePart.getMin()));
    }    
    
    @FXML
    void handleCancelBtn(ActionEvent event) throws Exception {
          
                stage.close();     
    }

    @FXML
    void handleInHouseSelected(ActionEvent event) {

        variableLbl.setText("Machine ID");

    }

    @FXML
    void handleOutsourcedSelected(ActionEvent event) {

        variableLbl.setText("Company Name");
        
    }

    @FXML
    void handleSaveBtn(ActionEvent event) {
          inHousePart.setPartID(partId);
          inHousePart.setName(partNameTxt.getText());
          inHousePart.setInStock(Integer.parseInt(invTxt.getText()));
          inHousePart.setPrice(Double.parseDouble(priceCostTxt.getText()));
          inHousePart.setMax(Integer.parseInt(maxTxt.getText()));
          inHousePart.setMin(Integer.parseInt(minTxt.getText()));
          
          //if actual inventory greater than max inventory
           if (Integer.parseInt(invTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.initOwner(myMain.getMainStage());
              alert.setTitle("Error");
              alert.setHeaderText("Validation Error");
              alert.setContentText("Inventory must be less than Max Inv.");
            
              alert.showAndWait();
          //if max inventory < min inventory
          }else if(Integer.parseInt(maxTxt.getText()) < Integer.parseInt(minTxt.getText())){
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.initOwner(myMain.getMainStage());
              alert.setTitle("Error");
              alert.setHeaderText("Validation Error");
              alert.setContentText("Maximun inventory must be greater than Minimun inventory");
            
              alert.showAndWait();
          }else
          //if part has no name
          if ((partNameTxt.getText()) == null) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.initOwner(myMain.getMainStage());
              alert.setTitle("Error");
              alert.setHeaderText("Validation Error");
              alert.setContentText("Part must have a name.");
            
              alert.showAndWait();
          
          }else {
              saveClicked = true;
              stage.close();
        }
    
}
    //"save" button is clicked?
    public boolean isSaveClicked() {
       return saveClicked;
    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //setting toggle of radio buttons
        final ToggleGroup radioGroup = new ToggleGroup();
        inHouseRadBtn.setToggleGroup(radioGroup);
        inHouseRadBtn.setSelected(true);
        outsourcedRadBtn.setToggleGroup(radioGroup);
        
        //Auto-incrementing the id
        for(int i = 0; i < myMain.getMainInventory().getAllParts().size(); i++){
           
            if(partId < myMain.getMainInventory().getAllParts().get(i).getPartID()) 
               partId = myMain.getMainInventory().getAllParts().get(i).getPartID();
        }
        
        partId += 1 ;

        partIdTxt.setDisable(true);
      }

}    
    
