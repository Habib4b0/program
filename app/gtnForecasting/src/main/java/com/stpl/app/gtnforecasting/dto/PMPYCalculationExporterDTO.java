/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class PMPYCalculationExporterDTO.
 *
 * @author lokeshwari
 */
public class PMPYCalculationExporterDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -7745574284821435089L;

    /**
     * The variable.
     */
    private String variable = StringUtils.EMPTY;

    /**
     * The sales.
     */
    private String sales = StringUtils.EMPTY;

    /**
     * The market share.
     */
    private String marketShare = StringUtils.EMPTY;

    /**
     * The analog lives.
     */
    private String analogLives = StringUtils.EMPTY;

    /**
     * The value per life.
     */
    private String valuePerLife = StringUtils.EMPTY;

    /**
     * The market share1.
     */
    private String marketShare1 = StringUtils.EMPTY;

    /**
     * The projected lives.
     */
    private String projectedLives = StringUtils.EMPTY;

    /**
     * The total sales.
     */
    private String totalSales = StringUtils.EMPTY;

    /**
     * The projection period total.
     */
    private String projectionPeriodTotal = StringUtils.EMPTY;

    /**
     * Gets the variable.
     *
     * @return the variable
     */
    public String getVariable() {
        return variable;
    }

    /**
     * Sets the variable.
     *
     * @param variable the variable to set
     */
    public void setVariable(final String variable) {
        this.variable = variable;
    }

    /**
     * Gets the sales.
     *
     * @return the sales
     */
    public String getSales() {
        return sales;
    }

    /**
     * Sets the sales.
     *
     * @param sales the sales to set
     */
    public void setSales(final String sales) {
        this.sales = sales;
    }

    /**
     * Gets the market share.
     *
     * @return the marketShare
     */
    public String getMarketShare() {
        return marketShare;
    }

    /**
     * Sets the market share.
     *
     * @param marketShare the marketShare to set
     */
    public void setMarketShare(final String marketShare) {
        this.marketShare = marketShare;
    }

    /**
     * Gets the analog lives.
     *
     * @return the analogLives
     */
    public String getAnalogLives() {
        return analogLives;
    }

    /**
     * Sets the analog lives.
     *
     * @param analogLives the analogLives to set
     */
    public void setAnalogLives(final String analogLives) {
        this.analogLives = analogLives;
    }

    /**
     * Gets the value per life.
     *
     * @return the valuePerLife
     */
    public String getValuePerLife() {
        return valuePerLife;
    }

    /**
     * Sets the value per life.
     *
     * @param valuePerLife the valuePerLife to set
     */
    public void setValuePerLife(final String valuePerLife) {
        this.valuePerLife = valuePerLife;
    }

    /**
     * Gets the market share1.
     *
     * @return the marketShare1
     */
    public String getMarketShare1() {
        return marketShare1;
    }

    /**
     * Sets the market share1.
     *
     * @param marketShare1 the marketShare1 to set
     */
    public void setMarketShare1(final String marketShare1) {
        this.marketShare1 = marketShare1;
    }

    /**
     * Gets the projected lives.
     *
     * @return the projectedLives
     */
    public String getProjectedLives() {
        return projectedLives;
    }

    /**
     * Sets the projected lives.
     *
     * @param projectedLives the projectedLives to set
     */
    public void setProjectedLives(final String projectedLives) {
        this.projectedLives = projectedLives;
    }

    /**
     * Gets the total sales.
     *
     * @return the totalSales
     */
    public String getTotalSales() {
        return totalSales;
    }

    /**
     * Sets the total sales.
     *
     * @param totalSales the totalSales to set
     */
    public void setTotalSales(final String totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * Gets the projection period total.
     *
     * @return the projectionPeriodTotal
     */
    public String getProjectionPeriodTotal() {
        return projectionPeriodTotal;
    }

    /**
     * Sets the projection period total.
     *
     * @param projectionPeriodTotal the projectionPeriodTotal to set
     */
    public void setProjectionPeriodTotal(final String projectionPeriodTotal) {
        this.projectionPeriodTotal = projectionPeriodTotal;
    }
}
