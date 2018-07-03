/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.common.CommonLogic;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class Trx7SelectionDTO extends AbstractSelectionDTO {

    private List<String[]> salesVariablesList;

    public Trx7SelectionDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public List<String[]> getSalesVariables() {
        return CommonLogic.getInstance().getArrayListCloned(salesVariablesList);
    }

    @Override
    public void setSalesVariables(List<String[]> salesVariables) {
        this.salesVariablesList = CommonLogic.getInstance().getArrayListCloned(salesVariables);
    }

    @Override
    public boolean isRateGenerateAllowed() {
        return this.getRateDeductionLevel() != 0 && StringUtils.isNotBlank(this.getRateDeductionValue());
    }

}
