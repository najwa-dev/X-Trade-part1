import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CryptoCurrency BTC=new CryptoCurrency("bitcoin","transparent",100.0,10);
        Stock stock=new Stock("nike","GLD","golden",400.0,10);
        TradingPlatform.getCryptoCurrencyList().add(BTC);
        TradingPlatform.getStockList().add(stock);

        String choix;
        do{

            System.out.println(  "enter votre choix  \n 1:ajouter trader \n 2:afficher les trader \n 3:afficher Assets " +
                    " \n 4:acheter asset \n 5:vender Asset  \n 6:afficher les transaction  " +" \n 7:changer Asset price " +
                    "\n E:exporter les transaction en csv ");



            choix = scanner.nextLine();
            if (choix.equals("1")) {
                TradingPlatform.addTrader(scanner);
            } else if (choix.equals("2")) {
                TradingPlatform.afficherTrader();
            } else if (choix.equals("3")) {
                TradingPlatform.afficherAsset();
            } else if (choix.equals("4")) {
                TradingPlatform.achat(scanner);
            } else if (choix.equals("5")) {
                TradingPlatform.vent(scanner);
            } else if (choix.equals("6")) {
                TradingPlatform.afficherTransaction();
            } else if (choix.equals("7")) {
                TradingPlatform.changerPriceAsset(scanner);
            } else if (choix.equals("E")) {
                TradingPlatform.exporterTransaction();
            } else {
                System.out.println("choix invalide");
            }


            try {
                Trader trader=TradingPlatform.getTraderList().getFirst();
                trader.getPortfolio().setTotallyBalance((BTC.getUnitPrice()*trader.getPortfolio().getBTC())+(stock.getUnitPrice()*(trader.getPortfolio().getStock()))+trader.getPortfolio().getRestBalance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }while (!choix.equalsIgnoreCase("Q"));



    }




}
