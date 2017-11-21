package impl;

/**
 * @author itguang
 * @create 2017-11-21 13:41
 **/
public interface B extends A {


    default void hello(){
        System.out.println("hello from B...");
    }

}
