package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MAssumptions}.
 * </p>
 *
 * @author
 * @see MAssumptions
 * @generated
 */
public class MAssumptionsWrapper implements MAssumptions,
    ModelWrapper<MAssumptions> {
    private MAssumptions _mAssumptions;

    public MAssumptionsWrapper(MAssumptions mAssumptions) {
        _mAssumptions = mAssumptions;
    }

    @Override
    public Class<?> getModelClass() {
        return MAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return MAssumptions.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("grossSalesPercentChange", getGrossSalesPercentChange());
        attributes.put("grossSalesPrior", getGrossSalesPrior());
        attributes.put("projYear", getProjYear());
        attributes.put("totalDiscountPercentProjected",
            getTotalDiscountPercentProjected());
        attributes.put("camId", getCamId());
        attributes.put("commentary", getCommentary());
        attributes.put("grossSalesProjected", getGrossSalesProjected());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("netSalesPercentChange", getNetSalesPercentChange());
        attributes.put("parent", getParent());
        attributes.put("projectionPeriod", getProjectionPeriod());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("netSalesPrior", getNetSalesPrior());
        attributes.put("netSalesProjected", getNetSalesProjected());
        attributes.put("reasonCodes", getReasonCodes());
        attributes.put("mAssumptionsSid", getMAssumptionsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double grossSalesPercentChange = (Double) attributes.get(
                "grossSalesPercentChange");

        if (grossSalesPercentChange != null) {
            setGrossSalesPercentChange(grossSalesPercentChange);
        }

        Double grossSalesPrior = (Double) attributes.get("grossSalesPrior");

        if (grossSalesPrior != null) {
            setGrossSalesPrior(grossSalesPrior);
        }

        Integer projYear = (Integer) attributes.get("projYear");

        if (projYear != null) {
            setProjYear(projYear);
        }

        Double totalDiscountPercentProjected = (Double) attributes.get(
                "totalDiscountPercentProjected");

        if (totalDiscountPercentProjected != null) {
            setTotalDiscountPercentProjected(totalDiscountPercentProjected);
        }

        Integer camId = (Integer) attributes.get("camId");

        if (camId != null) {
            setCamId(camId);
        }

        String commentary = (String) attributes.get("commentary");

        if (commentary != null) {
            setCommentary(commentary);
        }

        Double grossSalesProjected = (Double) attributes.get(
                "grossSalesProjected");

        if (grossSalesProjected != null) {
            setGrossSalesProjected(grossSalesProjected);
        }

        Double totalDiscountPercentChange = (Double) attributes.get(
                "totalDiscountPercentChange");

        if (totalDiscountPercentChange != null) {
            setTotalDiscountPercentChange(totalDiscountPercentChange);
        }

        Double totalDiscountPercentPrior = (Double) attributes.get(
                "totalDiscountPercentPrior");

        if (totalDiscountPercentPrior != null) {
            setTotalDiscountPercentPrior(totalDiscountPercentPrior);
        }

        Double netSalesPercentChange = (Double) attributes.get(
                "netSalesPercentChange");

        if (netSalesPercentChange != null) {
            setNetSalesPercentChange(netSalesPercentChange);
        }

        Boolean parent = (Boolean) attributes.get("parent");

        if (parent != null) {
            setParent(parent);
        }

        Integer projectionPeriod = (Integer) attributes.get("projectionPeriod");

        if (projectionPeriod != null) {
            setProjectionPeriod(projectionPeriod);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double netSalesPrior = (Double) attributes.get("netSalesPrior");

        if (netSalesPrior != null) {
            setNetSalesPrior(netSalesPrior);
        }

        Double netSalesProjected = (Double) attributes.get("netSalesProjected");

        if (netSalesProjected != null) {
            setNetSalesProjected(netSalesProjected);
        }

        String reasonCodes = (String) attributes.get("reasonCodes");

        if (reasonCodes != null) {
            setReasonCodes(reasonCodes);
        }

        Integer mAssumptionsSid = (Integer) attributes.get("mAssumptionsSid");

        if (mAssumptionsSid != null) {
            setMAssumptionsSid(mAssumptionsSid);
        }
    }

    /**
    * Returns the primary key of this m assumptions.
    *
    * @return the primary key of this m assumptions
    */
    @Override
    public int getPrimaryKey() {
        return _mAssumptions.getPrimaryKey();
    }

    /**
    * Sets the primary key of this m assumptions.
    *
    * @param primaryKey the primary key of this m assumptions
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _mAssumptions.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the gross sales percent change of this m assumptions.
    *
    * @return the gross sales percent change of this m assumptions
    */
    @Override
    public double getGrossSalesPercentChange() {
        return _mAssumptions.getGrossSalesPercentChange();
    }

    /**
    * Sets the gross sales percent change of this m assumptions.
    *
    * @param grossSalesPercentChange the gross sales percent change of this m assumptions
    */
    @Override
    public void setGrossSalesPercentChange(double grossSalesPercentChange) {
        _mAssumptions.setGrossSalesPercentChange(grossSalesPercentChange);
    }

    /**
    * Returns the gross sales prior of this m assumptions.
    *
    * @return the gross sales prior of this m assumptions
    */
    @Override
    public double getGrossSalesPrior() {
        return _mAssumptions.getGrossSalesPrior();
    }

    /**
    * Sets the gross sales prior of this m assumptions.
    *
    * @param grossSalesPrior the gross sales prior of this m assumptions
    */
    @Override
    public void setGrossSalesPrior(double grossSalesPrior) {
        _mAssumptions.setGrossSalesPrior(grossSalesPrior);
    }

    /**
    * Returns the proj year of this m assumptions.
    *
    * @return the proj year of this m assumptions
    */
    @Override
    public int getProjYear() {
        return _mAssumptions.getProjYear();
    }

    /**
    * Sets the proj year of this m assumptions.
    *
    * @param projYear the proj year of this m assumptions
    */
    @Override
    public void setProjYear(int projYear) {
        _mAssumptions.setProjYear(projYear);
    }

    /**
    * Returns the total discount percent projected of this m assumptions.
    *
    * @return the total discount percent projected of this m assumptions
    */
    @Override
    public double getTotalDiscountPercentProjected() {
        return _mAssumptions.getTotalDiscountPercentProjected();
    }

    /**
    * Sets the total discount percent projected of this m assumptions.
    *
    * @param totalDiscountPercentProjected the total discount percent projected of this m assumptions
    */
    @Override
    public void setTotalDiscountPercentProjected(
        double totalDiscountPercentProjected) {
        _mAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
    }

    /**
    * Returns the cam ID of this m assumptions.
    *
    * @return the cam ID of this m assumptions
    */
    @Override
    public int getCamId() {
        return _mAssumptions.getCamId();
    }

    /**
    * Sets the cam ID of this m assumptions.
    *
    * @param camId the cam ID of this m assumptions
    */
    @Override
    public void setCamId(int camId) {
        _mAssumptions.setCamId(camId);
    }

    /**
    * Returns the commentary of this m assumptions.
    *
    * @return the commentary of this m assumptions
    */
    @Override
    public java.lang.String getCommentary() {
        return _mAssumptions.getCommentary();
    }

    /**
    * Sets the commentary of this m assumptions.
    *
    * @param commentary the commentary of this m assumptions
    */
    @Override
    public void setCommentary(java.lang.String commentary) {
        _mAssumptions.setCommentary(commentary);
    }

    /**
    * Returns the gross sales projected of this m assumptions.
    *
    * @return the gross sales projected of this m assumptions
    */
    @Override
    public double getGrossSalesProjected() {
        return _mAssumptions.getGrossSalesProjected();
    }

    /**
    * Sets the gross sales projected of this m assumptions.
    *
    * @param grossSalesProjected the gross sales projected of this m assumptions
    */
    @Override
    public void setGrossSalesProjected(double grossSalesProjected) {
        _mAssumptions.setGrossSalesProjected(grossSalesProjected);
    }

    /**
    * Returns the total discount percent change of this m assumptions.
    *
    * @return the total discount percent change of this m assumptions
    */
    @Override
    public double getTotalDiscountPercentChange() {
        return _mAssumptions.getTotalDiscountPercentChange();
    }

    /**
    * Sets the total discount percent change of this m assumptions.
    *
    * @param totalDiscountPercentChange the total discount percent change of this m assumptions
    */
    @Override
    public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
        _mAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
    }

    /**
    * Returns the total discount percent prior of this m assumptions.
    *
    * @return the total discount percent prior of this m assumptions
    */
    @Override
    public double getTotalDiscountPercentPrior() {
        return _mAssumptions.getTotalDiscountPercentPrior();
    }

    /**
    * Sets the total discount percent prior of this m assumptions.
    *
    * @param totalDiscountPercentPrior the total discount percent prior of this m assumptions
    */
    @Override
    public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
        _mAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
    }

    /**
    * Returns the net sales percent change of this m assumptions.
    *
    * @return the net sales percent change of this m assumptions
    */
    @Override
    public double getNetSalesPercentChange() {
        return _mAssumptions.getNetSalesPercentChange();
    }

    /**
    * Sets the net sales percent change of this m assumptions.
    *
    * @param netSalesPercentChange the net sales percent change of this m assumptions
    */
    @Override
    public void setNetSalesPercentChange(double netSalesPercentChange) {
        _mAssumptions.setNetSalesPercentChange(netSalesPercentChange);
    }

    /**
    * Returns the parent of this m assumptions.
    *
    * @return the parent of this m assumptions
    */
    @Override
    public boolean getParent() {
        return _mAssumptions.getParent();
    }

    /**
    * Returns <code>true</code> if this m assumptions is parent.
    *
    * @return <code>true</code> if this m assumptions is parent; <code>false</code> otherwise
    */
    @Override
    public boolean isParent() {
        return _mAssumptions.isParent();
    }

    /**
    * Sets whether this m assumptions is parent.
    *
    * @param parent the parent of this m assumptions
    */
    @Override
    public void setParent(boolean parent) {
        _mAssumptions.setParent(parent);
    }

    /**
    * Returns the projection period of this m assumptions.
    *
    * @return the projection period of this m assumptions
    */
    @Override
    public int getProjectionPeriod() {
        return _mAssumptions.getProjectionPeriod();
    }

    /**
    * Sets the projection period of this m assumptions.
    *
    * @param projectionPeriod the projection period of this m assumptions
    */
    @Override
    public void setProjectionPeriod(int projectionPeriod) {
        _mAssumptions.setProjectionPeriod(projectionPeriod);
    }

    /**
    * Returns the projection details sid of this m assumptions.
    *
    * @return the projection details sid of this m assumptions
    */
    @Override
    public int getProjectionDetailsSid() {
        return _mAssumptions.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this m assumptions.
    *
    * @param projectionDetailsSid the projection details sid of this m assumptions
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _mAssumptions.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the net sales prior of this m assumptions.
    *
    * @return the net sales prior of this m assumptions
    */
    @Override
    public double getNetSalesPrior() {
        return _mAssumptions.getNetSalesPrior();
    }

    /**
    * Sets the net sales prior of this m assumptions.
    *
    * @param netSalesPrior the net sales prior of this m assumptions
    */
    @Override
    public void setNetSalesPrior(double netSalesPrior) {
        _mAssumptions.setNetSalesPrior(netSalesPrior);
    }

    /**
    * Returns the net sales projected of this m assumptions.
    *
    * @return the net sales projected of this m assumptions
    */
    @Override
    public double getNetSalesProjected() {
        return _mAssumptions.getNetSalesProjected();
    }

    /**
    * Sets the net sales projected of this m assumptions.
    *
    * @param netSalesProjected the net sales projected of this m assumptions
    */
    @Override
    public void setNetSalesProjected(double netSalesProjected) {
        _mAssumptions.setNetSalesProjected(netSalesProjected);
    }

    /**
    * Returns the reason codes of this m assumptions.
    *
    * @return the reason codes of this m assumptions
    */
    @Override
    public java.lang.String getReasonCodes() {
        return _mAssumptions.getReasonCodes();
    }

    /**
    * Sets the reason codes of this m assumptions.
    *
    * @param reasonCodes the reason codes of this m assumptions
    */
    @Override
    public void setReasonCodes(java.lang.String reasonCodes) {
        _mAssumptions.setReasonCodes(reasonCodes);
    }

    /**
    * Returns the m assumptions sid of this m assumptions.
    *
    * @return the m assumptions sid of this m assumptions
    */
    @Override
    public int getMAssumptionsSid() {
        return _mAssumptions.getMAssumptionsSid();
    }

    /**
    * Sets the m assumptions sid of this m assumptions.
    *
    * @param mAssumptionsSid the m assumptions sid of this m assumptions
    */
    @Override
    public void setMAssumptionsSid(int mAssumptionsSid) {
        _mAssumptions.setMAssumptionsSid(mAssumptionsSid);
    }

    @Override
    public boolean isNew() {
        return _mAssumptions.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _mAssumptions.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _mAssumptions.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _mAssumptions.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _mAssumptions.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _mAssumptions.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _mAssumptions.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _mAssumptions.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _mAssumptions.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _mAssumptions.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _mAssumptions.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MAssumptionsWrapper((MAssumptions) _mAssumptions.clone());
    }

    @Override
    public int compareTo(MAssumptions mAssumptions) {
        return _mAssumptions.compareTo(mAssumptions);
    }

    @Override
    public int hashCode() {
        return _mAssumptions.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MAssumptions> toCacheModel() {
        return _mAssumptions.toCacheModel();
    }

    @Override
    public MAssumptions toEscapedModel() {
        return new MAssumptionsWrapper(_mAssumptions.toEscapedModel());
    }

    @Override
    public MAssumptions toUnescapedModel() {
        return new MAssumptionsWrapper(_mAssumptions.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _mAssumptions.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _mAssumptions.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _mAssumptions.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MAssumptionsWrapper)) {
            return false;
        }

        MAssumptionsWrapper mAssumptionsWrapper = (MAssumptionsWrapper) obj;

        if (Validator.equals(_mAssumptions, mAssumptionsWrapper._mAssumptions)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MAssumptions getWrappedMAssumptions() {
        return _mAssumptions;
    }

    @Override
    public MAssumptions getWrappedModel() {
        return _mAssumptions;
    }

    @Override
    public void resetOriginalValues() {
        _mAssumptions.resetOriginalValues();
    }
}
