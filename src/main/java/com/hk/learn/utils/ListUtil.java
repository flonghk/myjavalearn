package com.hk.learn.utils;

import java.lang.Exception;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    private ListUtil() {
    }

    public static <T> List<T> findAll(List<T> oriList, Class<T> tClass, String propertyName, String propertyValue) {
        try {
            if (oriList == null)
                return oriList;

            List<T> resultList = new ArrayList<>();
            for (T item : oriList) {
                Field fed = item.getClass().getDeclaredField(propertyName);
                Object val = fed.get(tClass);
                if (propertyValue.equals(val.toString())) {
                    resultList.add(item);
                }
            }
            return resultList;
        } catch (Exception ex) {
           // logger.error("FindAll", ex);
            return new ArrayList<>(0);
        }
    }

    public static <T> T find(List<T> oriList, Class<T> tClass, String propertyName, String propertyValue) {
        try {
            if (oriList == null)
                return null;

            for (T item : oriList) {
                Field fed = item.getClass().getDeclaredField(propertyName);
                Object val = fed.get(tClass);
                if (propertyValue.equals(val.toString())) {
                    return item;
                }
            }
        } catch (Exception ex) {
           // logger.error("FindAll", ex);
            return null;
        }
        return null;
    }
}
