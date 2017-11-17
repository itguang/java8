import com.sun.istack.internal.NotNull;
import org.junit.Before;
import org.junit.Test;
import pojo.CaloricLevel;
import pojo.Currency;
import pojo.Dish;
import pojo.Trader;
import pojo.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author itguang
 * @create 2017-11-16 16:00
 **/
public class Test5 {

    List<Dish> menu = Dish.menu;
    public static List<Transaction> transactions;

    @Before
    public void before() {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");


        transactions = Arrays.asList(
                new Transaction(Currency.EUR, 1500.0, brian),
                new Transaction(Currency.USD, 2300.0, raoul),
                new Transaction(Currency.GBP, 9900.0, mario),
                new Transaction(Currency.EUR, 1100.0, alan),
                new Transaction(Currency.JPY, 7800.0, brian),
                new Transaction(Currency.CHF, 6700.0, brian),
                new Transaction(Currency.EUR, 5600.0, mario),
                new Transaction(Currency.USD, 4500.0, mario),
                new Transaction(Currency.CHF, 3400.0, brian),
                new Transaction(Currency.GBP, 3200.0, alan),
                new Transaction(Currency.USD, 4600.0, alan),
                new Transaction(Currency.JPY, 5700.0, alan),
                new Transaction(Currency.EUR, 6800.0, raoul));

    }


    @Test
    public void test1() {
        //一次打印
        System.out.println(transactions.toString());
        //Lambda循环打印
        transactions.stream()
                .forEach(System.out::println);
    }

    //用指令式风格对交易按照货币分组
    @Test
    public void test2() {
        Map<Currency, List<Transaction>> transactionsByCurrencies =
                new HashMap<>();
        List<Transaction> transactionsForCurrency = null;
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies
                        .put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
        //遍历map
        for (Currency currency : transactionsByCurrencies.keySet()) {
            List<Transaction> transactions = transactionsByCurrencies.get(currency);
            System.out.println(transactions);

        }
    }


    @Test
    public void test3() {
        Map<Currency, List<Transaction>> listMap = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));

        //遍历map
        for (Currency currency : listMap.keySet()) {

            System.out.println(listMap.get(currency));
        }
    }


    @Test
    public void test4() {
        Long aLong = menu.stream()
                .collect(Collectors.counting());
        System.out.println(aLong);


        long count = menu.stream()
                .count();

        System.out.println(count);

    }

    @Test
    public void test5() {
        Long aLong = menu.stream()
                .collect(counting());
        System.out.println(aLong);


        long count = menu.stream()
                .count();

        System.out.println(count);

    }

    //找出caid菜单里卡路里的最大值和最小值
    @Test
    public void test6() {

        //使用收集器的方法
        Optional<Dish> max = menu.stream()
                .collect(maxBy(Comparator.comparing(Dish::getCalories)));

        System.out.println(max);

        Optional<Dish> min = menu.stream()
                .collect(minBy(Comparator.comparing(Dish::getCalories)));

        System.out.println(min);

        //使用流的方法
        Optional<Dish> max1 = menu.stream()
                .max(Comparator.comparing(Dish::getCalories));
        System.out.println(max1);

        Optional<Dish> min1 = menu.stream()
                .min(Comparator.comparing(Dish::getCalories));
        System.out.println(min1);


    }

    //汇总
    @Test
    public void test7() {

        //使用收集器汇总
        Integer sum = menu.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(sum);


        //使用流汇总
        Integer sum2 = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(sum2);

    }

    //汇总
    @Test
    public void test8() {
        IntSummaryStatistics summaryStatistics = menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);
        System.out.println(summaryStatistics.getAverage());
    }


    //连接字符串 joining
    @Test
    public void test9() {

        String names = menu.stream()
                .map(Dish::getName)
                .collect(joining());
        System.out.println(names);

        String names2 = menu.stream()
                .map(Dish::getName)
                .collect(joining(", "));
        System.out.println(names2);


    }


    //使用reducing求和,以不同的方法执行同样的操作
    @Test
    public void test10() {

        //使用收集器汇总
        Integer sum = menu.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(sum);


        //使用流汇总
        Integer sum2 = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(sum2);

        //把流映射到一个 IntStream ，然后调用 sum 方法
        int sum1 = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum1);

        Integer sum3 = menu.stream()
                .collect(reducing(0,//初始值
                        Dish::getCalories,//转换函数
                        Integer::sum));//累积函数
        System.out.println(sum3);


    }


    //使用reducing求最大值,以不同的方法执行同样的操作
    @Test
    public void test11() {
        Optional<Dish> dishOptional = menu.stream()
                .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(dishOptional);
    }


    //分组操作
    @Test
    public void test12() {
        Map<Dish.Type, List<Dish>> typeListMap = menu.stream()
                .collect(groupingBy(Dish::getType));

        System.out.println(typeListMap);

        for (Dish.Type key : typeListMap.keySet()) {
            System.out.println(typeListMap.get(key));
        }

    }


    //多级分组操作
    @Test
    public void test13() {


        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> map = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                )
        );
        //遍历map
        System.out.println(map);
        for (Dish.Type key:map.keySet()){
            System.out.println(map.get(key));
            Map<CaloricLevel, List<Dish>> map2= map.get(key);
            for (CaloricLevel key2:map2.keySet()){
                System.out.println(map2.get(key2));
            }
        }


    }


    //按子组收集数据
    @Test
    public void test14(){
        Map<Dish.Type, Long> map = menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println(map);

    }


    //分区
    @Test
    public void test15(){
        Map<Boolean, List<Dish>> map = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(map);

        for (Boolean key:map.keySet()){
            System.out.println(map.get(key));
        }

    }

    //多级分区
    @Test
    public void test16(){
        Map<Boolean, Map<Dish.Type, List<Dish>>> map = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

        for (Boolean key:map.keySet()){
            System.out.println(map.get(key));
        }

    }


}
