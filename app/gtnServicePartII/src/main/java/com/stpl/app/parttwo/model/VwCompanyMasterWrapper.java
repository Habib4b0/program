package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwCompanyMaster}.
 * </p>
 *
 * @author
 * @see VwCompanyMaster
 * @generated
 */
public class VwCompanyMasterWrapper implements VwCompanyMaster,
    ModelWrapper<VwCompanyMaster> {
    private VwCompanyMaster _vwCompanyMaster;

    public VwCompanyMasterWrapper(VwCompanyMaster vwCompanyMaster) {
        _vwCompanyMaster = vwCompanyMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return VwCompanyMaster.class;
    }

    @Override
    public String getModelClassName() {
        return VwCompanyMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("state", getState());
        attributes.put("financialSystem", getFinancialSystem());
        attributes.put("companyGroup", getCompanyGroup());
        attributes.put("companyName", getCompanyName());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("companyCategory", getCompanyCategory());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("lives", getLives());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("address2", getAddress2());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("address1", getAddress1());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("zipCode", getZipCode());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
        attributes.put("companyId", getCompanyId());
        attributes.put("country", getCountry());
        attributes.put("companyType", getCompanyType());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("companyStatus", getCompanyStatus());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("city", getCity());
        attributes.put("regionCode", getRegionCode());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String state = (String) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        String financialSystem = (String) attributes.get("financialSystem");

        if (financialSystem != null) {
            setFinancialSystem(financialSystem);
        }

        String companyGroup = (String) attributes.get("companyGroup");

        if (companyGroup != null) {
            setCompanyGroup(companyGroup);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        String companyCategory = (String) attributes.get("companyCategory");

        if (companyCategory != null) {
            setCompanyCategory(companyCategory);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer lives = (Integer) attributes.get("lives");

        if (lives != null) {
            setLives(lives);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String address2 = (String) attributes.get("address2");

        if (address2 != null) {
            setAddress2(address2);
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

        String address1 = (String) attributes.get("address1");

        if (address1 != null) {
            setAddress1(address1);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String zipCode = (String) attributes.get("zipCode");

        if (zipCode != null) {
            setZipCode(zipCode);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String companyType = (String) attributes.get("companyType");

        if (companyType != null) {
            setCompanyType(companyType);
        }

        Date companyStartDate = (Date) attributes.get("companyStartDate");

        if (companyStartDate != null) {
            setCompanyStartDate(companyStartDate);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String companyStatus = (String) attributes.get("companyStatus");

        if (companyStatus != null) {
            setCompanyStatus(companyStatus);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String regionCode = (String) attributes.get("regionCode");

        if (regionCode != null) {
            setRegionCode(regionCode);
        }
    }

    /**
    * Returns the primary key of this vw company master.
    *
    * @return the primary key of this vw company master
    */
    @Override
    public int getPrimaryKey() {
        return _vwCompanyMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw company master.
    *
    * @param primaryKey the primary key of this vw company master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwCompanyMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the state of this vw company master.
    *
    * @return the state of this vw company master
    */
    @Override
    public java.lang.String getState() {
        return _vwCompanyMaster.getState();
    }

    /**
    * Sets the state of this vw company master.
    *
    * @param state the state of this vw company master
    */
    @Override
    public void setState(java.lang.String state) {
        _vwCompanyMaster.setState(state);
    }

    /**
    * Returns the financial system of this vw company master.
    *
    * @return the financial system of this vw company master
    */
    @Override
    public java.lang.String getFinancialSystem() {
        return _vwCompanyMaster.getFinancialSystem();
    }

    /**
    * Sets the financial system of this vw company master.
    *
    * @param financialSystem the financial system of this vw company master
    */
    @Override
    public void setFinancialSystem(java.lang.String financialSystem) {
        _vwCompanyMaster.setFinancialSystem(financialSystem);
    }

    /**
    * Returns the company group of this vw company master.
    *
    * @return the company group of this vw company master
    */
    @Override
    public java.lang.String getCompanyGroup() {
        return _vwCompanyMaster.getCompanyGroup();
    }

    /**
    * Sets the company group of this vw company master.
    *
    * @param companyGroup the company group of this vw company master
    */
    @Override
    public void setCompanyGroup(java.lang.String companyGroup) {
        _vwCompanyMaster.setCompanyGroup(companyGroup);
    }

    /**
    * Returns the company name of this vw company master.
    *
    * @return the company name of this vw company master
    */
    @Override
    public java.lang.String getCompanyName() {
        return _vwCompanyMaster.getCompanyName();
    }

    /**
    * Sets the company name of this vw company master.
    *
    * @param companyName the company name of this vw company master
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _vwCompanyMaster.setCompanyName(companyName);
    }

    /**
    * Returns the last updated date of this vw company master.
    *
    * @return the last updated date of this vw company master
    */
    @Override
    public java.util.Date getLastUpdatedDate() {
        return _vwCompanyMaster.getLastUpdatedDate();
    }

    /**
    * Sets the last updated date of this vw company master.
    *
    * @param lastUpdatedDate the last updated date of this vw company master
    */
    @Override
    public void setLastUpdatedDate(java.util.Date lastUpdatedDate) {
        _vwCompanyMaster.setLastUpdatedDate(lastUpdatedDate);
    }

    /**
    * Returns the company category of this vw company master.
    *
    * @return the company category of this vw company master
    */
    @Override
    public java.lang.String getCompanyCategory() {
        return _vwCompanyMaster.getCompanyCategory();
    }

    /**
    * Sets the company category of this vw company master.
    *
    * @param companyCategory the company category of this vw company master
    */
    @Override
    public void setCompanyCategory(java.lang.String companyCategory) {
        _vwCompanyMaster.setCompanyCategory(companyCategory);
    }

    /**
    * Returns the modified date of this vw company master.
    *
    * @return the modified date of this vw company master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwCompanyMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw company master.
    *
    * @param modifiedDate the modified date of this vw company master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwCompanyMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the lives of this vw company master.
    *
    * @return the lives of this vw company master
    */
    @Override
    public int getLives() {
        return _vwCompanyMaster.getLives();
    }

    /**
    * Sets the lives of this vw company master.
    *
    * @param lives the lives of this vw company master
    */
    @Override
    public void setLives(int lives) {
        _vwCompanyMaster.setLives(lives);
    }

    /**
    * Returns the organization key of this vw company master.
    *
    * @return the organization key of this vw company master
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _vwCompanyMaster.getOrganizationKey();
    }

    /**
    * Sets the organization key of this vw company master.
    *
    * @param organizationKey the organization key of this vw company master
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _vwCompanyMaster.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the address2 of this vw company master.
    *
    * @return the address2 of this vw company master
    */
    @Override
    public java.lang.String getAddress2() {
        return _vwCompanyMaster.getAddress2();
    }

    /**
    * Sets the address2 of this vw company master.
    *
    * @param address2 the address2 of this vw company master
    */
    @Override
    public void setAddress2(java.lang.String address2) {
        _vwCompanyMaster.setAddress2(address2);
    }

    /**
    * Returns the created date of this vw company master.
    *
    * @return the created date of this vw company master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwCompanyMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this vw company master.
    *
    * @param createdDate the created date of this vw company master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwCompanyMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this vw company master.
    *
    * @return the created by of this vw company master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwCompanyMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this vw company master.
    *
    * @param createdBy the created by of this vw company master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwCompanyMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this vw company master.
    *
    * @return the source of this vw company master
    */
    @Override
    public java.lang.String getSource() {
        return _vwCompanyMaster.getSource();
    }

    /**
    * Sets the source of this vw company master.
    *
    * @param source the source of this vw company master
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwCompanyMaster.setSource(source);
    }

    /**
    * Returns the address1 of this vw company master.
    *
    * @return the address1 of this vw company master
    */
    @Override
    public java.lang.String getAddress1() {
        return _vwCompanyMaster.getAddress1();
    }

    /**
    * Sets the address1 of this vw company master.
    *
    * @param address1 the address1 of this vw company master
    */
    @Override
    public void setAddress1(java.lang.String address1) {
        _vwCompanyMaster.setAddress1(address1);
    }

    /**
    * Returns the add chg del indicator of this vw company master.
    *
    * @return the add chg del indicator of this vw company master
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwCompanyMaster.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw company master.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw company master
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwCompanyMaster.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the modified by of this vw company master.
    *
    * @return the modified by of this vw company master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwCompanyMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw company master.
    *
    * @param modifiedBy the modified by of this vw company master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwCompanyMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the udc6 of this vw company master.
    *
    * @return the udc6 of this vw company master
    */
    @Override
    public java.lang.String getUdc6() {
        return _vwCompanyMaster.getUdc6();
    }

    /**
    * Sets the udc6 of this vw company master.
    *
    * @param udc6 the udc6 of this vw company master
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _vwCompanyMaster.setUdc6(udc6);
    }

    /**
    * Returns the udc5 of this vw company master.
    *
    * @return the udc5 of this vw company master
    */
    @Override
    public java.lang.String getUdc5() {
        return _vwCompanyMaster.getUdc5();
    }

    /**
    * Sets the udc5 of this vw company master.
    *
    * @param udc5 the udc5 of this vw company master
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _vwCompanyMaster.setUdc5(udc5);
    }

    /**
    * Returns the company master sid of this vw company master.
    *
    * @return the company master sid of this vw company master
    */
    @Override
    public int getCompanyMasterSid() {
        return _vwCompanyMaster.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this vw company master.
    *
    * @param companyMasterSid the company master sid of this vw company master
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _vwCompanyMaster.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the udc4 of this vw company master.
    *
    * @return the udc4 of this vw company master
    */
    @Override
    public java.lang.String getUdc4() {
        return _vwCompanyMaster.getUdc4();
    }

    /**
    * Sets the udc4 of this vw company master.
    *
    * @param udc4 the udc4 of this vw company master
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _vwCompanyMaster.setUdc4(udc4);
    }

    /**
    * Returns the udc1 of this vw company master.
    *
    * @return the udc1 of this vw company master
    */
    @Override
    public java.lang.String getUdc1() {
        return _vwCompanyMaster.getUdc1();
    }

    /**
    * Sets the udc1 of this vw company master.
    *
    * @param udc1 the udc1 of this vw company master
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _vwCompanyMaster.setUdc1(udc1);
    }

    /**
    * Returns the zip code of this vw company master.
    *
    * @return the zip code of this vw company master
    */
    @Override
    public java.lang.String getZipCode() {
        return _vwCompanyMaster.getZipCode();
    }

    /**
    * Sets the zip code of this vw company master.
    *
    * @param zipCode the zip code of this vw company master
    */
    @Override
    public void setZipCode(java.lang.String zipCode) {
        _vwCompanyMaster.setZipCode(zipCode);
    }

    /**
    * Returns the udc2 of this vw company master.
    *
    * @return the udc2 of this vw company master
    */
    @Override
    public java.lang.String getUdc2() {
        return _vwCompanyMaster.getUdc2();
    }

    /**
    * Sets the udc2 of this vw company master.
    *
    * @param udc2 the udc2 of this vw company master
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _vwCompanyMaster.setUdc2(udc2);
    }

    /**
    * Returns the udc3 of this vw company master.
    *
    * @return the udc3 of this vw company master
    */
    @Override
    public java.lang.String getUdc3() {
        return _vwCompanyMaster.getUdc3();
    }

    /**
    * Sets the udc3 of this vw company master.
    *
    * @param udc3 the udc3 of this vw company master
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _vwCompanyMaster.setUdc3(udc3);
    }

    /**
    * Returns the company ID of this vw company master.
    *
    * @return the company ID of this vw company master
    */
    @Override
    public java.lang.String getCompanyId() {
        return _vwCompanyMaster.getCompanyId();
    }

    /**
    * Sets the company ID of this vw company master.
    *
    * @param companyId the company ID of this vw company master
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _vwCompanyMaster.setCompanyId(companyId);
    }

    /**
    * Returns the country of this vw company master.
    *
    * @return the country of this vw company master
    */
    @Override
    public java.lang.String getCountry() {
        return _vwCompanyMaster.getCountry();
    }

    /**
    * Sets the country of this vw company master.
    *
    * @param country the country of this vw company master
    */
    @Override
    public void setCountry(java.lang.String country) {
        _vwCompanyMaster.setCountry(country);
    }

    /**
    * Returns the company type of this vw company master.
    *
    * @return the company type of this vw company master
    */
    @Override
    public java.lang.String getCompanyType() {
        return _vwCompanyMaster.getCompanyType();
    }

    /**
    * Sets the company type of this vw company master.
    *
    * @param companyType the company type of this vw company master
    */
    @Override
    public void setCompanyType(java.lang.String companyType) {
        _vwCompanyMaster.setCompanyType(companyType);
    }

    /**
    * Returns the company start date of this vw company master.
    *
    * @return the company start date of this vw company master
    */
    @Override
    public java.util.Date getCompanyStartDate() {
        return _vwCompanyMaster.getCompanyStartDate();
    }

    /**
    * Sets the company start date of this vw company master.
    *
    * @param companyStartDate the company start date of this vw company master
    */
    @Override
    public void setCompanyStartDate(java.util.Date companyStartDate) {
        _vwCompanyMaster.setCompanyStartDate(companyStartDate);
    }

    /**
    * Returns the company no of this vw company master.
    *
    * @return the company no of this vw company master
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _vwCompanyMaster.getCompanyNo();
    }

    /**
    * Sets the company no of this vw company master.
    *
    * @param companyNo the company no of this vw company master
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _vwCompanyMaster.setCompanyNo(companyNo);
    }

    /**
    * Returns the batch ID of this vw company master.
    *
    * @return the batch ID of this vw company master
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwCompanyMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this vw company master.
    *
    * @param batchId the batch ID of this vw company master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwCompanyMaster.setBatchId(batchId);
    }

    /**
    * Returns the company status of this vw company master.
    *
    * @return the company status of this vw company master
    */
    @Override
    public java.lang.String getCompanyStatus() {
        return _vwCompanyMaster.getCompanyStatus();
    }

    /**
    * Sets the company status of this vw company master.
    *
    * @param companyStatus the company status of this vw company master
    */
    @Override
    public void setCompanyStatus(java.lang.String companyStatus) {
        _vwCompanyMaster.setCompanyStatus(companyStatus);
    }

    /**
    * Returns the company end date of this vw company master.
    *
    * @return the company end date of this vw company master
    */
    @Override
    public java.util.Date getCompanyEndDate() {
        return _vwCompanyMaster.getCompanyEndDate();
    }

    /**
    * Sets the company end date of this vw company master.
    *
    * @param companyEndDate the company end date of this vw company master
    */
    @Override
    public void setCompanyEndDate(java.util.Date companyEndDate) {
        _vwCompanyMaster.setCompanyEndDate(companyEndDate);
    }

    /**
    * Returns the city of this vw company master.
    *
    * @return the city of this vw company master
    */
    @Override
    public java.lang.String getCity() {
        return _vwCompanyMaster.getCity();
    }

    /**
    * Sets the city of this vw company master.
    *
    * @param city the city of this vw company master
    */
    @Override
    public void setCity(java.lang.String city) {
        _vwCompanyMaster.setCity(city);
    }

    /**
    * Returns the region code of this vw company master.
    *
    * @return the region code of this vw company master
    */
    @Override
    public java.lang.String getRegionCode() {
        return _vwCompanyMaster.getRegionCode();
    }

    /**
    * Sets the region code of this vw company master.
    *
    * @param regionCode the region code of this vw company master
    */
    @Override
    public void setRegionCode(java.lang.String regionCode) {
        _vwCompanyMaster.setRegionCode(regionCode);
    }

    @Override
    public boolean isNew() {
        return _vwCompanyMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwCompanyMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwCompanyMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwCompanyMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwCompanyMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwCompanyMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwCompanyMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwCompanyMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwCompanyMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwCompanyMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwCompanyMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwCompanyMasterWrapper((VwCompanyMaster) _vwCompanyMaster.clone());
    }

    @Override
    public int compareTo(VwCompanyMaster vwCompanyMaster) {
        return _vwCompanyMaster.compareTo(vwCompanyMaster);
    }

    @Override
    public int hashCode() {
        return _vwCompanyMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwCompanyMaster> toCacheModel() {
        return _vwCompanyMaster.toCacheModel();
    }

    @Override
    public VwCompanyMaster toEscapedModel() {
        return new VwCompanyMasterWrapper(_vwCompanyMaster.toEscapedModel());
    }

    @Override
    public VwCompanyMaster toUnescapedModel() {
        return new VwCompanyMasterWrapper(_vwCompanyMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwCompanyMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwCompanyMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwCompanyMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwCompanyMasterWrapper)) {
            return false;
        }

        VwCompanyMasterWrapper vwCompanyMasterWrapper = (VwCompanyMasterWrapper) obj;

        if (Validator.equals(_vwCompanyMaster,
                    vwCompanyMasterWrapper._vwCompanyMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwCompanyMaster getWrappedVwCompanyMaster() {
        return _vwCompanyMaster;
    }

    @Override
    public VwCompanyMaster getWrappedModel() {
        return _vwCompanyMaster;
    }

    @Override
    public void resetOriginalValues() {
        _vwCompanyMaster.resetOriginalValues();
    }
}
