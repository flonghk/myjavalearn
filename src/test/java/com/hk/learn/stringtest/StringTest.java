package com.hk.learn.stringtest;

import org.testng.annotations.Test;

public class StringTest {
    @Test
    void testEquals()
    {
        String s = "QTE:/CI/NEGO 此指令仅限已建立PNR，配置为CAN732查询/出票, 请一切以指令为准！不得手工出票！)";
        String s1 = "QTE:/CI/NEGO 此指令仅限已建立PNR，配置为CAN732查询/出票, 请一切以指令为准！不得手工出票！)";
        System.out.println(s.equals(s1));
    }

    @Test
    void replace()
    {
        String ss ="\"1111\"";
        System.out.println(ss.replaceAll("\"",""));
    }

    @Test
    void kongge()
    {
        String ss ="";
        System.out.println("空格:"+String.valueOf(ss));
    }

    @Test
    void subString()
    {
        String ss ="695-2017010677";
        System.out.println(ss.substring(4));
    }
}
