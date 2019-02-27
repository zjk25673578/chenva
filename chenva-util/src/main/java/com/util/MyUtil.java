package com.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 工具类
 */
public class MyUtil {

    /**
     * 将多个对象以字符串的形式拼接起来
     *
     * @param objs
     * @return
     */
    public static String concat(Object... objs) {
        if (objs == null || objs.length == 0) {
            return "";
        }
        StringBuilder temp = new StringBuilder();
        for (Object o : objs) {
            if (o != null)
                temp.append(o);
        }
        return temp.toString();
    }

    /**
     * 用于将多个javabean转换成Map<String, Object><br>
     * 应当注意重复的key值
     * 主要用于MyBatis参数的合并
     *
     * @param objects
     * @return
     * @throws Exception
     */
    public static Map<String, Object> bean2Map(Object... objects) throws Exception {
        Map<String, Object> _map = null;
        if (objects != null && objects.length > 0) {
            for (Object object : objects) {
                if (_map == null) {
                    _map = transBean2Map(object);
                } else {
                    _map.putAll(transBean2Map(object));
                }
            }
        }
        return _map;
    }

    /**
     * 通过反射将javaBean转换成Map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> transBean2Map(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            try {
                return (Map<String, Object>) obj;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            // 过滤class属性
            if (!key.equals("class")) { // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 构建MyBatis查询参数
     *
     * @param objs
     * @return
     */
    public static Map<String, Object> searchForArgs(Object... objs) {
        return MsgUtil.ajaxData(objs);
    }

    /**
     * 将两个以','分割的数字字符串转换成Set集合(用Set是为了去除重复)
     *
     * @param childrenIds
     * @param parentMenuIds
     * @return
     */
    public static Set<Integer> concatMenuIds(String childrenIds, String parentMenuIds) {
        Set<Integer> set = string2Set(childrenIds);
        set.addAll(string2Set(parentMenuIds));
        return set;
    }

    /**
     * 将一个以','分割的数字字符串转换成Set集合(用Set是为了去除重复)
     *
     * @param content
     * @return
     */
    public static Set<Integer> string2Set(String content) {
        Set<Integer> set = new HashSet<>();
        if (content != null && content.trim().length() > 0) {
            String[] ids = content.split(",");
            int _id;
            for (String id : ids) {
                if ("0".equals(id)) continue;
                try {
                    _id = Integer.parseInt(id);
                } catch (NumberFormatException ignored) {
                    continue;
                }
                set.add(_id);
            }
        }
        return set;
    }
}
