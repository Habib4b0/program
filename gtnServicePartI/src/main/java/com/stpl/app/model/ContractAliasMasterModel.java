package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ContractAliasMaster service. Represents a row in the &quot;CONTRACT_ALIAS_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ContractAliasMasterImpl}.
 * </p>
 *
 * @author
 * @see ContractAliasMaster
 * @see com.stpl.app.model.impl.ContractAliasMasterImpl
 * @see com.stpl.app.model.impl.ContractAliasMasterModelImpl
 * @generated
 */
public interface ContractAliasMasterModel extends BaseModel<ContractAliasMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a contract alias master model instance should use the {@link ContractAliasMaster} interface instead.
     */

    /**
     * Returns the primary key of this contract alias master.
     *
     * @return the primary key of this contract alias master
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this contract alias master.
     *
     * @param primaryKey the primary key of this contract alias master
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the contract alias type of this contract alias master.
     *
     * @return the contract alias type of this contract alias master
     */
    public int getContractAliasType();

    /**
     * Sets the contract alias type of this contract alias master.
     *
     * @param contractAliasType the contract alias type of this contract alias master
     */
    public void setContractAliasType(int contractAliasType);

    /**
     * Returns the tp company master sid of this contract alias master.
     *
     * @return the tp company master sid of this contract alias master
     */
    public int getTpCompanyMasterSid();

    /**
     * Sets the tp company master sid of this contract alias master.
     *
     * @param tpCompanyMasterSid the tp company master sid of this contract alias master
     */
    public void setTpCompanyMasterSid(int tpCompanyMasterSid);

    /**
     * Returns the end date of this contract alias master.
     *
     * @return the end date of this contract alias master
     */
    public Date getEndDate();

    /**
     * Sets the end date of this contract alias master.
     *
     * @param endDate the end date of this contract alias master
     */
    public void setEndDate(Date endDate);

    /**
     * Returns the contract alias master sid of this contract alias master.
     *
     * @return the contract alias master sid of this contract alias master
     */
    public int getContractAliasMasterSid();

    /**
     * Sets the contract alias master sid of this contract alias master.
     *
     * @param contractAliasMasterSid the contract alias master sid of this contract alias master
     */
    public void setContractAliasMasterSid(int contractAliasMasterSid);

    /**
     * Returns the modified date of this contract alias master.
     *
     * @return the modified date of this contract alias master
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this contract alias master.
     *
     * @param modifiedDate the modified date of this contract alias master
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the contract alias no of this contract alias master.
     *
     * @return the contract alias no of this contract alias master
     */
    @AutoEscape
    public String getContractAliasNo();

    /**
     * Sets the contract alias no of this contract alias master.
     *
     * @param contractAliasNo the contract alias no of this contract alias master
     */
    public void setContractAliasNo(String contractAliasNo);

    /**
     * Returns the record lock status of this contract alias master.
     *
     * @return the record lock status of this contract alias master
     */
    public boolean getRecordLockStatus();

    /**
     * Returns <code>true</code> if this contract alias master is record lock status.
     *
     * @return <code>true</code> if this contract alias master is record lock status; <code>false</code> otherwise
     */
    public boolean isRecordLockStatus();

    /**
     * Sets whether this contract alias master is record lock status.
     *
     * @param recordLockStatus the record lock status of this contract alias master
     */
    public void setRecordLockStatus(boolean recordLockStatus);

    /**
     * Returns the start date of this contract alias master.
     *
     * @return the start date of this contract alias master
     */
    public Date getStartDate();

    /**
     * Sets the start date of this contract alias master.
     *
     * @param startDate the start date of this contract alias master
     */
    public void setStartDate(Date startDate);

    /**
     * Returns the created date of this contract alias master.
     *
     * @return the created date of this contract alias master
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this contract alias master.
     *
     * @param createdDate the created date of this contract alias master
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the source of this contract alias master.
     *
     * @return the source of this contract alias master
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this contract alias master.
     *
     * @param source the source of this contract alias master
     */
    public void setSource(String source);

    /**
     * Returns the created by of this contract alias master.
     *
     * @return the created by of this contract alias master
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this contract alias master.
     *
     * @param createdBy the created by of this contract alias master
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the contract master sid of this contract alias master.
     *
     * @return the contract master sid of this contract alias master
     */
    public int getContractMasterSid();

    /**
     * Sets the contract master sid of this contract alias master.
     *
     * @param contractMasterSid the contract master sid of this contract alias master
     */
    public void setContractMasterSid(int contractMasterSid);

    /**
     * Returns the batch ID of this contract alias master.
     *
     * @return the batch ID of this contract alias master
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this contract alias master.
     *
     * @param batchId the batch ID of this contract alias master
     */
    public void setBatchId(String batchId);

    /**
     * Returns the contract alias name of this contract alias master.
     *
     * @return the contract alias name of this contract alias master
     */
    @AutoEscape
    public String getContractAliasName();

    /**
     * Sets the contract alias name of this contract alias master.
     *
     * @param contractAliasName the contract alias name of this contract alias master
     */
    public void setContractAliasName(String contractAliasName);

    /**
     * Returns the modified by of this contract alias master.
     *
     * @return the modified by of this contract alias master
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this contract alias master.
     *
     * @param modifiedBy the modified by of this contract alias master
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the inbound status of this contract alias master.
     *
     * @return the inbound status of this contract alias master
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this contract alias master.
     *
     * @param inboundStatus the inbound status of this contract alias master
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
    public int compareTo(ContractAliasMaster contractAliasMaster);

    @Override
    public int hashCode();

    @Override
    public CacheModel<ContractAliasMaster> toCacheModel();

    @Override
    public ContractAliasMaster toEscapedModel();

    @Override
    public ContractAliasMaster toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
