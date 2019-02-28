package com.pojo;

import java.util.List;
import java.util.Map;

/**
 * 基于Layui第三方控件elemTree数据模型
 */
public class TreeModel {
    private Integer id;
    private String label;
    private String icon;
    private String url;
    private Integer parentId;
    private boolean spread; // 是否展开
    private boolean disabled; // 是否禁用
    private boolean checked; // 是否选中
    private List<TreeModel> children; // 子节点数据
    private Map<String, Object> attr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }

    public Map<String, Object> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, Object> attr) {
        this.attr = attr;
    }
}
