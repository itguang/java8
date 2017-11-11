package test;

import chapter_o2.AppleUtil;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import pojo.Apple;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author itguang
 * @create 2017-11-11 10:23
 **/

public class Test1 {


   @Test
    public void test(){
       System.out.println("hello test");

    }

    @Test
    public  void test1(){
        List<Apple> result = AppleUtil.filter(initInventory(), (Apple apple) -> "green".equals(apple.getColor()));
        System.out.println(result);
    }

    /**
     * 对仓库的苹果按重量升序排序
     */
    @Test
    public void test2(){
        List<Apple> inventory = initInventory();
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        System.out.println(inventory);
    }

    /**
     * 用Lambda表达式对仓库的苹果按重量升序排序
     */
    @Test
    public void test3(){
        List<Apple> inventory = initInventory();
        inventory.sort((Apple o1,Apple o2)->o1.getWeight().compareTo(o2.getWeight()));
        System.out.println(inventory);

    }


    @Test
    public void test4(){
        Thread t = new Thread(() -> System.out.println("Hello world"));
        t.start();

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
