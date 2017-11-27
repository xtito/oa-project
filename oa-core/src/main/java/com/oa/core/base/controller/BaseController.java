package com.oa.core.base.controller;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by [张渊]
 * 2017/11/27 23:11
 */
public class BaseController {

    /**
     * 将状态和提示文字封装到JSON对象
     * @param success 成功或失败状态
     * @param info 提示文字
     * @return JSON字符串
     */
    public String parseJsonStr(boolean success, String info) {
        JSONObject json = new JSONObject();
        json.put("success", success);
        json.put("info", info);
        return json.toString();
    }
}
