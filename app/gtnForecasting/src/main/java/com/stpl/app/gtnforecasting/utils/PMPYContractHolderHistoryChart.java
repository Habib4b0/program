/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.vaadin.ui.Component;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PMPYContractHolderHistoryChart.
 *
 * @author lokeshwari
 */
public class PMPYContractHolderHistoryChart {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PMPYContractHolderHistoryChart.class);

    protected String contractName = StringUtils.EMPTY;
    protected List<Object> headeres = null;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param contractName
     * @param headeres
     */
    public PMPYContractHolderHistoryChart(final String contractName, List<Object> headeres) {
        LOGGER.debug("Entering PMPYContractHolderHistoryChart method ");

        this.contractName = contractName;
        this.headeres = headeres;
        LOGGER.debug("End of PMPYContractHolderHistoryChart method ");

    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getCharts() {
        LOGGER.debug("Entering getChart method ");
        final VerticalLayout lay = new VerticalLayout();

        LOGGER.debug("End of getChart method ");
        return lay;
    }

}
