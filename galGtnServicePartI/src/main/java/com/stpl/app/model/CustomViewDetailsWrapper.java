package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CustomViewDetails}.
 * </p>
 *
 * @author
 * @see CustomViewDetails
 * @generated
 */
public class CustomViewDetailsWrapper implements CustomViewDetails,
    ModelWrapper<CustomViewDetails> {
    private CustomViewDetails _customViewDetails;

    public CustomViewDetailsWrapper(CustomViewDetails customViewDetails) {
        _customViewDetails = customViewDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CustomViewDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CustomViewDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("hierarchyId", getHierarchyId());
        attributes.put("hierarchyIndicator", getHierarchyIndicator());
        attributes.put("customViewMasterSid", getCustomViewMasterSid());
        attributes.put("customViewDetailsSid", getCustomViewDetailsSid());
        attributes.put("levelNo", getLevelNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer hierarchyId = (Integer) attributes.get("hierarchyId");

        if (hierarchyId != null) {
            setHierarchyId(hierarchyId);
        }

        String hierarchyIndicator = (String) attributes.get(
                "hierarchyIndicator");

        if (hierarchyIndicator != null) {
            setHierarchyIndicator(hierarchyIndicator);
        }

        Integer customViewMasterSid = (Integer) attributes.get(
                "customViewMasterSid");

        if (customViewMasterSid != null) {
            setCustomViewMasterSid(customViewMasterSid);
        }

        Integer customViewDetailsSid = (Integer) attributes.get(
                "customViewDetailsSid");

        if (customViewDetailsSid != null) {
            setCustomViewDetailsSid(customViewDetailsSid);
        }

        Integer levelNo = (Integer) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }
    }

    /**
    * Returns the primary key of this custom view details.
    *
    * @return the primary key of this custom view details
    */
    @Override
    public int getPrimaryKey() {
        return _customViewDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this custom view details.
    *
    * @param primaryKey the primary key of this custom view details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _customViewDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the hierarchy ID of this custom view details.
    *
    * @return the hierarchy ID of this custom view details
    */
    @Override
    public int getHierarchyId() {
        return _customViewDetails.getHierarchyId();
    }

    /**
    * Sets the hierarchy ID of this custom view details.
    *
    * @param hierarchyId the hierarchy ID of this custom view details
    */
    @Override
    public void setHierarchyId(int hierarchyId) {
        _customViewDetails.setHierarchyId(hierarchyId);
    }

    /**
    * Returns the hierarchy indicator of this custom view details.
    *
    * @return the hierarchy indicator of this custom view details
    */
    @Override
    public java.lang.String getHierarchyIndicator() {
        return _customViewDetails.getHierarchyIndicator();
    }

    /**
    * Sets the hierarchy indicator of this custom view details.
    *
    * @param hierarchyIndicator the hierarchy indicator of this custom view details
    */
    @Override
    public void setHierarchyIndicator(java.lang.String hierarchyIndicator) {
        _customViewDetails.setHierarchyIndicator(hierarchyIndicator);
    }

    /**
    * Returns the custom view master sid of this custom view details.
    *
    * @return the custom view master sid of this custom view details
    */
    @Override
    public int getCustomViewMasterSid() {
        return _customViewDetails.getCustomViewMasterSid();
    }

    /**
    * Sets the custom view master sid of this custom view details.
    *
    * @param customViewMasterSid the custom view master sid of this custom view details
    */
    @Override
    public void setCustomViewMasterSid(int customViewMasterSid) {
        _customViewDetails.setCustomViewMasterSid(customViewMasterSid);
    }

    /**
    * Returns the custom view details sid of this custom view details.
    *
    * @return the custom view details sid of this custom view details
    */
    @Override
    public int getCustomViewDetailsSid() {
        return _customViewDetails.getCustomViewDetailsSid();
    }

    /**
    * Sets the custom view details sid of this custom view details.
    *
    * @param customViewDetailsSid the custom view details sid of this custom view details
    */
    @Override
    public void setCustomViewDetailsSid(int customViewDetailsSid) {
        _customViewDetails.setCustomViewDetailsSid(customViewDetailsSid);
    }

    /**
    * Returns the level no of this custom view details.
    *
    * @return the level no of this custom view details
    */
    @Override
    public int getLevelNo() {
        return _customViewDetails.getLevelNo();
    }

    /**
    * Sets the level no of this custom view details.
    *
    * @param levelNo the level no of this custom view details
    */
    @Override
    public void setLevelNo(int levelNo) {
        _customViewDetails.setLevelNo(levelNo);
    }

    @Override
    public boolean isNew() {
        return _customViewDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _customViewDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _customViewDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _customViewDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _customViewDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _customViewDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _customViewDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _customViewDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _customViewDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _customViewDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _customViewDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CustomViewDetailsWrapper((CustomViewDetails) _customViewDetails.clone());
    }

    @Override
    public int compareTo(CustomViewDetails customViewDetails) {
        return _customViewDetails.compareTo(customViewDetails);
    }

    @Override
    public int hashCode() {
        return _customViewDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CustomViewDetails> toCacheModel() {
        return _customViewDetails.toCacheModel();
    }

    @Override
    public CustomViewDetails toEscapedModel() {
        return new CustomViewDetailsWrapper(_customViewDetails.toEscapedModel());
    }

    @Override
    public CustomViewDetails toUnescapedModel() {
        return new CustomViewDetailsWrapper(_customViewDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _customViewDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _customViewDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _customViewDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CustomViewDetailsWrapper)) {
            return false;
        }

        CustomViewDetailsWrapper customViewDetailsWrapper = (CustomViewDetailsWrapper) obj;

        if (Validator.equals(_customViewDetails,
                    customViewDetailsWrapper._customViewDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CustomViewDetails getWrappedCustomViewDetails() {
        return _customViewDetails;
    }

    @Override
    public CustomViewDetails getWrappedModel() {
        return _customViewDetails;
    }

    @Override
    public void resetOriginalValues() {
        _customViewDetails.resetOriginalValues();
    }
}
