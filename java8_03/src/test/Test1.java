package test;

import org.junit.Test;
import pojo.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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


    @Test
    public void test4() {
        List<String> lowCaloricDishesName =
                //1.从 menu 获得流（菜肴列表）,建立操作流水线
                menu.parallelStream()
                        //2.选出高热量菜肴
                        .filter(d -> d.getCalories() > 300)
                        //3.输出菜肴名称
                        .map(Dish::getName)
                        //4.只选择前三个
                        .limit(3)
                        //5.将结果保存在另一个List中
                        .collect(toList());

        System.out.println(lowCaloricDishesName);
    }

    @Test
    public void test5() {

        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> stream = title.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }


    @Test
    public void test6() {

        List<String> names =
                //1.从 menu 获得流（菜肴列表）,建立操作流水线
                menu.stream()
                        //2.选出高热量菜肴
                        .filter(d -> {
                            System.out.println("filtering" + d.getName());
                            return d.getCalories() > 300;
                        })
                        //3.输出菜肴名称
                        .map(d -> {
                            System.out.println("mapping" + d.getName());
                            return d.getName();
                        })
                        //4.只选择前三个
                        .limit(3)
                        //5.将结果保存在另一个List中
                        .collect(toList());

        System.out.println(names);

    }


}
