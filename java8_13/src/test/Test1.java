package test;

import org.junit.Test;

import java.util.function.Function;

/**
 * @author itguang
 * @create 2017-11-24 13:25
 **/
public class Test1 {



    @Test
    public void test1(){
        Function<String,Integer> str2int = (Integer::parseInt);

    }
}
