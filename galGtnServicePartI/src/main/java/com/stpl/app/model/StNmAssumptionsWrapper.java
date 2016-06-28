package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StNmAssumptions}.
 * </p>
 *
 * @author
 * @see StNmAssumptions
 * @generated
 */
public class StNmAssumptionsWrapper implements StNmAssumptions,
    ModelWrapper<StNmAssumptions> {
    private StNmAssumptions _stNmAssumptions;

    public StNmAssumptionsWrapper(StNmAssumptions stNmAssumptions) {
        _stNmAssumptions = stNmAssumptions;
    }

    @Override
    public Class<?> getModelClass() {
        return StNmAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return StNmAssumptions.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("parent", getParent());
        attributes.put("projectionPeriod", getProjectionPeriod());
        attributes.put("commentary", getCommentary());
        attributes.put("nmAssumptionsSid", getNmAssumptionsSid());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("netSalesPrior", getNetSalesPrior());
        attributes.put("userId", getUserId());
        attributes.put("grossSalesPercentChange", getGrossSalesPercentChange());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("reasonCodes", getReasonCodes());
        attributes.put("totalDiscountPercentProjected",
            getTotalDiscountPercentProjected());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("netSalesProjected", getNetSalesProjected());
        attributes.put("stNmAssumptionsSid", getStNmAssumptionsSid());
        attributes.put("grossSalesProjected", getGrossSalesProjected());
        attributes.put("sessionId", getSessionId());
        attributes.put("grossSalesPrior", getGrossSalesPrior());
        attributes.put("isChecked", getIsChecked());
        attributes.put("camId", getCamId());
        attributes.put("netSalesPercentChange", getNetSalesPercentChange());
        attributes.put("segment", getSegment());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Boolean parent = (Boolean) attributes.get("parent");

        if (parent != null) {
            setParent(parent);
        }

        Integer projectionPeriod = (Integer) attributes.get("projectionPeriod");

        if (projectionPeriod != null) {
            setProjectionPeriod(projectionPeriod);
        }

        String commentary = (String) attributes.get("commentary");

        if (commentary != null) {
            setCommentary(commentary);
        }

        Integer nmAssumptionsSid = (Integer) attributes.get("nmAssumptionsSid");

        if (nmAssumptionsSid != null) {
            setNmAssumptionsSid(nmAssumptionsSid);
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

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Double grossSalesPercentChange = (Double) attributes.get(
                "grossSalesPercentChange");

        if (grossSalesPercentChange != null) {
            setGrossSalesPercentChange(grossSalesPercentChange);
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

        Double netSalesProjected = (Double) attributes.get("netSalesProjected");

        if (netSalesProjected != null) {
            setNetSalesProjected(netSalesProjected);
        }

        String stNmAssumptionsSid = (String) attributes.get(
                "stNmAssumptionsSid");

        if (stNmAssumptionsSid != null) {
            setStNmAssumptionsSid(stNmAssumptionsSid);
        }

        Double grossSalesProjected = (Double) attributes.get(
                "grossSalesProjected");

        if (grossSalesProjected != null) {
            setGrossSalesProjected(grossSalesProjected);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double grossSalesPrior = (Double) attributes.get("grossSalesPrior");

        if (grossSalesPrior != null) {
            setGrossSalesPrior(grossSalesPrior);
        }

        Boolean isChecked = (Boolean) attributes.get("isChecked");

        if (isChecked != null) {
            setIsChecked(isChecked);
        }

        Integer camId = (Integer) attributes.get("camId");

        if (camId != null) {
            setCamId(camId);
        }

        Double netSalesPercentChange = (Double) attributes.get(
                "netSalesPercentChange");

        if (netSalesPercentChange != null) {
            setNetSalesPercentChange(netSalesPercentChange);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }
    }

    /**
    * Returns the primary key of this st nm assumptions.
    *
    * @return the primary key of this st nm assumptions
    */
    @Override
    public com.stpl.app.service.persistence.StNmAssumptionsPK getPrimaryKey() {
        return _stNmAssumptions.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st nm assumptions.
    *
    * @param primaryKey the primary key of this st nm assumptions
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StNmAssumptionsPK primaryKey) {
        _stNmAssumptions.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the last modified date of this st nm assumptions.
    *
    * @return the last modified date of this st nm assumptions
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stNmAssumptions.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st nm assumptions.
    *
    * @param lastModifiedDate the last modified date of this st nm assumptions
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stNmAssumptions.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the parent of this st nm assumptions.
    *
    * @return the parent of this st nm assumptions
    */
    @Override
    public boolean getParent() {
        return _stNmAssumptions.getParent();
    }

    /**
    * Returns <code>true</code> if this st nm assumptions is parent.
    *
    * @return <code>true</code> if this st nm assumptions is parent; <code>false</code> otherwise
    */
    @Override
    public boolean isParent() {
        return _stNmAssumptions.isParent();
    }

    /**
    * Sets whether this st nm assumptions is parent.
    *
    * @param parent the parent of this st nm assumptions
    */
    @Override
    public void setParent(boolean parent) {
        _stNmAssumptions.setParent(parent);
    }

    /**
    * Returns the projection period of this st nm assumptions.
    *
    * @return the projection period of this st nm assumptions
    */
    @Override
    public int getProjectionPeriod() {
        return _stNmAssumptions.getProjectionPeriod();
    }

    /**
    * Sets the projection period of this st nm assumptions.
    *
    * @param projectionPeriod the projection period of this st nm assumptions
    */
    @Override
    public void setProjectionPeriod(int projectionPeriod) {
        _stNmAssumptions.setProjectionPeriod(projectionPeriod);
    }

    /**
    * Returns the commentary of this st nm assumptions.
    *
    * @return the commentary of this st nm assumptions
    */
    @Override
    public java.lang.String getCommentary() {
        return _stNmAssumptions.getCommentary();
    }

    /**
    * Sets the commentary of this st nm assumptions.
    *
    * @param commentary the commentary of this st nm assumptions
    */
    @Override
    public void setCommentary(java.lang.String commentary) {
        _stNmAssumptions.setCommentary(commentary);
    }

    /**
    * Returns the nm assumptions sid of this st nm assumptions.
    *
    * @return the nm assumptions sid of this st nm assumptions
    */
    @Override
    public int getNmAssumptionsSid() {
        return _stNmAssumptions.getNmAssumptionsSid();
    }

    /**
    * Sets the nm assumptions sid of this st nm assumptions.
    *
    * @param nmAssumptionsSid the nm assumptions sid of this st nm assumptions
    */
    @Override
    public void setNmAssumptionsSid(int nmAssumptionsSid) {
        _stNmAssumptions.setNmAssumptionsSid(nmAssumptionsSid);
    }

    /**
    * Returns the projection details sid of this st nm assumptions.
    *
    * @return the projection details sid of this st nm assumptions
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stNmAssumptions.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st nm assumptions.
    *
    * @param projectionDetailsSid the projection details sid of this st nm assumptions
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stNmAssumptions.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the net sales prior of this st nm assumptions.
    *
    * @return the net sales prior of this st nm assumptions
    */
    @Override
    public double getNetSalesPrior() {
        return _stNmAssumptions.getNetSalesPrior();
    }

    /**
    * Sets the net sales prior of this st nm assumptions.
    *
    * @param netSalesPrior the net sales prior of this st nm assumptions
    */
    @Override
    public void setNetSalesPrior(double netSalesPrior) {
        _stNmAssumptions.setNetSalesPrior(netSalesPrior);
    }

    /**
    * Returns the user ID of this st nm assumptions.
    *
    * @return the user ID of this st nm assumptions
    */
    @Override
    public int getUserId() {
        return _stNmAssumptions.getUserId();
    }

    /**
    * Sets the user ID of this st nm assumptions.
    *
    * @param userId the user ID of this st nm assumptions
    */
    @Override
    public void setUserId(int userId) {
        _stNmAssumptions.setUserId(userId);
    }

    /**
    * Returns the gross sales percent change of this st nm assumptions.
    *
    * @return the gross sales percent change of this st nm assumptions
    */
    @Override
    public double getGrossSalesPercentChange() {
        return _stNmAssumptions.getGrossSalesPercentChange();
    }

    /**
    * Sets the gross sales percent change of this st nm assumptions.
    *
    * @param grossSalesPercentChange the gross sales percent change of this st nm assumptions
    */
    @Override
    public void setGrossSalesPercentChange(double grossSalesPercentChange) {
        _stNmAssumptions.setGrossSalesPercentChange(grossSalesPercentChange);
    }

    /**
    * Returns the total discount percent change of this st nm assumptions.
    *
    * @return the total discount percent change of this st nm assumptions
    */
    @Override
    public double getTotalDiscountPercentChange() {
        return _stNmAssumptions.getTotalDiscountPercentChange();
    }

    /**
    * Sets the total discount percent change of this st nm assumptions.
    *
    * @param totalDiscountPercentChange the total discount percent change of this st nm assumptions
    */
    @Override
    public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
        _stNmAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
    }

    /**
    * Returns the reason codes of this st nm assumptions.
    *
    * @return the reason codes of this st nm assumptions
    */
    @Override
    public java.lang.String getReasonCodes() {
        return _stNmAssumptions.getReasonCodes();
    }

    /**
    * Sets the reason codes of this st nm assumptions.
    *
    * @param reasonCodes the reason codes of this st nm assumptions
    */
    @Override
    public void setReasonCodes(java.lang.String reasonCodes) {
        _stNmAssumptions.setReasonCodes(reasonCodes);
    }

    /**
    * Returns the total discount percent projected of this st nm assumptions.
    *
    * @return the total discount percent projected of this st nm assumptions
    */
    @Override
    public double getTotalDiscountPercentProjected() {
        return _stNmAssumptions.getTotalDiscountPercentProjected();
    }

    /**
    * Sets the total discount percent projected of this st nm assumptions.
    *
    * @param totalDiscountPercentProjected the total discount percent projected of this st nm assumptions
    */
    @Override
    public void setTotalDiscountPercentProjected(
        double totalDiscountPercentProjected) {
        _stNmAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
    }

    /**
    * Returns the total discount percent prior of this st nm assumptions.
    *
    * @return the total discount percent prior of this st nm assumptions
    */
    @Override
    public double getTotalDiscountPercentPrior() {
        return _stNmAssumptions.getTotalDiscountPercentPrior();
    }

    /**
    * Sets the total discount percent prior of this st nm assumptions.
    *
    * @param totalDiscountPercentPrior the total discount percent prior of this st nm assumptions
    */
    @Override
    public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
        _stNmAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
    }

    /**
    * Returns the net sales projected of this st nm assumptions.
    *
    * @return the net sales projected of this st nm assumptions
    */
    @Override
    public double getNetSalesProjected() {
        return _stNmAssumptions.getNetSalesProjected();
    }

    /**
    * Sets the net sales projected of this st nm assumptions.
    *
    * @param netSalesProjected the net sales projected of this st nm assumptions
    */
    @Override
    public void setNetSalesProjected(double netSalesProjected) {
        _stNmAssumptions.setNetSalesProjected(netSalesProjected);
    }

    /**
    * Returns the st nm assumptions sid of this st nm assumptions.
    *
    * @return the st nm assumptions sid of this st nm assumptions
    */
    @Override
    public java.lang.String getStNmAssumptionsSid() {
        return _stNmAssumptions.getStNmAssumptionsSid();
    }

    /**
    * Sets the st nm assumptions sid of this st nm assumptions.
    *
    * @param stNmAssumptionsSid the st nm assumptions sid of this st nm assumptions
    */
    @Override
    public void setStNmAssumptionsSid(java.lang.String stNmAssumptionsSid) {
        _stNmAssumptions.setStNmAssumptionsSid(stNmAssumptionsSid);
    }

    /**
    * Returns the gross sales projected of this st nm assumptions.
    *
    * @return the gross sales projected of this st nm assumptions
    */
    @Override
    public double getGrossSalesProjected() {
        return _stNmAssumptions.getGrossSalesProjected();
    }

    /**
    * Sets the gross sales projected of this st nm assumptions.
    *
    * @param grossSalesProjected the gross sales projected of this st nm assumptions
    */
    @Override
    public void setGrossSalesProjected(double grossSalesProjected) {
        _stNmAssumptions.setGrossSalesProjected(grossSalesProjected);
    }

    /**
    * Returns the session ID of this st nm assumptions.
    *
    * @return the session ID of this st nm assumptions
    */
    @Override
    public int getSessionId() {
        return _stNmAssumptions.getSessionId();
    }

    /**
    * Sets the session ID of this st nm assumptions.
    *
    * @param sessionId the session ID of this st nm assumptions
    */
    @Override
    public void setSessionId(int sessionId) {
        _stNmAssumptions.setSessionId(sessionId);
    }

    /**
    * Returns the gross sales prior of this st nm assumptions.
    *
    * @return the gross sales prior of this st nm assumptions
    */
    @Override
    public double getGrossSalesPrior() {
        return _stNmAssumptions.getGrossSalesPrior();
    }

    /**
    * Sets the gross sales prior of this st nm assumptions.
    *
    * @param grossSalesPrior the gross sales prior of this st nm assumptions
    */
    @Override
    public void setGrossSalesPrior(double grossSalesPrior) {
        _stNmAssumptions.setGrossSalesPrior(grossSalesPrior);
    }

    /**
    * Returns the is checked of this st nm assumptions.
    *
    * @return the is checked of this st nm assumptions
    */
    @Override
    public boolean getIsChecked() {
        return _stNmAssumptions.getIsChecked();
    }

    /**
    * Returns <code>true</code> if this st nm assumptions is is checked.
    *
    * @return <code>true</code> if this st nm assumptions is is checked; <code>false</code> otherwise
    */
    @Override
    public boolean isIsChecked() {
        return _stNmAssumptions.isIsChecked();
    }

    /**
    * Sets whether this st nm assumptions is is checked.
    *
    * @param isChecked the is checked of this st nm assumptions
    */
    @Override
    public void setIsChecked(boolean isChecked) {
        _stNmAssumptions.setIsChecked(isChecked);
    }

    /**
    * Returns the cam ID of this st nm assumptions.
    *
    * @return the cam ID of this st nm assumptions
    */
    @Override
    public int getCamId() {
        return _stNmAssumptions.getCamId();
    }

    /**
    * Sets the cam ID of this st nm assumptions.
    *
    * @param camId the cam ID of this st nm assumptions
    */
    @Override
    public void setCamId(int camId) {
        _stNmAssumptions.setCamId(camId);
    }

    /**
    * Returns the net sales percent change of this st nm assumptions.
    *
    * @return the net sales percent change of this st nm assumptions
    */
    @Override
    public double getNetSalesPercentChange() {
        return _stNmAssumptions.getNetSalesPercentChange();
    }

    /**
    * Sets the net sales percent change of this st nm assumptions.
    *
    * @param netSalesPercentChange the net sales percent change of this st nm assumptions
    */
    @Override
    public void setNetSalesPercentChange(double netSalesPercentChange) {
        _stNmAssumptions.setNetSalesPercentChange(netSalesPercentChange);
    }

    /**
    * Returns the segment of this st nm assumptions.
    *
    * @return the segment of this st nm assumptions
    */
    @Override
    public java.lang.String getSegment() {
        return _stNmAssumptions.getSegment();
    }

    /**
    * Sets the segment of this st nm assumptions.
    *
    * @param segment the segment of this st nm assumptions
    */
    @Override
    public void setSegment(java.lang.String segment) {
        _stNmAssumptions.setSegment(segment);
    }

    @Override
    public boolean isNew() {
        return _stNmAssumptions.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stNmAssumptions.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stNmAssumptions.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stNmAssumptions.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stNmAssumptions.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stNmAssumptions.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stNmAssumptions.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stNmAssumptions.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stNmAssumptions.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stNmAssumptions.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stNmAssumptions.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StNmAssumptionsWrapper((StNmAssumptions) _stNmAssumptions.clone());
    }

    @Override
    public int compareTo(StNmAssumptions stNmAssumptions) {
        return _stNmAssumptions.compareTo(stNmAssumptions);
    }

    @Override
    public int hashCode() {
        return _stNmAssumptions.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StNmAssumptions> toCacheModel() {
        return _stNmAssumptions.toCacheModel();
    }

    @Override
    public StNmAssumptions toEscapedModel() {
        return new StNmAssumptionsWrapper(_stNmAssumptions.toEscapedModel());
    }

    @Override
    public StNmAssumptions toUnescapedModel() {
        return new StNmAssumptionsWrapper(_stNmAssumptions.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stNmAssumptions.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stNmAssumptions.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stNmAssumptions.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmAssumptionsWrapper)) {
            return false;
        }

        StNmAssumptionsWrapper stNmAssumptionsWrapper = (StNmAssumptionsWrapper) obj;

        if (Validator.equals(_stNmAssumptions,
                    stNmAssumptionsWrapper._stNmAssumptions)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StNmAssumptions getWrappedStNmAssumptions() {
        return _stNmAssumptions;
    }

    @Override
    public StNmAssumptions getWrappedModel() {
        return _stNmAssumptions;
    }

    @Override
    public void resetOriginalValues() {
        _stNmAssumptions.resetOriginalValues();
    }
}
