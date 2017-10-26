package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImtdCfpDetails}.
 * </p>
 *
 * @author
 * @see ImtdCfpDetails
 * @generated
 */
public class ImtdCfpDetailsWrapper implements ImtdCfpDetails,
    ModelWrapper<ImtdCfpDetails> {
    private ImtdCfpDetails _imtdCfpDetails;

    public ImtdCfpDetailsWrapper(ImtdCfpDetails imtdCfpDetails) {
        _imtdCfpDetails = imtdCfpDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdCfpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdCfpDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyNo", getCompanyNo());
        attributes.put("imtdCfpDetailsSid", getImtdCfpDetailsSid());
        attributes.put("cfpDetailsStartDate", getCfpDetailsStartDate());
        attributes.put("companyType", getCompanyType());
        attributes.put("cfpDetailsTcStartDate", getCfpDetailsTcStartDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("cfpDetailsTcEndDate", getCfpDetailsTcEndDate());
        attributes.put("cfpDetailsCreatedDate", getCfpDetailsCreatedDate());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("cfpDetailsModifiedDate", getCfpDetailsModifiedDate());
        attributes.put("cfpDetailsAttachedStatus", getCfpDetailsAttachedStatus());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("cfpDetailsAttachedDate", getCfpDetailsAttachedDate());
        attributes.put("cfpDetailsEndDate", getCfpDetailsEndDate());
        attributes.put("companyId", getCompanyId());
        attributes.put("cfpDetailsTradeClass", getCfpDetailsTradeClass());
        attributes.put("tradingPartnerContractNo", getTradingPartnerContractNo());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("cfpDetailsModifiedBy", getCfpDetailsModifiedBy());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("cfpDetailsSid", getCfpDetailsSid());
        attributes.put("companyStatus", getCompanyStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyName", getCompanyName());
        attributes.put("operation", getOperation());
        attributes.put("cfpDetailsCreatedBy", getCfpDetailsCreatedBy());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        Integer imtdCfpDetailsSid = (Integer) attributes.get(
                "imtdCfpDetailsSid");

        if (imtdCfpDetailsSid != null) {
            setImtdCfpDetailsSid(imtdCfpDetailsSid);
        }

        Date cfpDetailsStartDate = (Date) attributes.get("cfpDetailsStartDate");

        if (cfpDetailsStartDate != null) {
            setCfpDetailsStartDate(cfpDetailsStartDate);
        }

        String companyType = (String) attributes.get("companyType");

        if (companyType != null) {
            setCompanyType(companyType);
        }

        Date cfpDetailsTcStartDate = (Date) attributes.get(
                "cfpDetailsTcStartDate");

        if (cfpDetailsTcStartDate != null) {
            setCfpDetailsTcStartDate(cfpDetailsTcStartDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date cfpDetailsTcEndDate = (Date) attributes.get("cfpDetailsTcEndDate");

        if (cfpDetailsTcEndDate != null) {
            setCfpDetailsTcEndDate(cfpDetailsTcEndDate);
        }

        Date cfpDetailsCreatedDate = (Date) attributes.get(
                "cfpDetailsCreatedDate");

        if (cfpDetailsCreatedDate != null) {
            setCfpDetailsCreatedDate(cfpDetailsCreatedDate);
        }

        Date imtdCreatedDate = (Date) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        Date cfpDetailsModifiedDate = (Date) attributes.get(
                "cfpDetailsModifiedDate");

        if (cfpDetailsModifiedDate != null) {
            setCfpDetailsModifiedDate(cfpDetailsModifiedDate);
        }

        Integer cfpDetailsAttachedStatus = (Integer) attributes.get(
                "cfpDetailsAttachedStatus");

        if (cfpDetailsAttachedStatus != null) {
            setCfpDetailsAttachedStatus(cfpDetailsAttachedStatus);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date cfpDetailsAttachedDate = (Date) attributes.get(
                "cfpDetailsAttachedDate");

        if (cfpDetailsAttachedDate != null) {
            setCfpDetailsAttachedDate(cfpDetailsAttachedDate);
        }

        Date cfpDetailsEndDate = (Date) attributes.get("cfpDetailsEndDate");

        if (cfpDetailsEndDate != null) {
            setCfpDetailsEndDate(cfpDetailsEndDate);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String cfpDetailsTradeClass = (String) attributes.get(
                "cfpDetailsTradeClass");

        if (cfpDetailsTradeClass != null) {
            setCfpDetailsTradeClass(cfpDetailsTradeClass);
        }

        String tradingPartnerContractNo = (String) attributes.get(
                "tradingPartnerContractNo");

        if (tradingPartnerContractNo != null) {
            setTradingPartnerContractNo(tradingPartnerContractNo);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String usersSid = (String) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Date companyStartDate = (Date) attributes.get("companyStartDate");

        if (companyStartDate != null) {
            setCompanyStartDate(companyStartDate);
        }

        String cfpDetailsModifiedBy = (String) attributes.get(
                "cfpDetailsModifiedBy");

        if (cfpDetailsModifiedBy != null) {
            setCfpDetailsModifiedBy(cfpDetailsModifiedBy);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer cfpDetailsSid = (Integer) attributes.get("cfpDetailsSid");

        if (cfpDetailsSid != null) {
            setCfpDetailsSid(cfpDetailsSid);
        }

        Integer companyStatus = (Integer) attributes.get("companyStatus");

        if (companyStatus != null) {
            setCompanyStatus(companyStatus);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        String cfpDetailsCreatedBy = (String) attributes.get(
                "cfpDetailsCreatedBy");

        if (cfpDetailsCreatedBy != null) {
            setCfpDetailsCreatedBy(cfpDetailsCreatedBy);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    /**
    * Returns the primary key of this imtd cfp details.
    *
    * @return the primary key of this imtd cfp details
    */
    @Override
    public int getPrimaryKey() {
        return _imtdCfpDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this imtd cfp details.
    *
    * @param primaryKey the primary key of this imtd cfp details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _imtdCfpDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the company no of this imtd cfp details.
    *
    * @return the company no of this imtd cfp details
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _imtdCfpDetails.getCompanyNo();
    }

    /**
    * Sets the company no of this imtd cfp details.
    *
    * @param companyNo the company no of this imtd cfp details
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _imtdCfpDetails.setCompanyNo(companyNo);
    }

    /**
    * Returns the imtd cfp details sid of this imtd cfp details.
    *
    * @return the imtd cfp details sid of this imtd cfp details
    */
    @Override
    public int getImtdCfpDetailsSid() {
        return _imtdCfpDetails.getImtdCfpDetailsSid();
    }

    /**
    * Sets the imtd cfp details sid of this imtd cfp details.
    *
    * @param imtdCfpDetailsSid the imtd cfp details sid of this imtd cfp details
    */
    @Override
    public void setImtdCfpDetailsSid(int imtdCfpDetailsSid) {
        _imtdCfpDetails.setImtdCfpDetailsSid(imtdCfpDetailsSid);
    }

    /**
    * Returns the cfp details start date of this imtd cfp details.
    *
    * @return the cfp details start date of this imtd cfp details
    */
    @Override
    public java.util.Date getCfpDetailsStartDate() {
        return _imtdCfpDetails.getCfpDetailsStartDate();
    }

    /**
    * Sets the cfp details start date of this imtd cfp details.
    *
    * @param cfpDetailsStartDate the cfp details start date of this imtd cfp details
    */
    @Override
    public void setCfpDetailsStartDate(java.util.Date cfpDetailsStartDate) {
        _imtdCfpDetails.setCfpDetailsStartDate(cfpDetailsStartDate);
    }

    /**
    * Returns the company type of this imtd cfp details.
    *
    * @return the company type of this imtd cfp details
    */
    @Override
    public java.lang.String getCompanyType() {
        return _imtdCfpDetails.getCompanyType();
    }

    /**
    * Sets the company type of this imtd cfp details.
    *
    * @param companyType the company type of this imtd cfp details
    */
    @Override
    public void setCompanyType(java.lang.String companyType) {
        _imtdCfpDetails.setCompanyType(companyType);
    }

    /**
    * Returns the cfp details tc start date of this imtd cfp details.
    *
    * @return the cfp details tc start date of this imtd cfp details
    */
    @Override
    public java.util.Date getCfpDetailsTcStartDate() {
        return _imtdCfpDetails.getCfpDetailsTcStartDate();
    }

    /**
    * Sets the cfp details tc start date of this imtd cfp details.
    *
    * @param cfpDetailsTcStartDate the cfp details tc start date of this imtd cfp details
    */
    @Override
    public void setCfpDetailsTcStartDate(java.util.Date cfpDetailsTcStartDate) {
        _imtdCfpDetails.setCfpDetailsTcStartDate(cfpDetailsTcStartDate);
    }

    /**
    * Returns the modified by of this imtd cfp details.
    *
    * @return the modified by of this imtd cfp details
    */
    @Override
    public int getModifiedBy() {
        return _imtdCfpDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this imtd cfp details.
    *
    * @param modifiedBy the modified by of this imtd cfp details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _imtdCfpDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this imtd cfp details.
    *
    * @return the created date of this imtd cfp details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _imtdCfpDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this imtd cfp details.
    *
    * @param createdDate the created date of this imtd cfp details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _imtdCfpDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the cfp details tc end date of this imtd cfp details.
    *
    * @return the cfp details tc end date of this imtd cfp details
    */
    @Override
    public java.util.Date getCfpDetailsTcEndDate() {
        return _imtdCfpDetails.getCfpDetailsTcEndDate();
    }

    /**
    * Sets the cfp details tc end date of this imtd cfp details.
    *
    * @param cfpDetailsTcEndDate the cfp details tc end date of this imtd cfp details
    */
    @Override
    public void setCfpDetailsTcEndDate(java.util.Date cfpDetailsTcEndDate) {
        _imtdCfpDetails.setCfpDetailsTcEndDate(cfpDetailsTcEndDate);
    }

    /**
    * Returns the cfp details created date of this imtd cfp details.
    *
    * @return the cfp details created date of this imtd cfp details
    */
    @Override
    public java.util.Date getCfpDetailsCreatedDate() {
        return _imtdCfpDetails.getCfpDetailsCreatedDate();
    }

    /**
    * Sets the cfp details created date of this imtd cfp details.
    *
    * @param cfpDetailsCreatedDate the cfp details created date of this imtd cfp details
    */
    @Override
    public void setCfpDetailsCreatedDate(java.util.Date cfpDetailsCreatedDate) {
        _imtdCfpDetails.setCfpDetailsCreatedDate(cfpDetailsCreatedDate);
    }

    /**
    * Returns the imtd created date of this imtd cfp details.
    *
    * @return the imtd created date of this imtd cfp details
    */
    @Override
    public java.util.Date getImtdCreatedDate() {
        return _imtdCfpDetails.getImtdCreatedDate();
    }

    /**
    * Sets the imtd created date of this imtd cfp details.
    *
    * @param imtdCreatedDate the imtd created date of this imtd cfp details
    */
    @Override
    public void setImtdCreatedDate(java.util.Date imtdCreatedDate) {
        _imtdCfpDetails.setImtdCreatedDate(imtdCreatedDate);
    }

    /**
    * Returns the cfp details modified date of this imtd cfp details.
    *
    * @return the cfp details modified date of this imtd cfp details
    */
    @Override
    public java.util.Date getCfpDetailsModifiedDate() {
        return _imtdCfpDetails.getCfpDetailsModifiedDate();
    }

    /**
    * Sets the cfp details modified date of this imtd cfp details.
    *
    * @param cfpDetailsModifiedDate the cfp details modified date of this imtd cfp details
    */
    @Override
    public void setCfpDetailsModifiedDate(java.util.Date cfpDetailsModifiedDate) {
        _imtdCfpDetails.setCfpDetailsModifiedDate(cfpDetailsModifiedDate);
    }

    /**
    * Returns the cfp details attached status of this imtd cfp details.
    *
    * @return the cfp details attached status of this imtd cfp details
    */
    @Override
    public int getCfpDetailsAttachedStatus() {
        return _imtdCfpDetails.getCfpDetailsAttachedStatus();
    }

    /**
    * Sets the cfp details attached status of this imtd cfp details.
    *
    * @param cfpDetailsAttachedStatus the cfp details attached status of this imtd cfp details
    */
    @Override
    public void setCfpDetailsAttachedStatus(int cfpDetailsAttachedStatus) {
        _imtdCfpDetails.setCfpDetailsAttachedStatus(cfpDetailsAttachedStatus);
    }

    /**
    * Returns the check record of this imtd cfp details.
    *
    * @return the check record of this imtd cfp details
    */
    @Override
    public boolean getCheckRecord() {
        return _imtdCfpDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this imtd cfp details is check record.
    *
    * @return <code>true</code> if this imtd cfp details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _imtdCfpDetails.isCheckRecord();
    }

    /**
    * Sets whether this imtd cfp details is check record.
    *
    * @param checkRecord the check record of this imtd cfp details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _imtdCfpDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the cfp details attached date of this imtd cfp details.
    *
    * @return the cfp details attached date of this imtd cfp details
    */
    @Override
    public java.util.Date getCfpDetailsAttachedDate() {
        return _imtdCfpDetails.getCfpDetailsAttachedDate();
    }

    /**
    * Sets the cfp details attached date of this imtd cfp details.
    *
    * @param cfpDetailsAttachedDate the cfp details attached date of this imtd cfp details
    */
    @Override
    public void setCfpDetailsAttachedDate(java.util.Date cfpDetailsAttachedDate) {
        _imtdCfpDetails.setCfpDetailsAttachedDate(cfpDetailsAttachedDate);
    }

    /**
    * Returns the cfp details end date of this imtd cfp details.
    *
    * @return the cfp details end date of this imtd cfp details
    */
    @Override
    public java.util.Date getCfpDetailsEndDate() {
        return _imtdCfpDetails.getCfpDetailsEndDate();
    }

    /**
    * Sets the cfp details end date of this imtd cfp details.
    *
    * @param cfpDetailsEndDate the cfp details end date of this imtd cfp details
    */
    @Override
    public void setCfpDetailsEndDate(java.util.Date cfpDetailsEndDate) {
        _imtdCfpDetails.setCfpDetailsEndDate(cfpDetailsEndDate);
    }

    /**
    * Returns the company ID of this imtd cfp details.
    *
    * @return the company ID of this imtd cfp details
    */
    @Override
    public java.lang.String getCompanyId() {
        return _imtdCfpDetails.getCompanyId();
    }

    /**
    * Sets the company ID of this imtd cfp details.
    *
    * @param companyId the company ID of this imtd cfp details
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _imtdCfpDetails.setCompanyId(companyId);
    }

    /**
    * Returns the cfp details trade class of this imtd cfp details.
    *
    * @return the cfp details trade class of this imtd cfp details
    */
    @Override
    public java.lang.String getCfpDetailsTradeClass() {
        return _imtdCfpDetails.getCfpDetailsTradeClass();
    }

    /**
    * Sets the cfp details trade class of this imtd cfp details.
    *
    * @param cfpDetailsTradeClass the cfp details trade class of this imtd cfp details
    */
    @Override
    public void setCfpDetailsTradeClass(java.lang.String cfpDetailsTradeClass) {
        _imtdCfpDetails.setCfpDetailsTradeClass(cfpDetailsTradeClass);
    }

    /**
    * Returns the trading partner contract no of this imtd cfp details.
    *
    * @return the trading partner contract no of this imtd cfp details
    */
    @Override
    public java.lang.String getTradingPartnerContractNo() {
        return _imtdCfpDetails.getTradingPartnerContractNo();
    }

    /**
    * Sets the trading partner contract no of this imtd cfp details.
    *
    * @param tradingPartnerContractNo the trading partner contract no of this imtd cfp details
    */
    @Override
    public void setTradingPartnerContractNo(
        java.lang.String tradingPartnerContractNo) {
        _imtdCfpDetails.setTradingPartnerContractNo(tradingPartnerContractNo);
    }

    /**
    * Returns the created by of this imtd cfp details.
    *
    * @return the created by of this imtd cfp details
    */
    @Override
    public int getCreatedBy() {
        return _imtdCfpDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this imtd cfp details.
    *
    * @param createdBy the created by of this imtd cfp details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _imtdCfpDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the users sid of this imtd cfp details.
    *
    * @return the users sid of this imtd cfp details
    */
    @Override
    public java.lang.String getUsersSid() {
        return _imtdCfpDetails.getUsersSid();
    }

    /**
    * Sets the users sid of this imtd cfp details.
    *
    * @param usersSid the users sid of this imtd cfp details
    */
    @Override
    public void setUsersSid(java.lang.String usersSid) {
        _imtdCfpDetails.setUsersSid(usersSid);
    }

    /**
    * Returns the company start date of this imtd cfp details.
    *
    * @return the company start date of this imtd cfp details
    */
    @Override
    public java.util.Date getCompanyStartDate() {
        return _imtdCfpDetails.getCompanyStartDate();
    }

    /**
    * Sets the company start date of this imtd cfp details.
    *
    * @param companyStartDate the company start date of this imtd cfp details
    */
    @Override
    public void setCompanyStartDate(java.util.Date companyStartDate) {
        _imtdCfpDetails.setCompanyStartDate(companyStartDate);
    }

    /**
    * Returns the cfp details modified by of this imtd cfp details.
    *
    * @return the cfp details modified by of this imtd cfp details
    */
    @Override
    public java.lang.String getCfpDetailsModifiedBy() {
        return _imtdCfpDetails.getCfpDetailsModifiedBy();
    }

    /**
    * Sets the cfp details modified by of this imtd cfp details.
    *
    * @param cfpDetailsModifiedBy the cfp details modified by of this imtd cfp details
    */
    @Override
    public void setCfpDetailsModifiedBy(java.lang.String cfpDetailsModifiedBy) {
        _imtdCfpDetails.setCfpDetailsModifiedBy(cfpDetailsModifiedBy);
    }

    /**
    * Returns the company end date of this imtd cfp details.
    *
    * @return the company end date of this imtd cfp details
    */
    @Override
    public java.util.Date getCompanyEndDate() {
        return _imtdCfpDetails.getCompanyEndDate();
    }

    /**
    * Sets the company end date of this imtd cfp details.
    *
    * @param companyEndDate the company end date of this imtd cfp details
    */
    @Override
    public void setCompanyEndDate(java.util.Date companyEndDate) {
        _imtdCfpDetails.setCompanyEndDate(companyEndDate);
    }

    /**
    * Returns the company master sid of this imtd cfp details.
    *
    * @return the company master sid of this imtd cfp details
    */
    @Override
    public int getCompanyMasterSid() {
        return _imtdCfpDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this imtd cfp details.
    *
    * @param companyMasterSid the company master sid of this imtd cfp details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _imtdCfpDetails.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the cfp model sid of this imtd cfp details.
    *
    * @return the cfp model sid of this imtd cfp details
    */
    @Override
    public int getCfpModelSid() {
        return _imtdCfpDetails.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this imtd cfp details.
    *
    * @param cfpModelSid the cfp model sid of this imtd cfp details
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _imtdCfpDetails.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the cfp details sid of this imtd cfp details.
    *
    * @return the cfp details sid of this imtd cfp details
    */
    @Override
    public int getCfpDetailsSid() {
        return _imtdCfpDetails.getCfpDetailsSid();
    }

    /**
    * Sets the cfp details sid of this imtd cfp details.
    *
    * @param cfpDetailsSid the cfp details sid of this imtd cfp details
    */
    @Override
    public void setCfpDetailsSid(int cfpDetailsSid) {
        _imtdCfpDetails.setCfpDetailsSid(cfpDetailsSid);
    }

    /**
    * Returns the company status of this imtd cfp details.
    *
    * @return the company status of this imtd cfp details
    */
    @Override
    public int getCompanyStatus() {
        return _imtdCfpDetails.getCompanyStatus();
    }

    /**
    * Sets the company status of this imtd cfp details.
    *
    * @param companyStatus the company status of this imtd cfp details
    */
    @Override
    public void setCompanyStatus(int companyStatus) {
        _imtdCfpDetails.setCompanyStatus(companyStatus);
    }

    /**
    * Returns the modified date of this imtd cfp details.
    *
    * @return the modified date of this imtd cfp details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _imtdCfpDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this imtd cfp details.
    *
    * @param modifiedDate the modified date of this imtd cfp details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _imtdCfpDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the company name of this imtd cfp details.
    *
    * @return the company name of this imtd cfp details
    */
    @Override
    public java.lang.String getCompanyName() {
        return _imtdCfpDetails.getCompanyName();
    }

    /**
    * Sets the company name of this imtd cfp details.
    *
    * @param companyName the company name of this imtd cfp details
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _imtdCfpDetails.setCompanyName(companyName);
    }

    /**
    * Returns the operation of this imtd cfp details.
    *
    * @return the operation of this imtd cfp details
    */
    @Override
    public java.lang.String getOperation() {
        return _imtdCfpDetails.getOperation();
    }

    /**
    * Sets the operation of this imtd cfp details.
    *
    * @param operation the operation of this imtd cfp details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _imtdCfpDetails.setOperation(operation);
    }

    /**
    * Returns the cfp details created by of this imtd cfp details.
    *
    * @return the cfp details created by of this imtd cfp details
    */
    @Override
    public java.lang.String getCfpDetailsCreatedBy() {
        return _imtdCfpDetails.getCfpDetailsCreatedBy();
    }

    /**
    * Sets the cfp details created by of this imtd cfp details.
    *
    * @param cfpDetailsCreatedBy the cfp details created by of this imtd cfp details
    */
    @Override
    public void setCfpDetailsCreatedBy(java.lang.String cfpDetailsCreatedBy) {
        _imtdCfpDetails.setCfpDetailsCreatedBy(cfpDetailsCreatedBy);
    }

    /**
    * Returns the session ID of this imtd cfp details.
    *
    * @return the session ID of this imtd cfp details
    */
    @Override
    public java.lang.String getSessionId() {
        return _imtdCfpDetails.getSessionId();
    }

    /**
    * Sets the session ID of this imtd cfp details.
    *
    * @param sessionId the session ID of this imtd cfp details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _imtdCfpDetails.setSessionId(sessionId);
    }

    @Override
    public boolean isNew() {
        return _imtdCfpDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _imtdCfpDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _imtdCfpDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _imtdCfpDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _imtdCfpDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _imtdCfpDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _imtdCfpDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _imtdCfpDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _imtdCfpDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _imtdCfpDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _imtdCfpDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImtdCfpDetailsWrapper((ImtdCfpDetails) _imtdCfpDetails.clone());
    }

    @Override
    public int compareTo(ImtdCfpDetails imtdCfpDetails) {
        return _imtdCfpDetails.compareTo(imtdCfpDetails);
    }

    @Override
    public int hashCode() {
        return _imtdCfpDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ImtdCfpDetails> toCacheModel() {
        return _imtdCfpDetails.toCacheModel();
    }

    @Override
    public ImtdCfpDetails toEscapedModel() {
        return new ImtdCfpDetailsWrapper(_imtdCfpDetails.toEscapedModel());
    }

    @Override
    public ImtdCfpDetails toUnescapedModel() {
        return new ImtdCfpDetailsWrapper(_imtdCfpDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _imtdCfpDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _imtdCfpDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _imtdCfpDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdCfpDetailsWrapper)) {
            return false;
        }

        ImtdCfpDetailsWrapper imtdCfpDetailsWrapper = (ImtdCfpDetailsWrapper) obj;

        if (Validator.equals(_imtdCfpDetails,
                    imtdCfpDetailsWrapper._imtdCfpDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImtdCfpDetails getWrappedImtdCfpDetails() {
        return _imtdCfpDetails;
    }

    @Override
    public ImtdCfpDetails getWrappedModel() {
        return _imtdCfpDetails;
    }

    @Override
    public void resetOriginalValues() {
        _imtdCfpDetails.resetOriginalValues();
    }
}
