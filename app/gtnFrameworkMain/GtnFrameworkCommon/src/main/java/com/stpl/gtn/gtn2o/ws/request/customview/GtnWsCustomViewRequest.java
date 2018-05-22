/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.customview;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnWsCustomViewRequest implements GtnWSRequestData {
    public  GtnWsCustomViewRequest() {
        super();
    }
    private GtnWsRecordBean mainNode;
    private int customerRelVersionNo;
    private String customViewName;
    private String modifiedBy;
    private String customViewDescription;
    private int productRelationshipSid;
    private int customerRelationshipSid;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String customViewType;
    private int cvSysId;
    private int productRelVersionNo;
    private int userId;
    
    private List<GtnWsRecordBean> cvTreeNodeList;

    public String getCustomViewDescription() {
        return customViewDescription;
    }

    public void setCustomViewDescription(String customViewDescription) {
        this.customViewDescription = customViewDescription;
    }

    public String getCustomViewName() {
        return customViewName;
    }

    public void setCustomViewName(String customViewName) {
        this.customViewName = customViewName;
    }
    
    public GtnWsRecordBean getMainNode() {
        return mainNode;
    }

    public void setMainNode(GtnWsRecordBean mainNode) {
        this.mainNode = mainNode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getCustomerRelationshipSid() {
        return customerRelationshipSid;
    }

    public void setCustomerRelationshipSid(int customerRelationshipSid) {
        this.customerRelationshipSid = customerRelationshipSid;
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
    
    public int getProductRelationshipSid() {
        return productRelationshipSid;
    }

    public void setProductRelationshipSid(int productRelationshipSid) {
        this.productRelationshipSid = productRelationshipSid;
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

    public List<GtnWsRecordBean> getCvTreeNodeList() {
        return cvTreeNodeList == null ? null : Collections.unmodifiableList(cvTreeNodeList);
    }

    public void setCvTreeNodeList(List<GtnWsRecordBean> cvTreeNodeList) {
        this.cvTreeNodeList = cvTreeNodeList == null ? null : Collections.unmodifiableList(cvTreeNodeList);
    }

    public int getProductRelVersionNo() {
        return productRelVersionNo;
    }

    public void setProductRelVersionNo(int productRelVersionNo) {
        this.productRelVersionNo = productRelVersionNo;
    }

    public int getCustomerRelVersionNo() {
        return customerRelVersionNo;
    }

    public void setCustomerRelVersionNo(int customerRelVersionNo) {
        this.customerRelVersionNo = customerRelVersionNo;
    }

    public String getCustomViewType() {
        return customViewType;
    }

    public void setCustomViewType(String customViewType) {
        this.customViewType = customViewType;
    }
    
    public int getCvSysId() {
        return cvSysId;
    }

    public void setCvSysId(int cvSysId) {
        this.cvSysId = cvSysId;
    }
    
}
