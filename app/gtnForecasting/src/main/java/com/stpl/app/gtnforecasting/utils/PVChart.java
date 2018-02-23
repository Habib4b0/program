/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    /**
 *
 * @author srithar
 */
public class PVChart extends Window {

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
    private List<ProjectionVarianceDTO> dto;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(PPAChart.class);
    /**
     * projSelDTO;
     */
    protected PVSelectionDTO projSelDTO;

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */
    public PVChart(final List<ProjectionVarianceDTO> dto, final String frequency, final String history, CustomTableHeaderDTO rightDto, PVSelectionDTO projSelDTO) {
        LOGGER.debug("Entering PPAChart method ");
        this.frequency = frequency;
        this.history = history;
        this.dto = dto == null ? dto : new ArrayList<>(dto);
        this.rightDto = rightDto;
        this.projSelDTO = projSelDTO;
        setContent(getChart());
        LOGGER.debug("End of PPAChart method ");
    }

    /**
     * Gets the chart.
     *
     * @return the chart
     */
    public Component getChart() {
        LOGGER.debug("Entering getChart method ");
        try {
            return new Label();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<ProjectionVarianceDTO> getDto() {
        return dto == null ? dto : new ArrayList<>(dto);
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<ProjectionVarianceDTO> dto) {
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
