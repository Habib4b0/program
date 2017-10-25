package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ArpOutbound}.
 * </p>
 *
 * @author
 * @see ArpOutbound
 * @generated
 */
public class ArpOutboundWrapper implements ArpOutbound,
    ModelWrapper<ArpOutbound> {
    private ArpOutbound _arpOutbound;

    public ArpOutboundWrapper(ArpOutbound arpOutbound) {
        _arpOutbound = arpOutbound;
    }

    @Override
    public Class<?> getModelClass() {
        return ArpOutbound.class;
    }

    @Override
    public String getModelClassName() {
        return ArpOutbound.class.getName();
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
        attributes.put("arpMasterSid", getArpMasterSid());
        attributes.put("arpCreationDate", getArpCreationDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("arpId", getArpId());
        attributes.put("account", getAccount());
        attributes.put("outboundStatus", getOutboundStatus());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rsModelSid", getRsModelSid());

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
    }

    /**
    * Returns the primary key of this arp outbound.
    *
    * @return the primary key of this arp outbound
    */
    @Override
    public com.stpl.app.parttwo.service.persistence.ArpOutboundPK getPrimaryKey() {
        return _arpOutbound.getPrimaryKey();
    }

    /**
    * Sets the primary key of this arp outbound.
    *
    * @param primaryKey the primary key of this arp outbound
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.parttwo.service.persistence.ArpOutboundPK primaryKey) {
        _arpOutbound.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the sales units rate of this arp outbound.
    *
    * @return the sales units rate of this arp outbound
    */
    @Override
    public double getSalesUnitsRate() {
        return _arpOutbound.getSalesUnitsRate();
    }

    /**
    * Sets the sales units rate of this arp outbound.
    *
    * @param salesUnitsRate the sales units rate of this arp outbound
    */
    @Override
    public void setSalesUnitsRate(double salesUnitsRate) {
        _arpOutbound.setSalesUnitsRate(salesUnitsRate);
    }

    /**
    * Returns the account type of this arp outbound.
    *
    * @return the account type of this arp outbound
    */
    @Override
    public java.lang.String getAccountType() {
        return _arpOutbound.getAccountType();
    }

    /**
    * Sets the account type of this arp outbound.
    *
    * @param accountType the account type of this arp outbound
    */
    @Override
    public void setAccountType(java.lang.String accountType) {
        _arpOutbound.setAccountType(accountType);
    }

    /**
    * Returns the original batch ID of this arp outbound.
    *
    * @return the original batch ID of this arp outbound
    */
    @Override
    public java.lang.String getOriginalBatchId() {
        return _arpOutbound.getOriginalBatchId();
    }

    /**
    * Sets the original batch ID of this arp outbound.
    *
    * @param originalBatchId the original batch ID of this arp outbound
    */
    @Override
    public void setOriginalBatchId(java.lang.String originalBatchId) {
        _arpOutbound.setOriginalBatchId(originalBatchId);
    }

    /**
    * Returns the company master sid of this arp outbound.
    *
    * @return the company master sid of this arp outbound
    */
    @Override
    public int getCompanyMasterSid() {
        return _arpOutbound.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this arp outbound.
    *
    * @param companyMasterSid the company master sid of this arp outbound
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _arpOutbound.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the brand master sid of this arp outbound.
    *
    * @return the brand master sid of this arp outbound
    */
    @Override
    public int getBrandMasterSid() {
        return _arpOutbound.getBrandMasterSid();
    }

    /**
    * Sets the brand master sid of this arp outbound.
    *
    * @param brandMasterSid the brand master sid of this arp outbound
    */
    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _arpOutbound.setBrandMasterSid(brandMasterSid);
    }

    /**
    * Returns the arp approval date of this arp outbound.
    *
    * @return the arp approval date of this arp outbound
    */
    @Override
    public java.util.Date getArpApprovalDate() {
        return _arpOutbound.getArpApprovalDate();
    }

    /**
    * Sets the arp approval date of this arp outbound.
    *
    * @param arpApprovalDate the arp approval date of this arp outbound
    */
    @Override
    public void setArpApprovalDate(java.util.Date arpApprovalDate) {
        _arpOutbound.setArpApprovalDate(arpApprovalDate);
    }

    /**
    * Returns the arp master sid of this arp outbound.
    *
    * @return the arp master sid of this arp outbound
    */
    @Override
    public int getArpMasterSid() {
        return _arpOutbound.getArpMasterSid();
    }

    /**
    * Sets the arp master sid of this arp outbound.
    *
    * @param arpMasterSid the arp master sid of this arp outbound
    */
    @Override
    public void setArpMasterSid(int arpMasterSid) {
        _arpOutbound.setArpMasterSid(arpMasterSid);
    }

    /**
    * Returns the arp creation date of this arp outbound.
    *
    * @return the arp creation date of this arp outbound
    */
    @Override
    public java.util.Date getArpCreationDate() {
        return _arpOutbound.getArpCreationDate();
    }

    /**
    * Sets the arp creation date of this arp outbound.
    *
    * @param arpCreationDate the arp creation date of this arp outbound
    */
    @Override
    public void setArpCreationDate(java.util.Date arpCreationDate) {
        _arpOutbound.setArpCreationDate(arpCreationDate);
    }

    /**
    * Returns the check record of this arp outbound.
    *
    * @return the check record of this arp outbound
    */
    @Override
    public boolean getCheckRecord() {
        return _arpOutbound.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this arp outbound is check record.
    *
    * @return <code>true</code> if this arp outbound is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _arpOutbound.isCheckRecord();
    }

    /**
    * Sets whether this arp outbound is check record.
    *
    * @param checkRecord the check record of this arp outbound
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _arpOutbound.setCheckRecord(checkRecord);
    }

    /**
    * Returns the arp ID of this arp outbound.
    *
    * @return the arp ID of this arp outbound
    */
    @Override
    public int getArpId() {
        return _arpOutbound.getArpId();
    }

    /**
    * Sets the arp ID of this arp outbound.
    *
    * @param arpId the arp ID of this arp outbound
    */
    @Override
    public void setArpId(int arpId) {
        _arpOutbound.setArpId(arpId);
    }

    /**
    * Returns the account of this arp outbound.
    *
    * @return the account of this arp outbound
    */
    @Override
    public java.lang.String getAccount() {
        return _arpOutbound.getAccount();
    }

    /**
    * Sets the account of this arp outbound.
    *
    * @param account the account of this arp outbound
    */
    @Override
    public void setAccount(java.lang.String account) {
        _arpOutbound.setAccount(account);
    }

    /**
    * Returns the outbound status of this arp outbound.
    *
    * @return the outbound status of this arp outbound
    */
    @Override
    public boolean getOutboundStatus() {
        return _arpOutbound.getOutboundStatus();
    }

    /**
    * Returns <code>true</code> if this arp outbound is outbound status.
    *
    * @return <code>true</code> if this arp outbound is outbound status; <code>false</code> otherwise
    */
    @Override
    public boolean isOutboundStatus() {
        return _arpOutbound.isOutboundStatus();
    }

    /**
    * Sets whether this arp outbound is outbound status.
    *
    * @param outboundStatus the outbound status of this arp outbound
    */
    @Override
    public void setOutboundStatus(boolean outboundStatus) {
        _arpOutbound.setOutboundStatus(outboundStatus);
    }

    /**
    * Returns the period sid of this arp outbound.
    *
    * @return the period sid of this arp outbound
    */
    @Override
    public int getPeriodSid() {
        return _arpOutbound.getPeriodSid();
    }

    /**
    * Sets the period sid of this arp outbound.
    *
    * @param periodSid the period sid of this arp outbound
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _arpOutbound.setPeriodSid(periodSid);
    }

    /**
    * Returns the item master sid of this arp outbound.
    *
    * @return the item master sid of this arp outbound
    */
    @Override
    public int getItemMasterSid() {
        return _arpOutbound.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this arp outbound.
    *
    * @param itemMasterSid the item master sid of this arp outbound
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _arpOutbound.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the rs model sid of this arp outbound.
    *
    * @return the rs model sid of this arp outbound
    */
    @Override
    public int getRsModelSid() {
        return _arpOutbound.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this arp outbound.
    *
    * @param rsModelSid the rs model sid of this arp outbound
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _arpOutbound.setRsModelSid(rsModelSid);
    }

    @Override
    public boolean isNew() {
        return _arpOutbound.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _arpOutbound.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _arpOutbound.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _arpOutbound.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _arpOutbound.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _arpOutbound.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _arpOutbound.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _arpOutbound.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _arpOutbound.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _arpOutbound.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _arpOutbound.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ArpOutboundWrapper((ArpOutbound) _arpOutbound.clone());
    }

    @Override
    public int compareTo(ArpOutbound arpOutbound) {
        return _arpOutbound.compareTo(arpOutbound);
    }

    @Override
    public int hashCode() {
        return _arpOutbound.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ArpOutbound> toCacheModel() {
        return _arpOutbound.toCacheModel();
    }

    @Override
    public ArpOutbound toEscapedModel() {
        return new ArpOutboundWrapper(_arpOutbound.toEscapedModel());
    }

    @Override
    public ArpOutbound toUnescapedModel() {
        return new ArpOutboundWrapper(_arpOutbound.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _arpOutbound.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _arpOutbound.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _arpOutbound.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ArpOutboundWrapper)) {
            return false;
        }

        ArpOutboundWrapper arpOutboundWrapper = (ArpOutboundWrapper) obj;

        if (Validator.equals(_arpOutbound, arpOutboundWrapper._arpOutbound)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ArpOutbound getWrappedArpOutbound() {
        return _arpOutbound;
    }

    @Override
    public ArpOutbound getWrappedModel() {
        return _arpOutbound;
    }

    @Override
    public void resetOriginalValues() {
        _arpOutbound.resetOriginalValues();
    }
}
