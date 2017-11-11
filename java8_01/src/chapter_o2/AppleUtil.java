package chapter_o2;

import chapter_o2.impl.ApplePredicate;
import chapter_o2.impl.Predicate;
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
//    public static List<Apple> filterApplesByColor(List<Apple> inventory,
//                                                  String color) {
//        List<Apple> result = new ArrayList<Apple>();
//        for (Apple apple: inventory){
//            if ( apple.getColor().equals(color) ) {
//                result.add(apple);
//            }
//        }
//        return result;
//    }

    /**
     * 根据抽象条件进行筛选
     * @param inventory
     * @param applePredicate
     * @return
     */
//    public  static List<Apple> filter(List<Apple> inventory, ApplePredicate applePredicate){
//        ArrayList<Apple> list = new ArrayList<>();
//        for (Apple apple:list){
//            if(applePredicate.test(apple)){
//                    list.add(apple);
//            }
//        }
//        return list;
//
//    }


    //将 List 类型抽象化
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e: list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }



}
