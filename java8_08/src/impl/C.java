package impl;

import com.sun.tracing.dtrace.ArgsAttributes;

/**
 * @author itguang
 * @create 2017-11-21 13:41
 **/
public class C extends D implements B,A {

    public static void main(String[] args){
        new C().hello();
    }
}
