package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AhTempDetails}.
 * </p>
 *
 * @author
 * @see AhTempDetails
 * @generated
 */
public class AhTempDetailsWrapper implements AhTempDetails,
    ModelWrapper<AhTempDetails> {
    private AhTempDetails _ahTempDetails;

    public AhTempDetailsWrapper(AhTempDetails ahTempDetails) {
        _ahTempDetails = ahTempDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return AhTempDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AhTempDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractHolder", getContractHolder());
        attributes.put("userId", getUserId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("companyName", getCompanyName());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("componentName", getComponentName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("screenName", getScreenName());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("itemIdentifierType", getItemIdentifierType());
        attributes.put("componentNo", getComponentNo());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("companySid", getCompanySid());
        attributes.put("itemNo", getItemNo());
        attributes.put("componentType", getComponentType());
        attributes.put("theraputicClass", getTheraputicClass());
        attributes.put("componentMasterSid", getComponentMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String contractHolder = (String) attributes.get("contractHolder");

        if (contractHolder != null) {
            setContractHolder(contractHolder);
        }

        String userId = (String) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        String componentName = (String) attributes.get("componentName");

        if (componentName != null) {
            setComponentName(componentName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String itemIdentifierType = (String) attributes.get(
                "itemIdentifierType");

        if (itemIdentifierType != null) {
            setItemIdentifierType(itemIdentifierType);
        }

        String componentNo = (String) attributes.get("componentNo");

        if (componentNo != null) {
            setComponentNo(componentNo);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        Integer companySid = (Integer) attributes.get("companySid");

        if (companySid != null) {
            setCompanySid(companySid);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String componentType = (String) attributes.get("componentType");

        if (componentType != null) {
            setComponentType(componentType);
        }

        String theraputicClass = (String) attributes.get("theraputicClass");

        if (theraputicClass != null) {
            setTheraputicClass(theraputicClass);
        }

        Integer componentMasterSid = (Integer) attributes.get(
                "componentMasterSid");

        if (componentMasterSid != null) {
            setComponentMasterSid(componentMasterSid);
        }
    }

    /**
    * Returns the primary key of this ah temp details.
    *
    * @return the primary key of this ah temp details
    */
    @Override
    public int getPrimaryKey() {
        return _ahTempDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ah temp details.
    *
    * @param primaryKey the primary key of this ah temp details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ahTempDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the check record of this ah temp details.
    *
    * @return the check record of this ah temp details
    */
    @Override
    public boolean getCheckRecord() {
        return _ahTempDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ah temp details is check record.
    *
    * @return <code>true</code> if this ah temp details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ahTempDetails.isCheckRecord();
    }

    /**
    * Sets whether this ah temp details is check record.
    *
    * @param checkRecord the check record of this ah temp details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ahTempDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the contract holder of this ah temp details.
    *
    * @return the contract holder of this ah temp details
    */
    @Override
    public java.lang.String getContractHolder() {
        return _ahTempDetails.getContractHolder();
    }

    /**
    * Sets the contract holder of this ah temp details.
    *
    * @param contractHolder the contract holder of this ah temp details
    */
    @Override
    public void setContractHolder(java.lang.String contractHolder) {
        _ahTempDetails.setContractHolder(contractHolder);
    }

    /**
    * Returns the user ID of this ah temp details.
    *
    * @return the user ID of this ah temp details
    */
    @Override
    public java.lang.String getUserId() {
        return _ahTempDetails.getUserId();
    }

    /**
    * Sets the user ID of this ah temp details.
    *
    * @param userId the user ID of this ah temp details
    */
    @Override
    public void setUserId(java.lang.String userId) {
        _ahTempDetails.setUserId(userId);
    }

    /**
    * Returns the item master sid of this ah temp details.
    *
    * @return the item master sid of this ah temp details
    */
    @Override
    public int getItemMasterSid() {
        return _ahTempDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this ah temp details.
    *
    * @param itemMasterSid the item master sid of this ah temp details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _ahTempDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the business unit no of this ah temp details.
    *
    * @return the business unit no of this ah temp details
    */
    @Override
    public java.lang.String getBusinessUnitNo() {
        return _ahTempDetails.getBusinessUnitNo();
    }

    /**
    * Sets the business unit no of this ah temp details.
    *
    * @param businessUnitNo the business unit no of this ah temp details
    */
    @Override
    public void setBusinessUnitNo(java.lang.String businessUnitNo) {
        _ahTempDetails.setBusinessUnitNo(businessUnitNo);
    }

    /**
    * Returns the company name of this ah temp details.
    *
    * @return the company name of this ah temp details
    */
    @Override
    public java.lang.String getCompanyName() {
        return _ahTempDetails.getCompanyName();
    }

    /**
    * Sets the company name of this ah temp details.
    *
    * @param companyName the company name of this ah temp details
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _ahTempDetails.setCompanyName(companyName);
    }

    /**
    * Returns the item ID of this ah temp details.
    *
    * @return the item ID of this ah temp details
    */
    @Override
    public java.lang.String getItemId() {
        return _ahTempDetails.getItemId();
    }

    /**
    * Sets the item ID of this ah temp details.
    *
    * @param itemId the item ID of this ah temp details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ahTempDetails.setItemId(itemId);
    }

    /**
    * Returns the brand name of this ah temp details.
    *
    * @return the brand name of this ah temp details
    */
    @Override
    public java.lang.String getBrandName() {
        return _ahTempDetails.getBrandName();
    }

    /**
    * Sets the brand name of this ah temp details.
    *
    * @param brandName the brand name of this ah temp details
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _ahTempDetails.setBrandName(brandName);
    }

    /**
    * Returns the component name of this ah temp details.
    *
    * @return the component name of this ah temp details
    */
    @Override
    public java.lang.String getComponentName() {
        return _ahTempDetails.getComponentName();
    }

    /**
    * Sets the component name of this ah temp details.
    *
    * @param componentName the component name of this ah temp details
    */
    @Override
    public void setComponentName(java.lang.String componentName) {
        _ahTempDetails.setComponentName(componentName);
    }

    /**
    * Returns the created date of this ah temp details.
    *
    * @return the created date of this ah temp details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ahTempDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this ah temp details.
    *
    * @param createdDate the created date of this ah temp details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ahTempDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ah temp details.
    *
    * @return the created by of this ah temp details
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ahTempDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this ah temp details.
    *
    * @param createdBy the created by of this ah temp details
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ahTempDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the screen name of this ah temp details.
    *
    * @return the screen name of this ah temp details
    */
    @Override
    public java.lang.String getScreenName() {
        return _ahTempDetails.getScreenName();
    }

    /**
    * Sets the screen name of this ah temp details.
    *
    * @param screenName the screen name of this ah temp details
    */
    @Override
    public void setScreenName(java.lang.String screenName) {
        _ahTempDetails.setScreenName(screenName);
    }

    /**
    * Returns the business unit name of this ah temp details.
    *
    * @return the business unit name of this ah temp details
    */
    @Override
    public java.lang.String getBusinessUnitName() {
        return _ahTempDetails.getBusinessUnitName();
    }

    /**
    * Sets the business unit name of this ah temp details.
    *
    * @param businessUnitName the business unit name of this ah temp details
    */
    @Override
    public void setBusinessUnitName(java.lang.String businessUnitName) {
        _ahTempDetails.setBusinessUnitName(businessUnitName);
    }

    /**
    * Returns the company no of this ah temp details.
    *
    * @return the company no of this ah temp details
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _ahTempDetails.getCompanyNo();
    }

    /**
    * Sets the company no of this ah temp details.
    *
    * @param companyNo the company no of this ah temp details
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _ahTempDetails.setCompanyNo(companyNo);
    }

    /**
    * Returns the item identifier type of this ah temp details.
    *
    * @return the item identifier type of this ah temp details
    */
    @Override
    public java.lang.String getItemIdentifierType() {
        return _ahTempDetails.getItemIdentifierType();
    }

    /**
    * Sets the item identifier type of this ah temp details.
    *
    * @param itemIdentifierType the item identifier type of this ah temp details
    */
    @Override
    public void setItemIdentifierType(java.lang.String itemIdentifierType) {
        _ahTempDetails.setItemIdentifierType(itemIdentifierType);
    }

    /**
    * Returns the component no of this ah temp details.
    *
    * @return the component no of this ah temp details
    */
    @Override
    public java.lang.String getComponentNo() {
        return _ahTempDetails.getComponentNo();
    }

    /**
    * Sets the component no of this ah temp details.
    *
    * @param componentNo the component no of this ah temp details
    */
    @Override
    public void setComponentNo(java.lang.String componentNo) {
        _ahTempDetails.setComponentNo(componentNo);
    }

    /**
    * Returns the session ID of this ah temp details.
    *
    * @return the session ID of this ah temp details
    */
    @Override
    public java.lang.String getSessionId() {
        return _ahTempDetails.getSessionId();
    }

    /**
    * Sets the session ID of this ah temp details.
    *
    * @param sessionId the session ID of this ah temp details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _ahTempDetails.setSessionId(sessionId);
    }

    /**
    * Returns the item name of this ah temp details.
    *
    * @return the item name of this ah temp details
    */
    @Override
    public java.lang.String getItemName() {
        return _ahTempDetails.getItemName();
    }

    /**
    * Sets the item name of this ah temp details.
    *
    * @param itemName the item name of this ah temp details
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _ahTempDetails.setItemName(itemName);
    }

    /**
    * Returns the item identifier of this ah temp details.
    *
    * @return the item identifier of this ah temp details
    */
    @Override
    public java.lang.String getItemIdentifier() {
        return _ahTempDetails.getItemIdentifier();
    }

    /**
    * Sets the item identifier of this ah temp details.
    *
    * @param itemIdentifier the item identifier of this ah temp details
    */
    @Override
    public void setItemIdentifier(java.lang.String itemIdentifier) {
        _ahTempDetails.setItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the company sid of this ah temp details.
    *
    * @return the company sid of this ah temp details
    */
    @Override
    public int getCompanySid() {
        return _ahTempDetails.getCompanySid();
    }

    /**
    * Sets the company sid of this ah temp details.
    *
    * @param companySid the company sid of this ah temp details
    */
    @Override
    public void setCompanySid(int companySid) {
        _ahTempDetails.setCompanySid(companySid);
    }

    /**
    * Returns the item no of this ah temp details.
    *
    * @return the item no of this ah temp details
    */
    @Override
    public java.lang.String getItemNo() {
        return _ahTempDetails.getItemNo();
    }

    /**
    * Sets the item no of this ah temp details.
    *
    * @param itemNo the item no of this ah temp details
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _ahTempDetails.setItemNo(itemNo);
    }

    /**
    * Returns the component type of this ah temp details.
    *
    * @return the component type of this ah temp details
    */
    @Override
    public java.lang.String getComponentType() {
        return _ahTempDetails.getComponentType();
    }

    /**
    * Sets the component type of this ah temp details.
    *
    * @param componentType the component type of this ah temp details
    */
    @Override
    public void setComponentType(java.lang.String componentType) {
        _ahTempDetails.setComponentType(componentType);
    }

    /**
    * Returns the theraputic class of this ah temp details.
    *
    * @return the theraputic class of this ah temp details
    */
    @Override
    public java.lang.String getTheraputicClass() {
        return _ahTempDetails.getTheraputicClass();
    }

    /**
    * Sets the theraputic class of this ah temp details.
    *
    * @param theraputicClass the theraputic class of this ah temp details
    */
    @Override
    public void setTheraputicClass(java.lang.String theraputicClass) {
        _ahTempDetails.setTheraputicClass(theraputicClass);
    }

    /**
    * Returns the component master sid of this ah temp details.
    *
    * @return the component master sid of this ah temp details
    */
    @Override
    public int getComponentMasterSid() {
        return _ahTempDetails.getComponentMasterSid();
    }

    /**
    * Sets the component master sid of this ah temp details.
    *
    * @param componentMasterSid the component master sid of this ah temp details
    */
    @Override
    public void setComponentMasterSid(int componentMasterSid) {
        _ahTempDetails.setComponentMasterSid(componentMasterSid);
    }

    @Override
    public boolean isNew() {
        return _ahTempDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ahTempDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ahTempDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ahTempDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ahTempDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ahTempDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ahTempDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ahTempDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ahTempDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ahTempDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ahTempDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AhTempDetailsWrapper((AhTempDetails) _ahTempDetails.clone());
    }

    @Override
    public int compareTo(AhTempDetails ahTempDetails) {
        return _ahTempDetails.compareTo(ahTempDetails);
    }

    @Override
    public int hashCode() {
        return _ahTempDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AhTempDetails> toCacheModel() {
        return _ahTempDetails.toCacheModel();
    }

    @Override
    public AhTempDetails toEscapedModel() {
        return new AhTempDetailsWrapper(_ahTempDetails.toEscapedModel());
    }

    @Override
    public AhTempDetails toUnescapedModel() {
        return new AhTempDetailsWrapper(_ahTempDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ahTempDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ahTempDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ahTempDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AhTempDetailsWrapper)) {
            return false;
        }

        AhTempDetailsWrapper ahTempDetailsWrapper = (AhTempDetailsWrapper) obj;

        if (Validator.equals(_ahTempDetails, ahTempDetailsWrapper._ahTempDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AhTempDetails getWrappedAhTempDetails() {
        return _ahTempDetails;
    }

    @Override
    public AhTempDetails getWrappedModel() {
        return _ahTempDetails;
    }

    @Override
    public void resetOriginalValues() {
        _ahTempDetails.resetOriginalValues();
    }
}
