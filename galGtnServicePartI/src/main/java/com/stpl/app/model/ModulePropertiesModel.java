package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ModuleProperties service. Represents a row in the &quot;MODULE_PROPERTIES&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ModulePropertiesModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ModulePropertiesImpl}.
 * </p>
 *
 * @author
 * @see ModuleProperties
 * @see com.stpl.app.model.impl.ModulePropertiesImpl
 * @see com.stpl.app.model.impl.ModulePropertiesModelImpl
 * @generated
 */
public interface ModulePropertiesModel extends BaseModel<ModuleProperties> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a module properties model instance should use the {@link ModuleProperties} interface instead.
     */

    /**
     * Returns the primary key of this module properties.
     *
     * @return the primary key of this module properties
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this module properties.
     *
     * @param primaryKey the primary key of this module properties
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the module property sid of this module properties.
     *
     * @return the module property sid of this module properties
     */
    public int getModulePropertySid();

    /**
     * Sets the module property sid of this module properties.
     *
     * @param modulePropertySid the module property sid of this module properties
     */
    public void setModulePropertySid(int modulePropertySid);

    /**
     * Returns the created by of this module properties.
     *
     * @return the created by of this module properties
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this module properties.
     *
     * @param createdBy the created by of this module properties
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the module name of this module properties.
     *
     * @return the module name of this module properties
     */
    @AutoEscape
    public String getModuleName();

    /**
     * Sets the module name of this module properties.
     *
     * @param moduleName the module name of this module properties
     */
    public void setModuleName(String moduleName);

    /**
     * Returns the modified by of this module properties.
     *
     * @return the modified by of this module properties
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this module properties.
     *
     * @param modifiedBy the modified by of this module properties
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the created date of this module properties.
     *
     * @return the created date of this module properties
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this module properties.
     *
     * @param createdDate the created date of this module properties
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the null flag of this module properties.
     *
     * @return the null flag of this module properties
     */
    @AutoEscape
    public String getNullFlag();

    /**
     * Sets the null flag of this module properties.
     *
     * @param nullFlag the null flag of this module properties
     */
    public void setNullFlag(String nullFlag);

    /**
     * Returns the version no of this module properties.
     *
     * @return the version no of this module properties
     */
    public int getVersionNo();

    /**
     * Sets the version no of this module properties.
     *
     * @param versionNo the version no of this module properties
     */
    public void setVersionNo(int versionNo);

    /**
     * Returns the module submodule sid of this module properties.
     *
     * @return the module submodule sid of this module properties
     */
    public int getModuleSubmoduleSid();

    /**
     * Sets the module submodule sid of this module properties.
     *
     * @param moduleSubmoduleSid the module submodule sid of this module properties
     */
    public void setModuleSubmoduleSid(int moduleSubmoduleSid);

    /**
     * Returns the category name of this module properties.
     *
     * @return the category name of this module properties
     */
    @AutoEscape
    public String getCategoryName();

    /**
     * Sets the category name of this module properties.
     *
     * @param categoryName the category name of this module properties
     */
    public void setCategoryName(String categoryName);

    /**
     * Returns the property name of this module properties.
     *
     * @return the property name of this module properties
     */
    @AutoEscape
    public String getPropertyName();

    /**
     * Sets the property name of this module properties.
     *
     * @param propertyName the property name of this module properties
     */
    public void setPropertyName(String propertyName);

    /**
     * Returns the display name of this module properties.
     *
     * @return the display name of this module properties
     */
    @AutoEscape
    public String getDisplayName();

    /**
     * Sets the display name of this module properties.
     *
     * @param displayName the display name of this module properties
     */
    public void setDisplayName(String displayName);

    /**
     * Returns the modified date of this module properties.
     *
     * @return the modified date of this module properties
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this module properties.
     *
     * @param modifiedDate the modified date of this module properties
     */
    public void setModifiedDate(Date modifiedDate);

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
    public int compareTo(ModuleProperties moduleProperties);

    @Override
    public int hashCode();

    @Override
    public CacheModel<ModuleProperties> toCacheModel();

    @Override
    public ModuleProperties toEscapedModel();

    @Override
    public ModuleProperties toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
