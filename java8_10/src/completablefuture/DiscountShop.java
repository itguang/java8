package completablefuture;

import java.util.Random;

/**
 * @author itguang
 * @create 2017-11-23 8:17
 **/
public class DiscountShop {
    /**
     * 商店名称
     */
    private  String name;
    private Random random = new Random();


    public DiscountShop(String name) {
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
     * @return 返回  Shop-Name:price:DiscountCode 的格式字符串
     */
    public String getPrice(String product) {

     double price = calculatePrice(product);
     //随机得到一个折扣码
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",name,price,code);
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
