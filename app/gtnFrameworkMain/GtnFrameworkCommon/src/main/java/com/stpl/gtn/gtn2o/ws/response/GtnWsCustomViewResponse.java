/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnWsCustomViewResponse implements GtnWSResponseData {

    public GtnWsCustomViewResponse() {
        super();
    }
    private GtnWsRecordBean mainNode;
    private String customViewName;
    private String customViewDescription;
    private String customViewType;
    private int customerRelationshipSid;
    private int productRelationshipSid;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private int cvSysId;
    private int userId;
    private List<GtnWsRecordBean> cvTreeNodeList;
    private boolean success;
    private String messageType;
    private String message;

    public GtnWsRecordBean getMainNode() {
        return mainNode;
    }

    public void setMainNode(GtnWsRecordBean mainNode) {
        this.mainNode = mainNode;
    }

    public String getCustomViewName() {
        return customViewName;
    }

    public void setCustomViewName(String customViewName) {
        this.customViewName = customViewName;
    }

    public String getCustomViewDescription() {
        return customViewDescription;
    }

    public void setCustomViewDescription(String customViewDescription) {
        this.customViewDescription = customViewDescription;
    }

    public String getCustomViewType() {
        return customViewType;
    }

    public void setCustomViewType(String customViewType) {
        this.customViewType = customViewType;
    }

    public int getCustomerRelationshipSid() {
        return customerRelationshipSid;
    }

    public void setCustomerRelationshipSid(int customerRelationshipSid) {
        this.customerRelationshipSid = customerRelationshipSid;
    }

    public int getProductRelationshipSid() {
        return productRelationshipSid;
    }

    public void setProductRelationshipSid(int productRelationshipSid) {
        this.productRelationshipSid = productRelationshipSid;
    }

    public Date getCreatedDate() {
        return createdDate == null ? null : (Date) createdDate.clone();
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
    }

    public Date getModifiedDate() {
        return modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getCvSysId() {
        return cvSysId;
    }

    public void setCvSysId(int cvSysId) {
        this.cvSysId = cvSysId;
    }

    public List<GtnWsRecordBean> getCvTreeNodeList() {
        return cvTreeNodeList == null ? null : Collections.unmodifiableList(cvTreeNodeList);
    }

    public void setCvTreeNodeList(List<GtnWsRecordBean> cvTreeNodeList) {
        this.cvTreeNodeList = cvTreeNodeList == null ? null : Collections.unmodifiableList(cvTreeNodeList);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}