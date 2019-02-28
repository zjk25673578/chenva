package com.util.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class ModelParent {

    @TableId(type = IdType.AUTO)
    private Integer ids;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private Integer creator;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 修改日期
     */
    private Date updateTime;

    /**
     * 修改人id
     */
    private Integer updator;

    /**
     * 修改人名称
     */
    private String updateName;

    /**
     * 删除标志(0: 有效, 1: 已删除)
     */
    private Integer status;

    public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
