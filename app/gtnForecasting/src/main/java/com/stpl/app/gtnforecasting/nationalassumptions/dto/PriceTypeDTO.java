package com.stpl.app.gtnforecasting.nationalassumptions.dto;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.vaadin.ui.Button;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PriceTypeDTO.
 *
 * @author Nadhiya
 */
public class PriceTypeDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * The price type.
     */
    private String priceType;

    /**
     * The baseline methodology.
     */
    private String baselineMethodology;

    /**
     * The actuals period1.
     */
    private String basePeriod;

    /**
     * The forecast methodology.
     */
    private String forecastMethodology;

    /**
     * The growth rate.
     */
    private String growthRate;

    /**
     * The frequency.
     */
    private String frequency;

    /**
     * The actuals period2.
     */
    private String rollingPeriod;

    /**
     * The start period.
     */
    private String startPeriod;

    /**
     * The end period.
     */
    private String endPeriod;

    /**
     * The symbol.
     */
    private Button symbol;
    private int naProjMasterSid;
    private int userId;
    private int sessionId;
    private String priceBasis;
    private String cpiCompounding;

    public PriceTypeDTO() {
    }

    /**
     * Gets the price type.
     *
     * @return the price type
     */
    public String getPriceType() {
        return priceType;
    }

    /**
     * Sets the price type.
     *
     * @param priceType the new price type
     */
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    /**
     * Gets the baseline methodology.
     *
     * @return the baseline methodology
     */
    public String getBaselineMethodology() {
        return baselineMethodology;
    }

    /**
     * Sets the baseline methodology.
     *
     * @param baselineMethodology the new baseline methodology
     */
    public void setBaselineMethodology(String baselineMethodology) {
        this.baselineMethodology = baselineMethodology;
    }

    /**
     * Gets the forecast methodology.
     *
     * @return the forecast methodology
     */
    public String getForecastMethodology() {
        return forecastMethodology;
    }

    /**
     * Sets the forecast methodology.
     *
     * @param forecastMethodology the new forecast methodology
     */
    public void setForecastMethodology(String forecastMethodology) {
        this.forecastMethodology = forecastMethodology;
    }

    /**
     * Gets the growth rate.
     *
     * @return the growth rate
     */
    public String getGrowthRate() {
        return growthRate;
    }

    /**
     * Sets the growth rate.
     *
     * @param growthRate the new growth rate
     */
    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
    }

    /**
     * Gets the frequency.
     *
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the frequency.
     *
     * @param frequency the new frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * Gets the start period.
     *
     * @return the start period
     */
    public String getStartPeriod() {
        return startPeriod;
    }

    /**
     * Sets the start period.
     *
     * @param startPeriod the new start period
     */
    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    /**
     * Gets the end period.
     *
     * @return the end period
     */
    public String getEndPeriod() {
        return endPeriod;
    }

    /**
     * Sets the end period.
     *
     * @param endPeriod the new end period
     */
    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    /**
     * Gets the symbol.
     *
     * @return the symbol
     */
    public Button getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol.
     *
     * @param symbol the new symbol
     */
    public void setSymbol(Button symbol) {
        this.symbol = symbol;
    }

    public int getNaProjMasterSid() {
        return naProjMasterSid;
    }

    public void setNaProjMasterSid(int naProjMasterSid) {
        this.naProjMasterSid = naProjMasterSid;
    }

    public String getBasePeriod() {
        return basePeriod;
    }

    public void setBasePeriod(String basePeriod) {
        this.basePeriod = basePeriod;
    }

    public String getRollingPeriod() {
        return rollingPeriod;
    }

    public void setRollingPeriod(String rollingPeriod) {
        this.rollingPeriod = rollingPeriod;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getPriceBasis() {
        return priceBasis;
    }

    public void setPriceBasis(String priceBasis) {
        this.priceBasis = priceBasis;
    }

    public String getCpiCompounding() {
        return cpiCompounding;
    }

    public void setCpiCompounding(String cpiCompounding) {
        this.cpiCompounding = cpiCompounding;
    }

}
