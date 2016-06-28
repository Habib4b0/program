package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the PsModel service. Represents a row in the &quot;PS_MODEL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.PsModelModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.PsModelImpl}.
 * </p>
 *
 * @author
 * @see PsModel
 * @see com.stpl.app.model.impl.PsModelImpl
 * @see com.stpl.app.model.impl.PsModelModelImpl
 * @generated
 */
public interface PsModelModel extends BaseModel<PsModel> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a ps model model instance should use the {@link PsModel} interface instead.
     */

    /**
     * Returns the primary key of this ps model.
     *
     * @return the primary key of this ps model
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this ps model.
     *
     * @param primaryKey the primary key of this ps model
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the ps ID of this ps model.
     *
     * @return the ps ID of this ps model
     */
    @AutoEscape
    public String getPsId();

    /**
     * Sets the ps ID of this ps model.
     *
     * @param psId the ps ID of this ps model
     */
    public void setPsId(String psId);

    /**
     * Returns the ps name of this ps model.
     *
     * @return the ps name of this ps model
     */
    @AutoEscape
    public String getPsName();

    /**
     * Sets the ps name of this ps model.
     *
     * @param psName the ps name of this ps model
     */
    public void setPsName(String psName);

    /**
     * Returns the ps type of this ps model.
     *
     * @return the ps type of this ps model
     */
    public int getPsType();

    /**
     * Sets the ps type of this ps model.
     *
     * @param psType the ps type of this ps model
     */
    public void setPsType(int psType);

    /**
     * Returns the modified date of this ps model.
     *
     * @return the modified date of this ps model
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this ps model.
     *
     * @param modifiedDate the modified date of this ps model
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the ps category of this ps model.
     *
     * @return the ps category of this ps model
     */
    public int getPsCategory();

    /**
     * Sets the ps category of this ps model.
     *
     * @param psCategory the ps category of this ps model
     */
    public void setPsCategory(int psCategory);

    /**
     * Returns the record lock status of this ps model.
     *
     * @return the record lock status of this ps model
     */
    public boolean getRecordLockStatus();

    /**
     * Returns <code>true</code> if this ps model is record lock status.
     *
     * @return <code>true</code> if this ps model is record lock status; <code>false</code> otherwise
     */
    public boolean isRecordLockStatus();

    /**
     * Sets whether this ps model is record lock status.
     *
     * @param recordLockStatus the record lock status of this ps model
     */
    public void setRecordLockStatus(boolean recordLockStatus);

    /**
     * Returns the ps status of this ps model.
     *
     * @return the ps status of this ps model
     */
    public int getPsStatus();

    /**
     * Sets the ps status of this ps model.
     *
     * @param psStatus the ps status of this ps model
     */
    public void setPsStatus(int psStatus);

    /**
     * Returns the created date of this ps model.
     *
     * @return the created date of this ps model
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this ps model.
     *
     * @param createdDate the created date of this ps model
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this ps model.
     *
     * @return the created by of this ps model
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this ps model.
     *
     * @param createdBy the created by of this ps model
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the source of this ps model.
     *
     * @return the source of this ps model
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this ps model.
     *
     * @param source the source of this ps model
     */
    public void setSource(String source);

    /**
     * Returns the ps no of this ps model.
     *
     * @return the ps no of this ps model
     */
    @AutoEscape
    public String getPsNo();

    /**
     * Sets the ps no of this ps model.
     *
     * @param psNo the ps no of this ps model
     */
    public void setPsNo(String psNo);

    /**
     * Returns the ps designation of this ps model.
     *
     * @return the ps designation of this ps model
     */
    @AutoEscape
    public String getPsDesignation();

    /**
     * Sets the ps designation of this ps model.
     *
     * @param psDesignation the ps designation of this ps model
     */
    public void setPsDesignation(String psDesignation);

    /**
     * Returns the parent ps ID of this ps model.
     *
     * @return the parent ps ID of this ps model
     */
    @AutoEscape
    public String getParentPsId();

    /**
     * Sets the parent ps ID of this ps model.
     *
     * @param parentPsId the parent ps ID of this ps model
     */
    public void setParentPsId(String parentPsId);

    /**
     * Returns the batch ID of this ps model.
     *
     * @return the batch ID of this ps model
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this ps model.
     *
     * @param batchId the batch ID of this ps model
     */
    public void setBatchId(String batchId);

    /**
     * Returns the ps model sid of this ps model.
     *
     * @return the ps model sid of this ps model
     */
    public int getPsModelSid();

    /**
     * Sets the ps model sid of this ps model.
     *
     * @param psModelSid the ps model sid of this ps model
     */
    public void setPsModelSid(int psModelSid);

    /**
     * Returns the ps end date of this ps model.
     *
     * @return the ps end date of this ps model
     */
    public Date getPsEndDate();

    /**
     * Sets the ps end date of this ps model.
     *
     * @param psEndDate the ps end date of this ps model
     */
    public void setPsEndDate(Date psEndDate);

    /**
     * Returns the ps trade class of this ps model.
     *
     * @return the ps trade class of this ps model
     */
    public int getPsTradeClass();

    /**
     * Sets the ps trade class of this ps model.
     *
     * @param psTradeClass the ps trade class of this ps model
     */
    public void setPsTradeClass(int psTradeClass);

    /**
     * Returns the modified by of this ps model.
     *
     * @return the modified by of this ps model
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this ps model.
     *
     * @param modifiedBy the modified by of this ps model
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the inbound status of this ps model.
     *
     * @return the inbound status of this ps model
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this ps model.
     *
     * @param inboundStatus the inbound status of this ps model
     */
    public void setInboundStatus(String inboundStatus);

    /**
     * Returns the ps start date of this ps model.
     *
     * @return the ps start date of this ps model
     */
    public Date getPsStartDate();

    /**
     * Sets the ps start date of this ps model.
     *
     * @param psStartDate the ps start date of this ps model
     */
    public void setPsStartDate(Date psStartDate);

    /**
     * Returns the parent ps name of this ps model.
     *
     * @return the parent ps name of this ps model
     */
    @AutoEscape
    public String getParentPsName();

    /**
     * Sets the parent ps name of this ps model.
     *
     * @param parentPsName the parent ps name of this ps model
     */
    public void setParentPsName(String parentPsName);

    /**
     * Returns the internal notes of this ps model.
     *
     * @return the internal notes of this ps model
     */
    @AutoEscape
    public String getInternalNotes();

    /**
     * Sets the internal notes of this ps model.
     *
     * @param internalNotes the internal notes of this ps model
     */
    public void setInternalNotes(String internalNotes);

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
    public int compareTo(PsModel psModel);

    @Override
    public int hashCode();

    @Override
    public CacheModel<PsModel> toCacheModel();

    @Override
    public PsModel toEscapedModel();

    @Override
    public PsModel toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
