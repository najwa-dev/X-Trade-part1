public class CryptoCurrency extends Asset{
    private String BlockChaine;


    public CryptoCurrency(String code,String name,Double unitPrice,int quantityDispo)
    {
        super(code,name,unitPrice,quantityDispo);
        this.BlockChaine=code;
    }
    public String getBlockChaine() {
        return BlockChaine;
    }
    public void setBlockChaine(String BlockChaine) {
        this.BlockChaine = BlockChaine;
    }
}
