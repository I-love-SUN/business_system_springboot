package com.ljc.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @program: miyou_WeChat
 * @description
 * @author: WangTianLiang
 * @create: 2020-01-20 16:59
 **/
public class DateUtil {

    public final static String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    public final static String[] REPLACE_STRING = new String[]{"GMT+0800", "GMT+08:00"};

    public final static String SPLIT_STRING = "(中国标准时间)";

    public final static String FORMAT_STRING2 = "EEE MMM dd yyyy HH:mm:ss z";

    /**
     * 时间解析  Sun Jan 05 2020 00:00:00 GMT 0800 (中国标准时间)
     *
     * @param dateString
     * @return
     */
    public static String timeCycle(String dateString) {
        try {
            dateString = dateString.split (Pattern.quote (SPLIT_STRING))[0].replace (REPLACE_STRING[0], REPLACE_STRING[1]);
            //转换为date
            SimpleDateFormat sf1 = new SimpleDateFormat (FORMAT_STRING2, Locale.ENGLISH);
            Date date = sf1.parse (dateString);
            return new SimpleDateFormat (FORMAT_STRING).format (date);
        } catch (Exception e) {
            throw new RuntimeException ("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
        }
    }



}
