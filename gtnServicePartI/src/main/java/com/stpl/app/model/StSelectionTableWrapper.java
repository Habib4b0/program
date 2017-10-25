package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StSelectionTable}.
 * </p>
 *
 * @author
 * @see StSelectionTable
 * @generated
 */
public class StSelectionTableWrapper implements StSelectionTable,
    ModelWrapper<StSelectionTable> {
    private StSelectionTable _stSelectionTable;

    public StSelectionTableWrapper(StSelectionTable stSelectionTable) {
        _stSelectionTable = stSelectionTable;
    }

    @Override
    public Class<?> getModelClass() {
        return StSelectionTable.class;
    }

    @Override
    public String getModelClassName() {
        return StSelectionTable.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("selectionType", getSelectionType());
        attributes.put("userId", getUserId());
        attributes.put("sessionId", getSessionId());
        attributes.put("companyItemSid", getCompanyItemSid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String selectionType = (String) attributes.get("selectionType");

        if (selectionType != null) {
            setSelectionType(selectionType);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer companyItemSid = (Integer) attributes.get("companyItemSid");

        if (companyItemSid != null) {
            setCompanyItemSid(companyItemSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this st selection table.
    *
    * @return the primary key of this st selection table
    */
    @Override
    public com.stpl.app.service.persistence.StSelectionTablePK getPrimaryKey() {
        return _stSelectionTable.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st selection table.
    *
    * @param primaryKey the primary key of this st selection table
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StSelectionTablePK primaryKey) {
        _stSelectionTable.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the selection type of this st selection table.
    *
    * @return the selection type of this st selection table
    */
    @Override
    public java.lang.String getSelectionType() {
        return _stSelectionTable.getSelectionType();
    }

    /**
    * Sets the selection type of this st selection table.
    *
    * @param selectionType the selection type of this st selection table
    */
    @Override
    public void setSelectionType(java.lang.String selectionType) {
        _stSelectionTable.setSelectionType(selectionType);
    }

    /**
    * Returns the user ID of this st selection table.
    *
    * @return the user ID of this st selection table
    */
    @Override
    public int getUserId() {
        return _stSelectionTable.getUserId();
    }

    /**
    * Sets the user ID of this st selection table.
    *
    * @param userId the user ID of this st selection table
    */
    @Override
    public void setUserId(int userId) {
        _stSelectionTable.setUserId(userId);
    }

    /**
    * Returns the session ID of this st selection table.
    *
    * @return the session ID of this st selection table
    */
    @Override
    public java.lang.String getSessionId() {
        return _stSelectionTable.getSessionId();
    }

    /**
    * Sets the session ID of this st selection table.
    *
    * @param sessionId the session ID of this st selection table
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _stSelectionTable.setSessionId(sessionId);
    }

    /**
    * Returns the company item sid of this st selection table.
    *
    * @return the company item sid of this st selection table
    */
    @Override
    public int getCompanyItemSid() {
        return _stSelectionTable.getCompanyItemSid();
    }

    /**
    * Sets the company item sid of this st selection table.
    *
    * @param companyItemSid the company item sid of this st selection table
    */
    @Override
    public void setCompanyItemSid(int companyItemSid) {
        _stSelectionTable.setCompanyItemSid(companyItemSid);
    }

    /**
    * Returns the check record of this st selection table.
    *
    * @return the check record of this st selection table
    */
    @Override
    public boolean getCheckRecord() {
        return _stSelectionTable.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st selection table is check record.
    *
    * @return <code>true</code> if this st selection table is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stSelectionTable.isCheckRecord();
    }

    /**
    * Sets whether this st selection table is check record.
    *
    * @param checkRecord the check record of this st selection table
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stSelectionTable.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _stSelectionTable.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stSelectionTable.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stSelectionTable.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stSelectionTable.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stSelectionTable.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stSelectionTable.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stSelectionTable.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stSelectionTable.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stSelectionTable.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stSelectionTable.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stSelectionTable.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StSelectionTableWrapper((StSelectionTable) _stSelectionTable.clone());
    }

    @Override
    public int compareTo(StSelectionTable stSelectionTable) {
        return _stSelectionTable.compareTo(stSelectionTable);
    }

    @Override
    public int hashCode() {
        return _stSelectionTable.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StSelectionTable> toCacheModel() {
        return _stSelectionTable.toCacheModel();
    }

    @Override
    public StSelectionTable toEscapedModel() {
        return new StSelectionTableWrapper(_stSelectionTable.toEscapedModel());
    }

    @Override
    public StSelectionTable toUnescapedModel() {
        return new StSelectionTableWrapper(_stSelectionTable.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stSelectionTable.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stSelectionTable.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stSelectionTable.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StSelectionTableWrapper)) {
            return false;
        }

        StSelectionTableWrapper stSelectionTableWrapper = (StSelectionTableWrapper) obj;

        if (Validator.equals(_stSelectionTable,
                    stSelectionTableWrapper._stSelectionTable)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StSelectionTable getWrappedStSelectionTable() {
        return _stSelectionTable;
    }

    @Override
    public StSelectionTable getWrappedModel() {
        return _stSelectionTable;
    }

    @Override
    public void resetOriginalValues() {
        _stSelectionTable.resetOriginalValues();
    }
}
