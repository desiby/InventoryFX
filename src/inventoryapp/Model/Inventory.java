package inventoryapp.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

   private static  ObservableList<Product> products = FXCollections.observableArrayList();
   private static  ObservableList<Part> allParts = FXCollections.observableArrayList();

   private boolean foundRecord = false;

   /**
    * Reference of foundRecord which is a Boolean variable to notify or keep track 
    * of whether we found an item in the above ObservableLists
    * @return 
    */
    public boolean isFoundRecord() {
        return foundRecord;
    }
    
    /**
    * Getting all Product
    * @return products 
    */
   public ObservableList<Product> getProducts() {
        return products;
    }

   /**
    * ADDING a product
    * @param product on productTableView
    */
   public void addProduct(Product product){
       products.add(product);
   }

   /**
    * REMOVING a product
    * @param product class
    * @return Boolean
    */
   public boolean removeProduct(Product product){
       return products.remove(product);
   }

    /**
     * RETRIEVE a specific Product
     * @param productID number
     * @return Product
     */
   public Product lookupProduct(int productID){
       
       for (Product product: products){
           //if we find the id, notify in foundRecord
           if(productID == product.getProductID()){
              foundRecord = true; 
              return product;
           }else{
           //if we don't find anything notify in foundRecord    
           foundRecord = false;
           }
       }
       
       return null;
   }

   /**
     * UPDATE a Product
     * @param updatedProduct
     */
   public void updateProduct(Product updatedProduct){
         products.set(products.indexOf(updatedProduct), updatedProduct );
   }

   /**
     * Display all parts
     * @return ObservableList<Part>
     */
   public ObservableList<Part> getAllParts(){

      return allParts;
   }

   /**
     * ADD a part
     * @param part on partTableView
     */
   public void addPart(Part part){

      allParts.add(part);
   }


   /**
     *DELETE a part
     * @param part from partTableView
     * @return Boolean
     */
   public boolean deletePart(Part part){
       return allParts.remove(part);
   }

    /**
     * RETRIEVE a part
     * @param partID number
     * @return Part
     */
   public Part lookupPart(int partID){
       
       for(Part inHousePart : allParts){
          if(partID == inHousePart.getPartID()){
             foundRecord = true; 
             return inHousePart;       
          }else{
           foundRecord = false;
          }
       
       }
       return null;
   }

   /**
     *UPDATE a part
     * @param updatedPart
     */
   public void updatePart(Part updatedPart){
       allParts.set(allParts.indexOf(updatedPart), updatedPart);
   }
}