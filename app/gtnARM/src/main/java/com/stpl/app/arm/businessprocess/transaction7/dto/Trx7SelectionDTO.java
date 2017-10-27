/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class Trx7SelectionDTO extends AbstractSelectionDTO {

    List<String[]> salesVariablesList;

    @Override
    public List<String[]> getSalesVariables() {
        return salesVariablesList;
    }

    @Override
    public void setSalesVariables(List<String[]> salesVariables) {
        this.salesVariablesList = salesVariables;
    }

    @Override
    public boolean isRateGenerateAllowed() {
        return this.getRateDeductionLevel() != 0 && StringUtils.isNotBlank(this.getRateDeductionValue());
    }

}
