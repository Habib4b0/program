package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MSupplementalDiscProj}.
 * </p>
 *
 * @author
 * @see MSupplementalDiscProj
 * @generated
 */
public class MSupplementalDiscProjWrapper implements MSupplementalDiscProj,
    ModelWrapper<MSupplementalDiscProj> {
    private MSupplementalDiscProj _mSupplementalDiscProj;

    public MSupplementalDiscProjWrapper(
        MSupplementalDiscProj mSupplementalDiscProj) {
        _mSupplementalDiscProj = mSupplementalDiscProj;
    }

    @Override
    public Class<?> getModelClass() {
        return MSupplementalDiscProj.class;
    }

    @Override
    public String getModelClassName() {
        return MSupplementalDiscProj.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("methodology", getMethodology());
        attributes.put("projectionRate", getProjectionRate());
        attributes.put("parity", getParity());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("discountRate1", getDiscountRate1());
        attributes.put("parityReference", getParityReference());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("discountRate2", getDiscountRate2());
        attributes.put("parityDiscount", getParityDiscount());
        attributes.put("projectionSales", getProjectionSales());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("access", getAccess());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        Double projectionRate = (Double) attributes.get("projectionRate");

        if (projectionRate != null) {
            setProjectionRate(projectionRate);
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

        String parityReference = (String) attributes.get("parityReference");

        if (parityReference != null) {
            setParityReference(parityReference);
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

        Double projectionSales = (Double) attributes.get("projectionSales");

        if (projectionSales != null) {
            setProjectionSales(projectionSales);
        }

        Double contractPrice = (Double) attributes.get("contractPrice");

        if (contractPrice != null) {
            setContractPrice(contractPrice);
        }

        String access = (String) attributes.get("access");

        if (access != null) {
            setAccess(access);
        }
    }

    /**
    * Returns the primary key of this m supplemental disc proj.
    *
    * @return the primary key of this m supplemental disc proj
    */
    @Override
    public int getPrimaryKey() {
        return _mSupplementalDiscProj.getPrimaryKey();
    }

    /**
    * Sets the primary key of this m supplemental disc proj.
    *
    * @param primaryKey the primary key of this m supplemental disc proj
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _mSupplementalDiscProj.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the methodology of this m supplemental disc proj.
    *
    * @return the methodology of this m supplemental disc proj
    */
    @Override
    public java.lang.String getMethodology() {
        return _mSupplementalDiscProj.getMethodology();
    }

    /**
    * Sets the methodology of this m supplemental disc proj.
    *
    * @param methodology the methodology of this m supplemental disc proj
    */
    @Override
    public void setMethodology(java.lang.String methodology) {
        _mSupplementalDiscProj.setMethodology(methodology);
    }

    /**
    * Returns the projection rate of this m supplemental disc proj.
    *
    * @return the projection rate of this m supplemental disc proj
    */
    @Override
    public double getProjectionRate() {
        return _mSupplementalDiscProj.getProjectionRate();
    }

    /**
    * Sets the projection rate of this m supplemental disc proj.
    *
    * @param projectionRate the projection rate of this m supplemental disc proj
    */
    @Override
    public void setProjectionRate(double projectionRate) {
        _mSupplementalDiscProj.setProjectionRate(projectionRate);
    }

    /**
    * Returns the parity of this m supplemental disc proj.
    *
    * @return the parity of this m supplemental disc proj
    */
    @Override
    public boolean getParity() {
        return _mSupplementalDiscProj.getParity();
    }

    /**
    * Returns <code>true</code> if this m supplemental disc proj is parity.
    *
    * @return <code>true</code> if this m supplemental disc proj is parity; <code>false</code> otherwise
    */
    @Override
    public boolean isParity() {
        return _mSupplementalDiscProj.isParity();
    }

    /**
    * Sets whether this m supplemental disc proj is parity.
    *
    * @param parity the parity of this m supplemental disc proj
    */
    @Override
    public void setParity(boolean parity) {
        _mSupplementalDiscProj.setParity(parity);
    }

    /**
    * Returns the period sid of this m supplemental disc proj.
    *
    * @return the period sid of this m supplemental disc proj
    */
    @Override
    public int getPeriodSid() {
        return _mSupplementalDiscProj.getPeriodSid();
    }

    /**
    * Sets the period sid of this m supplemental disc proj.
    *
    * @param periodSid the period sid of this m supplemental disc proj
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _mSupplementalDiscProj.setPeriodSid(periodSid);
    }

    /**
    * Returns the discount rate1 of this m supplemental disc proj.
    *
    * @return the discount rate1 of this m supplemental disc proj
    */
    @Override
    public double getDiscountRate1() {
        return _mSupplementalDiscProj.getDiscountRate1();
    }

    /**
    * Sets the discount rate1 of this m supplemental disc proj.
    *
    * @param discountRate1 the discount rate1 of this m supplemental disc proj
    */
    @Override
    public void setDiscountRate1(double discountRate1) {
        _mSupplementalDiscProj.setDiscountRate1(discountRate1);
    }

    /**
    * Returns the parity reference of this m supplemental disc proj.
    *
    * @return the parity reference of this m supplemental disc proj
    */
    @Override
    public java.lang.String getParityReference() {
        return _mSupplementalDiscProj.getParityReference();
    }

    /**
    * Sets the parity reference of this m supplemental disc proj.
    *
    * @param parityReference the parity reference of this m supplemental disc proj
    */
    @Override
    public void setParityReference(java.lang.String parityReference) {
        _mSupplementalDiscProj.setParityReference(parityReference);
    }

    /**
    * Returns the projection details sid of this m supplemental disc proj.
    *
    * @return the projection details sid of this m supplemental disc proj
    */
    @Override
    public int getProjectionDetailsSid() {
        return _mSupplementalDiscProj.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this m supplemental disc proj.
    *
    * @param projectionDetailsSid the projection details sid of this m supplemental disc proj
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _mSupplementalDiscProj.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the discount rate2 of this m supplemental disc proj.
    *
    * @return the discount rate2 of this m supplemental disc proj
    */
    @Override
    public double getDiscountRate2() {
        return _mSupplementalDiscProj.getDiscountRate2();
    }

    /**
    * Sets the discount rate2 of this m supplemental disc proj.
    *
    * @param discountRate2 the discount rate2 of this m supplemental disc proj
    */
    @Override
    public void setDiscountRate2(double discountRate2) {
        _mSupplementalDiscProj.setDiscountRate2(discountRate2);
    }

    /**
    * Returns the parity discount of this m supplemental disc proj.
    *
    * @return the parity discount of this m supplemental disc proj
    */
    @Override
    public double getParityDiscount() {
        return _mSupplementalDiscProj.getParityDiscount();
    }

    /**
    * Sets the parity discount of this m supplemental disc proj.
    *
    * @param parityDiscount the parity discount of this m supplemental disc proj
    */
    @Override
    public void setParityDiscount(double parityDiscount) {
        _mSupplementalDiscProj.setParityDiscount(parityDiscount);
    }

    /**
    * Returns the projection sales of this m supplemental disc proj.
    *
    * @return the projection sales of this m supplemental disc proj
    */
    @Override
    public double getProjectionSales() {
        return _mSupplementalDiscProj.getProjectionSales();
    }

    /**
    * Sets the projection sales of this m supplemental disc proj.
    *
    * @param projectionSales the projection sales of this m supplemental disc proj
    */
    @Override
    public void setProjectionSales(double projectionSales) {
        _mSupplementalDiscProj.setProjectionSales(projectionSales);
    }

    /**
    * Returns the contract price of this m supplemental disc proj.
    *
    * @return the contract price of this m supplemental disc proj
    */
    @Override
    public double getContractPrice() {
        return _mSupplementalDiscProj.getContractPrice();
    }

    /**
    * Sets the contract price of this m supplemental disc proj.
    *
    * @param contractPrice the contract price of this m supplemental disc proj
    */
    @Override
    public void setContractPrice(double contractPrice) {
        _mSupplementalDiscProj.setContractPrice(contractPrice);
    }

    /**
    * Returns the access of this m supplemental disc proj.
    *
    * @return the access of this m supplemental disc proj
    */
    @Override
    public java.lang.String getAccess() {
        return _mSupplementalDiscProj.getAccess();
    }

    /**
    * Sets the access of this m supplemental disc proj.
    *
    * @param access the access of this m supplemental disc proj
    */
    @Override
    public void setAccess(java.lang.String access) {
        _mSupplementalDiscProj.setAccess(access);
    }

    @Override
    public boolean isNew() {
        return _mSupplementalDiscProj.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _mSupplementalDiscProj.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _mSupplementalDiscProj.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _mSupplementalDiscProj.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _mSupplementalDiscProj.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _mSupplementalDiscProj.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _mSupplementalDiscProj.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _mSupplementalDiscProj.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _mSupplementalDiscProj.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _mSupplementalDiscProj.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _mSupplementalDiscProj.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MSupplementalDiscProjWrapper((MSupplementalDiscProj) _mSupplementalDiscProj.clone());
    }

    @Override
    public int compareTo(MSupplementalDiscProj mSupplementalDiscProj) {
        return _mSupplementalDiscProj.compareTo(mSupplementalDiscProj);
    }

    @Override
    public int hashCode() {
        return _mSupplementalDiscProj.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MSupplementalDiscProj> toCacheModel() {
        return _mSupplementalDiscProj.toCacheModel();
    }

    @Override
    public MSupplementalDiscProj toEscapedModel() {
        return new MSupplementalDiscProjWrapper(_mSupplementalDiscProj.toEscapedModel());
    }

    @Override
    public MSupplementalDiscProj toUnescapedModel() {
        return new MSupplementalDiscProjWrapper(_mSupplementalDiscProj.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _mSupplementalDiscProj.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _mSupplementalDiscProj.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _mSupplementalDiscProj.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MSupplementalDiscProjWrapper)) {
            return false;
        }

        MSupplementalDiscProjWrapper mSupplementalDiscProjWrapper = (MSupplementalDiscProjWrapper) obj;

        if (Validator.equals(_mSupplementalDiscProj,
                    mSupplementalDiscProjWrapper._mSupplementalDiscProj)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MSupplementalDiscProj getWrappedMSupplementalDiscProj() {
        return _mSupplementalDiscProj;
    }

    @Override
    public MSupplementalDiscProj getWrappedModel() {
        return _mSupplementalDiscProj;
    }

    @Override
    public void resetOriginalValues() {
        _mSupplementalDiscProj.resetOriginalValues();
    }
}
