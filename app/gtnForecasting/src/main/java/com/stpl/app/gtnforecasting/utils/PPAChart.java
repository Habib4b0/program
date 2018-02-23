/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.pparesults.dto.PPAProjectionResultsDTO;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.headers.TableHeaderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class PPAChart {

    /**
     * The annual.
     */
    protected static String annual = "Annual";
    /**
     * The semi annual.
     */
    protected static String semiAnnual = "semi-Annual";
    /**
     * The month.
     */
    protected static String month = "monthly";
    /**
     * The quarter.
     */
    protected static String quarter = "quarterly";
    /**
     * The dto.
     */
    private List<PPAProjectionResultsDTO> dto;
    /**
     * The frequency.
     */
    private String frequency;
    /**
     * The history.
     */
    protected String history;

    protected ProjectionSelectionDTO selection;
    protected TableHeaderDTO fullHeader;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PPAChart.class);

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public PPAChart(final List<PPAProjectionResultsDTO> dto, final String frequency, final String history, ProjectionSelectionDTO selection, TableHeaderDTO rightDto) {
        LOGGER.debug("Entering PPAChart method ");
        this.frequency = frequency;
        this.history = history;
        this.dto = dto == null ? dto : new ArrayList<>(dto);
        this.selection = selection;
        this.fullHeader = rightDto;
        LOGGER.debug("End of PPAChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
        LOGGER.debug("Entering getChart method ");
        return new Label();
    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<PPAProjectionResultsDTO> getDto() {
        return dto == null ? dto : new ArrayList<>(dto);
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<PPAProjectionResultsDTO> dto) {
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
