public class Stock extends Asset {
    private String companyName;

    public Stock(String companyName,String code,String name,Double unitPrice,int quantity) {
        super(code,name,unitPrice,quantity);
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;

    }



}
