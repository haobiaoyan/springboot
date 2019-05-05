package com.hb.springboot.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OperationLog implements Serializable {

    private String id;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public void setCreateTime(Date createTime) {
        if (createTime == null) {
            this.createTime = null;
        } else {
            this.createTime = (Date) createTime.clone();
        }
    }

    public Date getCreateTime() {
        if (createTime == null) {
            return null;
        }
        return (Date) createTime.clone();
    }

    /**
     * 日志等级
     */
    private Integer level;
    /**
     * 被操作的对象
     */
    private String operationUnit;
    /***
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private String args;
    /**
     * 操作人id
     */
    private String userId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 日志描述
     */
    private String describe;
    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 方法运行时间
     */
    private Long runTime;
    /**
     * 方法返回值
     */
    private String returnValue;

    @Override
    public String toString() {
        return "OperationLog{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", level=" + level +
                ", operationUnit='" + operationUnit + '\'' +
                ", method='" + method + '\'' +
                ", args='" + args + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", describe='" + describe + '\'' +
                ", operationType='" + operationType + '\'' +
                ", runTime=" + runTime +
                ", returnValue='" + returnValue + '\'' +
                '}';
    }
}
