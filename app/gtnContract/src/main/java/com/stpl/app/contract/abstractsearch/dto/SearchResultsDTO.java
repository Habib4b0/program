/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.abstractsearch.dto;

import com.stpl.app.contract.util.Constants;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 * The Class SearchResultsDTO.
 *
 * @author pvinoth
 */
public class SearchResultsDTO implements Serializable {
    
    /** The contract id. */
    private String contractId = StringUtils.EMPTY;
    
    /** The contract no. */
    private String contractNo = StringUtils.EMPTY;
    
    /** The contract name. */
    private String contractName = StringUtils.EMPTY;
    
    /** The contract status. */
    private String contractStatus = Constants.ZEROSTRING;
    
    /** The contract type. */
    private String contractType = Constants.ZEROSTRING;
    
    /** The trade class. */
    private String tradeClass = StringUtils.EMPTY;
    
    /** The trading partner name. */
    private String tradingPartnerName = StringUtils.EMPTY;
    
    /** The contract system id. */
    private int contractSystemId;
    
    /** The record lock status. */
    private String recordLockStatus = StringUtils.EMPTY;

    /**
     * Gets the contract id.
     *
     * @return the contract id
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * Sets the contract id.
     *
     * @param contractId the new contract id
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    /**
     * Gets the contract no.
     *
     * @return the contract no
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * Sets the contract no.
     *
     * @param contractNo the new contract no
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * Gets the contract name.
     *
     * @return the contract name
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * Sets the contract name.
     *
     * @param contractName the new contract name
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    /**
     * Gets the contract status.
     *
     * @return the contract status
     */
    public String getContractStatus() {
        return contractStatus;
    }

    /**
     * Sets the contract status.
     *
     * @param contractStatus the new contract status
     */
    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    /**
     * Gets the contract type.
     *
     * @return the contract type
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * Sets the contract type.
     *
     * @param contractType the new contract type
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * Gets the trade class.
     *
     * @return the trade class
     */
    public String getTradeClass() {
        return tradeClass;
    }

    /**
     * Sets the trade class.
     *
     * @param tradeClass the new trade class
     */
    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    /**
     * Gets the trading partner name.
     *
     * @return the trading partner name
     */
    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    /**
     * Sets the trading partner name.
     *
     * @param tradingPartnerName the new trading partner name
     */
    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    /**
     * Gets the contract system id.
     *
     * @return the contract system id
     */
    public int getContractSystemId() {
        return contractSystemId;
    }

    /**
     * Sets the contract system id.
     *
     * @param contractSystemId the new contract system id
     */
    public void setContractSystemId(int contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    /**
     * Gets the record lock status.
     *
     * @return the record lock status
     */
    public String getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * Sets the record lock status.
     *
     * @param recordLockStatus the new record lock status
     */
    public void setRecordLockStatus(String recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    
}
