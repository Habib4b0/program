package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the NmPpaProjectionMaster service. Represents a row in the &quot;NM_PPA_PROJECTION_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.NmPpaProjectionMasterImpl}.
 * </p>
 *
 * @author
 * @see NmPpaProjectionMaster
 * @see com.stpl.app.model.impl.NmPpaProjectionMasterImpl
 * @see com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl
 * @generated
 */
public interface NmPpaProjectionMasterModel extends BaseModel<NmPpaProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a nm ppa projection master model instance should use the {@link NmPpaProjectionMaster} interface instead.
     */

    /**
     * Returns the primary key of this nm ppa projection master.
     *
     * @return the primary key of this nm ppa projection master
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this nm ppa projection master.
     *
     * @param primaryKey the primary key of this nm ppa projection master
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the check record of this nm ppa projection master.
     *
     * @return the check record of this nm ppa projection master
     */
    public boolean getCheckRecord();

    /**
     * Returns <code>true</code> if this nm ppa projection master is check record.
     *
     * @return <code>true</code> if this nm ppa projection master is check record; <code>false</code> otherwise
     */
    public boolean isCheckRecord();

    /**
     * Sets whether this nm ppa projection master is check record.
     *
     * @param checkRecord the check record of this nm ppa projection master
     */
    public void setCheckRecord(boolean checkRecord);

    /**
     * Returns the user group of this nm ppa projection master.
     *
     * @return the user group of this nm ppa projection master
     */
    @AutoEscape
    public String getUserGroup();

    /**
     * Sets the user group of this nm ppa projection master.
     *
     * @param userGroup the user group of this nm ppa projection master
     */
    public void setUserGroup(String userGroup);

    /**
     * Returns the projection details sid of this nm ppa projection master.
     *
     * @return the projection details sid of this nm ppa projection master
     */
    public int getProjectionDetailsSid();

    /**
     * Sets the projection details sid of this nm ppa projection master.
     *
     * @param projectionDetailsSid the projection details sid of this nm ppa projection master
     */
    public void setProjectionDetailsSid(int projectionDetailsSid);

    /**
     * Returns the price basis of this nm ppa projection master.
     *
     * @return the price basis of this nm ppa projection master
     */
    @AutoEscape
    public String getPriceBasis();

    /**
     * Sets the price basis of this nm ppa projection master.
     *
     * @param priceBasis the price basis of this nm ppa projection master
     */
    public void setPriceBasis(String priceBasis);

    /**
     * Returns the price protection end date of this nm ppa projection master.
     *
     * @return the price protection end date of this nm ppa projection master
     */
    public Date getPriceProtectionEndDate();

    /**
     * Sets the price protection end date of this nm ppa projection master.
     *
     * @param priceProtectionEndDate the price protection end date of this nm ppa projection master
     */
    public void setPriceProtectionEndDate(Date priceProtectionEndDate);

    /**
     * Returns the price protection start date of this nm ppa projection master.
     *
     * @return the price protection start date of this nm ppa projection master
     */
    public Date getPriceProtectionStartDate();

    /**
     * Sets the price protection start date of this nm ppa projection master.
     *
     * @param priceProtectionStartDate the price protection start date of this nm ppa projection master
     */
    public void setPriceProtectionStartDate(Date priceProtectionStartDate);

    /**
     * Returns the actual price cap of this nm ppa projection master.
     *
     * @return the actual price cap of this nm ppa projection master
     */
    public double getActualPriceCap();

    /**
     * Sets the actual price cap of this nm ppa projection master.
     *
     * @param actualPriceCap the actual price cap of this nm ppa projection master
     */
    public void setActualPriceCap(double actualPriceCap);

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
    public int compareTo(NmPpaProjectionMaster nmPpaProjectionMaster);

    @Override
    public int hashCode();

    @Override
    public CacheModel<NmPpaProjectionMaster> toCacheModel();

    @Override
    public NmPpaProjectionMaster toEscapedModel();

    @Override
    public NmPpaProjectionMaster toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
