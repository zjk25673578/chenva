package com.pojo;

/**
 * 枚举类
 * 消息提醒信息
 */
public enum MessageEnum {

    INVALIDATE_USERNAME_PASSWORD(-400, "用户名或密码错误"),
    USERNAME_DUPLICATE(-11, "用户名已经存在 !"),
    ADMIN_NOT_DELETE(-10, "不能删除超级管理员"),
    ADMIN_DUPLICATE(-9, "超级管理员有且必须只能有一个"),
    ADMIN_ERROR(-8, "超级管理员的信息只能由本人从个人信息页面修改"),
    USER_ACCOUNT_FORBIDDEN(-7, "账号已被禁用"),
    WRONG_CODE(-6, "验证码错误"),
    NOT_BIND(-5, "未登录状态，无法操作"),
    VALID_CODE_DEPRECATED(-4, "验证码已经失效, 请刷新页面"),
    INCORRECT_PARAM(-3, "参数不正确"),
    NULL_RESULT(-2, "没有数据"),
    FAILURE(-1, "操作失败");

    private int code;
    private String message;

    MessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return message;
    }

    /**
     * 根据代码获取消息提醒枚举类
     *
     * @param index
     * @return
     */
    public static MessageEnum codeOf(int index) {
        for (MessageEnum messageEnum : values()) {
            if (messageEnum.code() == index) {
                return messageEnum;
            }
        }
        return null;
    }
}
