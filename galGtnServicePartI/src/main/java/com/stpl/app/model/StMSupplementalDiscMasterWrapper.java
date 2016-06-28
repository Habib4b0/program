package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StMSupplementalDiscMaster}.
 * </p>
 *
 * @author
 * @see StMSupplementalDiscMaster
 * @generated
 */
public class StMSupplementalDiscMasterWrapper
    implements StMSupplementalDiscMaster,
        ModelWrapper<StMSupplementalDiscMaster> {
    private StMSupplementalDiscMaster _stMSupplementalDiscMaster;

    public StMSupplementalDiscMasterWrapper(
        StMSupplementalDiscMaster stMSupplementalDiscMaster) {
        _stMSupplementalDiscMaster = stMSupplementalDiscMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return StMSupplementalDiscMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StMSupplementalDiscMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualDiscountRate2", getActualDiscountRate2());
        attributes.put("actualDiscountRate1", getActualDiscountRate1());
        attributes.put("marketType", getMarketType());
        attributes.put("actualMethodology", getActualMethodology());
        attributes.put("actualContractPrice", getActualContractPrice());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("actualDiscount", getActualDiscount());
        attributes.put("sessionId", getSessionId());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractEndDate", getContractEndDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualDiscountRate2 = (Double) attributes.get(
                "actualDiscountRate2");

        if (actualDiscountRate2 != null) {
            setActualDiscountRate2(actualDiscountRate2);
        }

        Double actualDiscountRate1 = (Double) attributes.get(
                "actualDiscountRate1");

        if (actualDiscountRate1 != null) {
            setActualDiscountRate1(actualDiscountRate1);
        }

        String marketType = (String) attributes.get("marketType");

        if (marketType != null) {
            setMarketType(marketType);
        }

        String actualMethodology = (String) attributes.get("actualMethodology");

        if (actualMethodology != null) {
            setActualMethodology(actualMethodology);
        }

        Double actualContractPrice = (Double) attributes.get(
                "actualContractPrice");

        if (actualContractPrice != null) {
            setActualContractPrice(actualContractPrice);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double actualDiscount = (Double) attributes.get("actualDiscount");

        if (actualDiscount != null) {
            setActualDiscount(actualDiscount);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer checkRecord = (Integer) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date contractEndDate = (Date) attributes.get("contractEndDate");

        if (contractEndDate != null) {
            setContractEndDate(contractEndDate);
        }
    }

    /**
    * Returns the primary key of this st m supplemental disc master.
    *
    * @return the primary key of this st m supplemental disc master
    */
    @Override
    public com.stpl.app.service.persistence.StMSupplementalDiscMasterPK getPrimaryKey() {
        return _stMSupplementalDiscMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st m supplemental disc master.
    *
    * @param primaryKey the primary key of this st m supplemental disc master
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK primaryKey) {
        _stMSupplementalDiscMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the actual discount rate2 of this st m supplemental disc master.
    *
    * @return the actual discount rate2 of this st m supplemental disc master
    */
    @Override
    public double getActualDiscountRate2() {
        return _stMSupplementalDiscMaster.getActualDiscountRate2();
    }

    /**
    * Sets the actual discount rate2 of this st m supplemental disc master.
    *
    * @param actualDiscountRate2 the actual discount rate2 of this st m supplemental disc master
    */
    @Override
    public void setActualDiscountRate2(double actualDiscountRate2) {
        _stMSupplementalDiscMaster.setActualDiscountRate2(actualDiscountRate2);
    }

    /**
    * Returns the actual discount rate1 of this st m supplemental disc master.
    *
    * @return the actual discount rate1 of this st m supplemental disc master
    */
    @Override
    public double getActualDiscountRate1() {
        return _stMSupplementalDiscMaster.getActualDiscountRate1();
    }

    /**
    * Sets the actual discount rate1 of this st m supplemental disc master.
    *
    * @param actualDiscountRate1 the actual discount rate1 of this st m supplemental disc master
    */
    @Override
    public void setActualDiscountRate1(double actualDiscountRate1) {
        _stMSupplementalDiscMaster.setActualDiscountRate1(actualDiscountRate1);
    }

    /**
    * Returns the market type of this st m supplemental disc master.
    *
    * @return the market type of this st m supplemental disc master
    */
    @Override
    public java.lang.String getMarketType() {
        return _stMSupplementalDiscMaster.getMarketType();
    }

    /**
    * Sets the market type of this st m supplemental disc master.
    *
    * @param marketType the market type of this st m supplemental disc master
    */
    @Override
    public void setMarketType(java.lang.String marketType) {
        _stMSupplementalDiscMaster.setMarketType(marketType);
    }

    /**
    * Returns the actual methodology of this st m supplemental disc master.
    *
    * @return the actual methodology of this st m supplemental disc master
    */
    @Override
    public java.lang.String getActualMethodology() {
        return _stMSupplementalDiscMaster.getActualMethodology();
    }

    /**
    * Sets the actual methodology of this st m supplemental disc master.
    *
    * @param actualMethodology the actual methodology of this st m supplemental disc master
    */
    @Override
    public void setActualMethodology(java.lang.String actualMethodology) {
        _stMSupplementalDiscMaster.setActualMethodology(actualMethodology);
    }

    /**
    * Returns the actual contract price of this st m supplemental disc master.
    *
    * @return the actual contract price of this st m supplemental disc master
    */
    @Override
    public double getActualContractPrice() {
        return _stMSupplementalDiscMaster.getActualContractPrice();
    }

    /**
    * Sets the actual contract price of this st m supplemental disc master.
    *
    * @param actualContractPrice the actual contract price of this st m supplemental disc master
    */
    @Override
    public void setActualContractPrice(double actualContractPrice) {
        _stMSupplementalDiscMaster.setActualContractPrice(actualContractPrice);
    }

    /**
    * Returns the user ID of this st m supplemental disc master.
    *
    * @return the user ID of this st m supplemental disc master
    */
    @Override
    public int getUserId() {
        return _stMSupplementalDiscMaster.getUserId();
    }

    /**
    * Sets the user ID of this st m supplemental disc master.
    *
    * @param userId the user ID of this st m supplemental disc master
    */
    @Override
    public void setUserId(int userId) {
        _stMSupplementalDiscMaster.setUserId(userId);
    }

    /**
    * Returns the last modified date of this st m supplemental disc master.
    *
    * @return the last modified date of this st m supplemental disc master
    */
    @Override
    public java.util.Date getLastModifiedDate() {
        return _stMSupplementalDiscMaster.getLastModifiedDate();
    }

    /**
    * Sets the last modified date of this st m supplemental disc master.
    *
    * @param lastModifiedDate the last modified date of this st m supplemental disc master
    */
    @Override
    public void setLastModifiedDate(java.util.Date lastModifiedDate) {
        _stMSupplementalDiscMaster.setLastModifiedDate(lastModifiedDate);
    }

    /**
    * Returns the projection details sid of this st m supplemental disc master.
    *
    * @return the projection details sid of this st m supplemental disc master
    */
    @Override
    public int getProjectionDetailsSid() {
        return _stMSupplementalDiscMaster.getProjectionDetailsSid();
    }

    /**
    * Sets the projection details sid of this st m supplemental disc master.
    *
    * @param projectionDetailsSid the projection details sid of this st m supplemental disc master
    */
    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _stMSupplementalDiscMaster.setProjectionDetailsSid(projectionDetailsSid);
    }

    /**
    * Returns the actual discount of this st m supplemental disc master.
    *
    * @return the actual discount of this st m supplemental disc master
    */
    @Override
    public double getActualDiscount() {
        return _stMSupplementalDiscMaster.getActualDiscount();
    }

    /**
    * Sets the actual discount of this st m supplemental disc master.
    *
    * @param actualDiscount the actual discount of this st m supplemental disc master
    */
    @Override
    public void setActualDiscount(double actualDiscount) {
        _stMSupplementalDiscMaster.setActualDiscount(actualDiscount);
    }

    /**
    * Returns the session ID of this st m supplemental disc master.
    *
    * @return the session ID of this st m supplemental disc master
    */
    @Override
    public int getSessionId() {
        return _stMSupplementalDiscMaster.getSessionId();
    }

    /**
    * Sets the session ID of this st m supplemental disc master.
    *
    * @param sessionId the session ID of this st m supplemental disc master
    */
    @Override
    public void setSessionId(int sessionId) {
        _stMSupplementalDiscMaster.setSessionId(sessionId);
    }

    /**
    * Returns the check record of this st m supplemental disc master.
    *
    * @return the check record of this st m supplemental disc master
    */
    @Override
    public int getCheckRecord() {
        return _stMSupplementalDiscMaster.getCheckRecord();
    }

    /**
    * Sets the check record of this st m supplemental disc master.
    *
    * @param checkRecord the check record of this st m supplemental disc master
    */
    @Override
    public void setCheckRecord(int checkRecord) {
        _stMSupplementalDiscMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the contract end date of this st m supplemental disc master.
    *
    * @return the contract end date of this st m supplemental disc master
    */
    @Override
    public java.util.Date getContractEndDate() {
        return _stMSupplementalDiscMaster.getContractEndDate();
    }

    /**
    * Sets the contract end date of this st m supplemental disc master.
    *
    * @param contractEndDate the contract end date of this st m supplemental disc master
    */
    @Override
    public void setContractEndDate(java.util.Date contractEndDate) {
        _stMSupplementalDiscMaster.setContractEndDate(contractEndDate);
    }

    @Override
    public boolean isNew() {
        return _stMSupplementalDiscMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stMSupplementalDiscMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stMSupplementalDiscMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stMSupplementalDiscMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stMSupplementalDiscMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stMSupplementalDiscMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stMSupplementalDiscMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stMSupplementalDiscMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stMSupplementalDiscMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stMSupplementalDiscMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stMSupplementalDiscMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StMSupplementalDiscMasterWrapper((StMSupplementalDiscMaster) _stMSupplementalDiscMaster.clone());
    }

    @Override
    public int compareTo(StMSupplementalDiscMaster stMSupplementalDiscMaster) {
        return _stMSupplementalDiscMaster.compareTo(stMSupplementalDiscMaster);
    }

    @Override
    public int hashCode() {
        return _stMSupplementalDiscMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StMSupplementalDiscMaster> toCacheModel() {
        return _stMSupplementalDiscMaster.toCacheModel();
    }

    @Override
    public StMSupplementalDiscMaster toEscapedModel() {
        return new StMSupplementalDiscMasterWrapper(_stMSupplementalDiscMaster.toEscapedModel());
    }

    @Override
    public StMSupplementalDiscMaster toUnescapedModel() {
        return new StMSupplementalDiscMasterWrapper(_stMSupplementalDiscMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stMSupplementalDiscMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stMSupplementalDiscMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stMSupplementalDiscMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StMSupplementalDiscMasterWrapper)) {
            return false;
        }

        StMSupplementalDiscMasterWrapper stMSupplementalDiscMasterWrapper = (StMSupplementalDiscMasterWrapper) obj;

        if (Validator.equals(_stMSupplementalDiscMaster,
                    stMSupplementalDiscMasterWrapper._stMSupplementalDiscMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StMSupplementalDiscMaster getWrappedStMSupplementalDiscMaster() {
        return _stMSupplementalDiscMaster;
    }

    @Override
    public StMSupplementalDiscMaster getWrappedModel() {
        return _stMSupplementalDiscMaster;
    }

    @Override
    public void resetOriginalValues() {
        _stMSupplementalDiscMaster.resetOriginalValues();
    }
}
