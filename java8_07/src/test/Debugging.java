package test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author itguang
 * @create 2017-11-20 15:03
 **/
public class Debugging {
    public static void main(String[]args){

        List<Point> points = Arrays.asList(new Point(5, 10), null);
        points.stream()
                .map(Point::getX)
                .forEach(System.out::println);
    }
}
