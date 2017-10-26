package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ForecastingFormula}.
 * </p>
 *
 * @author
 * @see ForecastingFormula
 * @generated
 */
public class ForecastingFormulaWrapper implements ForecastingFormula,
    ModelWrapper<ForecastingFormula> {
    private ForecastingFormula _forecastingFormula;

    public ForecastingFormulaWrapper(ForecastingFormula forecastingFormula) {
        _forecastingFormula = forecastingFormula;
    }

    @Override
    public Class<?> getModelClass() {
        return ForecastingFormula.class;
    }

    @Override
    public String getModelClassName() {
        return ForecastingFormula.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("formulaType", getFormulaType());
        attributes.put("forecastingFormulaSid", getForecastingFormulaSid());
        attributes.put("formula", getFormula());
        attributes.put("formulaNo", getFormulaNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("isActive", getIsActive());
        attributes.put("formulaName", getFormulaName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer formulaType = (Integer) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        Integer forecastingFormulaSid = (Integer) attributes.get(
                "forecastingFormulaSid");

        if (forecastingFormulaSid != null) {
            setForecastingFormulaSid(forecastingFormulaSid);
        }

        String formula = (String) attributes.get("formula");

        if (formula != null) {
            setFormula(formula);
        }

        String formulaNo = (String) attributes.get("formulaNo");

        if (formulaNo != null) {
            setFormulaNo(formulaNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean isActive = (Boolean) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String formulaName = (String) attributes.get("formulaName");

        if (formulaName != null) {
            setFormulaName(formulaName);
        }
    }

    /**
    * Returns the primary key of this forecasting formula.
    *
    * @return the primary key of this forecasting formula
    */
    @Override
    public int getPrimaryKey() {
        return _forecastingFormula.getPrimaryKey();
    }

    /**
    * Sets the primary key of this forecasting formula.
    *
    * @param primaryKey the primary key of this forecasting formula
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _forecastingFormula.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this forecasting formula.
    *
    * @return the created date of this forecasting formula
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _forecastingFormula.getCreatedDate();
    }

    /**
    * Sets the created date of this forecasting formula.
    *
    * @param createdDate the created date of this forecasting formula
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _forecastingFormula.setCreatedDate(createdDate);
    }

    /**
    * Returns the formula type of this forecasting formula.
    *
    * @return the formula type of this forecasting formula
    */
    @Override
    public int getFormulaType() {
        return _forecastingFormula.getFormulaType();
    }

    /**
    * Sets the formula type of this forecasting formula.
    *
    * @param formulaType the formula type of this forecasting formula
    */
    @Override
    public void setFormulaType(int formulaType) {
        _forecastingFormula.setFormulaType(formulaType);
    }

    /**
    * Returns the forecasting formula sid of this forecasting formula.
    *
    * @return the forecasting formula sid of this forecasting formula
    */
    @Override
    public int getForecastingFormulaSid() {
        return _forecastingFormula.getForecastingFormulaSid();
    }

    /**
    * Sets the forecasting formula sid of this forecasting formula.
    *
    * @param forecastingFormulaSid the forecasting formula sid of this forecasting formula
    */
    @Override
    public void setForecastingFormulaSid(int forecastingFormulaSid) {
        _forecastingFormula.setForecastingFormulaSid(forecastingFormulaSid);
    }

    /**
    * Returns the formula of this forecasting formula.
    *
    * @return the formula of this forecasting formula
    */
    @Override
    public java.lang.String getFormula() {
        return _forecastingFormula.getFormula();
    }

    /**
    * Sets the formula of this forecasting formula.
    *
    * @param formula the formula of this forecasting formula
    */
    @Override
    public void setFormula(java.lang.String formula) {
        _forecastingFormula.setFormula(formula);
    }

    /**
    * Returns the formula no of this forecasting formula.
    *
    * @return the formula no of this forecasting formula
    */
    @Override
    public java.lang.String getFormulaNo() {
        return _forecastingFormula.getFormulaNo();
    }

    /**
    * Sets the formula no of this forecasting formula.
    *
    * @param formulaNo the formula no of this forecasting formula
    */
    @Override
    public void setFormulaNo(java.lang.String formulaNo) {
        _forecastingFormula.setFormulaNo(formulaNo);
    }

    /**
    * Returns the modified date of this forecasting formula.
    *
    * @return the modified date of this forecasting formula
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _forecastingFormula.getModifiedDate();
    }

    /**
    * Sets the modified date of this forecasting formula.
    *
    * @param modifiedDate the modified date of this forecasting formula
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _forecastingFormula.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the is active of this forecasting formula.
    *
    * @return the is active of this forecasting formula
    */
    @Override
    public boolean getIsActive() {
        return _forecastingFormula.getIsActive();
    }

    /**
    * Returns <code>true</code> if this forecasting formula is is active.
    *
    * @return <code>true</code> if this forecasting formula is is active; <code>false</code> otherwise
    */
    @Override
    public boolean isIsActive() {
        return _forecastingFormula.isIsActive();
    }

    /**
    * Sets whether this forecasting formula is is active.
    *
    * @param isActive the is active of this forecasting formula
    */
    @Override
    public void setIsActive(boolean isActive) {
        _forecastingFormula.setIsActive(isActive);
    }

    /**
    * Returns the formula name of this forecasting formula.
    *
    * @return the formula name of this forecasting formula
    */
    @Override
    public java.lang.String getFormulaName() {
        return _forecastingFormula.getFormulaName();
    }

    /**
    * Sets the formula name of this forecasting formula.
    *
    * @param formulaName the formula name of this forecasting formula
    */
    @Override
    public void setFormulaName(java.lang.String formulaName) {
        _forecastingFormula.setFormulaName(formulaName);
    }

    @Override
    public boolean isNew() {
        return _forecastingFormula.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _forecastingFormula.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _forecastingFormula.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _forecastingFormula.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _forecastingFormula.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _forecastingFormula.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _forecastingFormula.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _forecastingFormula.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _forecastingFormula.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _forecastingFormula.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _forecastingFormula.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ForecastingFormulaWrapper((ForecastingFormula) _forecastingFormula.clone());
    }

    @Override
    public int compareTo(ForecastingFormula forecastingFormula) {
        return _forecastingFormula.compareTo(forecastingFormula);
    }

    @Override
    public int hashCode() {
        return _forecastingFormula.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ForecastingFormula> toCacheModel() {
        return _forecastingFormula.toCacheModel();
    }

    @Override
    public ForecastingFormula toEscapedModel() {
        return new ForecastingFormulaWrapper(_forecastingFormula.toEscapedModel());
    }

    @Override
    public ForecastingFormula toUnescapedModel() {
        return new ForecastingFormulaWrapper(_forecastingFormula.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _forecastingFormula.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _forecastingFormula.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _forecastingFormula.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ForecastingFormulaWrapper)) {
            return false;
        }

        ForecastingFormulaWrapper forecastingFormulaWrapper = (ForecastingFormulaWrapper) obj;

        if (Validator.equals(_forecastingFormula,
                    forecastingFormulaWrapper._forecastingFormula)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ForecastingFormula getWrappedForecastingFormula() {
        return _forecastingFormula;
    }

    @Override
    public ForecastingFormula getWrappedModel() {
        return _forecastingFormula;
    }

    @Override
    public void resetOriginalValues() {
        _forecastingFormula.resetOriginalValues();
    }
}
