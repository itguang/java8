package test;

import chapter_o2.AppleUtil;
import org.junit.Test;
import pojo.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author itguang
 * @create 2017-11-11 10:23
 **/

public class Test1 {


    /**
     * 方法引用
     */
    @Test
    public void test5(){

        List<Apple> inventory1 = initInventory();
        inventory1.sort((Apple a1, Apple a2)-> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory1);

        List<Apple> inventory = initInventory();
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);

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
        inventory.add(new Apple(150, "green"));
        inventory.add(new Apple(250, "green"));
        inventory.add(new Apple(201, "green"));
        inventory.add(new Apple(350, "green"));

        return inventory;

    }

}
