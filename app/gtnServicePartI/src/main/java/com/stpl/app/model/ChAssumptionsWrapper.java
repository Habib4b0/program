package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChAssumptions}.
 * </p>
 *
 * @author
 * @see ChAssumptions
 * @generated
 */
public class ChAssumptionsWrapper implements ChAssumptions,
    ModelWrapper<ChAssumptions> {
    private ChAssumptions _chAssumptions;

    public ChAssumptionsWrapper(ChAssumptions chAssumptions) {
        _chAssumptions = chAssumptions;
    }

    @Override
    public Class<?> getModelClass() {
        return ChAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return ChAssumptions.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("parent", getParent());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("commentary", getCommentary());
        attributes.put("quarter", getQuarter());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("reasonCodes", getReasonCodes());
        attributes.put("year", getYear());
        attributes.put("totalDiscountPercentProjected",
            getTotalDiscountPercentProjected());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("chAssumptionsSid", getChAssumptionsSid());
        attributes.put("totalDiscountChange", getTotalDiscountChange());
        attributes.put("totalDiscountProjected", getTotalDiscountProjected());
        attributes.put("camId", getCamId());
        attributes.put("grossTradeSales", getGrossTradeSales());
        attributes.put("totalDiscountPrior", getTotalDiscountPrior());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean parent = (Boolean) attributes.get("parent");

        if (parent != null) {
            setParent(parent);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        String commentary = (String) attributes.get("commentary");

        if (commentary != null) {
            setCommentary(commentary);
        }

        Integer quarter = (Integer) attributes.get("quarter");

        if (quarter != null) {
            setQuarter(quarter);
        }

        Double totalDiscountPercentChange = (Double) attributes.get(
                "totalDiscountPercentChange");

        if (totalDiscountPercentChange != null) {
            setTotalDiscountPercentChange(totalDiscountPercentChange);
        }

        String reasonCodes = (String) attributes.get("reasonCodes");

        if (reasonCodes != null) {
            setReasonCodes(reasonCodes);
        }

        Integer year = (Integer) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        Double totalDiscountPercentProjected = (Double) attributes.get(
                "totalDiscountPercentProjected");

        if (totalDiscountPercentProjected != null) {
            setTotalDiscountPercentProjected(totalDiscountPercentProjected);
        }

        Double totalDiscountPercentPrior = (Double) attributes.get(
                "totalDiscountPercentPrior");

        if (totalDiscountPercentPrior != null) {
            setTotalDiscountPercentPrior(totalDiscountPercentPrior);
        }

        Integer chAssumptionsSid = (Integer) attributes.get("chAssumptionsSid");

        if (chAssumptionsSid != null) {
            setChAssumptionsSid(chAssumptionsSid);
        }

        Double totalDiscountChange = (Double) attributes.get(
                "totalDiscountChange");

        if (totalDiscountChange != null) {
            setTotalDiscountChange(totalDiscountChange);
        }

        Double totalDiscountProjected = (Double) attributes.get(
                "totalDiscountProjected");

        if (totalDiscountProjected != null) {
            setTotalDiscountProjected(totalDiscountProjected);
        }

        Integer camId = (Integer) attributes.get("camId");

        if (camId != null) {
            setCamId(camId);
        }

        Double grossTradeSales = (Double) attributes.get("grossTradeSales");

        if (grossTradeSales != null) {
            setGrossTradeSales(grossTradeSales);
        }

        Double totalDiscountPrior = (Double) attributes.get(
                "totalDiscountPrior");

        if (totalDiscountPrior != null) {
            setTotalDiscountPrior(totalDiscountPrior);
        }
    }

    /**
    * Returns the primary key of this ch assumptions.
    *
    * @return the primary key of this ch assumptions
    */
    @Override
    public int getPrimaryKey() {
        return _chAssumptions.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ch assumptions.
    *
    * @param primaryKey the primary key of this ch assumptions
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _chAssumptions.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the parent of this ch assumptions.
    *
    * @return the parent of this ch assumptions
    */
    @Override
    public boolean getParent() {
        return _chAssumptions.getParent();
    }

    /**
    * Returns <code>true</code> if this ch assumptions is parent.
    *
    * @return <code>true</code> if this ch assumptions is parent; <code>false</code> otherwise
    */
    @Override
    public boolean isParent() {
        return _chAssumptions.isParent();
    }

    /**
    * Sets whether this ch assumptions is parent.
    *
    * @param parent the parent of this ch assumptions
    */
    @Override
    public void setParent(boolean parent) {
        _chAssumptions.setParent(parent);
    }

    /**
    * Returns the projection details sid of this ch assumptions.
    *
    * @return the projection details sid of this ch assumptions
    */
    @Override
    public int getProjectionDetailsSid() {
        return _chAssumptions.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this ch assumptions.
    *
    * @param projectionDetailsSid the projection details sid of this ch assumptions
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _chAssumptions.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the commentary of this ch assumptions.
    *
    * @return the commentary of this ch assumptions
    */
    @Override
    public java.lang.String getCommentary() {
        return _chAssumptions.getCommentary();
    }

    /**
    * Sets the commentary of this ch assumptions.
    *
    * @param commentary the commentary of this ch assumptions
    */
    @Override
    public void setCommentary(java.lang.String commentary) {
        _chAssumptions.setCommentary(commentary);
    }

    /**
    * Returns the quarter of this ch assumptions.
    *
    * @return the quarter of this ch assumptions
    */
    @Override
    public int getQuarter() {
        return _chAssumptions.getQuarter();
    }

    /**
    * Sets the quarter of this ch assumptions.
    *
    * @param quarter the quarter of this ch assumptions
    */
    @Override
    public void setQuarter(int quarter) {
        _chAssumptions.setQuarter(quarter);
    }

    /**
    * Returns the total discount percent change of this ch assumptions.
    *
    * @return the total discount percent change of this ch assumptions
    */
    @Override
    public double getTotalDiscountPercentChange() {
        return _chAssumptions.getTotalDiscountPercentChange();
    }

    /**
    * Sets the total discount percent change of this ch assumptions.
    *
    * @param totalDiscountPercentChange the total discount percent change of this ch assumptions
    */
    @Override
    public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
        _chAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
    }

    /**
    * Returns the reason codes of this ch assumptions.
    *
    * @return the reason codes of this ch assumptions
    */
    @Override
    public java.lang.String getReasonCodes() {
        return _chAssumptions.getReasonCodes();
    }

    /**
    * Sets the reason codes of this ch assumptions.
    *
    * @param reasonCodes the reason codes of this ch assumptions
    */
    @Override
    public void setReasonCodes(java.lang.String reasonCodes) {
        _chAssumptions.setReasonCodes(reasonCodes);
    }

    /**
    * Returns the year of this ch assumptions.
    *
    * @return the year of this ch assumptions
    */
    @Override
    public int getYear() {
        return _chAssumptions.getYear();
    }

    /**
    * Sets the year of this ch assumptions.
    *
    * @param year the year of this ch assumptions
    */
    @Override
    public void setYear(int year) {
        _chAssumptions.setYear(year);
    }

    /**
    * Returns the total discount percent projected of this ch assumptions.
    *
    * @return the total discount percent projected of this ch assumptions
    */
    @Override
    public double getTotalDiscountPercentProjected() {
        return _chAssumptions.getTotalDiscountPercentProjected();
    }

    /**
    * Sets the total discount percent projected of this ch assumptions.
    *
    * @param totalDiscountPercentProjected the total discount percent projected of this ch assumptions
    */
    @Override
    public void setTotalDiscountPercentProjected(
        double totalDiscountPercentProjected) {
        _chAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
    }

    /**
    * Returns the total discount percent prior of this ch assumptions.
    *
    * @return the total discount percent prior of this ch assumptions
    */
    @Override
    public double getTotalDiscountPercentPrior() {
        return _chAssumptions.getTotalDiscountPercentPrior();
    }

    /**
    * Sets the total discount percent prior of this ch assumptions.
    *
    * @param totalDiscountPercentPrior the total discount percent prior of this ch assumptions
    */
    @Override
    public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
        _chAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
    }

    /**
    * Returns the ch assumptions sid of this ch assumptions.
    *
    * @return the ch assumptions sid of this ch assumptions
    */
    @Override
    public int getChAssumptionsSid() {
        return _chAssumptions.getChAssumptionsSid();
    }

    /**
    * Sets the ch assumptions sid of this ch assumptions.
    *
    * @param chAssumptionsSid the ch assumptions sid of this ch assumptions
    */
    @Override
    public void setChAssumptionsSid(int chAssumptionsSid) {
        _chAssumptions.setChAssumptionsSid(chAssumptionsSid);
    }

    /**
    * Returns the total discount change of this ch assumptions.
    *
    * @return the total discount change of this ch assumptions
    */
    @Override
    public double getTotalDiscountChange() {
        return _chAssumptions.getTotalDiscountChange();
    }

    /**
    * Sets the total discount change of this ch assumptions.
    *
    * @param totalDiscountChange the total discount change of this ch assumptions
    */
    @Override
    public void setTotalDiscountChange(double totalDiscountChange) {
        _chAssumptions.setTotalDiscountChange(totalDiscountChange);
    }

    /**
    * Returns the total discount projected of this ch assumptions.
    *
    * @return the total discount projected of this ch assumptions
    */
    @Override
    public double getTotalDiscountProjected() {
        return _chAssumptions.getTotalDiscountProjected();
    }

    /**
    * Sets the total discount projected of this ch assumptions.
    *
    * @param totalDiscountProjected the total discount projected of this ch assumptions
    */
    @Override
    public void setTotalDiscountProjected(double totalDiscountProjected) {
        _chAssumptions.setTotalDiscountProjected(totalDiscountProjected);
    }

    /**
    * Returns the cam ID of this ch assumptions.
    *
    * @return the cam ID of this ch assumptions
    */
    @Override
    public int getCamId() {
        return _chAssumptions.getCamId();
    }

    /**
    * Sets the cam ID of this ch assumptions.
    *
    * @param camId the cam ID of this ch assumptions
    */
    @Override
    public void setCamId(int camId) {
        _chAssumptions.setCamId(camId);
    }

    /**
    * Returns the gross trade sales of this ch assumptions.
    *
    * @return the gross trade sales of this ch assumptions
    */
    @Override
    public double getGrossTradeSales() {
        return _chAssumptions.getGrossTradeSales();
    }

    /**
    * Sets the gross trade sales of this ch assumptions.
    *
    * @param grossTradeSales the gross trade sales of this ch assumptions
    */
    @Override
    public void setGrossTradeSales(double grossTradeSales) {
        _chAssumptions.setGrossTradeSales(grossTradeSales);
    }

    /**
    * Returns the total discount prior of this ch assumptions.
    *
    * @return the total discount prior of this ch assumptions
    */
    @Override
    public double getTotalDiscountPrior() {
        return _chAssumptions.getTotalDiscountPrior();
    }

    /**
    * Sets the total discount prior of this ch assumptions.
    *
    * @param totalDiscountPrior the total discount prior of this ch assumptions
    */
    @Override
    public void setTotalDiscountPrior(double totalDiscountPrior) {
        _chAssumptions.setTotalDiscountPrior(totalDiscountPrior);
    }

    @Override
    public boolean isNew() {
        return _chAssumptions.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _chAssumptions.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _chAssumptions.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _chAssumptions.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _chAssumptions.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _chAssumptions.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _chAssumptions.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _chAssumptions.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _chAssumptions.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _chAssumptions.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _chAssumptions.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ChAssumptionsWrapper((ChAssumptions) _chAssumptions.clone());
    }

    @Override
    public int compareTo(ChAssumptions chAssumptions) {
        return _chAssumptions.compareTo(chAssumptions);
    }

    @Override
    public int hashCode() {
        return _chAssumptions.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ChAssumptions> toCacheModel() {
        return _chAssumptions.toCacheModel();
    }

    @Override
    public ChAssumptions toEscapedModel() {
        return new ChAssumptionsWrapper(_chAssumptions.toEscapedModel());
    }

    @Override
    public ChAssumptions toUnescapedModel() {
        return new ChAssumptionsWrapper(_chAssumptions.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _chAssumptions.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _chAssumptions.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _chAssumptions.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChAssumptionsWrapper)) {
            return false;
        }

        ChAssumptionsWrapper chAssumptionsWrapper = (ChAssumptionsWrapper) obj;

        if (Validator.equals(_chAssumptions, chAssumptionsWrapper._chAssumptions)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ChAssumptions getWrappedChAssumptions() {
        return _chAssumptions;
    }

    @Override
    public ChAssumptions getWrappedModel() {
        return _chAssumptions;
    }

    @Override
    public void resetOriginalValues() {
        _chAssumptions.resetOriginalValues();
    }
}
