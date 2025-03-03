package com.blog.entity;

import com.blog.context.RequestContext;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class ObjectEntity {
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;
    private String deletedFlag;

    public void setCreateInfo(){
        User userInfo = RequestContext.getRequestInfo().getUserInfo();
        this.createdBy = userInfo.getId();
        this.updatedBy = userInfo.getId();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.deletedFlag = "N";
    }

    public void setUpdateInfo(){
        User userInfo = RequestContext.getRequestInfo().getUserInfo();
        this.updatedBy = userInfo.getId();
        this.updatedDate = LocalDateTime.now();
    }

    public void setCreateUserInfo(String userId){
        this.createdBy = userId;
        this.updatedBy = userId;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.deletedFlag = "N";
    }
}
