package com.atguigu.juc.day3;

import java.util.ArrayList;
import java.util.List;

public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(1,"zhangsan",12);
        User u2 = new User(2,"lisi",22);
        User u3 = new User(3,"wangwu",32);
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        list.stream().filter((p)->{
            return p.getId()%2==0;
        }).filter((p)->{
            return  p.getAge()>20;
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).map((p)->{
            return p.getName().toUpperCase();
        }).limit(1).forEach(System.out::println);
    }
}
