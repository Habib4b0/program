package com.stpl.app.galforecasting.nationalassumptions.dto;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 * The Class BaselinePeriodDTO.
 *
 * @author Nadhiya
 */
public class BaselinePeriodDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * The check.
     */
    private Boolean check = false;

    /**
     * The period.
     */
    private String period;

    /**
     * The type.
     */
    private String type;
    
    /** The price type ddlb. */
    private String priceTypeDdlb = StringUtils.EMPTY;
    ;
    
    /** The growth rate. */
    private String growthRate=StringUtils.EMPTY;
    
    /** The baseline start period. */
    private String baselineStartPeriod;
    
    /** The baseline end period. */
    private String baselineEndPeriod;
   
    /** The baseline methodology. */
    private String baselineMethodology = StringUtils.EMPTY;
    
    /** The forecast methodology. */
    private String forecastMethodology = StringUtils.EMPTY;
    
    /** The frequency ddlb. */
    private String frequencyDdlb = StringUtils.EMPTY;
    
    /** The rolling start period. */
    private String rollingStartPeriod;
    
    /** The rolling end period. */
    private String rollingEndPeriod;

    /**
     * Gets the price type ddlb.
     *
     * @return the price type ddlb
     */
    public String getPriceTypeDdlb() {
        return priceTypeDdlb;
    }

    /**
     * Gets the rolling start period.
     *
     * @return the rolling start period
     */
    public String getRollingStartPeriod() {
        return rollingStartPeriod;
    }

    /**
     * Sets the rolling start period.
     *
     * @param rollingStartPeriod the new rolling start period
     */
    public void setRollingStartPeriod(String rollingStartPeriod) {
        this.rollingStartPeriod = rollingStartPeriod;
    }

    /**
     * Gets the rolling end period.
     *
     * @return the rolling end period
     */
    public String getRollingEndPeriod() {
        return rollingEndPeriod;
    }

    /**
     * Sets the rolling end period.
     *
     * @param rollingEndPeriod the new rolling end period
     */
    public void setRollingEndPeriod(String rollingEndPeriod) {
        this.rollingEndPeriod = rollingEndPeriod;
    }

    /**
     * Sets the price type ddlb.
     *
     * @param priceTypeDdlb the new price type ddlb
     */
    public void setPriceTypeDdlb(String priceTypeDdlb) {
        this.priceTypeDdlb = priceTypeDdlb;
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
     * Gets the baseline start period.
     *
     * @return the baseline start period
     */
    public String getBaselineStartPeriod() {
        return baselineStartPeriod;
    }

    /**
     * Sets the baseline start period.
     *
     * @param baselineStartPeriod the new baseline start period
     */
    public void setBaselineStartPeriod(String baselineStartPeriod) {
        this.baselineStartPeriod = baselineStartPeriod;
    }

    /**
     * Gets the baseline end period.
     *
     * @return the baseline end period
     */
    public String getBaselineEndPeriod() {
        return baselineEndPeriod;
    }

    /**
     * Sets the baseline end period.
     *
     * @param baselineEndPeriod the new baseline end period
     */
    public void setBaselineEndPeriod(String baselineEndPeriod) {
        this.baselineEndPeriod = baselineEndPeriod;
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
     * Gets the frequency ddlb.
     *
     * @return the frequency ddlb
     */
    public String getFrequencyDdlb() {
        return frequencyDdlb;
    }

    /**
     * Sets the frequency ddlb.
     *
     * @param frequencyDdlb the new frequency ddlb
     */
    public void setFrequencyDdlb(String frequencyDdlb) {
        this.frequencyDdlb = frequencyDdlb;
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
     * Gets the check.
     *
     * @return the check
     */
    public Boolean getCheck() {
        return check;
    }

    /**
     * Sets the check.
     *
     * @param check the new check
     */
    public void setCheck(Boolean check) {
        this.check = check;
    }

    /**
     * Gets the period.
     *
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the period.
     *
     * @param period the new period
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

}
