package com.stpl.app.contract.contractheader.dto;

import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * DTO to hold the properties of ContractAlias Master.
 *
 * @author sibi
 */
public class ContractAliasMasterDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 3356391927834158980L;
    /**
     * Contract Alias System Id.
     */
    private int contractAliasSystemId;
    /**
     * Contract system Id.
     */
    private int contractSystemId;
    /**
     * Contract Alias Name.
     */
    private String contractAliasName = StringUtils.EMPTY;
    
    private String tradingName = StringUtils.EMPTY;
    /**
     * Contract Alias Number.
     */
    private String contractAliasNo = StringUtils.EMPTY;
    /**
     * Trading Partner.
     */
    private int tradingPartner;
    /**
     * Trading Partner Name.
     */
    private String tpCompanyMasterSid = StringUtils.EMPTY;

    public String getTpCompanyMasterSid() {
        return tpCompanyMasterSid;
    }

    public void setTpCompanyMasterSid(String tpCompanyMasterSid) {
        this.tpCompanyMasterSid = tpCompanyMasterSid;
    }
    /**
     * Contract Alias Type.
     */
    private HelperDTO contractAliasType = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Contract Alias Type.
     */    
    private String contractAliasDesc = StringUtils.EMPTY;
    /**
     * Start Date.
     */
    private String aliasStartDate = StringUtils.EMPTY;

    public String getAliasStartDate() {
        return aliasStartDate;
    }

    public void setAliasStartDate(String aliasStartDate) {
        this.aliasStartDate = aliasStartDate;
    }
    private String aliasEndDate= StringUtils.EMPTY;

    public String getAliasEndDate() {
        return aliasEndDate;
    }

    public void setAliasEndDate(String aliasEndDate) {
        this.aliasEndDate = aliasEndDate;
    }
      /**
     * End date.
     */
    private String startDate = StringUtils.EMPTY;
    /**
     * End date.
     */
    private String endDate = StringUtils.EMPTY;
    /**
     * Created By.
     */
    private String createdBy;
    /**
     * Modified By.
     */
    private String modifiedBy;
    /**
     * Created Date.
     */
    private Date createdDate;
    /**
     * Modified Date.
     */
    private Date modifiedDate;
    /**
     * Record Lock Status.
     */
    private String recordLockStatus;

    /**
     * Gets the contract alias system id.
     *
     * @return the contract alias system id
     */
    public final int getContractAliasSystemId() {
        return contractAliasSystemId;
    }

    /**
     * Sets the contract alias system id.
     *
     * @param contractAliasSystemId the contract alias system id
     */
    public final void setContractAliasSystemId(final int contractAliasSystemId) {
        this.contractAliasSystemId = contractAliasSystemId;
    }

    /**
     * Gets the contract system id.
     *
     * @return the contract system id
     */
    public final int getContractSystemId() {
        return contractSystemId;
    }

    /**
     * Sets the contract system id.
     *
     * @param contractSystemId the contract system id
     */
    public final void setContractSystemId(final int contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    /**
     * Gets the contract alias name.
     *
     * @return the contract alias name
     */
    public final String getContractAliasName() {
        return contractAliasName;
    }

    /**
     * Sets the contract alias name.
     *
     * @param contractAliasName the contract alias name
     */
    public final void setContractAliasName(final String contractAliasName) {
        this.contractAliasName = contractAliasName;
    }

    /**
     * Gets the contract alias no.
     *
     * @return the contract alias no
     */
    public final String getContractAliasNo() {
        return contractAliasNo;
    }

    /**
     * Sets the contract alias no.
     *
     * @param contractAliasNo the contract alias no
     */
    public final void setContractAliasNo(final String contractAliasNo) {
        this.contractAliasNo = contractAliasNo;
    }

    /**
     * Gets the trading partner.
     *
     * @return the trading partner
     */
    public final int getTradingPartner() {
        return tradingPartner;
    }

    /**
     * Sets the trading partner.
     *
     * @param tradingPartner the trading partner
     */
    public final void setTradingPartner(final int tradingPartner) {
        this.tradingPartner = tradingPartner;
    }

  
    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public final String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the start date
     */
    public final void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public final String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the end date
     */
    public final void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public final String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the created by
     */
    public final void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the modified by.
     *
     * @return the modified by
     */
    public final String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the modified by
     */
    public final void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public final Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the created date
     */
    public final void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public final Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modified date
     */
    public final void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the record lock status.
     *
     * @return the record lock status
     */
    public final String getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * Sets the record lock status.
     *
     * @param recordLockStatus the record lock status
     */
    public final void setRecordLockStatus(final String recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public String getContractAliasDesc() {
        return contractAliasDesc;
    }

    public void setContractAliasDesc(String contractAliasDesc) {
        this.contractAliasDesc = contractAliasDesc;
    }        

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public HelperDTO getContractAliasType() {
        return contractAliasType;
    }

    public void setContractAliasType(HelperDTO contractAliasType) {
        this.contractAliasType = contractAliasType;
    }
        
}
