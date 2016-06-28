package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GcmContractDetails}.
 * </p>
 *
 * @author
 * @see GcmContractDetails
 * @generated
 */
public class GcmContractDetailsWrapper implements GcmContractDetails,
    ModelWrapper<GcmContractDetails> {
    private GcmContractDetails _gcmContractDetails;

    public GcmContractDetailsWrapper(GcmContractDetails gcmContractDetails) {
        _gcmContractDetails = gcmContractDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return GcmContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmContractDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("paymentMethod", getPaymentMethod());
        attributes.put("userId", getUserId());
        attributes.put("endDate", getEndDate());
        attributes.put("paymentFrequency", getPaymentFrequency());
        attributes.put("gcmContractDetailsSid", getGcmContractDetailsSid());
        attributes.put("componentId", getComponentId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("componentName", getComponentName());
        attributes.put("rsCalendar", getRsCalendar());
        attributes.put("fileName", getFileName());
        attributes.put("startDate", getStartDate());
        attributes.put("planLevel", getPlanLevel());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("componentNo", getComponentNo());
        attributes.put("programType", getProgramType());
        attributes.put("sessionId", getSessionId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("componentStatus", getComponentStatus());
        attributes.put("componentType", getComponentType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String paymentMethod = (String) attributes.get("paymentMethod");

        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String paymentFrequency = (String) attributes.get("paymentFrequency");

        if (paymentFrequency != null) {
            setPaymentFrequency(paymentFrequency);
        }

        Integer gcmContractDetailsSid = (Integer) attributes.get(
                "gcmContractDetailsSid");

        if (gcmContractDetailsSid != null) {
            setGcmContractDetailsSid(gcmContractDetailsSid);
        }

        String componentId = (String) attributes.get("componentId");

        if (componentId != null) {
            setComponentId(componentId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String componentName = (String) attributes.get("componentName");

        if (componentName != null) {
            setComponentName(componentName);
        }

        String rsCalendar = (String) attributes.get("rsCalendar");

        if (rsCalendar != null) {
            setRsCalendar(rsCalendar);
        }

        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String planLevel = (String) attributes.get("planLevel");

        if (planLevel != null) {
            setPlanLevel(planLevel);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String componentNo = (String) attributes.get("componentNo");

        if (componentNo != null) {
            setComponentNo(componentNo);
        }

        String programType = (String) attributes.get("programType");

        if (programType != null) {
            setProgramType(programType);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String componentStatus = (String) attributes.get("componentStatus");

        if (componentStatus != null) {
            setComponentStatus(componentStatus);
        }

        String componentType = (String) attributes.get("componentType");

        if (componentType != null) {
            setComponentType(componentType);
        }
    }

    /**
    * Returns the primary key of this gcm contract details.
    *
    * @return the primary key of this gcm contract details
    */
    @Override
    public int getPrimaryKey() {
        return _gcmContractDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this gcm contract details.
    *
    * @param primaryKey the primary key of this gcm contract details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _gcmContractDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the payment method of this gcm contract details.
    *
    * @return the payment method of this gcm contract details
    */
    @Override
    public java.lang.String getPaymentMethod() {
        return _gcmContractDetails.getPaymentMethod();
    }

    /**
    * Sets the payment method of this gcm contract details.
    *
    * @param paymentMethod the payment method of this gcm contract details
    */
    @Override
    public void setPaymentMethod(java.lang.String paymentMethod) {
        _gcmContractDetails.setPaymentMethod(paymentMethod);
    }

    /**
    * Returns the user ID of this gcm contract details.
    *
    * @return the user ID of this gcm contract details
    */
    @Override
    public int getUserId() {
        return _gcmContractDetails.getUserId();
    }

    /**
    * Sets the user ID of this gcm contract details.
    *
    * @param userId the user ID of this gcm contract details
    */
    @Override
    public void setUserId(int userId) {
        _gcmContractDetails.setUserId(userId);
    }

    /**
    * Returns the end date of this gcm contract details.
    *
    * @return the end date of this gcm contract details
    */
    @Override
    public java.util.Date getEndDate() {
        return _gcmContractDetails.getEndDate();
    }

    /**
    * Sets the end date of this gcm contract details.
    *
    * @param endDate the end date of this gcm contract details
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _gcmContractDetails.setEndDate(endDate);
    }

    /**
    * Returns the payment frequency of this gcm contract details.
    *
    * @return the payment frequency of this gcm contract details
    */
    @Override
    public java.lang.String getPaymentFrequency() {
        return _gcmContractDetails.getPaymentFrequency();
    }

    /**
    * Sets the payment frequency of this gcm contract details.
    *
    * @param paymentFrequency the payment frequency of this gcm contract details
    */
    @Override
    public void setPaymentFrequency(java.lang.String paymentFrequency) {
        _gcmContractDetails.setPaymentFrequency(paymentFrequency);
    }

    /**
    * Returns the gcm contract details sid of this gcm contract details.
    *
    * @return the gcm contract details sid of this gcm contract details
    */
    @Override
    public int getGcmContractDetailsSid() {
        return _gcmContractDetails.getGcmContractDetailsSid();
    }

    /**
    * Sets the gcm contract details sid of this gcm contract details.
    *
    * @param gcmContractDetailsSid the gcm contract details sid of this gcm contract details
    */
    @Override
    public void setGcmContractDetailsSid(int gcmContractDetailsSid) {
        _gcmContractDetails.setGcmContractDetailsSid(gcmContractDetailsSid);
    }

    /**
    * Returns the component ID of this gcm contract details.
    *
    * @return the component ID of this gcm contract details
    */
    @Override
    public java.lang.String getComponentId() {
        return _gcmContractDetails.getComponentId();
    }

    /**
    * Sets the component ID of this gcm contract details.
    *
    * @param componentId the component ID of this gcm contract details
    */
    @Override
    public void setComponentId(java.lang.String componentId) {
        _gcmContractDetails.setComponentId(componentId);
    }

    /**
    * Returns the modified date of this gcm contract details.
    *
    * @return the modified date of this gcm contract details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _gcmContractDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this gcm contract details.
    *
    * @param modifiedDate the modified date of this gcm contract details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _gcmContractDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the component name of this gcm contract details.
    *
    * @return the component name of this gcm contract details
    */
    @Override
    public java.lang.String getComponentName() {
        return _gcmContractDetails.getComponentName();
    }

    /**
    * Sets the component name of this gcm contract details.
    *
    * @param componentName the component name of this gcm contract details
    */
    @Override
    public void setComponentName(java.lang.String componentName) {
        _gcmContractDetails.setComponentName(componentName);
    }

    /**
    * Returns the rs calendar of this gcm contract details.
    *
    * @return the rs calendar of this gcm contract details
    */
    @Override
    public java.lang.String getRsCalendar() {
        return _gcmContractDetails.getRsCalendar();
    }

    /**
    * Sets the rs calendar of this gcm contract details.
    *
    * @param rsCalendar the rs calendar of this gcm contract details
    */
    @Override
    public void setRsCalendar(java.lang.String rsCalendar) {
        _gcmContractDetails.setRsCalendar(rsCalendar);
    }

    /**
    * Returns the file name of this gcm contract details.
    *
    * @return the file name of this gcm contract details
    */
    @Override
    public java.lang.String getFileName() {
        return _gcmContractDetails.getFileName();
    }

    /**
    * Sets the file name of this gcm contract details.
    *
    * @param fileName the file name of this gcm contract details
    */
    @Override
    public void setFileName(java.lang.String fileName) {
        _gcmContractDetails.setFileName(fileName);
    }

    /**
    * Returns the start date of this gcm contract details.
    *
    * @return the start date of this gcm contract details
    */
    @Override
    public java.util.Date getStartDate() {
        return _gcmContractDetails.getStartDate();
    }

    /**
    * Sets the start date of this gcm contract details.
    *
    * @param startDate the start date of this gcm contract details
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _gcmContractDetails.setStartDate(startDate);
    }

    /**
    * Returns the plan level of this gcm contract details.
    *
    * @return the plan level of this gcm contract details
    */
    @Override
    public java.lang.String getPlanLevel() {
        return _gcmContractDetails.getPlanLevel();
    }

    /**
    * Sets the plan level of this gcm contract details.
    *
    * @param planLevel the plan level of this gcm contract details
    */
    @Override
    public void setPlanLevel(java.lang.String planLevel) {
        _gcmContractDetails.setPlanLevel(planLevel);
    }

    /**
    * Returns the created date of this gcm contract details.
    *
    * @return the created date of this gcm contract details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _gcmContractDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this gcm contract details.
    *
    * @param createdDate the created date of this gcm contract details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _gcmContractDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this gcm contract details.
    *
    * @return the created by of this gcm contract details
    */
    @Override
    public int getCreatedBy() {
        return _gcmContractDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this gcm contract details.
    *
    * @param createdBy the created by of this gcm contract details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _gcmContractDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the component no of this gcm contract details.
    *
    * @return the component no of this gcm contract details
    */
    @Override
    public java.lang.String getComponentNo() {
        return _gcmContractDetails.getComponentNo();
    }

    /**
    * Sets the component no of this gcm contract details.
    *
    * @param componentNo the component no of this gcm contract details
    */
    @Override
    public void setComponentNo(java.lang.String componentNo) {
        _gcmContractDetails.setComponentNo(componentNo);
    }

    /**
    * Returns the program type of this gcm contract details.
    *
    * @return the program type of this gcm contract details
    */
    @Override
    public java.lang.String getProgramType() {
        return _gcmContractDetails.getProgramType();
    }

    /**
    * Sets the program type of this gcm contract details.
    *
    * @param programType the program type of this gcm contract details
    */
    @Override
    public void setProgramType(java.lang.String programType) {
        _gcmContractDetails.setProgramType(programType);
    }

    /**
    * Returns the session ID of this gcm contract details.
    *
    * @return the session ID of this gcm contract details
    */
    @Override
    public java.lang.String getSessionId() {
        return _gcmContractDetails.getSessionId();
    }

    /**
    * Sets the session ID of this gcm contract details.
    *
    * @param sessionId the session ID of this gcm contract details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _gcmContractDetails.setSessionId(sessionId);
    }

    /**
    * Returns the modified by of this gcm contract details.
    *
    * @return the modified by of this gcm contract details
    */
    @Override
    public int getModifiedBy() {
        return _gcmContractDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this gcm contract details.
    *
    * @param modifiedBy the modified by of this gcm contract details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _gcmContractDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the component status of this gcm contract details.
    *
    * @return the component status of this gcm contract details
    */
    @Override
    public java.lang.String getComponentStatus() {
        return _gcmContractDetails.getComponentStatus();
    }

    /**
    * Sets the component status of this gcm contract details.
    *
    * @param componentStatus the component status of this gcm contract details
    */
    @Override
    public void setComponentStatus(java.lang.String componentStatus) {
        _gcmContractDetails.setComponentStatus(componentStatus);
    }

    /**
    * Returns the component type of this gcm contract details.
    *
    * @return the component type of this gcm contract details
    */
    @Override
    public java.lang.String getComponentType() {
        return _gcmContractDetails.getComponentType();
    }

    /**
    * Sets the component type of this gcm contract details.
    *
    * @param componentType the component type of this gcm contract details
    */
    @Override
    public void setComponentType(java.lang.String componentType) {
        _gcmContractDetails.setComponentType(componentType);
    }

    @Override
    public boolean isNew() {
        return _gcmContractDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _gcmContractDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _gcmContractDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _gcmContractDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _gcmContractDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _gcmContractDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _gcmContractDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _gcmContractDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _gcmContractDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _gcmContractDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _gcmContractDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new GcmContractDetailsWrapper((GcmContractDetails) _gcmContractDetails.clone());
    }

    @Override
    public int compareTo(GcmContractDetails gcmContractDetails) {
        return _gcmContractDetails.compareTo(gcmContractDetails);
    }

    @Override
    public int hashCode() {
        return _gcmContractDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<GcmContractDetails> toCacheModel() {
        return _gcmContractDetails.toCacheModel();
    }

    @Override
    public GcmContractDetails toEscapedModel() {
        return new GcmContractDetailsWrapper(_gcmContractDetails.toEscapedModel());
    }

    @Override
    public GcmContractDetails toUnescapedModel() {
        return new GcmContractDetailsWrapper(_gcmContractDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _gcmContractDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _gcmContractDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _gcmContractDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof GcmContractDetailsWrapper)) {
            return false;
        }

        GcmContractDetailsWrapper gcmContractDetailsWrapper = (GcmContractDetailsWrapper) obj;

        if (Validator.equals(_gcmContractDetails,
                    gcmContractDetailsWrapper._gcmContractDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public GcmContractDetails getWrappedGcmContractDetails() {
        return _gcmContractDetails;
    }

    @Override
    public GcmContractDetails getWrappedModel() {
        return _gcmContractDetails;
    }

    @Override
    public void resetOriginalValues() {
        _gcmContractDetails.resetOriginalValues();
    }
}
