import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Transaction {
    private String type;
    private int quantity;
    private double price;
    private String asset;
    private LocalDateTime  localtime;
    private static ArrayList<Transaction> transactions = new ArrayList<>();


    public Transaction (String type,int quantity,double price,String asset){
        this.type=type;
        this.quantity=quantity;
        this.price=price;
        this.asset=asset;
    }
    public static ArrayList<Transaction> getTransactions(){
        return transactions;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public String getAsset(){
        return asset;
    }
    public void setAsset(String asset){
        this.asset=asset;
    }
    public LocalDateTime getLocalDateTime(){
        return localtime;
    }
    public  String getFormatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return localtime.format(formatter);
    }










}
