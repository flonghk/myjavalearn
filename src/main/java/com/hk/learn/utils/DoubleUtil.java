package com.hk.learn.utils;

import java.math.BigDecimal;

public class DoubleUtil {
    private DoubleUtil(){}
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    private static final String ERROR_MESSAGE_ZERO="The scale must be a positive integer or zero";

    /**
     * 加法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 加法
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double add(double v1, double v2, int scale)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 加法
     *
     * @param v1
     * @param v2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double add(double v1, double v2, int scale, int roundingMode)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(scale, roundingMode).doubleValue();
    }

    /**
     * 减法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double subtract(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 减法
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double subtract(double v1, double v2, int scale)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 减法
     *
     * @param v1
     * @param v2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double subtract(double v1, double v2, int scale, int roundingMode)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).setScale(scale, roundingMode).doubleValue();
    }

    /**
     * 乘法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double multiply(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 乘法
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double multiply(double v1, double v2, int scale)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 乘法
     *
     * @param v1
     * @param v2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double multiply(double v1, double v2, int scale, int roundingMode)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(scale, roundingMode).doubleValue();
    }

    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double divide(double v1, double v2)
    {
        return divide(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double divide(double v1, double v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException(ERROR_MESSAGE_ZERO);
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double divide(double v1, double v2, int scale, int roundingMode)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException(ERROR_MESSAGE_ZERO);
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, roundingMode).doubleValue();
    }

    /**
     * 除法
     *
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException(ERROR_MESSAGE_ZERO);
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法
     *
     * @param v
     * @param scale
     * @param roundingMode
     * @return
     */
    public static double round(double v, int scale, int roundingMode)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException(ERROR_MESSAGE_ZERO);
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, roundingMode).doubleValue();
    }
}
