package com.atguigu.juc.day3;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionTest {
    public static void main(String[] args) {
        //消费型
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("1234");

        //供给型
        Supplier<String> supplier =()->{

            return "abc";
        };

        String s = supplier.get();
        System.out.println("s = " + s);

        //函数型接口
        Function<String,String> function = (t)->{
            System.out.println("t = " + t);
            return "baidu";
        };
        String apply = function.apply("0621");
        System.out.println("apply = " + apply);


        //断言型
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                System.out.println("s = " + s);
                return true;
            }
        };

        boolean hello = predicate.test("hello");
        System.out.println("hello = " + hello);
    }
}
