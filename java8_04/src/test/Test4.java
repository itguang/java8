package test;

import org.junit.Test;
import pojo.Dish;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 使用流
 *
 * @author itguang
 * @create 2017-11-14 8:49
 **/
public class Test4 {

    List<Dish> menu = Dish.menu;


    @Test
    public void test1() {

        List<Dish> vegetarianMenu =
                menu.stream()
                        .filter(dish -> dish.isVegetarian())
                        .collect(toList());
        System.out.println(vegetarianMenu);
    }

    @Test
    public void test2() {

        List<Dish> vegetarianMenu =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());
        System.out.println(vegetarianMenu);
    }


    @Test
    public void test3() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);


    }

    @Test
    public void test4() {

        List<Dish> vegetarianMenu = menu.stream().filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(toList());

        System.out.println(vegetarianMenu);
    }

    @Test
    public void test5() {

        List<String> vegetarianMenu =
                menu.stream()
                        .map(Dish::getName)
                        .collect(toList());

        System.out.println(vegetarianMenu);
    }

    @Test
    public void test6() {

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> integers = words.stream()
                .map(String::length)
                .collect(toList());

        System.out.println(integers);
    }

    @Test
    public void test7() {

        List<Integer> integers = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(integers);
    }

    @Test
    public void test8() {

        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> list = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        System.out.println(list.toString());
    }

    @Test
    public void test9() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> list = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        System.out.println(list);

    }

    @Test
    public void test10() {
        List<String> words = Arrays.asList("Hello", "World");
        List<Stream<String>> list = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());

        System.out.println(list);

    }

    @Test
    public void test11() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String> list = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        System.out.println(list);

    }

    @Test
    public void test12() {
        boolean b = menu.stream().anyMatch(Dish::isVegetarian);
        if (b) {
            System.out.println("有素菜!");

        }

    }

    @Test
    public void test13() {
        boolean b = menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
        if (b) {
            System.out.println("没有高热量事物,吃!");
        }

    }

    @Test
    public void test14() {
        boolean b = menu.stream().noneMatch(dish -> {
            return dish.getCalories() > 1000;
        });
        if (b) {
            System.out.println("没有高热量事物,吃!");
        }

    }


    @Test
    public void test15() {
        Optional<Dish> any = menu.stream()
                .filter(dish -> dish.getCalories() == 450)
                .findAny();
        System.out.println(any);

        any.ifPresent(dish -> {
            System.out.println(dish.getCalories());
        });

    }


    @Test
    public void test16() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
        System.out.println(firstSquareDivisibleByThree);
        firstSquareDivisibleByThree.ifPresent(System.out::println);

    }


    @Test
    public void test17() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println(reduce);

    }


    @Test
    public void test18() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> optional = numbers.stream()
                .reduce(Integer::sum);
        System.out.println(optional);

    }


    @Test
    public void test19() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = numbers.stream()
                .reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        System.out.println("max=" + max + ",min=" + min);

    }

    @Test
    public void test20() {
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        System.out.println(sum);

    }


    @Test
    public void test21() {
        OptionalInt optionalInt = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        int max = optionalInt.orElse(0);


        System.out.println(max);

    }

    @Test
    public void test22() {

        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);

        IntStream intStream1 = IntStream.range(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(intStream1.count());
    }


    @Test
    public void test23() {

        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }


    @Test
    public void test25() {

        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream intStream = Arrays.stream(numbers);

        int sum = intStream.sum();
        System.out.println(sum);
    }


    @Test
    public void test26() {
        try {
            Stream<String> stream = Files.lines(Paths.get("C:\\data.txt"));
            //Arrays.stream()把字符串数组转成流
            long count = stream.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test27() {
    Stream.iterate(0,n->n+2)
            .limit(10)
            .forEach(System.out::println);
    }


}
