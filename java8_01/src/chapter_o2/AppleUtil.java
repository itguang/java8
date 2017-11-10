package chapter_o2;

import pojo.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选苹果
 *
 * @author itguang
 * @create 2017-11-10 16:30
 **/
public  class AppleUtil {

    /**
     *筛选绿苹果
     * @param inventory 苹果仓库
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();

        for(Apple apple:inventory){
            if ("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;

    }

    /**
     * 把颜色参数化,应对颜色变化的需求
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory,
                                                  String color) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple: inventory){
            if ( apple.getColor().equals(color) ) {
                result.add(apple);
            }
        }
        return result;
    }


}
