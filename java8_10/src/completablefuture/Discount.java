package completablefuture;


/**
 * @author itguang
 * @create 2017-11-22 17:26
 **/
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }


    /**
     * 根据一个Quote返回一个折扣信息
     * @param quote
     * @return
     */
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    /**
     * 根据价格和折扣计算折扣后的价格
     * @param price
     * @param code
     * @return
     */
    private static double apply(double price, Code code) {
        Util.delay(1000);//模拟Discount服务的响应延迟
        return Util.format(price * (100 - code.percentage) / 100);
    }

}
