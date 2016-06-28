package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the IvldAverageShelfLife service. Represents a row in the &quot;IVLD_AVERAGE_SHELF_LIFE&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.IvldAverageShelfLifeImpl}.
 * </p>
 *
 * @author
 * @see IvldAverageShelfLife
 * @see com.stpl.app.model.impl.IvldAverageShelfLifeImpl
 * @see com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl
 * @generated
 */
public interface IvldAverageShelfLifeModel extends BaseModel<IvldAverageShelfLife> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a ivld average shelf life model instance should use the {@link IvldAverageShelfLife} interface instead.
     */

    /**
     * Returns the primary key of this ivld average shelf life.
     *
     * @return the primary key of this ivld average shelf life
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this ivld average shelf life.
     *
     * @param primaryKey the primary key of this ivld average shelf life
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the reason for failure of this ivld average shelf life.
     *
     * @return the reason for failure of this ivld average shelf life
     */
    @AutoEscape
    public String getReasonForFailure();

    /**
     * Sets the reason for failure of this ivld average shelf life.
     *
     * @param reasonForFailure the reason for failure of this ivld average shelf life
     */
    public void setReasonForFailure(String reasonForFailure);

    /**
     * Returns the ivld average shelf life sid of this ivld average shelf life.
     *
     * @return the ivld average shelf life sid of this ivld average shelf life
     */
    public int getIvldAverageShelfLifeSid();

    /**
     * Sets the ivld average shelf life sid of this ivld average shelf life.
     *
     * @param ivldAverageShelfLifeSid the ivld average shelf life sid of this ivld average shelf life
     */
    public void setIvldAverageShelfLifeSid(int ivldAverageShelfLifeSid);

    /**
     * Returns the item ID of this ivld average shelf life.
     *
     * @return the item ID of this ivld average shelf life
     */
    @AutoEscape
    public String getItemId();

    /**
     * Sets the item ID of this ivld average shelf life.
     *
     * @param itemId the item ID of this ivld average shelf life
     */
    public void setItemId(String itemId);

    /**
     * Returns the avg shelf life of this ivld average shelf life.
     *
     * @return the avg shelf life of this ivld average shelf life
     */
    @AutoEscape
    public String getAvgShelfLife();

    /**
     * Sets the avg shelf life of this ivld average shelf life.
     *
     * @param avgShelfLife the avg shelf life of this ivld average shelf life
     */
    public void setAvgShelfLife(String avgShelfLife);

    /**
     * Returns the modified date of this ivld average shelf life.
     *
     * @return the modified date of this ivld average shelf life
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this ivld average shelf life.
     *
     * @param modifiedDate the modified date of this ivld average shelf life
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the created by of this ivld average shelf life.
     *
     * @return the created by of this ivld average shelf life
     */
    @AutoEscape
    public String getCreatedBy();

    /**
     * Sets the created by of this ivld average shelf life.
     *
     * @param createdBy the created by of this ivld average shelf life
     */
    public void setCreatedBy(String createdBy);

    /**
     * Returns the created date of this ivld average shelf life.
     *
     * @return the created date of this ivld average shelf life
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this ivld average shelf life.
     *
     * @param createdDate the created date of this ivld average shelf life
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the source of this ivld average shelf life.
     *
     * @return the source of this ivld average shelf life
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this ivld average shelf life.
     *
     * @param source the source of this ivld average shelf life
     */
    public void setSource(String source);

    /**
     * Returns the item ID type of this ivld average shelf life.
     *
     * @return the item ID type of this ivld average shelf life
     */
    @AutoEscape
    public String getItemIdType();

    /**
     * Sets the item ID type of this ivld average shelf life.
     *
     * @param itemIdType the item ID type of this ivld average shelf life
     */
    public void setItemIdType(String itemIdType);

    /**
     * Returns the batch ID of this ivld average shelf life.
     *
     * @return the batch ID of this ivld average shelf life
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this ivld average shelf life.
     *
     * @param batchId the batch ID of this ivld average shelf life
     */
    public void setBatchId(String batchId);

    /**
     * Returns the add chg del indicator of this ivld average shelf life.
     *
     * @return the add chg del indicator of this ivld average shelf life
     */
    @AutoEscape
    public String getAddChgDelIndicator();

    /**
     * Sets the add chg del indicator of this ivld average shelf life.
     *
     * @param addChgDelIndicator the add chg del indicator of this ivld average shelf life
     */
    public void setAddChgDelIndicator(String addChgDelIndicator);

    /**
     * Returns the average shelf life intfid of this ivld average shelf life.
     *
     * @return the average shelf life intfid of this ivld average shelf life
     */
    @AutoEscape
    public String getAverageShelfLifeIntfid();

    /**
     * Sets the average shelf life intfid of this ivld average shelf life.
     *
     * @param averageShelfLifeIntfid the average shelf life intfid of this ivld average shelf life
     */
    public void setAverageShelfLifeIntfid(String averageShelfLifeIntfid);

    /**
     * Returns the error field of this ivld average shelf life.
     *
     * @return the error field of this ivld average shelf life
     */
    @AutoEscape
    public String getErrorField();

    /**
     * Sets the error field of this ivld average shelf life.
     *
     * @param errorField the error field of this ivld average shelf life
     */
    public void setErrorField(String errorField);

    /**
     * Returns the error code of this ivld average shelf life.
     *
     * @return the error code of this ivld average shelf life
     */
    @AutoEscape
    public String getErrorCode();

    /**
     * Sets the error code of this ivld average shelf life.
     *
     * @param errorCode the error code of this ivld average shelf life
     */
    public void setErrorCode(String errorCode);

    /**
     * Returns the intf inserted date of this ivld average shelf life.
     *
     * @return the intf inserted date of this ivld average shelf life
     */
    public Date getIntfInsertedDate();

    /**
     * Sets the intf inserted date of this ivld average shelf life.
     *
     * @param intfInsertedDate the intf inserted date of this ivld average shelf life
     */
    public void setIntfInsertedDate(Date intfInsertedDate);

    /**
     * Returns the modified by of this ivld average shelf life.
     *
     * @return the modified by of this ivld average shelf life
     */
    @AutoEscape
    public String getModifiedBy();

    /**
     * Sets the modified by of this ivld average shelf life.
     *
     * @param modifiedBy the modified by of this ivld average shelf life
     */
    public void setModifiedBy(String modifiedBy);

    /**
     * Returns the reprocessed flag of this ivld average shelf life.
     *
     * @return the reprocessed flag of this ivld average shelf life
     */
    @AutoEscape
    public String getReprocessedFlag();

    /**
     * Sets the reprocessed flag of this ivld average shelf life.
     *
     * @param reprocessedFlag the reprocessed flag of this ivld average shelf life
     */
    public void setReprocessedFlag(String reprocessedFlag);

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
    public int compareTo(IvldAverageShelfLife ivldAverageShelfLife);

    @Override
    public int hashCode();

    @Override
    public CacheModel<IvldAverageShelfLife> toCacheModel();

    @Override
    public IvldAverageShelfLife toEscapedModel();

    @Override
    public IvldAverageShelfLife toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
