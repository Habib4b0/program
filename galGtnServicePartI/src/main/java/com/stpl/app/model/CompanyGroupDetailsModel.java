package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CompanyGroupDetails service. Represents a row in the &quot;COMPANY_GROUP_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.CompanyGroupDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.CompanyGroupDetailsImpl}.
 * </p>
 *
 * @author
 * @see CompanyGroupDetails
 * @see com.stpl.app.model.impl.CompanyGroupDetailsImpl
 * @see com.stpl.app.model.impl.CompanyGroupDetailsModelImpl
 * @generated
 */
public interface CompanyGroupDetailsModel extends BaseModel<CompanyGroupDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a company group details model instance should use the {@link CompanyGroupDetails} interface instead.
     */

    /**
     * Returns the primary key of this company group details.
     *
     * @return the primary key of this company group details
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this company group details.
     *
     * @param primaryKey the primary key of this company group details
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the created date of this company group details.
     *
     * @return the created date of this company group details
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this company group details.
     *
     * @param createdDate the created date of this company group details
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this company group details.
     *
     * @return the created by of this company group details
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this company group details.
     *
     * @param createdBy the created by of this company group details
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the company parent details sid of this company group details.
     *
     * @return the company parent details sid of this company group details
     */
    @AutoEscape
    public String getCompanyParentDetailsSid();

    /**
     * Sets the company parent details sid of this company group details.
     *
     * @param companyParentDetailsSid the company parent details sid of this company group details
     */
    public void setCompanyParentDetailsSid(String companyParentDetailsSid);

    /**
     * Returns the company tradeclass sid of this company group details.
     *
     * @return the company tradeclass sid of this company group details
     */
    public int getCompanyTradeclassSid();

    /**
     * Sets the company tradeclass sid of this company group details.
     *
     * @param companyTradeclassSid the company tradeclass sid of this company group details
     */
    public void setCompanyTradeclassSid(int companyTradeclassSid);

    /**
     * Returns the company group sid of this company group details.
     *
     * @return the company group sid of this company group details
     */
    public int getCompanyGroupSid();

    /**
     * Sets the company group sid of this company group details.
     *
     * @param companyGroupSid the company group sid of this company group details
     */
    public void setCompanyGroupSid(int companyGroupSid);

    /**
     * Returns the version no of this company group details.
     *
     * @return the version no of this company group details
     */
    public int getVersionNo();

    /**
     * Sets the version no of this company group details.
     *
     * @param versionNo the version no of this company group details
     */
    public void setVersionNo(int versionNo);

    /**
     * Returns the company group details sid of this company group details.
     *
     * @return the company group details sid of this company group details
     */
    public int getCompanyGroupDetailsSid();

    /**
     * Sets the company group details sid of this company group details.
     *
     * @param companyGroupDetailsSid the company group details sid of this company group details
     */
    public void setCompanyGroupDetailsSid(int companyGroupDetailsSid);

    /**
     * Returns the modified by of this company group details.
     *
     * @return the modified by of this company group details
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this company group details.
     *
     * @param modifiedBy the modified by of this company group details
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the modified date of this company group details.
     *
     * @return the modified date of this company group details
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this company group details.
     *
     * @param modifiedDate the modified date of this company group details
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the company master sid of this company group details.
     *
     * @return the company master sid of this company group details
     */
    public int getCompanyMasterSid();

    /**
     * Sets the company master sid of this company group details.
     *
     * @param companyMasterSid the company master sid of this company group details
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
    public int compareTo(CompanyGroupDetails companyGroupDetails);

    @Override
    public int hashCode();

    @Override
    public CacheModel<CompanyGroupDetails> toCacheModel();

    @Override
    public CompanyGroupDetails toEscapedModel();

    @Override
    public CompanyGroupDetails toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
