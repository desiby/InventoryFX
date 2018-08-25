package inventoryapp.Model;

public class OutsourcedPart extends Part {

    private String companyName;

    public OutsourcedPart(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
