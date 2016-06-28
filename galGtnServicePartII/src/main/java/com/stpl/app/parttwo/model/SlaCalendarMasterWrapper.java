package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SlaCalendarMaster}.
 * </p>
 *
 * @author
 * @see SlaCalendarMaster
 * @generated
 */
public class SlaCalendarMasterWrapper implements SlaCalendarMaster,
    ModelWrapper<SlaCalendarMaster> {
    private SlaCalendarMaster _slaCalendarMaster;

    public SlaCalendarMasterWrapper(SlaCalendarMaster slaCalendarMaster) {
        _slaCalendarMaster = slaCalendarMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return SlaCalendarMaster.class;
    }

    @Override
    public String getModelClassName() {
        return SlaCalendarMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("slaCalendarMasterSid", getSlaCalendarMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("defaultHolidays", getDefaultHolidays());
        attributes.put("calendarName", getCalendarName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer slaCalendarMasterSid = (Integer) attributes.get(
                "slaCalendarMasterSid");

        if (slaCalendarMasterSid != null) {
            setSlaCalendarMasterSid(slaCalendarMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Boolean defaultHolidays = (Boolean) attributes.get("defaultHolidays");

        if (defaultHolidays != null) {
            setDefaultHolidays(defaultHolidays);
        }

        String calendarName = (String) attributes.get("calendarName");

        if (calendarName != null) {
            setCalendarName(calendarName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this sla calendar master.
    *
    * @return the primary key of this sla calendar master
    */
    @Override
    public int getPrimaryKey() {
        return _slaCalendarMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this sla calendar master.
    *
    * @param primaryKey the primary key of this sla calendar master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _slaCalendarMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this sla calendar master.
    *
    * @return the created by of this sla calendar master
    */
    @Override
    public int getCreatedBy() {
        return _slaCalendarMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this sla calendar master.
    *
    * @param createdBy the created by of this sla calendar master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _slaCalendarMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the modified by of this sla calendar master.
    *
    * @return the modified by of this sla calendar master
    */
    @Override
    public int getModifiedBy() {
        return _slaCalendarMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this sla calendar master.
    *
    * @param modifiedBy the modified by of this sla calendar master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _slaCalendarMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the sla calendar master sid of this sla calendar master.
    *
    * @return the sla calendar master sid of this sla calendar master
    */
    @Override
    public int getSlaCalendarMasterSid() {
        return _slaCalendarMaster.getSlaCalendarMasterSid();
    }

    /**
    * Sets the sla calendar master sid of this sla calendar master.
    *
    * @param slaCalendarMasterSid the sla calendar master sid of this sla calendar master
    */
    @Override
    public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
        _slaCalendarMaster.setSlaCalendarMasterSid(slaCalendarMasterSid);
    }

    /**
    * Returns the created date of this sla calendar master.
    *
    * @return the created date of this sla calendar master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _slaCalendarMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this sla calendar master.
    *
    * @param createdDate the created date of this sla calendar master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _slaCalendarMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the default holidays of this sla calendar master.
    *
    * @return the default holidays of this sla calendar master
    */
    @Override
    public boolean getDefaultHolidays() {
        return _slaCalendarMaster.getDefaultHolidays();
    }

    /**
    * Returns <code>true</code> if this sla calendar master is default holidays.
    *
    * @return <code>true</code> if this sla calendar master is default holidays; <code>false</code> otherwise
    */
    @Override
    public boolean isDefaultHolidays() {
        return _slaCalendarMaster.isDefaultHolidays();
    }

    /**
    * Sets whether this sla calendar master is default holidays.
    *
    * @param defaultHolidays the default holidays of this sla calendar master
    */
    @Override
    public void setDefaultHolidays(boolean defaultHolidays) {
        _slaCalendarMaster.setDefaultHolidays(defaultHolidays);
    }

    /**
    * Returns the calendar name of this sla calendar master.
    *
    * @return the calendar name of this sla calendar master
    */
    @Override
    public java.lang.String getCalendarName() {
        return _slaCalendarMaster.getCalendarName();
    }

    /**
    * Sets the calendar name of this sla calendar master.
    *
    * @param calendarName the calendar name of this sla calendar master
    */
    @Override
    public void setCalendarName(java.lang.String calendarName) {
        _slaCalendarMaster.setCalendarName(calendarName);
    }

    /**
    * Returns the modified date of this sla calendar master.
    *
    * @return the modified date of this sla calendar master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _slaCalendarMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this sla calendar master.
    *
    * @param modifiedDate the modified date of this sla calendar master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _slaCalendarMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the inbound status of this sla calendar master.
    *
    * @return the inbound status of this sla calendar master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _slaCalendarMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this sla calendar master.
    *
    * @param inboundStatus the inbound status of this sla calendar master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _slaCalendarMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _slaCalendarMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _slaCalendarMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _slaCalendarMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _slaCalendarMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _slaCalendarMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _slaCalendarMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _slaCalendarMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _slaCalendarMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _slaCalendarMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _slaCalendarMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _slaCalendarMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new SlaCalendarMasterWrapper((SlaCalendarMaster) _slaCalendarMaster.clone());
    }

    @Override
    public int compareTo(SlaCalendarMaster slaCalendarMaster) {
        return _slaCalendarMaster.compareTo(slaCalendarMaster);
    }

    @Override
    public int hashCode() {
        return _slaCalendarMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<SlaCalendarMaster> toCacheModel() {
        return _slaCalendarMaster.toCacheModel();
    }

    @Override
    public SlaCalendarMaster toEscapedModel() {
        return new SlaCalendarMasterWrapper(_slaCalendarMaster.toEscapedModel());
    }

    @Override
    public SlaCalendarMaster toUnescapedModel() {
        return new SlaCalendarMasterWrapper(_slaCalendarMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _slaCalendarMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _slaCalendarMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _slaCalendarMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SlaCalendarMasterWrapper)) {
            return false;
        }

        SlaCalendarMasterWrapper slaCalendarMasterWrapper = (SlaCalendarMasterWrapper) obj;

        if (Validator.equals(_slaCalendarMaster,
                    slaCalendarMasterWrapper._slaCalendarMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public SlaCalendarMaster getWrappedSlaCalendarMaster() {
        return _slaCalendarMaster;
    }

    @Override
    public SlaCalendarMaster getWrappedModel() {
        return _slaCalendarMaster;
    }

    @Override
    public void resetOriginalValues() {
        _slaCalendarMaster.resetOriginalValues();
    }
}
