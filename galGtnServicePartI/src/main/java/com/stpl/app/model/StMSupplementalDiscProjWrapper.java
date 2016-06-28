package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StMSupplementalDiscProj}.
 * </p>
 *
 * @author
 * @see StMSupplementalDiscProj
 * @generated
 */
public class StMSupplementalDiscProjWrapper implements StMSupplementalDiscProj,
    ModelWrapper<StMSupplementalDiscProj> {
    private StMSupplementalDiscProj _stMSupplementalDiscProj;

    public StMSupplementalDiscProjWrapper(
        StMSupplementalDiscProj stMSupplementalDiscProj) {
        _stMSupplementalDiscProj = stMSupplementalDiscProj;
    }

    @Override
    public Class<?> getModelClass() {
        return StMSupplementalDiscProj.class;
    }

    @Override
    public String getModelClassName() {
        return StMSupplementalDiscProj.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("projectionRate", getProjectionRate());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("parityReference", getParityReference());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("methodology", getMethodology());
        attributes.put("parity", getParity());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("discountRate1", getDiscountRate1());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("discountRate2", getDiscountRate2());
        attributes.put("parityDiscount", getParityDiscount());
        attributes.put("sessionId", getSessionId());
        attributes.put("access", getAccess());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        String parityReference = (String) attributes.get("parityReference");

        if (parityReference != null) {
            setParityReference(parityReference);
        }

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        Double contractPrice = (Double) attributes.get("contractPrice");

        if (contractPrice != null) {
            setContractPrice(contractPrice);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        Boolean parity = (Boolean) attributes.get("parity");

        if (parity != null) {
            setParity(parity);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double discountRate1 = (Double) attributes.get("discountRate1");

        if (discountRate1 != null) {
            setDiscountRate1(discountRate1);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double discountRate2 = (Double) attributes.get("discountRate2");

        if (discountRate2 != null) {
            setDiscountRate2(discountRate2);
        }

        Double parityDiscount = (Double) attributes.get("parityDiscount");

        if (parityDiscount != null) {
            setParityDiscount(parityDiscount);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String access = (String) attributes.get("access");

        if (access != null) {
            setAccess(access);
        }
    }

    /**
    * Returns the primary key of this st m supplemental disc proj.
    *
    * @return the primary key of this st m supplemental disc proj
    */
    @Override
    public com.stpl.app.service.persistence.StMSupplementalDiscProjPK getPrimaryKey() {
        return _stMSupplementalDiscProj.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st m supplemental disc proj.
    *
    * @param primaryKey the primary key of this st m supplemental disc proj
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StMSupplementalDiscProjPK primaryKey) {
        _stMSupplementalDiscProj.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the projection rate of this st m supplemental disc proj.
    *
    * @return the projection rate of this st m supplemental disc proj
    */
    @Override
    public double getProjectionRate() {
        return _stMSupplementalDiscProj.getProjectionRate();
    }

    /**
    * Sets the projection rate of this st m supplemental disc proj.
    *
    * @param projectionRate the projection rate of this st m supplemental disc proj
    */
    @Override
    public void setProjectionRate(double projectionRate) {
        _stMSupplementalDiscProj.setProjectionRate(projectionRate);
    }

    /**
    * Returns the user ID of this st m supplemental disc proj.
    *
    * @return the user ID of this st m supplemental disc proj
    */
    @Override
    public int getUserId() {
        return _stMSupplementalDiscProj.getUserId();
    }

    /**
    * Sets the user ID of this st m supplemental disc proj.
    *
    * @param userId the user ID of this st m supplemental disc proj
    */
    @Override
    public void setUserId(int userId) {
        _stMSupplementalDiscProj.setUserId(userId);
    }

    /**
    * Returns the last modified date of this st m supplemental disc proj.
    *
    * @return the last modified date of this st m supplemental disc proj
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stMSupplementalDiscProj.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st m supplemental disc proj.
    *
    * @param lastModifiedDate the last modified date of this st m supplemental disc proj
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stMSupplementalDiscProj.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the parity reference of this st m supplemental disc proj.
    *
    * @return the parity reference of this st m supplemental disc proj
    */
    @Override
    public java.lang.String getParityReference() {
        return _stMSupplementalDiscProj.getParityReference();
    }

    /**
    * Sets the parity reference of this st m supplemental disc proj.
    *
    * @param parityReference the parity reference of this st m supplemental disc proj
    */
    @Override
    public void setParityReference(java.lang.String parityReference) {
        _stMSupplementalDiscProj.setParityReference(parityReference);
    }

    /**
    * Returns the projection sales of this st m supplemental disc proj.
    *
    * @return the projection sales of this st m supplemental disc proj
    */
    @Override
    public double getProjectionSales() {
        return _stMSupplementalDiscProj.getProjectionSales();
    }

    /**
    * Sets the projection sales of this st m supplemental disc proj.
    *
    * @param projectionSales the projection sales of this st m supplemental disc proj
    */
    @Override
    public void setProjectionSales(double projectionSales) {
        _stMSupplementalDiscProj.setProjectionSales(projectionSales);
    }

    /**
    * Returns the contract price of this st m supplemental disc proj.
    *
    * @return the contract price of this st m supplemental disc proj
    */
    @Override
    public double getContractPrice() {
        return _stMSupplementalDiscProj.getContractPrice();
    }

    /**
    * Sets the contract price of this st m supplemental disc proj.
    *
    * @param contractPrice the contract price of this st m supplemental disc proj
    */
    @Override
    public void setContractPrice(double contractPrice) {
        _stMSupplementalDiscProj.setContractPrice(contractPrice);
    }

    /**
    * Returns the methodology of this st m supplemental disc proj.
    *
    * @return the methodology of this st m supplemental disc proj
    */
    @Override
    public java.lang.String getMethodology() {
        return _stMSupplementalDiscProj.getMethodology();
    }

    /**
    * Sets the methodology of this st m supplemental disc proj.
    *
    * @param methodology the methodology of this st m supplemental disc proj
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _stMSupplementalDiscProj.setMethodology(methodology);
    }

    /**
    * Returns the parity of this st m supplemental disc proj.
    *
    * @return the parity of this st m supplemental disc proj
    */
    @Override
    public boolean getParity() {
        return _stMSupplementalDiscProj.getParity();
    }

    /**
    * Returns <code>true</code> if this st m supplemental disc proj is parity.
    *
    * @return <code>true</code> if this st m supplemental disc proj is parity; <code>false</code> otherwise
    */
    @Override
    public boolean isParity() {
        return _stMSupplementalDiscProj.isParity();
    }

    /**
    * Sets whether this st m supplemental disc proj is parity.
    *
    * @param parity the parity of this st m supplemental disc proj
    */
    @Override
    public void setParity(boolean parity) {
        _stMSupplementalDiscProj.setParity(parity);
    }

    /**
    * Returns the period sid of this st m supplemental disc proj.
    *
    * @return the period sid of this st m supplemental disc proj
    */
    @Override
    public int getPeriodSid() {
        return _stMSupplementalDiscProj.getPeriodSid();
    }

    /**
    * Sets the period sid of this st m supplemental disc proj.
    *
    * @param periodSid the period sid of this st m supplemental disc proj
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _stMSupplementalDiscProj.setPeriodSid(periodSid);
    }

    /**
    * Returns the discount rate1 of this st m supplemental disc proj.
    *
    * @return the discount rate1 of this st m supplemental disc proj
    */
    @Override
    public double getDiscountRate1() {
        return _stMSupplementalDiscProj.getDiscountRate1();
    }

    /**
    * Sets the discount rate1 of this st m supplemental disc proj.
    *
    * @param discountRate1 the discount rate1 of this st m supplemental disc proj
    */
    @Override
    public void setDiscountRate1(double discountRate1) {
        _stMSupplementalDiscProj.setDiscountRate1(discountRate1);
    }

    /**
    * Returns the projection details sid of this st m supplemental disc proj.
    *
    * @return the projection details sid of this st m supplemental disc proj
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stMSupplementalDiscProj.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st m supplemental disc proj.
    *
    * @param projectionDetailsSid the projection details sid of this st m supplemental disc proj
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stMSupplementalDiscProj.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the discount rate2 of this st m supplemental disc proj.
    *
    * @return the discount rate2 of this st m supplemental disc proj
    */
    @Override
    public double getDiscountRate2() {
        return _stMSupplementalDiscProj.getDiscountRate2();
    }

    /**
    * Sets the discount rate2 of this st m supplemental disc proj.
    *
    * @param discountRate2 the discount rate2 of this st m supplemental disc proj
    */
    @Override
    public void setDiscountRate2(double discountRate2) {
        _stMSupplementalDiscProj.setDiscountRate2(discountRate2);
    }

    /**
    * Returns the parity discount of this st m supplemental disc proj.
    *
    * @return the parity discount of this st m supplemental disc proj
    */
    @Override
    public double getParityDiscount() {
        return _stMSupplementalDiscProj.getParityDiscount();
    }

    /**
    * Sets the parity discount of this st m supplemental disc proj.
    *
    * @param parityDiscount the parity discount of this st m supplemental disc proj
    */
    @Override
    public void setParityDiscount(double parityDiscount) {
        _stMSupplementalDiscProj.setParityDiscount(parityDiscount);
    }

    /**
    * Returns the session ID of this st m supplemental disc proj.
    *
    * @return the session ID of this st m supplemental disc proj
    */
    @Override
    public int getSessionId() {
        return _stMSupplementalDiscProj.getSessionId();
    }

    /**
    * Sets the session ID of this st m supplemental disc proj.
    *
    * @param sessionId the session ID of this st m supplemental disc proj
    */
    @Override
    public void setSessionId(int sessionId) {
        _stMSupplementalDiscProj.setSessionId(sessionId);
    }

    /**
    * Returns the access of this st m supplemental disc proj.
    *
    * @return the access of this st m supplemental disc proj
    */
    @Override
    public java.lang.String getAccess() {
        return _stMSupplementalDiscProj.getAccess();
    }

    /**
    * Sets the access of this st m supplemental disc proj.
    *
    * @param access the access of this st m supplemental disc proj
    */
    @Override
    public void setAccess(java.lang.String access) {
        _stMSupplementalDiscProj.setAccess(access);
    }

    @Override
    public boolean isNew() {
        return _stMSupplementalDiscProj.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stMSupplementalDiscProj.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stMSupplementalDiscProj.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stMSupplementalDiscProj.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stMSupplementalDiscProj.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stMSupplementalDiscProj.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stMSupplementalDiscProj.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stMSupplementalDiscProj.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stMSupplementalDiscProj.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stMSupplementalDiscProj.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stMSupplementalDiscProj.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StMSupplementalDiscProjWrapper((StMSupplementalDiscProj) _stMSupplementalDiscProj.clone());
    }

    @Override
    public int compareTo(StMSupplementalDiscProj stMSupplementalDiscProj) {
        return _stMSupplementalDiscProj.compareTo(stMSupplementalDiscProj);
    }

    @Override
    public int hashCode() {
        return _stMSupplementalDiscProj.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StMSupplementalDiscProj> toCacheModel() {
        return _stMSupplementalDiscProj.toCacheModel();
    }

    @Override
    public StMSupplementalDiscProj toEscapedModel() {
        return new StMSupplementalDiscProjWrapper(_stMSupplementalDiscProj.toEscapedModel());
    }

    @Override
    public StMSupplementalDiscProj toUnescapedModel() {
        return new StMSupplementalDiscProjWrapper(_stMSupplementalDiscProj.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stMSupplementalDiscProj.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stMSupplementalDiscProj.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stMSupplementalDiscProj.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StMSupplementalDiscProjWrapper)) {
            return false;
        }

        StMSupplementalDiscProjWrapper stMSupplementalDiscProjWrapper = (StMSupplementalDiscProjWrapper) obj;

        if (Validator.equals(_stMSupplementalDiscProj,
                    stMSupplementalDiscProjWrapper._stMSupplementalDiscProj)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StMSupplementalDiscProj getWrappedStMSupplementalDiscProj() {
        return _stMSupplementalDiscProj;
    }

    @Override
    public StMSupplementalDiscProj getWrappedModel() {
        return _stMSupplementalDiscProj;
    }

    @Override
    public void resetOriginalValues() {
        _stMSupplementalDiscProj.resetOriginalValues();
    }
}
