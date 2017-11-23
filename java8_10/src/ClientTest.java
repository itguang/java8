import com.sun.org.apache.xpath.internal.SourceTree;
import completablefuture.DiscountShop;
import completablefuture.Shop;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

/**
 * 让你的代码免受阻塞之苦
 *
 * @author itguang
 * @create 2017-11-21 16:50
 **/
public class ClientTest {

    List<Shop> shops;

    @Before
    public void before() {
        shops = Arrays.asList(new Shop("淘宝"),
                new Shop("天猫"),
                new Shop("京东"),
                new Shop("亚马逊"),
                new Shop("实体店")
        );

    }

    /**
     * 采用顺序查询所有商店的方式实现的 findPrices 方法
     *
     * @param product
     * @return
     */
    public List<String> findPrice(String product) {
        List<String> list = shops.stream()
                .map(shop ->
                        String.format("%s price is %.2f RMB",
                                shop.getName(),
                                shop.getPrice(product)))

                .collect(toList());

        return list;
    }


    /**
     * 使用并行流对请求进行并行操作
     *
     * @param product
     * @return
     */
    public List<String> findPrice2(String product) {
        List<String> list = shops.parallelStream()
                .map(shop ->
                        String.format("%s price is %.2f RMB",
                                shop.getName(),
                                shop.getPrice(product)))

                .collect(toList());

        return list;
    }


    /**
     * 使用 CompletableFuture 发起异步请求
     *
     * @param product
     * @return
     */
    public List<String> findPrice3(String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f RMB",
                                shop.getName(),
                                shop.getPrice(product)))
                )
                .collect(toList());
        List<String> list = futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());


        return list;
    }


    /**
     * 使用定制的 Executor 配置 CompletableFuture
     *
     * @param product
     * @return
     */
    public List<String> findPrice4(String product) {

        //为“最优价格查询器”应用定制的执行器 Execotor
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
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

      //将执行器Execotor 作为第二个参数传递给 supplyAsync 工厂方法
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f RMB",
                                shop.getName(),
                                shop.getPrice(product)), executor)
                )
                .collect(toList());
        List<String> list = futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());


        return list;
    }









    /**
     * 采用顺序查询所有商店的方式实现的 findPrices 方法,查询每个商店里的 iphone666s
     */
    @Test
    public void test() {
        long start = System.nanoTime();
        List<String> list = findPrice("iphone666s");
        System.out.println(list);
        System.out.println("Done in " + (System.nanoTime() - start) / 1_000_000 + " ms");
    }


    /**
     * 使用并行流对请求进行并行操作
     */
    @Test
    public void test2() {
        long start = System.nanoTime();
        List<String> list = findPrice2("iphone666s");
        System.out.println(list);
        System.out.println("Done in " + (System.nanoTime() - start) / 1_000_000 + " ms");
    }


    /**
     * 使用 CompletableFuture 发起异步请求
     */
    @Test
    public void test3() {
        long start = System.nanoTime();
        List<String> list = findPrice3("iphone666s");
        System.out.println(list);
        System.out.println("Done in " + (System.nanoTime() - start) / 1_000_000 + " ms");
    }

    /**
     * 使用定制的 Executor 配置 CompletableFuture
     */
    @Test
    public void test4() {
        long start = System.nanoTime();
        List<String> list = findPrice4("iphone666s");
        System.out.println(list);
        System.out.println("Done in " + (System.nanoTime() - start) / 1_000_000 + " ms");
    }




}
