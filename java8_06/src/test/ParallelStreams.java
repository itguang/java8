package test;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author itguang
 * @create 2017-11-18 9:50
 **/
public class ParallelStreams {
    //顺序
    public static Long sequentialSum(Long n) {
        return Stream.iterate(1L, i -> i + 1L)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    //顺序流改进版 LongStream.rangeClosed
    public static Long sequentialSum2(Long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L,Long::sum);

    }


    //传统迭代
    public static Long iterativeSum(Long n) {
        Long result = 0L;
        for (Long i = 1L; i < n; i++) {
            result = result + i;
        }
        return result;
    }


    //并行流
    public static Long parallelSum(Long n) {
        return Stream.iterate(1L, i -> i + 1L)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    //并行流改进版
    public static Long paraparallelSum2(Long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L,Long::sum);

    }
}