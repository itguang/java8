package test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author itguang
 * @create 2017-11-21 9:35
 **/
public class Test1 {

    @Test
    public void test(){

        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
       numbers.sort(Comparator.naturalOrder());
        System.out.println("正序"+numbers);
        numbers.sort(Comparator.reverseOrder());
        System.out.println("倒序"+numbers);

    }

    @Test
    public void test2(){

        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);



    }

}
