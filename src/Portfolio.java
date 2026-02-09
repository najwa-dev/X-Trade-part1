public class Portfolio {
    private int stock;
    private int BTC;
    private double restBalance;
    private double totallyBalance;


    public Portfolio(int stock,int BTC,double restBalance,double totallyBalance){
        this.stock=stock;
        this.BTC=BTC;
        this.restBalance=restBalance;
        this.totallyBalance=totallyBalance;

    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public double getRestBalance() {
        return restBalance;
    }
    public void setRestBalance(double restBalance) {
        this.restBalance = restBalance;
    }
    public double getTotallyBalance() {
        return totallyBalance;
    }
    public void setTotallyBalance(double totallyBalance) {
        this.totallyBalance = totallyBalance;
    }
    public int getBTC() {
        return BTC;
    }
    public void setBTC(int BTC) {
        this.BTC = BTC;
    }









}
