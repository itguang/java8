# java8之----Optional取代null


# 小结:

* null在历史上被引入到程序设计语言中,目的是为了表示变量值的缺失.

* Java 8中引入了一个新的类 java.util.Optional<T> ，对存在或缺失的变量值进行建模。

* 你可以使用静态工厂 Optional.empty(), Optional.of(),Optional.ofNullable 创建Optional对象.

* Optional类支持多种方法,如: map,flatMap,filter,他们在概念上与Stream中对应的方法十分相似.

* 使用Optional能是你更积极的解引用Optional对象,以应对变量值缺失的问题.最终你能更有效的防止代码中出现不期而至的异常.

* 使用 Optional 能帮助你设计更好的API，用户只需要阅读方法签名，就能了解该方法是否接受一个 Optional 类型的值


**注意到Optional和Stream的那些相似之处了吗? 他们都是对数据库查询过程的反思,查询时,多种会被串接在一起执行.**










