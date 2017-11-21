package impl;

public interface A {

    default void hello(){
        System.out.println("hello from A...");
    }
}
