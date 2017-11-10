# 通过行为参数化传递代码

> **如何对你的代码加以改进，从而更灵活地适应不断变化的需求?**

**行为参数化就是可以帮你处理频繁变更的需求的一种软件开发模式.**

一言以蔽之，它意味
着拿出一个代码块，把它准备好却不去执行它。这个代码块以后可以被你程序的其他部分调用，
这意味着你可以推迟这块代码的执行。例如，你可以将代码块作为参数传递给另一个方法，稍后
再去执行它。这样，这个方法的行为就基于那块代码被参数化了。

**打个比方吧:**

你的室友知道怎么开车去超市，再开回家。于是你可
以告诉他去买一些东西，比如面包、奶酪、葡萄酒什么的。这相当于调用一个 goAndBuy 方法，把
购物单作为参数。然而，有一天你在上班，你需要他去做一件他从来没有做过的事情：从邮局取一
个包裹。现在你就需要传递给他一系列指示了：去邮局，使用单号，和工作人员说明情况，取走包
裹。你可以把这些指示用电子邮件发给他，当他收到之后就可以按照指示行事了。你现在做的事情
就更高级一些了，相当于一个方法： go ，它可以接受不同的新行为作为参数，然后去执行.



## 应对不断变化的需求

编写能够应对变化的需求的代码并不容易。让我们来看一个例子，我们会逐步改进这个例子，
以展示一些让代码更灵活的最佳做法。就农场库存程序而言，你必须实现一个从列表中筛选绿苹
果的功能。听起来很简单吧？

第一个解决方案可能是下面这样的：

```java
 /**
     *筛选绿苹果
     * @param inventory 苹果仓库
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();

        for(Apple apple:inventory){
            if ("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;

    }
```
现在农民改主意了，他还想要筛选红苹果。
你该怎么做呢？简单的解决办法就是复制这个方法，把名字改成 filterRedApples ，然后更改
if 条件来匹配红苹果。然而，要是农民想要筛选多种颜色：浅绿色、暗红色、黄色等，这种方法
就应付不了了。一个良好的原则是在编写类似的代码之后，尝试将其抽象化。

```java
 /**
     * 把颜色参数化,应对颜色变化的需求
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory,
                                                  String color) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple: inventory){
            if ( apple.getColor().equals(color) ) {
                result.add(apple);
            }
        }
        return result;
    }
```

这时,农民不断提新需求,按重量刷选,按装量和颜色筛选,于是你不断修改代码,粘贴复制,
一种把所有属性结合起来的笨拙尝试如下所示：

```java_holder_method_tree

public static List<Apple> filterApples(List<Apple> inventory, String color,
int weight, boolean flag) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple: inventory){
                if ( (flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight) ){
                    result.add(apple);
                }
            }
        return result;
}
```