package test;

import java.util.Arrays;
import java.util.List;

/**
 * @author itguang
 * @create 2017-11-20 15:42
 **/
public class Test {


   @org.junit.Test
    public void test(){
       List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
       numbers.stream()
               .map(x->x+17)
               .filter(x->x%2==0)
               .limit(3)
               .forEach(System.out::println);

    }

   @org.junit.Test
    public void test2(){
       List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
       numbers.stream()
               .peek(x-> System.out.println("from Stream:"+x))
               .map(x->x+17)
               .peek(x-> System.out.println("from map:"+x))
               .filter(x->x%2==0)
               .peek(x-> System.out.println("from filter:"+x))
               .limit(3)
               .peek(x-> System.out.println("from limit:"+x))
               .forEach(System.out::println);

    }
}
