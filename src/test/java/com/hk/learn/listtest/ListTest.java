package com.hk.learn.listtest;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    @Test
    void addList()
    {
        List list = new ArrayList<String>();
        List list2 = new ArrayList<String>();
        list2.add("111");
        list.addAll(list2);
        System.out.println(list.toString());
    }

    @Test
    void nullList()
    {
        List list = new ArrayList<String>();
        List list2 = new ArrayList<String>();
        list2.add("");
        list.add("");
        System.out.println(list.contains(list2));
    }

}
