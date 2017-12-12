package com.oa.core.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装Jackson
 *
 * Created by [张渊]
 * 2017/12/12 20:42
 */
public class JacksonUtil {

    private JacksonUtil() {}

    /**
     * 使用jackson返回前台请求响应状态
     * @param success 操作是否成功
     * @param info 返回信息
     * @return JSON串
     * @throws JsonProcessingException
     */
    public static String parseJsonStr(boolean success, String info) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        map.put("info", info);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }


    /**
     * 使用jackson将对象转换为JSON
     * @param object 要转换的对象
     * @return JSON串
     * @throws JsonProcessingException
     */
    public static String parseJsonStr(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }


    /**
     * 使用jackson将JSON转换为对象
     * @param json 要转换的JSON串
     * @return 转换后的对象
     * @throws JsonProcessingException
     */
    public static Object jsonToObject(String json, Class entityClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, entityClass);
    }

}
