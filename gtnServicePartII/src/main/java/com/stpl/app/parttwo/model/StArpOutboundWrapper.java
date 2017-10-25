package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StArpOutbound}.
 * </p>
 *
 * @author
 * @see StArpOutbound
 * @generated
 */
public class StArpOutboundWrapper implements StArpOutbound,
    ModelWrapper<StArpOutbound> {
    private StArpOutbound _stArpOutbound;

    public StArpOutboundWrapper(StArpOutbound stArpOutbound) {
        _stArpOutbound = stArpOutbound;
    }

    @Override
    public Class<?> getModelClass() {
        return StArpOutbound.class;
    }

    @Override
    public String getModelClassName() {
        return StArpOutbound.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("salesUnitsRate", getSalesUnitsRate());
        attributes.put("accountType", getAccountType());
        attributes.put("originalBatchId", getOriginalBatchId());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("arpApprovalDate", getArpApprovalDate());
        attributes.put("userId", getUserId());
        attributes.put("arpMasterSid", getArpMasterSid());
        attributes.put("arpCreationDate", getArpCreationDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("arpId", getArpId());
        attributes.put("account", getAccount());
        attributes.put("outboundStatus", getOutboundStatus());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double salesUnitsRate = (Double) attributes.get("salesUnitsRate");

        if (salesUnitsRate != null) {
            setSalesUnitsRate(salesUnitsRate);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
        }

        String originalBatchId = (String) attributes.get("originalBatchId");

        if (originalBatchId != null) {
            setOriginalBatchId(originalBatchId);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Date arpApprovalDate = (Date) attributes.get("arpApprovalDate");

        if (arpApprovalDate != null) {
            setArpApprovalDate(arpApprovalDate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer arpMasterSid = (Integer) attributes.get("arpMasterSid");

        if (arpMasterSid != null) {
            setArpMasterSid(arpMasterSid);
        }

        Date arpCreationDate = (Date) attributes.get("arpCreationDate");

        if (arpCreationDate != null) {
            setArpCreationDate(arpCreationDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Integer arpId = (Integer) attributes.get("arpId");

        if (arpId != null) {
            setArpId(arpId);
        }

        String account = (String) attributes.get("account");

        if (account != null) {
            setAccount(account);
        }

        Boolean outboundStatus = (Boolean) attributes.get("outboundStatus");

        if (outboundStatus != null) {
            setOutboundStatus(outboundStatus);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    /**
    * Returns the primary key of this st arp outbound.
    *
    * @return the primary key of this st arp outbound
    */
    @Override
    public com.stpl.app.parttwo.service.persistence.StArpOutboundPK getPrimaryKey() {
        return _stArpOutbound.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st arp outbound.
    *
    * @param primaryKey the primary key of this st arp outbound
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.parttwo.service.persistence.StArpOutboundPK primaryKey) {
        _stArpOutbound.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the sales units rate of this st arp outbound.
    *
    * @return the sales units rate of this st arp outbound
    */
    @Override
    public double getSalesUnitsRate() {
        return _stArpOutbound.getSalesUnitsRate();
    }

    /**
    * Sets the sales units rate of this st arp outbound.
    *
    * @param salesUnitsRate the sales units rate of this st arp outbound
    */
    @Override
    public void setSalesUnitsRate(double salesUnitsRate) {
        _stArpOutbound.setSalesUnitsRate(salesUnitsRate);
    }

    /**
    * Returns the account type of this st arp outbound.
    *
    * @return the account type of this st arp outbound
    */
    @Override
    public java.lang.String getAccountType() {
        return _stArpOutbound.getAccountType();
    }

    /**
    * Sets the account type of this st arp outbound.
    *
    * @param accountType the account type of this st arp outbound
    */
    @Override
    public void setAccountType(java.lang.String accountType) {
        _stArpOutbound.setAccountType(accountType);
    }

    /**
    * Returns the original batch ID of this st arp outbound.
    *
    * @return the original batch ID of this st arp outbound
    */
    @Override
    public java.lang.String getOriginalBatchId() {
        return _stArpOutbound.getOriginalBatchId();
    }

    /**
    * Sets the original batch ID of this st arp outbound.
    *
    * @param originalBatchId the original batch ID of this st arp outbound
    */
    @Override
    public void setOriginalBatchId(java.lang.String originalBatchId) {
        _stArpOutbound.setOriginalBatchId(originalBatchId);
    }

    /**
    * Returns the company master sid of this st arp outbound.
    *
    * @return the company master sid of this st arp outbound
    */
    @Override
    public int getCompanyMasterSid() {
        return _stArpOutbound.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this st arp outbound.
    *
    * @param companyMasterSid the company master sid of this st arp outbound
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _stArpOutbound.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the brand master sid of this st arp outbound.
    *
    * @return the brand master sid of this st arp outbound
    */
    @Override
    public int getBrandMasterSid() {
        return _stArpOutbound.getBrandMasterSid();
    }

    /**
    * Sets the brand master sid of this st arp outbound.
    *
    * @param brandMasterSid the brand master sid of this st arp outbound
    */
    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _stArpOutbound.setBrandMasterSid(brandMasterSid);
    }

    /**
    * Returns the arp approval date of this st arp outbound.
    *
    * @return the arp approval date of this st arp outbound
    */
    @Override
    public java.util.Date getArpApprovalDate() {
        return _stArpOutbound.getArpApprovalDate();
    }

    /**
    * Sets the arp approval date of this st arp outbound.
    *
    * @param arpApprovalDate the arp approval date of this st arp outbound
    */
    @Override
    public void setArpApprovalDate(java.util.Date arpApprovalDate) {
        _stArpOutbound.setArpApprovalDate(arpApprovalDate);
    }

    /**
    * Returns the user ID of this st arp outbound.
    *
    * @return the user ID of this st arp outbound
    */
    @Override
    public int getUserId() {
        return _stArpOutbound.getUserId();
    }

    /**
    * Sets the user ID of this st arp outbound.
    *
    * @param userId the user ID of this st arp outbound
    */
    @Override
    public void setUserId(int userId) {
        _stArpOutbound.setUserId(userId);
    }

    /**
    * Returns the arp master sid of this st arp outbound.
    *
    * @return the arp master sid of this st arp outbound
    */
    @Override
    public int getArpMasterSid() {
        return _stArpOutbound.getArpMasterSid();
    }

    /**
    * Sets the arp master sid of this st arp outbound.
    *
    * @param arpMasterSid the arp master sid of this st arp outbound
    */
    @Override
    public void setArpMasterSid(int arpMasterSid) {
        _stArpOutbound.setArpMasterSid(arpMasterSid);
    }

    /**
    * Returns the arp creation date of this st arp outbound.
    *
    * @return the arp creation date of this st arp outbound
    */
    @Override
    public java.util.Date getArpCreationDate() {
        return _stArpOutbound.getArpCreationDate();
    }

    /**
    * Sets the arp creation date of this st arp outbound.
    *
    * @param arpCreationDate the arp creation date of this st arp outbound
    */
    @Override
    public void setArpCreationDate(java.util.Date arpCreationDate) {
        _stArpOutbound.setArpCreationDate(arpCreationDate);
    }

    /**
    * Returns the check record of this st arp outbound.
    *
    * @return the check record of this st arp outbound
    */
    @Override
    public boolean getCheckRecord() {
        return _stArpOutbound.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st arp outbound is check record.
    *
    * @return <code>true</code> if this st arp outbound is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stArpOutbound.isCheckRecord();
    }

    /**
    * Sets whether this st arp outbound is check record.
    *
    * @param checkRecord the check record of this st arp outbound
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stArpOutbound.setCheckRecord(checkRecord);
    }

    /**
    * Returns the arp ID of this st arp outbound.
    *
    * @return the arp ID of this st arp outbound
    */
    @Override
    public int getArpId() {
        return _stArpOutbound.getArpId();
    }

    /**
    * Sets the arp ID of this st arp outbound.
    *
    * @param arpId the arp ID of this st arp outbound
    */
    @Override
    public void setArpId(int arpId) {
        _stArpOutbound.setArpId(arpId);
    }

    /**
    * Returns the account of this st arp outbound.
    *
    * @return the account of this st arp outbound
    */
    @Override
    public java.lang.String getAccount() {
        return _stArpOutbound.getAccount();
    }

    /**
    * Sets the account of this st arp outbound.
    *
    * @param account the account of this st arp outbound
    */
    @Override
    public void setAccount(java.lang.String account) {
        _stArpOutbound.setAccount(account);
    }

    /**
    * Returns the outbound status of this st arp outbound.
    *
    * @return the outbound status of this st arp outbound
    */
    @Override
    public boolean getOutboundStatus() {
        return _stArpOutbound.getOutboundStatus();
    }

    /**
    * Returns <code>true</code> if this st arp outbound is outbound status.
    *
    * @return <code>true</code> if this st arp outbound is outbound status; <code>false</code> otherwise
    */
    @Override
    public boolean isOutboundStatus() {
        return _stArpOutbound.isOutboundStatus();
    }

    /**
    * Sets whether this st arp outbound is outbound status.
    *
    * @param outboundStatus the outbound status of this st arp outbound
    */
    @Override
    public void setOutboundStatus(boolean outboundStatus) {
        _stArpOutbound.setOutboundStatus(outboundStatus);
    }

    /**
    * Returns the period sid of this st arp outbound.
    *
    * @return the period sid of this st arp outbound
    */
    @Override
    public int getPeriodSid() {
        return _stArpOutbound.getPeriodSid();
    }

    /**
    * Sets the period sid of this st arp outbound.
    *
    * @param periodSid the period sid of this st arp outbound
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _stArpOutbound.setPeriodSid(periodSid);
    }

    /**
    * Returns the item master sid of this st arp outbound.
    *
    * @return the item master sid of this st arp outbound
    */
    @Override
    public int getItemMasterSid() {
        return _stArpOutbound.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this st arp outbound.
    *
    * @param itemMasterSid the item master sid of this st arp outbound
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _stArpOutbound.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the rs model sid of this st arp outbound.
    *
    * @return the rs model sid of this st arp outbound
    */
    @Override
    public int getRsModelSid() {
        return _stArpOutbound.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this st arp outbound.
    *
    * @param rsModelSid the rs model sid of this st arp outbound
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _stArpOutbound.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the session ID of this st arp outbound.
    *
    * @return the session ID of this st arp outbound
    */
    @Override
    public java.lang.String getSessionId() {
        return _stArpOutbound.getSessionId();
    }

    /**
    * Sets the session ID of this st arp outbound.
    *
    * @param sessionId the session ID of this st arp outbound
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _stArpOutbound.setSessionId(sessionId);
    }

    @Override
    public boolean isNew() {
        return _stArpOutbound.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stArpOutbound.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stArpOutbound.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stArpOutbound.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stArpOutbound.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stArpOutbound.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stArpOutbound.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stArpOutbound.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stArpOutbound.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stArpOutbound.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stArpOutbound.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StArpOutboundWrapper((StArpOutbound) _stArpOutbound.clone());
    }

    @Override
    public int compareTo(StArpOutbound stArpOutbound) {
        return _stArpOutbound.compareTo(stArpOutbound);
    }

    @Override
    public int hashCode() {
        return _stArpOutbound.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StArpOutbound> toCacheModel() {
        return _stArpOutbound.toCacheModel();
    }

    @Override
    public StArpOutbound toEscapedModel() {
        return new StArpOutboundWrapper(_stArpOutbound.toEscapedModel());
    }

    @Override
    public StArpOutbound toUnescapedModel() {
        return new StArpOutboundWrapper(_stArpOutbound.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stArpOutbound.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stArpOutbound.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stArpOutbound.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StArpOutboundWrapper)) {
            return false;
        }

        StArpOutboundWrapper stArpOutboundWrapper = (StArpOutboundWrapper) obj;

        if (Validator.equals(_stArpOutbound, stArpOutboundWrapper._stArpOutbound)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StArpOutbound getWrappedStArpOutbound() {
        return _stArpOutbound;
    }

    @Override
    public StArpOutbound getWrappedModel() {
        return _stArpOutbound;
    }

    @Override
    public void resetOriginalValues() {
        _stArpOutbound.resetOriginalValues();
    }
}
