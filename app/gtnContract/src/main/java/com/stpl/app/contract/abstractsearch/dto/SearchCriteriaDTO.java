/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.abstractsearch.dto;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 * The Class SearchCriteriaDTO.
 *
 * @author pvinoth
 */
public class SearchCriteriaDTO implements Serializable {
    
    /** The text1. */
    private String text1 = StringUtils.EMPTY;
    
    /** The text2. */
    private String text2 = StringUtils.EMPTY;
    
    /** The text3. */
    private String text3 = StringUtils.EMPTY;
    
    /** The text4. */
    private String text4 = StringUtils.EMPTY;
    
    /** The text5. */
    private String text5 = StringUtils.EMPTY;
    
    /** The combo1. */
    private HelperDTO combo1 = new HelperDTO(0, ConstantUtil.SELECT_ONE);
    
    /** The combo2. */
    private HelperDTO combo2 = new HelperDTO(0, ConstantUtil.SELECT_ONE);
    
    /** The trading partner system id. */
    private int tradingPartnerSystemId;

    /**
     * Gets the text1.
     *
     * @return the text1
     */
    public String getText1() {
        return text1;
    }

    /**
     * Sets the text1.
     *
     * @param text1 the new text1
     */
    public void setText1(String text1) {
        this.text1 = text1;
    }

    /**
     * Gets the text2.
     *
     * @return the text2
     */
    public String getText2() {
        return text2;
    }

    /**
     * Sets the text2.
     *
     * @param text2 the new text2
     */
    public void setText2(String text2) {
        this.text2 = text2;
    }

    /**
     * Gets the text3.
     *
     * @return the text3
     */
    public String getText3() {
        return text3;
    }

    /**
     * Sets the text3.
     *
     * @param text3 the new text3
     */
    public void setText3(String text3) {
        this.text3 = text3;
    }

    /**
     * Gets the text4.
     *
     * @return the text4
     */
    public String getText4() {
        return text4;
    }

    /**
     * Sets the text4.
     *
     * @param text4 the new text4
     */
    public void setText4(String text4) {
        this.text4 = text4;
    }

    /**
     * Gets the text5.
     *
     * @return the text5
     */
    public String getText5() {
        return text5;
    }

    /**
     * Sets the text5.
     *
     * @param text5 the new text5
     */
    public void setText5(String text5) {
        this.text5 = text5;
    }

    /**
     * Gets the combo1.
     *
     * @return the combo1
     */
    public HelperDTO getCombo1() {
        return combo1;
    }

    /**
     * Sets the combo1.
     *
     * @param combo1 the new combo1
     */
    public void setCombo1(HelperDTO combo1) {
        this.combo1 = combo1;
    }

    /**
     * Gets the combo2.
     *
     * @return the combo2
     */
    public HelperDTO getCombo2() {
        return combo2;
    }

    /**
     * Sets the combo2.
     *
     * @param combo2 the new combo2
     */
    public void setCombo2(HelperDTO combo2) {
        this.combo2 = combo2;
    }

    /**
     * Gets the trading partner system id.
     *
     * @return the trading partner system id
     */
    public int getTradingPartnerSystemId() {
        return tradingPartnerSystemId;
    }

    /**
     * Sets the trading partner system id.
     *
     * @param tradingPartnerSystemId the new trading partner system id
     */
    public void setTradingPartnerSystemId(int tradingPartnerSystemId) {
        this.tradingPartnerSystemId = tradingPartnerSystemId;
    }
    
    
}
