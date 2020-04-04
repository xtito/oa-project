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
     * 创建一个 UUID，并去除 - 号
     * @return 返回没有 - 号的 UUID
     */
    public static synchronized String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 将 UUID 中的 - 号替换为 _ 下划线
     * @return 返回替换为 _ 下划线的 UUID
     */
    public static synchronized String createLineUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "_");
    }

    /**
     * 创建一个带 UUID 字符串前缀的 UUID 编号
     * @return 返回带 UUID 字符串前缀的 UUID 编号
     */
    public static synchronized String createPrefixUUID() {
        //"-"转换成"A" 使该值可以用做js定义变量
        return "UUID" + UUID.randomUUID().toString().replaceAll("-", "A");
    }
}
