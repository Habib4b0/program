package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SlaCalendarDetails}.
 * </p>
 *
 * @author
 * @see SlaCalendarDetails
 * @generated
 */
public class SlaCalendarDetailsWrapper implements SlaCalendarDetails,
    ModelWrapper<SlaCalendarDetails> {
    private SlaCalendarDetails _slaCalendarDetails;

    public SlaCalendarDetailsWrapper(SlaCalendarDetails slaCalendarDetails) {
        _slaCalendarDetails = slaCalendarDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return SlaCalendarDetails.class;
    }

    @Override
    public String getModelClassName() {
        return SlaCalendarDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("slaCalendarMasterSid", getSlaCalendarMasterSid());
        attributes.put("holidayYear", getHolidayYear());
        attributes.put("slaCalendarDetailsSid", getSlaCalendarDetailsSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("holidayDay", getHolidayDay());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("holidayCombined", getHolidayCombined());
        attributes.put("holidayMonth", getHolidayMonth());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer slaCalendarMasterSid = (Integer) attributes.get(
                "slaCalendarMasterSid");

        if (slaCalendarMasterSid != null) {
            setSlaCalendarMasterSid(slaCalendarMasterSid);
        }

        String holidayYear = (String) attributes.get("holidayYear");

        if (holidayYear != null) {
            setHolidayYear(holidayYear);
        }

        Integer slaCalendarDetailsSid = (Integer) attributes.get(
                "slaCalendarDetailsSid");

        if (slaCalendarDetailsSid != null) {
            setSlaCalendarDetailsSid(slaCalendarDetailsSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String holidayDay = (String) attributes.get("holidayDay");

        if (holidayDay != null) {
            setHolidayDay(holidayDay);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date holidayCombined = (Date) attributes.get("holidayCombined");

        if (holidayCombined != null) {
            setHolidayCombined(holidayCombined);
        }

        String holidayMonth = (String) attributes.get("holidayMonth");

        if (holidayMonth != null) {
            setHolidayMonth(holidayMonth);
        }
    }

    /**
    * Returns the primary key of this sla calendar details.
    *
    * @return the primary key of this sla calendar details
    */
    @Override
    public int getPrimaryKey() {
        return _slaCalendarDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this sla calendar details.
    *
    * @param primaryKey the primary key of this sla calendar details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _slaCalendarDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this sla calendar details.
    *
    * @return the created date of this sla calendar details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _slaCalendarDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this sla calendar details.
    *
    * @param createdDate the created date of this sla calendar details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _slaCalendarDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this sla calendar details.
    *
    * @return the created by of this sla calendar details
    */
    @Override
    public int getCreatedBy() {
        return _slaCalendarDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this sla calendar details.
    *
    * @param createdBy the created by of this sla calendar details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _slaCalendarDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the sla calendar master sid of this sla calendar details.
    *
    * @return the sla calendar master sid of this sla calendar details
    */
    @Override
    public int getSlaCalendarMasterSid() {
        return _slaCalendarDetails.getSlaCalendarMasterSid();
    }

    /**
    * Sets the sla calendar master sid of this sla calendar details.
    *
    * @param slaCalendarMasterSid the sla calendar master sid of this sla calendar details
    */
    @Override
    public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
        _slaCalendarDetails.setSlaCalendarMasterSid(slaCalendarMasterSid);
    }

    /**
    * Returns the holiday year of this sla calendar details.
    *
    * @return the holiday year of this sla calendar details
    */
    @Override
    public java.lang.String getHolidayYear() {
        return _slaCalendarDetails.getHolidayYear();
    }

    /**
    * Sets the holiday year of this sla calendar details.
    *
    * @param holidayYear the holiday year of this sla calendar details
    */
    @Override
    public void setHolidayYear(java.lang.String holidayYear) {
        _slaCalendarDetails.setHolidayYear(holidayYear);
    }

    /**
    * Returns the sla calendar details sid of this sla calendar details.
    *
    * @return the sla calendar details sid of this sla calendar details
    */
    @Override
    public int getSlaCalendarDetailsSid() {
        return _slaCalendarDetails.getSlaCalendarDetailsSid();
    }

    /**
    * Sets the sla calendar details sid of this sla calendar details.
    *
    * @param slaCalendarDetailsSid the sla calendar details sid of this sla calendar details
    */
    @Override
    public void setSlaCalendarDetailsSid(int slaCalendarDetailsSid) {
        _slaCalendarDetails.setSlaCalendarDetailsSid(slaCalendarDetailsSid);
    }

    /**
    * Returns the modified by of this sla calendar details.
    *
    * @return the modified by of this sla calendar details
    */
    @Override
    public int getModifiedBy() {
        return _slaCalendarDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this sla calendar details.
    *
    * @param modifiedBy the modified by of this sla calendar details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _slaCalendarDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this sla calendar details.
    *
    * @return the inbound status of this sla calendar details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _slaCalendarDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this sla calendar details.
    *
    * @param inboundStatus the inbound status of this sla calendar details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _slaCalendarDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the holiday day of this sla calendar details.
    *
    * @return the holiday day of this sla calendar details
    */
    @Override
    public java.lang.String getHolidayDay() {
        return _slaCalendarDetails.getHolidayDay();
    }

    /**
    * Sets the holiday day of this sla calendar details.
    *
    * @param holidayDay the holiday day of this sla calendar details
    */
    @Override
    public void setHolidayDay(java.lang.String holidayDay) {
        _slaCalendarDetails.setHolidayDay(holidayDay);
    }

    /**
    * Returns the modified date of this sla calendar details.
    *
    * @return the modified date of this sla calendar details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _slaCalendarDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this sla calendar details.
    *
    * @param modifiedDate the modified date of this sla calendar details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _slaCalendarDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the holiday combined of this sla calendar details.
    *
    * @return the holiday combined of this sla calendar details
    */
    @Override
    public java.util.Date getHolidayCombined() {
        return _slaCalendarDetails.getHolidayCombined();
    }

    /**
    * Sets the holiday combined of this sla calendar details.
    *
    * @param holidayCombined the holiday combined of this sla calendar details
    */
    @Override
    public void setHolidayCombined(java.util.Date holidayCombined) {
        _slaCalendarDetails.setHolidayCombined(holidayCombined);
    }

    /**
    * Returns the holiday month of this sla calendar details.
    *
    * @return the holiday month of this sla calendar details
    */
    @Override
    public java.lang.String getHolidayMonth() {
        return _slaCalendarDetails.getHolidayMonth();
    }

    /**
    * Sets the holiday month of this sla calendar details.
    *
    * @param holidayMonth the holiday month of this sla calendar details
    */
    @Override
    public void setHolidayMonth(java.lang.String holidayMonth) {
        _slaCalendarDetails.setHolidayMonth(holidayMonth);
    }

    @Override
    public boolean isNew() {
        return _slaCalendarDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _slaCalendarDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _slaCalendarDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _slaCalendarDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _slaCalendarDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _slaCalendarDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _slaCalendarDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _slaCalendarDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _slaCalendarDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _slaCalendarDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _slaCalendarDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new SlaCalendarDetailsWrapper((SlaCalendarDetails) _slaCalendarDetails.clone());
    }

    @Override
    public int compareTo(SlaCalendarDetails slaCalendarDetails) {
        return _slaCalendarDetails.compareTo(slaCalendarDetails);
    }

    @Override
    public int hashCode() {
        return _slaCalendarDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<SlaCalendarDetails> toCacheModel() {
        return _slaCalendarDetails.toCacheModel();
    }

    @Override
    public SlaCalendarDetails toEscapedModel() {
        return new SlaCalendarDetailsWrapper(_slaCalendarDetails.toEscapedModel());
    }

    @Override
    public SlaCalendarDetails toUnescapedModel() {
        return new SlaCalendarDetailsWrapper(_slaCalendarDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _slaCalendarDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _slaCalendarDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _slaCalendarDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SlaCalendarDetailsWrapper)) {
            return false;
        }

        SlaCalendarDetailsWrapper slaCalendarDetailsWrapper = (SlaCalendarDetailsWrapper) obj;

        if (Validator.equals(_slaCalendarDetails,
                    slaCalendarDetailsWrapper._slaCalendarDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public SlaCalendarDetails getWrappedSlaCalendarDetails() {
        return _slaCalendarDetails;
    }

    @Override
    public SlaCalendarDetails getWrappedModel() {
        return _slaCalendarDetails;
    }

    @Override
    public void resetOriginalValues() {
        _slaCalendarDetails.resetOriginalValues();
    }
}
