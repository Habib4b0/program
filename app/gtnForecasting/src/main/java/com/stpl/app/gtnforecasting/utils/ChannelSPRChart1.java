/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lokeshwari
 */
public class ChannelSPRChart1 {
    /**
     * The annual.
     */
    public static final String ANNUAL = "Annual";
    /**
     * The semi annual.
     */
    public static final String SEMI_ANNUAL = "semi-Annual";
    /**
     * The month.
     */
    public static final String MONTH = "monthly";
    /**
     * The quarter.
     */
    public static final String QUARTER = "quarterly";
    /**
     * The dto.
     */
    private List<SalesProjectionResultsDTO> dto;
    /**
     * The frequency.
     */
    private String frequency;
    /**
     * The history.
     */
    private static String history;

    protected CustomTableHeaderDTO rightDto;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SPRChart.class);
    protected ProjectionSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public ChannelSPRChart1(final List<SalesProjectionResultsDTO> dto, CustomTableHeaderDTO rightDto, ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("Entering SPRChart method ");
        this.frequency = projSelDTO.getFrequency();
        ChannelSPRChart1.history = projSelDTO.getHistory();
        this.dto = dto == null ? dto : new ArrayList<>(dto);
        this.rightDto = rightDto;
        this.projSelDTO = projSelDTO;
        LOGGER.debug("End of SPRChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
        return new Label();
    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<SalesProjectionResultsDTO> getDto() {
        return dto == null ? dto : new ArrayList<>(dto);
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<SalesProjectionResultsDTO> dto) {
        this.dto = dto == null ? dto : new ArrayList<>(dto);
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
    public void setFrequency(final String frequency) {
        this.frequency = frequency;
    }

    /**
     * Gets the history.
     *
     * @return the history
     */
    public String getHistory() {
        return history;
    }

    /**
     * Sets the history.
     *
     * @param history the new history
     */
    public void setHistory(final String history) {
        ChannelSPRChart1.history = history;
    }
}
