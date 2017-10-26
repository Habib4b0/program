package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GcmItemDetails}.
 * </p>
 *
 * @author
 * @see GcmItemDetails
 * @generated
 */
public class GcmItemDetailsWrapper implements GcmItemDetails,
    ModelWrapper<GcmItemDetails> {
    private GcmItemDetails _gcmItemDetails;

    public GcmItemDetailsWrapper(GcmItemDetails gcmItemDetails) {
        _gcmItemDetails = gcmItemDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return GcmItemDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmItemDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ifpDetailsEndDate", getIfpDetailsEndDate());
        attributes.put("itemStatus", getItemStatus());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("ifpDetailsStartDate", getIfpDetailsStartDate());
        attributes.put("userId", getUserId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("gcmItemDetailsSid", getGcmItemDetailsSid());
        attributes.put("itemIfpDetailsSid", getItemIfpDetailsSid());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("operation", getOperation());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("itemStatusSid", getItemStatusSid());
        attributes.put("itemNo", getItemNo());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("theraputicClass", getTheraputicClass());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date ifpDetailsEndDate = (Date) attributes.get("ifpDetailsEndDate");

        if (ifpDetailsEndDate != null) {
            setIfpDetailsEndDate(ifpDetailsEndDate);
        }

        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date ifpDetailsStartDate = (Date) attributes.get("ifpDetailsStartDate");

        if (ifpDetailsStartDate != null) {
            setIfpDetailsStartDate(ifpDetailsStartDate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        Integer gcmItemDetailsSid = (Integer) attributes.get(
                "gcmItemDetailsSid");

        if (gcmItemDetailsSid != null) {
            setGcmItemDetailsSid(gcmItemDetailsSid);
        }

        Integer itemIfpDetailsSid = (Integer) attributes.get(
                "itemIfpDetailsSid");

        if (itemIfpDetailsSid != null) {
            setItemIfpDetailsSid(itemIfpDetailsSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer itemStatusSid = (Integer) attributes.get("itemStatusSid");

        if (itemStatusSid != null) {
            setItemStatusSid(itemStatusSid);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        String theraputicClass = (String) attributes.get("theraputicClass");

        if (theraputicClass != null) {
            setTheraputicClass(theraputicClass);
        }
    }

    /**
    * Returns the primary key of this gcm item details.
    *
    * @return the primary key of this gcm item details
    */
    @Override
    public int getPrimaryKey() {
        return _gcmItemDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this gcm item details.
    *
    * @param primaryKey the primary key of this gcm item details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _gcmItemDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ifp details end date of this gcm item details.
    *
    * @return the ifp details end date of this gcm item details
    */
    @Override
    public java.util.Date getIfpDetailsEndDate() {
        return _gcmItemDetails.getIfpDetailsEndDate();
    }

    /**
    * Sets the ifp details end date of this gcm item details.
    *
    * @param ifpDetailsEndDate the ifp details end date of this gcm item details
    */
    @Override
    public void setIfpDetailsEndDate(java.util.Date ifpDetailsEndDate) {
        _gcmItemDetails.setIfpDetailsEndDate(ifpDetailsEndDate);
    }

    /**
    * Returns the item status of this gcm item details.
    *
    * @return the item status of this gcm item details
    */
    @Override
    public java.lang.String getItemStatus() {
        return _gcmItemDetails.getItemStatus();
    }

    /**
    * Sets the item status of this gcm item details.
    *
    * @param itemStatus the item status of this gcm item details
    */
    @Override
    public void setItemStatus(java.lang.String itemStatus) {
        _gcmItemDetails.setItemStatus(itemStatus);
    }

    /**
    * Returns the check record of this gcm item details.
    *
    * @return the check record of this gcm item details
    */
    @Override
    public boolean getCheckRecord() {
        return _gcmItemDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this gcm item details is check record.
    *
    * @return <code>true</code> if this gcm item details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _gcmItemDetails.isCheckRecord();
    }

    /**
    * Sets whether this gcm item details is check record.
    *
    * @param checkRecord the check record of this gcm item details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _gcmItemDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the ifp details start date of this gcm item details.
    *
    * @return the ifp details start date of this gcm item details
    */
    @Override
    public java.util.Date getIfpDetailsStartDate() {
        return _gcmItemDetails.getIfpDetailsStartDate();
    }

    /**
    * Sets the ifp details start date of this gcm item details.
    *
    * @param ifpDetailsStartDate the ifp details start date of this gcm item details
    */
    @Override
    public void setIfpDetailsStartDate(java.util.Date ifpDetailsStartDate) {
        _gcmItemDetails.setIfpDetailsStartDate(ifpDetailsStartDate);
    }

    /**
    * Returns the user ID of this gcm item details.
    *
    * @return the user ID of this gcm item details
    */
    @Override
    public int getUserId() {
        return _gcmItemDetails.getUserId();
    }

    /**
    * Sets the user ID of this gcm item details.
    *
    * @param userId the user ID of this gcm item details
    */
    @Override
    public void setUserId(int userId) {
        _gcmItemDetails.setUserId(userId);
    }

    /**
    * Returns the item master sid of this gcm item details.
    *
    * @return the item master sid of this gcm item details
    */
    @Override
    public int getItemMasterSid() {
        return _gcmItemDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this gcm item details.
    *
    * @param itemMasterSid the item master sid of this gcm item details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _gcmItemDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the item end date of this gcm item details.
    *
    * @return the item end date of this gcm item details
    */
    @Override
    public java.util.Date getItemEndDate() {
        return _gcmItemDetails.getItemEndDate();
    }

    /**
    * Sets the item end date of this gcm item details.
    *
    * @param itemEndDate the item end date of this gcm item details
    */
    @Override
    public void setItemEndDate(java.util.Date itemEndDate) {
        _gcmItemDetails.setItemEndDate(itemEndDate);
    }

    /**
    * Returns the gcm item details sid of this gcm item details.
    *
    * @return the gcm item details sid of this gcm item details
    */
    @Override
    public int getGcmItemDetailsSid() {
        return _gcmItemDetails.getGcmItemDetailsSid();
    }

    /**
    * Sets the gcm item details sid of this gcm item details.
    *
    * @param gcmItemDetailsSid the gcm item details sid of this gcm item details
    */
    @Override
    public void setGcmItemDetailsSid(int gcmItemDetailsSid) {
        _gcmItemDetails.setGcmItemDetailsSid(gcmItemDetailsSid);
    }

    /**
    * Returns the item ifp details sid of this gcm item details.
    *
    * @return the item ifp details sid of this gcm item details
    */
    @Override
    public int getItemIfpDetailsSid() {
        return _gcmItemDetails.getItemIfpDetailsSid();
    }

    /**
    * Sets the item ifp details sid of this gcm item details.
    *
    * @param itemIfpDetailsSid the item ifp details sid of this gcm item details
    */
    @Override
    public void setItemIfpDetailsSid(int itemIfpDetailsSid) {
        _gcmItemDetails.setItemIfpDetailsSid(itemIfpDetailsSid);
    }

    /**
    * Returns the item ID of this gcm item details.
    *
    * @return the item ID of this gcm item details
    */
    @Override
    public java.lang.String getItemId() {
        return _gcmItemDetails.getItemId();
    }

    /**
    * Sets the item ID of this gcm item details.
    *
    * @param itemId the item ID of this gcm item details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _gcmItemDetails.setItemId(itemId);
    }

    /**
    * Returns the brand name of this gcm item details.
    *
    * @return the brand name of this gcm item details
    */
    @Override
    public java.lang.String getBrandName() {
        return _gcmItemDetails.getBrandName();
    }

    /**
    * Sets the brand name of this gcm item details.
    *
    * @param brandName the brand name of this gcm item details
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _gcmItemDetails.setBrandName(brandName);
    }

    /**
    * Returns the modified date of this gcm item details.
    *
    * @return the modified date of this gcm item details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _gcmItemDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this gcm item details.
    *
    * @param modifiedDate the modified date of this gcm item details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _gcmItemDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the created date of this gcm item details.
    *
    * @return the created date of this gcm item details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _gcmItemDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this gcm item details.
    *
    * @param createdDate the created date of this gcm item details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _gcmItemDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this gcm item details.
    *
    * @return the created by of this gcm item details
    */
    @Override
    public int getCreatedBy() {
        return _gcmItemDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this gcm item details.
    *
    * @param createdBy the created by of this gcm item details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _gcmItemDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the item start date of this gcm item details.
    *
    * @return the item start date of this gcm item details
    */
    @Override
    public java.util.Date getItemStartDate() {
        return _gcmItemDetails.getItemStartDate();
    }

    /**
    * Sets the item start date of this gcm item details.
    *
    * @param itemStartDate the item start date of this gcm item details
    */
    @Override
    public void setItemStartDate(java.util.Date itemStartDate) {
        _gcmItemDetails.setItemStartDate(itemStartDate);
    }

    /**
    * Returns the session ID of this gcm item details.
    *
    * @return the session ID of this gcm item details
    */
    @Override
    public java.lang.String getSessionId() {
        return _gcmItemDetails.getSessionId();
    }

    /**
    * Sets the session ID of this gcm item details.
    *
    * @param sessionId the session ID of this gcm item details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _gcmItemDetails.setSessionId(sessionId);
    }

    /**
    * Returns the item name of this gcm item details.
    *
    * @return the item name of this gcm item details
    */
    @Override
    public java.lang.String getItemName() {
        return _gcmItemDetails.getItemName();
    }

    /**
    * Sets the item name of this gcm item details.
    *
    * @param itemName the item name of this gcm item details
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _gcmItemDetails.setItemName(itemName);
    }

    /**
    * Returns the operation of this gcm item details.
    *
    * @return the operation of this gcm item details
    */
    @Override
    public java.lang.String getOperation() {
        return _gcmItemDetails.getOperation();
    }

    /**
    * Sets the operation of this gcm item details.
    *
    * @param operation the operation of this gcm item details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _gcmItemDetails.setOperation(operation);
    }

    /**
    * Returns the modified by of this gcm item details.
    *
    * @return the modified by of this gcm item details
    */
    @Override
    public int getModifiedBy() {
        return _gcmItemDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this gcm item details.
    *
    * @param modifiedBy the modified by of this gcm item details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _gcmItemDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this gcm item details.
    *
    * @return the inbound status of this gcm item details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _gcmItemDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this gcm item details.
    *
    * @param inboundStatus the inbound status of this gcm item details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _gcmItemDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the item status sid of this gcm item details.
    *
    * @return the item status sid of this gcm item details
    */
    @Override
    public int getItemStatusSid() {
        return _gcmItemDetails.getItemStatusSid();
    }

    /**
    * Sets the item status sid of this gcm item details.
    *
    * @param itemStatusSid the item status sid of this gcm item details
    */
    @Override
    public void setItemStatusSid(int itemStatusSid) {
        _gcmItemDetails.setItemStatusSid(itemStatusSid);
    }

    /**
    * Returns the item no of this gcm item details.
    *
    * @return the item no of this gcm item details
    */
    @Override
    public java.lang.String getItemNo() {
        return _gcmItemDetails.getItemNo();
    }

    /**
    * Sets the item no of this gcm item details.
    *
    * @param itemNo the item no of this gcm item details
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _gcmItemDetails.setItemNo(itemNo);
    }

    /**
    * Returns the ifp model sid of this gcm item details.
    *
    * @return the ifp model sid of this gcm item details
    */
    @Override
    public int getIfpModelSid() {
        return _gcmItemDetails.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this gcm item details.
    *
    * @param ifpModelSid the ifp model sid of this gcm item details
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _gcmItemDetails.setIfpModelSid(ifpModelSid);
    }

    /**
    * Returns the theraputic class of this gcm item details.
    *
    * @return the theraputic class of this gcm item details
    */
    @Override
    public java.lang.String getTheraputicClass() {
        return _gcmItemDetails.getTheraputicClass();
    }

    /**
    * Sets the theraputic class of this gcm item details.
    *
    * @param theraputicClass the theraputic class of this gcm item details
    */
    @Override
    public void setTheraputicClass(java.lang.String theraputicClass) {
        _gcmItemDetails.setTheraputicClass(theraputicClass);
    }

    @Override
    public boolean isNew() {
        return _gcmItemDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _gcmItemDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _gcmItemDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _gcmItemDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _gcmItemDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _gcmItemDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _gcmItemDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _gcmItemDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _gcmItemDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _gcmItemDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _gcmItemDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new GcmItemDetailsWrapper((GcmItemDetails) _gcmItemDetails.clone());
    }

    @Override
    public int compareTo(GcmItemDetails gcmItemDetails) {
        return _gcmItemDetails.compareTo(gcmItemDetails);
    }

    @Override
    public int hashCode() {
        return _gcmItemDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<GcmItemDetails> toCacheModel() {
        return _gcmItemDetails.toCacheModel();
    }

    @Override
    public GcmItemDetails toEscapedModel() {
        return new GcmItemDetailsWrapper(_gcmItemDetails.toEscapedModel());
    }

    @Override
    public GcmItemDetails toUnescapedModel() {
        return new GcmItemDetailsWrapper(_gcmItemDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _gcmItemDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _gcmItemDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _gcmItemDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof GcmItemDetailsWrapper)) {
            return false;
        }

        GcmItemDetailsWrapper gcmItemDetailsWrapper = (GcmItemDetailsWrapper) obj;

        if (Validator.equals(_gcmItemDetails,
                    gcmItemDetailsWrapper._gcmItemDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public GcmItemDetails getWrappedGcmItemDetails() {
        return _gcmItemDetails;
    }

    @Override
    public GcmItemDetails getWrappedModel() {
        return _gcmItemDetails;
    }

    @Override
    public void resetOriginalValues() {
        _gcmItemDetails.resetOriginalValues();
    }
}
