/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import java.util.ArrayList;
import com.stpl.app.gtnforecasting.dto.PMPYRowDto;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PMPYTradingPartnerHistoryChart.
 *
 * @author lokeshwari
 */
public class PMPYTradingPartnerHistoryChart {

    /**
     * The dto.
     */
    private final List<PMPYRowDto> dto;

    /**
     * The chart.
     */
    /**
     * The conf.
     */
    /**
     * The chart1.
     */
    /**
     * The conf1.
     */
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PMPYTradingPartnerHistoryChart.class);

    private String tpName = StringUtils.EMPTY;

    protected List<Object> headers = null;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     */
    public PMPYTradingPartnerHistoryChart(final List<PMPYRowDto> dto, String tpName, List<Object> headers) {
        LOGGER.debug("Entering PMPYTradingPartnerHistoryChart method ");
        this.tpName = tpName;
        this.dto = dto == null ? dto : new ArrayList<>(dto);
        this.headers = headers;
        LOGGER.debug("End of PMPYTradingPartnerHistoryChart method ");
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
