package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the GlBalanceMaster service. Represents a row in the &quot;GL_BALANCE_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.GlBalanceMasterImpl}.
 * </p>
 *
 * @author
 * @see GlBalanceMaster
 * @see com.stpl.app.model.impl.GlBalanceMasterImpl
 * @see com.stpl.app.model.impl.GlBalanceMasterModelImpl
 * @generated
 */
public interface GlBalanceMasterModel extends BaseModel<GlBalanceMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a gl balance master model instance should use the {@link GlBalanceMaster} interface instead.
     */

    /**
     * Returns the primary key of this gl balance master.
     *
     * @return the primary key of this gl balance master
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this gl balance master.
     *
     * @param primaryKey the primary key of this gl balance master
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the created by of this gl balance master.
     *
     * @return the created by of this gl balance master
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this gl balance master.
     *
     * @param createdBy the created by of this gl balance master
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the modified by of this gl balance master.
     *
     * @return the modified by of this gl balance master
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this gl balance master.
     *
     * @param modifiedBy the modified by of this gl balance master
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the account ID of this gl balance master.
     *
     * @return the account ID of this gl balance master
     */
    @AutoEscape
    public String getAccountId();

    /**
     * Sets the account ID of this gl balance master.
     *
     * @param accountId the account ID of this gl balance master
     */
    public void setAccountId(String accountId);

    /**
     * Returns the upload date of this gl balance master.
     *
     * @return the upload date of this gl balance master
     */
    public Date getUploadDate();

    /**
     * Sets the upload date of this gl balance master.
     *
     * @param uploadDate the upload date of this gl balance master
     */
    public void setUploadDate(Date uploadDate);

    /**
     * Returns the created date of this gl balance master.
     *
     * @return the created date of this gl balance master
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this gl balance master.
     *
     * @param createdDate the created date of this gl balance master
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the gl balance master sid of this gl balance master.
     *
     * @return the gl balance master sid of this gl balance master
     */
    public int getGlBalanceMasterSid();

    /**
     * Sets the gl balance master sid of this gl balance master.
     *
     * @param glBalanceMasterSid the gl balance master sid of this gl balance master
     */
    public void setGlBalanceMasterSid(int glBalanceMasterSid);

    /**
     * Returns the is active of this gl balance master.
     *
     * @return the is active of this gl balance master
     */
    @AutoEscape
    public String getIsActive();

    /**
     * Sets the is active of this gl balance master.
     *
     * @param isActive the is active of this gl balance master
     */
    public void setIsActive(String isActive);

    /**
     * Returns the batch ID of this gl balance master.
     *
     * @return the batch ID of this gl balance master
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this gl balance master.
     *
     * @param batchId the batch ID of this gl balance master
     */
    public void setBatchId(String batchId);

    /**
     * Returns the modified date of this gl balance master.
     *
     * @return the modified date of this gl balance master
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this gl balance master.
     *
     * @param modifiedDate the modified date of this gl balance master
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the balance of this gl balance master.
     *
     * @return the balance of this gl balance master
     */
    @AutoEscape
    public String getBalance();

    /**
     * Sets the balance of this gl balance master.
     *
     * @param balance the balance of this gl balance master
     */
    public void setBalance(String balance);

    /**
     * Returns the close indicator of this gl balance master.
     *
     * @return the close indicator of this gl balance master
     */
    @AutoEscape
    public String getCloseIndicator();

    /**
     * Sets the close indicator of this gl balance master.
     *
     * @param closeIndicator the close indicator of this gl balance master
     */
    public void setCloseIndicator(String closeIndicator);

    /**
     * Returns the record lock status of this gl balance master.
     *
     * @return the record lock status of this gl balance master
     */
    public boolean getRecordLockStatus();

    /**
     * Returns <code>true</code> if this gl balance master is record lock status.
     *
     * @return <code>true</code> if this gl balance master is record lock status; <code>false</code> otherwise
     */
    public boolean isRecordLockStatus();

    /**
     * Sets whether this gl balance master is record lock status.
     *
     * @param recordLockStatus the record lock status of this gl balance master
     */
    public void setRecordLockStatus(boolean recordLockStatus);

    /**
     * Returns the year of this gl balance master.
     *
     * @return the year of this gl balance master
     */
    @AutoEscape
    public String getYear();

    /**
     * Sets the year of this gl balance master.
     *
     * @param year the year of this gl balance master
     */
    public void setYear(String year);

    /**
     * Returns the period of this gl balance master.
     *
     * @return the period of this gl balance master
     */
    @AutoEscape
    public String getPeriod();

    /**
     * Sets the period of this gl balance master.
     *
     * @param period the period of this gl balance master
     */
    public void setPeriod(String period);

    /**
     * Returns the source of this gl balance master.
     *
     * @return the source of this gl balance master
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this gl balance master.
     *
     * @param source the source of this gl balance master
     */
    public void setSource(String source);

    /**
     * Returns the inserted date of this gl balance master.
     *
     * @return the inserted date of this gl balance master
     */
    public Date getInsertedDate();

    /**
     * Sets the inserted date of this gl balance master.
     *
     * @param insertedDate the inserted date of this gl balance master
     */
    public void setInsertedDate(Date insertedDate);

    /**
     * Returns the account no of this gl balance master.
     *
     * @return the account no of this gl balance master
     */
    @AutoEscape
    public String getAccountNo();

    /**
     * Sets the account no of this gl balance master.
     *
     * @param accountNo the account no of this gl balance master
     */
    public void setAccountNo(String accountNo);

    /**
     * Returns the inbound status of this gl balance master.
     *
     * @return the inbound status of this gl balance master
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this gl balance master.
     *
     * @param inboundStatus the inbound status of this gl balance master
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
    public int compareTo(GlBalanceMaster glBalanceMaster);

    @Override
    public int hashCode();

    @Override
    public CacheModel<GlBalanceMaster> toCacheModel();

    @Override
    public GlBalanceMaster toEscapedModel();

    @Override
    public GlBalanceMaster toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
