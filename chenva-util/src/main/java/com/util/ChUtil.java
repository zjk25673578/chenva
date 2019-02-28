package com.util;

import com.pojo.TreeModel;
import com.util.msg.MsgUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 工具类
 */
public class ChUtil {

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
     * 初始化layui返回数据
     *
     * @param count
     * @param list
     * @return
     */

    /**
     * 将List<Map<String, Object>>转换成List<TreeModel>的数据
     *
     * @param dataList 数据源
     * @param id       开始于此, 从parentId为这个值的数据开始取
     * @return
     */
    public static List<TreeModel> list2TreeModel(List<Map<String, Object>> dataList, Integer id) {
        Map<String, String> model = new HashMap<>();
        return list2TreeModel(dataList, id, model);
    }

    /**
     * 将List<Map<String, Object>>转换成List<TreeModel>的数据
     *
     * @param dataList 数据源
     * @param id       开始于此, 从parentId为这个值的数据开始取
     * @param model    作为解析的格式
     *                 原数据模型中的属性可以通过这个参数来匹配
     *                 比如:
     *                 TreeModel中显示的文字是label属性
     *                 而数据中显示的文字是menuName属性
     *                 可以实例化一个Map添加参数
     *                 map.put("label", "menuName")
     *                 方法在解析时可以通过menuName来获取显示的文字而不再是原来的label
     *                 其他不设置的默认使用原属性
     * @return
     */
    public static List<TreeModel> list2TreeModel(List<Map<String, Object>> dataList,
                                                 Integer id,
                                                 Map<String, String> model) {
        if (model == null) {
            return list2TreeModel(dataList, id);
        }

        String _id = model.get("id") == null ? "id" : model.get("id");
        String _label = model.get("label") == null ? "label" : model.get("label");
        String _icon = model.get("icon") == null ? "icon" : model.get("icon");
        String _url = model.get("url") == null ? "url" : model.get("url");
        String _parentId = model.get("parentId") == null ? "parentId" : model.get("parentId");
        String _spread = model.get("spread") == null ? "spread" : model.get("spread");
        String _disabled = model.get("disabled") == null ? "disabled" : model.get("disabled");
        String _checked = model.get("checked") == null ? "checked" : model.get("checked");

        List<TreeModel> treeList = new LinkedList<>();
        if (dataList.size() > 0) {
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> data = dataList.get(i);
                if (id.toString().equals(data.get(_parentId).toString())) {
                    TreeModel treeModel = new TreeModel();
                    treeModel.setId(Integer.parseInt(String.valueOf(data.get(_id))));
                    treeModel.setLabel(String.valueOf(data.get(_label)));
                    Object o = data.get(_icon);
                    if (o != null) {
                        treeModel.setIcon(o.toString());
                    }
                    treeModel.setUrl(String.valueOf(data.get(_url)));
                    treeModel.setParentId(Integer.parseInt(String.valueOf(data.get(_parentId))));

                    treeModel.setSpread(Boolean.parseBoolean(String.valueOf(data.get(_spread))));
                    treeModel.setDisabled(Boolean.parseBoolean(String.valueOf(data.get(_disabled))));
                    treeModel.setChecked(Boolean.parseBoolean(String.valueOf(data.get(_checked))));

                    List<TreeModel> temp = list2TreeModel(dataList, treeModel.getId(), model);
                    treeModel.setChildren(temp);
                    /*
                        将当前遍历的这个Map中的与TreeModel中的属性匹配上的去掉key值
                        然后将剩下的键值对存到TreeModel的attr属性中作为额外的属性
                     */
                    removeKeyValues(data, model.keySet());
                    removeKeyValues(data, getFieldString());
                    treeModel.setAttr(data); // 将除TreeModel类中成员变量额外的属性保存到这里
                    /**/
                    treeList.add(treeModel);
                }
            }
        }
        return treeList;
    }

    /**
     * 将已经赋值的TreeModel属性移除
     *
     * @param map 源数据
     * @param set 需要移除的key值
     */
    private static void removeKeyValues(Map<String, Object> map, Set<String> set) {
        if (map.size() > 0) {
            for (String key : set) {
                map.remove(key);
            }
        }
    }

    /**
     * 获取TreeModel的属性名
     *
     * @return TreeModel成员属性的集合
     */
    private static Set<String> getFieldString() {
        Set<String> set = new HashSet<>();
        Field[] fields = TreeModel.class.getDeclaredFields();
        for (Field field : fields) {
            set.add(field.getName());
        }
        return set;
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
