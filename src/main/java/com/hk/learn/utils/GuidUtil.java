package com.hk.learn.utils;

import java.util.UUID;

public class GuidUtil {
    private GuidUtil() {
    }

    /**
     * 获取guid唯一标识符格式化
     * @return
     */
    public static String getSessionId() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }
}
