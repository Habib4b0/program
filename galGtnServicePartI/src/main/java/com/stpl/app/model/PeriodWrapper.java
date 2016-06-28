package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Period}.
 * </p>
 *
 * @author
 * @see Period
 * @generated
 */
public class PeriodWrapper implements Period, ModelWrapper<Period> {
    private Period _period;

    public PeriodWrapper(Period period) {
        _period = period;
    }

    @Override
    public Class<?> getModelClass() {
        return Period.class;
    }

    @Override
    public String getModelClassName() {
        return Period.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("periodDate", getPeriodDate());
        attributes.put("quarter", getQuarter());
        attributes.put("year", getYear());
        attributes.put("semiAnnual", getSemiAnnual());
        attributes.put("month", getMonth());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Date periodDate = (Date) attributes.get("periodDate");

        if (periodDate != null) {
            setPeriodDate(periodDate);
        }

        Integer quarter = (Integer) attributes.get("quarter");

        if (quarter != null) {
            setQuarter(quarter);
        }

        Integer year = (Integer) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        Integer semiAnnual = (Integer) attributes.get("semiAnnual");

        if (semiAnnual != null) {
            setSemiAnnual(semiAnnual);
        }

        Integer month = (Integer) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }
    }

    /**
    * Returns the primary key of this period.
    *
    * @return the primary key of this period
    */
    @Override
    public int getPrimaryKey() {
        return _period.getPrimaryKey();
    }

    /**
    * Sets the primary key of this period.
    *
    * @param primaryKey the primary key of this period
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _period.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the period sid of this period.
    *
    * @return the period sid of this period
    */
    @Override
    public int getPeriodSid() {
        return _period.getPeriodSid();
    }

    /**
    * Sets the period sid of this period.
    *
    * @param periodSid the period sid of this period
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _period.setPeriodSid(periodSid);
    }

    /**
    * Returns the period date of this period.
    *
    * @return the period date of this period
    */
    @Override
    public java.util.Date getPeriodDate() {
        return _period.getPeriodDate();
    }

    /**
    * Sets the period date of this period.
    *
    * @param periodDate the period date of this period
    */
    @Override
    public void setPeriodDate(java.util.Date periodDate) {
        _period.setPeriodDate(periodDate);
    }

    /**
    * Returns the quarter of this period.
    *
    * @return the quarter of this period
    */
    @Override
    public int getQuarter() {
        return _period.getQuarter();
    }

    /**
    * Sets the quarter of this period.
    *
    * @param quarter the quarter of this period
    */
    @Override
    public void setQuarter(int quarter) {
        _period.setQuarter(quarter);
    }

    /**
    * Returns the year of this period.
    *
    * @return the year of this period
    */
    @Override
    public int getYear() {
        return _period.getYear();
    }

    /**
    * Sets the year of this period.
    *
    * @param year the year of this period
    */
    @Override
    public void setYear(int year) {
        _period.setYear(year);
    }

    /**
    * Returns the semi annual of this period.
    *
    * @return the semi annual of this period
    */
    @Override
    public int getSemiAnnual() {
        return _period.getSemiAnnual();
    }

    /**
    * Sets the semi annual of this period.
    *
    * @param semiAnnual the semi annual of this period
    */
    @Override
    public void setSemiAnnual(int semiAnnual) {
        _period.setSemiAnnual(semiAnnual);
    }

    /**
    * Returns the month of this period.
    *
    * @return the month of this period
    */
    @Override
    public int getMonth() {
        return _period.getMonth();
    }

    /**
    * Sets the month of this period.
    *
    * @param month the month of this period
    */
    @Override
    public void setMonth(int month) {
        _period.setMonth(month);
    }

    @Override
    public boolean isNew() {
        return _period.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _period.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _period.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _period.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _period.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _period.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _period.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _period.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _period.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _period.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _period.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PeriodWrapper((Period) _period.clone());
    }

    @Override
    public int compareTo(Period period) {
        return _period.compareTo(period);
    }

    @Override
    public int hashCode() {
        return _period.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<Period> toCacheModel() {
        return _period.toCacheModel();
    }

    @Override
    public Period toEscapedModel() {
        return new PeriodWrapper(_period.toEscapedModel());
    }

    @Override
    public Period toUnescapedModel() {
        return new PeriodWrapper(_period.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _period.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _period.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _period.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PeriodWrapper)) {
            return false;
        }

        PeriodWrapper periodWrapper = (PeriodWrapper) obj;

        if (Validator.equals(_period, periodWrapper._period)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Period getWrappedPeriod() {
        return _period;
    }

    @Override
    public Period getWrappedModel() {
        return _period;
    }

    @Override
    public void resetOriginalValues() {
        _period.resetOriginalValues();
    }
}
