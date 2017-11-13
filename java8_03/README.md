# 函数式数据处理(一)--流

## 引入流

集合是Java中使用最多的API。要是没有集合，还能做什么呢？几乎每个Java应用程序都会制
造和处理集合。集合对于很多编程任务来说都是非常基本的：它们可以让你把数据分组并加以处
理。

*  很多业务逻辑都涉及类似于数据库的操作，比如对几道菜按照类别进行分组 （比如全素
  菜肴），或查找出最贵的菜。你自己用迭代器重新实现过这些操作多少遍？大部分数据库
  都允许你声明式地指定这些操作。比如，以下SQL查询语句就可以选出热量较低的菜肴名
  称： SELECT name FROM dishes WHERE calorie < 400 。你看，你不需要实现如何
  根据菜肴的属性进行筛选（比如利用迭代器和累加器），你只需要表达你想要什么。这个
  基本的思路意味着，你用不着担心怎么去显式地实现这些查询语句——都替你办好了！
  怎么到了集合这里就不能这样了呢？
  
*  要是要处理大量元素又该怎么办呢？为了提高性能，你需要并行处理，并利用多核架构。
  但写并行代码比用迭代器还要复杂，而且调试起来也够受的！

**那Java语言的设计者能做些什么，来帮助你节约宝贵的时间，让你这个程序员活得轻松一点儿呢？你可能已经猜到了，答案就是流。**
  
  

## 流 是什么

流(Stream)是javaAPI的新成员,它允许你以声明性方式处理数据集(通过查询语句来表达而不是临时编写一个实现).
此外,流还可以并行的进行处理,你无须写任何多线程代码了.

首先,我们以一个例子看下流的使用:下面两段代码都是用来返回低热量的菜肴名称的，
并按照卡路里排序，一个是用Java 7写的，另一个是用Java 8的流写的。比较一下。不用太担心
Java 8代码怎么写，我们在接下来的几节里会详细解释。

实体类 Dish.java
```java
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    
    //get/set 略
    }
```
* **java7中实现**

```java
 /**
     * java7
     */
    @Test
    public void test1() {

        //选出低热量菜肴
        ArrayList<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);

            }
        }
        //按照卡路里进行排序
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish dish1, Dish dish2) {
                return Integer.compare(dish1.getCalories(), dish2.getCalories());
            }
        });
        //输出菜肴名称
        ArrayList<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());

        }


        System.out.println(lowCaloricDishesName);

    }
```

在这段代码中，你用了一个“垃圾变量” lowCaloricDishes 。它唯一的作用就是作为一次
性的中间容器。在Java 8中，实现的细节被放在它本该归属的库里了。

* **之后（Java 8）**

```java
/**
     * java8
     */
    @Test
    public void test2() {
        List<String> lowCaloricDishesName =
                menu.stream()
                        //选出低热量菜肴
                        .filter(d -> d.getCalories() < 400)
                        //按照卡路里进行排序
                        .sorted(Comparator.comparing(Dish::getCalories))
                        //输出菜肴名称
                        .map(Dish::getName)
                        .collect(toList());

        System.out.println(lowCaloricDishesName);
    }
```

为了利用多核架构并行执行这段代码，你只需要把 stream() 换成 parallelStream() ：

```java
/**
     * java8多核架构并行执行这段代码
     */
    @Test
    public void test3() {
        List<String> lowCaloricDishesName =
                menu.parallelStream()
                        //选出低热量菜肴
                        .filter(d -> d.getCalories() < 400)
                        //按照卡路里进行排序
                        .sorted(Comparator.comparing(Dish::getCalories))
                        //输出菜肴名称
                        .map(Dish::getName)
                        .collect(toList());

        System.out.println(lowCaloricDishesName);
    }
```

> 你可能会想，在调用 parallelStream 方法的时候到底发生了什么。用了多少个线程？对性
  能有多大提升？后面会详细讨论这些问题
  

现在，你可以看出，从软件工程师的角度来看，新
的方法有几个显而易见的好处:

* 代码是以声明性方式写的:说明想要完成什么,而不是说明如何实现一个操作(利用for if 等控制语句).这种方法加上行为参数化,可以让你很轻松的应对变化的需求,你很容易再创建一个代码版本，利用
Lambda表达式来筛选高卡路里的菜肴，而用不着去复制粘贴代码

* 你可以把几个基础操作连接起来:来表达复杂的数据流水线工作,同时保证代码清晰可读.filter 的结果被传给了 sorted 方法，再传给 map 方法，最后传给 collect 方法。

**需要注意的是: filter(),sorted(),map(),collect() 返回的都是流(Stream),都是Stream的方法**


  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
