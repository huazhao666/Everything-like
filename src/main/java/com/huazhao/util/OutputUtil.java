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
public class OutputUtil {
    public static String formatLength(Long length) {
        if(length < 1024){
            return length + "Byte";
        }
        if(length < 1024 * 1024){
            return (length / 1024) + "KB";
        }
        if(length < 1024 * 1024 *1024){
            return (length / 1024 / 1024) + "MB";
        }
        return (length / 1024 / 1024 / 1024) + "GB";

    }

    public static String formatTimeStamp(Long lastModifiedTimeStamp) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
