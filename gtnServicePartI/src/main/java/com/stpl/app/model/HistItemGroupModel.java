package com.stpl.app.model;

import com.stpl.app.service.persistence.HistItemGroupPK;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the HistItemGroup service. Represents a row in the &quot;HIST_ITEM_GROUP&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.HistItemGroupModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.HistItemGroupImpl}.
 * </p>
 *
 * @author
 * @see HistItemGroup
 * @see com.stpl.app.model.impl.HistItemGroupImpl
 * @see com.stpl.app.model.impl.HistItemGroupModelImpl
 * @generated
 */
public interface HistItemGroupModel extends BaseModel<HistItemGroup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a hist item group model instance should use the {@link HistItemGroup} interface instead.
     */

    /**
     * Returns the primary key of this hist item group.
     *
     * @return the primary key of this hist item group
     */
    public HistItemGroupPK getPrimaryKey();

    /**
     * Sets the primary key of this hist item group.
     *
     * @param primaryKey the primary key of this hist item group
     */
    public void setPrimaryKey(HistItemGroupPK primaryKey);

    /**
     * Returns the created date of this hist item group.
     *
     * @return the created date of this hist item group
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this hist item group.
     *
     * @param createdDate the created date of this hist item group
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this hist item group.
     *
     * @return the created by of this hist item group
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this hist item group.
     *
     * @param createdBy the created by of this hist item group
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the action flag of this hist item group.
     *
     * @return the action flag of this hist item group
     */
    @AutoEscape
    public String getActionFlag();

    /**
     * Sets the action flag of this hist item group.
     *
     * @param actionFlag the action flag of this hist item group
     */
    public void setActionFlag(String actionFlag);

    /**
     * Returns the item group no of this hist item group.
     *
     * @return the item group no of this hist item group
     */
    @AutoEscape
    public String getItemGroupNo();

    /**
     * Sets the item group no of this hist item group.
     *
     * @param itemGroupNo the item group no of this hist item group
     */
    public void setItemGroupNo(String itemGroupNo);

    /**
     * Returns the version no of this hist item group.
     *
     * @return the version no of this hist item group
     */
    public int getVersionNo();

    /**
     * Sets the version no of this hist item group.
     *
     * @param versionNo the version no of this hist item group
     */
    public void setVersionNo(int versionNo);

    /**
     * Returns the modified by of this hist item group.
     *
     * @return the modified by of this hist item group
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this hist item group.
     *
     * @param modifiedBy the modified by of this hist item group
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the item group description of this hist item group.
     *
     * @return the item group description of this hist item group
     */
    @AutoEscape
    public String getItemGroupDescription();

    /**
     * Sets the item group description of this hist item group.
     *
     * @param itemGroupDescription the item group description of this hist item group
     */
    public void setItemGroupDescription(String itemGroupDescription);

    /**
     * Returns the modified date of this hist item group.
     *
     * @return the modified date of this hist item group
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this hist item group.
     *
     * @param modifiedDate the modified date of this hist item group
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the item group name of this hist item group.
     *
     * @return the item group name of this hist item group
     */
    @AutoEscape
    public String getItemGroupName();

    /**
     * Sets the item group name of this hist item group.
     *
     * @param itemGroupName the item group name of this hist item group
     */
    public void setItemGroupName(String itemGroupName);

    /**
     * Returns the item group sid of this hist item group.
     *
     * @return the item group sid of this hist item group
     */
    public int getItemGroupSid();

    /**
     * Sets the item group sid of this hist item group.
     *
     * @param itemGroupSid the item group sid of this hist item group
     */
    public void setItemGroupSid(int itemGroupSid);

    /**
     * Returns the company master sid of this hist item group.
     *
     * @return the company master sid of this hist item group
     */
    public int getCompanyMasterSid();

    /**
     * Sets the company master sid of this hist item group.
     *
     * @param companyMasterSid the company master sid of this hist item group
     */
    public void setCompanyMasterSid(int companyMasterSid);

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
    public int compareTo(HistItemGroup histItemGroup);

    @Override
    public int hashCode();

    @Override
    public CacheModel<HistItemGroup> toCacheModel();

    @Override
    public HistItemGroup toEscapedModel();

    @Override
    public HistItemGroup toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
