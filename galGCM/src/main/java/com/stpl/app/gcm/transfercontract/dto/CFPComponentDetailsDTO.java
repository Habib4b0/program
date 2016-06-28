/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author harlin
 */
public class CFPComponentDetailsDTO {

    private String tradingPartnerNo = StringUtils.EMPTY;
    private String tradingPartnerName = StringUtils.EMPTY;
    private String tpContractNo = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private String status = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private Date attachedDate;

    public String getTradingPartnerNo() {
        return tradingPartnerNo;
    }

    public void setTradingPartnerNo(String tradingPartnerNo) {
        this.tradingPartnerNo = tradingPartnerNo;
    }

    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getTpContractNo() {
        return tpContractNo;
    }

    public void setTpContractNo(String tpContractNo) {
        this.tpContractNo = tpContractNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public Date getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
    }

}
