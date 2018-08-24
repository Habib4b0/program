/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.PMPYRowDto;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PMPYTradingPartnerHistoryChart.
 *
 * @author lokeshwari
 */
public class PMPYTradingPartnerHistoryChart {


    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PMPYTradingPartnerHistoryChart.class);


    protected List<Object> headers = null;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param tpName
     * @param headers
     */
    public PMPYTradingPartnerHistoryChart(final List<PMPYRowDto> dto, String tpName, List<Object> headers) {
        LOGGER.debug("Entering PMPYTradingPartnerHistoryChart method{} ", dto);
        this.headers = headers;
        LOGGER.debug("End of PMPYTradingPartnerHistoryChart method {}, {}" , tpName , headers);
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getCharts() {
        LOGGER.debug("Entering getChart method ");
        final VerticalLayout lay = new VerticalLayout();
        return lay;
    }

}
