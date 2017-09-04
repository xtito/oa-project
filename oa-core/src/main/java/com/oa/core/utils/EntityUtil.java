package com.oa.core.utils;

import com.oa.core.annotation.ID;
import com.oa.core.utils.generate.GenerateIdUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * 实体类工具类
 * <p/>
 * Created by [Zy]
 * 2017/8/10 12:01
 */
public class EntityUtil {

    private EntityUtil() {}

    public static Object setEntity(Class<Object> clazz, Map<String, Object> map) throws Exception {
        StringBuffer buffer = null;
        //创建对象实例
        Object object = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                //获取属性的名字
                String fieldName = field.getName();
                if (map.get(fieldName) != null) {
                    //参数类型
                    Class[] paramType = {field.getType()};

                    String value = null;
                    //参数值
                    Object[] paramValue = new Object[1];
                    try {
                        value = map.get(fieldName).toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (value != null) {
                        if (field.getType().toString().equals("class java.lang.String")) {
                            paramValue[0] = value;
                        }
                        if (field.getType().toString().equals("class java.lang.Integer")) {
                            paramValue[0] = new Integer(value);
                        }
                        if (field.getType().toString().equals("class java.lang.Double")) {
                            paramValue[0] = new Double(value);
                        }
                        if (field.getType().toString().equals("class java.util.Date")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            paramValue[0] = sdf.parse(value);
                        }
                        //得到set方法的名字
                        buffer = new StringBuffer("set");
                        buffer.append(fieldName.substring(0, 1).toUpperCase());
                        buffer.append(fieldName.substring(1));
                        //获取放回
                        Method method = null;
                        method = clazz.getDeclaredMethod(buffer.toString(), paramType);
                        //执行方法
                        method.invoke(object, paramValue);
                    }
                }
            }
        }

        return object;
    }


    /**
     * @param obj1 放回
     * @param obj2 获取
     * @param objs 不要的字段
     */
    public static void copyField(Object obj1, Object obj2, String[] objs) {
        Class class1 = obj1.getClass();
        Class class2 = obj2.getClass();
        StringBuffer buffer = null;
        Field[] fields = class1.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String fieldName = field.getName();
                boolean isHave = false;
                if (objs != null && objs.length > 0) {
                    for (String f : objs) {
                        if (f.equals(fieldName)) {
                            isHave = true;
                            break;
                        }
                    }
                }
                if (isHave) {
                    continue;
                }
                Class[] paramType = {field.getType()};
                //获取放回
                Method method = null;
                //执行方法
                Object val = null;
                try {
                    //得到set方法的名字
                    buffer = new StringBuffer("get");
                    buffer.append(fieldName.substring(0, 1).toUpperCase());
                    buffer.append(fieldName.substring(1));
                    method = class2.getDeclaredMethod(buffer.toString(), null);
                    val = method.invoke(obj2, null);
                } catch (Exception e) {
//                e.printStackTrace();
                }
                if (val != null) {
                    try {
                        buffer = new StringBuffer("set");
                        buffer.append(fieldName.substring(0, 1).toUpperCase());
                        buffer.append(fieldName.substring(1));
                        method = class1.getDeclaredMethod(buffer.toString(), paramType);
                        method.invoke(obj1, val);
                    } catch (Exception e) {
//                    e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void setId(Object obj) {
        Class class1 = obj.getClass();
        Field[] fields = class1.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(ID.class)) {
                    Object val = null;
                    field.setAccessible(true);  //允许访问private 方法
                    try {
                        val = field.get(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (val == null || val.toString().equals("")) {
                        try {
                            field.set(obj, GenerateIdUtil.createUUID());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

}
