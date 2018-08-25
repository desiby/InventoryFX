package inventoryapp.Model;

public class InHousePart extends Part {

    private int machineID;
    
    public InHousePart(){
    
    }

    public InHousePart(int partID, String name, double price, int inStock, int max, int min, int machineID) {
        this.setPartID(partID);
        this.setName(name);
        this.setPrice(price);
        this.setInStock(inStock);
        this.setMachineID(machineID);
        this.setMax(max);
        this.setMin(min);
    }



    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}