public abstract class Asset {
    private String code;
    private String name;
    private Double unitPrice;
    private Integer quantityDispo;

    public Asset(String code,String name,Double unitPrice,int quantity){
        this.code=code;
        this.name=name;
        this.unitPrice=unitPrice;
        this.quantityDispo=quantity;
    }
    public String getCode(){
        return code;
    }
    public String getName(){
        return name;
    }
    public Double getUnitPrice(){
        return unitPrice;
    }
    public int getQuantity(){
        return quantityDispo;
    }

    public void setCode(String code){
        this.code=code;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setUnitPrice(Double unitPrice){
        this.unitPrice=unitPrice;
    }
    public void setQuantity(Integer quantity){
        this.quantityDispo=quantity;
    }

}

