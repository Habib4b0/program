package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ReturnsMaster}.
 * </p>
 *
 * @author
 * @see ReturnsMaster
 * @generated
 */
public class ReturnsMasterWrapper implements ReturnsMaster,
    ModelWrapper<ReturnsMaster> {
    private ReturnsMaster _returnsMaster;

    public ReturnsMasterWrapper(ReturnsMaster returnsMaster) {
        _returnsMaster = returnsMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ReturnsMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ReturnsMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjValueAtOrigAsp", getAdjValueAtOrigAsp());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("firstReturn", getFirstReturn());
        attributes.put("asp", getAsp());
        attributes.put("maxExpiredMonthPlusCutoff",
            getMaxExpiredMonthPlusCutoff());
        attributes.put("posEstimatedReturnUnits", getPosEstimatedReturnUnits());
        attributes.put("origSaleMonthCutOff", getOrigSaleMonthCutOff());
        attributes.put("calcUsed", getCalcUsed());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("lastReturn", getLastReturn());
        attributes.put("expectedReturnUnits", getExpectedReturnUnits());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("version", getVersion());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("returnsMasterSid", getReturnsMasterSid());
        attributes.put("weightedAvgMonths", getWeightedAvgMonths());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("pct25th", getPct25th());
        attributes.put("loadDate", getLoadDate());
        attributes.put("maxExpiredMonth", getMaxExpiredMonth());
        attributes.put("actualReturnRate", getActualReturnRate());
        attributes.put("rreserveId", getRreserveId());
        attributes.put("returnComplete", getReturnComplete());
        attributes.put("expectedReturnRate", getExpectedReturnRate());
        attributes.put("pct50th", getPct50th());
        attributes.put("within50qrtile", getWithin50qrtile());
        attributes.put("cumReturnUnits", getCumReturnUnits());
        attributes.put("origSaleMonth", getOrigSaleMonth());
        attributes.put("description", getDescription());
        attributes.put("sku", getSku());
        attributes.put("upperLimit", getUpperLimit());
        attributes.put("lowerLimit", getLowerLimit());
        attributes.put("valueAtOrigAsp", getValueAtOrigAsp());
        attributes.put("adjEstimatedReturnUnits", getAdjEstimatedReturnUnits());
        attributes.put("pct75th", getPct75th());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("pastExpiration", getPastExpiration());
        attributes.put("batchId", getBatchId());
        attributes.put("maximum", getMaximum());
        attributes.put("origSaleUnits", getOrigSaleUnits());
        attributes.put("brand", getBrand());
        attributes.put("origSaleDollars", getOrigSaleDollars());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String adjValueAtOrigAsp = (String) attributes.get("adjValueAtOrigAsp");

        if (adjValueAtOrigAsp != null) {
            setAdjValueAtOrigAsp(adjValueAtOrigAsp);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date firstReturn = (Date) attributes.get("firstReturn");

        if (firstReturn != null) {
            setFirstReturn(firstReturn);
        }

        String asp = (String) attributes.get("asp");

        if (asp != null) {
            setAsp(asp);
        }

        Date maxExpiredMonthPlusCutoff = (Date) attributes.get(
                "maxExpiredMonthPlusCutoff");

        if (maxExpiredMonthPlusCutoff != null) {
            setMaxExpiredMonthPlusCutoff(maxExpiredMonthPlusCutoff);
        }

        String posEstimatedReturnUnits = (String) attributes.get(
                "posEstimatedReturnUnits");

        if (posEstimatedReturnUnits != null) {
            setPosEstimatedReturnUnits(posEstimatedReturnUnits);
        }

        Date origSaleMonthCutOff = (Date) attributes.get("origSaleMonthCutOff");

        if (origSaleMonthCutOff != null) {
            setOrigSaleMonthCutOff(origSaleMonthCutOff);
        }

        String calcUsed = (String) attributes.get("calcUsed");

        if (calcUsed != null) {
            setCalcUsed(calcUsed);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Date lastReturn = (Date) attributes.get("lastReturn");

        if (lastReturn != null) {
            setLastReturn(lastReturn);
        }

        String expectedReturnUnits = (String) attributes.get(
                "expectedReturnUnits");

        if (expectedReturnUnits != null) {
            setExpectedReturnUnits(expectedReturnUnits);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String version = (String) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Integer returnsMasterSid = (Integer) attributes.get("returnsMasterSid");

        if (returnsMasterSid != null) {
            setReturnsMasterSid(returnsMasterSid);
        }

        String weightedAvgMonths = (String) attributes.get("weightedAvgMonths");

        if (weightedAvgMonths != null) {
            setWeightedAvgMonths(weightedAvgMonths);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String pct25th = (String) attributes.get("pct25th");

        if (pct25th != null) {
            setPct25th(pct25th);
        }

        Date loadDate = (Date) attributes.get("loadDate");

        if (loadDate != null) {
            setLoadDate(loadDate);
        }

        Date maxExpiredMonth = (Date) attributes.get("maxExpiredMonth");

        if (maxExpiredMonth != null) {
            setMaxExpiredMonth(maxExpiredMonth);
        }

        String actualReturnRate = (String) attributes.get("actualReturnRate");

        if (actualReturnRate != null) {
            setActualReturnRate(actualReturnRate);
        }

        String rreserveId = (String) attributes.get("rreserveId");

        if (rreserveId != null) {
            setRreserveId(rreserveId);
        }

        String returnComplete = (String) attributes.get("returnComplete");

        if (returnComplete != null) {
            setReturnComplete(returnComplete);
        }

        String expectedReturnRate = (String) attributes.get(
                "expectedReturnRate");

        if (expectedReturnRate != null) {
            setExpectedReturnRate(expectedReturnRate);
        }

        String pct50th = (String) attributes.get("pct50th");

        if (pct50th != null) {
            setPct50th(pct50th);
        }

        String within50qrtile = (String) attributes.get("within50qrtile");

        if (within50qrtile != null) {
            setWithin50qrtile(within50qrtile);
        }

        String cumReturnUnits = (String) attributes.get("cumReturnUnits");

        if (cumReturnUnits != null) {
            setCumReturnUnits(cumReturnUnits);
        }

        Date origSaleMonth = (Date) attributes.get("origSaleMonth");

        if (origSaleMonth != null) {
            setOrigSaleMonth(origSaleMonth);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String sku = (String) attributes.get("sku");

        if (sku != null) {
            setSku(sku);
        }

        String upperLimit = (String) attributes.get("upperLimit");

        if (upperLimit != null) {
            setUpperLimit(upperLimit);
        }

        String lowerLimit = (String) attributes.get("lowerLimit");

        if (lowerLimit != null) {
            setLowerLimit(lowerLimit);
        }

        String valueAtOrigAsp = (String) attributes.get("valueAtOrigAsp");

        if (valueAtOrigAsp != null) {
            setValueAtOrigAsp(valueAtOrigAsp);
        }

        String adjEstimatedReturnUnits = (String) attributes.get(
                "adjEstimatedReturnUnits");

        if (adjEstimatedReturnUnits != null) {
            setAdjEstimatedReturnUnits(adjEstimatedReturnUnits);
        }

        String pct75th = (String) attributes.get("pct75th");

        if (pct75th != null) {
            setPct75th(pct75th);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String pastExpiration = (String) attributes.get("pastExpiration");

        if (pastExpiration != null) {
            setPastExpiration(pastExpiration);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String maximum = (String) attributes.get("maximum");

        if (maximum != null) {
            setMaximum(maximum);
        }

        String origSaleUnits = (String) attributes.get("origSaleUnits");

        if (origSaleUnits != null) {
            setOrigSaleUnits(origSaleUnits);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String origSaleDollars = (String) attributes.get("origSaleDollars");

        if (origSaleDollars != null) {
            setOrigSaleDollars(origSaleDollars);
        }
    }

    /**
    * Returns the primary key of this returns master.
    *
    * @return the primary key of this returns master
    */
    @Override
    public int getPrimaryKey() {
        return _returnsMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this returns master.
    *
    * @param primaryKey the primary key of this returns master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _returnsMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adj value at orig asp of this returns master.
    *
    * @return the adj value at orig asp of this returns master
    */
    @Override
    public java.lang.String getAdjValueAtOrigAsp() {
        return _returnsMaster.getAdjValueAtOrigAsp();
    }

    /**
    * Sets the adj value at orig asp of this returns master.
    *
    * @param adjValueAtOrigAsp the adj value at orig asp of this returns master
    */
    @Override
    public void setAdjValueAtOrigAsp(java.lang.String adjValueAtOrigAsp) {
        _returnsMaster.setAdjValueAtOrigAsp(adjValueAtOrigAsp);
    }

    /**
    * Returns the item master sid of this returns master.
    *
    * @return the item master sid of this returns master
    */
    @Override
    public int getItemMasterSid() {
        return _returnsMaster.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this returns master.
    *
    * @param itemMasterSid the item master sid of this returns master
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _returnsMaster.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the first return of this returns master.
    *
    * @return the first return of this returns master
    */
    @Override
    public java.util.Date getFirstReturn() {
        return _returnsMaster.getFirstReturn();
    }

    /**
    * Sets the first return of this returns master.
    *
    * @param firstReturn the first return of this returns master
    */
    @Override
    public void setFirstReturn(java.util.Date firstReturn) {
        _returnsMaster.setFirstReturn(firstReturn);
    }

    /**
    * Returns the asp of this returns master.
    *
    * @return the asp of this returns master
    */
    @Override
    public java.lang.String getAsp() {
        return _returnsMaster.getAsp();
    }

    /**
    * Sets the asp of this returns master.
    *
    * @param asp the asp of this returns master
    */
    @Override
    public void setAsp(java.lang.String asp) {
        _returnsMaster.setAsp(asp);
    }

    /**
    * Returns the max expired month plus cutoff of this returns master.
    *
    * @return the max expired month plus cutoff of this returns master
    */
    @Override
    public java.util.Date getMaxExpiredMonthPlusCutoff() {
        return _returnsMaster.getMaxExpiredMonthPlusCutoff();
    }

    /**
    * Sets the max expired month plus cutoff of this returns master.
    *
    * @param maxExpiredMonthPlusCutoff the max expired month plus cutoff of this returns master
    */
    @Override
    public void setMaxExpiredMonthPlusCutoff(
        java.util.Date maxExpiredMonthPlusCutoff) {
        _returnsMaster.setMaxExpiredMonthPlusCutoff(maxExpiredMonthPlusCutoff);
    }

    /**
    * Returns the pos estimated return units of this returns master.
    *
    * @return the pos estimated return units of this returns master
    */
    @Override
    public java.lang.String getPosEstimatedReturnUnits() {
        return _returnsMaster.getPosEstimatedReturnUnits();
    }

    /**
    * Sets the pos estimated return units of this returns master.
    *
    * @param posEstimatedReturnUnits the pos estimated return units of this returns master
    */
    @Override
    public void setPosEstimatedReturnUnits(
        java.lang.String posEstimatedReturnUnits) {
        _returnsMaster.setPosEstimatedReturnUnits(posEstimatedReturnUnits);
    }

    /**
    * Returns the orig sale month cut off of this returns master.
    *
    * @return the orig sale month cut off of this returns master
    */
    @Override
    public java.util.Date getOrigSaleMonthCutOff() {
        return _returnsMaster.getOrigSaleMonthCutOff();
    }

    /**
    * Sets the orig sale month cut off of this returns master.
    *
    * @param origSaleMonthCutOff the orig sale month cut off of this returns master
    */
    @Override
    public void setOrigSaleMonthCutOff(java.util.Date origSaleMonthCutOff) {
        _returnsMaster.setOrigSaleMonthCutOff(origSaleMonthCutOff);
    }

    /**
    * Returns the calc used of this returns master.
    *
    * @return the calc used of this returns master
    */
    @Override
    public java.lang.String getCalcUsed() {
        return _returnsMaster.getCalcUsed();
    }

    /**
    * Sets the calc used of this returns master.
    *
    * @param calcUsed the calc used of this returns master
    */
    @Override
    public void setCalcUsed(java.lang.String calcUsed) {
        _returnsMaster.setCalcUsed(calcUsed);
    }

    /**
    * Returns the modified date of this returns master.
    *
    * @return the modified date of this returns master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _returnsMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this returns master.
    *
    * @param modifiedDate the modified date of this returns master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _returnsMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the brand master sid of this returns master.
    *
    * @return the brand master sid of this returns master
    */
    @Override
    public int getBrandMasterSid() {
        return _returnsMaster.getBrandMasterSid();
    }

    /**
    * Sets the brand master sid of this returns master.
    *
    * @param brandMasterSid the brand master sid of this returns master
    */
    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _returnsMaster.setBrandMasterSid(brandMasterSid);
    }

    /**
    * Returns the last return of this returns master.
    *
    * @return the last return of this returns master
    */
    @Override
    public java.util.Date getLastReturn() {
        return _returnsMaster.getLastReturn();
    }

    /**
    * Sets the last return of this returns master.
    *
    * @param lastReturn the last return of this returns master
    */
    @Override
    public void setLastReturn(java.util.Date lastReturn) {
        _returnsMaster.setLastReturn(lastReturn);
    }

    /**
    * Returns the expected return units of this returns master.
    *
    * @return the expected return units of this returns master
    */
    @Override
    public java.lang.String getExpectedReturnUnits() {
        return _returnsMaster.getExpectedReturnUnits();
    }

    /**
    * Sets the expected return units of this returns master.
    *
    * @param expectedReturnUnits the expected return units of this returns master
    */
    @Override
    public void setExpectedReturnUnits(java.lang.String expectedReturnUnits) {
        _returnsMaster.setExpectedReturnUnits(expectedReturnUnits);
    }

    /**
    * Returns the created date of this returns master.
    *
    * @return the created date of this returns master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _returnsMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this returns master.
    *
    * @param createdDate the created date of this returns master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _returnsMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this returns master.
    *
    * @return the created by of this returns master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _returnsMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this returns master.
    *
    * @param createdBy the created by of this returns master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _returnsMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this returns master.
    *
    * @return the source of this returns master
    */
    @Override
    public java.lang.String getSource() {
        return _returnsMaster.getSource();
    }

    /**
    * Sets the source of this returns master.
    *
    * @param source the source of this returns master
    */
    @Override
    public void setSource(java.lang.String source) {
        _returnsMaster.setSource(source);
    }

    /**
    * Returns the version of this returns master.
    *
    * @return the version of this returns master
    */
    @Override
    public java.lang.String getVersion() {
        return _returnsMaster.getVersion();
    }

    /**
    * Sets the version of this returns master.
    *
    * @param version the version of this returns master
    */
    @Override
    public void setVersion(java.lang.String version) {
        _returnsMaster.setVersion(version);
    }

    /**
    * Returns the add chg del indicator of this returns master.
    *
    * @return the add chg del indicator of this returns master
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _returnsMaster.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this returns master.
    *
    * @param addChgDelIndicator the add chg del indicator of this returns master
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _returnsMaster.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the returns master sid of this returns master.
    *
    * @return the returns master sid of this returns master
    */
    @Override
    public int getReturnsMasterSid() {
        return _returnsMaster.getReturnsMasterSid();
    }

    /**
    * Sets the returns master sid of this returns master.
    *
    * @param returnsMasterSid the returns master sid of this returns master
    */
    @Override
    public void setReturnsMasterSid(int returnsMasterSid) {
        _returnsMaster.setReturnsMasterSid(returnsMasterSid);
    }

    /**
    * Returns the weighted avg months of this returns master.
    *
    * @return the weighted avg months of this returns master
    */
    @Override
    public java.lang.String getWeightedAvgMonths() {
        return _returnsMaster.getWeightedAvgMonths();
    }

    /**
    * Sets the weighted avg months of this returns master.
    *
    * @param weightedAvgMonths the weighted avg months of this returns master
    */
    @Override
    public void setWeightedAvgMonths(java.lang.String weightedAvgMonths) {
        _returnsMaster.setWeightedAvgMonths(weightedAvgMonths);
    }

    /**
    * Returns the modified by of this returns master.
    *
    * @return the modified by of this returns master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _returnsMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this returns master.
    *
    * @param modifiedBy the modified by of this returns master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _returnsMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this returns master.
    *
    * @return the inbound status of this returns master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _returnsMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this returns master.
    *
    * @param inboundStatus the inbound status of this returns master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _returnsMaster.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the pct25th of this returns master.
    *
    * @return the pct25th of this returns master
    */
    @Override
    public java.lang.String getPct25th() {
        return _returnsMaster.getPct25th();
    }

    /**
    * Sets the pct25th of this returns master.
    *
    * @param pct25th the pct25th of this returns master
    */
    @Override
    public void setPct25th(java.lang.String pct25th) {
        _returnsMaster.setPct25th(pct25th);
    }

    /**
    * Returns the load date of this returns master.
    *
    * @return the load date of this returns master
    */
    @Override
    public java.util.Date getLoadDate() {
        return _returnsMaster.getLoadDate();
    }

    /**
    * Sets the load date of this returns master.
    *
    * @param loadDate the load date of this returns master
    */
    @Override
    public void setLoadDate(java.util.Date loadDate) {
        _returnsMaster.setLoadDate(loadDate);
    }

    /**
    * Returns the max expired month of this returns master.
    *
    * @return the max expired month of this returns master
    */
    @Override
    public java.util.Date getMaxExpiredMonth() {
        return _returnsMaster.getMaxExpiredMonth();
    }

    /**
    * Sets the max expired month of this returns master.
    *
    * @param maxExpiredMonth the max expired month of this returns master
    */
    @Override
    public void setMaxExpiredMonth(java.util.Date maxExpiredMonth) {
        _returnsMaster.setMaxExpiredMonth(maxExpiredMonth);
    }

    /**
    * Returns the actual return rate of this returns master.
    *
    * @return the actual return rate of this returns master
    */
    @Override
    public java.lang.String getActualReturnRate() {
        return _returnsMaster.getActualReturnRate();
    }

    /**
    * Sets the actual return rate of this returns master.
    *
    * @param actualReturnRate the actual return rate of this returns master
    */
    @Override
    public void setActualReturnRate(java.lang.String actualReturnRate) {
        _returnsMaster.setActualReturnRate(actualReturnRate);
    }

    /**
    * Returns the rreserve ID of this returns master.
    *
    * @return the rreserve ID of this returns master
    */
    @Override
    public java.lang.String getRreserveId() {
        return _returnsMaster.getRreserveId();
    }

    /**
    * Sets the rreserve ID of this returns master.
    *
    * @param rreserveId the rreserve ID of this returns master
    */
    @Override
    public void setRreserveId(java.lang.String rreserveId) {
        _returnsMaster.setRreserveId(rreserveId);
    }

    /**
    * Returns the return complete of this returns master.
    *
    * @return the return complete of this returns master
    */
    @Override
    public java.lang.String getReturnComplete() {
        return _returnsMaster.getReturnComplete();
    }

    /**
    * Sets the return complete of this returns master.
    *
    * @param returnComplete the return complete of this returns master
    */
    @Override
    public void setReturnComplete(java.lang.String returnComplete) {
        _returnsMaster.setReturnComplete(returnComplete);
    }

    /**
    * Returns the expected return rate of this returns master.
    *
    * @return the expected return rate of this returns master
    */
    @Override
    public java.lang.String getExpectedReturnRate() {
        return _returnsMaster.getExpectedReturnRate();
    }

    /**
    * Sets the expected return rate of this returns master.
    *
    * @param expectedReturnRate the expected return rate of this returns master
    */
    @Override
    public void setExpectedReturnRate(java.lang.String expectedReturnRate) {
        _returnsMaster.setExpectedReturnRate(expectedReturnRate);
    }

    /**
    * Returns the pct50th of this returns master.
    *
    * @return the pct50th of this returns master
    */
    @Override
    public java.lang.String getPct50th() {
        return _returnsMaster.getPct50th();
    }

    /**
    * Sets the pct50th of this returns master.
    *
    * @param pct50th the pct50th of this returns master
    */
    @Override
    public void setPct50th(java.lang.String pct50th) {
        _returnsMaster.setPct50th(pct50th);
    }

    /**
    * Returns the within50qrtile of this returns master.
    *
    * @return the within50qrtile of this returns master
    */
    @Override
    public java.lang.String getWithin50qrtile() {
        return _returnsMaster.getWithin50qrtile();
    }

    /**
    * Sets the within50qrtile of this returns master.
    *
    * @param within50qrtile the within50qrtile of this returns master
    */
    @Override
    public void setWithin50qrtile(java.lang.String within50qrtile) {
        _returnsMaster.setWithin50qrtile(within50qrtile);
    }

    /**
    * Returns the cum return units of this returns master.
    *
    * @return the cum return units of this returns master
    */
    @Override
    public java.lang.String getCumReturnUnits() {
        return _returnsMaster.getCumReturnUnits();
    }

    /**
    * Sets the cum return units of this returns master.
    *
    * @param cumReturnUnits the cum return units of this returns master
    */
    @Override
    public void setCumReturnUnits(java.lang.String cumReturnUnits) {
        _returnsMaster.setCumReturnUnits(cumReturnUnits);
    }

    /**
    * Returns the orig sale month of this returns master.
    *
    * @return the orig sale month of this returns master
    */
    @Override
    public java.util.Date getOrigSaleMonth() {
        return _returnsMaster.getOrigSaleMonth();
    }

    /**
    * Sets the orig sale month of this returns master.
    *
    * @param origSaleMonth the orig sale month of this returns master
    */
    @Override
    public void setOrigSaleMonth(java.util.Date origSaleMonth) {
        _returnsMaster.setOrigSaleMonth(origSaleMonth);
    }

    /**
    * Returns the description of this returns master.
    *
    * @return the description of this returns master
    */
    @Override
    public java.lang.String getDescription() {
        return _returnsMaster.getDescription();
    }

    /**
    * Sets the description of this returns master.
    *
    * @param description the description of this returns master
    */
    @Override
    public void setDescription(java.lang.String description) {
        _returnsMaster.setDescription(description);
    }

    /**
    * Returns the sku of this returns master.
    *
    * @return the sku of this returns master
    */
    @Override
    public java.lang.String getSku() {
        return _returnsMaster.getSku();
    }

    /**
    * Sets the sku of this returns master.
    *
    * @param sku the sku of this returns master
    */
    @Override
    public void setSku(java.lang.String sku) {
        _returnsMaster.setSku(sku);
    }

    /**
    * Returns the upper limit of this returns master.
    *
    * @return the upper limit of this returns master
    */
    @Override
    public java.lang.String getUpperLimit() {
        return _returnsMaster.getUpperLimit();
    }

    /**
    * Sets the upper limit of this returns master.
    *
    * @param upperLimit the upper limit of this returns master
    */
    @Override
    public void setUpperLimit(java.lang.String upperLimit) {
        _returnsMaster.setUpperLimit(upperLimit);
    }

    /**
    * Returns the lower limit of this returns master.
    *
    * @return the lower limit of this returns master
    */
    @Override
    public java.lang.String getLowerLimit() {
        return _returnsMaster.getLowerLimit();
    }

    /**
    * Sets the lower limit of this returns master.
    *
    * @param lowerLimit the lower limit of this returns master
    */
    @Override
    public void setLowerLimit(java.lang.String lowerLimit) {
        _returnsMaster.setLowerLimit(lowerLimit);
    }

    /**
    * Returns the value at orig asp of this returns master.
    *
    * @return the value at orig asp of this returns master
    */
    @Override
    public java.lang.String getValueAtOrigAsp() {
        return _returnsMaster.getValueAtOrigAsp();
    }

    /**
    * Sets the value at orig asp of this returns master.
    *
    * @param valueAtOrigAsp the value at orig asp of this returns master
    */
    @Override
    public void setValueAtOrigAsp(java.lang.String valueAtOrigAsp) {
        _returnsMaster.setValueAtOrigAsp(valueAtOrigAsp);
    }

    /**
    * Returns the adj estimated return units of this returns master.
    *
    * @return the adj estimated return units of this returns master
    */
    @Override
    public java.lang.String getAdjEstimatedReturnUnits() {
        return _returnsMaster.getAdjEstimatedReturnUnits();
    }

    /**
    * Sets the adj estimated return units of this returns master.
    *
    * @param adjEstimatedReturnUnits the adj estimated return units of this returns master
    */
    @Override
    public void setAdjEstimatedReturnUnits(
        java.lang.String adjEstimatedReturnUnits) {
        _returnsMaster.setAdjEstimatedReturnUnits(adjEstimatedReturnUnits);
    }

    /**
    * Returns the pct75th of this returns master.
    *
    * @return the pct75th of this returns master
    */
    @Override
    public java.lang.String getPct75th() {
        return _returnsMaster.getPct75th();
    }

    /**
    * Sets the pct75th of this returns master.
    *
    * @param pct75th the pct75th of this returns master
    */
    @Override
    public void setPct75th(java.lang.String pct75th) {
        _returnsMaster.setPct75th(pct75th);
    }

    /**
    * Returns the record lock status of this returns master.
    *
    * @return the record lock status of this returns master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _returnsMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this returns master is record lock status.
    *
    * @return <code>true</code> if this returns master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _returnsMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this returns master is record lock status.
    *
    * @param recordLockStatus the record lock status of this returns master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _returnsMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the past expiration of this returns master.
    *
    * @return the past expiration of this returns master
    */
    @Override
    public java.lang.String getPastExpiration() {
        return _returnsMaster.getPastExpiration();
    }

    /**
    * Sets the past expiration of this returns master.
    *
    * @param pastExpiration the past expiration of this returns master
    */
    @Override
    public void setPastExpiration(java.lang.String pastExpiration) {
        _returnsMaster.setPastExpiration(pastExpiration);
    }

    /**
    * Returns the batch ID of this returns master.
    *
    * @return the batch ID of this returns master
    */
    @Override
    public java.lang.String getBatchId() {
        return _returnsMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this returns master.
    *
    * @param batchId the batch ID of this returns master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _returnsMaster.setBatchId(batchId);
    }

    /**
    * Returns the maximum of this returns master.
    *
    * @return the maximum of this returns master
    */
    @Override
    public java.lang.String getMaximum() {
        return _returnsMaster.getMaximum();
    }

    /**
    * Sets the maximum of this returns master.
    *
    * @param maximum the maximum of this returns master
    */
    @Override
    public void setMaximum(java.lang.String maximum) {
        _returnsMaster.setMaximum(maximum);
    }

    /**
    * Returns the orig sale units of this returns master.
    *
    * @return the orig sale units of this returns master
    */
    @Override
    public java.lang.String getOrigSaleUnits() {
        return _returnsMaster.getOrigSaleUnits();
    }

    /**
    * Sets the orig sale units of this returns master.
    *
    * @param origSaleUnits the orig sale units of this returns master
    */
    @Override
    public void setOrigSaleUnits(java.lang.String origSaleUnits) {
        _returnsMaster.setOrigSaleUnits(origSaleUnits);
    }

    /**
    * Returns the brand of this returns master.
    *
    * @return the brand of this returns master
    */
    @Override
    public java.lang.String getBrand() {
        return _returnsMaster.getBrand();
    }

    /**
    * Sets the brand of this returns master.
    *
    * @param brand the brand of this returns master
    */
    @Override
    public void setBrand(java.lang.String brand) {
        _returnsMaster.setBrand(brand);
    }

    /**
    * Returns the orig sale dollars of this returns master.
    *
    * @return the orig sale dollars of this returns master
    */
    @Override
    public java.lang.String getOrigSaleDollars() {
        return _returnsMaster.getOrigSaleDollars();
    }

    /**
    * Sets the orig sale dollars of this returns master.
    *
    * @param origSaleDollars the orig sale dollars of this returns master
    */
    @Override
    public void setOrigSaleDollars(java.lang.String origSaleDollars) {
        _returnsMaster.setOrigSaleDollars(origSaleDollars);
    }

    @Override
    public boolean isNew() {
        return _returnsMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _returnsMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _returnsMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _returnsMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _returnsMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _returnsMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _returnsMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _returnsMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _returnsMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _returnsMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _returnsMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ReturnsMasterWrapper((ReturnsMaster) _returnsMaster.clone());
    }

    @Override
    public int compareTo(ReturnsMaster returnsMaster) {
        return _returnsMaster.compareTo(returnsMaster);
    }

    @Override
    public int hashCode() {
        return _returnsMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ReturnsMaster> toCacheModel() {
        return _returnsMaster.toCacheModel();
    }

    @Override
    public ReturnsMaster toEscapedModel() {
        return new ReturnsMasterWrapper(_returnsMaster.toEscapedModel());
    }

    @Override
    public ReturnsMaster toUnescapedModel() {
        return new ReturnsMasterWrapper(_returnsMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _returnsMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _returnsMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _returnsMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ReturnsMasterWrapper)) {
            return false;
        }

        ReturnsMasterWrapper returnsMasterWrapper = (ReturnsMasterWrapper) obj;

        if (Validator.equals(_returnsMaster, returnsMasterWrapper._returnsMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ReturnsMaster getWrappedReturnsMaster() {
        return _returnsMaster;
    }

    @Override
    public ReturnsMaster getWrappedModel() {
        return _returnsMaster;
    }

    @Override
    public void resetOriginalValues() {
        _returnsMaster.resetOriginalValues();
    }
}
