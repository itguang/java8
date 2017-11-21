# 默认方法

传统上，Java程序的接口是将相关方法按照约定组合到一起的方式。实现接口的类必须为接
口中定义的每个方法提供一个实现，或者从父类中继承它的实现。

但是，一旦类库的设计者需要更新接口，向其中加入新的方法，这种方式就会出现问题。

现实的情况是:现存的实体类往往不在接口设计者的控制范围之内,这些实体类为了适配新的接口约定也需要进行修改。

通过前面的介绍我们知道,java8在现存的API接口上引入了很多新的方法,一个例子就是前面使用过的List接口上的sort方法.

Java 8为了解决这一问题引入了一种新的机制。java8的接口现在支持声明方法的同时支持实现.

通过两种方式可以完成这种操作。其一，Java 8允许在接口内声明静态方法。其二，Java 8引入了一个新功能，叫默认方法，通过默认方法
你可以指定接口方法的默认实现。

**换句话说,接口能提供方法的具体实现,因此,实现接口的类如果不显示提供该方法的具体实现,就会自动继承默认的实现.**

这种机制可以让我们平滑的进行接口的优化和演进.

实际上，到目前为止你已经使用了多个默认方法。两个例子就是你前面已经
见过的 List 接口中的 sort ，以及 Collection 接口中的 stream 。

我们看到的 List 接口中的 sort 方法是Java 8中全新的方法，它的定义如下：
```java
 default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }
```

请注意返回类型前的新修饰符 default ,通过它,我们可以知道一个方法是否是默认方法.

**正是有了这个默认方法,我们可以在列表中直接调用sort进行排序.**

```java
 @Test
    public void test(){

        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
       numbers.sort(Comparator.naturalOrder());
        System.out.println("正序"+numbers);
        numbers.sort(Comparator.reverseOrder());
        System.out.println("倒序"+numbers);

    }
```
不 过 除 此 之 外 ， 这 段 代 码 中 还 有 些 其 他 的 新 东 西 。 注 意 到 了 吗 ， 

我们调用了 Comparator.naturalOrder()方法.这是Comparetor接口的一个全新静态方法.它返回一个
Comparator 对象，并按自然序列对其中的元素进行排序（即标准的字母数字方式排序）.
类似的还有  Comparator.reverseOrder()

还有 Collection 接口中的 stream 方法的定义如下：
```java
  default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
 default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
    
```

这里 stream 默认方法中调用了SteamSupport.stream 方法来返回一个流。

看了上面的例子,你可能已经明白了java8设计者为什么要引入默认方法:
**它可以让类自动的继承接口的一个默认实现**














































