
public class Trader extends Person {
    private double initialBalance;
    private Portfolio portfolio;

    public Trader (String name,String id ,double initialBalance){
        super(name,id);
        this.initialBalance= initialBalance;
        this.portfolio=new Portfolio(0,0,initialBalance,initialBalance);
    }
    public Trader (String name,String id ,double initialBalance,Portfolio portfolio){
        super(name,id);
        this.initialBalance= initialBalance;
        this.portfolio=portfolio;
    }
    public double getInitialBalance(){
        return initialBalance;
    }
    public void setInitialBalance(double initialBalance){
        this.initialBalance=initialBalance;
    }
    public Portfolio getPortfolio(){
        return portfolio;
    }
    public void setPortfolio(Portfolio portfolio){
        this.portfolio=portfolio;
    }

}

