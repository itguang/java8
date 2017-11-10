import chapter_o2.AppleUtil;
import pojo.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itguang
 * @create 2017-11-10 16:28
 **/
public class Test {

    public static void main(String[] agrs) {
        System.out.println("hello java8");
        List<Apple> result = AppleUtil.filterGreenApples(initInventory());

        List<Apple> result2 = AppleUtil.filterApplesByColor(initInventory(),"red");

        System.out.println(result2);

    }






    /**
     * 初始化苹果仓库
     * @return
     */
    public static List<Apple> initInventory() {
        ArrayList<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(100, "green"));
        inventory.add(new Apple(200, "red"));
        inventory.add(new Apple(300, "green"));
        inventory.add(new Apple(400, "red"));
        inventory.add(new Apple(500, "green"));

        return inventory;

    }


}
