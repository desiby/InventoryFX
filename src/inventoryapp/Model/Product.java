package inventoryapp.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int productID;

    private String name;

    private double price;

    private int inStock;

    private int min;

    private int max;

    public Product(){}

    public Product( 
            int productID, String name, double price, int inStock, int min, int max) { 
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    
    
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    
   
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getInStock() {
        return inStock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }

    public void addAssociatedPart(Part part){
          associatedParts.add(part);
    }

    public boolean removeAssociatedPart(Part part){
          associatedParts.remove(part);
          return  true;
    }

    public Part lookupAssociatedPart(int partId){
        return null;
    }
    
}

