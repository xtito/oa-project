package com.oa.core.utils.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 反射工具类
 * 
 * @author fangll
 * 
 */
public class ReflectorHelper {
	/**
	 * 得到某个对象的公共属性
	 * 
	 * @param owner
	 *            , fieldName
	 * @return 该属性对象
	 * @throws Exception
	 * 
	 */
	public static Object getProperty(Object owner, String fieldName) throws Exception {
		Class<?> ownerClass = owner.getClass();
		Field field = ownerClass.getField(fieldName);
		return field.get(owner);
	}
	
	/**
	 * 获取属性列表
	 */
	public static List<Field> getFields(Class<?> clazz) {
		if(clazz == null) throw new NullPointerException();
		List<Field> list = new ArrayList<Field>();
		if(clazz.getDeclaredFields() != null && clazz.getDeclaredFields().length > 0){
			Collections.addAll(list, clazz.getDeclaredFields());
		}
		return list;
	}
	
	/**
	 * 得到某类的静态公共属性
	 * 
	 * @param className
	 *            类名
	 * @param fieldName
	 *            属性名
	 * @return 该属性对象
	 * @throws Exception
	 */
	public static Object getStaticProperty(String className, String fieldName) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Field field = ownerClass.getField(fieldName);
		return field.get(ownerClass);
	}

	/**
	 * 执行某对象方法
	 * 
	 * @param owner
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数
	 * @return 方法返回值
	 * @throws Exception
	 */
	public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
		Class<?> ownerClass = owner.getClass();
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}
		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(owner, args);
	}

	/**
	 * 执行某类的静态方法
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数数组
	 * @return 执行方法返回的结果
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}
		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(null, args);
	}

	/**
	 * 新建实例
	 * 
	 * @param className
	 *            类名
	 * @param args
	 *            构造函数的参数 如果无构造参数，args 填写为 null
	 * @return 新建的实例
	 * @throws Exception
	 */
	public static Object newInstance(String className, Object[] args) throws Exception {
		Class<?> newoneClass = Class.forName(className);

		if (args == null) {
			return newoneClass.newInstance();

		} else {
			Class<?>[] argsClass = new Class[args.length];

			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}
			Constructor<?> cons = newoneClass.getConstructor(argsClass);
			return cons.newInstance(args);
		}

	}

	/**
	 * 是不是某个类的实例
	 * 
	 * @param obj
	 *            实例
	 * @param cls
	 *            类
	 * @return 如果 obj 是此类的实例，则返回 true
	 */
	public static boolean isInstance(Object obj, Class<?> cls) {
		return cls.isInstance(obj);
	}

}
