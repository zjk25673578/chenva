package com.util.msg;

import com.alibaba.fastjson.JSONObject;
import com.pojo.Message;
import com.pojo.MessageEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于构建$.ajax, $.post, $.get, $.load等简单ajax结构返回数据
 */
public class MsgUtil {

    /**
     * 构建response消息模型
     * 根据代码从枚举MessageEnum中获取消息
     *
     * @param code 消息代码
     * @return
     */
    public static Message msg(int code) {
        if (code >= 0) {
            return msg(true, "操作成功 !");
        }
        MessageEnum m = MessageEnum.codeOf(code);
        if (m == null) {
            return msg(false, "未知的错误消息类型");
        }
        return msg(false, m.msg());
    }

    /**
     * 构建response消息模型
     *
     * @param code
     * @param action
     * @return
     */
    public static Message msg(int code, String action) {
        return msg(code >= 0, action);
    }

    /**
     * 构建response消息模型
     *
     * @param result true, false
     * @param action
     * @return
     */
    public static Message msg(boolean result, String action) {
        Message msg = new Message();
        msg.setSuccess(result);
        msg.setMessage(action);
        msg.setIconType(result ? 1 : 7); // iconType属性用于前端layui框架layer弹出层的icon显示图标
        return msg;
    }

    /**
     * 构建response消息模型
     *
     * @param result true, false
     * @param action
     * @param data   数据列表
     * @return
     */
    public static Message msg(int result, String action, List<?> data) {
        Message msg = msg(result >= 0, action);
        msg.setData(data);
        return msg;
    }

    /**
     * 用于接收BaseMapper中searchFor方法的返回值
     * 组成layui-table的数据结构
     *
     * @param count
     * @param list
     * @return
     */
    public static Map<String, Object> searchForLayData(int count, List list) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", count);
        resultMap.put("data", list);
        return resultMap;
    }

    /**
     * 组装layui-data-table所需的数据格式
     *
     * @param code  状态码
     * @param msg   当数据获取失败时返回的消息
     * @param count 数据条数, 用于分页和统计
     * @param data  数据列表
     * @return
     */
    public static Map<String, Object> layData(int code, String msg, int count, List<?> data) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        resultMap.put("count", count);
        resultMap.put("data", data);
        return resultMap;
    }

    /**
     * 组装layui-data-table所需的数据格式
     *
     * @param code 状态码
     * @param msg  当数据获取失败时返回的消息
     * @return
     */
    public static void layData(Map<String, Object> resultMap, int code, String msg) {
        if (resultMap == null) {
            System.err.println("resultMap为空");
            return;
        }
        if (resultMap.get("count") == null) {
            System.err.println("resultMap中count属性为空");
        }
        if (resultMap.get("data") == null) {
            System.err.println("resultMap中data属性为空");
        }
        resultMap.put("code", code);
        resultMap.put("msg", msg);
    }

    /**
     * 组装ajax返回的数据结构
     *
     * @param objs
     * @return
     */
    public static JSONObject ajaxData(Object... objs) {
        if (!valid(objs)) {
            System.err.println("参数必须为偶数个 !");
            return null;
        }
        if (!validString(objs)) {
            System.err.println("奇数位上的参数必须为字符串 !");
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < objs.length; i += 2) {
            jsonObject.put(objs[i].toString(), objs[i + 1]);
        }
        return jsonObject;
    }

    /**
     * 判断参数个数是否为偶数个
     *
     * @param objs
     * @return
     */
    private static boolean valid(Object... objs) {
        if (objs == null) {
            return false;
        }
        return objs.length % 2 == 0;
    }

    /**
     * 判断奇数位参数是否为String类型
     *
     * @param objs
     * @return
     */
    private static boolean validString(Object... objs) {
        for (int i = 0; i < objs.length; i += 2) {
            if (!(objs[i] instanceof String)) {
                return false;
            }
        }
        return true;
    }
}
