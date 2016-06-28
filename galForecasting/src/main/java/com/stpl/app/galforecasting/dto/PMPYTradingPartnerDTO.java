/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class PMPYTradingPartnerDTO.
 *
 * @author lokeshwari
 */
public class PMPYTradingPartnerDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 4722468959887510125L;

    /**
     * The trading partner no.
     */
    private String tradingPartnerNo = StringUtils.EMPTY;

    /**
     * The trading partner name.
     */
    private String tradingPartnerName = StringUtils.EMPTY;
    
    private int companySysId=0;

    public int getCompanySysId() {
        return companySysId;
    }

    public void setCompanySysId(int companySysId) {
        this.companySysId = companySysId;
    }
    

    /**
     * Gets the trading partner no.
     *
     * @return the tradingPartnerNo
     */
    public String getTradingPartnerNo() {
        return tradingPartnerNo;
    }

    /**
     * Sets the trading partner no.
     *
     * @param tradingPartnerNo the tradingPartnerNo to set
     */
    public void setTradingPartnerNo(final String tradingPartnerNo) {
        this.tradingPartnerNo = tradingPartnerNo;
    }

    /**
     * Gets the trading partner name.
     *
     * @return the tradingPartnerName
     */
    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    /**
     * Sets the trading partner name.
     *
     * @param tradingPartnerName the tradingPartnerName to set
     */
    public void setTradingPartnerName(final String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }
}
