package org.chy.platform.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("t_app_service")
public class AppService implements Serializable {

    @TableId
    private String id;

    private String serviceName;

    private String price;

    private String agreementId;

    private String keywords;

    private Integer click;

    private Integer classify;

    private Integer type;

    private Integer isHot;

    private Integer sort;

    private Integer serviceStatus;

    private Integer auditStatus;

    private String note;

    private Integer deleteFlag;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AppService{" +
                "id='" + id + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", price='" + price + '\'' +
                ", agreementId='" + agreementId + '\'' +
                ", keywords='" + keywords + '\'' +
                ", click=" + click +
                ", classify=" + classify +
                ", type=" + type +
                ", isHot=" + isHot +
                ", sort=" + sort +
                ", serviceStatus=" + serviceStatus +
                ", auditStatus=" + auditStatus +
                ", note='" + note + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", updateName='" + updateName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}