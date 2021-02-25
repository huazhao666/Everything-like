package com.huazhao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:43
 */
public class LogUtil {

    public static void log(String format, Object ... args) {
        String message = String.format(format,args);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String now = dateFormat.format(date);
        System.out.printf("%s: %s\n",now,message);
    }

    public static void main(String[] args) {
        log("hello");
    }
}
