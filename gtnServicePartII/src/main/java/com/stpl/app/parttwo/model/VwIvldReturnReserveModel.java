package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the VwIvldReturnReserve service. Represents a row in the &quot;VW_IVLD_RETURN_RESERVE&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveImpl}.
 * </p>
 *
 * @author
 * @see VwIvldReturnReserve
 * @see com.stpl.app.parttwo.model.impl.VwIvldReturnReserveImpl
 * @see com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl
 * @generated
 */
public interface VwIvldReturnReserveModel extends BaseModel<VwIvldReturnReserve> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a vw ivld return reserve model instance should use the {@link VwIvldReturnReserve} interface instead.
     */

    /**
     * Returns the primary key of this vw ivld return reserve.
     *
     * @return the primary key of this vw ivld return reserve
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this vw ivld return reserve.
     *
     * @param primaryKey the primary key of this vw ivld return reserve
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the ivld return reserve sid of this vw ivld return reserve.
     *
     * @return the ivld return reserve sid of this vw ivld return reserve
     */
    public int getIvldReturnReserveSid();

    /**
     * Sets the ivld return reserve sid of this vw ivld return reserve.
     *
     * @param ivldReturnReserveSid the ivld return reserve sid of this vw ivld return reserve
     */
    public void setIvldReturnReserveSid(int ivldReturnReserveSid);

    /**
     * Returns the company name of this vw ivld return reserve.
     *
     * @return the company name of this vw ivld return reserve
     */
    @AutoEscape
    public String getCompanyName();

    /**
     * Sets the company name of this vw ivld return reserve.
     *
     * @param companyName the company name of this vw ivld return reserve
     */
    public void setCompanyName(String companyName);

    /**
     * Returns the year of this vw ivld return reserve.
     *
     * @return the year of this vw ivld return reserve
     */
    @AutoEscape
    public String getYear();

    /**
     * Sets the year of this vw ivld return reserve.
     *
     * @param year the year of this vw ivld return reserve
     */
    public void setYear(String year);

    /**
     * Returns the project of this vw ivld return reserve.
     *
     * @return the project of this vw ivld return reserve
     */
    @AutoEscape
    public String getProject();

    /**
     * Sets the project of this vw ivld return reserve.
     *
     * @param project the project of this vw ivld return reserve
     */
    public void setProject(String project);

    /**
     * Returns the item ID of this vw ivld return reserve.
     *
     * @return the item ID of this vw ivld return reserve
     */
    @AutoEscape
    public String getItemId();

    /**
     * Sets the item ID of this vw ivld return reserve.
     *
     * @param itemId the item ID of this vw ivld return reserve
     */
    public void setItemId(String itemId);

    /**
     * Returns the brand name of this vw ivld return reserve.
     *
     * @return the brand name of this vw ivld return reserve
     */
    @AutoEscape
    public String getBrandName();

    /**
     * Sets the brand name of this vw ivld return reserve.
     *
     * @param brandName the brand name of this vw ivld return reserve
     */
    public void setBrandName(String brandName);

    /**
     * Returns the modified date of this vw ivld return reserve.
     *
     * @return the modified date of this vw ivld return reserve
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this vw ivld return reserve.
     *
     * @param modifiedDate the modified date of this vw ivld return reserve
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the account of this vw ivld return reserve.
     *
     * @return the account of this vw ivld return reserve
     */
    @AutoEscape
    public String getAccount();

    /**
     * Sets the account of this vw ivld return reserve.
     *
     * @param account the account of this vw ivld return reserve
     */
    public void setAccount(String account);

    /**
     * Returns the return reserve intf ID of this vw ivld return reserve.
     *
     * @return the return reserve intf ID of this vw ivld return reserve
     */
    @AutoEscape
    public String getReturnReserveIntfId();

    /**
     * Sets the return reserve intf ID of this vw ivld return reserve.
     *
     * @param returnReserveIntfId the return reserve intf ID of this vw ivld return reserve
     */
    public void setReturnReserveIntfId(String returnReserveIntfId);

    /**
     * Returns the source of this vw ivld return reserve.
     *
     * @return the source of this vw ivld return reserve
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this vw ivld return reserve.
     *
     * @param source the source of this vw ivld return reserve
     */
    public void setSource(String source);

    /**
     * Returns the created date of this vw ivld return reserve.
     *
     * @return the created date of this vw ivld return reserve
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this vw ivld return reserve.
     *
     * @param createdDate the created date of this vw ivld return reserve
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this vw ivld return reserve.
     *
     * @return the created by of this vw ivld return reserve
     */
    @AutoEscape
    public String getCreatedBy();

    /**
     * Sets the created by of this vw ivld return reserve.
     *
     * @param createdBy the created by of this vw ivld return reserve
     */
    public void setCreatedBy(String createdBy);

    /**
     * Returns the business unit of this vw ivld return reserve.
     *
     * @return the business unit of this vw ivld return reserve
     */
    @AutoEscape
    public String getBusinessUnit();

    /**
     * Sets the business unit of this vw ivld return reserve.
     *
     * @param businessUnit the business unit of this vw ivld return reserve
     */
    public void setBusinessUnit(String businessUnit);

    /**
     * Returns the business unit name of this vw ivld return reserve.
     *
     * @return the business unit name of this vw ivld return reserve
     */
    @AutoEscape
    public String getBusinessUnitName();

    /**
     * Sets the business unit name of this vw ivld return reserve.
     *
     * @param businessUnitName the business unit name of this vw ivld return reserve
     */
    public void setBusinessUnitName(String businessUnitName);

    /**
     * Returns the add chg del indicator of this vw ivld return reserve.
     *
     * @return the add chg del indicator of this vw ivld return reserve
     */
    @AutoEscape
    public String getAddChgDelIndicator();

    /**
     * Sets the add chg del indicator of this vw ivld return reserve.
     *
     * @param addChgDelIndicator the add chg del indicator of this vw ivld return reserve
     */
    public void setAddChgDelIndicator(String addChgDelIndicator);

    /**
     * Returns the error code of this vw ivld return reserve.
     *
     * @return the error code of this vw ivld return reserve
     */
    @AutoEscape
    public String getErrorCode();

    /**
     * Sets the error code of this vw ivld return reserve.
     *
     * @param errorCode the error code of this vw ivld return reserve
     */
    public void setErrorCode(String errorCode);

    /**
     * Returns the intf inserted date of this vw ivld return reserve.
     *
     * @return the intf inserted date of this vw ivld return reserve
     */
    public Date getIntfInsertedDate();

    /**
     * Sets the intf inserted date of this vw ivld return reserve.
     *
     * @param intfInsertedDate the intf inserted date of this vw ivld return reserve
     */
    public void setIntfInsertedDate(Date intfInsertedDate);

    /**
     * Returns the modified by of this vw ivld return reserve.
     *
     * @return the modified by of this vw ivld return reserve
     */
    @AutoEscape
    public String getModifiedBy();

    /**
     * Sets the modified by of this vw ivld return reserve.
     *
     * @param modifiedBy the modified by of this vw ivld return reserve
     */
    public void setModifiedBy(String modifiedBy);

    /**
     * Returns the item no of this vw ivld return reserve.
     *
     * @return the item no of this vw ivld return reserve
     */
    @AutoEscape
    public String getItemNo();

    /**
     * Sets the item no of this vw ivld return reserve.
     *
     * @param itemNo the item no of this vw ivld return reserve
     */
    public void setItemNo(String itemNo);

    /**
     * Returns the month of this vw ivld return reserve.
     *
     * @return the month of this vw ivld return reserve
     */
    @AutoEscape
    public String getMonth();

    /**
     * Sets the month of this vw ivld return reserve.
     *
     * @param month the month of this vw ivld return reserve
     */
    public void setMonth(String month);

    /**
     * Returns the reprocessed flag of this vw ivld return reserve.
     *
     * @return the reprocessed flag of this vw ivld return reserve
     */
    @AutoEscape
    public String getReprocessedFlag();

    /**
     * Sets the reprocessed flag of this vw ivld return reserve.
     *
     * @param reprocessedFlag the reprocessed flag of this vw ivld return reserve
     */
    public void setReprocessedFlag(String reprocessedFlag);

    /**
     * Returns the udc6 of this vw ivld return reserve.
     *
     * @return the udc6 of this vw ivld return reserve
     */
    @AutoEscape
    public String getUdc6();

    /**
     * Sets the udc6 of this vw ivld return reserve.
     *
     * @param udc6 the udc6 of this vw ivld return reserve
     */
    public void setUdc6(String udc6);

    /**
     * Returns the udc5 of this vw ivld return reserve.
     *
     * @return the udc5 of this vw ivld return reserve
     */
    @AutoEscape
    public String getUdc5();

    /**
     * Sets the udc5 of this vw ivld return reserve.
     *
     * @param udc5 the udc5 of this vw ivld return reserve
     */
    public void setUdc5(String udc5);

    /**
     * Returns the udc4 of this vw ivld return reserve.
     *
     * @return the udc4 of this vw ivld return reserve
     */
    @AutoEscape
    public String getUdc4();

    /**
     * Sets the udc4 of this vw ivld return reserve.
     *
     * @param udc4 the udc4 of this vw ivld return reserve
     */
    public void setUdc4(String udc4);

    /**
     * Returns the udc1 of this vw ivld return reserve.
     *
     * @return the udc1 of this vw ivld return reserve
     */
    @AutoEscape
    public String getUdc1();

    /**
     * Sets the udc1 of this vw ivld return reserve.
     *
     * @param udc1 the udc1 of this vw ivld return reserve
     */
    public void setUdc1(String udc1);

    /**
     * Returns the units of this vw ivld return reserve.
     *
     * @return the units of this vw ivld return reserve
     */
    @AutoEscape
    public String getUnits();

    /**
     * Sets the units of this vw ivld return reserve.
     *
     * @param units the units of this vw ivld return reserve
     */
    public void setUnits(String units);

    /**
     * Returns the udc2 of this vw ivld return reserve.
     *
     * @return the udc2 of this vw ivld return reserve
     */
    @AutoEscape
    public String getUdc2();

    /**
     * Sets the udc2 of this vw ivld return reserve.
     *
     * @param udc2 the udc2 of this vw ivld return reserve
     */
    public void setUdc2(String udc2);

    /**
     * Returns the udc3 of this vw ivld return reserve.
     *
     * @return the udc3 of this vw ivld return reserve
     */
    @AutoEscape
    public String getUdc3();

    /**
     * Sets the udc3 of this vw ivld return reserve.
     *
     * @param udc3 the udc3 of this vw ivld return reserve
     */
    public void setUdc3(String udc3);

    /**
     * Returns the reason for failure of this vw ivld return reserve.
     *
     * @return the reason for failure of this vw ivld return reserve
     */
    @AutoEscape
    public String getReasonForFailure();

    /**
     * Sets the reason for failure of this vw ivld return reserve.
     *
     * @param reasonForFailure the reason for failure of this vw ivld return reserve
     */
    public void setReasonForFailure(String reasonForFailure);

    /**
     * Returns the country of this vw ivld return reserve.
     *
     * @return the country of this vw ivld return reserve
     */
    @AutoEscape
    public String getCountry();

    /**
     * Sets the country of this vw ivld return reserve.
     *
     * @param country the country of this vw ivld return reserve
     */
    public void setCountry(String country);

    /**
     * Returns the company ID of this vw ivld return reserve.
     *
     * @return the company ID of this vw ivld return reserve
     */
    @AutoEscape
    public String getCompanyId();

    /**
     * Sets the company ID of this vw ivld return reserve.
     *
     * @param companyId the company ID of this vw ivld return reserve
     */
    public void setCompanyId(String companyId);

    /**
     * Returns the cost center of this vw ivld return reserve.
     *
     * @return the cost center of this vw ivld return reserve
     */
    @AutoEscape
    public String getCostCenter();

    /**
     * Sets the cost center of this vw ivld return reserve.
     *
     * @param costCenter the cost center of this vw ivld return reserve
     */
    public void setCostCenter(String costCenter);

    /**
     * Returns the gl company of this vw ivld return reserve.
     *
     * @return the gl company of this vw ivld return reserve
     */
    @AutoEscape
    public String getGlCompany();

    /**
     * Sets the gl company of this vw ivld return reserve.
     *
     * @param glCompany the gl company of this vw ivld return reserve
     */
    public void setGlCompany(String glCompany);

    /**
     * Returns the brand ID of this vw ivld return reserve.
     *
     * @return the brand ID of this vw ivld return reserve
     */
    @AutoEscape
    public String getBrandId();

    /**
     * Sets the brand ID of this vw ivld return reserve.
     *
     * @param brandId the brand ID of this vw ivld return reserve
     */
    public void setBrandId(String brandId);

    /**
     * Returns the future1 of this vw ivld return reserve.
     *
     * @return the future1 of this vw ivld return reserve
     */
    @AutoEscape
    public String getFuture1();

    /**
     * Sets the future1 of this vw ivld return reserve.
     *
     * @param future1 the future1 of this vw ivld return reserve
     */
    public void setFuture1(String future1);

    /**
     * Returns the future2 of this vw ivld return reserve.
     *
     * @return the future2 of this vw ivld return reserve
     */
    @AutoEscape
    public String getFuture2();

    /**
     * Sets the future2 of this vw ivld return reserve.
     *
     * @param future2 the future2 of this vw ivld return reserve
     */
    public void setFuture2(String future2);

    /**
     * Returns the amount of this vw ivld return reserve.
     *
     * @return the amount of this vw ivld return reserve
     */
    @AutoEscape
    public String getAmount();

    /**
     * Sets the amount of this vw ivld return reserve.
     *
     * @param amount the amount of this vw ivld return reserve
     */
    public void setAmount(String amount);

    /**
     * Returns the division of this vw ivld return reserve.
     *
     * @return the division of this vw ivld return reserve
     */
    @AutoEscape
    public String getDivision();

    /**
     * Sets the division of this vw ivld return reserve.
     *
     * @param division the division of this vw ivld return reserve
     */
    public void setDivision(String division);

    /**
     * Returns the company no of this vw ivld return reserve.
     *
     * @return the company no of this vw ivld return reserve
     */
    @AutoEscape
    public String getCompanyNo();

    /**
     * Sets the company no of this vw ivld return reserve.
     *
     * @param companyNo the company no of this vw ivld return reserve
     */
    public void setCompanyNo(String companyNo);

    /**
     * Returns the batch ID of this vw ivld return reserve.
     *
     * @return the batch ID of this vw ivld return reserve
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this vw ivld return reserve.
     *
     * @param batchId the batch ID of this vw ivld return reserve
     */
    public void setBatchId(String batchId);

    /**
     * Returns the item name of this vw ivld return reserve.
     *
     * @return the item name of this vw ivld return reserve
     */
    @AutoEscape
    public String getItemName();

    /**
     * Sets the item name of this vw ivld return reserve.
     *
     * @param itemName the item name of this vw ivld return reserve
     */
    public void setItemName(String itemName);

    /**
     * Returns the error field of this vw ivld return reserve.
     *
     * @return the error field of this vw ivld return reserve
     */
    @AutoEscape
    public String getErrorField();

    /**
     * Sets the error field of this vw ivld return reserve.
     *
     * @param errorField the error field of this vw ivld return reserve
     */
    public void setErrorField(String errorField);

    /**
     * Returns the check record of this vw ivld return reserve.
     *
     * @return the check record of this vw ivld return reserve
     */
    public boolean getCheckRecord();

    /**
     * Returns <code>true</code> if this vw ivld return reserve is check record.
     *
     * @return <code>true</code> if this vw ivld return reserve is check record; <code>false</code> otherwise
     */
    public boolean isCheckRecord();

    /**
     * Sets whether this vw ivld return reserve is check record.
     *
     * @param checkRecord the check record of this vw ivld return reserve
     */
    public void setCheckRecord(boolean checkRecord);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(VwIvldReturnReserve vwIvldReturnReserve);

    @Override
    public int hashCode();

    @Override
    public CacheModel<VwIvldReturnReserve> toCacheModel();

    @Override
    public VwIvldReturnReserve toEscapedModel();

    @Override
    public VwIvldReturnReserve toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
