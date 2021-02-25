package com.huazhao.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 12:43
 */
public class ListUtil {
    public static <E> List<E> differenceSet(List<E> list1, List<E> list2) {
        List<E> list = new ArrayList<>();
        for(E item : list1){
            if(!list2.contains(item)){
                list.add(item);
            }
        }
        return list;
    }
}
