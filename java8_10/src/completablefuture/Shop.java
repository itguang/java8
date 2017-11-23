package completablefuture;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 每个商店都提供的对外访问的API
 * @author itguang
 * @create 2017-11-22 11:05
 **/
public class Shop {

    /**
     * 商店名称
     */
    private  String name;
    private Random random = new Random();


    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    /**
     * (阻塞式)通过名称查询价格
     * @param product
     * @return
     */
    public double getPrice(String product) {
           return calculatePrice(product);
    }

    /**
     * (非阻塞式)异步获取价格
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(()->{
            double price = calculatePrice(product);
            //需要长时间计算的任务结束并返回结果时,设置Future返回值
            future.complete(price);
        }).start();

        //无需等待还没结束的计算,直接返回Future对象
        return future;
    }


    /**
     * 计算价格(模拟一个产生价格的方法)
     * @param product
     * @return
     */
    private double calculatePrice(String product){
        delay();
        //数字*字符=数字(产生价格的方法)
        return random.nextDouble()*product.charAt(0)*product.charAt(1);
    }


    /**
     * 模拟耗时操作,阻塞1秒
     */
    private void delay(){
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
