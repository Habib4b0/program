/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sooriya.lakshmanan
 */
public class MandatedChartUtils {

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
    private List<?> dto;
    /**
     * The frequency.
     */
    private String frequency;
    /**
     * The history.
     */
    protected String history;

    protected CustomTableHeaderDTO rightDto;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MandatedChartUtils.class);

    protected String screenName;

    protected ProjectionSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public MandatedChartUtils(final List<?> dto, final String frequency, final String history, CustomTableHeaderDTO rightDto, String screenName, ProjectionSelectionDTO projectionDTO) {
        LOGGER.debug("Entering SPRChart method ");
        this.frequency = frequency;
        this.history = history;
        this.dto = dto == null ? dto : new ArrayList<>(dto);
        this.rightDto = rightDto;
        this.screenName = screenName;
        this.projSelDTO = projectionDTO;
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
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<?> dto) {
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
        this.history = history;
    }
}
