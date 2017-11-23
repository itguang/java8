package completablefuture.factory.test;

import completablefuture.factory.Shop;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author itguang
 * @create 2017-11-22 14:39
 **/
public class TestFactory {

    /**
     * 测试静态工厂方法 supplyAsync 创建的异步API
     */
    @Test
    public void testSupplyAsync(){

        Shop shop = new Shop("Best Shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("mac book pro");
        long invocationTime = (System.nanoTime()-start)/1_000_000;
        System.out.println("异步方法调用花费时间 : --- "+invocationTime+" --- msecs");


        //...其他操作
        doSomethingElse();

        //从future对象中读取价格,如果价格未知,则发生阻塞.
        try {
            Double price = futurePrice.get();
            System.out.printf(shop.getName()+" Price  is %.2f%n",price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long retrievalTime = (System.nanoTime()-start)/1_000_000;
        System.out.println("异步方法返回价格所需时间: --- "+retrievalTime+" ---msecs");

    }


    /**
     * 其它操作.耗时一秒
     */
    public static void doSomethingElse(){
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
