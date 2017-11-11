package chapter_o2.impl;

import pojo.Apple;

/**
 * 选出重苹果的实现类
 *
 * @author itguang
 * @create 2017-11-11 8:59
 **/
public class AppleHeavyWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight()>150;
    }
}
