package test;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import pojo.Dish;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author itguang
 * @create 2017-11-13 13:32
 **/

public class Test1 {

    List<Dish> menu = Dish.menu;

    /**
     * java7
     */
    @Test
    public void test1() {

        //选出低热量菜肴
        ArrayList<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);

            }
        }
        //按照卡路里进行排序
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish dish1, Dish dish2) {
                return Integer.compare(dish1.getCalories(), dish2.getCalories());
            }
        });
        //输出菜肴名称
        ArrayList<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());

        }


        System.out.println(lowCaloricDishesName);

    }

    /**
     * java8
     */
    @Test
    public void test2() {
        List<String> lowCaloricDishesName =
                menu.stream()
                        //选出低热量菜肴
                        .filter(d -> d.getCalories() < 400)
                        //按照卡路里进行排序
                        .sorted(Comparator.comparing(Dish::getCalories))
                        //输出菜肴名称
                        .map(Dish::getName)
                        .collect(toList());

        System.out.println(lowCaloricDishesName);
    }

    /**
     * java8多核架构并行执行这段代码
     */
    @Test
    public void test3() {

        List<String> lowCaloricDishesName =
                menu.parallelStream()
                        //选出低热量菜肴
                        .filter(d -> d.getCalories() < 400)
                        //按照卡路里进行排序
                        .sorted(Comparator.comparing(Dish::getCalories))
                        //输出菜肴名称
                        .map(Dish::getName)
                        .collect(toList());

        System.out.println(lowCaloricDishesName);
    }



}
