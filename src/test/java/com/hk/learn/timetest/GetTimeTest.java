package com.hk.learn.timetest;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimeTest {
    public static Date toDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return sdf.parse(date);
        } catch (Exception e) {

        }
        return null;
    }

    @Test
    public void ss()
    {
        String s = "2017-10-25 15:29:06";
        System.out.println(toDate(s).getTime());
    }
}
