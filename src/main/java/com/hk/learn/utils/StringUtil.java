package com.hk.learn.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.lang.Exception;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StringUtil {
    private StringUtil() {
    }

    /**
     * 保留x位小数，并向上取整,不足补0
     *
     * @param source
     * @param carryType
     * @return
     */
    public static String toFixed(BigDecimal source, BigDecimal carryType) {
        double md = new BigDecimal(1).divide(carryType).doubleValue();
        int x = (int) Math.log10(md);
        return toFixed(source, x);
    }

    /**
     * 保留x位小数，并向上取整,不足补0
     *
     * @param source
     * @param x
     * @return
     */
    public static String toFixed(BigDecimal source, int x) {
        if (source.compareTo(BigDecimal.ZERO) == 0)
            return "0";
        long num = Math.round(Math.pow(10, x));
        StringBuilder format = new StringBuilder();
        format.append("#0");
        for (int i = 0; i < x; i++) {
            if (i == 0) {
                format.append(".0");
            } else {
                format.append("0");
            }
        }
        double val = Math.ceil(source.doubleValue() * num) / num;
        DecimalFormat dFormat = new DecimalFormat(format.toString());
        return dFormat.format(val);
    }

    /**
     * 保留x位小数，并向上取整,不足补0
     *
     * @param source
     * @param carryType
     * @return
     */
    public static String toFixed(Double source, Double carryType) {
        BigDecimal b = new BigDecimal(Double.toString(carryType));
        double md = new BigDecimal(1).divide(b).doubleValue();
        int x = (int) Math.log10(md);
        return toFixed(source, x);
    }

    /**
     * 保留x位小数，并向上取整,不足补0
     *
     * @param source
     * @param x
     * @return
     */
    public static String toFixed(Double source, int x) {
        long num = Math.round(Math.pow(10, x));
        StringBuilder format = new StringBuilder();
        format.append("#.");
        for (int i = 0; i < x; i++) {
            format.append("0");
        }
        double val = Math.ceil(source * num) / num;
        DecimalFormat dFormat = new DecimalFormat(format.toString());
        return dFormat.format(val);
    }

    /**
     * 字符串是否为空或Null
     *
     * @param value
     * @return
     */
    public static Boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * 日期转化成指定格式的字符串
     *
     * @param value
     * @param formatString
     * @return
     */
    public static String dateToFormatString(Date value, String formatString) {
        DateFormat format = new SimpleDateFormat(formatString);
        return format.format(value);
    }

    public static String dateToFormatString(Date value, String formatString, Locale locale) {
        DateFormat format = new SimpleDateFormat(formatString, locale);
        return format.format(value);
    }

    /**
     * 字符串转化成日期
     *
     * @param source
     * @param formatString
     * @return
     */
    public static Date stringToDate(String source, String formatString) {
        DateFormat format = new SimpleDateFormat(formatString);
        try {
            return format.parse(source);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 生成由特定符号分隔的字符串
     *
     * @param separator
     * @param values
     * @return
     */
    public static String join(String separator, List<String> values) {
        if (values == null || values.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            if (i == 0) {
                result.append(values.get(i));
            } else {
                result.append(separator + values.get(i));
            }
        }
        return result.toString();
    }

    public static <T> String objectToXml(T entity) {
        String result = "";
        try {
            JAXBContext context = JAXBContext.newInstance(entity.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(entity, sw);
            result = sw.toString();
        } catch (Exception ex) {
            //logger.error("object2Xml", ex);
        }
        return result;
    }

    public static boolean isBlankOrEquals(String str, String compare) {
        if(str==null||str.trim().isEmpty())return true;
        return str.equals(compare);
    }

    public static boolean isAllBlankOrEquals(String str, String compare) {
        if(str==null||str.trim().isEmpty()
                ||compare==null||compare.trim().isEmpty()) {
            return true;
        }
        return str.equals(compare);
    }
}
