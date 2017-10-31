package com.hk.learn.listtest;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {

    @Test
    void addList()
    {
        List list = new ArrayList<String>();
        List list2 = new ArrayList<String>();
        list2.add("111");
        list.addAll(list2);
        list.containsAll(list2);
        System.out.println(list.toString());
    }

    @Test
    void nullList()
    {
        List list = new ArrayList<String>();
        List list2 = new ArrayList<String>();
        list2.add("");
        list.add("");
        System.out.println(list.containsAll(list2));
    }

    @Test
    void streamList()
    {
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();

        Map<String,String> h1 = new HashMap<String,String>();
        Map<String,String> h2 = new HashMap<String,String>();
        Map<String,String> h3 = new HashMap<String,String>();

        Map<String,String> h4 = new HashMap<String,String>(){
            { put("A","1");put("B","1");put("C","1");put("D","1");put("E","2");}};

        list.add(h4);

        Map<String,String> h5 = new HashMap<String,String>(){{
             put("A","1");put("B","1");put("C","1");put("D","1");put("A","2");}};
        System.out.println(h4);
        list.add(h5);
        System.out.println(list.toString());
        //System.out.println(list.stream().filter(item -> item.entrySet().iterator().next().getKey().equals("A")).count());
        System.out.println(list.stream()
                .filter(item -> {
                    for(Map.Entry< String, String> entry:item.entrySet())
                    {
                        if(entry.getKey().equals("E"))
                            return true;
                    }
                    return false;
                }).collect(Collectors.toList()));
        //System.out.println(list.stream().map(key -> h.get(key)));
    }

    //去重
    @Test
    void removeDuplicateWithOrder()
    {
        List list = new ArrayList();
        list.add("111111");
        list.add("111111");
        list.add("111111");
        list.add("222222");
        list.add("333333");
        list.add("222222");
        list.add("333333");
        list.add("111111");
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println(" remove duplicate " + list);
    }

    @Test
    void testStringUtils()
    {
        List listBA_1 = new ArrayList();
        listBA_1.add("''");
        System.out.println(StringUtils.join(listBA_1,","));
    }
}
