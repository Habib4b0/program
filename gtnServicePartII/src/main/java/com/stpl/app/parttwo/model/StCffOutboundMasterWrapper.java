package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StCffOutboundMaster}.
 * </p>
 *
 * @author
 * @see StCffOutboundMaster
 * @generated
 */
public class StCffOutboundMasterWrapper implements StCffOutboundMaster,
    ModelWrapper<StCffOutboundMaster> {
    private StCffOutboundMaster _stCffOutboundMaster;

    public StCffOutboundMasterWrapper(StCffOutboundMaster stCffOutboundMaster) {
        _stCffOutboundMaster = stCffOutboundMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return StCffOutboundMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StCffOutboundMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("etlCheckRecord", getEtlCheckRecord());
        attributes.put("customerName", getCustomerName());
        attributes.put("contractHolderId", getContractHolderId());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("year", getYear());
        attributes.put("financialForecastApprovalDate",
            getFinancialForecastApprovalDate());
        attributes.put("deductionId", getDeductionId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("deductionPerUnit", getDeductionPerUnit());
        attributes.put("cogsPerUnit", getCogsPerUnit());
        attributes.put("contractType", getContractType());
        attributes.put("source", getSource());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("financialForecastId", getFinancialForecastId());
        attributes.put("projectId", getProjectId());
        attributes.put("customerNo", getCustomerNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("salesDollars", getSalesDollars());
        attributes.put("month", getMonth());
        attributes.put("cffDetailsSid", getCffDetailsSid());
        attributes.put("type", getType());
        attributes.put("deductionType", getDeductionType());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractName", getContractName());
        attributes.put("deductionRate", getDeductionRate());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("cogsAmount", getCogsAmount());
        attributes.put("deductionNo", getDeductionNo());
        attributes.put("financialForecastCreationDate",
            getFinancialForecastCreationDate());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("salesUnits", getSalesUnits());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("deductionInclusion", getDeductionInclusion());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("contractHolderName", getContractHolderName());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("companyName", getCompanyName());
        attributes.put("customerId", getCustomerId());
        attributes.put("itemId", getItemId());
        attributes.put("netProfitDollars", getNetProfitDollars());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionCategory1", getDeductionCategory1());
        attributes.put("deductionCategory2", getDeductionCategory2());
        attributes.put("contractHolderNo", getContractHolderNo());
        attributes.put("deductionCategory3", getDeductionCategory3());
        attributes.put("itemNo", getItemNo());
        attributes.put("deductionCategory4", getDeductionCategory4());
        attributes.put("deductionCategory5", getDeductionCategory5());
        attributes.put("deductionCategory6", getDeductionCategory6());
        attributes.put("contractId", getContractId());
        attributes.put("deductionProgram", getDeductionProgram());
        attributes.put("businessUnitId", getBusinessUnitId());
        attributes.put("projectionName", getProjectionName());
        attributes.put("userId", getUserId());
        attributes.put("companyId", getCompanyId());
        attributes.put("outboundStatus", getOutboundStatus());
        attributes.put("originalBatchId", getOriginalBatchId());
        attributes.put("deductionName", getDeductionName());
        attributes.put("netProfitPerUnit", getNetProfitPerUnit());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("salesInclusion", getSalesInclusion());
        attributes.put("batchId", getBatchId());
        attributes.put("financialForecastName", getFinancialForecastName());
        attributes.put("netSalesDollar", getNetSalesDollar());
        attributes.put("deductionDollars", getDeductionDollars());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean etlCheckRecord = (Boolean) attributes.get("etlCheckRecord");

        if (etlCheckRecord != null) {
            setEtlCheckRecord(etlCheckRecord);
        }

        String customerName = (String) attributes.get("customerName");

        if (customerName != null) {
            setCustomerName(customerName);
        }

        String contractHolderId = (String) attributes.get("contractHolderId");

        if (contractHolderId != null) {
            setContractHolderId(contractHolderId);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        Date financialForecastApprovalDate = (Date) attributes.get(
                "financialForecastApprovalDate");

        if (financialForecastApprovalDate != null) {
            setFinancialForecastApprovalDate(financialForecastApprovalDate);
        }

        String deductionId = (String) attributes.get("deductionId");

        if (deductionId != null) {
            setDeductionId(deductionId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Double deductionPerUnit = (Double) attributes.get("deductionPerUnit");

        if (deductionPerUnit != null) {
            setDeductionPerUnit(deductionPerUnit);
        }

        Double cogsPerUnit = (Double) attributes.get("cogsPerUnit");

        if (cogsPerUnit != null) {
            setCogsPerUnit(cogsPerUnit);
        }

        String contractType = (String) attributes.get("contractType");

        if (contractType != null) {
            setContractType(contractType);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String financialForecastId = (String) attributes.get(
                "financialForecastId");

        if (financialForecastId != null) {
            setFinancialForecastId(financialForecastId);
        }

        String projectId = (String) attributes.get("projectId");

        if (projectId != null) {
            setProjectId(projectId);
        }

        String customerNo = (String) attributes.get("customerNo");

        if (customerNo != null) {
            setCustomerNo(customerNo);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Double salesDollars = (Double) attributes.get("salesDollars");

        if (salesDollars != null) {
            setSalesDollars(salesDollars);
        }

        Integer month = (Integer) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        Integer cffDetailsSid = (Integer) attributes.get("cffDetailsSid");

        if (cffDetailsSid != null) {
            setCffDetailsSid(cffDetailsSid);
        }

        String type = (String) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        Double deductionRate = (Double) attributes.get("deductionRate");

        if (deductionRate != null) {
            setDeductionRate(deductionRate);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }

        Double cogsAmount = (Double) attributes.get("cogsAmount");

        if (cogsAmount != null) {
            setCogsAmount(cogsAmount);
        }

        String deductionNo = (String) attributes.get("deductionNo");

        if (deductionNo != null) {
            setDeductionNo(deductionNo);
        }

        Date financialForecastCreationDate = (Date) attributes.get(
                "financialForecastCreationDate");

        if (financialForecastCreationDate != null) {
            setFinancialForecastCreationDate(financialForecastCreationDate);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        Double salesUnits = (Double) attributes.get("salesUnits");

        if (salesUnits != null) {
            setSalesUnits(salesUnits);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String contractHolderName = (String) attributes.get(
                "contractHolderName");

        if (contractHolderName != null) {
            setContractHolderName(contractHolderName);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String customerId = (String) attributes.get("customerId");

        if (customerId != null) {
            setCustomerId(customerId);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double netProfitDollars = (Double) attributes.get("netProfitDollars");

        if (netProfitDollars != null) {
            setNetProfitDollars(netProfitDollars);
        }

        Integer glCompanyMasterSid = (Integer) attributes.get(
                "glCompanyMasterSid");

        if (glCompanyMasterSid != null) {
            setGlCompanyMasterSid(glCompanyMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String deductionCategory1 = (String) attributes.get(
                "deductionCategory1");

        if (deductionCategory1 != null) {
            setDeductionCategory1(deductionCategory1);
        }

        String deductionCategory2 = (String) attributes.get(
                "deductionCategory2");

        if (deductionCategory2 != null) {
            setDeductionCategory2(deductionCategory2);
        }

        String contractHolderNo = (String) attributes.get("contractHolderNo");

        if (contractHolderNo != null) {
            setContractHolderNo(contractHolderNo);
        }

        String deductionCategory3 = (String) attributes.get(
                "deductionCategory3");

        if (deductionCategory3 != null) {
            setDeductionCategory3(deductionCategory3);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String deductionCategory4 = (String) attributes.get(
                "deductionCategory4");

        if (deductionCategory4 != null) {
            setDeductionCategory4(deductionCategory4);
        }

        String deductionCategory5 = (String) attributes.get(
                "deductionCategory5");

        if (deductionCategory5 != null) {
            setDeductionCategory5(deductionCategory5);
        }

        String deductionCategory6 = (String) attributes.get(
                "deductionCategory6");

        if (deductionCategory6 != null) {
            setDeductionCategory6(deductionCategory6);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String deductionProgram = (String) attributes.get("deductionProgram");

        if (deductionProgram != null) {
            setDeductionProgram(deductionProgram);
        }

        String businessUnitId = (String) attributes.get("businessUnitId");

        if (businessUnitId != null) {
            setBusinessUnitId(businessUnitId);
        }

        String projectionName = (String) attributes.get("projectionName");

        if (projectionName != null) {
            setProjectionName(projectionName);
        }

        String userId = (String) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String outboundStatus = (String) attributes.get("outboundStatus");

        if (outboundStatus != null) {
            setOutboundStatus(outboundStatus);
        }

        String originalBatchId = (String) attributes.get("originalBatchId");

        if (originalBatchId != null) {
            setOriginalBatchId(originalBatchId);
        }

        String deductionName = (String) attributes.get("deductionName");

        if (deductionName != null) {
            setDeductionName(deductionName);
        }

        Double netProfitPerUnit = (Double) attributes.get("netProfitPerUnit");

        if (netProfitPerUnit != null) {
            setNetProfitPerUnit(netProfitPerUnit);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String salesInclusion = (String) attributes.get("salesInclusion");

        if (salesInclusion != null) {
            setSalesInclusion(salesInclusion);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String financialForecastName = (String) attributes.get(
                "financialForecastName");

        if (financialForecastName != null) {
            setFinancialForecastName(financialForecastName);
        }

        Double netSalesDollar = (Double) attributes.get("netSalesDollar");

        if (netSalesDollar != null) {
            setNetSalesDollar(netSalesDollar);
        }

        Double deductionDollars = (Double) attributes.get("deductionDollars");

        if (deductionDollars != null) {
            setDeductionDollars(deductionDollars);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    /**
    * Returns the primary key of this st cff outbound master.
    *
    * @return the primary key of this st cff outbound master
    */
    @Override
    public com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK getPrimaryKey() {
        return _stCffOutboundMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st cff outbound master.
    *
    * @param primaryKey the primary key of this st cff outbound master
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK primaryKey) {
        _stCffOutboundMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the etl check record of this st cff outbound master.
    *
    * @return the etl check record of this st cff outbound master
    */
    @Override
    public boolean getEtlCheckRecord() {
        return _stCffOutboundMaster.getEtlCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st cff outbound master is etl check record.
    *
    * @return <code>true</code> if this st cff outbound master is etl check record; <code>false</code> otherwise
    */
    @Override
    public boolean isEtlCheckRecord() {
        return _stCffOutboundMaster.isEtlCheckRecord();
    }

    /**
    * Sets whether this st cff outbound master is etl check record.
    *
    * @param etlCheckRecord the etl check record of this st cff outbound master
    */
    @Override
    public void setEtlCheckRecord(boolean etlCheckRecord) {
        _stCffOutboundMaster.setEtlCheckRecord(etlCheckRecord);
    }

    /**
    * Returns the customer name of this st cff outbound master.
    *
    * @return the customer name of this st cff outbound master
    */
    @Override
    public java.lang.String getCustomerName() {
        return _stCffOutboundMaster.getCustomerName();
    }

    /**
    * Sets the customer name of this st cff outbound master.
    *
    * @param customerName the customer name of this st cff outbound master
    */
    @Override
    public void setCustomerName(java.lang.String customerName) {
        _stCffOutboundMaster.setCustomerName(customerName);
    }

    /**
    * Returns the contract holder ID of this st cff outbound master.
    *
    * @return the contract holder ID of this st cff outbound master
    */
    @Override
    public java.lang.String getContractHolderId() {
        return _stCffOutboundMaster.getContractHolderId();
    }

    /**
    * Sets the contract holder ID of this st cff outbound master.
    *
    * @param contractHolderId the contract holder ID of this st cff outbound master
    */
    @Override
    public void setContractHolderId(java.lang.String contractHolderId) {
        _stCffOutboundMaster.setContractHolderId(contractHolderId);
    }

    /**
    * Returns the business unit no of this st cff outbound master.
    *
    * @return the business unit no of this st cff outbound master
    */
    @Override
    public java.lang.String getBusinessUnitNo() {
        return _stCffOutboundMaster.getBusinessUnitNo();
    }

    /**
    * Sets the business unit no of this st cff outbound master.
    *
    * @param businessUnitNo the business unit no of this st cff outbound master
    */
    @Override
    public void setBusinessUnitNo(java.lang.String businessUnitNo) {
        _stCffOutboundMaster.setBusinessUnitNo(businessUnitNo);
    }

    /**
    * Returns the year of this st cff outbound master.
    *
    * @return the year of this st cff outbound master
    */
    @Override
    public java.lang.String getYear() {
        return _stCffOutboundMaster.getYear();
    }

    /**
    * Sets the year of this st cff outbound master.
    *
    * @param year the year of this st cff outbound master
    */
    @Override
    public void setYear(java.lang.String year) {
        _stCffOutboundMaster.setYear(year);
    }

    /**
    * Returns the financial forecast approval date of this st cff outbound master.
    *
    * @return the financial forecast approval date of this st cff outbound master
    */
    @Override
    public java.util.Date getFinancialForecastApprovalDate() {
        return _stCffOutboundMaster.getFinancialForecastApprovalDate();
    }

    /**
    * Sets the financial forecast approval date of this st cff outbound master.
    *
    * @param financialForecastApprovalDate the financial forecast approval date of this st cff outbound master
    */
    @Override
    public void setFinancialForecastApprovalDate(
        java.util.Date financialForecastApprovalDate) {
        _stCffOutboundMaster.setFinancialForecastApprovalDate(financialForecastApprovalDate);
    }

    /**
    * Returns the deduction ID of this st cff outbound master.
    *
    * @return the deduction ID of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionId() {
        return _stCffOutboundMaster.getDeductionId();
    }

    /**
    * Sets the deduction ID of this st cff outbound master.
    *
    * @param deductionId the deduction ID of this st cff outbound master
    */
    @Override
    public void setDeductionId(java.lang.String deductionId) {
        _stCffOutboundMaster.setDeductionId(deductionId);
    }

    /**
    * Returns the modified date of this st cff outbound master.
    *
    * @return the modified date of this st cff outbound master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _stCffOutboundMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this st cff outbound master.
    *
    * @param modifiedDate the modified date of this st cff outbound master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _stCffOutboundMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the deduction per unit of this st cff outbound master.
    *
    * @return the deduction per unit of this st cff outbound master
    */
    @Override
    public double getDeductionPerUnit() {
        return _stCffOutboundMaster.getDeductionPerUnit();
    }

    /**
    * Sets the deduction per unit of this st cff outbound master.
    *
    * @param deductionPerUnit the deduction per unit of this st cff outbound master
    */
    @Override
    public void setDeductionPerUnit(double deductionPerUnit) {
        _stCffOutboundMaster.setDeductionPerUnit(deductionPerUnit);
    }

    /**
    * Returns the cogs per unit of this st cff outbound master.
    *
    * @return the cogs per unit of this st cff outbound master
    */
    @Override
    public double getCogsPerUnit() {
        return _stCffOutboundMaster.getCogsPerUnit();
    }

    /**
    * Sets the cogs per unit of this st cff outbound master.
    *
    * @param cogsPerUnit the cogs per unit of this st cff outbound master
    */
    @Override
    public void setCogsPerUnit(double cogsPerUnit) {
        _stCffOutboundMaster.setCogsPerUnit(cogsPerUnit);
    }

    /**
    * Returns the contract type of this st cff outbound master.
    *
    * @return the contract type of this st cff outbound master
    */
    @Override
    public java.lang.String getContractType() {
        return _stCffOutboundMaster.getContractType();
    }

    /**
    * Sets the contract type of this st cff outbound master.
    *
    * @param contractType the contract type of this st cff outbound master
    */
    @Override
    public void setContractType(java.lang.String contractType) {
        _stCffOutboundMaster.setContractType(contractType);
    }

    /**
    * Returns the source of this st cff outbound master.
    *
    * @return the source of this st cff outbound master
    */
    @Override
    public java.lang.String getSource() {
        return _stCffOutboundMaster.getSource();
    }

    /**
    * Sets the source of this st cff outbound master.
    *
    * @param source the source of this st cff outbound master
    */
    @Override
    public void setSource(java.lang.String source) {
        _stCffOutboundMaster.setSource(source);
    }

    /**
    * Returns the business unit name of this st cff outbound master.
    *
    * @return the business unit name of this st cff outbound master
    */
    @Override
    public java.lang.String getBusinessUnitName() {
        return _stCffOutboundMaster.getBusinessUnitName();
    }

    /**
    * Sets the business unit name of this st cff outbound master.
    *
    * @param businessUnitName the business unit name of this st cff outbound master
    */
    @Override
    public void setBusinessUnitName(java.lang.String businessUnitName) {
        _stCffOutboundMaster.setBusinessUnitName(businessUnitName);
    }

    /**
    * Returns the contract master sid of this st cff outbound master.
    *
    * @return the contract master sid of this st cff outbound master
    */
    @Override
    public int getContractMasterSid() {
        return _stCffOutboundMaster.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this st cff outbound master.
    *
    * @param contractMasterSid the contract master sid of this st cff outbound master
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _stCffOutboundMaster.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the financial forecast ID of this st cff outbound master.
    *
    * @return the financial forecast ID of this st cff outbound master
    */
    @Override
    public java.lang.String getFinancialForecastId() {
        return _stCffOutboundMaster.getFinancialForecastId();
    }

    /**
    * Sets the financial forecast ID of this st cff outbound master.
    *
    * @param financialForecastId the financial forecast ID of this st cff outbound master
    */
    @Override
    public void setFinancialForecastId(java.lang.String financialForecastId) {
        _stCffOutboundMaster.setFinancialForecastId(financialForecastId);
    }

    /**
    * Returns the project ID of this st cff outbound master.
    *
    * @return the project ID of this st cff outbound master
    */
    @Override
    public java.lang.String getProjectId() {
        return _stCffOutboundMaster.getProjectId();
    }

    /**
    * Sets the project ID of this st cff outbound master.
    *
    * @param projectId the project ID of this st cff outbound master
    */
    @Override
    public void setProjectId(java.lang.String projectId) {
        _stCffOutboundMaster.setProjectId(projectId);
    }

    /**
    * Returns the customer no of this st cff outbound master.
    *
    * @return the customer no of this st cff outbound master
    */
    @Override
    public java.lang.String getCustomerNo() {
        return _stCffOutboundMaster.getCustomerNo();
    }

    /**
    * Sets the customer no of this st cff outbound master.
    *
    * @param customerNo the customer no of this st cff outbound master
    */
    @Override
    public void setCustomerNo(java.lang.String customerNo) {
        _stCffOutboundMaster.setCustomerNo(customerNo);
    }

    /**
    * Returns the modified by of this st cff outbound master.
    *
    * @return the modified by of this st cff outbound master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _stCffOutboundMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this st cff outbound master.
    *
    * @param modifiedBy the modified by of this st cff outbound master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _stCffOutboundMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the sales dollars of this st cff outbound master.
    *
    * @return the sales dollars of this st cff outbound master
    */
    @Override
    public double getSalesDollars() {
        return _stCffOutboundMaster.getSalesDollars();
    }

    /**
    * Sets the sales dollars of this st cff outbound master.
    *
    * @param salesDollars the sales dollars of this st cff outbound master
    */
    @Override
    public void setSalesDollars(double salesDollars) {
        _stCffOutboundMaster.setSalesDollars(salesDollars);
    }

    /**
    * Returns the month of this st cff outbound master.
    *
    * @return the month of this st cff outbound master
    */
    @Override
    public int getMonth() {
        return _stCffOutboundMaster.getMonth();
    }

    /**
    * Sets the month of this st cff outbound master.
    *
    * @param month the month of this st cff outbound master
    */
    @Override
    public void setMonth(int month) {
        _stCffOutboundMaster.setMonth(month);
    }

    /**
    * Returns the cff details sid of this st cff outbound master.
    *
    * @return the cff details sid of this st cff outbound master
    */
    @Override
    public int getCffDetailsSid() {
        return _stCffOutboundMaster.getCffDetailsSid();
    }

    /**
    * Sets the cff details sid of this st cff outbound master.
    *
    * @param cffDetailsSid the cff details sid of this st cff outbound master
    */
    @Override
    public void setCffDetailsSid(int cffDetailsSid) {
        _stCffOutboundMaster.setCffDetailsSid(cffDetailsSid);
    }

    /**
    * Returns the type of this st cff outbound master.
    *
    * @return the type of this st cff outbound master
    */
    @Override
    public java.lang.String getType() {
        return _stCffOutboundMaster.getType();
    }

    /**
    * Sets the type of this st cff outbound master.
    *
    * @param type the type of this st cff outbound master
    */
    @Override
    public void setType(java.lang.String type) {
        _stCffOutboundMaster.setType(type);
    }

    /**
    * Returns the deduction type of this st cff outbound master.
    *
    * @return the deduction type of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionType() {
        return _stCffOutboundMaster.getDeductionType();
    }

    /**
    * Sets the deduction type of this st cff outbound master.
    *
    * @param deductionType the deduction type of this st cff outbound master
    */
    @Override
    public void setDeductionType(java.lang.String deductionType) {
        _stCffOutboundMaster.setDeductionType(deductionType);
    }

    /**
    * Returns the company master sid of this st cff outbound master.
    *
    * @return the company master sid of this st cff outbound master
    */
    @Override
    public int getCompanyMasterSid() {
        return _stCffOutboundMaster.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this st cff outbound master.
    *
    * @param companyMasterSid the company master sid of this st cff outbound master
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _stCffOutboundMaster.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the check record of this st cff outbound master.
    *
    * @return the check record of this st cff outbound master
    */
    @Override
    public boolean getCheckRecord() {
        return _stCffOutboundMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st cff outbound master is check record.
    *
    * @return <code>true</code> if this st cff outbound master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stCffOutboundMaster.isCheckRecord();
    }

    /**
    * Sets whether this st cff outbound master is check record.
    *
    * @param checkRecord the check record of this st cff outbound master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stCffOutboundMaster.setCheckRecord(checkRecord);
    }

    /**
    * Returns the contract name of this st cff outbound master.
    *
    * @return the contract name of this st cff outbound master
    */
    @Override
    public java.lang.String getContractName() {
        return _stCffOutboundMaster.getContractName();
    }

    /**
    * Sets the contract name of this st cff outbound master.
    *
    * @param contractName the contract name of this st cff outbound master
    */
    @Override
    public void setContractName(java.lang.String contractName) {
        _stCffOutboundMaster.setContractName(contractName);
    }

    /**
    * Returns the deduction rate of this st cff outbound master.
    *
    * @return the deduction rate of this st cff outbound master
    */
    @Override
    public double getDeductionRate() {
        return _stCffOutboundMaster.getDeductionRate();
    }

    /**
    * Sets the deduction rate of this st cff outbound master.
    *
    * @param deductionRate the deduction rate of this st cff outbound master
    */
    @Override
    public void setDeductionRate(double deductionRate) {
        _stCffOutboundMaster.setDeductionRate(deductionRate);
    }

    /**
    * Returns the deduction category of this st cff outbound master.
    *
    * @return the deduction category of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionCategory() {
        return _stCffOutboundMaster.getDeductionCategory();
    }

    /**
    * Sets the deduction category of this st cff outbound master.
    *
    * @param deductionCategory the deduction category of this st cff outbound master
    */
    @Override
    public void setDeductionCategory(java.lang.String deductionCategory) {
        _stCffOutboundMaster.setDeductionCategory(deductionCategory);
    }

    /**
    * Returns the cogs amount of this st cff outbound master.
    *
    * @return the cogs amount of this st cff outbound master
    */
    @Override
    public double getCogsAmount() {
        return _stCffOutboundMaster.getCogsAmount();
    }

    /**
    * Sets the cogs amount of this st cff outbound master.
    *
    * @param cogsAmount the cogs amount of this st cff outbound master
    */
    @Override
    public void setCogsAmount(double cogsAmount) {
        _stCffOutboundMaster.setCogsAmount(cogsAmount);
    }

    /**
    * Returns the deduction no of this st cff outbound master.
    *
    * @return the deduction no of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionNo() {
        return _stCffOutboundMaster.getDeductionNo();
    }

    /**
    * Sets the deduction no of this st cff outbound master.
    *
    * @param deductionNo the deduction no of this st cff outbound master
    */
    @Override
    public void setDeductionNo(java.lang.String deductionNo) {
        _stCffOutboundMaster.setDeductionNo(deductionNo);
    }

    /**
    * Returns the financial forecast creation date of this st cff outbound master.
    *
    * @return the financial forecast creation date of this st cff outbound master
    */
    @Override
    public java.util.Date getFinancialForecastCreationDate() {
        return _stCffOutboundMaster.getFinancialForecastCreationDate();
    }

    /**
    * Sets the financial forecast creation date of this st cff outbound master.
    *
    * @param financialForecastCreationDate the financial forecast creation date of this st cff outbound master
    */
    @Override
    public void setFinancialForecastCreationDate(
        java.util.Date financialForecastCreationDate) {
        _stCffOutboundMaster.setFinancialForecastCreationDate(financialForecastCreationDate);
    }

    /**
    * Returns the company no of this st cff outbound master.
    *
    * @return the company no of this st cff outbound master
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _stCffOutboundMaster.getCompanyNo();
    }

    /**
    * Sets the company no of this st cff outbound master.
    *
    * @param companyNo the company no of this st cff outbound master
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _stCffOutboundMaster.setCompanyNo(companyNo);
    }

    /**
    * Returns the sales units of this st cff outbound master.
    *
    * @return the sales units of this st cff outbound master
    */
    @Override
    public double getSalesUnits() {
        return _stCffOutboundMaster.getSalesUnits();
    }

    /**
    * Sets the sales units of this st cff outbound master.
    *
    * @param salesUnits the sales units of this st cff outbound master
    */
    @Override
    public void setSalesUnits(double salesUnits) {
        _stCffOutboundMaster.setSalesUnits(salesUnits);
    }

    /**
    * Returns the session ID of this st cff outbound master.
    *
    * @return the session ID of this st cff outbound master
    */
    @Override
    public java.lang.String getSessionId() {
        return _stCffOutboundMaster.getSessionId();
    }

    /**
    * Sets the session ID of this st cff outbound master.
    *
    * @param sessionId the session ID of this st cff outbound master
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _stCffOutboundMaster.setSessionId(sessionId);
    }

    /**
    * Returns the item name of this st cff outbound master.
    *
    * @return the item name of this st cff outbound master
    */
    @Override
    public java.lang.String getItemName() {
        return _stCffOutboundMaster.getItemName();
    }

    /**
    * Sets the item name of this st cff outbound master.
    *
    * @param itemName the item name of this st cff outbound master
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _stCffOutboundMaster.setItemName(itemName);
    }

    /**
    * Returns the deduction inclusion of this st cff outbound master.
    *
    * @return the deduction inclusion of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionInclusion() {
        return _stCffOutboundMaster.getDeductionInclusion();
    }

    /**
    * Sets the deduction inclusion of this st cff outbound master.
    *
    * @param deductionInclusion the deduction inclusion of this st cff outbound master
    */
    @Override
    public void setDeductionInclusion(java.lang.String deductionInclusion) {
        _stCffOutboundMaster.setDeductionInclusion(deductionInclusion);
    }

    /**
    * Returns the rs model sid of this st cff outbound master.
    *
    * @return the rs model sid of this st cff outbound master
    */
    @Override
    public int getRsModelSid() {
        return _stCffOutboundMaster.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this st cff outbound master.
    *
    * @param rsModelSid the rs model sid of this st cff outbound master
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _stCffOutboundMaster.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the contract holder name of this st cff outbound master.
    *
    * @return the contract holder name of this st cff outbound master
    */
    @Override
    public java.lang.String getContractHolderName() {
        return _stCffOutboundMaster.getContractHolderName();
    }

    /**
    * Sets the contract holder name of this st cff outbound master.
    *
    * @param contractHolderName the contract holder name of this st cff outbound master
    */
    @Override
    public void setContractHolderName(java.lang.String contractHolderName) {
        _stCffOutboundMaster.setContractHolderName(contractHolderName);
    }

    /**
    * Returns the item master sid of this st cff outbound master.
    *
    * @return the item master sid of this st cff outbound master
    */
    @Override
    public int getItemMasterSid() {
        return _stCffOutboundMaster.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this st cff outbound master.
    *
    * @param itemMasterSid the item master sid of this st cff outbound master
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _stCffOutboundMaster.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the company name of this st cff outbound master.
    *
    * @return the company name of this st cff outbound master
    */
    @Override
    public java.lang.String getCompanyName() {
        return _stCffOutboundMaster.getCompanyName();
    }

    /**
    * Sets the company name of this st cff outbound master.
    *
    * @param companyName the company name of this st cff outbound master
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _stCffOutboundMaster.setCompanyName(companyName);
    }

    /**
    * Returns the customer ID of this st cff outbound master.
    *
    * @return the customer ID of this st cff outbound master
    */
    @Override
    public java.lang.String getCustomerId() {
        return _stCffOutboundMaster.getCustomerId();
    }

    /**
    * Sets the customer ID of this st cff outbound master.
    *
    * @param customerId the customer ID of this st cff outbound master
    */
    @Override
    public void setCustomerId(java.lang.String customerId) {
        _stCffOutboundMaster.setCustomerId(customerId);
    }

    /**
    * Returns the item ID of this st cff outbound master.
    *
    * @return the item ID of this st cff outbound master
    */
    @Override
    public java.lang.String getItemId() {
        return _stCffOutboundMaster.getItemId();
    }

    /**
    * Sets the item ID of this st cff outbound master.
    *
    * @param itemId the item ID of this st cff outbound master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _stCffOutboundMaster.setItemId(itemId);
    }

    /**
    * Returns the net profit dollars of this st cff outbound master.
    *
    * @return the net profit dollars of this st cff outbound master
    */
    @Override
    public double getNetProfitDollars() {
        return _stCffOutboundMaster.getNetProfitDollars();
    }

    /**
    * Sets the net profit dollars of this st cff outbound master.
    *
    * @param netProfitDollars the net profit dollars of this st cff outbound master
    */
    @Override
    public void setNetProfitDollars(double netProfitDollars) {
        _stCffOutboundMaster.setNetProfitDollars(netProfitDollars);
    }

    /**
    * Returns the gl company master sid of this st cff outbound master.
    *
    * @return the gl company master sid of this st cff outbound master
    */
    @Override
    public int getGlCompanyMasterSid() {
        return _stCffOutboundMaster.getGlCompanyMasterSid();
    }

    /**
    * Sets the gl company master sid of this st cff outbound master.
    *
    * @param glCompanyMasterSid the gl company master sid of this st cff outbound master
    */
    @Override
    public void setGlCompanyMasterSid(int glCompanyMasterSid) {
        _stCffOutboundMaster.setGlCompanyMasterSid(glCompanyMasterSid);
    }

    /**
    * Returns the created date of this st cff outbound master.
    *
    * @return the created date of this st cff outbound master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _stCffOutboundMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this st cff outbound master.
    *
    * @param createdDate the created date of this st cff outbound master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _stCffOutboundMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this st cff outbound master.
    *
    * @return the created by of this st cff outbound master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _stCffOutboundMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this st cff outbound master.
    *
    * @param createdBy the created by of this st cff outbound master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _stCffOutboundMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the deduction category1 of this st cff outbound master.
    *
    * @return the deduction category1 of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionCategory1() {
        return _stCffOutboundMaster.getDeductionCategory1();
    }

    /**
    * Sets the deduction category1 of this st cff outbound master.
    *
    * @param deductionCategory1 the deduction category1 of this st cff outbound master
    */
    @Override
    public void setDeductionCategory1(java.lang.String deductionCategory1) {
        _stCffOutboundMaster.setDeductionCategory1(deductionCategory1);
    }

    /**
    * Returns the deduction category2 of this st cff outbound master.
    *
    * @return the deduction category2 of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionCategory2() {
        return _stCffOutboundMaster.getDeductionCategory2();
    }

    /**
    * Sets the deduction category2 of this st cff outbound master.
    *
    * @param deductionCategory2 the deduction category2 of this st cff outbound master
    */
    @Override
    public void setDeductionCategory2(java.lang.String deductionCategory2) {
        _stCffOutboundMaster.setDeductionCategory2(deductionCategory2);
    }

    /**
    * Returns the contract holder no of this st cff outbound master.
    *
    * @return the contract holder no of this st cff outbound master
    */
    @Override
    public java.lang.String getContractHolderNo() {
        return _stCffOutboundMaster.getContractHolderNo();
    }

    /**
    * Sets the contract holder no of this st cff outbound master.
    *
    * @param contractHolderNo the contract holder no of this st cff outbound master
    */
    @Override
    public void setContractHolderNo(java.lang.String contractHolderNo) {
        _stCffOutboundMaster.setContractHolderNo(contractHolderNo);
    }

    /**
    * Returns the deduction category3 of this st cff outbound master.
    *
    * @return the deduction category3 of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionCategory3() {
        return _stCffOutboundMaster.getDeductionCategory3();
    }

    /**
    * Sets the deduction category3 of this st cff outbound master.
    *
    * @param deductionCategory3 the deduction category3 of this st cff outbound master
    */
    @Override
    public void setDeductionCategory3(java.lang.String deductionCategory3) {
        _stCffOutboundMaster.setDeductionCategory3(deductionCategory3);
    }

    /**
    * Returns the item no of this st cff outbound master.
    *
    * @return the item no of this st cff outbound master
    */
    @Override
    public java.lang.String getItemNo() {
        return _stCffOutboundMaster.getItemNo();
    }

    /**
    * Sets the item no of this st cff outbound master.
    *
    * @param itemNo the item no of this st cff outbound master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _stCffOutboundMaster.setItemNo(itemNo);
    }

    /**
    * Returns the deduction category4 of this st cff outbound master.
    *
    * @return the deduction category4 of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionCategory4() {
        return _stCffOutboundMaster.getDeductionCategory4();
    }

    /**
    * Sets the deduction category4 of this st cff outbound master.
    *
    * @param deductionCategory4 the deduction category4 of this st cff outbound master
    */
    @Override
    public void setDeductionCategory4(java.lang.String deductionCategory4) {
        _stCffOutboundMaster.setDeductionCategory4(deductionCategory4);
    }

    /**
    * Returns the deduction category5 of this st cff outbound master.
    *
    * @return the deduction category5 of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionCategory5() {
        return _stCffOutboundMaster.getDeductionCategory5();
    }

    /**
    * Sets the deduction category5 of this st cff outbound master.
    *
    * @param deductionCategory5 the deduction category5 of this st cff outbound master
    */
    @Override
    public void setDeductionCategory5(java.lang.String deductionCategory5) {
        _stCffOutboundMaster.setDeductionCategory5(deductionCategory5);
    }

    /**
    * Returns the deduction category6 of this st cff outbound master.
    *
    * @return the deduction category6 of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionCategory6() {
        return _stCffOutboundMaster.getDeductionCategory6();
    }

    /**
    * Sets the deduction category6 of this st cff outbound master.
    *
    * @param deductionCategory6 the deduction category6 of this st cff outbound master
    */
    @Override
    public void setDeductionCategory6(java.lang.String deductionCategory6) {
        _stCffOutboundMaster.setDeductionCategory6(deductionCategory6);
    }

    /**
    * Returns the contract ID of this st cff outbound master.
    *
    * @return the contract ID of this st cff outbound master
    */
    @Override
    public java.lang.String getContractId() {
        return _stCffOutboundMaster.getContractId();
    }

    /**
    * Sets the contract ID of this st cff outbound master.
    *
    * @param contractId the contract ID of this st cff outbound master
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _stCffOutboundMaster.setContractId(contractId);
    }

    /**
    * Returns the deduction program of this st cff outbound master.
    *
    * @return the deduction program of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionProgram() {
        return _stCffOutboundMaster.getDeductionProgram();
    }

    /**
    * Sets the deduction program of this st cff outbound master.
    *
    * @param deductionProgram the deduction program of this st cff outbound master
    */
    @Override
    public void setDeductionProgram(java.lang.String deductionProgram) {
        _stCffOutboundMaster.setDeductionProgram(deductionProgram);
    }

    /**
    * Returns the business unit ID of this st cff outbound master.
    *
    * @return the business unit ID of this st cff outbound master
    */
    @Override
    public java.lang.String getBusinessUnitId() {
        return _stCffOutboundMaster.getBusinessUnitId();
    }

    /**
    * Sets the business unit ID of this st cff outbound master.
    *
    * @param businessUnitId the business unit ID of this st cff outbound master
    */
    @Override
    public void setBusinessUnitId(java.lang.String businessUnitId) {
        _stCffOutboundMaster.setBusinessUnitId(businessUnitId);
    }

    /**
    * Returns the projection name of this st cff outbound master.
    *
    * @return the projection name of this st cff outbound master
    */
    @Override
    public java.lang.String getProjectionName() {
        return _stCffOutboundMaster.getProjectionName();
    }

    /**
    * Sets the projection name of this st cff outbound master.
    *
    * @param projectionName the projection name of this st cff outbound master
    */
    @Override
    public void setProjectionName(java.lang.String projectionName) {
        _stCffOutboundMaster.setProjectionName(projectionName);
    }

    /**
    * Returns the user ID of this st cff outbound master.
    *
    * @return the user ID of this st cff outbound master
    */
    @Override
    public java.lang.String getUserId() {
        return _stCffOutboundMaster.getUserId();
    }

    /**
    * Sets the user ID of this st cff outbound master.
    *
    * @param userId the user ID of this st cff outbound master
    */
    @Override
    public void setUserId(java.lang.String userId) {
        _stCffOutboundMaster.setUserId(userId);
    }

    /**
    * Returns the company ID of this st cff outbound master.
    *
    * @return the company ID of this st cff outbound master
    */
    @Override
    public java.lang.String getCompanyId() {
        return _stCffOutboundMaster.getCompanyId();
    }

    /**
    * Sets the company ID of this st cff outbound master.
    *
    * @param companyId the company ID of this st cff outbound master
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _stCffOutboundMaster.setCompanyId(companyId);
    }

    /**
    * Returns the outbound status of this st cff outbound master.
    *
    * @return the outbound status of this st cff outbound master
    */
    @Override
    public java.lang.String getOutboundStatus() {
        return _stCffOutboundMaster.getOutboundStatus();
    }

    /**
    * Sets the outbound status of this st cff outbound master.
    *
    * @param outboundStatus the outbound status of this st cff outbound master
    */
    @Override
    public void setOutboundStatus(java.lang.String outboundStatus) {
        _stCffOutboundMaster.setOutboundStatus(outboundStatus);
    }

    /**
    * Returns the original batch ID of this st cff outbound master.
    *
    * @return the original batch ID of this st cff outbound master
    */
    @Override
    public java.lang.String getOriginalBatchId() {
        return _stCffOutboundMaster.getOriginalBatchId();
    }

    /**
    * Sets the original batch ID of this st cff outbound master.
    *
    * @param originalBatchId the original batch ID of this st cff outbound master
    */
    @Override
    public void setOriginalBatchId(java.lang.String originalBatchId) {
        _stCffOutboundMaster.setOriginalBatchId(originalBatchId);
    }

    /**
    * Returns the deduction name of this st cff outbound master.
    *
    * @return the deduction name of this st cff outbound master
    */
    @Override
    public java.lang.String getDeductionName() {
        return _stCffOutboundMaster.getDeductionName();
    }

    /**
    * Sets the deduction name of this st cff outbound master.
    *
    * @param deductionName the deduction name of this st cff outbound master
    */
    @Override
    public void setDeductionName(java.lang.String deductionName) {
        _stCffOutboundMaster.setDeductionName(deductionName);
    }

    /**
    * Returns the net profit per unit of this st cff outbound master.
    *
    * @return the net profit per unit of this st cff outbound master
    */
    @Override
    public double getNetProfitPerUnit() {
        return _stCffOutboundMaster.getNetProfitPerUnit();
    }

    /**
    * Sets the net profit per unit of this st cff outbound master.
    *
    * @param netProfitPerUnit the net profit per unit of this st cff outbound master
    */
    @Override
    public void setNetProfitPerUnit(double netProfitPerUnit) {
        _stCffOutboundMaster.setNetProfitPerUnit(netProfitPerUnit);
    }

    /**
    * Returns the period sid of this st cff outbound master.
    *
    * @return the period sid of this st cff outbound master
    */
    @Override
    public int getPeriodSid() {
        return _stCffOutboundMaster.getPeriodSid();
    }

    /**
    * Sets the period sid of this st cff outbound master.
    *
    * @param periodSid the period sid of this st cff outbound master
    */
    @Override
    public void setPeriodSid(int periodSid) {
        _stCffOutboundMaster.setPeriodSid(periodSid);
    }

    /**
    * Returns the sales inclusion of this st cff outbound master.
    *
    * @return the sales inclusion of this st cff outbound master
    */
    @Override
    public java.lang.String getSalesInclusion() {
        return _stCffOutboundMaster.getSalesInclusion();
    }

    /**
    * Sets the sales inclusion of this st cff outbound master.
    *
    * @param salesInclusion the sales inclusion of this st cff outbound master
    */
    @Override
    public void setSalesInclusion(java.lang.String salesInclusion) {
        _stCffOutboundMaster.setSalesInclusion(salesInclusion);
    }

    /**
    * Returns the batch ID of this st cff outbound master.
    *
    * @return the batch ID of this st cff outbound master
    */
    @Override
    public java.lang.String getBatchId() {
        return _stCffOutboundMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this st cff outbound master.
    *
    * @param batchId the batch ID of this st cff outbound master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _stCffOutboundMaster.setBatchId(batchId);
    }

    /**
    * Returns the financial forecast name of this st cff outbound master.
    *
    * @return the financial forecast name of this st cff outbound master
    */
    @Override
    public java.lang.String getFinancialForecastName() {
        return _stCffOutboundMaster.getFinancialForecastName();
    }

    /**
    * Sets the financial forecast name of this st cff outbound master.
    *
    * @param financialForecastName the financial forecast name of this st cff outbound master
    */
    @Override
    public void setFinancialForecastName(java.lang.String financialForecastName) {
        _stCffOutboundMaster.setFinancialForecastName(financialForecastName);
    }

    /**
    * Returns the net sales dollar of this st cff outbound master.
    *
    * @return the net sales dollar of this st cff outbound master
    */
    @Override
    public double getNetSalesDollar() {
        return _stCffOutboundMaster.getNetSalesDollar();
    }

    /**
    * Sets the net sales dollar of this st cff outbound master.
    *
    * @param netSalesDollar the net sales dollar of this st cff outbound master
    */
    @Override
    public void setNetSalesDollar(double netSalesDollar) {
        _stCffOutboundMaster.setNetSalesDollar(netSalesDollar);
    }

    /**
    * Returns the deduction dollars of this st cff outbound master.
    *
    * @return the deduction dollars of this st cff outbound master
    */
    @Override
    public double getDeductionDollars() {
        return _stCffOutboundMaster.getDeductionDollars();
    }

    /**
    * Sets the deduction dollars of this st cff outbound master.
    *
    * @param deductionDollars the deduction dollars of this st cff outbound master
    */
    @Override
    public void setDeductionDollars(double deductionDollars) {
        _stCffOutboundMaster.setDeductionDollars(deductionDollars);
    }

    /**
    * Returns the contract no of this st cff outbound master.
    *
    * @return the contract no of this st cff outbound master
    */
    @Override
    public java.lang.String getContractNo() {
        return _stCffOutboundMaster.getContractNo();
    }

    /**
    * Sets the contract no of this st cff outbound master.
    *
    * @param contractNo the contract no of this st cff outbound master
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _stCffOutboundMaster.setContractNo(contractNo);
    }

    @Override
    public boolean isNew() {
        return _stCffOutboundMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stCffOutboundMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stCffOutboundMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stCffOutboundMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stCffOutboundMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stCffOutboundMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stCffOutboundMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stCffOutboundMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stCffOutboundMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stCffOutboundMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stCffOutboundMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StCffOutboundMasterWrapper((StCffOutboundMaster) _stCffOutboundMaster.clone());
    }

    @Override
    public int compareTo(StCffOutboundMaster stCffOutboundMaster) {
        return _stCffOutboundMaster.compareTo(stCffOutboundMaster);
    }

    @Override
    public int hashCode() {
        return _stCffOutboundMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StCffOutboundMaster> toCacheModel() {
        return _stCffOutboundMaster.toCacheModel();
    }

    @Override
    public StCffOutboundMaster toEscapedModel() {
        return new StCffOutboundMasterWrapper(_stCffOutboundMaster.toEscapedModel());
    }

    @Override
    public StCffOutboundMaster toUnescapedModel() {
        return new StCffOutboundMasterWrapper(_stCffOutboundMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stCffOutboundMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stCffOutboundMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stCffOutboundMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StCffOutboundMasterWrapper)) {
            return false;
        }

        StCffOutboundMasterWrapper stCffOutboundMasterWrapper = (StCffOutboundMasterWrapper) obj;

        if (Validator.equals(_stCffOutboundMaster,
                    stCffOutboundMasterWrapper._stCffOutboundMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StCffOutboundMaster getWrappedStCffOutboundMaster() {
        return _stCffOutboundMaster;
    }

    @Override
    public StCffOutboundMaster getWrappedModel() {
        return _stCffOutboundMaster;
    }

    @Override
    public void resetOriginalValues() {
        _stCffOutboundMaster.resetOriginalValues();
    }
}
