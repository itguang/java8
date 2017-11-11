package chapter_o2.impl;

import pojo.Apple;

/**
 * 选出绿苹果的实现类
 *
 * @author itguang
 * @create 2017-11-11 9:00
 **/
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
