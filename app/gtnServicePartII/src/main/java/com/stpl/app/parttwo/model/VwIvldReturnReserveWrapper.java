package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwIvldReturnReserve}.
 * </p>
 *
 * @author
 * @see VwIvldReturnReserve
 * @generated
 */
public class VwIvldReturnReserveWrapper implements VwIvldReturnReserve,
    ModelWrapper<VwIvldReturnReserve> {
    private VwIvldReturnReserve _vwIvldReturnReserve;

    public VwIvldReturnReserveWrapper(VwIvldReturnReserve vwIvldReturnReserve) {
        _vwIvldReturnReserve = vwIvldReturnReserve;
    }

    @Override
    public Class<?> getModelClass() {
        return VwIvldReturnReserve.class;
    }

    @Override
    public String getModelClassName() {
        return VwIvldReturnReserve.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ivldReturnReserveSid", getIvldReturnReserveSid());
        attributes.put("companyName", getCompanyName());
        attributes.put("year", getYear());
        attributes.put("project", getProject());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("account", getAccount());
        attributes.put("returnReserveIntfId", getReturnReserveIntfId());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("businessUnit", getBusinessUnit());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemNo", getItemNo());
        attributes.put("month", getMonth());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("units", getUnits());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("costCenter", getCostCenter());
        attributes.put("glCompany", getGlCompany());
        attributes.put("brandId", getBrandId());
        attributes.put("future1", getFuture1());
        attributes.put("future2", getFuture2());
        attributes.put("amount", getAmount());
        attributes.put("division", getDivision());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("errorField", getErrorField());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ivldReturnReserveSid = (Integer) attributes.get(
                "ivldReturnReserveSid");

        if (ivldReturnReserveSid != null) {
            setIvldReturnReserveSid(ivldReturnReserveSid);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String project = (String) attributes.get("project");

        if (project != null) {
            setProject(project);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String account = (String) attributes.get("account");

        if (account != null) {
            setAccount(account);
        }

        String returnReserveIntfId = (String) attributes.get(
                "returnReserveIntfId");

        if (returnReserveIntfId != null) {
            setReturnReserveIntfId(returnReserveIntfId);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String businessUnit = (String) attributes.get("businessUnit");

        if (businessUnit != null) {
            setBusinessUnit(businessUnit);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String units = (String) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String glCompany = (String) attributes.get("glCompany");

        if (glCompany != null) {
            setGlCompany(glCompany);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String future1 = (String) attributes.get("future1");

        if (future1 != null) {
            setFuture1(future1);
        }

        String future2 = (String) attributes.get("future2");

        if (future2 != null) {
            setFuture2(future2);
        }

        String amount = (String) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String division = (String) attributes.get("division");

        if (division != null) {
            setDivision(division);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this vw ivld return reserve.
    *
    * @return the primary key of this vw ivld return reserve
    */
    @Override
    public int getPrimaryKey() {
        return _vwIvldReturnReserve.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw ivld return reserve.
    *
    * @param primaryKey the primary key of this vw ivld return reserve
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwIvldReturnReserve.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ivld return reserve sid of this vw ivld return reserve.
    *
    * @return the ivld return reserve sid of this vw ivld return reserve
    */
    @Override
    public int getIvldReturnReserveSid() {
        return _vwIvldReturnReserve.getIvldReturnReserveSid();
    }

    /**
    * Sets the ivld return reserve sid of this vw ivld return reserve.
    *
    * @param ivldReturnReserveSid the ivld return reserve sid of this vw ivld return reserve
    */
    @Override
    public void setIvldReturnReserveSid(int ivldReturnReserveSid) {
        _vwIvldReturnReserve.setIvldReturnReserveSid(ivldReturnReserveSid);
    }

    /**
    * Returns the company name of this vw ivld return reserve.
    *
    * @return the company name of this vw ivld return reserve
    */
    @Override
    public java.lang.String getCompanyName() {
        return _vwIvldReturnReserve.getCompanyName();
    }

    /**
    * Sets the company name of this vw ivld return reserve.
    *
    * @param companyName the company name of this vw ivld return reserve
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _vwIvldReturnReserve.setCompanyName(companyName);
    }

    /**
    * Returns the year of this vw ivld return reserve.
    *
    * @return the year of this vw ivld return reserve
    */
    @Override
    public java.lang.String getYear() {
        return _vwIvldReturnReserve.getYear();
    }

    /**
    * Sets the year of this vw ivld return reserve.
    *
    * @param year the year of this vw ivld return reserve
    */
    @Override
    public void setYear(java.lang.String year) {
        _vwIvldReturnReserve.setYear(year);
    }

    /**
    * Returns the project of this vw ivld return reserve.
    *
    * @return the project of this vw ivld return reserve
    */
    @Override
    public java.lang.String getProject() {
        return _vwIvldReturnReserve.getProject();
    }

    /**
    * Sets the project of this vw ivld return reserve.
    *
    * @param project the project of this vw ivld return reserve
    */
    @Override
    public void setProject(java.lang.String project) {
        _vwIvldReturnReserve.setProject(project);
    }

    /**
    * Returns the item ID of this vw ivld return reserve.
    *
    * @return the item ID of this vw ivld return reserve
    */
    @Override
    public java.lang.String getItemId() {
        return _vwIvldReturnReserve.getItemId();
    }

    /**
    * Sets the item ID of this vw ivld return reserve.
    *
    * @param itemId the item ID of this vw ivld return reserve
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwIvldReturnReserve.setItemId(itemId);
    }

    /**
    * Returns the brand name of this vw ivld return reserve.
    *
    * @return the brand name of this vw ivld return reserve
    */
    @Override
    public java.lang.String getBrandName() {
        return _vwIvldReturnReserve.getBrandName();
    }

    /**
    * Sets the brand name of this vw ivld return reserve.
    *
    * @param brandName the brand name of this vw ivld return reserve
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _vwIvldReturnReserve.setBrandName(brandName);
    }

    /**
    * Returns the modified date of this vw ivld return reserve.
    *
    * @return the modified date of this vw ivld return reserve
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwIvldReturnReserve.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw ivld return reserve.
    *
    * @param modifiedDate the modified date of this vw ivld return reserve
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwIvldReturnReserve.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the account of this vw ivld return reserve.
    *
    * @return the account of this vw ivld return reserve
    */
    @Override
    public java.lang.String getAccount() {
        return _vwIvldReturnReserve.getAccount();
    }

    /**
    * Sets the account of this vw ivld return reserve.
    *
    * @param account the account of this vw ivld return reserve
    */
    @Override
    public void setAccount(java.lang.String account) {
        _vwIvldReturnReserve.setAccount(account);
    }

    /**
    * Returns the return reserve intf ID of this vw ivld return reserve.
    *
    * @return the return reserve intf ID of this vw ivld return reserve
    */
    @Override
    public java.lang.String getReturnReserveIntfId() {
        return _vwIvldReturnReserve.getReturnReserveIntfId();
    }

    /**
    * Sets the return reserve intf ID of this vw ivld return reserve.
    *
    * @param returnReserveIntfId the return reserve intf ID of this vw ivld return reserve
    */
    @Override
    public void setReturnReserveIntfId(java.lang.String returnReserveIntfId) {
        _vwIvldReturnReserve.setReturnReserveIntfId(returnReserveIntfId);
    }

    /**
    * Returns the source of this vw ivld return reserve.
    *
    * @return the source of this vw ivld return reserve
    */
    @Override
    public java.lang.String getSource() {
        return _vwIvldReturnReserve.getSource();
    }

    /**
    * Sets the source of this vw ivld return reserve.
    *
    * @param source the source of this vw ivld return reserve
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwIvldReturnReserve.setSource(source);
    }

    /**
    * Returns the created date of this vw ivld return reserve.
    *
    * @return the created date of this vw ivld return reserve
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwIvldReturnReserve.getCreatedDate();
    }

    /**
    * Sets the created date of this vw ivld return reserve.
    *
    * @param createdDate the created date of this vw ivld return reserve
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwIvldReturnReserve.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this vw ivld return reserve.
    *
    * @return the created by of this vw ivld return reserve
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwIvldReturnReserve.getCreatedBy();
    }

    /**
    * Sets the created by of this vw ivld return reserve.
    *
    * @param createdBy the created by of this vw ivld return reserve
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwIvldReturnReserve.setCreatedBy(createdBy);
    }

    /**
    * Returns the business unit of this vw ivld return reserve.
    *
    * @return the business unit of this vw ivld return reserve
    */
    @Override
    public java.lang.String getBusinessUnit() {
        return _vwIvldReturnReserve.getBusinessUnit();
    }

    /**
    * Sets the business unit of this vw ivld return reserve.
    *
    * @param businessUnit the business unit of this vw ivld return reserve
    */
    @Override
    public void setBusinessUnit(java.lang.String businessUnit) {
        _vwIvldReturnReserve.setBusinessUnit(businessUnit);
    }

    /**
    * Returns the business unit name of this vw ivld return reserve.
    *
    * @return the business unit name of this vw ivld return reserve
    */
    @Override
    public java.lang.String getBusinessUnitName() {
        return _vwIvldReturnReserve.getBusinessUnitName();
    }

    /**
    * Sets the business unit name of this vw ivld return reserve.
    *
    * @param businessUnitName the business unit name of this vw ivld return reserve
    */
    @Override
    public void setBusinessUnitName(java.lang.String businessUnitName) {
        _vwIvldReturnReserve.setBusinessUnitName(businessUnitName);
    }

    /**
    * Returns the add chg del indicator of this vw ivld return reserve.
    *
    * @return the add chg del indicator of this vw ivld return reserve
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwIvldReturnReserve.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw ivld return reserve.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw ivld return reserve
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwIvldReturnReserve.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the error code of this vw ivld return reserve.
    *
    * @return the error code of this vw ivld return reserve
    */
    @Override
    public java.lang.String getErrorCode() {
        return _vwIvldReturnReserve.getErrorCode();
    }

    /**
    * Sets the error code of this vw ivld return reserve.
    *
    * @param errorCode the error code of this vw ivld return reserve
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _vwIvldReturnReserve.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this vw ivld return reserve.
    *
    * @return the intf inserted date of this vw ivld return reserve
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _vwIvldReturnReserve.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this vw ivld return reserve.
    *
    * @param intfInsertedDate the intf inserted date of this vw ivld return reserve
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _vwIvldReturnReserve.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this vw ivld return reserve.
    *
    * @return the modified by of this vw ivld return reserve
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwIvldReturnReserve.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw ivld return reserve.
    *
    * @param modifiedBy the modified by of this vw ivld return reserve
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwIvldReturnReserve.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the item no of this vw ivld return reserve.
    *
    * @return the item no of this vw ivld return reserve
    */
    @Override
    public java.lang.String getItemNo() {
        return _vwIvldReturnReserve.getItemNo();
    }

    /**
    * Sets the item no of this vw ivld return reserve.
    *
    * @param itemNo the item no of this vw ivld return reserve
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _vwIvldReturnReserve.setItemNo(itemNo);
    }

    /**
    * Returns the month of this vw ivld return reserve.
    *
    * @return the month of this vw ivld return reserve
    */
    @Override
    public java.lang.String getMonth() {
        return _vwIvldReturnReserve.getMonth();
    }

    /**
    * Sets the month of this vw ivld return reserve.
    *
    * @param month the month of this vw ivld return reserve
    */
    @Override
    public void setMonth(java.lang.String month) {
        _vwIvldReturnReserve.setMonth(month);
    }

    /**
    * Returns the reprocessed flag of this vw ivld return reserve.
    *
    * @return the reprocessed flag of this vw ivld return reserve
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _vwIvldReturnReserve.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this vw ivld return reserve.
    *
    * @param reprocessedFlag the reprocessed flag of this vw ivld return reserve
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _vwIvldReturnReserve.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the udc6 of this vw ivld return reserve.
    *
    * @return the udc6 of this vw ivld return reserve
    */
    @Override
    public java.lang.String getUdc6() {
        return _vwIvldReturnReserve.getUdc6();
    }

    /**
    * Sets the udc6 of this vw ivld return reserve.
    *
    * @param udc6 the udc6 of this vw ivld return reserve
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _vwIvldReturnReserve.setUdc6(udc6);
    }

    /**
    * Returns the udc5 of this vw ivld return reserve.
    *
    * @return the udc5 of this vw ivld return reserve
    */
    @Override
    public java.lang.String getUdc5() {
        return _vwIvldReturnReserve.getUdc5();
    }

    /**
    * Sets the udc5 of this vw ivld return reserve.
    *
    * @param udc5 the udc5 of this vw ivld return reserve
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _vwIvldReturnReserve.setUdc5(udc5);
    }

    /**
    * Returns the udc4 of this vw ivld return reserve.
    *
    * @return the udc4 of this vw ivld return reserve
    */
    @Override
    public java.lang.String getUdc4() {
        return _vwIvldReturnReserve.getUdc4();
    }

    /**
    * Sets the udc4 of this vw ivld return reserve.
    *
    * @param udc4 the udc4 of this vw ivld return reserve
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _vwIvldReturnReserve.setUdc4(udc4);
    }

    /**
    * Returns the udc1 of this vw ivld return reserve.
    *
    * @return the udc1 of this vw ivld return reserve
    */
    @Override
    public java.lang.String getUdc1() {
        return _vwIvldReturnReserve.getUdc1();
    }

    /**
    * Sets the udc1 of this vw ivld return reserve.
    *
    * @param udc1 the udc1 of this vw ivld return reserve
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _vwIvldReturnReserve.setUdc1(udc1);
    }

    /**
    * Returns the units of this vw ivld return reserve.
    *
    * @return the units of this vw ivld return reserve
    */
    @Override
    public java.lang.String getUnits() {
        return _vwIvldReturnReserve.getUnits();
    }

    /**
    * Sets the units of this vw ivld return reserve.
    *
    * @param units the units of this vw ivld return reserve
    */
    @Override
    public void setUnits(java.lang.String units) {
        _vwIvldReturnReserve.setUnits(units);
    }

    /**
    * Returns the udc2 of this vw ivld return reserve.
    *
    * @return the udc2 of this vw ivld return reserve
    */
    @Override
    public java.lang.String getUdc2() {
        return _vwIvldReturnReserve.getUdc2();
    }

    /**
    * Sets the udc2 of this vw ivld return reserve.
    *
    * @param udc2 the udc2 of this vw ivld return reserve
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _vwIvldReturnReserve.setUdc2(udc2);
    }

    /**
    * Returns the udc3 of this vw ivld return reserve.
    *
    * @return the udc3 of this vw ivld return reserve
    */
    @Override
    public java.lang.String getUdc3() {
        return _vwIvldReturnReserve.getUdc3();
    }

    /**
    * Sets the udc3 of this vw ivld return reserve.
    *
    * @param udc3 the udc3 of this vw ivld return reserve
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _vwIvldReturnReserve.setUdc3(udc3);
    }

    /**
    * Returns the reason for failure of this vw ivld return reserve.
    *
    * @return the reason for failure of this vw ivld return reserve
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _vwIvldReturnReserve.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this vw ivld return reserve.
    *
    * @param reasonForFailure the reason for failure of this vw ivld return reserve
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _vwIvldReturnReserve.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the country of this vw ivld return reserve.
    *
    * @return the country of this vw ivld return reserve
    */
    @Override
    public java.lang.String getCountry() {
        return _vwIvldReturnReserve.getCountry();
    }

    /**
    * Sets the country of this vw ivld return reserve.
    *
    * @param country the country of this vw ivld return reserve
    */
    @Override
    public void setCountry(java.lang.String country) {
        _vwIvldReturnReserve.setCountry(country);
    }

    /**
    * Returns the company ID of this vw ivld return reserve.
    *
    * @return the company ID of this vw ivld return reserve
    */
    @Override
    public java.lang.String getCompanyId() {
        return _vwIvldReturnReserve.getCompanyId();
    }

    /**
    * Sets the company ID of this vw ivld return reserve.
    *
    * @param companyId the company ID of this vw ivld return reserve
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _vwIvldReturnReserve.setCompanyId(companyId);
    }

    /**
    * Returns the cost center of this vw ivld return reserve.
    *
    * @return the cost center of this vw ivld return reserve
    */
    @Override
    public java.lang.String getCostCenter() {
        return _vwIvldReturnReserve.getCostCenter();
    }

    /**
    * Sets the cost center of this vw ivld return reserve.
    *
    * @param costCenter the cost center of this vw ivld return reserve
    */
    @Override
    public void setCostCenter(java.lang.String costCenter) {
        _vwIvldReturnReserve.setCostCenter(costCenter);
    }

    /**
    * Returns the gl company of this vw ivld return reserve.
    *
    * @return the gl company of this vw ivld return reserve
    */
    @Override
    public java.lang.String getGlCompany() {
        return _vwIvldReturnReserve.getGlCompany();
    }

    /**
    * Sets the gl company of this vw ivld return reserve.
    *
    * @param glCompany the gl company of this vw ivld return reserve
    */
    @Override
    public void setGlCompany(java.lang.String glCompany) {
        _vwIvldReturnReserve.setGlCompany(glCompany);
    }

    /**
    * Returns the brand ID of this vw ivld return reserve.
    *
    * @return the brand ID of this vw ivld return reserve
    */
    @Override
    public java.lang.String getBrandId() {
        return _vwIvldReturnReserve.getBrandId();
    }

    /**
    * Sets the brand ID of this vw ivld return reserve.
    *
    * @param brandId the brand ID of this vw ivld return reserve
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _vwIvldReturnReserve.setBrandId(brandId);
    }

    /**
    * Returns the future1 of this vw ivld return reserve.
    *
    * @return the future1 of this vw ivld return reserve
    */
    @Override
    public java.lang.String getFuture1() {
        return _vwIvldReturnReserve.getFuture1();
    }

    /**
    * Sets the future1 of this vw ivld return reserve.
    *
    * @param future1 the future1 of this vw ivld return reserve
    */
    @Override
    public void setFuture1(java.lang.String future1) {
        _vwIvldReturnReserve.setFuture1(future1);
    }

    /**
    * Returns the future2 of this vw ivld return reserve.
    *
    * @return the future2 of this vw ivld return reserve
    */
    @Override
    public java.lang.String getFuture2() {
        return _vwIvldReturnReserve.getFuture2();
    }

    /**
    * Sets the future2 of this vw ivld return reserve.
    *
    * @param future2 the future2 of this vw ivld return reserve
    */
    @Override
    public void setFuture2(java.lang.String future2) {
        _vwIvldReturnReserve.setFuture2(future2);
    }

    /**
    * Returns the amount of this vw ivld return reserve.
    *
    * @return the amount of this vw ivld return reserve
    */
    @Override
    public java.lang.String getAmount() {
        return _vwIvldReturnReserve.getAmount();
    }

    /**
    * Sets the amount of this vw ivld return reserve.
    *
    * @param amount the amount of this vw ivld return reserve
    */
    @Override
    public void setAmount(java.lang.String amount) {
        _vwIvldReturnReserve.setAmount(amount);
    }

    /**
    * Returns the division of this vw ivld return reserve.
    *
    * @return the division of this vw ivld return reserve
    */
    @Override
    public java.lang.String getDivision() {
        return _vwIvldReturnReserve.getDivision();
    }

    /**
    * Sets the division of this vw ivld return reserve.
    *
    * @param division the division of this vw ivld return reserve
    */
    @Override
    public void setDivision(java.lang.String division) {
        _vwIvldReturnReserve.setDivision(division);
    }

    /**
    * Returns the company no of this vw ivld return reserve.
    *
    * @return the company no of this vw ivld return reserve
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _vwIvldReturnReserve.getCompanyNo();
    }

    /**
    * Sets the company no of this vw ivld return reserve.
    *
    * @param companyNo the company no of this vw ivld return reserve
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _vwIvldReturnReserve.setCompanyNo(companyNo);
    }

    /**
    * Returns the batch ID of this vw ivld return reserve.
    *
    * @return the batch ID of this vw ivld return reserve
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwIvldReturnReserve.getBatchId();
    }

    /**
    * Sets the batch ID of this vw ivld return reserve.
    *
    * @param batchId the batch ID of this vw ivld return reserve
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwIvldReturnReserve.setBatchId(batchId);
    }

    /**
    * Returns the item name of this vw ivld return reserve.
    *
    * @return the item name of this vw ivld return reserve
    */
    @Override
    public java.lang.String getItemName() {
        return _vwIvldReturnReserve.getItemName();
    }

    /**
    * Sets the item name of this vw ivld return reserve.
    *
    * @param itemName the item name of this vw ivld return reserve
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwIvldReturnReserve.setItemName(itemName);
    }

    /**
    * Returns the error field of this vw ivld return reserve.
    *
    * @return the error field of this vw ivld return reserve
    */
    @Override
    public java.lang.String getErrorField() {
        return _vwIvldReturnReserve.getErrorField();
    }

    /**
    * Sets the error field of this vw ivld return reserve.
    *
    * @param errorField the error field of this vw ivld return reserve
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _vwIvldReturnReserve.setErrorField(errorField);
    }

    /**
    * Returns the check record of this vw ivld return reserve.
    *
    * @return the check record of this vw ivld return reserve
    */
    @Override
    public boolean getCheckRecord() {
        return _vwIvldReturnReserve.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this vw ivld return reserve is check record.
    *
    * @return <code>true</code> if this vw ivld return reserve is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _vwIvldReturnReserve.isCheckRecord();
    }

    /**
    * Sets whether this vw ivld return reserve is check record.
    *
    * @param checkRecord the check record of this vw ivld return reserve
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _vwIvldReturnReserve.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _vwIvldReturnReserve.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwIvldReturnReserve.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwIvldReturnReserve.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwIvldReturnReserve.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwIvldReturnReserve.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwIvldReturnReserve.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwIvldReturnReserve.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwIvldReturnReserve.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwIvldReturnReserve.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwIvldReturnReserve.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwIvldReturnReserve.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwIvldReturnReserveWrapper((VwIvldReturnReserve) _vwIvldReturnReserve.clone());
    }

    @Override
    public int compareTo(VwIvldReturnReserve vwIvldReturnReserve) {
        return _vwIvldReturnReserve.compareTo(vwIvldReturnReserve);
    }

    @Override
    public int hashCode() {
        return _vwIvldReturnReserve.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwIvldReturnReserve> toCacheModel() {
        return _vwIvldReturnReserve.toCacheModel();
    }

    @Override
    public VwIvldReturnReserve toEscapedModel() {
        return new VwIvldReturnReserveWrapper(_vwIvldReturnReserve.toEscapedModel());
    }

    @Override
    public VwIvldReturnReserve toUnescapedModel() {
        return new VwIvldReturnReserveWrapper(_vwIvldReturnReserve.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwIvldReturnReserve.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwIvldReturnReserve.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwIvldReturnReserve.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwIvldReturnReserveWrapper)) {
            return false;
        }

        VwIvldReturnReserveWrapper vwIvldReturnReserveWrapper = (VwIvldReturnReserveWrapper) obj;

        if (Validator.equals(_vwIvldReturnReserve,
                    vwIvldReturnReserveWrapper._vwIvldReturnReserve)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwIvldReturnReserve getWrappedVwIvldReturnReserve() {
        return _vwIvldReturnReserve;
    }

    @Override
    public VwIvldReturnReserve getWrappedModel() {
        return _vwIvldReturnReserve;
    }

    @Override
    public void resetOriginalValues() {
        _vwIvldReturnReserve.resetOriginalValues();
    }
}
