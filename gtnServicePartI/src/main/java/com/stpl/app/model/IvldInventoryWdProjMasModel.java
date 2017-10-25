package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the IvldInventoryWdProjMas service. Represents a row in the &quot;IVLD_INVENTORY_WD_PROJ_MAS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.IvldInventoryWdProjMasImpl}.
 * </p>
 *
 * @author
 * @see IvldInventoryWdProjMas
 * @see com.stpl.app.model.impl.IvldInventoryWdProjMasImpl
 * @see com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl
 * @generated
 */
public interface IvldInventoryWdProjMasModel extends BaseModel<IvldInventoryWdProjMas> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a ivld inventory wd proj mas model instance should use the {@link IvldInventoryWdProjMas} interface instead.
     */

    /**
     * Returns the primary key of this ivld inventory wd proj mas.
     *
     * @return the primary key of this ivld inventory wd proj mas
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this ivld inventory wd proj mas.
     *
     * @param primaryKey the primary key of this ivld inventory wd proj mas
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the inventory wd proj mas intf ID of this ivld inventory wd proj mas.
     *
     * @return the inventory wd proj mas intf ID of this ivld inventory wd proj mas
     */
    public int getInventoryWdProjMasIntfId();

    /**
     * Sets the inventory wd proj mas intf ID of this ivld inventory wd proj mas.
     *
     * @param inventoryWdProjMasIntfId the inventory wd proj mas intf ID of this ivld inventory wd proj mas
     */
    public void setInventoryWdProjMasIntfId(int inventoryWdProjMasIntfId);

    /**
     * Returns the week of this ivld inventory wd proj mas.
     *
     * @return the week of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getWeek();

    /**
     * Sets the week of this ivld inventory wd proj mas.
     *
     * @param week the week of this ivld inventory wd proj mas
     */
    public void setWeek(String week);

    /**
     * Returns the units withdrawn of this ivld inventory wd proj mas.
     *
     * @return the units withdrawn of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getUnitsWithdrawn();

    /**
     * Sets the units withdrawn of this ivld inventory wd proj mas.
     *
     * @param unitsWithdrawn the units withdrawn of this ivld inventory wd proj mas
     */
    public void setUnitsWithdrawn(String unitsWithdrawn);

    /**
     * Returns the reason for failure of this ivld inventory wd proj mas.
     *
     * @return the reason for failure of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getReasonForFailure();

    /**
     * Sets the reason for failure of this ivld inventory wd proj mas.
     *
     * @param reasonForFailure the reason for failure of this ivld inventory wd proj mas
     */
    public void setReasonForFailure(String reasonForFailure);

    /**
     * Returns the country of this ivld inventory wd proj mas.
     *
     * @return the country of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getCountry();

    /**
     * Sets the country of this ivld inventory wd proj mas.
     *
     * @param country the country of this ivld inventory wd proj mas
     */
    public void setCountry(String country);

    /**
     * Returns the year of this ivld inventory wd proj mas.
     *
     * @return the year of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getYear();

    /**
     * Sets the year of this ivld inventory wd proj mas.
     *
     * @param year the year of this ivld inventory wd proj mas
     */
    public void setYear(String year);

    /**
     * Returns the item ID of this ivld inventory wd proj mas.
     *
     * @return the item ID of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getItemId();

    /**
     * Sets the item ID of this ivld inventory wd proj mas.
     *
     * @param itemId the item ID of this ivld inventory wd proj mas
     */
    public void setItemId(String itemId);

    /**
     * Returns the modified date of this ivld inventory wd proj mas.
     *
     * @return the modified date of this ivld inventory wd proj mas
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this ivld inventory wd proj mas.
     *
     * @param modifiedDate the modified date of this ivld inventory wd proj mas
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the organization key of this ivld inventory wd proj mas.
     *
     * @return the organization key of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getOrganizationKey();

    /**
     * Sets the organization key of this ivld inventory wd proj mas.
     *
     * @param organizationKey the organization key of this ivld inventory wd proj mas
     */
    public void setOrganizationKey(String organizationKey);

    /**
     * Returns the item identifier code qualifier of this ivld inventory wd proj mas.
     *
     * @return the item identifier code qualifier of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getItemIdentifierCodeQualifier();

    /**
     * Sets the item identifier code qualifier of this ivld inventory wd proj mas.
     *
     * @param itemIdentifierCodeQualifier the item identifier code qualifier of this ivld inventory wd proj mas
     */
    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier);

    /**
     * Returns the source of this ivld inventory wd proj mas.
     *
     * @return the source of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this ivld inventory wd proj mas.
     *
     * @param source the source of this ivld inventory wd proj mas
     */
    public void setSource(String source);

    /**
     * Returns the created date of this ivld inventory wd proj mas.
     *
     * @return the created date of this ivld inventory wd proj mas
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this ivld inventory wd proj mas.
     *
     * @param createdDate the created date of this ivld inventory wd proj mas
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this ivld inventory wd proj mas.
     *
     * @return the created by of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getCreatedBy();

    /**
     * Sets the created by of this ivld inventory wd proj mas.
     *
     * @param createdBy the created by of this ivld inventory wd proj mas
     */
    public void setCreatedBy(String createdBy);

    /**
     * Returns the day of this ivld inventory wd proj mas.
     *
     * @return the day of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getDay();

    /**
     * Sets the day of this ivld inventory wd proj mas.
     *
     * @param day the day of this ivld inventory wd proj mas
     */
    public void setDay(String day);

    /**
     * Returns the forecast ver of this ivld inventory wd proj mas.
     *
     * @return the forecast ver of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getForecastVer();

    /**
     * Sets the forecast ver of this ivld inventory wd proj mas.
     *
     * @param forecastVer the forecast ver of this ivld inventory wd proj mas
     */
    public void setForecastVer(String forecastVer);

    /**
     * Returns the batch ID of this ivld inventory wd proj mas.
     *
     * @return the batch ID of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this ivld inventory wd proj mas.
     *
     * @param batchId the batch ID of this ivld inventory wd proj mas
     */
    public void setBatchId(String batchId);

    /**
     * Returns the add chg del indicator of this ivld inventory wd proj mas.
     *
     * @return the add chg del indicator of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getAddChgDelIndicator();

    /**
     * Sets the add chg del indicator of this ivld inventory wd proj mas.
     *
     * @param addChgDelIndicator the add chg del indicator of this ivld inventory wd proj mas
     */
    public void setAddChgDelIndicator(String addChgDelIndicator);

    /**
     * Returns the item identifier of this ivld inventory wd proj mas.
     *
     * @return the item identifier of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getItemIdentifier();

    /**
     * Sets the item identifier of this ivld inventory wd proj mas.
     *
     * @param itemIdentifier the item identifier of this ivld inventory wd proj mas
     */
    public void setItemIdentifier(String itemIdentifier);

    /**
     * Returns the error field of this ivld inventory wd proj mas.
     *
     * @return the error field of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getErrorField();

    /**
     * Sets the error field of this ivld inventory wd proj mas.
     *
     * @param errorField the error field of this ivld inventory wd proj mas
     */
    public void setErrorField(String errorField);

    /**
     * Returns the error code of this ivld inventory wd proj mas.
     *
     * @return the error code of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getErrorCode();

    /**
     * Sets the error code of this ivld inventory wd proj mas.
     *
     * @param errorCode the error code of this ivld inventory wd proj mas
     */
    public void setErrorCode(String errorCode);

    /**
     * Returns the intf inserted date of this ivld inventory wd proj mas.
     *
     * @return the intf inserted date of this ivld inventory wd proj mas
     */
    public Date getIntfInsertedDate();

    /**
     * Sets the intf inserted date of this ivld inventory wd proj mas.
     *
     * @param intfInsertedDate the intf inserted date of this ivld inventory wd proj mas
     */
    public void setIntfInsertedDate(Date intfInsertedDate);

    /**
     * Returns the modified by of this ivld inventory wd proj mas.
     *
     * @return the modified by of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getModifiedBy();

    /**
     * Sets the modified by of this ivld inventory wd proj mas.
     *
     * @param modifiedBy the modified by of this ivld inventory wd proj mas
     */
    public void setModifiedBy(String modifiedBy);

    /**
     * Returns the ivld inventory wd proj mas sid of this ivld inventory wd proj mas.
     *
     * @return the ivld inventory wd proj mas sid of this ivld inventory wd proj mas
     */
    public int getIvldInventoryWdProjMasSid();

    /**
     * Sets the ivld inventory wd proj mas sid of this ivld inventory wd proj mas.
     *
     * @param ivldInventoryWdProjMasSid the ivld inventory wd proj mas sid of this ivld inventory wd proj mas
     */
    public void setIvldInventoryWdProjMasSid(int ivldInventoryWdProjMasSid);

    /**
     * Returns the month of this ivld inventory wd proj mas.
     *
     * @return the month of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getMonth();

    /**
     * Sets the month of this ivld inventory wd proj mas.
     *
     * @param month the month of this ivld inventory wd proj mas
     */
    public void setMonth(String month);

    /**
     * Returns the reprocessed flag of this ivld inventory wd proj mas.
     *
     * @return the reprocessed flag of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getReprocessedFlag();

    /**
     * Sets the reprocessed flag of this ivld inventory wd proj mas.
     *
     * @param reprocessedFlag the reprocessed flag of this ivld inventory wd proj mas
     */
    public void setReprocessedFlag(String reprocessedFlag);

    /**
     * Returns the forecast name of this ivld inventory wd proj mas.
     *
     * @return the forecast name of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getForecastName();

    /**
     * Sets the forecast name of this ivld inventory wd proj mas.
     *
     * @param forecastName the forecast name of this ivld inventory wd proj mas
     */
    public void setForecastName(String forecastName);

    /**
     * Returns the amount withdrawn of this ivld inventory wd proj mas.
     *
     * @return the amount withdrawn of this ivld inventory wd proj mas
     */
    @AutoEscape
    public String getAmountWithdrawn();

    /**
     * Sets the amount withdrawn of this ivld inventory wd proj mas.
     *
     * @param amountWithdrawn the amount withdrawn of this ivld inventory wd proj mas
     */
    public void setAmountWithdrawn(String amountWithdrawn);

    /**
     * Returns the check record of this ivld inventory wd proj mas.
     *
     * @return the check record of this ivld inventory wd proj mas
     */
    public boolean getCheckRecord();

    /**
     * Returns <code>true</code> if this ivld inventory wd proj mas is check record.
     *
     * @return <code>true</code> if this ivld inventory wd proj mas is check record; <code>false</code> otherwise
     */
    public boolean isCheckRecord();

    /**
     * Sets whether this ivld inventory wd proj mas is check record.
     *
     * @param checkRecord the check record of this ivld inventory wd proj mas
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
    public int compareTo(IvldInventoryWdProjMas ivldInventoryWdProjMas);

    @Override
    public int hashCode();

    @Override
    public CacheModel<IvldInventoryWdProjMas> toCacheModel();

    @Override
    public IvldInventoryWdProjMas toEscapedModel();

    @Override
    public IvldInventoryWdProjMas toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
