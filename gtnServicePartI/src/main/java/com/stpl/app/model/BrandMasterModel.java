package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the BrandMaster service. Represents a row in the &quot;BRAND_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.BrandMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.BrandMasterImpl}.
 * </p>
 *
 * @author
 * @see BrandMaster
 * @see com.stpl.app.model.impl.BrandMasterImpl
 * @see com.stpl.app.model.impl.BrandMasterModelImpl
 * @generated
 */
public interface BrandMasterModel extends BaseModel<BrandMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a brand master model instance should use the {@link BrandMaster} interface instead.
     */

    /**
     * Returns the primary key of this brand master.
     *
     * @return the primary key of this brand master
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this brand master.
     *
     * @param primaryKey the primary key of this brand master
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the created by of this brand master.
     *
     * @return the created by of this brand master
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this brand master.
     *
     * @param createdBy the created by of this brand master
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the modified by of this brand master.
     *
     * @return the modified by of this brand master
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this brand master.
     *
     * @param modifiedBy the modified by of this brand master
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the created date of this brand master.
     *
     * @return the created date of this brand master
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this brand master.
     *
     * @param createdDate the created date of this brand master
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the brand master sid of this brand master.
     *
     * @return the brand master sid of this brand master
     */
    public int getBrandMasterSid();

    /**
     * Sets the brand master sid of this brand master.
     *
     * @param brandMasterSid the brand master sid of this brand master
     */
    public void setBrandMasterSid(int brandMasterSid);

    /**
     * Returns the batch ID of this brand master.
     *
     * @return the batch ID of this brand master
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this brand master.
     *
     * @param batchId the batch ID of this brand master
     */
    public void setBatchId(String batchId);

    /**
     * Returns the modified date of this brand master.
     *
     * @return the modified date of this brand master
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this brand master.
     *
     * @param modifiedDate the modified date of this brand master
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the brand ID of this brand master.
     *
     * @return the brand ID of this brand master
     */
    @AutoEscape
    public String getBrandId();

    /**
     * Sets the brand ID of this brand master.
     *
     * @param brandId the brand ID of this brand master
     */
    public void setBrandId(String brandId);

    /**
     * Returns the display brand of this brand master.
     *
     * @return the display brand of this brand master
     */
    @AutoEscape
    public String getDisplayBrand();

    /**
     * Sets the display brand of this brand master.
     *
     * @param displayBrand the display brand of this brand master
     */
    public void setDisplayBrand(String displayBrand);

    /**
     * Returns the record lock status of this brand master.
     *
     * @return the record lock status of this brand master
     */
    public boolean getRecordLockStatus();

    /**
     * Returns <code>true</code> if this brand master is record lock status.
     *
     * @return <code>true</code> if this brand master is record lock status; <code>false</code> otherwise
     */
    public boolean isRecordLockStatus();

    /**
     * Sets whether this brand master is record lock status.
     *
     * @param recordLockStatus the record lock status of this brand master
     */
    public void setRecordLockStatus(boolean recordLockStatus);

    /**
     * Returns the brand name of this brand master.
     *
     * @return the brand name of this brand master
     */
    @AutoEscape
    public String getBrandName();

    /**
     * Sets the brand name of this brand master.
     *
     * @param brandName the brand name of this brand master
     */
    public void setBrandName(String brandName);

    /**
     * Returns the brand desc of this brand master.
     *
     * @return the brand desc of this brand master
     */
    @AutoEscape
    public String getBrandDesc();

    /**
     * Sets the brand desc of this brand master.
     *
     * @param brandDesc the brand desc of this brand master
     */
    public void setBrandDesc(String brandDesc);

    /**
     * Returns the source of this brand master.
     *
     * @return the source of this brand master
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this brand master.
     *
     * @param source the source of this brand master
     */
    public void setSource(String source);

    /**
     * Returns the inbound status of this brand master.
     *
     * @return the inbound status of this brand master
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this brand master.
     *
     * @param inboundStatus the inbound status of this brand master
     */
    public void setInboundStatus(String inboundStatus);

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
    public int compareTo(BrandMaster brandMaster);

    @Override
    public int hashCode();

    @Override
    public CacheModel<BrandMaster> toCacheModel();

    @Override
    public BrandMaster toEscapedModel();

    @Override
    public BrandMaster toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
