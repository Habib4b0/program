package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StChAssumptions}.
 * </p>
 *
 * @author
 * @see StChAssumptions
 * @generated
 */
public class StChAssumptionsWrapper implements StChAssumptions,
    ModelWrapper<StChAssumptions> {
    private StChAssumptions _stChAssumptions;

    public StChAssumptionsWrapper(StChAssumptions stChAssumptions) {
        _stChAssumptions = stChAssumptions;
    }

    @Override
    public Class<?> getModelClass() {
        return StChAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return StChAssumptions.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("parent", getParent());
        attributes.put("commentary", getCommentary());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("quarter", getQuarter());
        attributes.put("totalDiscountPercentChange",
            getTotalDiscountPercentChange());
        attributes.put("reasonCodes", getReasonCodes());
        attributes.put("year", getYear());
        attributes.put("totalDiscountPercentProjected",
            getTotalDiscountPercentProjected());
        attributes.put("totalDiscountPercentPrior",
            getTotalDiscountPercentPrior());
        attributes.put("stChAssumptionsSid", getStChAssumptionsSid());
        attributes.put("chAssumptionsSid", getChAssumptionsSid());
        attributes.put("totalDiscountChange", getTotalDiscountChange());
        attributes.put("sessionId", getSessionId());
        attributes.put("totalDiscountProjected", getTotalDiscountProjected());
        attributes.put("isChecked", getIsChecked());
        attributes.put("camId", getCamId());
        attributes.put("grossTradeSales", getGrossTradeSales());
        attributes.put("totalDiscountPrior", getTotalDiscountPrior());

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

        String commentary = (String) attributes.get("commentary");

        if (commentary != null) {
            setCommentary(commentary);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
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

        String stChAssumptionsSid = (String) attributes.get(
                "stChAssumptionsSid");

        if (stChAssumptionsSid != null) {
            setStChAssumptionsSid(stChAssumptionsSid);
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

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double totalDiscountProjected = (Double) attributes.get(
                "totalDiscountProjected");

        if (totalDiscountProjected != null) {
            setTotalDiscountProjected(totalDiscountProjected);
        }

        Boolean isChecked = (Boolean) attributes.get("isChecked");

        if (isChecked != null) {
            setIsChecked(isChecked);
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
    * Returns the primary key of this st ch assumptions.
    *
    * @return the primary key of this st ch assumptions
    */
    @Override
    public com.stpl.app.service.persistence.StChAssumptionsPK getPrimaryKey() {
        return _stChAssumptions.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st ch assumptions.
    *
    * @param primaryKey the primary key of this st ch assumptions
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StChAssumptionsPK primaryKey) {
        _stChAssumptions.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the last modified date of this st ch assumptions.
    *
    * @return the last modified date of this st ch assumptions
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stChAssumptions.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st ch assumptions.
    *
    * @param lastModifiedDate the last modified date of this st ch assumptions
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stChAssumptions.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the parent of this st ch assumptions.
    *
    * @return the parent of this st ch assumptions
    */
    @Override
    public boolean getParent() {
        return _stChAssumptions.getParent();
    }

    /**
    * Returns <code>true</code> if this st ch assumptions is parent.
    *
    * @return <code>true</code> if this st ch assumptions is parent; <code>false</code> otherwise
    */
    @Override
    public boolean isParent() {
        return _stChAssumptions.isParent();
    }

    /**
    * Sets whether this st ch assumptions is parent.
    *
    * @param parent the parent of this st ch assumptions
    */
    @Override
    public void setParent(boolean parent) {
        _stChAssumptions.setParent(parent);
    }

    /**
    * Returns the commentary of this st ch assumptions.
    *
    * @return the commentary of this st ch assumptions
    */
    @Override
    public java.lang.String getCommentary() {
        return _stChAssumptions.getCommentary();
    }

    /**
    * Sets the commentary of this st ch assumptions.
    *
    * @param commentary the commentary of this st ch assumptions
    */
    @Override
    public void setCommentary(java.lang.String commentary) {
        _stChAssumptions.setCommentary(commentary);
    }

    /**
    * Returns the projection details sid of this st ch assumptions.
    *
    * @return the projection details sid of this st ch assumptions
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stChAssumptions.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st ch assumptions.
    *
    * @param projectionDetailsSid the projection details sid of this st ch assumptions
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stChAssumptions.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the user ID of this st ch assumptions.
    *
    * @return the user ID of this st ch assumptions
    */
    @Override
    public int getUserId() {
        return _stChAssumptions.getUserId();
    }

    /**
    * Sets the user ID of this st ch assumptions.
    *
    * @param userId the user ID of this st ch assumptions
    */
    @Override
    public void setUserId(int userId) {
        _stChAssumptions.setUserId(userId);
    }

    /**
    * Returns the quarter of this st ch assumptions.
    *
    * @return the quarter of this st ch assumptions
    */
    @Override
    public int getQuarter() {
        return _stChAssumptions.getQuarter();
    }

    /**
    * Sets the quarter of this st ch assumptions.
    *
    * @param quarter the quarter of this st ch assumptions
    */
    @Override
    public void setQuarter(int quarter) {
        _stChAssumptions.setQuarter(quarter);
    }

    /**
    * Returns the total discount percent change of this st ch assumptions.
    *
    * @return the total discount percent change of this st ch assumptions
    */
    @Override
    public double getTotalDiscountPercentChange() {
        return _stChAssumptions.getTotalDiscountPercentChange();
    }

    /**
    * Sets the total discount percent change of this st ch assumptions.
    *
    * @param totalDiscountPercentChange the total discount percent change of this st ch assumptions
    */
    @Override
    public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
        _stChAssumptions.setTotalDiscountPercentChange(totalDiscountPercentChange);
    }

    /**
    * Returns the reason codes of this st ch assumptions.
    *
    * @return the reason codes of this st ch assumptions
    */
    @Override
    public java.lang.String getReasonCodes() {
        return _stChAssumptions.getReasonCodes();
    }

    /**
    * Sets the reason codes of this st ch assumptions.
    *
    * @param reasonCodes the reason codes of this st ch assumptions
    */
    @Override
    public void setReasonCodes(java.lang.String reasonCodes) {
        _stChAssumptions.setReasonCodes(reasonCodes);
    }

    /**
    * Returns the year of this st ch assumptions.
    *
    * @return the year of this st ch assumptions
    */
    @Override
    public int getYear() {
        return _stChAssumptions.getYear();
    }

    /**
    * Sets the year of this st ch assumptions.
    *
    * @param year the year of this st ch assumptions
    */
    @Override
    public void setYear(int year) {
        _stChAssumptions.setYear(year);
    }

    /**
    * Returns the total discount percent projected of this st ch assumptions.
    *
    * @return the total discount percent projected of this st ch assumptions
    */
    @Override
    public double getTotalDiscountPercentProjected() {
        return _stChAssumptions.getTotalDiscountPercentProjected();
    }

    /**
    * Sets the total discount percent projected of this st ch assumptions.
    *
    * @param totalDiscountPercentProjected the total discount percent projected of this st ch assumptions
    */
    @Override
    public void setTotalDiscountPercentProjected(
        double totalDiscountPercentProjected) {
        _stChAssumptions.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
    }

    /**
    * Returns the total discount percent prior of this st ch assumptions.
    *
    * @return the total discount percent prior of this st ch assumptions
    */
    @Override
    public double getTotalDiscountPercentPrior() {
        return _stChAssumptions.getTotalDiscountPercentPrior();
    }

    /**
    * Sets the total discount percent prior of this st ch assumptions.
    *
    * @param totalDiscountPercentPrior the total discount percent prior of this st ch assumptions
    */
    @Override
    public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
        _stChAssumptions.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
    }

    /**
    * Returns the st ch assumptions sid of this st ch assumptions.
    *
    * @return the st ch assumptions sid of this st ch assumptions
    */
    @Override
    public java.lang.String getStChAssumptionsSid() {
        return _stChAssumptions.getStChAssumptionsSid();
    }

    /**
    * Sets the st ch assumptions sid of this st ch assumptions.
    *
    * @param stChAssumptionsSid the st ch assumptions sid of this st ch assumptions
    */
    @Override
    public void setStChAssumptionsSid(java.lang.String stChAssumptionsSid) {
        _stChAssumptions.setStChAssumptionsSid(stChAssumptionsSid);
    }

    /**
    * Returns the ch assumptions sid of this st ch assumptions.
    *
    * @return the ch assumptions sid of this st ch assumptions
    */
    @Override
    public int getChAssumptionsSid() {
        return _stChAssumptions.getChAssumptionsSid();
    }

    /**
    * Sets the ch assumptions sid of this st ch assumptions.
    *
    * @param chAssumptionsSid the ch assumptions sid of this st ch assumptions
    */
    @Override
    public void setChAssumptionsSid(int chAssumptionsSid) {
        _stChAssumptions.setChAssumptionsSid(chAssumptionsSid);
    }

    /**
    * Returns the total discount change of this st ch assumptions.
    *
    * @return the total discount change of this st ch assumptions
    */
    @Override
    public double getTotalDiscountChange() {
        return _stChAssumptions.getTotalDiscountChange();
    }

    /**
    * Sets the total discount change of this st ch assumptions.
    *
    * @param totalDiscountChange the total discount change of this st ch assumptions
    */
    @Override
    public void setTotalDiscountChange(double totalDiscountChange) {
        _stChAssumptions.setTotalDiscountChange(totalDiscountChange);
    }

    /**
    * Returns the session ID of this st ch assumptions.
    *
    * @return the session ID of this st ch assumptions
    */
    @Override
    public int getSessionId() {
        return _stChAssumptions.getSessionId();
    }

    /**
    * Sets the session ID of this st ch assumptions.
    *
    * @param sessionId the session ID of this st ch assumptions
    */
    @Override
    public void setSessionId(int sessionId) {
        _stChAssumptions.setSessionId(sessionId);
    }

    /**
    * Returns the total discount projected of this st ch assumptions.
    *
    * @return the total discount projected of this st ch assumptions
    */
    @Override
    public double getTotalDiscountProjected() {
        return _stChAssumptions.getTotalDiscountProjected();
    }

    /**
    * Sets the total discount projected of this st ch assumptions.
    *
    * @param totalDiscountProjected the total discount projected of this st ch assumptions
    */
    @Override
    public void setTotalDiscountProjected(double totalDiscountProjected) {
        _stChAssumptions.setTotalDiscountProjected(totalDiscountProjected);
    }

    /**
    * Returns the is checked of this st ch assumptions.
    *
    * @return the is checked of this st ch assumptions
    */
    @Override
    public boolean getIsChecked() {
        return _stChAssumptions.getIsChecked();
    }

    /**
    * Returns <code>true</code> if this st ch assumptions is is checked.
    *
    * @return <code>true</code> if this st ch assumptions is is checked; <code>false</code> otherwise
    */
    @Override
    public boolean isIsChecked() {
        return _stChAssumptions.isIsChecked();
    }

    /**
    * Sets whether this st ch assumptions is is checked.
    *
    * @param isChecked the is checked of this st ch assumptions
    */
    @Override
    public void setIsChecked(boolean isChecked) {
        _stChAssumptions.setIsChecked(isChecked);
    }

    /**
    * Returns the cam ID of this st ch assumptions.
    *
    * @return the cam ID of this st ch assumptions
    */
    @Override
    public int getCamId() {
        return _stChAssumptions.getCamId();
    }

    /**
    * Sets the cam ID of this st ch assumptions.
    *
    * @param camId the cam ID of this st ch assumptions
    */
    @Override
    public void setCamId(int camId) {
        _stChAssumptions.setCamId(camId);
    }

    /**
    * Returns the gross trade sales of this st ch assumptions.
    *
    * @return the gross trade sales of this st ch assumptions
    */
    @Override
    public double getGrossTradeSales() {
        return _stChAssumptions.getGrossTradeSales();
    }

    /**
    * Sets the gross trade sales of this st ch assumptions.
    *
    * @param grossTradeSales the gross trade sales of this st ch assumptions
    */
    @Override
    public void setGrossTradeSales(double grossTradeSales) {
        _stChAssumptions.setGrossTradeSales(grossTradeSales);
    }

    /**
    * Returns the total discount prior of this st ch assumptions.
    *
    * @return the total discount prior of this st ch assumptions
    */
    @Override
    public double getTotalDiscountPrior() {
        return _stChAssumptions.getTotalDiscountPrior();
    }

    /**
    * Sets the total discount prior of this st ch assumptions.
    *
    * @param totalDiscountPrior the total discount prior of this st ch assumptions
    */
    @Override
    public void setTotalDiscountPrior(double totalDiscountPrior) {
        _stChAssumptions.setTotalDiscountPrior(totalDiscountPrior);
    }

    @Override
    public boolean isNew() {
        return _stChAssumptions.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stChAssumptions.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stChAssumptions.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stChAssumptions.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stChAssumptions.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stChAssumptions.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stChAssumptions.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stChAssumptions.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stChAssumptions.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stChAssumptions.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stChAssumptions.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StChAssumptionsWrapper((StChAssumptions) _stChAssumptions.clone());
    }

    @Override
    public int compareTo(StChAssumptions stChAssumptions) {
        return _stChAssumptions.compareTo(stChAssumptions);
    }

    @Override
    public int hashCode() {
        return _stChAssumptions.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StChAssumptions> toCacheModel() {
        return _stChAssumptions.toCacheModel();
    }

    @Override
    public StChAssumptions toEscapedModel() {
        return new StChAssumptionsWrapper(_stChAssumptions.toEscapedModel());
    }

    @Override
    public StChAssumptions toUnescapedModel() {
        return new StChAssumptionsWrapper(_stChAssumptions.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stChAssumptions.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stChAssumptions.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stChAssumptions.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StChAssumptionsWrapper)) {
            return false;
        }

        StChAssumptionsWrapper stChAssumptionsWrapper = (StChAssumptionsWrapper) obj;

        if (Validator.equals(_stChAssumptions,
                    stChAssumptionsWrapper._stChAssumptions)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StChAssumptions getWrappedStChAssumptions() {
        return _stChAssumptions;
    }

    @Override
    public StChAssumptions getWrappedModel() {
        return _stChAssumptions;
    }

    @Override
    public void resetOriginalValues() {
        _stChAssumptions.resetOriginalValues();
    }
}
