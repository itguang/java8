package test;

import com.sun.xml.internal.bind.v2.util.FatalAdapter;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

/**
 * 并行流
 *
 * @author itguang
 * @create 2017-11-18 8:43
 **/
public class Test6 {

    public Long measureSumPref(Function<Long, Long> addr, Long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            Long start = System.nanoTime();
            Long sum = addr.apply(n);
            Long druation = (System.nanoTime() - start) / 1000000;
            if (druation < fastest) {
                fastest = druation;
            }
        }
        return fastest;

    }


    @Before
    public void before() {


    }


    @Test
    public void test3() {
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("ForkJoinPool默认线程数量等于cpu数量=" + i);
    }

    //顺序
    @Test
    public void test4() {
        Long fast = measureSumPref(ParallelStreams::sequentialSum, 1000 * 10000L);
        System.out.println("sequentialSum= " + fast);//398毫秒


    }

    //迭代
    @Test
    public void test5() {
        Long fast = measureSumPref(ParallelStreams::iterativeSum, 1000 * 10000L);
        System.out.println("iterativeSum= "+ fast);//153毫秒


    }


    //并行
    @Test
    public void test6(){
        Long fast = measureSumPref(ParallelStreams::parallelSum, 1000 * 10000L);
        System.out.println("parallelSum= "+fast);//1309毫秒
    }

    //顺序流(改进版)
    @Test
    public void test7(){
        Long fast = measureSumPref(ParallelStreams::sequentialSum2, 1000 * 10000L);
        System.out.println("顺序流(改进版)="+fast);//56毫秒------改进之前:398毫秒

    }



    //并行流(改进版)
    @Test
    public void test8(){
        Long fast = measureSumPref(ParallelStreams::paraparallelSum2, 1000 * 10000L);
        System.out.println("并行流(改进版)="+fast);//14毫秒--------改进之前:1309毫秒

    }

    @Test
    public void test9(){
        long[] numbers = LongStream.rangeClosed(1, 1000*10000).toArray();
        ForkJoinTest forkJoinTest = new ForkJoinTest(numbers);
        Long sum = new ForkJoinPool().invoke(forkJoinTest);
        System.out.println(sum);

    }






}
