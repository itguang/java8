package test;

import org.junit.Test;
import pojo.Trader;
import pojo.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author itguang
 * @create 2017-11-16 10:05
 **/
public class TradeTest {


    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


    //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
    @Test
    public  void test1(){

        List<Transaction> list = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(list);

    }

    //(2) 交易员都在哪些不同的城市工作过？
    @Test
    public void test2(){

        List<String> list = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());
        System.out.println(list);

        List<String> list1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(list1);


    }

    //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
    @Test
    public void test3(){
        List<Trader> list = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
        System.out.println(list);

        List<Trader> list1 = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());

        System.out.println(list1);


    }

    //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
    @Test
    public void test4(){
        List<String> list = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted((s1, s2) -> s1.compareTo(s2))
                //.distinct()
                .collect(toList());
        System.out.println(list);


        List<String> list1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(toList());
        System.out.println(list1);


    }

    //(5) 有没有交易员是在米兰工作的？
    @Test
    public void test5(){
        boolean b = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(" 有没有交易员是在米兰工作的？="+b);

        boolean b1 = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(" 有没有交易员是在米兰工作的？="+b1);


    }

    //(6) 打印生活在剑桥的交易员的所有交易额。
    @Test
    public void test6(){
        List<Integer> list = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(toList());
        System.out.println(list);
    }

    // (7) 所有交易中，最高的交易额是多少？
    @Test
    public void test7(){
        Optional<Integer> reduce = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(reduce);

    }

    //(8) 找到交易额最小的交易。
    @Test
    public void test8(){
        Optional<Integer> reduce = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println(reduce);
    }

    //(9) 计算交易总额
    @Test
    public void test9(){
        Integer reduce = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }




}
