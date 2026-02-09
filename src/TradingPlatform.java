
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class TradingPlatform{


    private static ArrayList<Trader>TraderList=new ArrayList<>();
    private static ArrayList<Stock>StockList=new ArrayList<>();
    private static ArrayList<CryptoCurrency> CryptoCurrencyList =new ArrayList<>();
    private static ArrayList<Transaction>TransactionList=new ArrayList<>();
    public static ArrayList<Trader> getTraderList() {
        return TraderList;
    }
    public static ArrayList<CryptoCurrency> getCryptoCurrencyList() {
        return CryptoCurrencyList;
    }
    public static ArrayList<Transaction> getTransactionList() {
        return TransactionList;
    }
    public static ArrayList<Stock> getStockList() {
        return StockList;
    }



    public static Trader addTrader(Scanner scanner){
        try {
            String name;
            do {
                System.out.println("Enter the trader's Name:");
                name = scanner.nextLine();
                if (name == null || name.trim().isEmpty()) {
                    System.out.println("the name can't be validated");
                }
                else {
                    break;
                }
            }while (true);
            System.out.println("Enter the Trader's ID:");
            String Id;

            do {
                Id= scanner.nextLine();
                if (Id == null || Id.trim().isEmpty()) {
                    System.out.println("the ID can't be validated");
                }else  {
                    break;
                }
            }while (true);

            for(Trader trader:TraderList){
                if(Id.equals(trader.getId())){
                    throw new IllegalArgumentException("A trader with the same ID already exists ");
                }
            }

            System.out.println("Enter the initial balance :");
            double initialBalance;
            do {
                try {

                    initialBalance=scanner.nextDouble();
                    scanner.nextLine();
                    if (initialBalance>0) {
                        break;
                    }else {
                        System.out.println("enter a balande greater than zero :");
                    }

                }catch (InputMismatchException e){
                    System.err .println("error enter a valid balance :");
                    scanner.nextLine();
                }
            }while (true) ;


            Trader trader= new Trader(name,Id,initialBalance);
            TraderList.add(trader);

            System.out.println("the trader added successfully ! "+trader.getName());

            return trader;
        }catch (IllegalArgumentException e){
            System.err.println("validation error :"+e.getMessage());
            return null;
        }catch (Exception e){
            System.err.println("unexpected error:"+e.getMessage());
            return null;
        }
    }


    public static void afficherTrader(){
        if(TraderList.isEmpty()){
            System.out.println("la list des traders est vide");
        }
        System.out.println("=============traders list ==============");
        for(int i=0;i<TraderList.size();i++){
            Trader trader=TraderList.get(i);
            System.out.println("trader : "+(i+1));
            System.out.println("name   : "+trader.getName());
            System.out.println("ID     : "+trader.getId());
            System.out.println("SoldeInitial : "+trader.getInitialBalance());
            System.out.println("========folio===========");
            System.out.println("stok   : " +trader.getPortfolio().getStock());
            System.out.println("BTC    :  "+trader.getPortfolio().getBTC());
            System.out.println("restsolde : "+trader.getPortfolio().getRestBalance());
            System.out.println("SouldeTotal : "+trader.getPortfolio().getTotallyBalance());
            System.out.println("________________________________________________");
        }
    }


    public static void afficherAsset(){
        if(CryptoCurrencyList.isEmpty() && StockList.isEmpty() ){
            System.out.println("the list of assets is empty !");
        }

        System.out.println("=============stock list ==============");
        for(int i = 0; i< StockList.size(); i++){
            Stock asset= StockList.get(i);
            System.out.println("Asset : "+(i+1));
            System.out.println("code  : "+ asset.getCode());
            System.out.println("nom   : "+ asset.getName());
            System.out.println("unit price   : "+ asset.getUnitPrice());
            System.out.println("quantity   : "+ asset.getQuantity());
            System.out.println("société : "+ asset.getCompanyName());
            System.out.println("________________________________________________");
        }

        System.out.println("=============Crypto currency list ===============");
        for(int i = 0; i< CryptoCurrencyList.size(); i++){
            CryptoCurrency asset1 = CryptoCurrencyList.get(i);
            System.out.println("Asset : "+(i+1));
            System.out.println("code  : "+ asset1.getCode());
            System.out.println("name   : "+ asset1.getName());
            System.out.println("unit price   : "+ asset1.getUnitPrice());
            System.out.println("name   : "+ asset1.getQuantity());
            System.out.println("blockshaine  : "+ asset1.getBlockChaine());
            System.out.println("________________________________________________");
        }

    }




    public static void achat(Scanner scanner){
        System.out.println("Available Asset");
        afficherAsset();
        System.out.println("enter the code of the asset to buy: ");
        String codesearch;
        do {
            codesearch=scanner.nextLine();
            if(scanner.hasNextLine()){
                break;
            }else {
                System.out.println("the code must not be empty ");
            }
        }while (true);

        if(codesearch.equals("BTC")){
            Trader trader= getTraderList().getFirst();
            CryptoCurrency  btc=getCryptoCurrencyList().getFirst();
            Stock stock=getStockList().getFirst();
            System.out.println("carrency : "+btc.getName());
            System.out.println("code  : "+btc.getCode());
            System.out.println("quantity   : "+btc.getQuantity());
            System.out.println("prix de l'unité: " + btc.getUnitPrice());
            System.out.println("entrer la quantity a acheter de : " + btc.getCode() );
            int Qacheter;
            do {

                Qacheter = scanner.nextInt();
                scanner.nextLine();
                if(Qacheter>0){

                    break;
                }else {
                    System.out.println("the quantity must not be empty or zero !");
                }

            }while (true);
            if(Qacheter<btc.getQuantity() ||Qacheter==btc.getQuantity() ){
                double prixTotal= btc.getUnitPrice()*Qacheter;
                if(prixTotal<=trader.getPortfolio().getRestBalance()){
                    double RestBalance=trader.getPortfolio().getRestBalance()-prixTotal;
                    trader.getPortfolio().getRestBalance();
                    int soldBtc=trader.getPortfolio().getBTC();
                    trader.getPortfolio().setBTC(soldBtc+Qacheter);
                    int QexistantBtc=btc.getQuantity();
                    btc.setQuantity(QexistantBtc-Qacheter);
                    trader.getPortfolio().setTotallyBalance((btc.getUnitPrice()*trader.getPortfolio().getBTC())+(stock.getUnitPrice()*(trader.getPortfolio().getStock()))+trader.getPortfolio().getRestBalance());
                    Transaction transaction=new Transaction(" achat ",Qacheter,btc.getUnitPrice()," BTC ");
                    getTransactionList().add(transaction);
                    System.out.println("you have successfully purchased "+ Qacheter+ " BTC  "+ " your remaining balance is: "+ trader.getPortfolio().getRestBalance());
                }else {
                    System.out.println("your balance is insufficient: ");
                }

            }else {
                System.out.println("the quantity requested is not available:");
            }
        }



        else if(codesearch.equals("GLD")){
            Trader trader= getTraderList().getFirst();
            Stock  stock=getStockList().getFirst();
            CryptoCurrency btc=getCryptoCurrencyList().getFirst();
            System.out.println("stock : "+stock.getName());
            System.out.println("code  : "+stock.getCode());
            System.out.println("quantity   : "+ stock.getQuantity());
            System.out.println("prix de l'unité  : " + stock.getUnitPrice());
            System.out.println("entrer la quantity a acheter de : " + stock.getCode() );
            int Qacheter;
            do {
                Qacheter = scanner.nextInt();
                scanner.nextLine();
                if(Qacheter>0){
                    break;
                }else {
                    System.out.println("the quantity must not be empty or zero !");
                }
            }while (true);
            if(Qacheter<stock.getQuantity() ||Qacheter==stock.getQuantity() ){
                double prixTotal= stock.getUnitPrice()*Qacheter;
                if(prixTotal<=trader.getPortfolio().getRestBalance()){
                    double restsolde=trader.getPortfolio().getRestBalance()-prixTotal;
                    trader.getPortfolio().setRestBalance(restsolde);
                    int soldStock=trader.getPortfolio().getStock();
                    trader.getPortfolio().setStock(soldStock+Qacheter);
                    int QexistantStock=stock.getQuantity();
                    stock.setQuantity(QexistantStock-Qacheter);
                    trader.getPortfolio().setTotallyBalance((btc.getUnitPrice()*trader.getPortfolio().getBTC())+(stock.getUnitPrice()*(trader.getPortfolio().getStock()))+trader.getPortfolio().getRestBalance());
                    Transaction transaction=new Transaction(" achat ",Qacheter,btc.getUnitPrice()," BTC ");
                    getTransactionList().add(transaction);
                    System.out.println("vous avez acheter avec succes  "+ Qacheter+ "  DG  "+ "votre solde restant est : "+ trader.getPortfolio().getRestBalance());
                }else {
                    System.out.println("votre solde est insufusant");
                }

            }else {
                System.out.println("la quantity demander non disponible");
            }
        }else {
            System.out.println("choix d'Asset invalide");
        }

    }









    public static void vent(Scanner scanner){
        System.out.println("Asset que vous posseder");
        System.out.println("stock : "+getTraderList().getFirst().getPortfolio().getStock() +" GD");
        System.out.println("BTC   :  "+getTraderList().getFirst().getPortfolio().getBTC() + " BTC");
        System.out.println("entrer le code de l'Asset a vendre  ");
        String codesearch;
        do {
            codesearch = scanner.nextLine();
            if (scanner.hasNextLine()) {
                break;
            }else {
                System.out.println("la code ne doit pas etre vide ");
            }

        }while (true);
        if(codesearch.equals("BTC")){
            Trader trader= getTraderList().getFirst();
            CryptoCurrency  btc=getCryptoCurrencyList().getFirst();
            Stock  stock=getStockList().getFirst();
            System.out.println("carrency : "+btc.getName());
            System.out.println("code  : "+btc.getCode());
            System.out.println("prix de l'unité: " + btc.getUnitPrice());
            System.out.println("entrer la quantity a vender de : " + btc.getCode() );
            int Qavender ;
            do {
                Qavender = scanner.nextInt();
                scanner.nextLine();
                if (Qavender>0){
                    break;
                }else {
                    System.out.println("la quantity ne peut pas etre vide ");
                }
            }while (true);
            if(Qavender <trader.getPortfolio().getBTC() || Qavender ==trader.getPortfolio().getBTC() ){
                double prixTotal= btc.getUnitPrice()* Qavender;

                double restsolde=trader.getPortfolio().getRestBalance()+prixTotal;
                trader.getPortfolio().setRestBalance(restsolde);
                int soldBtc=trader.getPortfolio().getBTC();
                trader.getPortfolio().setBTC(soldBtc - Qavender);
                int QexistantBtc=btc.getQuantity();
                btc.setQuantity(QexistantBtc + Qavender);
                trader.getPortfolio().setTotallyBalance((btc.getUnitPrice()*trader.getPortfolio().getBTC())+(stock.getUnitPrice()*(trader.getPortfolio().getStock()))+trader.getPortfolio().getRestBalance());

                Transaction transaction=new Transaction(" achat ",Qavender,btc.getUnitPrice()," BTC ");
                getTransactionList().add(transaction);
                System.out.println("vous avez vendu avec succes  "+ Qavender + " BTC  "+ " votre solde restant est : "+ trader.getPortfolio().getRestBalance());


            }else {
                System.out.println("la quantity selectionner  non disponible sur votre portfieul");
            }
        }



        else if(codesearch.equals("GD")){
            Trader trader= getTraderList().getFirst();
            Stock  stock=getStockList().getFirst();
            CryptoCurrency btc=getCryptoCurrencyList().getFirst();
            System.out.println("stock : "+stock.getName());
            System.out.println("code  : "+stock.getCode());
            System.out.println("prix de l'unité: " + stock.getUnitPrice());
            System.out.println("entrer la quantity a vender de : " + stock.getCode() );
            int Qavender;

            do {
                Qavender = scanner.nextInt();
                scanner.nextLine();
                if (Qavender>0){
                    break;
                }else {
                    System.out.println("la quantity ne peut pas etre vide ");
                }
            }while (true);
            if(Qavender <trader.getPortfolio().getStock() || Qavender ==trader.getPortfolio().getStock() ){
                double prixTotal= stock.getUnitPrice()* Qavender;

                double soldeEexistant=trader.getPortfolio().getRestBalance();
                trader.getPortfolio().setRestBalance(soldeEexistant+prixTotal);
                int soldStock=trader.getPortfolio().getStock();
                trader.getPortfolio().setStock(soldStock - Qavender);
                int QexistantStock=stock.getQuantity();
                stock.setQuantity(QexistantStock + Qavender);
                trader.getPortfolio().setTotallyBalance((btc.getUnitPrice()*trader.getPortfolio().getBTC())+(stock.getUnitPrice()*(trader.getPortfolio().getStock()))+trader.getPortfolio().getRestBalance());
                Transaction transaction=new Transaction(" achat ",Qavender,stock.getUnitPrice()," BTC ");
                getTransactionList().add(transaction);

                System.out.println("vous avez vendu avec succes  "+ Qavender + "  DG  "+ "votre solde restant est : "+ trader.getPortfolio().getRestBalance());


            }else {
                System.out.println("la quantity demander non disponible sur votre portfieul");
            }
        }else {
            System.out.println("choix d'Asset invalide");
        }

    }





    public static void afficherTransaction(){
        if (TransactionList.isEmpty()){
            System.out.println("la liste des transactions est vide");
        }

        System.out.println("============ la list des transaction=============");
        System.out.println("  type   |  Asset   | quantity |     price     | date ");
        for (int i=0;i< TransactionList.size();i++){
            Transaction trans=TransactionList.get(i);
            System.out.println(trans.getType() +"     "+trans.getAsset()+"         "+trans.getQuantity()+"           "+trans.getPrice()+"            "+trans.getFormatedDate());


        }
    }






    public static void exporterTransaction(){
        if (TransactionList.isEmpty()){
            System.out.println("la liste des transactions est vide");
            return;
        }

        String date= java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        String nomFichier ="C:\\Users\\arkka\\Downloads " + date + ".csv";

        final String SEPARATOR = ";";
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setDecimalSeparatorAlwaysShown(true);

        try (FileWriter writer = new FileWriter(nomFichier)){
            // type   |  Asset   | quantity |     price     | date
            writer.append(" type " + SEPARATOR + "Asset" + SEPARATOR +
                    "quantity"+SEPARATOR +" price " + SEPARATOR +
                    " totale price" + SEPARATOR + "date \n");

            int totalTransaction=0;
            for (Transaction transaction:TransactionList){
                String line=String.join(SEPARATOR, transaction.getType(),transaction.getAsset(),
                        String.valueOf(transaction.getQuantity()),
                        df.format(transaction.getPrice()),
                        df.format(transaction.getPrice()*transaction.getQuantity()),
                        transaction.getFormatedDate())  + "\n";

                writer.append(line);


                totalTransaction++;

            }


            writer.append("Total Transactions: " + totalTransaction + "\n");

            System.out.println("exporter transaction successful");
            System.out.println("fichier cree" + nomFichier);
            System.out.println("transaction exporter " + totalTransaction);
        }catch (Exception e) {
            System.err.println("Erreur lors de l'export: " + e.getMessage());
            e.printStackTrace();
        }
    }







    public static  void changerPriceAsset(Scanner scanner){
        CryptoCurrency BTC=getCryptoCurrencyList().getFirst();
        Stock stock=getStockList().getFirst();
        afficherAsset();
        System.out.println("entrer le code de Asset voulu pour changer le prix : ");
        String codeAsset;

        do {
            codeAsset = scanner.nextLine();
            if(scanner.hasNextLine()) {
                break;
            }else {
                System.out.println("code asset ne peut pas etre vide");
            }
        }while (true);
        if(codeAsset.equals(BTC.getCode())){
            System.out.println("code Asset :"+BTC.getCode());
            System.out.println("asset price :"+BTC.getUnitPrice());
            System.out.println("entrer la nouvelle valeur du price :");

            double NewPrice;

            do {
                NewPrice = scanner.nextDouble();
                scanner.nextLine();
                if (NewPrice>0){
                    break;
                }else {
                    System.out.println("price ne pas etre null ou negatif");
                }
            }while (true);

            if (NewPrice>0){
                BTC.setUnitPrice(NewPrice);
                System.out.println("price modifier avec succees!  nouveau BTC price : " + BTC.getUnitPrice());
            }else {
                System.out.println("pas de pix negatif ");
            }
        }

        else if(codeAsset.equals(stock.getCode())){
            System.out.println("code Asset :"+stock.getCode());
            System.out.println("asset price :"+stock.getUnitPrice());
            System.out.println("entrer la nouvelle valeur du price :");
            double NewPrice;

            do {
                NewPrice = scanner.nextDouble();
                scanner.nextLine();
                if (NewPrice>0){
                    break;
                }else {
                    System.out.println("price ne pas etre null ou negatif");
                }
            }while (true);
            if (NewPrice>0){
                stock.setUnitPrice(NewPrice);
                System.out.println("price modifier avec succees!  nouveau stock price : " + stock.getUnitPrice());
            }else {
                System.out.println("pas de pix negatif ");
            }
        }
        else {
            System.out.println("code entrer invalide");

        }




    }








}
