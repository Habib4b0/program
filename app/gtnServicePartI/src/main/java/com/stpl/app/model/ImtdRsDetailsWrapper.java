package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImtdRsDetails}.
 * </p>
 *
 * @author
 * @see ImtdRsDetails
 * @generated
 */
public class ImtdRsDetailsWrapper implements ImtdRsDetails,
    ModelWrapper<ImtdRsDetails> {
    private ImtdRsDetails _imtdRsDetails;

    public ImtdRsDetailsWrapper(ImtdRsDetails imtdRsDetails) {
        _imtdRsDetails = imtdRsDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdRsDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdRsDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("rsDetailsModifiedDate", getRsDetailsModifiedDate());
        attributes.put("rsDetailsBundleNo", getRsDetailsBundleNo());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("imtdRsDetailsSid", getImtdRsDetailsSid());
        attributes.put("itemId", getItemId());
        attributes.put("rsDetailsFormulaMethodId", getRsDetailsFormulaMethodId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("rsDetailsFormulaId", getRsDetailsFormulaId());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsDetailsCreatedDate", getRsDetailsCreatedDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("rsDetailsFormulaName", getRsDetailsFormulaName());
        attributes.put("udc6", getUdc6());
        attributes.put("rsDetailsCreatedBy", getRsDetailsCreatedBy());
        attributes.put("udc5", getUdc5());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("udc4", getUdc4());
        attributes.put("rsDetailsFormulaNo", getRsDetailsFormulaNo());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("rsId", getRsId());
        attributes.put("udc1", getUdc1());
        attributes.put("rsDetailsRebateAmount", getRsDetailsRebateAmount());
        attributes.put("udc2", getUdc2());
        attributes.put("rsDetailsModifiedBy", getRsDetailsModifiedBy());
        attributes.put("udc3", getUdc3());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("rsDetailsAttachedDate", getRsDetailsAttachedDate());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("rsDetailsRebatePlanName", getRsDetailsRebatePlanName());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("rsDetailsFormulaType", getRsDetailsFormulaType());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("operation", getOperation());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("rsDetailsSid", getRsDetailsSid());
        attributes.put("rsDetailsAttachedStatus", getRsDetailsAttachedStatus());
        attributes.put("rsDetailsNetSalesFormulaNo",
            getRsDetailsNetSalesFormulaNo());
        attributes.put("rsDetailsNetSalesFormulaName",
            getRsDetailsNetSalesFormulaName());
        attributes.put("rsDetailsDeductionCalendarNo",
            getRsDetailsDeductionCalendarNo());
        attributes.put("rsDetailsDeductionCalendarName",
            getRsDetailsDeductionCalendarName());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("evaluationRule", getEvaluationRule());
        attributes.put("netSalesRule", getNetSalesRule());
        attributes.put("formulaType", getFormulaType());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleBundle", getCalculationRuleBundle());
        attributes.put("evaluationRuleBundle", getEvaluationRuleBundle());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date rsDetailsModifiedDate = (Date) attributes.get(
                "rsDetailsModifiedDate");

        if (rsDetailsModifiedDate != null) {
            setRsDetailsModifiedDate(rsDetailsModifiedDate);
        }

        String rsDetailsBundleNo = (String) attributes.get("rsDetailsBundleNo");

        if (rsDetailsBundleNo != null) {
            setRsDetailsBundleNo(rsDetailsBundleNo);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer imtdRsDetailsSid = (Integer) attributes.get("imtdRsDetailsSid");

        if (imtdRsDetailsSid != null) {
            setImtdRsDetailsSid(imtdRsDetailsSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String rsDetailsFormulaMethodId = (String) attributes.get(
                "rsDetailsFormulaMethodId");

        if (rsDetailsFormulaMethodId != null) {
            setRsDetailsFormulaMethodId(rsDetailsFormulaMethodId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String usersSid = (String) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String rsDetailsFormulaId = (String) attributes.get(
                "rsDetailsFormulaId");

        if (rsDetailsFormulaId != null) {
            setRsDetailsFormulaId(rsDetailsFormulaId);
        }

        Date imtdCreatedDate = (Date) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date rsDetailsCreatedDate = (Date) attributes.get(
                "rsDetailsCreatedDate");

        if (rsDetailsCreatedDate != null) {
            setRsDetailsCreatedDate(rsDetailsCreatedDate);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String rsDetailsFormulaName = (String) attributes.get(
                "rsDetailsFormulaName");

        if (rsDetailsFormulaName != null) {
            setRsDetailsFormulaName(rsDetailsFormulaName);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String rsDetailsCreatedBy = (String) attributes.get(
                "rsDetailsCreatedBy");

        if (rsDetailsCreatedBy != null) {
            setRsDetailsCreatedBy(rsDetailsCreatedBy);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String rsDetailsFormulaNo = (String) attributes.get(
                "rsDetailsFormulaNo");

        if (rsDetailsFormulaNo != null) {
            setRsDetailsFormulaNo(rsDetailsFormulaNo);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String rsId = (String) attributes.get("rsId");

        if (rsId != null) {
            setRsId(rsId);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        Double rsDetailsRebateAmount = (Double) attributes.get(
                "rsDetailsRebateAmount");

        if (rsDetailsRebateAmount != null) {
            setRsDetailsRebateAmount(rsDetailsRebateAmount);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String rsDetailsModifiedBy = (String) attributes.get(
                "rsDetailsModifiedBy");

        if (rsDetailsModifiedBy != null) {
            setRsDetailsModifiedBy(rsDetailsModifiedBy);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String rebatePlanMasterSid = (String) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Date rsDetailsAttachedDate = (Date) attributes.get(
                "rsDetailsAttachedDate");

        if (rsDetailsAttachedDate != null) {
            setRsDetailsAttachedDate(rsDetailsAttachedDate);
        }

        Date itemRebateEndDate = (Date) attributes.get("itemRebateEndDate");

        if (itemRebateEndDate != null) {
            setItemRebateEndDate(itemRebateEndDate);
        }

        String rsDetailsRebatePlanName = (String) attributes.get(
                "rsDetailsRebatePlanName");

        if (rsDetailsRebatePlanName != null) {
            setRsDetailsRebatePlanName(rsDetailsRebatePlanName);
        }

        Date itemRebateStartDate = (Date) attributes.get("itemRebateStartDate");

        if (itemRebateStartDate != null) {
            setItemRebateStartDate(itemRebateStartDate);
        }

        String rsDetailsFormulaType = (String) attributes.get(
                "rsDetailsFormulaType");

        if (rsDetailsFormulaType != null) {
            setRsDetailsFormulaType(rsDetailsFormulaType);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer rsDetailsSid = (Integer) attributes.get("rsDetailsSid");

        if (rsDetailsSid != null) {
            setRsDetailsSid(rsDetailsSid);
        }

        Integer rsDetailsAttachedStatus = (Integer) attributes.get(
                "rsDetailsAttachedStatus");

        if (rsDetailsAttachedStatus != null) {
            setRsDetailsAttachedStatus(rsDetailsAttachedStatus);
        }

        String rsDetailsNetSalesFormulaNo = (String) attributes.get(
                "rsDetailsNetSalesFormulaNo");

        if (rsDetailsNetSalesFormulaNo != null) {
            setRsDetailsNetSalesFormulaNo(rsDetailsNetSalesFormulaNo);
        }

        String rsDetailsNetSalesFormulaName = (String) attributes.get(
                "rsDetailsNetSalesFormulaName");

        if (rsDetailsNetSalesFormulaName != null) {
            setRsDetailsNetSalesFormulaName(rsDetailsNetSalesFormulaName);
        }

        String rsDetailsDeductionCalendarNo = (String) attributes.get(
                "rsDetailsDeductionCalendarNo");

        if (rsDetailsDeductionCalendarNo != null) {
            setRsDetailsDeductionCalendarNo(rsDetailsDeductionCalendarNo);
        }

        String rsDetailsDeductionCalendarName = (String) attributes.get(
                "rsDetailsDeductionCalendarName");

        if (rsDetailsDeductionCalendarName != null) {
            setRsDetailsDeductionCalendarName(rsDetailsDeductionCalendarName);
        }

        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        String evaluationRule = (String) attributes.get("evaluationRule");

        if (evaluationRule != null) {
            setEvaluationRule(evaluationRule);
        }

        String netSalesRule = (String) attributes.get("netSalesRule");

        if (netSalesRule != null) {
            setNetSalesRule(netSalesRule);
        }

        String formulaType = (String) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        String calculationRule = (String) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleBundle = (String) attributes.get(
                "calculationRuleBundle");

        if (calculationRuleBundle != null) {
            setCalculationRuleBundle(calculationRuleBundle);
        }

        String evaluationRuleBundle = (String) attributes.get(
                "evaluationRuleBundle");

        if (evaluationRuleBundle != null) {
            setEvaluationRuleBundle(evaluationRuleBundle);
        }
    }

    /**
    * Returns the primary key of this imtd rs details.
    *
    * @return the primary key of this imtd rs details
    */
    @Override
    public int getPrimaryKey() {
        return _imtdRsDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this imtd rs details.
    *
    * @param primaryKey the primary key of this imtd rs details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _imtdRsDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the rs details modified date of this imtd rs details.
    *
    * @return the rs details modified date of this imtd rs details
    */
    @Override
    public java.util.Date getRsDetailsModifiedDate() {
        return _imtdRsDetails.getRsDetailsModifiedDate();
    }

    /**
    * Sets the rs details modified date of this imtd rs details.
    *
    * @param rsDetailsModifiedDate the rs details modified date of this imtd rs details
    */
    @Override
    public void setRsDetailsModifiedDate(java.util.Date rsDetailsModifiedDate) {
        _imtdRsDetails.setRsDetailsModifiedDate(rsDetailsModifiedDate);
    }

    /**
    * Returns the rs details bundle no of this imtd rs details.
    *
    * @return the rs details bundle no of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsBundleNo() {
        return _imtdRsDetails.getRsDetailsBundleNo();
    }

    /**
    * Sets the rs details bundle no of this imtd rs details.
    *
    * @param rsDetailsBundleNo the rs details bundle no of this imtd rs details
    */
    @Override
    public void setRsDetailsBundleNo(java.lang.String rsDetailsBundleNo) {
        _imtdRsDetails.setRsDetailsBundleNo(rsDetailsBundleNo);
    }

    /**
    * Returns the item master sid of this imtd rs details.
    *
    * @return the item master sid of this imtd rs details
    */
    @Override
    public int getItemMasterSid() {
        return _imtdRsDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this imtd rs details.
    *
    * @param itemMasterSid the item master sid of this imtd rs details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _imtdRsDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the imtd rs details sid of this imtd rs details.
    *
    * @return the imtd rs details sid of this imtd rs details
    */
    @Override
    public int getImtdRsDetailsSid() {
        return _imtdRsDetails.getImtdRsDetailsSid();
    }

    /**
    * Sets the imtd rs details sid of this imtd rs details.
    *
    * @param imtdRsDetailsSid the imtd rs details sid of this imtd rs details
    */
    @Override
    public void setImtdRsDetailsSid(int imtdRsDetailsSid) {
        _imtdRsDetails.setImtdRsDetailsSid(imtdRsDetailsSid);
    }

    /**
    * Returns the item ID of this imtd rs details.
    *
    * @return the item ID of this imtd rs details
    */
    @Override
    public java.lang.String getItemId() {
        return _imtdRsDetails.getItemId();
    }

    /**
    * Sets the item ID of this imtd rs details.
    *
    * @param itemId the item ID of this imtd rs details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _imtdRsDetails.setItemId(itemId);
    }

    /**
    * Returns the rs details formula method ID of this imtd rs details.
    *
    * @return the rs details formula method ID of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsFormulaMethodId() {
        return _imtdRsDetails.getRsDetailsFormulaMethodId();
    }

    /**
    * Sets the rs details formula method ID of this imtd rs details.
    *
    * @param rsDetailsFormulaMethodId the rs details formula method ID of this imtd rs details
    */
    @Override
    public void setRsDetailsFormulaMethodId(
        java.lang.String rsDetailsFormulaMethodId) {
        _imtdRsDetails.setRsDetailsFormulaMethodId(rsDetailsFormulaMethodId);
    }

    /**
    * Returns the modified date of this imtd rs details.
    *
    * @return the modified date of this imtd rs details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _imtdRsDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this imtd rs details.
    *
    * @param modifiedDate the modified date of this imtd rs details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _imtdRsDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the created date of this imtd rs details.
    *
    * @return the created date of this imtd rs details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _imtdRsDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this imtd rs details.
    *
    * @param createdDate the created date of this imtd rs details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _imtdRsDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this imtd rs details.
    *
    * @return the created by of this imtd rs details
    */
    @Override
    public int getCreatedBy() {
        return _imtdRsDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this imtd rs details.
    *
    * @param createdBy the created by of this imtd rs details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _imtdRsDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the users sid of this imtd rs details.
    *
    * @return the users sid of this imtd rs details
    */
    @Override
    public java.lang.String getUsersSid() {
        return _imtdRsDetails.getUsersSid();
    }

    /**
    * Sets the users sid of this imtd rs details.
    *
    * @param usersSid the users sid of this imtd rs details
    */
    @Override
    public void setUsersSid(java.lang.String usersSid) {
        _imtdRsDetails.setUsersSid(usersSid);
    }

    /**
    * Returns the contract master sid of this imtd rs details.
    *
    * @return the contract master sid of this imtd rs details
    */
    @Override
    public int getContractMasterSid() {
        return _imtdRsDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this imtd rs details.
    *
    * @param contractMasterSid the contract master sid of this imtd rs details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _imtdRsDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the rs details formula ID of this imtd rs details.
    *
    * @return the rs details formula ID of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsFormulaId() {
        return _imtdRsDetails.getRsDetailsFormulaId();
    }

    /**
    * Sets the rs details formula ID of this imtd rs details.
    *
    * @param rsDetailsFormulaId the rs details formula ID of this imtd rs details
    */
    @Override
    public void setRsDetailsFormulaId(java.lang.String rsDetailsFormulaId) {
        _imtdRsDetails.setRsDetailsFormulaId(rsDetailsFormulaId);
    }

    /**
    * Returns the imtd created date of this imtd rs details.
    *
    * @return the imtd created date of this imtd rs details
    */
    @Override
    public java.util.Date getImtdCreatedDate() {
        return _imtdRsDetails.getImtdCreatedDate();
    }

    /**
    * Sets the imtd created date of this imtd rs details.
    *
    * @param imtdCreatedDate the imtd created date of this imtd rs details
    */
    @Override
    public void setImtdCreatedDate(java.util.Date imtdCreatedDate) {
        _imtdRsDetails.setImtdCreatedDate(imtdCreatedDate);
    }

    /**
    * Returns the ps model sid of this imtd rs details.
    *
    * @return the ps model sid of this imtd rs details
    */
    @Override
    public int getPsModelSid() {
        return _imtdRsDetails.getPsModelSid();
    }

    /**
    * Sets the ps model sid of this imtd rs details.
    *
    * @param psModelSid the ps model sid of this imtd rs details
    */
    @Override
    public void setPsModelSid(int psModelSid) {
        _imtdRsDetails.setPsModelSid(psModelSid);
    }

    /**
    * Returns the modified by of this imtd rs details.
    *
    * @return the modified by of this imtd rs details
    */
    @Override
    public int getModifiedBy() {
        return _imtdRsDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this imtd rs details.
    *
    * @param modifiedBy the modified by of this imtd rs details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _imtdRsDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the rs details created date of this imtd rs details.
    *
    * @return the rs details created date of this imtd rs details
    */
    @Override
    public java.util.Date getRsDetailsCreatedDate() {
        return _imtdRsDetails.getRsDetailsCreatedDate();
    }

    /**
    * Sets the rs details created date of this imtd rs details.
    *
    * @param rsDetailsCreatedDate the rs details created date of this imtd rs details
    */
    @Override
    public void setRsDetailsCreatedDate(java.util.Date rsDetailsCreatedDate) {
        _imtdRsDetails.setRsDetailsCreatedDate(rsDetailsCreatedDate);
    }

    /**
    * Returns the item no of this imtd rs details.
    *
    * @return the item no of this imtd rs details
    */
    @Override
    public java.lang.String getItemNo() {
        return _imtdRsDetails.getItemNo();
    }

    /**
    * Sets the item no of this imtd rs details.
    *
    * @param itemNo the item no of this imtd rs details
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _imtdRsDetails.setItemNo(itemNo);
    }

    /**
    * Returns the rs details formula name of this imtd rs details.
    *
    * @return the rs details formula name of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsFormulaName() {
        return _imtdRsDetails.getRsDetailsFormulaName();
    }

    /**
    * Sets the rs details formula name of this imtd rs details.
    *
    * @param rsDetailsFormulaName the rs details formula name of this imtd rs details
    */
    @Override
    public void setRsDetailsFormulaName(java.lang.String rsDetailsFormulaName) {
        _imtdRsDetails.setRsDetailsFormulaName(rsDetailsFormulaName);
    }

    /**
    * Returns the udc6 of this imtd rs details.
    *
    * @return the udc6 of this imtd rs details
    */
    @Override
    public java.lang.String getUdc6() {
        return _imtdRsDetails.getUdc6();
    }

    /**
    * Sets the udc6 of this imtd rs details.
    *
    * @param udc6 the udc6 of this imtd rs details
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _imtdRsDetails.setUdc6(udc6);
    }

    /**
    * Returns the rs details created by of this imtd rs details.
    *
    * @return the rs details created by of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsCreatedBy() {
        return _imtdRsDetails.getRsDetailsCreatedBy();
    }

    /**
    * Sets the rs details created by of this imtd rs details.
    *
    * @param rsDetailsCreatedBy the rs details created by of this imtd rs details
    */
    @Override
    public void setRsDetailsCreatedBy(java.lang.String rsDetailsCreatedBy) {
        _imtdRsDetails.setRsDetailsCreatedBy(rsDetailsCreatedBy);
    }

    /**
    * Returns the udc5 of this imtd rs details.
    *
    * @return the udc5 of this imtd rs details
    */
    @Override
    public java.lang.String getUdc5() {
        return _imtdRsDetails.getUdc5();
    }

    /**
    * Sets the udc5 of this imtd rs details.
    *
    * @param udc5 the udc5 of this imtd rs details
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _imtdRsDetails.setUdc5(udc5);
    }

    /**
    * Returns the ifp model sid of this imtd rs details.
    *
    * @return the ifp model sid of this imtd rs details
    */
    @Override
    public int getIfpModelSid() {
        return _imtdRsDetails.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this imtd rs details.
    *
    * @param ifpModelSid the ifp model sid of this imtd rs details
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _imtdRsDetails.setIfpModelSid(ifpModelSid);
    }

    /**
    * Returns the udc4 of this imtd rs details.
    *
    * @return the udc4 of this imtd rs details
    */
    @Override
    public java.lang.String getUdc4() {
        return _imtdRsDetails.getUdc4();
    }

    /**
    * Sets the udc4 of this imtd rs details.
    *
    * @param udc4 the udc4 of this imtd rs details
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _imtdRsDetails.setUdc4(udc4);
    }

    /**
    * Returns the rs details formula no of this imtd rs details.
    *
    * @return the rs details formula no of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsFormulaNo() {
        return _imtdRsDetails.getRsDetailsFormulaNo();
    }

    /**
    * Sets the rs details formula no of this imtd rs details.
    *
    * @param rsDetailsFormulaNo the rs details formula no of this imtd rs details
    */
    @Override
    public void setRsDetailsFormulaNo(java.lang.String rsDetailsFormulaNo) {
        _imtdRsDetails.setRsDetailsFormulaNo(rsDetailsFormulaNo);
    }

    /**
    * Returns the check record of this imtd rs details.
    *
    * @return the check record of this imtd rs details
    */
    @Override
    public boolean getCheckRecord() {
        return _imtdRsDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this imtd rs details is check record.
    *
    * @return <code>true</code> if this imtd rs details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _imtdRsDetails.isCheckRecord();
    }

    /**
    * Sets whether this imtd rs details is check record.
    *
    * @param checkRecord the check record of this imtd rs details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _imtdRsDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the rs ID of this imtd rs details.
    *
    * @return the rs ID of this imtd rs details
    */
    @Override
    public java.lang.String getRsId() {
        return _imtdRsDetails.getRsId();
    }

    /**
    * Sets the rs ID of this imtd rs details.
    *
    * @param rsId the rs ID of this imtd rs details
    */
    @Override
    public void setRsId(java.lang.String rsId) {
        _imtdRsDetails.setRsId(rsId);
    }

    /**
    * Returns the udc1 of this imtd rs details.
    *
    * @return the udc1 of this imtd rs details
    */
    @Override
    public java.lang.String getUdc1() {
        return _imtdRsDetails.getUdc1();
    }

    /**
    * Sets the udc1 of this imtd rs details.
    *
    * @param udc1 the udc1 of this imtd rs details
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _imtdRsDetails.setUdc1(udc1);
    }

    /**
    * Returns the rs details rebate amount of this imtd rs details.
    *
    * @return the rs details rebate amount of this imtd rs details
    */
    @Override
    public double getRsDetailsRebateAmount() {
        return _imtdRsDetails.getRsDetailsRebateAmount();
    }

    /**
    * Sets the rs details rebate amount of this imtd rs details.
    *
    * @param rsDetailsRebateAmount the rs details rebate amount of this imtd rs details
    */
    @Override
    public void setRsDetailsRebateAmount(double rsDetailsRebateAmount) {
        _imtdRsDetails.setRsDetailsRebateAmount(rsDetailsRebateAmount);
    }

    /**
    * Returns the udc2 of this imtd rs details.
    *
    * @return the udc2 of this imtd rs details
    */
    @Override
    public java.lang.String getUdc2() {
        return _imtdRsDetails.getUdc2();
    }

    /**
    * Sets the udc2 of this imtd rs details.
    *
    * @param udc2 the udc2 of this imtd rs details
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _imtdRsDetails.setUdc2(udc2);
    }

    /**
    * Returns the rs details modified by of this imtd rs details.
    *
    * @return the rs details modified by of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsModifiedBy() {
        return _imtdRsDetails.getRsDetailsModifiedBy();
    }

    /**
    * Sets the rs details modified by of this imtd rs details.
    *
    * @param rsDetailsModifiedBy the rs details modified by of this imtd rs details
    */
    @Override
    public void setRsDetailsModifiedBy(java.lang.String rsDetailsModifiedBy) {
        _imtdRsDetails.setRsDetailsModifiedBy(rsDetailsModifiedBy);
    }

    /**
    * Returns the udc3 of this imtd rs details.
    *
    * @return the udc3 of this imtd rs details
    */
    @Override
    public java.lang.String getUdc3() {
        return _imtdRsDetails.getUdc3();
    }

    /**
    * Sets the udc3 of this imtd rs details.
    *
    * @param udc3 the udc3 of this imtd rs details
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _imtdRsDetails.setUdc3(udc3);
    }

    /**
    * Returns the rebate plan master sid of this imtd rs details.
    *
    * @return the rebate plan master sid of this imtd rs details
    */
    @Override
    public java.lang.String getRebatePlanMasterSid() {
        return _imtdRsDetails.getRebatePlanMasterSid();
    }

    /**
    * Sets the rebate plan master sid of this imtd rs details.
    *
    * @param rebatePlanMasterSid the rebate plan master sid of this imtd rs details
    */
    @Override
    public void setRebatePlanMasterSid(java.lang.String rebatePlanMasterSid) {
        _imtdRsDetails.setRebatePlanMasterSid(rebatePlanMasterSid);
    }

    /**
    * Returns the rs details attached date of this imtd rs details.
    *
    * @return the rs details attached date of this imtd rs details
    */
    @Override
    public java.util.Date getRsDetailsAttachedDate() {
        return _imtdRsDetails.getRsDetailsAttachedDate();
    }

    /**
    * Sets the rs details attached date of this imtd rs details.
    *
    * @param rsDetailsAttachedDate the rs details attached date of this imtd rs details
    */
    @Override
    public void setRsDetailsAttachedDate(java.util.Date rsDetailsAttachedDate) {
        _imtdRsDetails.setRsDetailsAttachedDate(rsDetailsAttachedDate);
    }

    /**
    * Returns the item rebate end date of this imtd rs details.
    *
    * @return the item rebate end date of this imtd rs details
    */
    @Override
    public java.util.Date getItemRebateEndDate() {
        return _imtdRsDetails.getItemRebateEndDate();
    }

    /**
    * Sets the item rebate end date of this imtd rs details.
    *
    * @param itemRebateEndDate the item rebate end date of this imtd rs details
    */
    @Override
    public void setItemRebateEndDate(java.util.Date itemRebateEndDate) {
        _imtdRsDetails.setItemRebateEndDate(itemRebateEndDate);
    }

    /**
    * Returns the rs details rebate plan name of this imtd rs details.
    *
    * @return the rs details rebate plan name of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsRebatePlanName() {
        return _imtdRsDetails.getRsDetailsRebatePlanName();
    }

    /**
    * Sets the rs details rebate plan name of this imtd rs details.
    *
    * @param rsDetailsRebatePlanName the rs details rebate plan name of this imtd rs details
    */
    @Override
    public void setRsDetailsRebatePlanName(
        java.lang.String rsDetailsRebatePlanName) {
        _imtdRsDetails.setRsDetailsRebatePlanName(rsDetailsRebatePlanName);
    }

    /**
    * Returns the item rebate start date of this imtd rs details.
    *
    * @return the item rebate start date of this imtd rs details
    */
    @Override
    public java.util.Date getItemRebateStartDate() {
        return _imtdRsDetails.getItemRebateStartDate();
    }

    /**
    * Sets the item rebate start date of this imtd rs details.
    *
    * @param itemRebateStartDate the item rebate start date of this imtd rs details
    */
    @Override
    public void setItemRebateStartDate(java.util.Date itemRebateStartDate) {
        _imtdRsDetails.setItemRebateStartDate(itemRebateStartDate);
    }

    /**
    * Returns the rs details formula type of this imtd rs details.
    *
    * @return the rs details formula type of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsFormulaType() {
        return _imtdRsDetails.getRsDetailsFormulaType();
    }

    /**
    * Sets the rs details formula type of this imtd rs details.
    *
    * @param rsDetailsFormulaType the rs details formula type of this imtd rs details
    */
    @Override
    public void setRsDetailsFormulaType(java.lang.String rsDetailsFormulaType) {
        _imtdRsDetails.setRsDetailsFormulaType(rsDetailsFormulaType);
    }

    /**
    * Returns the session ID of this imtd rs details.
    *
    * @return the session ID of this imtd rs details
    */
    @Override
    public java.lang.String getSessionId() {
        return _imtdRsDetails.getSessionId();
    }

    /**
    * Sets the session ID of this imtd rs details.
    *
    * @param sessionId the session ID of this imtd rs details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _imtdRsDetails.setSessionId(sessionId);
    }

    /**
    * Returns the item name of this imtd rs details.
    *
    * @return the item name of this imtd rs details
    */
    @Override
    public java.lang.String getItemName() {
        return _imtdRsDetails.getItemName();
    }

    /**
    * Sets the item name of this imtd rs details.
    *
    * @param itemName the item name of this imtd rs details
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _imtdRsDetails.setItemName(itemName);
    }

    /**
    * Returns the operation of this imtd rs details.
    *
    * @return the operation of this imtd rs details
    */
    @Override
    public java.lang.String getOperation() {
        return _imtdRsDetails.getOperation();
    }

    /**
    * Sets the operation of this imtd rs details.
    *
    * @param operation the operation of this imtd rs details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _imtdRsDetails.setOperation(operation);
    }

    /**
    * Returns the cfp model sid of this imtd rs details.
    *
    * @return the cfp model sid of this imtd rs details
    */
    @Override
    public int getCfpModelSid() {
        return _imtdRsDetails.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this imtd rs details.
    *
    * @param cfpModelSid the cfp model sid of this imtd rs details
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _imtdRsDetails.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the rs model sid of this imtd rs details.
    *
    * @return the rs model sid of this imtd rs details
    */
    @Override
    public int getRsModelSid() {
        return _imtdRsDetails.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this imtd rs details.
    *
    * @param rsModelSid the rs model sid of this imtd rs details
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _imtdRsDetails.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the rs details sid of this imtd rs details.
    *
    * @return the rs details sid of this imtd rs details
    */
    @Override
    public int getRsDetailsSid() {
        return _imtdRsDetails.getRsDetailsSid();
    }

    /**
    * Sets the rs details sid of this imtd rs details.
    *
    * @param rsDetailsSid the rs details sid of this imtd rs details
    */
    @Override
    public void setRsDetailsSid(int rsDetailsSid) {
        _imtdRsDetails.setRsDetailsSid(rsDetailsSid);
    }

    /**
    * Returns the rs details attached status of this imtd rs details.
    *
    * @return the rs details attached status of this imtd rs details
    */
    @Override
    public int getRsDetailsAttachedStatus() {
        return _imtdRsDetails.getRsDetailsAttachedStatus();
    }

    /**
    * Sets the rs details attached status of this imtd rs details.
    *
    * @param rsDetailsAttachedStatus the rs details attached status of this imtd rs details
    */
    @Override
    public void setRsDetailsAttachedStatus(int rsDetailsAttachedStatus) {
        _imtdRsDetails.setRsDetailsAttachedStatus(rsDetailsAttachedStatus);
    }

    /**
    * Returns the rs details net sales formula no of this imtd rs details.
    *
    * @return the rs details net sales formula no of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsNetSalesFormulaNo() {
        return _imtdRsDetails.getRsDetailsNetSalesFormulaNo();
    }

    /**
    * Sets the rs details net sales formula no of this imtd rs details.
    *
    * @param rsDetailsNetSalesFormulaNo the rs details net sales formula no of this imtd rs details
    */
    @Override
    public void setRsDetailsNetSalesFormulaNo(
        java.lang.String rsDetailsNetSalesFormulaNo) {
        _imtdRsDetails.setRsDetailsNetSalesFormulaNo(rsDetailsNetSalesFormulaNo);
    }

    /**
    * Returns the rs details net sales formula name of this imtd rs details.
    *
    * @return the rs details net sales formula name of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsNetSalesFormulaName() {
        return _imtdRsDetails.getRsDetailsNetSalesFormulaName();
    }

    /**
    * Sets the rs details net sales formula name of this imtd rs details.
    *
    * @param rsDetailsNetSalesFormulaName the rs details net sales formula name of this imtd rs details
    */
    @Override
    public void setRsDetailsNetSalesFormulaName(
        java.lang.String rsDetailsNetSalesFormulaName) {
        _imtdRsDetails.setRsDetailsNetSalesFormulaName(rsDetailsNetSalesFormulaName);
    }

    /**
    * Returns the rs details deduction calendar no of this imtd rs details.
    *
    * @return the rs details deduction calendar no of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsDeductionCalendarNo() {
        return _imtdRsDetails.getRsDetailsDeductionCalendarNo();
    }

    /**
    * Sets the rs details deduction calendar no of this imtd rs details.
    *
    * @param rsDetailsDeductionCalendarNo the rs details deduction calendar no of this imtd rs details
    */
    @Override
    public void setRsDetailsDeductionCalendarNo(
        java.lang.String rsDetailsDeductionCalendarNo) {
        _imtdRsDetails.setRsDetailsDeductionCalendarNo(rsDetailsDeductionCalendarNo);
    }

    /**
    * Returns the rs details deduction calendar name of this imtd rs details.
    *
    * @return the rs details deduction calendar name of this imtd rs details
    */
    @Override
    public java.lang.String getRsDetailsDeductionCalendarName() {
        return _imtdRsDetails.getRsDetailsDeductionCalendarName();
    }

    /**
    * Sets the rs details deduction calendar name of this imtd rs details.
    *
    * @param rsDetailsDeductionCalendarName the rs details deduction calendar name of this imtd rs details
    */
    @Override
    public void setRsDetailsDeductionCalendarName(
        java.lang.String rsDetailsDeductionCalendarName) {
        _imtdRsDetails.setRsDetailsDeductionCalendarName(rsDetailsDeductionCalendarName);
    }

    /**
    * Returns the deduction calendar master sid of this imtd rs details.
    *
    * @return the deduction calendar master sid of this imtd rs details
    */
    @Override
    public int getDeductionCalendarMasterSid() {
        return _imtdRsDetails.getDeductionCalendarMasterSid();
    }

    /**
    * Sets the deduction calendar master sid of this imtd rs details.
    *
    * @param deductionCalendarMasterSid the deduction calendar master sid of this imtd rs details
    */
    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _imtdRsDetails.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
    }

    /**
    * Returns the net sales formula master sid of this imtd rs details.
    *
    * @return the net sales formula master sid of this imtd rs details
    */
    @Override
    public int getNetSalesFormulaMasterSid() {
        return _imtdRsDetails.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this imtd rs details.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this imtd rs details
    */
    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _imtdRsDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the evaluation rule of this imtd rs details.
    *
    * @return the evaluation rule of this imtd rs details
    */
    @Override
    public java.lang.String getEvaluationRule() {
        return _imtdRsDetails.getEvaluationRule();
    }

    /**
    * Sets the evaluation rule of this imtd rs details.
    *
    * @param evaluationRule the evaluation rule of this imtd rs details
    */
    @Override
    public void setEvaluationRule(java.lang.String evaluationRule) {
        _imtdRsDetails.setEvaluationRule(evaluationRule);
    }

    /**
    * Returns the net sales rule of this imtd rs details.
    *
    * @return the net sales rule of this imtd rs details
    */
    @Override
    public java.lang.String getNetSalesRule() {
        return _imtdRsDetails.getNetSalesRule();
    }

    /**
    * Sets the net sales rule of this imtd rs details.
    *
    * @param netSalesRule the net sales rule of this imtd rs details
    */
    @Override
    public void setNetSalesRule(java.lang.String netSalesRule) {
        _imtdRsDetails.setNetSalesRule(netSalesRule);
    }

    /**
    * Returns the formula type of this imtd rs details.
    *
    * @return the formula type of this imtd rs details
    */
    @Override
    public java.lang.String getFormulaType() {
        return _imtdRsDetails.getFormulaType();
    }

    /**
    * Sets the formula type of this imtd rs details.
    *
    * @param formulaType the formula type of this imtd rs details
    */
    @Override
    public void setFormulaType(java.lang.String formulaType) {
        _imtdRsDetails.setFormulaType(formulaType);
    }

    /**
    * Returns the calculation rule of this imtd rs details.
    *
    * @return the calculation rule of this imtd rs details
    */
    @Override
    public java.lang.String getCalculationRule() {
        return _imtdRsDetails.getCalculationRule();
    }

    /**
    * Sets the calculation rule of this imtd rs details.
    *
    * @param calculationRule the calculation rule of this imtd rs details
    */
    @Override
    public void setCalculationRule(java.lang.String calculationRule) {
        _imtdRsDetails.setCalculationRule(calculationRule);
    }

    /**
    * Returns the calculation rule bundle of this imtd rs details.
    *
    * @return the calculation rule bundle of this imtd rs details
    */
    @Override
    public java.lang.String getCalculationRuleBundle() {
        return _imtdRsDetails.getCalculationRuleBundle();
    }

    /**
    * Sets the calculation rule bundle of this imtd rs details.
    *
    * @param calculationRuleBundle the calculation rule bundle of this imtd rs details
    */
    @Override
    public void setCalculationRuleBundle(java.lang.String calculationRuleBundle) {
        _imtdRsDetails.setCalculationRuleBundle(calculationRuleBundle);
    }

    /**
    * Returns the evaluation rule bundle of this imtd rs details.
    *
    * @return the evaluation rule bundle of this imtd rs details
    */
    @Override
    public java.lang.String getEvaluationRuleBundle() {
        return _imtdRsDetails.getEvaluationRuleBundle();
    }

    /**
    * Sets the evaluation rule bundle of this imtd rs details.
    *
    * @param evaluationRuleBundle the evaluation rule bundle of this imtd rs details
    */
    @Override
    public void setEvaluationRuleBundle(java.lang.String evaluationRuleBundle) {
        _imtdRsDetails.setEvaluationRuleBundle(evaluationRuleBundle);
    }

    @Override
    public boolean isNew() {
        return _imtdRsDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _imtdRsDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _imtdRsDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _imtdRsDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _imtdRsDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _imtdRsDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _imtdRsDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _imtdRsDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _imtdRsDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _imtdRsDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _imtdRsDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImtdRsDetailsWrapper((ImtdRsDetails) _imtdRsDetails.clone());
    }

    @Override
    public int compareTo(ImtdRsDetails imtdRsDetails) {
        return _imtdRsDetails.compareTo(imtdRsDetails);
    }

    @Override
    public int hashCode() {
        return _imtdRsDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ImtdRsDetails> toCacheModel() {
        return _imtdRsDetails.toCacheModel();
    }

    @Override
    public ImtdRsDetails toEscapedModel() {
        return new ImtdRsDetailsWrapper(_imtdRsDetails.toEscapedModel());
    }

    @Override
    public ImtdRsDetails toUnescapedModel() {
        return new ImtdRsDetailsWrapper(_imtdRsDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _imtdRsDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _imtdRsDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _imtdRsDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdRsDetailsWrapper)) {
            return false;
        }

        ImtdRsDetailsWrapper imtdRsDetailsWrapper = (ImtdRsDetailsWrapper) obj;

        if (Validator.equals(_imtdRsDetails, imtdRsDetailsWrapper._imtdRsDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImtdRsDetails getWrappedImtdRsDetails() {
        return _imtdRsDetails;
    }

    @Override
    public ImtdRsDetails getWrappedModel() {
        return _imtdRsDetails;
    }

    @Override
    public void resetOriginalValues() {
        _imtdRsDetails.resetOriginalValues();
    }
}
