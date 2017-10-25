package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ImtdCfpDetails service. Represents a row in the &quot;IMTD_CFP_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ImtdCfpDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ImtdCfpDetailsImpl}.
 * </p>
 *
 * @author
 * @see ImtdCfpDetails
 * @see com.stpl.app.model.impl.ImtdCfpDetailsImpl
 * @see com.stpl.app.model.impl.ImtdCfpDetailsModelImpl
 * @generated
 */
public interface ImtdCfpDetailsModel extends BaseModel<ImtdCfpDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a imtd cfp details model instance should use the {@link ImtdCfpDetails} interface instead.
     */

    /**
     * Returns the primary key of this imtd cfp details.
     *
     * @return the primary key of this imtd cfp details
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this imtd cfp details.
     *
     * @param primaryKey the primary key of this imtd cfp details
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the company no of this imtd cfp details.
     *
     * @return the company no of this imtd cfp details
     */
    @AutoEscape
    public String getCompanyNo();

    /**
     * Sets the company no of this imtd cfp details.
     *
     * @param companyNo the company no of this imtd cfp details
     */
    public void setCompanyNo(String companyNo);

    /**
     * Returns the imtd cfp details sid of this imtd cfp details.
     *
     * @return the imtd cfp details sid of this imtd cfp details
     */
    public int getImtdCfpDetailsSid();

    /**
     * Sets the imtd cfp details sid of this imtd cfp details.
     *
     * @param imtdCfpDetailsSid the imtd cfp details sid of this imtd cfp details
     */
    public void setImtdCfpDetailsSid(int imtdCfpDetailsSid);

    /**
     * Returns the cfp details start date of this imtd cfp details.
     *
     * @return the cfp details start date of this imtd cfp details
     */
    public Date getCfpDetailsStartDate();

    /**
     * Sets the cfp details start date of this imtd cfp details.
     *
     * @param cfpDetailsStartDate the cfp details start date of this imtd cfp details
     */
    public void setCfpDetailsStartDate(Date cfpDetailsStartDate);

    /**
     * Returns the company type of this imtd cfp details.
     *
     * @return the company type of this imtd cfp details
     */
    @AutoEscape
    public String getCompanyType();

    /**
     * Sets the company type of this imtd cfp details.
     *
     * @param companyType the company type of this imtd cfp details
     */
    public void setCompanyType(String companyType);

    /**
     * Returns the cfp details tc start date of this imtd cfp details.
     *
     * @return the cfp details tc start date of this imtd cfp details
     */
    public Date getCfpDetailsTcStartDate();

    /**
     * Sets the cfp details tc start date of this imtd cfp details.
     *
     * @param cfpDetailsTcStartDate the cfp details tc start date of this imtd cfp details
     */
    public void setCfpDetailsTcStartDate(Date cfpDetailsTcStartDate);

    /**
     * Returns the modified by of this imtd cfp details.
     *
     * @return the modified by of this imtd cfp details
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this imtd cfp details.
     *
     * @param modifiedBy the modified by of this imtd cfp details
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the created date of this imtd cfp details.
     *
     * @return the created date of this imtd cfp details
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this imtd cfp details.
     *
     * @param createdDate the created date of this imtd cfp details
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the cfp details tc end date of this imtd cfp details.
     *
     * @return the cfp details tc end date of this imtd cfp details
     */
    public Date getCfpDetailsTcEndDate();

    /**
     * Sets the cfp details tc end date of this imtd cfp details.
     *
     * @param cfpDetailsTcEndDate the cfp details tc end date of this imtd cfp details
     */
    public void setCfpDetailsTcEndDate(Date cfpDetailsTcEndDate);

    /**
     * Returns the cfp details created date of this imtd cfp details.
     *
     * @return the cfp details created date of this imtd cfp details
     */
    public Date getCfpDetailsCreatedDate();

    /**
     * Sets the cfp details created date of this imtd cfp details.
     *
     * @param cfpDetailsCreatedDate the cfp details created date of this imtd cfp details
     */
    public void setCfpDetailsCreatedDate(Date cfpDetailsCreatedDate);

    /**
     * Returns the imtd created date of this imtd cfp details.
     *
     * @return the imtd created date of this imtd cfp details
     */
    public Date getImtdCreatedDate();

    /**
     * Sets the imtd created date of this imtd cfp details.
     *
     * @param imtdCreatedDate the imtd created date of this imtd cfp details
     */
    public void setImtdCreatedDate(Date imtdCreatedDate);

    /**
     * Returns the cfp details modified date of this imtd cfp details.
     *
     * @return the cfp details modified date of this imtd cfp details
     */
    public Date getCfpDetailsModifiedDate();

    /**
     * Sets the cfp details modified date of this imtd cfp details.
     *
     * @param cfpDetailsModifiedDate the cfp details modified date of this imtd cfp details
     */
    public void setCfpDetailsModifiedDate(Date cfpDetailsModifiedDate);

    /**
     * Returns the cfp details attached status of this imtd cfp details.
     *
     * @return the cfp details attached status of this imtd cfp details
     */
    public int getCfpDetailsAttachedStatus();

    /**
     * Sets the cfp details attached status of this imtd cfp details.
     *
     * @param cfpDetailsAttachedStatus the cfp details attached status of this imtd cfp details
     */
    public void setCfpDetailsAttachedStatus(int cfpDetailsAttachedStatus);

    /**
     * Returns the check record of this imtd cfp details.
     *
     * @return the check record of this imtd cfp details
     */
    public boolean getCheckRecord();

    /**
     * Returns <code>true</code> if this imtd cfp details is check record.
     *
     * @return <code>true</code> if this imtd cfp details is check record; <code>false</code> otherwise
     */
    public boolean isCheckRecord();

    /**
     * Sets whether this imtd cfp details is check record.
     *
     * @param checkRecord the check record of this imtd cfp details
     */
    public void setCheckRecord(boolean checkRecord);

    /**
     * Returns the cfp details attached date of this imtd cfp details.
     *
     * @return the cfp details attached date of this imtd cfp details
     */
    public Date getCfpDetailsAttachedDate();

    /**
     * Sets the cfp details attached date of this imtd cfp details.
     *
     * @param cfpDetailsAttachedDate the cfp details attached date of this imtd cfp details
     */
    public void setCfpDetailsAttachedDate(Date cfpDetailsAttachedDate);

    /**
     * Returns the cfp details end date of this imtd cfp details.
     *
     * @return the cfp details end date of this imtd cfp details
     */
    public Date getCfpDetailsEndDate();

    /**
     * Sets the cfp details end date of this imtd cfp details.
     *
     * @param cfpDetailsEndDate the cfp details end date of this imtd cfp details
     */
    public void setCfpDetailsEndDate(Date cfpDetailsEndDate);

    /**
     * Returns the company ID of this imtd cfp details.
     *
     * @return the company ID of this imtd cfp details
     */
    @AutoEscape
    public String getCompanyId();

    /**
     * Sets the company ID of this imtd cfp details.
     *
     * @param companyId the company ID of this imtd cfp details
     */
    public void setCompanyId(String companyId);

    /**
     * Returns the cfp details trade class of this imtd cfp details.
     *
     * @return the cfp details trade class of this imtd cfp details
     */
    @AutoEscape
    public String getCfpDetailsTradeClass();

    /**
     * Sets the cfp details trade class of this imtd cfp details.
     *
     * @param cfpDetailsTradeClass the cfp details trade class of this imtd cfp details
     */
    public void setCfpDetailsTradeClass(String cfpDetailsTradeClass);

    /**
     * Returns the trading partner contract no of this imtd cfp details.
     *
     * @return the trading partner contract no of this imtd cfp details
     */
    @AutoEscape
    public String getTradingPartnerContractNo();

    /**
     * Sets the trading partner contract no of this imtd cfp details.
     *
     * @param tradingPartnerContractNo the trading partner contract no of this imtd cfp details
     */
    public void setTradingPartnerContractNo(String tradingPartnerContractNo);

    /**
     * Returns the created by of this imtd cfp details.
     *
     * @return the created by of this imtd cfp details
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this imtd cfp details.
     *
     * @param createdBy the created by of this imtd cfp details
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the users sid of this imtd cfp details.
     *
     * @return the users sid of this imtd cfp details
     */
    @AutoEscape
    public String getUsersSid();

    /**
     * Sets the users sid of this imtd cfp details.
     *
     * @param usersSid the users sid of this imtd cfp details
     */
    public void setUsersSid(String usersSid);

    /**
     * Returns the company start date of this imtd cfp details.
     *
     * @return the company start date of this imtd cfp details
     */
    public Date getCompanyStartDate();

    /**
     * Sets the company start date of this imtd cfp details.
     *
     * @param companyStartDate the company start date of this imtd cfp details
     */
    public void setCompanyStartDate(Date companyStartDate);

    /**
     * Returns the cfp details modified by of this imtd cfp details.
     *
     * @return the cfp details modified by of this imtd cfp details
     */
    @AutoEscape
    public String getCfpDetailsModifiedBy();

    /**
     * Sets the cfp details modified by of this imtd cfp details.
     *
     * @param cfpDetailsModifiedBy the cfp details modified by of this imtd cfp details
     */
    public void setCfpDetailsModifiedBy(String cfpDetailsModifiedBy);

    /**
     * Returns the company end date of this imtd cfp details.
     *
     * @return the company end date of this imtd cfp details
     */
    public Date getCompanyEndDate();

    /**
     * Sets the company end date of this imtd cfp details.
     *
     * @param companyEndDate the company end date of this imtd cfp details
     */
    public void setCompanyEndDate(Date companyEndDate);

    /**
     * Returns the company master sid of this imtd cfp details.
     *
     * @return the company master sid of this imtd cfp details
     */
    public int getCompanyMasterSid();

    /**
     * Sets the company master sid of this imtd cfp details.
     *
     * @param companyMasterSid the company master sid of this imtd cfp details
     */
    public void setCompanyMasterSid(int companyMasterSid);

    /**
     * Returns the cfp model sid of this imtd cfp details.
     *
     * @return the cfp model sid of this imtd cfp details
     */
    public int getCfpModelSid();

    /**
     * Sets the cfp model sid of this imtd cfp details.
     *
     * @param cfpModelSid the cfp model sid of this imtd cfp details
     */
    public void setCfpModelSid(int cfpModelSid);

    /**
     * Returns the cfp details sid of this imtd cfp details.
     *
     * @return the cfp details sid of this imtd cfp details
     */
    public int getCfpDetailsSid();

    /**
     * Sets the cfp details sid of this imtd cfp details.
     *
     * @param cfpDetailsSid the cfp details sid of this imtd cfp details
     */
    public void setCfpDetailsSid(int cfpDetailsSid);

    /**
     * Returns the company status of this imtd cfp details.
     *
     * @return the company status of this imtd cfp details
     */
    public int getCompanyStatus();

    /**
     * Sets the company status of this imtd cfp details.
     *
     * @param companyStatus the company status of this imtd cfp details
     */
    public void setCompanyStatus(int companyStatus);

    /**
     * Returns the modified date of this imtd cfp details.
     *
     * @return the modified date of this imtd cfp details
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this imtd cfp details.
     *
     * @param modifiedDate the modified date of this imtd cfp details
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the company name of this imtd cfp details.
     *
     * @return the company name of this imtd cfp details
     */
    @AutoEscape
    public String getCompanyName();

    /**
     * Sets the company name of this imtd cfp details.
     *
     * @param companyName the company name of this imtd cfp details
     */
    public void setCompanyName(String companyName);

    /**
     * Returns the operation of this imtd cfp details.
     *
     * @return the operation of this imtd cfp details
     */
    @AutoEscape
    public String getOperation();

    /**
     * Sets the operation of this imtd cfp details.
     *
     * @param operation the operation of this imtd cfp details
     */
    public void setOperation(String operation);

    /**
     * Returns the cfp details created by of this imtd cfp details.
     *
     * @return the cfp details created by of this imtd cfp details
     */
    @AutoEscape
    public String getCfpDetailsCreatedBy();

    /**
     * Sets the cfp details created by of this imtd cfp details.
     *
     * @param cfpDetailsCreatedBy the cfp details created by of this imtd cfp details
     */
    public void setCfpDetailsCreatedBy(String cfpDetailsCreatedBy);

    /**
     * Returns the session ID of this imtd cfp details.
     *
     * @return the session ID of this imtd cfp details
     */
    @AutoEscape
    public String getSessionId();

    /**
     * Sets the session ID of this imtd cfp details.
     *
     * @param sessionId the session ID of this imtd cfp details
     */
    public void setSessionId(String sessionId);

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
    public int compareTo(ImtdCfpDetails imtdCfpDetails);

    @Override
    public int hashCode();

    @Override
    public CacheModel<ImtdCfpDetails> toCacheModel();

    @Override
    public ImtdCfpDetails toEscapedModel();

    @Override
    public ImtdCfpDetails toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
