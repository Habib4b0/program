/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

/**
 *
 * @author abhiram
 */
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
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
 * @author Nandhaa
 */
public class DPRChart {

    /**
     * The dto.
     */
    private List<DiscountProjectionResultsDTO> dto;

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
    public DPRChart(final List<DiscountProjectionResultsDTO> dto, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO rightDto) {
        LOGGER.debug("Entering SPRChart method ");
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
    public List<DiscountProjectionResultsDTO> getDto() {
        return dto == null ? dto : new ArrayList<>(dto);
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<DiscountProjectionResultsDTO> dto) {
        this.dto = dto == null ? dto : new ArrayList<>(dto);
    }

    public CustomTableHeaderDTO getRightDto() {
        return rightDto;
    }

    public void setRightDto(CustomTableHeaderDTO rightDto) {
        this.rightDto = rightDto;
    }

    public ProjectionSelectionDTO getProjSelDTO() {
        return projSelDTO;
    }

    public void setProjSelDTO(ProjectionSelectionDTO projSelDTO) {
        this.projSelDTO = projSelDTO;
    }

}
