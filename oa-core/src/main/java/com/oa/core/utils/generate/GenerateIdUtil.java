package com.oa.core.utils.generate;

import java.util.UUID;

/**
 * 生成uuid
 *
 * Created by [张渊]
 * 2017/8/10 9:57
 */
public class GenerateIdUtil {

    private GenerateIdUtil() {
        super();
    }

    /**
     * 创建一个新的Id(uuid),线程同步
     */
    public static synchronized String createId() {
        //"-"转换成"A" 使该值可以用做js定义变量
        return "UUID" + UUID.randomUUID().toString().replaceAll("-", "A");
    }

    public static synchronized String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "_");
    }

}
