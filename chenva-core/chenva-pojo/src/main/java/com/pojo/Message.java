package com.pojo;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * ajax相应数据模型
 *
 * @author Administrator
 */
public class Message {
    private boolean success; // 是否成功获取数据
    private String message; // 返回的消息提示
    private Integer iconType; // layer弹出层对应的icon
    private List<?> data; // 数据集

    public Message() {
    }

    public Message(boolean success, String message, Integer iconType) {
        this.success = success;
        this.message = message;
        this.iconType = iconType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIconType() {
        return iconType;
    }

    public void setIconType(Integer iconType) {
        this.iconType = iconType;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
