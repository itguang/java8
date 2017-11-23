package completablefuture;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

/**
 * @author itguang
 * @create 2017-11-23 8:54
 **/
public class DiscountShopTest {


    List<DiscountShop> discountShops;

    @Before
    public void before() {

        discountShops = Arrays.asList(new DiscountShop("淘宝"),
                new DiscountShop("天猫"),
                new DiscountShop("京东"),
                new DiscountShop("亚马逊"),
                new DiscountShop("实体店")
        );

    }


    /**
     * 得到折扣商店信息 Shop-Name:price:DiscountCode 的格式字符串
     */
    public List<String> findPrice() {
        List<String> list = discountShops.stream()
                .map(discountShop -> discountShop.getPrice("iphone99s"))
                .collect(toList());
        return list;
    }


    /**
     * 得到折扣商店信息(已经被解析过)
     */
    public List<String> findPrice1(String product) {
        List<String> list = discountShops.stream()
                .map(discountShop -> discountShop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());

        return list;
    }


    /**
     * 使用 CompletableFuture 实现 findPrices 方法
     */
    public List<String> findPrice2(String product) {
        //为“最优价格查询器”应用定制的执行器 Execotor
        Executor executor = Executors.newFixedThreadPool(Math.min(discountShops.size(), 100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        //使用守护线程,使用这种方式不会组织程序的关停
                        thread.setDaemon(true);
                        return thread;
                    }
                }
        );

        List<CompletableFuture<String>> futureList = discountShops.stream()
                .map(discountShop -> CompletableFuture.supplyAsync(
                        //异步方式取得商店中产品价格
                        () -> discountShop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                //使用另一个异步任务访问折扣服务
                                () -> Discount.applyDiscount(quote), executor
                        )
                ))
                .collect(toList());

        //等待流中所有future执行完毕,并提取各自的返回值.
        List<String> list = futureList.stream()
                //join想但与future中的get方法,只是不会抛出异常
                .map(CompletableFuture::join)
                .collect(toList());

        return list;
    }


    /**
     * 得到折扣商店信息 Shop-Name:price:DiscountCode 的格式字符串
     */
    @Test
    public void test() {
        long start = System.nanoTime();
        List<String> list = findPrice();
        System.out.println(list);
        System.out.println("Done in " + (System.nanoTime() - start) / 1_000_000 + " ms");

    }

    /**
     * 得到折扣商店信息(已经被解析过)
     */
    @Test
    public void test1() {
        long start = System.nanoTime();
        List<String> list = findPrice1("xiaomi 8s");
        System.out.println(list);
        System.out.println("Done in " + (System.nanoTime() - start) / 1_000_000 + " ms");

    }

    /**
     * 得到折扣商店信息(已经被解析过)
     */
    @Test
    public void test2() {
        long start = System.nanoTime();
        List<String> list = findPrice2("xiaomi 8s");
        System.out.println(list);
        System.out.println("Done in " + (System.nanoTime() - start) / 1_000_000 + " ms");

    }

}
