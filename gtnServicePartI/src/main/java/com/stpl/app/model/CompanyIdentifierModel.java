package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CompanyIdentifier service. Represents a row in the &quot;COMPANY_IDENTIFIER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.CompanyIdentifierImpl}.
 * </p>
 *
 * @author
 * @see CompanyIdentifier
 * @see com.stpl.app.model.impl.CompanyIdentifierImpl
 * @see com.stpl.app.model.impl.CompanyIdentifierModelImpl
 * @generated
 */
public interface CompanyIdentifierModel extends BaseModel<CompanyIdentifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a company identifier model instance should use the {@link CompanyIdentifier} interface instead.
     */

    /**
     * Returns the primary key of this company identifier.
     *
     * @return the primary key of this company identifier
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this company identifier.
     *
     * @param primaryKey the primary key of this company identifier
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the end date of this company identifier.
     *
     * @return the end date of this company identifier
     */
    public Date getEndDate();

    /**
     * Sets the end date of this company identifier.
     *
     * @param endDate the end date of this company identifier
     */
    public void setEndDate(Date endDate);

    /**
     * Returns the company identifier sid of this company identifier.
     *
     * @return the company identifier sid of this company identifier
     */
    public int getCompanyIdentifierSid();

    /**
     * Sets the company identifier sid of this company identifier.
     *
     * @param companyIdentifierSid the company identifier sid of this company identifier
     */
    public void setCompanyIdentifierSid(int companyIdentifierSid);

    /**
     * Returns the modified date of this company identifier.
     *
     * @return the modified date of this company identifier
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this company identifier.
     *
     * @param modifiedDate the modified date of this company identifier
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the identifier status of this company identifier.
     *
     * @return the identifier status of this company identifier
     */
    public int getIdentifierStatus();

    /**
     * Sets the identifier status of this company identifier.
     *
     * @param identifierStatus the identifier status of this company identifier
     */
    public void setIdentifierStatus(int identifierStatus);

    /**
     * Returns the entity code of this company identifier.
     *
     * @return the entity code of this company identifier
     */
    @AutoEscape
    public String getEntityCode();

    /**
     * Sets the entity code of this company identifier.
     *
     * @param entityCode the entity code of this company identifier
     */
    public void setEntityCode(String entityCode);

    /**
     * Returns the record lock status of this company identifier.
     *
     * @return the record lock status of this company identifier
     */
    public boolean getRecordLockStatus();

    /**
     * Returns <code>true</code> if this company identifier is record lock status.
     *
     * @return <code>true</code> if this company identifier is record lock status; <code>false</code> otherwise
     */
    public boolean isRecordLockStatus();

    /**
     * Sets whether this company identifier is record lock status.
     *
     * @param recordLockStatus the record lock status of this company identifier
     */
    public void setRecordLockStatus(boolean recordLockStatus);

    /**
     * Returns the start date of this company identifier.
     *
     * @return the start date of this company identifier
     */
    public Date getStartDate();

    /**
     * Sets the start date of this company identifier.
     *
     * @param startDate the start date of this company identifier
     */
    public void setStartDate(Date startDate);

    /**
     * Returns the created date of this company identifier.
     *
     * @return the created date of this company identifier
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this company identifier.
     *
     * @param createdDate the created date of this company identifier
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the source of this company identifier.
     *
     * @return the source of this company identifier
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this company identifier.
     *
     * @param source the source of this company identifier
     */
    public void setSource(String source);

    /**
     * Returns the created by of this company identifier.
     *
     * @return the created by of this company identifier
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this company identifier.
     *
     * @param createdBy the created by of this company identifier
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the company identifier value of this company identifier.
     *
     * @return the company identifier value of this company identifier
     */
    @AutoEscape
    public String getCompanyIdentifierValue();

    /**
     * Sets the company identifier value of this company identifier.
     *
     * @param companyIdentifierValue the company identifier value of this company identifier
     */
    public void setCompanyIdentifierValue(String companyIdentifierValue);

    /**
     * Returns the batch ID of this company identifier.
     *
     * @return the batch ID of this company identifier
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this company identifier.
     *
     * @param batchId the batch ID of this company identifier
     */
    public void setBatchId(String batchId);

    /**
     * Returns the company qualifier sid of this company identifier.
     *
     * @return the company qualifier sid of this company identifier
     */
    public int getCompanyQualifierSid();

    /**
     * Sets the company qualifier sid of this company identifier.
     *
     * @param companyQualifierSid the company qualifier sid of this company identifier
     */
    public void setCompanyQualifierSid(int companyQualifierSid);

    /**
     * Returns the modified by of this company identifier.
     *
     * @return the modified by of this company identifier
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this company identifier.
     *
     * @param modifiedBy the modified by of this company identifier
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the inbound status of this company identifier.
     *
     * @return the inbound status of this company identifier
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this company identifier.
     *
     * @param inboundStatus the inbound status of this company identifier
     */
    public void setInboundStatus(String inboundStatus);

    /**
     * Returns the company master sid of this company identifier.
     *
     * @return the company master sid of this company identifier
     */
    public int getCompanyMasterSid();

    /**
     * Sets the company master sid of this company identifier.
     *
     * @param companyMasterSid the company master sid of this company identifier
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
    public int compareTo(CompanyIdentifier companyIdentifier);

    @Override
    public int hashCode();

    @Override
    public CacheModel<CompanyIdentifier> toCacheModel();

    @Override
    public CompanyIdentifier toEscapedModel();

    @Override
    public CompanyIdentifier toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
