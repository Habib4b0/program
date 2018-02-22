/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.ui.Window;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class PVChart extends Window {

    /**
     * The annual.
     */
    protected static final String ANNUAL = "Annual";
    /**
     * The semi annual.
     */
    protected static final String SEMI_ANNUAL = "semi-Annual";
    /**
     * The MONTH.
     */
    protected static final String MONTH = "monthly";
    /**
     * The QUARTER.
     */
    protected static final String QUARTER = "quarterly";
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
    private String history;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PVChart.class);
    /**
     * projSelDTO;
     */

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
        this.dto = dto == null ? dto : Collections.unmodifiableList(dto);
        LOGGER.debug("End of PPAChart method ");
    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<ProjectionVarianceDTO> getDto() {
        return dto == null ? dto : Collections.unmodifiableList(dto);
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<ProjectionVarianceDTO> dto) {
        this.dto = dto == null ? dto : Collections.unmodifiableList(dto);
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
