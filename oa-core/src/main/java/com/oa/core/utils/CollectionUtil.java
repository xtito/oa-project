package com.oa.core.utils;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * 集合工具类
 *
 * Created by [张渊]
 * 2017/12/11 15:47
 */
public class CollectionUtil {

    private CollectionUtil() {}

    /**
     * 判断集合是否不为空
     * @param list 要判断的集合
     * @return 集合不为空返回 true，集合为空返回 false
     */
    public static boolean isNotEmpty(Collection list) {
        return !isEmpty(list);
    }

    /**
     * 判断集合是否为空
     * @param list 要判断的集合
     * @return 集合为空返回 true， 集合不为空返回 false
     */
    public static boolean isEmpty(Collection list) {
        return list == null || list.isEmpty();
    }

    /**
     * 把数组转换成set
     * @param array 要转换的数据
     * @return 转换后的Set集合
     */
    public static Set<?> array2Set(Object[] array) {
        Set<Object> set = new TreeSet<Object>();
        if (array != null && array.length > 0) {
            for (Object id : array) {
                if(null != id){
                    set.add(id);
                }
            }
        }
        return set;
    }

}
