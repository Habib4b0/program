/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

/**
 *
 * @author abhiram
 */
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.util.List;

/**
 *
 * @author Lokeshwari
 */
public class PRChart {

    /**
     * The dto.
     */
    private List<ProjectionResultsDTO> dto;

    private CustomTableHeaderDTO rightDto;
    /**
     * The Constant LOGGER.
     */
    private ProjectionSelectionDTO projSelDTO;

    public PRChart() {
    }

    /**
     * The Constructor.
     *
     * @param dto the dto
     * @param frequency the frequency
     * @param history the history
     */

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public List<ProjectionResultsDTO> getDto() {
        return dto;
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final List<ProjectionResultsDTO> dto) {
        this.dto = dto;
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

    public static String[] stringListToStringArray(List<String> stringList) {
        String[] stringArray = {};
        if (stringList != null) {
            stringArray = new String[stringList.size()];
            stringArray = stringList.toArray(stringArray);
        }
        return stringArray;
    }

    }
