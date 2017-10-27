/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import java.util.List;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class PipelineAccrualSelectionDTO extends AbstractSelectionDTO {

    List<String[]> salesVariablesList;

    @Override
    public List<String[]> getSalesVariables() {
        return salesVariablesList;
    }

    @Override
    public void setSalesVariables(List<String[]> salesVariables) {
        this.salesVariablesList = salesVariables;
    }

}
