package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MedicaidNewNdc}.
 * </p>
 *
 * @author
 * @see MedicaidNewNdc
 * @generated
 */
public class MedicaidNewNdcWrapper implements MedicaidNewNdc,
    ModelWrapper<MedicaidNewNdc> {
    private MedicaidNewNdc _medicaidNewNdc;

    public MedicaidNewNdcWrapper(MedicaidNewNdc medicaidNewNdc) {
        _medicaidNewNdc = medicaidNewNdc;
    }

    @Override
    public Class<?> getModelClass() {
        return MedicaidNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return MedicaidNewNdc.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastAmp", getForecastAmp());
        attributes.put("forecastBestprice", getForecastBestprice());
        attributes.put("baseYearCpi", getBaseYearCpi());
        attributes.put("ndc9", getNdc9());
        attributes.put("wacPrice", getWacPrice());
        attributes.put("baseYearAmp", getBaseYearAmp());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double forecastAmp = (Double) attributes.get("forecastAmp");

        if (forecastAmp != null) {
            setForecastAmp(forecastAmp);
        }

        Double forecastBestprice = (Double) attributes.get("forecastBestprice");

        if (forecastBestprice != null) {
            setForecastBestprice(forecastBestprice);
        }

        Double baseYearCpi = (Double) attributes.get("baseYearCpi");

        if (baseYearCpi != null) {
            setBaseYearCpi(baseYearCpi);
        }

        String ndc9 = (String) attributes.get("ndc9");

        if (ndc9 != null) {
            setNdc9(ndc9);
        }

        Double wacPrice = (Double) attributes.get("wacPrice");

        if (wacPrice != null) {
            setWacPrice(wacPrice);
        }

        Double baseYearAmp = (Double) attributes.get("baseYearAmp");

        if (baseYearAmp != null) {
            setBaseYearAmp(baseYearAmp);
        }
    }

    /**
    * Returns the primary key of this medicaid new ndc.
    *
    * @return the primary key of this medicaid new ndc
    */
    @Override
    public java.lang.String getPrimaryKey() {
        return _medicaidNewNdc.getPrimaryKey();
    }

    /**
    * Sets the primary key of this medicaid new ndc.
    *
    * @param primaryKey the primary key of this medicaid new ndc
    */
    @Override
    public void setPrimaryKey(java.lang.String primaryKey) {
        _medicaidNewNdc.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the forecast amp of this medicaid new ndc.
    *
    * @return the forecast amp of this medicaid new ndc
    */
    @Override
    public double getForecastAmp() {
        return _medicaidNewNdc.getForecastAmp();
    }

    /**
    * Sets the forecast amp of this medicaid new ndc.
    *
    * @param forecastAmp the forecast amp of this medicaid new ndc
    */
    @Override
    public void setForecastAmp(double forecastAmp) {
        _medicaidNewNdc.setForecastAmp(forecastAmp);
    }

    /**
    * Returns the forecast bestprice of this medicaid new ndc.
    *
    * @return the forecast bestprice of this medicaid new ndc
    */
    @Override
    public double getForecastBestprice() {
        return _medicaidNewNdc.getForecastBestprice();
    }

    /**
    * Sets the forecast bestprice of this medicaid new ndc.
    *
    * @param forecastBestprice the forecast bestprice of this medicaid new ndc
    */
    @Override
    public void setForecastBestprice(double forecastBestprice) {
        _medicaidNewNdc.setForecastBestprice(forecastBestprice);
    }

    /**
    * Returns the base year cpi of this medicaid new ndc.
    *
    * @return the base year cpi of this medicaid new ndc
    */
    @Override
    public double getBaseYearCpi() {
        return _medicaidNewNdc.getBaseYearCpi();
    }

    /**
    * Sets the base year cpi of this medicaid new ndc.
    *
    * @param baseYearCpi the base year cpi of this medicaid new ndc
    */
    @Override
    public void setBaseYearCpi(double baseYearCpi) {
        _medicaidNewNdc.setBaseYearCpi(baseYearCpi);
    }

    /**
    * Returns the ndc9 of this medicaid new ndc.
    *
    * @return the ndc9 of this medicaid new ndc
    */
    @Override
    public java.lang.String getNdc9() {
        return _medicaidNewNdc.getNdc9();
    }

    /**
    * Sets the ndc9 of this medicaid new ndc.
    *
    * @param ndc9 the ndc9 of this medicaid new ndc
    */
    @Override
    public void setNdc9(java.lang.String ndc9) {
        _medicaidNewNdc.setNdc9(ndc9);
    }

    /**
    * Returns the wac price of this medicaid new ndc.
    *
    * @return the wac price of this medicaid new ndc
    */
    @Override
    public double getWacPrice() {
        return _medicaidNewNdc.getWacPrice();
    }

    /**
    * Sets the wac price of this medicaid new ndc.
    *
    * @param wacPrice the wac price of this medicaid new ndc
    */
    @Override
    public void setWacPrice(double wacPrice) {
        _medicaidNewNdc.setWacPrice(wacPrice);
    }

    /**
    * Returns the base year amp of this medicaid new ndc.
    *
    * @return the base year amp of this medicaid new ndc
    */
    @Override
    public double getBaseYearAmp() {
        return _medicaidNewNdc.getBaseYearAmp();
    }

    /**
    * Sets the base year amp of this medicaid new ndc.
    *
    * @param baseYearAmp the base year amp of this medicaid new ndc
    */
    @Override
    public void setBaseYearAmp(double baseYearAmp) {
        _medicaidNewNdc.setBaseYearAmp(baseYearAmp);
    }

    @Override
    public boolean isNew() {
        return _medicaidNewNdc.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _medicaidNewNdc.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _medicaidNewNdc.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _medicaidNewNdc.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _medicaidNewNdc.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _medicaidNewNdc.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _medicaidNewNdc.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _medicaidNewNdc.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _medicaidNewNdc.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _medicaidNewNdc.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _medicaidNewNdc.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MedicaidNewNdcWrapper((MedicaidNewNdc) _medicaidNewNdc.clone());
    }

    @Override
    public int compareTo(MedicaidNewNdc medicaidNewNdc) {
        return _medicaidNewNdc.compareTo(medicaidNewNdc);
    }

    @Override
    public int hashCode() {
        return _medicaidNewNdc.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MedicaidNewNdc> toCacheModel() {
        return _medicaidNewNdc.toCacheModel();
    }

    @Override
    public MedicaidNewNdc toEscapedModel() {
        return new MedicaidNewNdcWrapper(_medicaidNewNdc.toEscapedModel());
    }

    @Override
    public MedicaidNewNdc toUnescapedModel() {
        return new MedicaidNewNdcWrapper(_medicaidNewNdc.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _medicaidNewNdc.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _medicaidNewNdc.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _medicaidNewNdc.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MedicaidNewNdcWrapper)) {
            return false;
        }

        MedicaidNewNdcWrapper medicaidNewNdcWrapper = (MedicaidNewNdcWrapper) obj;

        if (Validator.equals(_medicaidNewNdc,
                    medicaidNewNdcWrapper._medicaidNewNdc)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MedicaidNewNdc getWrappedMedicaidNewNdc() {
        return _medicaidNewNdc;
    }

    @Override
    public MedicaidNewNdc getWrappedModel() {
        return _medicaidNewNdc;
    }

    @Override
    public void resetOriginalValues() {
        _medicaidNewNdc.resetOriginalValues();
    }
}
