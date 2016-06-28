package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the VwIvldInventoryWdActualProjMas service. Represents a row in the &quot;VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasImpl}.
 * </p>
 *
 * @author
 * @see VwIvldInventoryWdActualProjMas
 * @see com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasImpl
 * @see com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl
 * @generated
 */
public interface VwIvldInventoryWdActualProjMasModel extends BaseModel<VwIvldInventoryWdActualProjMas> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a vw ivld inventory wd actual proj mas model instance should use the {@link VwIvldInventoryWdActualProjMas} interface instead.
     */

    /**
     * Returns the primary key of this vw ivld inventory wd actual proj mas.
     *
     * @return the primary key of this vw ivld inventory wd actual proj mas
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this vw ivld inventory wd actual proj mas.
     *
     * @param primaryKey the primary key of this vw ivld inventory wd actual proj mas
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the ivld inventory wd actual proj mas sid of this vw ivld inventory wd actual proj mas.
     *
     * @return the ivld inventory wd actual proj mas sid of this vw ivld inventory wd actual proj mas
     */
    public int getIvldInventoryWdActualProjMasSid();

    /**
     * Sets the ivld inventory wd actual proj mas sid of this vw ivld inventory wd actual proj mas.
     *
     * @param ivldInventoryWdActualProjMasSid the ivld inventory wd actual proj mas sid of this vw ivld inventory wd actual proj mas
     */
    public void setIvldInventoryWdActualProjMasSid(
        int ivldInventoryWdActualProjMasSid);

    /**
     * Returns the quantity on order of this vw ivld inventory wd actual proj mas.
     *
     * @return the quantity on order of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getQuantityOnOrder();

    /**
     * Sets the quantity on order of this vw ivld inventory wd actual proj mas.
     *
     * @param quantityOnOrder the quantity on order of this vw ivld inventory wd actual proj mas
     */
    public void setQuantityOnOrder(String quantityOnOrder);

    /**
     * Returns the week of this vw ivld inventory wd actual proj mas.
     *
     * @return the week of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getWeek();

    /**
     * Sets the week of this vw ivld inventory wd actual proj mas.
     *
     * @param week the week of this vw ivld inventory wd actual proj mas
     */
    public void setWeek(String week);

    /**
     * Returns the price of this vw ivld inventory wd actual proj mas.
     *
     * @return the price of this vw ivld inventory wd actual proj mas
     */
    public double getPrice();

    /**
     * Sets the price of this vw ivld inventory wd actual proj mas.
     *
     * @param price the price of this vw ivld inventory wd actual proj mas
     */
    public void setPrice(double price);

    /**
     * Returns the amount on hand of this vw ivld inventory wd actual proj mas.
     *
     * @return the amount on hand of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getAmountOnHand();

    /**
     * Sets the amount on hand of this vw ivld inventory wd actual proj mas.
     *
     * @param amountOnHand the amount on hand of this vw ivld inventory wd actual proj mas
     */
    public void setAmountOnHand(String amountOnHand);

    /**
     * Returns the is master of this vw ivld inventory wd actual proj mas.
     *
     * @return the is master of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getIsMaster();

    /**
     * Sets the is master of this vw ivld inventory wd actual proj mas.
     *
     * @param isMaster the is master of this vw ivld inventory wd actual proj mas
     */
    public void setIsMaster(String isMaster);

    /**
     * Returns the company name of this vw ivld inventory wd actual proj mas.
     *
     * @return the company name of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getCompanyName();

    /**
     * Sets the company name of this vw ivld inventory wd actual proj mas.
     *
     * @param companyName the company name of this vw ivld inventory wd actual proj mas
     */
    public void setCompanyName(String companyName);

    /**
     * Returns the year of this vw ivld inventory wd actual proj mas.
     *
     * @return the year of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getYear();

    /**
     * Sets the year of this vw ivld inventory wd actual proj mas.
     *
     * @param year the year of this vw ivld inventory wd actual proj mas
     */
    public void setYear(String year);

    /**
     * Returns the item ID of this vw ivld inventory wd actual proj mas.
     *
     * @return the item ID of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getItemId();

    /**
     * Sets the item ID of this vw ivld inventory wd actual proj mas.
     *
     * @param itemId the item ID of this vw ivld inventory wd actual proj mas
     */
    public void setItemId(String itemId);

    /**
     * Returns the modified date of this vw ivld inventory wd actual proj mas.
     *
     * @return the modified date of this vw ivld inventory wd actual proj mas
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this vw ivld inventory wd actual proj mas.
     *
     * @param modifiedDate the modified date of this vw ivld inventory wd actual proj mas
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the organization key of this vw ivld inventory wd actual proj mas.
     *
     * @return the organization key of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getOrganizationKey();

    /**
     * Sets the organization key of this vw ivld inventory wd actual proj mas.
     *
     * @param organizationKey the organization key of this vw ivld inventory wd actual proj mas
     */
    public void setOrganizationKey(String organizationKey);

    /**
     * Returns the source of this vw ivld inventory wd actual proj mas.
     *
     * @return the source of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this vw ivld inventory wd actual proj mas.
     *
     * @param source the source of this vw ivld inventory wd actual proj mas
     */
    public void setSource(String source);

    /**
     * Returns the created by of this vw ivld inventory wd actual proj mas.
     *
     * @return the created by of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getCreatedBy();

    /**
     * Sets the created by of this vw ivld inventory wd actual proj mas.
     *
     * @param createdBy the created by of this vw ivld inventory wd actual proj mas
     */
    public void setCreatedBy(String createdBy);

    /**
     * Returns the created date of this vw ivld inventory wd actual proj mas.
     *
     * @return the created date of this vw ivld inventory wd actual proj mas
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this vw ivld inventory wd actual proj mas.
     *
     * @param createdDate the created date of this vw ivld inventory wd actual proj mas
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the day of this vw ivld inventory wd actual proj mas.
     *
     * @return the day of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getDay();

    /**
     * Sets the day of this vw ivld inventory wd actual proj mas.
     *
     * @param day the day of this vw ivld inventory wd actual proj mas
     */
    public void setDay(String day);

    /**
     * Returns the add chg del indicator of this vw ivld inventory wd actual proj mas.
     *
     * @return the add chg del indicator of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getAddChgDelIndicator();

    /**
     * Sets the add chg del indicator of this vw ivld inventory wd actual proj mas.
     *
     * @param addChgDelIndicator the add chg del indicator of this vw ivld inventory wd actual proj mas
     */
    public void setAddChgDelIndicator(String addChgDelIndicator);

    /**
     * Returns the units on hand of this vw ivld inventory wd actual proj mas.
     *
     * @return the units on hand of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getUnitsOnHand();

    /**
     * Sets the units on hand of this vw ivld inventory wd actual proj mas.
     *
     * @param unitsOnHand the units on hand of this vw ivld inventory wd actual proj mas
     */
    public void setUnitsOnHand(String unitsOnHand);

    /**
     * Returns the amount received of this vw ivld inventory wd actual proj mas.
     *
     * @return the amount received of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getAmountReceived();

    /**
     * Sets the amount received of this vw ivld inventory wd actual proj mas.
     *
     * @param amountReceived the amount received of this vw ivld inventory wd actual proj mas
     */
    public void setAmountReceived(String amountReceived);

    /**
     * Returns the error code of this vw ivld inventory wd actual proj mas.
     *
     * @return the error code of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getErrorCode();

    /**
     * Sets the error code of this vw ivld inventory wd actual proj mas.
     *
     * @param errorCode the error code of this vw ivld inventory wd actual proj mas
     */
    public void setErrorCode(String errorCode);

    /**
     * Returns the intf inserted date of this vw ivld inventory wd actual proj mas.
     *
     * @return the intf inserted date of this vw ivld inventory wd actual proj mas
     */
    public Date getIntfInsertedDate();

    /**
     * Sets the intf inserted date of this vw ivld inventory wd actual proj mas.
     *
     * @param intfInsertedDate the intf inserted date of this vw ivld inventory wd actual proj mas
     */
    public void setIntfInsertedDate(Date intfInsertedDate);

    /**
     * Returns the modified by of this vw ivld inventory wd actual proj mas.
     *
     * @return the modified by of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getModifiedBy();

    /**
     * Sets the modified by of this vw ivld inventory wd actual proj mas.
     *
     * @param modifiedBy the modified by of this vw ivld inventory wd actual proj mas
     */
    public void setModifiedBy(String modifiedBy);

    /**
     * Returns the month of this vw ivld inventory wd actual proj mas.
     *
     * @return the month of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getMonth();

    /**
     * Sets the month of this vw ivld inventory wd actual proj mas.
     *
     * @param month the month of this vw ivld inventory wd actual proj mas
     */
    public void setMonth(String month);

    /**
     * Returns the reprocessed flag of this vw ivld inventory wd actual proj mas.
     *
     * @return the reprocessed flag of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getReprocessedFlag();

    /**
     * Sets the reprocessed flag of this vw ivld inventory wd actual proj mas.
     *
     * @param reprocessedFlag the reprocessed flag of this vw ivld inventory wd actual proj mas
     */
    public void setReprocessedFlag(String reprocessedFlag);

    /**
     * Returns the amount withdrawn of this vw ivld inventory wd actual proj mas.
     *
     * @return the amount withdrawn of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getAmountWithdrawn();

    /**
     * Sets the amount withdrawn of this vw ivld inventory wd actual proj mas.
     *
     * @param amountWithdrawn the amount withdrawn of this vw ivld inventory wd actual proj mas
     */
    public void setAmountWithdrawn(String amountWithdrawn);

    /**
     * Returns the quantity received of this vw ivld inventory wd actual proj mas.
     *
     * @return the quantity received of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getQuantityReceived();

    /**
     * Sets the quantity received of this vw ivld inventory wd actual proj mas.
     *
     * @param quantityReceived the quantity received of this vw ivld inventory wd actual proj mas
     */
    public void setQuantityReceived(String quantityReceived);

    /**
     * Returns the units withdrawn of this vw ivld inventory wd actual proj mas.
     *
     * @return the units withdrawn of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getUnitsWithdrawn();

    /**
     * Sets the units withdrawn of this vw ivld inventory wd actual proj mas.
     *
     * @param unitsWithdrawn the units withdrawn of this vw ivld inventory wd actual proj mas
     */
    public void setUnitsWithdrawn(String unitsWithdrawn);

    /**
     * Returns the reason for failure of this vw ivld inventory wd actual proj mas.
     *
     * @return the reason for failure of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getReasonForFailure();

    /**
     * Sets the reason for failure of this vw ivld inventory wd actual proj mas.
     *
     * @param reasonForFailure the reason for failure of this vw ivld inventory wd actual proj mas
     */
    public void setReasonForFailure(String reasonForFailure);

    /**
     * Returns the country of this vw ivld inventory wd actual proj mas.
     *
     * @return the country of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getCountry();

    /**
     * Sets the country of this vw ivld inventory wd actual proj mas.
     *
     * @param country the country of this vw ivld inventory wd actual proj mas
     */
    public void setCountry(String country);

    /**
     * Returns the company ID of this vw ivld inventory wd actual proj mas.
     *
     * @return the company ID of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getCompanyId();

    /**
     * Sets the company ID of this vw ivld inventory wd actual proj mas.
     *
     * @param companyId the company ID of this vw ivld inventory wd actual proj mas
     */
    public void setCompanyId(String companyId);

    /**
     * Returns the is forecast of this vw ivld inventory wd actual proj mas.
     *
     * @return the is forecast of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getIsForecast();

    /**
     * Sets the is forecast of this vw ivld inventory wd actual proj mas.
     *
     * @param isForecast the is forecast of this vw ivld inventory wd actual proj mas
     */
    public void setIsForecast(String isForecast);

    /**
     * Returns the inventory wd actual proj mas intf ID of this vw ivld inventory wd actual proj mas.
     *
     * @return the inventory wd actual proj mas intf ID of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getInventoryWdActualProjMasIntfId();

    /**
     * Sets the inventory wd actual proj mas intf ID of this vw ivld inventory wd actual proj mas.
     *
     * @param inventoryWdActualProjMasIntfId the inventory wd actual proj mas intf ID of this vw ivld inventory wd actual proj mas
     */
    public void setInventoryWdActualProjMasIntfId(
        String inventoryWdActualProjMasIntfId);

    /**
     * Returns the forecast ver of this vw ivld inventory wd actual proj mas.
     *
     * @return the forecast ver of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getForecastVer();

    /**
     * Sets the forecast ver of this vw ivld inventory wd actual proj mas.
     *
     * @param forecastVer the forecast ver of this vw ivld inventory wd actual proj mas
     */
    public void setForecastVer(String forecastVer);

    /**
     * Returns the batch ID of this vw ivld inventory wd actual proj mas.
     *
     * @return the batch ID of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this vw ivld inventory wd actual proj mas.
     *
     * @param batchId the batch ID of this vw ivld inventory wd actual proj mas
     */
    public void setBatchId(String batchId);

    /**
     * Returns the item name of this vw ivld inventory wd actual proj mas.
     *
     * @return the item name of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getItemName();

    /**
     * Sets the item name of this vw ivld inventory wd actual proj mas.
     *
     * @param itemName the item name of this vw ivld inventory wd actual proj mas
     */
    public void setItemName(String itemName);

    /**
     * Returns the error field of this vw ivld inventory wd actual proj mas.
     *
     * @return the error field of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getErrorField();

    /**
     * Sets the error field of this vw ivld inventory wd actual proj mas.
     *
     * @param errorField the error field of this vw ivld inventory wd actual proj mas
     */
    public void setErrorField(String errorField);

    /**
     * Returns the amount on order of this vw ivld inventory wd actual proj mas.
     *
     * @return the amount on order of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getAmountOnOrder();

    /**
     * Sets the amount on order of this vw ivld inventory wd actual proj mas.
     *
     * @param amountOnOrder the amount on order of this vw ivld inventory wd actual proj mas
     */
    public void setAmountOnOrder(String amountOnOrder);

    /**
     * Returns the forecast name of this vw ivld inventory wd actual proj mas.
     *
     * @return the forecast name of this vw ivld inventory wd actual proj mas
     */
    @AutoEscape
    public String getForecastName();

    /**
     * Sets the forecast name of this vw ivld inventory wd actual proj mas.
     *
     * @param forecastName the forecast name of this vw ivld inventory wd actual proj mas
     */
    public void setForecastName(String forecastName);

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
    public int compareTo(
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas);

    @Override
    public int hashCode();

    @Override
    public CacheModel<VwIvldInventoryWdActualProjMas> toCacheModel();

    @Override
    public VwIvldInventoryWdActualProjMas toEscapedModel();

    @Override
    public VwIvldInventoryWdActualProjMas toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
