package com.atguigu.juc.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArrayListTest {
    public static void main(String[] args) {
        List<UUID> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(UUID.randomUUID());
            System.out.println(list);
        }

    }
}
