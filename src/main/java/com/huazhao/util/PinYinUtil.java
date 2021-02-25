package com.huazhao.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:44
 */
public class PinYinUtil {
    public static String getPinYin(String name) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuffer sb = new StringBuffer();
        for (char x : name.toCharArray()) {
            try {
                String pinArray[] = PinyinHelper.toHanyuPinyinStringArray(x, format);
                if (pinArray == null || pinArray.length == 0) {
                    sb.append(x);
                    continue;
                }
                sb.append(pinArray[0]);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    public static String getPinYinFirst(String name) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuffer sb = new StringBuffer();
        for (char x : name.toCharArray()) {
            try {
                String pinArray[] = PinyinHelper.toHanyuPinyinStringArray(x, format);
                if (pinArray == null || pinArray.length == 0) {
                    sb.append(x);
                    continue;
                }
                sb.append(pinArray[0].charAt(0));
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPinYin("我爱你"));
        System.out.println(getPinYinFirst("我爱你"));
    }
}
