package pojo;

/**
 * @author itguang
 * @create 2017-11-16 16:56
 **/
public class Transaction {

    /**
     * 货币种类
     */
    private final Currency currency;
    /**
     * 面值
     */
    private final double value;

    /**
     * 交易
     */
    private  Trader trader;

    public Transaction(Currency currency, double value, Trader trader) {
        this.currency = currency;
        this.value = value;
        this.trader = trader;
    }

    public Transaction(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "currency=" + currency +
                ", value=" + value +
                ", trader=" + trader +
                '}';
    }
}
